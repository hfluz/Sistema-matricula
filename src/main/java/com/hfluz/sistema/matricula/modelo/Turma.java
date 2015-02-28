/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.modelo;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author hfluz
 */
@Entity
@Getter
@Setter
public class Turma {

    private Long codigo;
    private String descricaoSala;
    private Curso curso;
    private List<Matricula> matriculas;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getCodigo() {
        return codigo;
    }

    @ManyToOne
    public Curso getCurso() {
        return curso;
    }

    @OneToMany(mappedBy = "turma")
    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    @Column(name = "descricao_sala")
    public String getDescricaoSala() {
        return descricaoSala;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Turma other = (Turma) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

}
