package com.palpites.esportivos.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name= "classificacao")
@Entity(name = "Classificacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Classificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    private String season;
    private int timeId;
    private int classificacao;
    private String nome;
    private int pontos;
    private String descricao;
    private int jogos;
    private int vitorias;
    private int derrotas;
    private int empates;
    private int golsFeitos;
    private int golsSofridos;
    private int jogosEmCasa;
    private int vitoriasEmCasa;
    private int derrotasEmCasa;
    private int empatesEmCasa;
    private int golsFeitosEmCasa;
    private int golsSofridosEmCasa;
    private int jogosFora;
    private int vitoriasFora;
    private int derrotasFora;
    private int empatesFora;
    private int golsFeitosFora;
    private int golsSofridosFora;

}
