/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.mobile.actions;

import com.google.gson.Gson;
import com.seglan.shop.entities.JsonResponse;
import com.seglan.shop.entities.user;
import com.seglan.shop.sourcecode.DataMethods;
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
public class medituser extends org.apache.struts.action.Action {

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
            com.seglan.shop.mobile.forms.medituser frm = (com.seglan.shop.mobile.forms.medituser) form;
            Gson gson = new Gson();

            user usr = gson.fromJson(frm.getUs(), user.class);

            DataMethods DBM = new DataMethods(conn);
            if (!DBM.saveUser(usr)) {
                resp.status="KO";
                resp.data=usr;
            } else {
                resp.status="OK";
                resp.data=usr;
            }

        } catch (Exception e) {
                resp.status="KO";
                resp.data="";
        }
        PrintWriter outfile = response.getWriter();
        Gson gson=new Gson();
        String json=gson.toJson(resp);
        outfile.println(json);
        common.CloseConnection(conn);
        return null;
    }
}
