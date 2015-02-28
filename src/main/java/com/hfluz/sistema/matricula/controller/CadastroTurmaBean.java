/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.controller;

import com.hfluz.sistema.matricula.dao.CursoDAO;
import com.hfluz.sistema.matricula.jsf.FacesUtil;
import com.hfluz.sistema.matricula.modelo.Curso;
import com.hfluz.sistema.matricula.modelo.Turma;
import com.hfluz.sistema.matricula.service.CadastroTurmaService;
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
public class CadastroTurmaBean implements Serializable {
    
    private Turma turma;
    private List<Curso> cursos;
    
    @Inject
    private CadastroTurmaService cadastroTurmaService;
    @Inject
    private CursoDAO cursoDAO;
    
    public void salvar() {
        try {
            cadastroTurmaService.salvar(turma);
            FacesUtil.addSuccessMessage("Turma " + turma.getDescricaoSala() + " salvo com sucesso!");
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    @PostConstruct
    public void init() {
        cursos = cursoDAO.buscarTodos();
        limpar();
    }
    
    public void limpar() {
        turma = new Turma();
    }
    
}
