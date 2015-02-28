/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.converter;

import com.hfluz.sistema.matricula.cdi.CDIServiceLocator;
import com.hfluz.sistema.matricula.dao.ProfessorDAO;
import com.hfluz.sistema.matricula.modelo.Professor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author hfluz
 */
@FacesConverter(forClass = Professor.class)
public class ProfessorConverter implements Converter {

    @Inject
    private ProfessorDAO professorDAO;

    public ProfessorConverter() {
        professorDAO = CDIServiceLocator.getBean(ProfessorDAO.class);
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Professor retorno = null;
        if (value != null) {
            retorno = professorDAO.buscarPeloCodigo(Long.valueOf(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        String retorno = null;
        if (value != null) {
            Long codigo = ((Professor) value).getCodigo();
            retorno = codigo == null ? null : codigo.toString();
        }
        return retorno;
    }

}
