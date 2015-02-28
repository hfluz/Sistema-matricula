/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.converter;

import com.hfluz.sistema.matricula.cdi.CDIServiceLocator;
import com.hfluz.sistema.matricula.dao.AlunoDAO;
import com.hfluz.sistema.matricula.modelo.Aluno;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author hfluz
 */
@FacesConverter(forClass = Aluno.class)
public class AlunoConverter implements Converter {

    private AlunoDAO alunoDAO;

    public AlunoConverter() {
        alunoDAO = CDIServiceLocator.getBean(AlunoDAO.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Aluno retorno = null;
        if (value != null) {
            retorno = alunoDAO.buscarPeloCodigo(Long.valueOf(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String retorno = null;
        if (value != null) {
            Long codigo = ((Aluno) value).getCodigo();
            retorno = codigo == null ? null : codigo.toString();
        }
        return retorno;
    }

}
