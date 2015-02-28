/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.dao;

import com.hfluz.sistema.matricula.jpa.Transactional;
import com.hfluz.sistema.matricula.modelo.Matricula;
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
public class MatriculaDAO implements Serializable {
    @Inject
    private EntityManager em;
    
    public Matricula buscarPeloCodigo(Long codigo) {
		return em.find(Matricula.class, codigo);
	}
	
	public void salvar(Matricula matricula) {
		em.merge(matricula);
	}

	public List<Matricula> buscarTodos() {
		return em.createQuery("from Matricula m").getResultList();
	}
	
	@Transactional
	public void excluir(Matricula matricula) throws NegocioException {
		matricula = buscarPeloCodigo(matricula.getCodigo());
		try {
			em.remove(matricula);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Matricula não pode ser excluída.");
		}
	}
}
