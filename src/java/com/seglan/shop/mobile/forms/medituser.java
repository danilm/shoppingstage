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
public class medituser extends masterActionForm {

    private String us;
    public medituser() {
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
     * @return the us
     */
    public String getUs() {
        return us;
    }

    /**
     * @param us the us to set
     */
    public void setUs(String us) {
        this.us = us;
    }
}
