/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.service;

import com.hfluz.sistema.matricula.dao.AlunoDAO;
import com.hfluz.sistema.matricula.jpa.Transactional;
import com.hfluz.sistema.matricula.modelo.Aluno;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author hfluz
 */
public class CadastroAlunoService implements Serializable {

    @Inject
    private AlunoDAO alunoDAO;

    @Transactional
    public void salvar(Aluno aluno) throws NegocioException {

        if (aluno.getNome() == null || aluno.getNome().trim().equals("")) {
            throw new NegocioException("O nome do aluno é obrigatório");
        }

        this.alunoDAO.salvar(aluno);
    }
}
