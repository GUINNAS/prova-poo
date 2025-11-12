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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService){
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<CursoModel> criarCurso(@RequestBody CursoModel cursoModel){
        cursoService.criarCurso(cursoModel);
        return ResponseEntity.ok().body(cursoModel);
    }

    @GetMapping
    public List<CursoModel> getCursos(){
        return cursoService.listarCursos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoModel> atualizarCurso(@PathVariable("id") Long id, @RequestBody CursoModel cursoAtualizado){
        CursoModel cursoAtualizadoSalvo = cursoService.atualizarCurso(id, cursoAtualizado);
        return ResponseEntity.ok(cursoAtualizadoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCurso(@PathVariable("id") Long id){
        cursoService.deletarCurso(id);
        return ResponseEntity.ok().body("Curso Deletado com sucesso");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoModel> obterCursoPorId(@PathVariable("id") Long id){
        Optional<CursoModel> cursoExistente = cursoService.obterCursoPorId(id);
        return cursoExistente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
