/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.mobile.actions;

import com.google.gson.Gson;
import com.seglan.shop.entities.JsonResponse;
import com.seglan.shop.entities.pedido;
import com.seglan.shop.sourcecode.DataMethods;
import com.seglan.shop.sourcecode.common;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author igaraizabal
 */
public class getchart extends org.apache.struts.action.Action {

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
        DataMethods DBM = new DataMethods(conn);
        PrintWriter outfile = response.getWriter();
        com.seglan.shop.mobile.forms.getchart frm = (com.seglan.shop.mobile.forms.getchart) form;
        String usId = frm.getIduser();
        String evId = frm.getIdevento();
        JsonResponse resp = new JsonResponse();
        ArrayList<pedido> chart = DBM.getChart(usId,evId);
        if (chart == null) {
            resp.status = "KO";
            resp.data = "DBError";
        } else if (chart.isEmpty()) {
            resp.status = "KO";
            resp.data = "Empty";
        } else {
            resp.status = "OK";
            resp.data = chart;
        }
        common.CloseConnection(conn);
        Gson gson = new Gson();
        String jsonString = gson.toJson(resp);
        jsonString = common.formatTextUnicode(jsonString);
        outfile.println(jsonString);
        return null;
    }
}
