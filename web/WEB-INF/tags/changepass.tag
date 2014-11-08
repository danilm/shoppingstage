<%--
    Document   : flatProperties
    Created on : 11-mar-2010, 12:27:06
    Author     : Iñaki Garaizabal
--%>

<%@tag import="com.seglan.shop.entities.user"%>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%
    request.setCharacterEncoding("UTF-8");
user us=(user)session.getAttribute("SSUSER");
if(us==null){%>
<script>
    document.location.href='index.jsp';
</script>     
<%}
%>
<html:form action="changepass">
    
    <table width="30%">
    <tr>
        <td colspan="3">&nbsp;</td>
    </tr>
    <tr>
        <td style="font-weight: bold;" colspan="2">Cambio&nbsp;de&nbsp;contrase&ntilde;a:</td></td>
    </tr>
    <tr>
        <td>Contrase&ntilde;a&nbsp;actual:</td><td><html:password property="currpass" maxlength="50" style="width:200px;"/></td>
    </tr>
    <tr>
        <td>Contrase&ntilde;a&nbsp;nueva:</td><td><html:password property="newpass" maxlength="20" style="width:200px;"/></td>
    </tr>
    <tr>
        <td>Confirme&nbsp;contrase&ntilde;a:</td><td><html:password property="newpass2" maxlength="20" style="width:200px"/></td>
    </tr>
    <tr>
        <td colspan="2"><html:submit>Guardar</html:submit></td>
    </tr>
    <tr>
        <td colspan="2">&nbsp;<html:errors property="changepassError"/></td>
    </tr>
</table>
</html:form>
