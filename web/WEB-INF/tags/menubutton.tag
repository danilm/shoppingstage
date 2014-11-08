<%-- 
    Document   : menubutton
    Created on : 10-jun-2010, 17:47:53
    Author     : igaraizabal
--%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="text"%>
<table style="height: 40px;width:100%;" cellpadding="0" cellspacing="0">
    <tr style="height:40px; vertical-align: middle;">
        <td style="height:40px;width:50%;background-image: url('resources/menuleft.png');background-repeat: no-repeat;background-position: top left;"></td>
        <td style="height:40px; vertical-align: middle;margin-top: 2px;">${text}</td>
        <td style="height:40px;width:50%;background-image: url('resources/menuright.png');background-repeat: no-repeat;background-position: top right;"></td>
    </tr>
</table>

