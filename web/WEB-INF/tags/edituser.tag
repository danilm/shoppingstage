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
<html:form action="edituser">
    <table width="100%" >
    <tr>
        <td colspan="3">&nbsp;</td>
    </tr>
    <tr>
        <td style="font-weight: bold;" colspan="2">
            <%
                String tp=request.getParameter("mode");
                if((tp==null)||(tp.length()==0)){
            %>  
            Mi cuenta:
            <%}else{%>Nueva cuenta:<%}%>
        </td></td>
    </tr>
    <tr>
        <td>Email:</td><td><html:text property="email" maxlength="50" style="width:200px;"/></td>
    </tr>
    <%if((tp!=null)&&(tp.length()>0)){%>
    <tr>
        <td>Contrase&ntilde;a:</td><td><html:password property="pwd" maxlength="20" style="width:200px;"/></td>
    </tr>
    <tr>
        <td>Confirme&nbsp;contrase&ntilde;a:</td><td><html:password property="pwd2" maxlength="20" style="width:200px"/></td>
    </tr>
    <%}%>
    <tr>
        <td>Nombre:</td><td><html:text property="username" maxlength="20" style="width:200px;"/></td>
    </tr>
    <tr>
        <td>Apellidos:</td><td><html:text property="userfname" maxlength="20" style="width:200px"/></td>
    </tr>
    <tr>
        <td>Direcci&oacute;n:</td><td><html:text property="address" maxlength="50" style="width:200px"/></td>
    </tr>
    <tr>
        <td>C&oacute;digo&nbsp;Postal:</td><td><html:text property="cp" maxlength="6" style="width:200px" onkeypress="return numericInput(event)"/></td>
    </tr>
    <tr>
        <td>Poblaci&oacute;n:</td><td><html:text property="poblacion" maxlength="20" style="width:200px"/></td>
    </tr>
    <tr>
        <td>Provincia:</td><td><html:text property="provincia" maxlength="20" style="width:200px"/></td>
    </tr>
    <tr>
        <td>Pa&iacute;s:</td><td><html:text property="pais" maxlength="20" style="width:200px"/></td>
    </tr>
    <tr>
        <td>Tel&eacute;fono:</td><td><html:text property="tlf" maxlength="20" style="width:200px" onkeypress="return numericInput(event);"/></td>
    </tr>
    <tr>
        <td>Tipo:</td><td><html:select property="usertype" style="width:200px"><html:option value="A">Administrador</html:option><html:option value="U">Usuario</html:option></html:select></td>
    </tr>
    <tr>
        <td colspan="2"><html:submit>Guardar</html:submit>&nbsp;<html:errors property="edituserError"/></td>
    </tr>
</table>
</html:form>
