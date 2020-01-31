package com.dev.eficiente.nosso.bolao.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.eficiente.nosso.bolao.domain.model.Time;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {

	Optional<Time> findByNome(String nome);
}
