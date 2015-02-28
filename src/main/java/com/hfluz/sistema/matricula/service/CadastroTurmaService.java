/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.service;

import com.hfluz.sistema.matricula.dao.TurmaDAO;
import com.hfluz.sistema.matricula.jpa.Transactional;
import com.hfluz.sistema.matricula.modelo.Turma;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author hfluz
 */
public class CadastroTurmaService implements Serializable {

    @Inject
    private TurmaDAO turmaDAO;

    @Transactional
    public void salvar(Turma turma) throws NegocioException {

        if (turma.getDescricaoSala() == null || turma.getDescricaoSala().trim().equals("")) {
            throw new NegocioException("A descrição da sala é obrigatória");
        }
        if (turma.getCurso() == null) {
            throw new NegocioException("O campo curso é obrigatório");
        }
        this.turmaDAO.salvar(turma);
    }
}
