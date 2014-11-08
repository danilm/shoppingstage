/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.mobile.actions;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.seglan.shop.entities.JsonResponse;
import com.seglan.shop.entities.user;
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
 * @author igaraizabal
 */
public class mchangepass extends org.apache.struts.action.Action {

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
        com.seglan.shop.mobile.forms.mchangepass frm = (com.seglan.shop.mobile.forms.mchangepass) form;
        String iduser = frm.getUserid();
        String cpass = frm.getCurrpass();
        String npass = frm.getNewpass();

        Connection conn = common.getConnection();
        DataMethods DBM = new DataMethods(conn);
        JsonResponse resp=new JsonResponse();
        if(DBM.changePassword(iduser, cpass, npass))
        {
            resp.status="OK";
            resp.data="Password Saved";
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
