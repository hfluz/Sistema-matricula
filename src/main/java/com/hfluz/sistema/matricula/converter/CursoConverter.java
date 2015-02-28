/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.converter;

import com.hfluz.sistema.matricula.cdi.CDIServiceLocator;
import com.hfluz.sistema.matricula.dao.CursoDAO;
import com.hfluz.sistema.matricula.modelo.Curso;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author hfluz
 */
@FacesConverter(forClass = Curso.class)
public class CursoConverter implements Converter {

    private CursoDAO cursoDAO;

    public CursoConverter() {
        cursoDAO = CDIServiceLocator.getBean(CursoDAO.class);
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Curso retorno = null;
        if (value != null) {
            retorno = cursoDAO.buscarPeloCodigo(Long.valueOf(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        String retorno = null;
        if (value != null) {
            Long codigo = ((Curso) value).getCodigo();
            retorno = codigo == null ? null : codigo.toString();
        }
        return retorno;
    }

}
