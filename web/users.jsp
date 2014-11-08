<%--
    Document   : main
    Created on : 28-nov-2009, 11:03:08
    Author     : Iñaki Garaizabal
--%>

<%@page import="com.seglan.shop.entities.user"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="shop" uri="/WEB-INF/tlds/shopping.tld"%>
<%
    
    request.setCharacterEncoding("UTF-8");
    String mode = request.getParameter("mode");
    
    
try
        {
        com.seglan.shop.entities.user us=(com.seglan.shop.entities.user)session.getAttribute("SSUSER");
        if((us==null)||(!us.getUsertype().equals("A")))
        {
            response.sendRedirect("login.jsp");
        }
%>
<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Shopping Stage</title>
        <script language="Javascript" src="resources/utils.js"></script>
        <link href="resources/shop.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="resources/icon.png"/>
        <html:base/>
    </head>
    <body>
            <div id="Content">
                <shop:header light="2"/>
                <shop:submenuusers/>
                <table style="width:100%;">
                    <tr>
                        <td style="vertical-align: top;">
                            
                <% if((mode==null)||(!mode.equals("N"))){%>
                <div style="height:460px;overflow-y: scroll;">
                <shop:itemslist listType="U"/>
                </div>
                <%}else{
                session.setAttribute("CURRENTUSER", new user("0"));
                %>
                <shop:edituser/>
                <%}%>
                            
                        </td>
                    </tr>
                </table>
            </div>
    </body>
</html:html>
<%
}catch(Exception ex)
        {String err=ex.getMessage();}
%>
