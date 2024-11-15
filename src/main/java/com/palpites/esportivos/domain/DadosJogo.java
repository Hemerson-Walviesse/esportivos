package com.palpites.esportivos.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "brasileirao")
@Entity(name = "DadosJogo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DadosJogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int jogoId;
    private String nameLeague;
    private String data;
    private String local;
    private String arbitro;
    private String timeCasa;
    private String timeVisitante;
    private Integer placarCasa;
    private Integer placarVisitante;
    private String status;

    private LocalDateTime dataUltimaAtualizacao;


}
