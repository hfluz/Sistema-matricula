/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.controller;

import com.hfluz.sistema.matricula.dao.AlunoDAO;
import com.hfluz.sistema.matricula.jsf.FacesUtil;
import com.hfluz.sistema.matricula.modelo.Aluno;
import com.hfluz.sistema.matricula.service.NegocioException;
import java.io.Serializable;
import java.util.List;
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
public class PesquisaAlunoBean implements Serializable {
    private List<Aluno> alunos;
    private Aluno alunoSelecionado;
    
    @Inject
    private AlunoDAO alunoDAO;
    
    @PostConstruct
    public void init(){
        alunos = alunoDAO.buscarTodos();
    }
    
    public void excluir(){
        try {
            alunoDAO.excluir(alunoSelecionado);
            FacesUtil.addSuccessMessage("Aluno " + alunoSelecionado.getNome() + " excluído com sucesso!");
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }
}
