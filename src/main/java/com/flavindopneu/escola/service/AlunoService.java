package com.flavindopneu.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flavindopneu.escola.model.AlunoModel;
import com.flavindopneu.escola.repository.AlunoRepository;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public AlunoModel criarAluno(AlunoModel aluno){
        return repository.save(aluno);
    }

    public Optional<AlunoModel> obterAlunoPorId(Long id){
        Optional<AlunoModel> alunoExistente = repository.findById(id);
        return alunoExistente;
    }

    public List<AlunoModel> listarAlunos(){
        return repository.findAll();
    }

    public AlunoModel atualizarAluno(Long id, AlunoModel alunoAtualizado){
        AlunoModel alunoExistente = repository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        alunoExistente.setNome(alunoAtualizado.getNome());
        alunoExistente.setMatricula(alunoAtualizado.getMatricula());
        alunoExistente.setStatus(alunoAtualizado.getStatus());
        return repository.save(alunoExistente);
    }

    public Optional<String> deletarAluno(Long id){
        repository.deleteById(id);
        return Optional.ofNullable("Aluno deletado com sucesso");
    }

}
