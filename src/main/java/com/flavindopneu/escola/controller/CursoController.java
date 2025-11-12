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
import org.springframework.web.bind.annotation.RestController;

import com.flavindopneu.escola.model.CursoModel;
import com.flavindopneu.escola.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/cursos")
@Tag(name = "Cursos", description = "Operações relacionadas a cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService){
        this.cursoService = cursoService;
    }

    @PostMapping
    @Operation(summary = "Criar curso", description = "Cria um novo curso")
    public ResponseEntity<CursoModel> criarCurso(@RequestBody CursoModel cursoModel){
        cursoService.criarCurso(cursoModel);
        return ResponseEntity.ok().body(cursoModel);
    }

    @GetMapping
    @Operation(summary = "Listar cursos", description = "Retorna a lista de todos os cursos")
    public List<CursoModel> getCursos(){
        return cursoService.listarCursos();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar curso", description = "Atualiza um curso existente pelo ID")
    public ResponseEntity<CursoModel> atualizarCurso(@Parameter(description = "ID do curso") @PathVariable("id") Long id, @RequestBody CursoModel cursoAtualizado){
        CursoModel cursoAtualizadoSalvo = cursoService.atualizarCurso(id, cursoAtualizado);
        return ResponseEntity.ok(cursoAtualizadoSalvo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar curso", description = "Remove um curso pelo ID")
    public ResponseEntity<String> deletarCurso(@Parameter(description = "ID do curso") @PathVariable("id") Long id){
        cursoService.deletarCurso(id);
        return ResponseEntity.ok().body("Curso Deletado com sucesso");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter curso por ID", description = "Retorna um curso pelo seu ID")
    public ResponseEntity<CursoModel> obterCursoPorId(@Parameter(description = "ID do curso") @PathVariable("id") Long id){
        Optional<CursoModel> cursoExistente = cursoService.obterCursoPorId(id);
        return cursoExistente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
