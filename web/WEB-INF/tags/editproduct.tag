<%--
    Document   : flatProperties
    Created on : 11-mar-2010, 12:27:06
    Author     : Iñaki Garaizabal
--%>

<%@tag import="com.seglan.shop.entities.product"%>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%
    
String prod = request.getParameter("prod");
String evt = request.getParameter("evt");
if((prod!=null)&&(evt!=null))
{
    session.setAttribute("CURRENTPROD", new product(prod, evt));
}
product pro=(product)session.getAttribute("CURRENTPROD");
prod=pro.getIdProd();
evt=pro.getEvento();
%>
<html:form action="editproduct" enctype="multipart/form-data">
    <table width="100%">
    <tr>
        <td colspan="3">&nbsp;</td>
    </tr>
    <tr>
        <td rowspan="8" style="width: 320px;">&nbsp;<img alt="The Shopping Stage" src="events/<%=pro.getEvento()%>/products/<%=pro.getIdProd()%>.jpg" style="width: 300px;"/></td>
    </tr>
    <tr>
        <td style="width:1%;">Nombre&nbsp;Producto:</td><td><html:text property="prodName"/></td>
    </tr>
    <tr>
        <td>Referencia:</td><td><html:text property="reference" style="width:200px;"/></td>
    </tr>
    <tr>
        <td>Precio:</td><td><html:text property="price" style="width:60px;" onkeypress="return numericInputComa(event)"/></td>
    </tr>
    <tr>
        <td>Descripci&oacute;n:</td><td><html:textarea property="description" style="width:90%;height:40px;font:normal 14px arial;"/></td>
    </tr>
    <tr>
        <td>Composici&oacute;n:</td><td><html:textarea property="composition"  style="width:90%;height:40px;font:normal 14px arial;"/></td>
    </tr>
    <tr>
        <td>Tallas:</td><td><html:text property="sizes" maxlength="60" style="width:90%;"/></td>
    </tr>
    <tr>
        <td>Colores:</td><td><html:text property="colors" maxlength="60" style="width:90%;"/></td>
    </tr>
    <tr>
        <td colspan="2"><html:file property="file"/></td>
        <td><html:submit>Guardar</html:submit>&nbsp;<html:errors property="editproductError"/></td>
    </tr>
</table>
</html:form>
