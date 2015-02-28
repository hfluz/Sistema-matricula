/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.controller;

import com.hfluz.sistema.matricula.jsf.FacesUtil;
import com.hfluz.sistema.matricula.modelo.Aluno;
import com.hfluz.sistema.matricula.service.CadastroAlunoService;
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
public class CadastroAlunoBean implements Serializable {
    
    private Aluno aluno;
    
    @Inject
    private CadastroAlunoService cadastroAlunoService;
    
    public void salvar() {
        try {
            cadastroAlunoService.salvar(aluno);
            FacesUtil.addSuccessMessage("Aluno " + aluno.getNome() + " salvo com sucesso!");
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    @PostConstruct
    public void init() {
        limpar();
    }
    
    public void limpar() {
        aluno = new Aluno();
    }
    
}
