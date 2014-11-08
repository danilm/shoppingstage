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
    String conf = request.getParameter("conf");
    if (conf == null) {
        conf = "1";
    }
%>
<table  class="submenu" style="width:100%;padding:0px;margin:0px;" cellpadding="0" cellspacing="0">
    <tr><td colspan="3">&nbsp;</td></tr>
    <tr>
        <td style="width:33;">&nbsp;Pedidos:&nbsp;
            <select id="confirmed" style="width:140px;" styleClass="font" onchange="document.location.href = 'ped.jsp?conf=' + document.getElementById('confirmed').value;">
                <option value="1" <% if(conf.equals("1")){%> selected="true"<%}%>>Confirmados</option>
                <option value="0"<% if(conf.equals("0")){%> selected="true"<%}%>>No confirmados</option>
            </select>
        </td>
        <td style="width:33%;">&nbsp;Usuarios:&nbsp;
            <select id="users" style="width:140px;" styleClass="font" onchange="document.location.href = 'ped.jsp?conf=' + document.getElementById('confirmed').value + '&us=' + document.getElementById('users').value;">
                <option value="-1"></option>
                <%
                    java.sql.Connection conn = common.getConnection();
                    DataMethods DBM = new DataMethods(conn);
                    ResultSet mails = DBM.getEmailsHojaPedido(conf);
                    String us = request.getParameter("us");
                    if (us == null) {us = "0";}

                    String usid;
                    if(mails!=null)
                    while (mails.next()) {
                        usid = "" + mails.getInt("iduser");
                        if (usid.equals(us)) {
                            out.println("<option value=\"" + usid + "\" selected=\"true\">" + mails.getString("email") + "</option>");
                        } else {
                            out.println("<option value=\"" + usid + "\">" + mails.getString("email") + "</option>");
                        }
                    }
                    common.CloseConnection (conn);
                %>
            </select>
        </td>
    </tr>
    <tr><td colspan="3">&nbsp;</td></tr>
</table>