/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.service;

import com.hfluz.sistema.matricula.dao.MatriculaDAO;
import com.hfluz.sistema.matricula.jpa.Transactional;
import com.hfluz.sistema.matricula.modelo.Matricula;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author hfluz
 */
public class CadastroMatriculaService implements Serializable {

    @Inject
    private MatriculaDAO matriculaDAO;

    @Transactional
    public Matricula salvar(Matricula matricula) throws NegocioException {

        if (matricula.getAluno() == null) {
            throw new NegocioException("O campo aluno é obrigatório");
        }
        if (matricula.getTurma() == null) {
            throw new NegocioException("O campo turma é obrigatório");
        }
        
        this.matriculaDAO.salvar(matricula);
    }
}
