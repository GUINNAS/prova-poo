package com.flavindopneu.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flavindopneu.escola.model.CursoModel;
import com.flavindopneu.escola.repository.AlunoRepository;
import com.flavindopneu.escola.repository.CursoRepository;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository){
        this.repository = repository;
    }

    public CursoModel criarCurso(CursoModel curso){
        return repository.save(curso);
    }

    public Optional<CursoModel> obterCursoPorId(Long id){
        Optional<CursoModel> cursoExistente = repository.findById(id);
        return cursoExistente;
    }

    public List<CursoModel> listarCursos(){
        return repository.findAll();
    }

    public CursoModel atualizarCurso(Long id, CursoModel cursoAtualizado){
        CursoModel cursoExistente = repository.findById(id).orElseThrow(() -> new RuntimeException("Curso nao Encontrado"));
        cursoExistente.setNome(cursoAtualizado.getNome());
        cursoExistente.setCargaHoraria(cursoAtualizado.getCargaHoraria());
        return repository.save(cursoExistente);
    }

    public Optional<String> deletarCurso(Long id){
        repository.deleteById(id);
        return Optional.ofNullable("Curso deletado com sucesso");
    }
}
