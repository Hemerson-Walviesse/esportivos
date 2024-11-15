package com.palpites.esportivos.controller;

import com.palpites.esportivos.domain.Classificacao;
import com.palpites.esportivos.service.DadosTimesService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/time")
public class DadosTimeController {

    private final DadosTimesService dadosTimesService;

    public DadosTimeController(DadosTimesService dadosTimesService) {
        this.dadosTimesService = dadosTimesService;
    }

    @GetMapping
    @Transactional
    public List<Classificacao> dadosTimes(@RequestParam String league, @RequestParam String season) {
        System.out.println(league + "-" + season);
        return dadosTimesService.obterDadosTimes(league, season);
    }
}
