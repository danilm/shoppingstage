<%--
    Document   : flatProperties
    Created on : 11-mar-2010, 12:27:06
    Author     : Iñaki Garaizabal
--%>

<%@tag import="java.sql.ResultSet"%>
<%@tag import="com.seglan.shop.sourcecode.DataMethods"%>
<%@tag import="com.seglan.shop.sourcecode.common"%>
<%@tag import="com.seglan.shop.entities.model"%>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="shop" uri="/WEB-INF/tlds/shopping.tld"%>
<%
    request.setCharacterEncoding("UTF-8");
    String mod = request.getParameter("mod");
    String evt = request.getParameter("evt");
    if ((mod != null) && (evt != null)) {
        session.setAttribute("CURRENTMOD", new model(evt, mod));
    }
    model mo = (model) session.getAttribute("CURRENTMOD");
    mod = mo.getIdModel();
    evt = mo.getIdEvento();
    java.sql.Connection conn = common.getConnection();
    DataMethods DBM = new DataMethods(conn);

%>
<html:form action="editmodel" enctype="multipart/form-data">
    <table style="width:100%">
        <tr>
            <td style="width:10%;">
                <table width="10%">
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td  colspan="2" style="width:1%;">Nombre&nbsp;del&nbsp;Conjunto:&nbsp;<html:text property="modName" style="width:200px;"/></td>
                    </tr>
                    <tr>
                        <td  colspan="2" style="width:1%;">Marca:&nbsp;
                            <html:select property="marca">
                                <html:option value="0">Marca</html:option>");
                                <%                                    
                                    ResultSet marcas = DBM.getMarcas(mo.getIdEvento());
                                    String mid, mname;
                                    if (marcas != null)
                                        while (marcas.next()) {
                                            mid = "" + marcas.getInt("idmarca");
                                            mname = marcas.getString("mname");
                                            %>
                                <html:option value="<%=mid%>"><%=mname%></html:option>");
                                
                                <%
                                  }%>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 150px;">&nbsp;<img alt="The Shopping Stage" src="events/<%=mo.getIdEvento()%>/models/<%=mo.getIdModel()%>.jpg" style="width: 150px;"/></td>
                        <td><shop:buttonlist/></td>
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
                            <td colspan="2">&nbsp;<html:errors property="editmodelError"/></td>
                    </tr>
                </table>
            </td>
            <td style="text-align: left;vertical-align: top;">
                <table width="10%" style="border:1px solid gray;">
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="2">Añadir&nbsp;Boton:</td>
                    </tr>
                    <tr>
                        <td style="width:1%;">Texto:&nbsp;</td><td><html:text property="btnText" style="width:150px;" maxlength="12"/></td>
                    </tr>
                    <tr>
                        <td>Producto:&nbsp;</td>
                        <td><html:select property="btnProd">
                                <%
                                    ResultSet prods = DBM.getProducts(mo.getIdEvento());
                                    String pid, ref;
                                    if (prods != null)
                                        while (prods.next()) {
                                            pid = "" + prods.getInt("idprod");
                                            ref = prods.getString("reference");
                                %>
                                <html:option value="<%=pid%>"><%=ref%></html:option>");
                                <%}
                                    common.CloseConnection(conn);
                                %>
                            </html:select></td>
                    </tr>
                    <tr>
                        <td colspan="2"><html:submit style="width:100%;" property="btn">A&ntilde;adir</html:submit></td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;<html:errors property="addbuttonError"/></td>
                    </tr>
                </table>
            </td></tr>
    </table>
</html:form>
