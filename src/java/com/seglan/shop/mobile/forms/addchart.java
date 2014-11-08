/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.mobile.forms;

import com.seglan.shop.forms.*;
import com.seglan.shop.entities.user;
import com.seglan.shop.sourcecode.DataMethods;
import com.seglan.shop.sourcecode.common;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author igaraizabal
 */
public class addchart extends masterActionForm {

    private String iduser, idprod,idevt, color, size;

    public addchart() {
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
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
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
     * @return the idprod
     */
    public String getIdprod() {
        return idprod;
    }

    /**
     * @param idprod the idprod to set
     */
    public void setIdprod(String idprod) {
        this.idprod = idprod;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the idevt
     */
    public String getIdevt() {
        return idevt;
    }

    /**
     * @param idevt the idevt to set
     */
    public void setIdevt(String idevt) {
        this.idevt = idevt;
    }
}
