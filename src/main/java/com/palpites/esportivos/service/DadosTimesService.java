package com.palpites.esportivos.service;

import com.google.gson.Gson;
import com.palpites.esportivos.DTO.Classificacoes;
import com.palpites.esportivos.domain.Classificacao;
import com.palpites.esportivos.domain.ClassificacaoRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DadosTimesService {

    private static final String API_URL = "https://v3.football.api-sports.io/standings";
    private static final String API_KEY = "08b1734d8318fc801a12680654cdaba2";

    private OkHttpClient client = new OkHttpClient();
    private final ClassificacaoRepository classificacaoRepository;

    public DadosTimesService(ClassificacaoRepository classificacaoRepository) {
        this.classificacaoRepository = classificacaoRepository;
    }

    public List<Classificacao> obterDadosTimes(String league, String season) {

        List<Classificacao> classificacoesExistentes = classificacaoRepository.findAll();

        if(!classificacoesExistentes.isEmpty()){
            return classificacoesExistentes;
        }
        String url = API_URL + "?league=" + league + "&season=" + season;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", "v3.football.api-sports.io")
                .build();

        try(Response response = client.newCall(request).execute()) {
            if(response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string(); // Armazena o JSON como String
                System.out.println(jsonResponse); // Imprime a resposta JSON para debug

                Gson gson = new Gson();
                Classificacoes classificacoes = gson.fromJson(jsonResponse, Classificacoes.class); // Usa o jsonResponse

                List<Classificacao> classificacoesPSalvar = classificacoes.response.stream()
                        .flatMap(resp -> resp.league.standings.get(0).stream()) // Acessa a primeira lista de standings
                        .map(standing -> {
                            Classificacao classificacao = new Classificacao();
                            classificacao.setClassificacao(standing.rank);
                            classificacao.setNome(standing.team.name);
                            classificacao.setPontos(standing.points);
                            classificacao.setDescricao(standing.description);
                            classificacao.setJogos(standing.all.played);
                            classificacao.setVitorias(standing.all.win);
                            classificacao.setDerrotas(standing.all.lose);
                            classificacao.setEmpates(standing.all.draw);
                            classificacao.setJogosEmCasa(standing.home.played);
                            classificacao.setVitoriasEmCasa(standing.home.win);
                            classificacao.setDerrotasEmCasa(standing.home.lose);
                            classificacao.setEmpatesEmCasa(standing.home.draw);
                            classificacao.setGolsFeitosEmCasa(standing.home.goals.goalsFor);
                            classificacao.setGolsSofridosEmCasa(standing.home.goals.goalsAgainst);
                            classificacao.setJogosFora(standing.away.played);
                            classificacao.setVitoriasFora(standing.away.win);
                            classificacao.setDerrotasFora(standing.away.lose);
                            classificacao.setEmpatesFora(standing.away.draw);
                            classificacao.setGolsFeitosFora(standing.away.goals.goalsFor);
                            classificacao.setGolsSofridosFora(standing.away.goals.goalsAgainst);
                            return classificacao;
                        })
                        .collect(Collectors.toList());

                classificacaoRepository.saveAll(classificacoesPSalvar);
                return classificacoesPSalvar;

            }else {
                System.out.println("Erro ao obter dados de times: " + response.code());
            }
        }catch (IOException e) { System.err.println("Erro ao chamar a API: " + e.getMessage()); } return null;
    }
}
