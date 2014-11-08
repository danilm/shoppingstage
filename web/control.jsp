<%--
    Document   : main
    Created on : 28-nov-2009, 11:03:08
    Author     : Iñaki Garaizabal
--%>

<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="shop" uri="/WEB-INF/tlds/shopping.tld"%>
<%
    
    try {
        request.setCharacterEncoding("UTF-8");
        com.seglan.shop.entities.user us = (com.seglan.shop.entities.user) session.getAttribute("SSUSER");
        if ((us == null) || (!us.getUsertype().equals("A"))) {
            response.sendRedirect("login.jsp");
        }
        String fpath = "events/3/models";

        fpath = getServletContext().getRealPath(fpath);

        File folder = new File(fpath);
        if (folder.exists()) {
        File[] files = folder.listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f : files) {
                if (f.isDirectory()) {
                    f.delete();
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
        }

%>
<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Shopping Stage</title>
        <link href="resources/shop.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="resources/icon.png"/>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
        <script language="Javascript" src="resources/utils.js"></script>
        <html:base/>
    </head>
    <body>

        <div id="Content">
            <shop:header light="0"/>
            <html:form action="control">
                <table width="100%">
                    <tr>
                        <td>
                            <table>
                                <tr><td>Carpeta:&nbsp;<html:text property="evento" style="width:40px;"/></td></tr>
                                <tr><td><html:submit property="button" value="Guardar">Guardar</html:submit></td></tr>
                                <tr><td><html:errors property="controlError"/></td></tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </html:form>
        </div>

    </body>

</html:html>
<%    } catch (Exception ex) {
        String err = ex.getMessage();
    }
%>
