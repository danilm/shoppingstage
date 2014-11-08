/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.seglan.shop.mobile.forms;

import com.seglan.shop.forms.masterActionForm;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author daniellopezmesa
 */
public class addfav extends masterActionForm {

    private String iduser, idprod, idevt, fav;

    public addfav() {
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

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }
    
}
