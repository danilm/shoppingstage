/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.mobile.actions;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.seglan.shop.actions.*;
import com.seglan.shop.entities.JsonResponse;
import com.seglan.shop.entities.user;
import com.seglan.shop.sourcecode.DataMethods;
import com.seglan.shop.sourcecode.common;
import com.seglan.shop.sourcecode.email;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author igaraizabal
 */
public class mnewuser extends org.apache.struts.action.Action {

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

        java.sql.Connection conn = common.getConnection();
        JsonResponse resp=new JsonResponse();
        try {
            DataMethods DBM = new DataMethods(conn);
            com.seglan.shop.mobile.forms.mnewuser frm=(com.seglan.shop.mobile.forms.mnewuser)form;
            user us=new user(0,frm.getUsername(),frm.getUserfname(),frm.getPwd(),frm.getEmail(),frm.getTlf(),frm.getAddress(),frm.getCp(),frm.getPoblacion(),frm.getProvincia(),frm.getPais(),frm.getPubli(),"U",frm.getSexo(),frm.getEdad());
            if(DBM.getUserEmailExist(frm.getEmail()))
            {
                resp.status="KO";
                resp.data="exist";
            }
            else if (!DBM.saveUser(us)) {
                resp.status="KO";
                resp.data="database";
            } else {
                String body = "<p>Bienvenido a The Shopping Stage,</p>";
                body += "<p>Para completar la activación de su cuenta, por favor, haga click en el siguiente enlace:</br><a href=\"http://shoppingstage.net/activation.do?id=" + us.getActivation() + "\">Completar Activación</a></p>";
                body += "<p>Le saluda atentamente,</p><p>El equipo \"The Shopping Stage\"</p>";

                if (email.sendActivation(us.getEmail(), "Bienvenido a The Shopping Stage", body)){
                   resp.status="OK";
                    resp.data=us; 
                } else {
                    resp.status="KO";
                    resp.data="error";
                }
                
                
            }

        } catch (Exception e) {
                resp.status="KO";
                resp.data="error";
        }
        common.CloseConnection(conn);


        PrintWriter outfile = response.getWriter();
        Gson gson = new Gson();
        String json = gson.toJson(resp);
        outfile.println(json);
        return null;
    }
}
