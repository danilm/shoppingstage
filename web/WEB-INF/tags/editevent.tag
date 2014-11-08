<%--
    Document   : flatProperties
    Created on : 11-mar-2010, 12:27:06
    Author     : Iñaki Garaizabal
--%>

<%@tag import="java.sql.ResultSet"%>
<%@tag import="com.seglan.shop.sourcecode.common"%>
<%@tag import="com.seglan.shop.sourcecode.DataMethods"%>
<%@tag import="com.seglan.shop.entities.evento"%>
<%@tag import="com.seglan.shop.entities.product"%>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="shop" uri="/WEB-INF/tlds/shopping.tld"%>
        <script>
            $(function() {
                $.datepicker.setDefaults($.datepicker.regional["es"]);
                $("#datepicker").datepicker({
                    firstDay: 1,
                    dateFormat: 'dd/mm/yy',
                    onSelect: function(date) {
                        document.getElementsByName('fecha')[0].value=date;
                        //alert(date)
                    },
                });
            });
        </script>
<%
    request.setCharacterEncoding("UTF-8");
    String evt = request.getParameter("evt");
    if (evt == null) {
        evento ev = (evento) session.getAttribute("CURRENTEVT");
        if (ev == null) {
            evt = "0";
        } else {
            evt = ev.getIdEvt();
        }
    }
    session.setAttribute("CURRENTEVT", new evento(evt));
%>
<html:form action="editevent">
    <table width="100%">
        <tr><td colspan="3" style="text-align: center;"><html:errors property="editeventError"/></td></tr>
        <tr>
            <td style="vertical-align: top;border:1px solid #6e193b;">
                <table>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td style="width:1%;">Nombre&nbsp;Evento:</td><td><html:text property="eventname" style="width:200px;"/></td>
                    </tr>
                    <tr>
                        <td>Direcci&oacute;n:</td><td><html:text property="address" style="width:200px;"/></td>
                    </tr>
                    <tr>
                        <td>Telefono:</td><td><html:text property="phone" style="width:80px;" styleClass="font" onkeypress="return numericInput(event);"/></td>
                    </tr>
                    <tr>
                        <td>email:</td><td><html:text property="email" style="width:200px;"/></td>
                    </tr>
                    <tr>
                        <td>Fecha:</td><td><html:text property="fecha"  style="width:80px;" readonly="true"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div id="datepicker"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><html:submit property="button" value="Guardar"/></td>
                    </tr>
                </table>
            </td>
            <%if(!evt.equals("0")){ %>
            <td style="vertical-align: top;border:1px solid #6e193b;">
                <table>
                    <tr>
                        <td>Orden&nbsp;pases:&nbsp;</td>
                        <td>
                            <html:select property="btnMod">
                                <%
                                    java.sql.Connection conn = common.getConnection();
                                    DataMethods DBM = new DataMethods(conn);
                                    ResultSet mods = DBM.getModels(evt);
                                    String pid, ref;
                                    if(mods!=null)
                                    while (mods.next()) {
                                        pid = "" + mods.getInt("idmodel");
                                        ref = mods.getString("modelname");
                                %>
                                <html:option value="<%=pid%>"><%=ref%></html:option>");
                                <%}
                                    common.CloseConnection(conn);
                                %>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><html:submit property="button" value="Agregar conjunto"/></td>
                    </tr>
                </table>
            </td>
            <td style="vertical-align: top;border:1px solid #6e193b;width:100%;">
                <table>
                    <tr>
                        <td colspan="2"><div style="height:360px;overflow-y: scroll;"><shop:modellist/><div></td>
                    </tr>
                </table>
            </td>
            <%}%>
        </tr>
    </table>
</html:form>
