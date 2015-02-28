/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.controller;

import com.hfluz.sistema.matricula.jsf.FacesUtil;
import com.hfluz.sistema.matricula.modelo.Curso;
import com.hfluz.sistema.matricula.service.CadastroCursoService;
import com.hfluz.sistema.matricula.service.NegocioException;
import java.io.Serializable;
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
public class CadastroCursoBean implements Serializable {
        
    private Curso curso;
    
    @Inject
    private CadastroCursoService cadastroCursoService;
    
    public void salvar() {
        try {
            cadastroCursoService.salvar(curso);
            FacesUtil.addSuccessMessage("Curso " + curso.getNome() + " salvo com sucesso!");
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    @PostConstruct
    public void init() {
        limpar();
    }
    
    public void limpar() {
        curso = new Curso();
    }
}
