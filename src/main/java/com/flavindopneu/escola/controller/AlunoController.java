package com.flavindopneu.escola.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flavindopneu.escola.model.AlunoModel;
import com.flavindopneu.escola.service.AlunoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<AlunoModel> criarAluno(@RequestBody AlunoModel alunoModel){
        alunoService.criarAluno(alunoModel);
        return ResponseEntity.ok().body(alunoModel);
    }

    @GetMapping
    public List<AlunoModel> getAlunos(){
        return alunoService.listarAlunos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoModel> atualizarAluno(@PathVariable("id") Long id, @RequestBody AlunoModel alunoAtuazado){
        AlunoModel alunoAtualizadoSalvo = alunoService.atualizarAluno(id, alunoAtuazado);
        return ResponseEntity.ok(alunoAtualizadoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarAluno(@PathVariable("id") Long id){
        alunoService.deletarAluno(id);
        return ResponseEntity.ok().body("Aluno deletado com sucesso");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoModel> obterAlunoPorId(@PathVariable("id") Long id){
        Optional<AlunoModel> alunoEncontrado = alunoService.obterAlunoPorId(id);
        return alunoEncontrado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
