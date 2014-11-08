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
<table  class="submenu" style="width:100%;padding:0px;margin:0px;" cellpadding="0" cellspacing="0">
    <tr><td colspan="3">&nbsp;</td></tr>
    <tr>
        <td style="width:25%;">&nbsp;Evento:&nbsp;
            <select id="events" style="width:140px;" styleClass="font" onchange="document.location.href = 'evt.jsp?evt=' + document.getElementById('events').value;">
                <option value="0">Nuevo</option>
                <%
                    request.setCharacterEncoding("UTF-8");
                    String event = request.getParameter("evt");
                    if (event == null) {
                        evento ev = (evento) session.getAttribute("CURRENTEVT");
                        if (ev == null) {
                            event = "0";
                        } else {
                            event = ev.getIdEvt();
                        }
                    }

                    java.sql.Connection conn = common.getConnection();
                    DataMethods DBM = new DataMethods(conn);
                    ResultSet evts = DBM.getEvents();
                    String evid;
                    if(evts!=null)
                    while (evts.next()) {
                        evid = "" + evts.getInt("idevento");
                        if (evid.equals(event)) {
                            out.println("<option value=\"" + evts.getInt("idevento") + "\" selected=\"true\">" + evts.getString("eventname") + "</option>");
                        } else {
                            out.println("<option value=\"" + evts.getInt("idevento") + "\">" + evts.getString("eventname") + "</option>");
                        }
                    }
                %>
            </select>
        </td>
        <%  if (!event.equals("0")) {%>
        <td style="width:25%;">&nbsp;Conjunto:&nbsp;
            <select id="models" style="width:140px;" styleClass="font" onchange="document.location.href = 'mod.jsp?evt=' + document.getElementById('events').value + '&mod=' + document.getElementById('models').value;">
                <option value="-1"></option>

                <%
                    String mod = request.getParameter("mod");
                    if (mod == null) {
                        mod = "-1";
                    }
                    if (mod.equals("0")) {
                        out.println("<option value=\"0\" selected=\"true\">Nuevo</option>");
                    } else {
                        out.println("<option value=\"0\">Nuevo</option>");
                    }
                    ResultSet mods = DBM.getModels(event);
                    String modid;
                    if(mods!=null)
                    while (mods.next()) {
                        modid = "" + mods.getInt("idmodel");
                        if (modid.equals(mod)) {
                            out.println("<option value=\"" + mods.getInt("idmodel") + "\" selected=\"true\">" + mods.getString("modelname") + "</option>");
                        } else {
                            out.println("<option value=\"" + mods.getInt("idmodel") + "\">" + mods.getString("modelname") + "</option>");
                        }
                    }

                %>
            </select>
        </td>
        <td style="width:25%;">&nbsp;Producto:&nbsp;
            <select id="products" style="width:140px;" styleClass="font" onchange="document.location.href = 'pro.jsp?evt=' + document.getElementById('events').value + '&prod=' + document.getElementById('products').value;">
                <option value="-1"></option>
                <%
                    String prod = request.getParameter("prod");
                    if (prod == null) {
                        prod = "-1";
                    }
                    if (prod.equals("0")) {
                        out.println("<option value=\"0\" selected=\"true\">Nuevo</option>");
                    } else {
                        out.println("<option value=\"0\">Nuevo</option>");
                    }
                    ResultSet prods = DBM.getProducts(event);
                    String prodid;
                    if(prods!=null)
                    while (prods.next()) {
                        prodid = "" + prods.getInt("idprod");
                        if (prodid.equals(prod)) {
                            out.println("<option value=\"" + prods.getInt("idprod") + "\" selected=\"true\">" + prods.getString("reference") + "</option>");
                        } else {
                            out.println("<option value=\"" + prods.getInt("idprod") + "\">" + prods.getString("reference") + "</option>");
                        }
                    }

                %>
            </select>
        </td>
        <td style="width:25%;">&nbsp;Marca&nbsp;
            <select id="marcas" style="width:140px;" styleClass="font" onchange="document.location.href = 'mar.jsp?evt=' + document.getElementById('events').value + '&marca=' + document.getElementById('marcas').value;">
                <option value="-1"></option>
                <%
                    String marca = request.getParameter("marca");
                    if (marca == null) {
                        marca = "-1";
                    }
                    if (marca.equals("0")) {
                        out.println("<option value=\"0\" selected=\"true\">Nueva</option>");
                    } else {
                        out.println("<option value=\"0\">Nueva</option>");
                    }
                    ResultSet marcas = DBM.getMarcas(event);
                    String marcaid;
                    if(marcas!=null)
                    while (marcas.next()) {
                        marcaid = "" + marcas.getInt("idmarca");
                        if (marcaid.equals(marca)) {
                            out.println("<option value=\"" + marcas.getInt("idmarca") + "\" selected=\"true\">" + marcas.getString("mname") + "</option>");
                        } else {
                            out.println("<option value=\"" + marcas.getInt("idmarca") + "\">" + marcas.getString("mname") + "</option>");
                        }
                    }

                %>
            </select>
        </td>

        <% }
            common.CloseConnection(conn);%>
    </tr>
    <tr><td colspan="3">&nbsp;</td></tr>
</table>