/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.customtags;

/**
 *
 * @author Iñaki Garaizabal
 */
import com.seglan.shop.entities.product;
import com.seglan.shop.entities.user;
import com.seglan.shop.sourcecode.DataMethods;
import com.seglan.shop.sourcecode.common;
import java.sql.ResultSet;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.MessageResources;
//import com.enoman.entities.*;

public class itemslist extends TagSupport {

    private String listType;

    @Override
    public int doStartTag() {
        JspWriter out = pageContext.getOut();

        user us = (user) pageContext.getSession().getAttribute("SSUSER");
        product prod = (product) pageContext.getSession().getAttribute("CURRENTPROD");

        java.sql.Connection conn = common.getConnection();
        DataMethods DBM = new DataMethods(conn);
        ResultSet items = null;
        
        if (listType.equals("U")) {
            items = DBM.getUsers();
        } else if (listType.equals("S")) {
            items = DBM.getStock(prod.getIdProd());
        }
        //else if(listType.equals("S")){ items= DBM.getConsultants();}
        //MessageResources msg = MessageResources.getMessageResources("com.enoman.struts.ApplicationResource"+getLang());

        if ((items == null)) {
            try {
                out.println("No se han encontrado resultados");
            } catch (Exception ex) {
            }
        } else {
            try {
                out.println("<table style=\"width:100%;padding:0px;margin:0px;\" cellpadding=\"0\" cellspacing=\"0\">");
                if (listType.equals("U")) {
                    out.println("<tr style=\"font-weight: bold;border-bottom:1px solid black;\"><td>Apellidos</td><td>Nombre</td><td>Email</td><td>Telefono</td><td></td></tr>");
                } else if (listType.equals("S")) {
                    out.println("<tr style=\"font-weight: bold;border-bottom:1px solid black;\"><td>Color</td><td>Talla</td><td>Cantidad</td><td>Cantidad2</td><td></td></tr>");
                }
                boolean line = false;
                String stl = "bgcolor=\"white\"";
                while (items.next()) {
                    try {
                        if (line) {
                            stl = "bgcolor=\"white\"";
                        } else {
                            stl = "bgcolor=\"#f7a4c0\"";
                        }
                        line = !line;
                        if (listType.equals("U")) {
                            out.println("<tr " + stl + "><td>" + new String(items.getBytes("userfname"), "UTF-8") + "</td><td>" + new String(items.getBytes("username"), "UTF-8") + "</td><td>" + items.getString("email") + "</td><td>" + items.getString("tlf") + "</td>");
                        } else if (listType.equals("S")) {
                            out.println("<tr " + stl + "><td>" + new String(items.getBytes("color"), "UTF-8") + "</td><td>" + new String(items.getBytes("size"), "UTF-8") + "</td><td>" + items.getInt("cantidad") + "</td><td>" + items.getInt("cantidad2") + "</td>");
                        }
                        out.println("<td style=\"cursor:pointer;\" onclick=\"document.location.href='delete.do?id=" + items.getInt(1) + "&it=" + listType + "'\"><img alt=\"Eliminar\" src=\"resources/del20.png\"/></td></tr>");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                out.println("</table>");
                items.beforeFirst();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    

    common.CloseConnection (conn);
    return EVAL_BODY_INCLUDE ;
}
private String getContactNL(boolean NL)
    {
        if(NL){return "<img style=\"width:20px;\" alt=\"\" src=\"../ok.gif\"/>";}
        else{return "&nbsp;";}
    }
    private String getContLang(String lang)
    {
        if(lang.equals("")){return "&nbsp;";}
        else {return "<img style=\"width:20px;\" alt=\""+lang+"\" src=\"../lang."+lang+".jpg\"/>";}
    }
    @Override
        public int doAfterBody() throws JspException
    {
        return EVAL_BODY_AGAIN;
    }

    /**
     * @return the listType
     */
    public String getListType() {
        return listType;
    }

    /**
     * @param listType the listType to set
     */
    public void setListType(String listType) {
        this.listType = listType;
    }
}
