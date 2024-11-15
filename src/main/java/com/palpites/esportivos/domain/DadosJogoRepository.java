package com.palpites.esportivos.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DadosJogoRepository extends JpaRepository<DadosJogo, Long> {
    List<DadosJogo> findByJogoIdAndData(int jogoId, String data);
}
