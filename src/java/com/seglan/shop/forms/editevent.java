/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.forms;

import com.seglan.shop.entities.evento;
import com.seglan.shop.entities.product;
import com.seglan.shop.forms.masterActionForm;
import com.seglan.shop.sourcecode.DataMethods;
import com.seglan.shop.sourcecode.common;
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author igaraizabal
 */
public class editevent extends masterActionForm {

    private evento evt;
    private String btnMod;

    public editevent() {
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
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        java.sql.Connection conn = common.getConnection();
        DataMethods DBM = new DataMethods(conn);
        try {
            if (getButton().equalsIgnoreCase("Guardar")) {
                //product prod = new product(0, 0, prodName, reference, composition, description, price);

                if (!DBM.saveEvent(evt)) {
                    errors.add("editeventError", new ActionMessage("error.operation"));
                } else {
                    errors.add("editeventError", new ActionMessage("error.saveok"));
                }
            }
            else
            {
                if (!DBM.addEventorder(evt.getIdEvt(),btnMod)) {
                    errors.add("editeventError", new ActionMessage("error.operation"));
                } 
            }
        } catch (Exception e) {
            errors.add("editeventError", new ActionMessage("error.operation"));
        }
        common.CloseConnection(conn);
        return errors;
    }

    /**
     * @return the idEvt
     */
    public String getIdEvt() {
        return getEvt().getIdEvt();
    }

    /**
     * @param idEvt the idEvt to set
     */
    public void setIdEvt(String idEvt) {
        getEvt().setIdEvt(idEvt);
    }

    /**
     * @return the eventname
     */
    public String getEventname() {
        return getEvt().getEventname();
    }

    /**
     * @param eventname the eventname to set
     */
    public void setEventname(String eventname) {
        getEvt().setEventname(eventname);
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return getEvt().getAddress();
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        getEvt().setAddress(address);
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return getEvt().getPhone();
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        getEvt().setPhone(phone);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return getEvt().getEmail();
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        getEvt().setEmail(email);
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return getEvt().getFecha();
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        getEvt().setFecha(fecha);
    }

    /**
     * @return the evt
     */
    public evento getEvt() {
        if (evt == null) {
            evt = (evento) session.getAttribute("CURRENTEVT");
        }
        return evt;
    }

    /**
     * @param evt the evt to set
     */
    public void setEvt(evento evt) {
        this.evt = evt;
    }

    /**
     * @return the btnMod
     */
    public String getBtnMod() {
        return btnMod;
    }

    /**
     * @param btnMod the btnMod to set
     */
    public void setBtnMod(String btnMod) {
        this.btnMod = btnMod;
    }
}
