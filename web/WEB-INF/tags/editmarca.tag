<%--
    Document   : flatProperties
    Created on : 11-mar-2010, 12:27:06
    Author     : Iñaki Garaizabal
--%>

<%@tag import="java.sql.ResultSet"%>
<%@tag import="com.seglan.shop.sourcecode.DataMethods"%>
<%@tag import="com.seglan.shop.sourcecode.common"%>
<%@tag import="com.seglan.shop.entities.marca"%>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="shop" uri="/WEB-INF/tlds/shopping.tld"%>
<%
    request.setCharacterEncoding("UTF-8");
    String mark = request.getParameter("marca");
    String evt = request.getParameter("evt");
    if ((mark != null) && (evt != null)) {
        session.setAttribute("CURRENTMAR", new marca(evt, mark));
    }
    marca mo = (marca) session.getAttribute("CURRENTMAR");
    mark = mo.getIdMarca();
    evt = mo.getIdEvento();
%>
<html:form action="editmarca" enctype="multipart/form-data">
    <table style="width:100%">
        <tr>
            <td style="width:10%;">
                <table width="10%">
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td style="width:1%;">Nombre:&nbsp;<html:text property="marcaName" style="width:200px;"/></td>
                    </tr>
                    <tr>
                        <td style="width:1%;">Dir.&nbsp;Internet:&nbsp;<html:text property="marcaUri" style="width:200px;"/></td>
                    </tr>
                    <tr>
                        <td style="width: 150px;height:150px;">&nbsp;<img alt="The Shopping Stage" src="events/<%=mo.getIdEvento()%>/marcas/<%=mo.getIdMarca()%>.jpg" style="width: 150px;height:150px;"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><html:file property="file"/></td>
                    </tr>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="2"><html:submit style="width:100%;" property="btn">Guardar</html:submit></td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;<html:errors property="editmarcaError"/></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</html:form>
