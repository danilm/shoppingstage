<%--
    Document   : header
    Created on : 01-nov-2009, 15:26:07
    Author     : Iñaki Garaizabal
--%>

<%@tag import="com.seglan.shop.entities.evento"%>
<%@tag import="java.sql.ResultSet"%>
<%@tag import="com.seglan.shop.sourcecode.DataMethods"%>
<%@tag import="com.seglan.shop.sourcecode.common"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib prefix="turkana" uri="/WEB-INF/tlds/shopping.tld"%>
<%@tag description="Site Header" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
     String mode = request.getParameter("mode");
%>           
<table  class="submenu" style="width:100%;padding:0px;margin:0px;" cellpadding="0" cellspacing="0">
    <tr><td colspan="3">&nbsp;</td></tr>
    <tr>
        <td style="width:33%;">&nbsp;Ver:&nbsp;
            <select id="events" style="width:140px;" styleClass="font" onchange="document.location.href = 'users.jsp?mode=' + document.getElementById('events').value;">
                <option value="L">Lista</option>
                <% if((mode==null)||(!mode.equals("N"))){%>
                <option value="N">Nuevo</option>
                <%}else{%>
                <option value="N" selected="true">Nuevo</option>
                <%}%>
            </select>
        </td>
    </tr>
    <tr><td colspan="3">&nbsp;</td></tr>
</table>