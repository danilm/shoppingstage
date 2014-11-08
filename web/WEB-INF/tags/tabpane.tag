<%-- 
    Document   : tabpane
    Created on : 10-mar-2010, 17:55:39
    Author     : Iñaki Garaizabal
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<script language=javascript type='text/javascript'>
function showdiv(id){
if (document.getElementById) { // DOM3 = IE5, NS6
document.getElementById('p1').style.visibility = 'collapse';
document.getElementById('p2').style.visibility = 'collapse';
document.getElementById('p3').style.visibility = 'collapse';
document.getElementById('p'+id).style.visibility = 'visible';
document.getElementById('m1').className  = 'tabitem';
document.getElementById('m2').className  = 'tabitem';
document.getElementById('m3').className  = 'tabitem';
document.getElementById('m'+id).className  = 'tabitemsel';
}
else {
if (document.layers) { // Netscape 4
document.tabmenu.visibility = 'collapse';
document.p1.visibility = 'visible';
document.p3.visibility = 'visible';
document.p2.visibility = 'visible';
}
else { // IE 4
document.all.tabmenu.style.visibility = 'collapse';
document.all.p1.style.visibility = 'visible';
document.all.p2.style.visibility = 'visible';
document.all.p3.style.visibility = 'visible';
}
}
}

</script>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="turkana" uri="/WEB-INF/tlds/turkana.tld"%>

<table width="100%" cellpadding="0" cellspacing="0">
    <tr id="tabmenu">
        <td id="m1" width="33%" onclick="javascript:showdiv(1);" class="tabitemsel"><bean:message key="tab.properties"/></td>
        <td id="m2" width="33%" onclick="javascript:showdiv(2);" class="tabitem"><bean:message key="tab.pictures"/></td>
        <td id="m3" onclick="javascript:showdiv(3);" class="tabitem"><bean:message key="tab.location"/></td>
    </tr>
    <tr id="p1" style="visibility:visible;height:400px;vertical-align:top;"><td colspan="3" class="tabcontent"><turkana:flatProperties/></td></tr>
    <tr id="p2" style="visibility:collapse;height:400px;vertical-align:top;"><td colspan="3" class="tabcontent"><turkana:flatPics/></td></tr>
    <tr id="p3" style="visibility:collapse;height:400px;vertical-align:top;"><td colspan="3" class="tabcontent"><turkana:flatMap/></td></tr>
</table>