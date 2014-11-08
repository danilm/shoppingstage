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
public class mcurrentpase extends org.apache.struts.action.Action {

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
        com.seglan.shop.mobile.forms.mcurrentpase frm = (com.seglan.shop.mobile.forms.mcurrentpase) form;
        JsonResponse resp = new JsonResponse();
        resp.status = "OK";
        resp.data = common.getCurrentPase(frm.getEvt());
        Gson gson = new Gson();
        String json = gson.toJson(resp);
        outfile.println(json);
        return null;
    }
}
