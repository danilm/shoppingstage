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
public class mevent extends org.apache.struts.action.Action {

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
        com.seglan.shop.mobile.forms.mevent frm = (com.seglan.shop.mobile.forms.mevent) form;
        JsonResponse resp = new JsonResponse();
        String idEvt = frm.getIdevent();
        String models = common.getEventModelsJSON(idEvt);
        String marcas = common.getMarcasJSON(idEvt);
        String likeStatus = common.getLikeStatusJSON(idEvt);
        String favStatus = common.getFavStatusJSON(idEvt);

        if ((marcas.length() > 0) && (models.length() > 0)) {
            resp.status = marcas;
            resp.data = models;
            resp.like = likeStatus;
            resp.fav = favStatus;

        } else {
            resp.status = "KO";
            resp.data = "";
            resp.like = "";
            resp.fav = "";

        }
        
       

        Gson gson = new Gson();
        String jsonString = gson.toJson(resp);
        jsonString = common.formatTextUnicode(jsonString);
        outfile.println(jsonString);
        return null;
    }
}
