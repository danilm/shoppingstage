<%--
    Document   : header
    Created on : 01-nov-2009, 15:26:07
    Author     : Iñaki Garaizabal
--%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib prefix="turkana" uri="/WEB-INF/tlds/shopping.tld"%>
<%@tag description="Site Header" pageEncoding="UTF-8"%>
<%@attribute name="light" required="true" %>
<table style="width:100%;padding:0px;margin:0px;" cellpadding="0" cellspacing="0">
    <tr>
        <td style="background-image:url('resources/logo_bk.png');background-repeat:repeat-x;text-align: right;"><img alt="The Shopping Stage" src="resources/logo.png"/></td>
     </tr>
    <tr>
        <td>&nbsp;</td>
     </tr>
    <tr valign="bottom">
        <td align="center">
            <table cellpadding="0" cellspacing="0" style="height:36px;width: 100%;padding-left: 4px;border:none;">
                <tr>
                    <td class="<% if(this.light.equals("1")){out.print("menuItemSelected");}else{out.print("menuItem");} %>" style="" onclick="document.location.href='evt.jsp';">
                        <turkana:menubutton text="Gestion"/>
                    </td>
                    <td class="<% if(this.light.equals("2")){out.print("menuItemSelected");}else{out.print("menuItem");} %>" onclick="document.location.href='users.jsp';">
                        <turkana:menubutton text="Usuarios"/>
                    </td>
                    <td class="<% if(this.light.equals("3")){out.print("menuItemSelected");}else{out.print("menuItem");} %>" onclick="">
                        <turkana:menubutton text=""/>
                    </td>
                    <td class="<% if(this.light.equals("4")){out.print("menuItemSelected");}else{out.print("menuItem");} %>" onclick="">
                        <turkana:menubutton text="Pedidos"/>
                    </td>
                    <td class="<% if(this.light.equals("5")){out.print("menuItemSelected");}else{out.print("menuItem");} %>" onclick="document.location.href='config.jsp';">
                        <turkana:menubutton text="Cuenta"/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

