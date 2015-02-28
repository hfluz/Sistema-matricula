/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.controller;

import com.hfluz.sistema.matricula.dao.ProfessorDAO;
import com.hfluz.sistema.matricula.jsf.FacesUtil;
import com.hfluz.sistema.matricula.modelo.Professor;
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
public class PesquisaProfessorBean implements Serializable {
    private List<Professor> professores;
    private Professor professorSelecionado;
    
    @Inject
    private ProfessorDAO professorDAO;
    
    @PostConstruct
    public void init(){
        professores = professorDAO.buscarTodos();
    }
    
    public void excluir(){
        try {
            professorDAO.excluir(professorSelecionado);
            FacesUtil.addSuccessMessage("Professor " + professorSelecionado.getNome() + " excluído com sucesso!");
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }
}
