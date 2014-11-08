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
public class mreminder extends masterActionForm {

    private String email;

    public mreminder() {
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
        /*if (user.length() == 0) {
            errors.add("loginError", new ActionMessage("error.mobile.login"));
        } else if (user.length() == 0) {
            errors.add("loginError", new ActionMessage("error.mobile.login"));
        } else {
            user us = new user(user, pwd);
            if (us.getId().equals("-1")) {
                errors.add("loginError", new ActionMessage("error.mobile.login"));
                pwd = "";
            } else if (!us.isActivated()) {
                String body = "<p>Bienvenido a The Shopping Stage,</p>";
                body += "<p>Para completar la activación de su cuenta, por favor, haga click en el siguiente enlace:</br><a href=\"http://shoppingstage.net/activation.do?id=" + us.getActivation() + "\">Completar Activación</a></p>";
                body += "<p>Le saluda atentamente,</p><p>El equipo \"The Shopping Stage\"</p>";
                email.sendActivation(us.getEmail(), "Bienvenido a The Shopping Stage", body);
                errors.add("loginError", new ActionMessage("error.mobile.loginactivation"));
                pwd = "";
            } else {
                request.getSession().setAttribute("SSUSER", us);
            }
        }*/
        return errors;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
