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
public class edituser extends masterActionForm {

    private user us;
    private String editType;
    private String pwd2 = "";

    public edituser() {
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
            //product prod = new product(0, 0, prodName, reference, composition, description, price);
            DataMethods DBM = new DataMethods(conn);
            boolean isNew=false;
            if (us.getId().equals("0")) {
                isNew=true;
                if(pwd2.length()<6){errors.add("edituserError", new ActionMessage("error.passlength"));}
                else if(!getPwd().equals(pwd2)){errors.add("edituserError", new ActionMessage("error.passconfirm"));}
            }
            if (errors.size() == 0) {
                if(getEmail().length()<2){errors.add("edituserError", new ActionMessage("error.email"));}
                else if (!DBM.saveUser(us)) {
                    errors.add("edituserError", new ActionMessage("error.operation"));
                } else if(!isNew){
                    errors.add("edituserError", new ActionMessage("error.saveok"));
                }
            }
        } catch (Exception e) {
            errors.add("edituserError", new ActionMessage("error.operation"));
        }
        common.CloseConnection(conn);
        return errors;
    }

    /**
     * @return the usertype
     */
    public String getUsertype() {
        return getUs().getUsertype();
    }

    /**
     * @param usertype the usertype to set
     */
    public void setUsertype(String usertype) {
        getUs().setUsertype(usertype);
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return getUs().getUsername();
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        getUs().setUsername(username);
    }

    /**
     * @return the userfname
     */
    public String getUserfname() {
        return getUs().getUserfname();
    }

    /**
     * @param userfname the userfname to set
     */
    public void setUserfname(String userfname) {
        getUs().setUserfname(userfname);
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
        return getUs().getPwd();
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        getUs().setPwd(pwd);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return getUs().getEmail();
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        getUs().setEmail(email);
    }

    /**
     * @return the cp
     */
    public String getCp() {
        return getUs().getCp();
    }

    /**
     * @param cp the cp to set
     */
    public void setCp(String cp) {
        getUs().setCp(cp);
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return getUs().getAddress();
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        getUs().setAddress(address);
    }

    /**
     * @return the poblacion
     */
    public String getPoblacion() {
        return getUs().getPoblacion();
    }

    /**
     * @param poblacion the poblacion to set
     */
    public void setPoblacion(String poblacion) {
        getUs().setPoblacion(poblacion);
    }

    /**
     * @return the provincia
     */
    public String getProvincia() {
        return getUs().getProvincia();
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(String provincia) {
        getUs().setProvincia(provincia);
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return getUs().getPais();
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        getUs().setPais(pais);
    }

    /**
     * @return the tlf
     */
    public String getTlf() {
        return getUs().getTlf();
    }

    /**
     * @param tlf the tlf to set
     */
    public void setTlf(String tlf) {
        getUs().setTlf(tlf);
    }

    /**
     * @return the us
     */
    public user getUs() {
        if (us == null) {
            us = (user) session.getAttribute("CURRENTUSER");
        }
        return us;
    }

    /**
     * @param us the us to set
     */
    public void setUs(user us) {
        this.us = us;
    }

    /**
     * @return the editType
     */
    public String getEditType() {
        return editType;
    }

    /**
     * @param editType the editType to set
     */
    public void setEditType(String editType) {
        this.editType = editType;
    }

    /**
     * @return the pwd2
     */
    public String getPwd2() {
        return pwd2;
    }

    /**
     * @param pwd2 the pwd2 to set
     */
    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }
}
