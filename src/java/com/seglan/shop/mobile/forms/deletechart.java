/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.mobile.forms;

import com.seglan.shop.forms.*;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author igaraizabal
 */
public class deletechart extends masterActionForm {

    private String iduser, idrow,idevento;

    public deletechart() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        return errors;
    }
    /**
     * @return the iduser
     */
    public String getIduser() {
        return iduser;
    }

    /**
     * @param iduser the iduser to set
     */
    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    /**
     * @return the idrow
     */
    public String getIdrow() {
        return idrow;
    }

    /**
     * @param idrow the idrow to set
     */
    public void setIdrow(String idrow) {
        this.idrow = idrow;
    }

    /**
     * @return the idevento
     */
    public String getIdevento() {
        return idevento;
    }

    /**
     * @param idevento the idevento to set
     */
    public void setIdevento(String idevento) {
        this.idevento = idevento;
    }
}
