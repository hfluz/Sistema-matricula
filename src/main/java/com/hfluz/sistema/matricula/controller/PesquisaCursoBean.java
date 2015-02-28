/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.controller;

import com.hfluz.sistema.matricula.dao.CursoDAO;
import com.hfluz.sistema.matricula.jsf.FacesUtil;
import com.hfluz.sistema.matricula.modelo.Curso;
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
public class PesquisaCursoBean implements Serializable {
    private List<Curso> cursos;
    private Curso cursoSelecionado;
    
    @Inject
    private CursoDAO cursoDAO;
    
    @PostConstruct
    public void init(){
        cursos = cursoDAO.buscarTodos();
    }
    
    public void excluir(){
        try {
            cursoDAO.excluir(cursoSelecionado);
            FacesUtil.addSuccessMessage("Curso " + cursoSelecionado.getNome() + " excluído com sucesso!");
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }
}
