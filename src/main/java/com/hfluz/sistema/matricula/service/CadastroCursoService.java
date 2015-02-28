/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.service;

import com.hfluz.sistema.matricula.dao.CursoDAO;
import com.hfluz.sistema.matricula.jpa.Transactional;
import com.hfluz.sistema.matricula.modelo.Curso;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author hfluz
 */
public class CadastroCursoService implements Serializable {

    @Inject
    private CursoDAO cursoDAO;

    @Transactional
    public void salvar(Curso curso) throws NegocioException {

        if (curso.getNome() == null || curso.getNome().trim().equals("")) {
            throw new NegocioException("O nome do curso é obrigatório");
        }

        this.cursoDAO.salvar(curso);
    }
}