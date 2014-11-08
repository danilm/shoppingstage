/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.forms;

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
public class changepass extends masterActionForm {

    private String currpass, newpass, newpass2;

    public changepass() {
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
        java.sql.Connection conn = common.getConnection();
        try {
            user cus = (user) session.getAttribute("SSUSER");
            //product prod = new product(0, 0, prodName, reference, composition, description, price);
            DataMethods DBM = new DataMethods(conn);
            if ((currpass.length() == 0) || (newpass.length() == 0 || newpass2.length() == 0)) {
                errors.add("changepassError", new ActionMessage("error.fillall"));
            } else if (!currpass.equals(cus.getPwd())) {
                errors.add("changepassError", new ActionMessage("error.currpasserror"));
            } else if (!newpass.equals(newpass2)) {
                errors.add("changepassError", new ActionMessage("error.passconfirm"));
            } else {

                if (!DBM.changePassword(cus.getId(),currpass,newpass)) {
                    errors.add("changepassError", new ActionMessage("error.operation"));
                } else {
                    errors.add("changepassError", new ActionMessage("error.saveok"));
                    cus.setPwd(newpass);
                    session.setAttribute("SSUSER", cus);
                }
            }
        } catch (Exception e) {
            errors.add("changepassError", new ActionMessage("error.operation"));
        }
        common.CloseConnection(conn);
        return errors;
    }

    /**
     * @return the newpass2
     */
    public String getNewpass2() {
        return newpass2;
    }

    /**
     * @param newpass2 the newpass2 to set
     */
    public void setNewpass2(String newpass2) {
        this.newpass2 = newpass2;
    }

    /**
     * @return the currpass
     */
    public String getCurrpass() {
        return currpass;
    }

    /**
     * @param currpass the currpass to set
     */
    public void setCurrpass(String currpass) {
        this.currpass = currpass;
    }

    /**
     * @return the newpass
     */
    public String getNewpass() {
        return newpass;
    }

    /**
     * @param newpass the newpass to set
     */
    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }
}
