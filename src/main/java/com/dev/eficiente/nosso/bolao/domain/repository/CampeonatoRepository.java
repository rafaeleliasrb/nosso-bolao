package com.dev.eficiente.nosso.bolao.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.eficiente.nosso.bolao.domain.model.Campeonato;

@Repository
public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {

	Optional<Campeonato> findByNome(String nome);
}
