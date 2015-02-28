/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.dao;

import com.hfluz.sistema.matricula.jpa.Transactional;
import com.hfluz.sistema.matricula.modelo.Professor;
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
public class ProfessorDAO implements Serializable {
    @Inject
    private EntityManager em;
    
    public Professor buscarPeloCodigo(Long codigo) {
		return em.find(Professor.class, codigo);
	}
	
	public void salvar(Professor professor) {
		em.merge(professor);
	}

	public List<Professor> buscarTodos() {
		return em.createQuery("from Professor p").getResultList();
	}
	
	@Transactional
	public void excluir(Professor professor) throws NegocioException {
		professor = buscarPeloCodigo(professor.getCodigo());
		try {
			em.remove(professor);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Professor não pode ser excluído.");
		}
	}
}
