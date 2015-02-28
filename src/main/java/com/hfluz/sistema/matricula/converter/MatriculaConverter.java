/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfluz.sistema.matricula.converter;

import com.hfluz.sistema.matricula.cdi.CDIServiceLocator;
import com.hfluz.sistema.matricula.dao.MatriculaDAO;
import com.hfluz.sistema.matricula.modelo.Matricula;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author hfluz
 */
@FacesConverter(forClass = Matricula.class)
public class MatriculaConverter implements Converter {

    private MatriculaDAO matriculaDAO;

    public MatriculaConverter() {
        matriculaDAO = CDIServiceLocator.getBean(MatriculaDAO.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Matricula retorno = null;
        if (value != null) {
            retorno = matriculaDAO.buscarPeloCodigo(Long.valueOf(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String retorno = null;
        if (value != null) {
            Long codigo = ((Matricula) value).getCodigo();
            retorno = codigo == null ? null : codigo.toString();
        }
        return retorno;
    }

}
