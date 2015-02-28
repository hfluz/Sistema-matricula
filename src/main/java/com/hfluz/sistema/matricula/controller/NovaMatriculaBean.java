/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.controller;

import com.hfluz.sistema.matricula.dao.AlunoDAO;
import com.hfluz.sistema.matricula.dao.CursoDAO;
import com.hfluz.sistema.matricula.dao.TurmaDAO;
import com.hfluz.sistema.matricula.jsf.FacesUtil;
import com.hfluz.sistema.matricula.modelo.Aluno;
import com.hfluz.sistema.matricula.modelo.Curso;
import com.hfluz.sistema.matricula.modelo.Matricula;
import com.hfluz.sistema.matricula.modelo.Turma;
import com.hfluz.sistema.matricula.service.CadastroMatriculaService;
import com.hfluz.sistema.matricula.service.NegocioException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;

/**
 *
 * @author hfluz
 */
@Named
@ViewScoped
@Data
public class NovaMatriculaBean implements Serializable {
    
    private Matricula matricula;
    private List<Aluno> alunos;
    private List<Turma> turmas;
    private List<Curso> cursos;
    private Curso cursoSelecionado;
    
    @Inject
    private CadastroMatriculaService cadastroMatriculaService;
    @Inject
    private AlunoDAO alunoDAO;
    @Inject
    private CursoDAO cursoDAO;
    @Inject
    private TurmaDAO turmaDAO;
    
    public void salvar() {
        try {
            Matricula matriculaGerenciada = cadastroMatriculaService.salvar(matricula);
            FacesUtil.addSuccessMessage("Matricula " + matriculaGerenciada.getCodigo() + " salva com sucesso!");
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    @PostConstruct
    public void init() {
        alunos = alunoDAO.buscarTodos();
        cursos = cursoDAO.buscarTodos();
        limpar();
    }
    
    public void carregarTurmasPeloCurso(){
        turmas = turmaDAO.buscarPeloCurso(cursoSelecionado);
    }
    
    public void limpar() {
        matricula = new Matricula();
    }
    
}
