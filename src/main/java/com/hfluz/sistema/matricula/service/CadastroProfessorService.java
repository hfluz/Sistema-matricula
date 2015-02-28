/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.service;

import com.hfluz.sistema.matricula.dao.ProfessorDAO;
import com.hfluz.sistema.matricula.jpa.Transactional;
import com.hfluz.sistema.matricula.modelo.Professor;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author hfluz
 */
public class CadastroProfessorService implements Serializable {

    @Inject
    private ProfessorDAO professorDAO;

    @Transactional
    public void salvar(Professor professor) throws NegocioException {

        if (professor.getNome() == null || professor.getNome().trim().equals("")) {
            throw new NegocioException("O nome do professor é obrigatório");
        }

        this.professorDAO.salvar(professor);
    }
}
