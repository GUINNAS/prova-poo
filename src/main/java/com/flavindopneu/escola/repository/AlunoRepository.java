package com.flavindopneu.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flavindopneu.escola.model.AlunoModel;

public interface AlunoRepository extends JpaRepository<AlunoModel, Long> {

}
