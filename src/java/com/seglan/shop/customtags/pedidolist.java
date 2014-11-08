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
import com.seglan.shop.sourcecode.DataMethods;
import com.seglan.shop.sourcecode.common;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;
//import com.enoman.entities.*;

public class pedidolist extends TagSupport {

    @Override
    public int doStartTag() {
        JspWriter out = pageContext.getOut();

        String us = (String) pageContext.getSession().getAttribute("SSPEDIDOUSER");
        String conf = (String) pageContext.getSession().getAttribute("SSCONFIRMED");
        Connection conn=common.getConnection();
        DataMethods DBM=new DataMethods(conn);
        ResultSet pedidos=DBM.getHojaPedido(us, conf);
        
            try {
                out.println("<table style=\"width:100%;padding:0px;margin:0px;\" cellpadding=\"0\" cellspacing=\"0\">");
                out.println("<tr style=\"border:1px solid black;\"><td>En&nbsp;tienda</td><td>En&nbsp;Total</td><td>Referencia</td><td>Producto</td><td>Color</td><td>Talla</td><td>Precio</td><td></td></tr>");
                while(pedidos.next()) {
                    try {
                        out.println("<tr style=\"border:1px solid black;\"><td>"+pedidos.getInt("cantidad")+"</td><td>"+pedidos.getInt("cantidad2")+"</td><td>"+pedidos.getString("reference")+"</td><td>"+pedidos.getString("prodname")+"</td><td>"+pedidos.getString("color")+"</td><td>"+pedidos.getString("size")+"</td><td>Precio</td>");
                        out.println("<td style=\"cursor:pointer;\" onclick=\"document.location.href='delete.do?id=" + pedidos.getInt("idrow") + "&it=P'\"><img alt=\"Eliminar\" src=\"resources/del20.png\"/></td></tr>");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
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
