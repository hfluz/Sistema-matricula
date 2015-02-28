/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.dao;

import com.hfluz.sistema.matricula.jpa.Transactional;
import com.hfluz.sistema.matricula.modelo.Curso;
import com.hfluz.sistema.matricula.modelo.Turma;
import com.hfluz.sistema.matricula.service.NegocioException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 *
 * @author hfluz
 */
public class TurmaDAO implements Serializable {

    @Inject
    private EntityManager em;

    public Turma buscarPeloCodigo(Long codigo) {
        return em.find(Turma.class, codigo);
    }
    
    public List<Turma> buscarPeloCurso(Curso curso) {
        return em.createQuery("from Turma t WHERE t.curso.codigo = :codigoCurso").setParameter("codigoCurso", curso.getCodigo()).getResultList();
    }

    public void salvar(Turma turma) {
        em.merge(turma);
    }

    public List<Turma> buscarTodos() {
        return em.createQuery("from Turma t").getResultList();
    }

    @Transactional
    public void excluir(Turma turma) throws NegocioException {
        turma = buscarPeloCodigo(turma.getCodigo());
        try {
            em.remove(turma);
            em.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Turma não pode ser excluído.");
        }
    }
}
