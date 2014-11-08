/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.customtags;

/**
 *
 * @author Iñaki Garaizabal
 */
import com.seglan.shop.entities.evento;
import com.seglan.shop.entities.user;
import com.seglan.shop.sourcecode.DataMethods;
import com.seglan.shop.sourcecode.common;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;
//import com.enoman.entities.*;

public class modellist extends TagSupport {

    @Override
    public int doStartTag() {
        JspWriter out = pageContext.getOut();

        user us = (user) pageContext.getSession().getAttribute("SSUSER");
        evento ev = (evento) pageContext.getSession().getAttribute("CURRENTEVT");

        Connection conn= common.getConnection();
        DataMethods DBM=new DataMethods(conn);
        ResultSet items = DBM.getModelsOrder(ev.getIdEvt());
                
            try {
                out.println("<table style=\"width:100%;padding:0px;margin:0px;\" cellpadding=\"0\" cellspacing=\"0\">");
                int i=0;
                String row;
                while (items.next()) {
                    row=""+(i+1);
                    try {
                        out.println("<tr style=\"border:1px solid black;\"><td style=\"vertical-align:middle;\">"+row+"</td><td><img alt=\"\" src=\"events/"+ev.getIdEvt()+"/models/"+items.getInt("idmodel")+".jpg\" style=\"height: 50px;vertical-align:middle;\"/></td><td style=\"vertical-align:middle;width:200px;\">" + items.getString("modelname") + "</td>");
                        out.println("<td style=\"cursor:pointer;\" onclick=\"document.location.href='delete.do?id=" + i + "&it=M'\"><img alt=\"Eliminar\" src=\"resources/del20.png\"/></td></tr>");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    i=i+1;
                }
                out.println("</table>");
            } catch (Exception e) {
                System.out.println(e);
            }
        common.CloseConnection(conn);

    return EVAL_BODY_INCLUDE ;
}
    @Override
        public int doAfterBody() throws JspException
    {
        return EVAL_BODY_AGAIN;
    }
}
