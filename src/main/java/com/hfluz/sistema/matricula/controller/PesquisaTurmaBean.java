/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.controller;

import com.hfluz.sistema.matricula.dao.TurmaDAO;
import com.hfluz.sistema.matricula.jsf.FacesUtil;
import com.hfluz.sistema.matricula.modelo.Turma;
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
public class PesquisaTurmaBean implements Serializable {
    private List<Turma> turmas;
    private Turma turmaSelecionado;
    
    @Inject
    private TurmaDAO turmaDAO;
    
    @PostConstruct
    public void init(){
        turmas = turmaDAO.buscarTodos();
    }
    
    public void excluir(){
        try {
            turmaDAO.excluir(turmaSelecionado);
            FacesUtil.addSuccessMessage("Turma " + turmaSelecionado.getDescricaoSala() + " excluída com sucesso!");
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }
}
