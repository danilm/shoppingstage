/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.mobile.actions;

import com.google.gson.Gson;
import com.seglan.shop.entities.JsonResponse;
import com.seglan.shop.sourcecode.common;
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
public class updatecurrent extends org.apache.struts.action.Action {

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
        com.seglan.shop.mobile.forms.updatecurrent frm = (com.seglan.shop.mobile.forms.updatecurrent) form;
        String evento = frm.getEvento();
        String pase = frm.getPase();

        JsonResponse resp=new JsonResponse();
        if(common.saveCurrentState(evento, pase))
        {
            resp.status="OK";
            resp.data=common.getCurrentPase(evento) ;
        }
        else
        {
            resp.status="KO";
            resp.data="ErrorUpdating";
        }
        Gson gson = new Gson();
        String json = gson.toJson(resp);
        outfile.println(json);


        return null;
    }
}
