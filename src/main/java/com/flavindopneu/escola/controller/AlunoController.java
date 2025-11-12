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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/alunos")
@Tag(name = "Alunos", description = "Operações relacionadas a alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }

    @PostMapping
    @Operation(summary = "Criar aluno", description = "Cria um novo aluno")
    public ResponseEntity<AlunoModel> criarAluno(@RequestBody AlunoModel alunoModel){
        alunoService.criarAluno(alunoModel);
        return ResponseEntity.ok().body(alunoModel);
    }

    @GetMapping
    @Operation(summary = "Listar alunos", description = "Retorna a lista de todos os alunos")
    public List<AlunoModel> getAlunos(){
        return alunoService.listarAlunos();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar aluno", description = "Atualiza um aluno existente pelo ID")
    public ResponseEntity<AlunoModel> atualizarAluno(@Parameter(description = "ID do aluno") @PathVariable("id") Long id, @RequestBody AlunoModel alunoAtuazado){
        AlunoModel alunoAtualizadoSalvo = alunoService.atualizarAluno(id, alunoAtuazado);
        return ResponseEntity.ok(alunoAtualizadoSalvo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar aluno", description = "Remove um aluno pelo ID")
    public ResponseEntity<String> deletarAluno(@Parameter(description = "ID do aluno") @PathVariable("id") Long id){
        alunoService.deletarAluno(id);
        return ResponseEntity.ok().body("Aluno deletado com sucesso");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter aluno por ID", description = "Retorna um aluno pelo seu ID")
    public ResponseEntity<AlunoModel> obterAlunoPorId(@Parameter(description = "ID do aluno") @PathVariable("id") Long id){
        Optional<AlunoModel> alunoEncontrado = alunoService.obterAlunoPorId(id);
        return alunoEncontrado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
