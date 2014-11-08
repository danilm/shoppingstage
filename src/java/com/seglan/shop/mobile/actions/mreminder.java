/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.mobile.actions;

import com.google.gson.Gson;
import com.seglan.shop.entities.JsonResponse;
import com.seglan.shop.sourcecode.DataMethods;
import com.seglan.shop.sourcecode.common;
import com.seglan.shop.sourcecode.email;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author igaraizabal
 */
public class mreminder extends org.apache.struts.action.Action {

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PrintWriter outfile = response.getWriter();
        com.seglan.shop.mobile.forms.mreminder frm = (com.seglan.shop.mobile.forms.mreminder) form;
        JsonResponse resp = new JsonResponse();
        resp.status = "KO";
        Connection conn = common.getConnection();
        DataMethods DBM = new DataMethods(conn);
        String emailadd = frm.getEmail();
        String newpass = DBM.restorePassword(emailadd);
        if (newpass.equals("ERROR")) {
            resp.data = "ERROR";
        } else {
            resp.status = "OK";
            resp.data = "RESTORED";
            String body = "<p>Su contraseña The Shopping Stage ha sido reestablecida,</p>";
            body += "<p>Su nueva contraseña para acceder a la aplicación es <b>" + newpass + "</b></p>";
            body += "<p>Podrá volver a cambiarla desde sus ajustes de cuenta.</p>";
            body += "<p>Le saluda atentamente,</p><p>El equipo \"The Shopping Stage\"</p>";
            if (!email.sendActivation(emailadd, "Contraseña The Shopping Stage", body)){
                
                resp.status = "KO";
                resp.data = "ERROR";
            }
        }
        common.CloseConnection(conn);
        Gson gson = new Gson();
        String json = gson.toJson(resp);
        outfile.println(json);
        return null;
    }
}
