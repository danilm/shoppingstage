<%--
    Document   : main
    Created on : 28-nov-2009, 11:03:08
    Author     : Iñaki Garaizabal
--%>

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
            <shop:header light="1"/>
            <shop:submenuhome/>
            <shop:editmodel/>
        </div>
    </body>
</html:html>
<%    } catch (Exception ex) {
        String err = ex.getMessage();
    }
%>
