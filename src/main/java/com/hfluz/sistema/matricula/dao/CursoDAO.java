/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.dao;

import com.hfluz.sistema.matricula.jpa.Transactional;
import com.hfluz.sistema.matricula.modelo.Curso;
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
public class CursoDAO implements Serializable {
    @Inject
    private EntityManager em;
    
    public Curso buscarPeloCodigo(Long codigo) {
		return em.find(Curso.class, codigo);
	}
	
	public void salvar(Curso curso) {
		em.merge(curso);
	}

	public List<Curso> buscarTodos() {
		return em.createQuery("from Curso c").getResultList();
	}
	
	@Transactional
	public void excluir(Curso curso) throws NegocioException {
		curso = buscarPeloCodigo(curso.getCodigo());
		try {
			em.remove(curso);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Curso não pode ser excluído.");
		}
	}
}
