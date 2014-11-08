/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.mobile.actions;

import com.google.gson.Gson;
import com.seglan.shop.entities.JsonResponse;
import com.seglan.shop.entities.user;
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
public class mlogin extends org.apache.struts.action.Action {

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
        com.seglan.shop.mobile.forms.mlogin frm = (com.seglan.shop.mobile.forms.mlogin) form;
        JsonResponse resp = new JsonResponse();
        user us = new user(frm.getUser(), frm.getPwd());
        resp.status = "KO";
        if (us.getId().equals("-1")) {
            resp.data = "wrong";
        } else if (!us.isActivated()) {
            String body = "<p>Bienvenido a The Shopping Stage,</p>";
            body += "<p>Para completar la activación de su cuenta, por favor, haga click en el siguiente enlace:</br><a href=\"http://shoppingstage.net/activation.do?id=" + us.getActivation() + "\">Completar Activación</a></p>";
            body += "<p>Le saluda atentamente,</p><p>El equipo \"The Shopping Stage\"</p>";
            email.sendActivation(us.getEmail(), "Bienvenido a The Shopping Stage", body);
            resp.data = "activation";
        } else {
            resp.status = "OK";
            resp.data = us;
        }
        Gson gson = new Gson();
        
        String jsonString = gson.toJson(resp);
        jsonString=common.formatTextUnicode(jsonString);
        outfile.println(jsonString);
        return null;
    }
}
