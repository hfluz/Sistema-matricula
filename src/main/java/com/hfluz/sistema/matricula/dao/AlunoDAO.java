/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.dao;

import com.hfluz.sistema.matricula.jpa.Transactional;
import com.hfluz.sistema.matricula.modelo.Aluno;
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
public class AlunoDAO implements Serializable {
    @Inject
    private EntityManager em;
    
    public Aluno buscarPeloCodigo(Long codigo) {
		return em.find(Aluno.class, codigo);
	}
	
	public void salvar(Aluno aluno) {
		em.merge(aluno);
	}

	public List<Aluno> buscarTodos() {
		return em.createQuery("from Aluno a").getResultList();
	}
	
	@Transactional
	public void excluir(Aluno aluno) throws NegocioException {
		aluno = buscarPeloCodigo(aluno.getCodigo());
		try {
			em.remove(aluno);
			em.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Aluno não pode ser excluído.");
		}
	}
}
