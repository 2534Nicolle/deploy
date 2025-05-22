package com.query.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.query.entities.Aluno;
import com.query.service.AlunoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

//@Tag(name= " Módulo Alunos - ", description = "API REST - GERENCIAMENTO DE ALUNOS")
@Tag(name = "Modulo Alunos ", description = "API DE CONTROLE DE ALUNOS")
@RestController
@RequestMapping(value ="/aluno")

public class AlunoController {
    
    private final AlunoService alunoService;
    
    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }
    //Query Method
    @Operation(summary = "Localiza cidade por nome")
    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<Aluno>> buscarAlunosPorCidade(@PathVariable String cidade) {
      List<Aluno> alunos = alunoService.buscarAlunosPorCidade(cidade);
      return ResponseEntity.ok(alunos);
    }
    @Operation(summary = "Localiza aluno por nome")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Aluno>> buscarAlunosPorNome(@PathVariable String nome) {
      List<Aluno> alunos = alunoService.buscarAlunosPorNome(nome);
      return ResponseEntity.ok(alunos);
    }
    @Operation(summary = "Localizar aluno por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getProductById(@PathVariable Long id) {
        Aluno aluno = alunoService.getAlunoById(id);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> getAllAlunos() {
        List<Aluno> alunos = alunoService.getAllAlunos();
        return ResponseEntity.ok(alunos);
    }

    @PostMapping("/")
    public ResponseEntity<Aluno> criarAluno(@RequestBody @Valid Aluno aluno) {
        Aluno criarAluno = alunoService.salvarAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarAluno);
    }
   

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @RequestBody @Valid Aluno aluno) {
        Aluno updatedAluno = alunoService.updateAluno(id, aluno);
        if (updatedAluno != null) {
            return ResponseEntity.ok(updatedAluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> deleteAluno(@PathVariable Long id) {
        boolean deleted = alunoService.deleteAluno(id);
        if (deleted) {
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }
       
 }
/*

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.query.entities.Aluno;
import com.query.service.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluno")

public class AlunoController_com_methods {
    
    private final AlunoService alunoService;
    
    @Autowired
    public AlunoController_com_methods(AlunoService alunoService) {
        this.alunoService = alunoService;
    }
    //Query Method
    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<Aluno>> buscarAlunosPorCidade(@PathVariable String cidade) {
      List<Aluno> alunos = alunoService.buscarAlunosPorCidade(cidade);
      return ResponseEntity.ok(alunos);
    }
    //Query Method (Não utulizar esse)
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Aluno>> buscarAlunosPorNome(@PathVariable String nome) {
      List<Aluno> alunos = alunoService.buscarAlunosPorNome(nome);
      return ResponseEntity.ok(alunos);
    }
    
    //@query
    @GetMapping("/nome/{nome}")
    public List<Aluno> findAlunosPorNome(@PathVariable String nome) {
        return alunoService.findByNome(nome);
    }
    //@query
    @GetMapping("/nome-completo/{nomeCompleto}")
    public List<Aluno> findAlunosPorNomeCompletoLike(@PathVariable String nomeCompleto) {
        return alunoService.findByNomeCompletoLike(nomeCompleto);
    }
    
    //@query
    @GetMapping("/turma/{turmaId}")
    public List<Aluno> findAlunosPorTurma(@PathVariable Long turmaId) {
        return alunoService.findByTurmaId(turmaId);
    }
      
    @GetMapping("/nome/{nome}/renda/{renda}")
    public ResponseEntity<List<Aluno>> buscarAlunosPorNomeERenda(@PathVariable String nome,@PathVariable Double renda) {
      List<Aluno> alunos = alunoService.buscarAlunosPorNomeERenda(nome,renda);
      return ResponseEntity.ok(alunos);
    }
    @GetMapping("/cidade/{cidade}/renda/{renda}")
    public ResponseEntity<List<Aluno>> buscarAlunosPorCidadeERenda(@PathVariable String cidade,@PathVariable Double renda) {
      List<Aluno> alunos = alunoService.buscarCidadeERenda(cidade,renda);
      return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getProductById(@PathVariable Long id) {
        Aluno aluno = alunoService.getAlunoById(id);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Aluno>> getAllAlunos() {
        List<Aluno> alunos = alunoService.getAllAlunos();
        return ResponseEntity.ok(alunos);
    }

    @PostMapping("/")
    public ResponseEntity<Aluno> criarAluno(@RequestBody @Valid Aluno aluno) {
        Aluno criarAluno = alunoService.salvarAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarAluno);
    }
   

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @RequestBody @Valid Aluno aluno) {
        Aluno updatedAluno = alunoService.updateAluno(id, aluno);
        if (updatedAluno != null) {
            return ResponseEntity.ok(updatedAluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> deleteAluno(@PathVariable Long id) {
        boolean deleted = alunoService.deleteAluno(id);
        if (deleted) {
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }
       
 }
*/