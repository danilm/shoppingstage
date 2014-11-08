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
    request.setCharacterEncoding("UTF-8");
    //String prod = request.getParameter("prod");
    //String evt = request.getParameter("evt");
    product prod = (product) session.getAttribute("CURRENTPROD");
    String[] colors = prod.getColors().split(",");
    String[] sizes = prod.getSizes().split(",");
%>
<html:form action="editproductstock">
    <table width="100%">
        <tr>
            <td>
                A&ntilde;adir Stock: 
            </td>
        </tr>
        <tr>
            <td>Color:&nbsp;</td>
            <td><html:select property="color" style="width:80px;" styleClass="font">
                    <%for (int i = 0; i < colors.length; i++) {%>
                    <html:option value="<%=colors[i]%>"><%=colors[i]%></html:option>  
                    <%}%>
                </html:select>
            </td>
        </tr>
        <tr>
            <td>Talla:&nbsp;</td>
            <td><html:select property="size" style="width:80px;" styleClass="font">
                    <%for (int i = 0; i < sizes.length; i++) {%>
                    <html:option value="<%=sizes[i]%>"><%=sizes[i]%></html:option>  
                    <%}%>
                </html:select>
            </td>
        </tr>
        <tr>
            <td>Cantidad:&nbsp;</td>
            <td><html:text property="cantidad" style="width:80px;"  onkeypress="return numericInput(event);"/></td>
        </tr>
        <tr>
            <td>Cant.&nbsp;Fuera:&nbsp;</td>
            <td><html:text property="cantidad2" style="width:80px;"  onkeypress="return numericInput(event);"/></td>
        </tr>
        <tr>
            <td colspan="2"><html:submit>A&ntilde;adir&nbsp;</html:submit></td>
        </tr>
        <tr>
            <td colspan="2">&nbsp;<html:errors property="editproductstockError"/></td>
        </tr>
    </table>
</html:form>
