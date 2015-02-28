/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.controller;

import com.hfluz.sistema.matricula.jsf.FacesUtil;
import com.hfluz.sistema.matricula.modelo.Professor;
import com.hfluz.sistema.matricula.service.CadastroProfessorService;
import com.hfluz.sistema.matricula.service.NegocioException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CadastroProfessorBean implements Serializable {
    
    private Professor professor;
    
    @Inject
    private CadastroProfessorService cadastroProfessorService;
    
    public void salvar() {
        try {
            cadastroProfessorService.salvar(professor);
            FacesUtil.addSuccessMessage("Professor " + professor.getNome() + " salvo com sucesso!");
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    @PostConstruct
    public void init() {
        limpar();
    }
    
    public void limpar() {
        professor = new Professor();
    }
    
}
