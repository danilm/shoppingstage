/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seglan.shop.customtags;

/**
 *
 * @author Iñaki Garaizabal
 */
import com.seglan.shop.entities.model;
import com.seglan.shop.entities.product;
import com.seglan.shop.entities.user;
import java.util.ArrayList;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;
//import com.enoman.entities.*;

public class buttonlist extends TagSupport {

    @Override
    public int doStartTag() {
        JspWriter out = pageContext.getOut();

        user us = (user) pageContext.getSession().getAttribute("SSUSER");
        model mod = (model) pageContext.getSession().getAttribute("CURRENTMOD");

        ArrayList<product> items = mod.getProducts();
        if ((items == null)||(items.size()==0)) {
            try {
                out.println("No hay botones");
            } catch (Exception ex) {
            }
        } else {
            try {
                out.println("<table style=\"width:100%;padding:0px;margin:0px;\" cellpadding=\"0\" cellspacing=\"0\">");
                product pro;
                for(int i=0;i<items.size();i++) {
                    pro=items.get(i);
                    try {
                        out.println("<tr style=\"border:1px solid black;\"><td><img alt=\"\" src=\"events/"+pro.getEvento()+"/products/"+pro.getIdProd()+".jpg\" style=\"width: 30px;\"/></td><td>" + pro.getBtnText() + "</td>");
                        out.println("<td style=\"cursor:pointer;\" onclick=\"document.location.href='delete.do?id=" + i + "&it=B'\"><img alt=\"Eliminar\" src=\"resources/del20.png\"/></td></tr>");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                out.println("</table>");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    

    return EVAL_BODY_INCLUDE ;
}
    @Override
        public int doAfterBody() throws JspException
    {
        return EVAL_BODY_AGAIN;
    }
}
