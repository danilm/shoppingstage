<%@page import="com.seglan.shop.sourcecode.DataMethods"%>
<%@page import="com.seglan.shop.sourcecode.email"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.File"%>
<%@page import="com.seglan.shop.sourcecode.common"%>
<%@page import="com.seglan.shop.entities.model"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%
    /*String body = "<p>Bienvenido a The Shopping Stage,</p>";
    body += "<p>Para completar la activación de su cuenta, por favor, haga click en el siguiente enlace:</br><a href=\"http://shoppingstage.net/activation.do?id=00001\">Completar Activación</a></p>";
    body += "<p>Le saluda atentamente,</p><p>El equipo \"The Shopping Stage\"</p>";

    email.sendActivation("igaraizabal@yahoo.es", "Bienvenido a The Shopping Stage", body);
    
*/    
%>
<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Shopping Stage</title>
        <script language="Javascript" src="resources/utils.js"></script>
        <link href="shop.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="icon.png"/>
        <html:base/>
    </head>
    <body style="background-color: white">
        <html:form action="login">
            <table style="width:300px;border:1px solid gray;background-color: white;text-align: left; margin-left: auto;margin-right: auto;">
                <tr><td colspan="2"><img alt="The Shoping Stage" src="resources/logo.png" style="width:100%;"/></td></tr>
                <tr><td colspan="2">&nbsp;<html:errors property="loginError"/></td></tr>
                <tr><td style="width:80px;">User:</td><td style="width:220px;"><html:text style="width:220px;" property="luser" onkeypress="return nospaces(event)"/></td></tr>
                <tr><td>Password:</td><td><html:password style="width:220px;" property="pwd"/></td></tr>
                <tr><td colspan="2"><html:submit style="width:100%;">Entrar</html:submit></td></tr>
                <tr><td><html:errors property="FrmError"/></td></tr>
            </table>
        </html:form>
    </body>
</html:html>
