/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.actions;

import com.seglan.shop.sourcecode.DataMethods;
import com.seglan.shop.sourcecode.common;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Iñaki Garaizabal
 */
public class activation extends org.apache.struts.action.Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        String id = (String) PropertyUtils.getSimpleProperty(form, "id");
        String uid="0";
        if(id.length()>0){uid=id.split("-")[0];}
        java.sql.Connection conn = common.getConnection();
        DataMethods DBM = new DataMethods(conn);
        DBM.activateUser(uid);
        common.CloseConnection(conn);
        try
        {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/mobile/index.html")); 
            //response.sendRedirect("mobile/index.html");
            return null;
        }
        catch(Exception ex)
        {
            return new ActionForward("mobile/index.html", false);
        }
    }
}
