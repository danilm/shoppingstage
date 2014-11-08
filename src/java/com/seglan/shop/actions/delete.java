/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.seglan.shop.actions;

import com.seglan.shop.entities.*;
import com.seglan.shop.sourcecode.DataMethods;
import com.seglan.shop.sourcecode.common;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
public class delete extends org.apache.struts.action.Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
            String id=(String) PropertyUtils.getSimpleProperty(form, "id");
            String it=(String) PropertyUtils.getSimpleProperty(form, "it");
            java.sql.Connection conn=common.getConnection();
            DataMethods DBM=new DataMethods(conn);
            if(it.equalsIgnoreCase("U"))
            {
                DBM.deleteUser(id);
                common.CloseConnection(conn);
                return new ActionForward("users.jsp", true);
            }
            else if (it.equalsIgnoreCase("S"))
            {
                product prod=(product)request.getSession().getAttribute("CURRENTPROD");
                DBM.deleteStock(id);
                common.CloseConnection(conn);
                return new ActionForward("pro.jsp?evt="+prod.getEvento()+"&prod="+prod.getIdProd(), true);
            }
            else if (it.equalsIgnoreCase("M"))
            {
                evento ev = (evento) request.getSession().getAttribute("CURRENTEVT");
                DBM.deleteModelsOrder(id,ev.getIdEvt());
                common.CloseConnection(conn);
                return new ActionForward("evt.jsp?evt="+ev.getIdEvt(), true);
            }
            else if (it.equalsIgnoreCase("B"))
            {
                model mod=(model)request.getSession().getAttribute("CURRENTMOD");
                //product prod=mod.getProducts().get(Integer.parseInt(id));
                
                mod.getProducts().remove(Integer.parseInt(id));
                request.getSession().setAttribute("CURRENTMOD", mod);
                return new ActionForward("mod.jsp", true);
            }
            else{return new ActionForward("evt.jsp", true);}
    }
}
