/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.seglan.shop.forms;

import com.seglan.shop.entities.user;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author igaraizabal
 */
public class login extends masterActionForm {
    
    private String user,pwd;
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (user.length()==0) {errors.add("loginError", new ActionMessage("error.login"));}
        else if (user.length()==0) {errors.add("loginError", new ActionMessage("error.login"));}
        else
        {
            user us=new user(user, pwd);
            if(us.getId().equals("-1")){errors.add("loginError", new ActionMessage("error.login"));pwd="";}
            else{request.getSession().setAttribute("SSUSER", us);}
        }
        return errors;
    }

    /**
     * @return the user
     */
    public String getLuser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setLuser(String user) {
        this.user = user;
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
}
