/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.converter;

import com.hfluz.sistema.matricula.cdi.CDIServiceLocator;
import com.hfluz.sistema.matricula.dao.TurmaDAO;
import com.hfluz.sistema.matricula.modelo.Turma;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author hfluz
 */
@FacesConverter(forClass = Turma.class)
public class TurmaConverter implements Converter {
    @Inject
    private TurmaDAO turmaDAO;

    public TurmaConverter() {
        turmaDAO = CDIServiceLocator.getBean(TurmaDAO.class);
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Turma retorno = null;
        if (value != null) {
            retorno = turmaDAO.buscarPeloCodigo(Long.valueOf(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        String retorno = null;
        if (value != null) {
            Long codigo = ((Turma) value).getCodigo();
            retorno = codigo == null ? null : codigo.toString();
        }
        return retorno;
    }
}
