/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.forms;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author igaraizabal
 */
public class control extends masterActionForm {

    private String evento, pase;

    public control() {
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
        try {
            if (getButton().equals("Guardar")) {
                String fpath = "events/" + evento;

                fpath = this.getServlet().getServletContext().getRealPath(fpath);

                File f = new File(fpath);
                if (f.exists()) {
                    deleteFolder(f);
                }
                errors.add("controlError", new ActionMessage("error.saveok"));
            }
        } catch (Exception e) {
            errors.add("controlError", new ActionMessage("error.operation"));
        }
        return errors;
    }

    private void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }
    /**
     * @return the evento
     */
    /**
     * @return the pase
     */
    /*public String getPase() {
     if (pase == null) {
     pase = common.getCurrentPase(this.getServlet().getServletContext());
     }
     return pase;
     }

     /**
     * @param pase the pase to set
     */
    /*public void setPase(String pase) {
     this.pase = pase;
     }*/
}
