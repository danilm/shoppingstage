/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.seglan.shop.mobile.actions;

import com.google.gson.Gson;
import com.seglan.shop.entities.JsonResponse;
import com.seglan.shop.sourcecode.DataMethods;
import com.seglan.shop.sourcecode.common;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author daniellopezmesa
 */
public class addlike extends org.apache.struts.action.Action {

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
        com.seglan.shop.mobile.forms.addlike frm = (com.seglan.shop.mobile.forms.addlike) form;
        String iduser = frm.getIduser();
        String idprod = frm.getIdprod();
        String idevt = frm.getIdevt();
        String like = frm.getLike();
       

        Connection conn = common.getConnection();
        DataMethods DBM = new DataMethods(conn);
        int result = DBM.addLike(iduser,idevt, idprod, like);
        JsonResponse resp=new JsonResponse();
        if(result>=0)
        {
            resp.status="OK";
            resp.data=""+result;
        }
        else
        {
            resp.status="KO";
            resp.data="DBError";
        }
        Gson gson = new Gson();
        String json = gson.toJson(resp);
        outfile.println(json);
        common.CloseConnection(conn);

        return null;
    }
    
}
