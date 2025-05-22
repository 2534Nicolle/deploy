package com.query.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.query.entities.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
	public List<Turma> findByNome(String nome);

}

//List<Turma> findByNomeAndRenda(String nome, String descricao);