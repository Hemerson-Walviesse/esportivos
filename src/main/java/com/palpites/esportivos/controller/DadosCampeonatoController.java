package com.palpites.esportivos.controller;

import com.palpites.esportivos.Service.DadosCampeonatoService;
import com.palpites.esportivos.domain.DadosJogo;
import com.palpites.esportivos.domain.DadosJogoRepository;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/resultadoJogos")
public class DadosCampeonatoController {

    private final DadosCampeonatoService dadosCampeonatoService;

    public DadosCampeonatoController(DadosCampeonatoService dadosCampeonatoService) {
        this.dadosCampeonatoService = dadosCampeonatoService;
    }
    @GetMapping
    @Transactional
    public List<DadosJogo> getMatchData(@RequestParam String league, @RequestParam String season) throws IOException {
        return dadosCampeonatoService.getMatchData(league, season);
    }

}
