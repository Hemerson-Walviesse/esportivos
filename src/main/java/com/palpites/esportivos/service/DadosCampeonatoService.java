package com.palpites.esportivos.Service;
import com.google.gson.Gson;
import com.palpites.esportivos.DTO.DadosJogos;
import com.palpites.esportivos.domain.DadosJogo;
import com.palpites.esportivos.domain.DadosJogoRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DadosCampeonatoService {


    private static final String API_URL = "https://v3.football.api-sports.io/fixtures";
    private static final String API_KEY = "08b1734d8318fc801a12680654cdaba2";

    private OkHttpClient client = new OkHttpClient();
    private final DadosJogoRepository dadosJogoRepository;

    public DadosCampeonatoService(DadosJogoRepository dadosJogoRepository) {
        this.dadosJogoRepository = dadosJogoRepository;
    }

    public List<DadosJogo> getMatchData(String league, String season) throws IOException {

        List<DadosJogo> dadosExistentes = dadosJogoRepository.findAll();

        if(!dadosExistentes.isEmpty()) {
            return dadosExistentes;
        }
        String url = API_URL + "?league=" + league+ "&season=" + season;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", "v3.football.api-sports.io")
                .build();

        try(Response response = client.newCall(request).execute()){
            if(response.isSuccessful() && response.body() != null){

                Gson gson = new Gson();

                DadosJogos dadosJogos = gson.fromJson(response.body().string(), DadosJogos.class);

                List<DadosJogo> dadosParaSalvar = dadosJogos.response.stream().map(resp -> {
                    DadosJogo jogo = new DadosJogo();
                    jogo.setJogoId(resp.fixture.id);
                    jogo.setData(resp.fixture.date);
                    jogo.setLocal(resp.fixture.venue.name + ", " + resp.fixture.venue.city);
                    jogo.setArbitro(resp.fixture.referee);
                    jogo.setTimeCasa(resp.teams.home.name);
                    jogo.setTimeVisitante(resp.teams.away.name);
                    jogo.setPlacarCasa(resp.score.fulltime.home);
                    jogo.setPlacarVisitante(resp.score.fulltime.away);
                    jogo.setStatus(resp.fixture.status.longStatus);
                    jogo.setDataUltimaAtualizacao(LocalDateTime.now());
                    return jogo;
                }).collect(Collectors.toList());

                dadosJogoRepository.saveAll(dadosParaSalvar);
                return dadosParaSalvar;

            }else {

                System.out.println("Erro: " + response.code());
                System.out.println("Resposta do corpo: " + response.body().string());
                throw new IOException("Erro inesperado " + response);
            }
        }
    }
}
