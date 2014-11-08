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
public class mlogin extends masterActionForm {

    private String user, pwd;

    public mlogin() {
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
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }
}
