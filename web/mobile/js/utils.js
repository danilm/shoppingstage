var ProVersion = false;
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
function nospaces(evt)
{
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode === 32)
        return false;

    return true;
}
function numericInput(evt)
{
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;

    return true;
}
function numericInputComa(evt)
{
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode === 44) {
        return true;
    }
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}

function save(nam, val)
{
    localStorage.setItem(nam, val);
}
function read(nam)
{
    return localStorage.getItem(nam);
}
function showDialog(text1, text2, button, callback) {
    $("#dialog").css('left', parseInt($(window).width() / 2 - 150) + "px");
    $("#dlg .dlg-1").text(text1);
    $("#dlg .dlg-2").text(text2);
    $("#dlg .dlg-do").text(button);
    $("#dlg .dlg-do").unbind('click');
    $("#dlg .dlg-do").click(function() {
        callback();
        closeDialog();
    });
    $("#dlg").css("display", "block");
    $("#dlg").trigger("create");
}
function scrtransitionNoAnimation(contentData, composeFunction, solid)
{
    //$("#content").animate({
    //    opacity: 0}, 0, function() {
        $('#content').html(contentData);
        composeFunction();
    //}).animate({opacity: 1}, 0);
    $("#content").trigger("create");
}
function scrtransition(contentData, composeFunction,solid)
{
    
    
    $('#transition').html(contentData);
    composeFunction();
    $("#colorbackground").css("min-height", parseInt($(window).height() - 70) + "px");
    $("#colorbackground").css("height", parseInt($("#content").height()) + "px");
    $("#transition").trigger("create");
    $("#content").animate({left: ""+parseInt($(window).width()) + "px",  opacity: 1}, 600, function() {
        $('#content').html(contentData);
        composeFunction();
        $("#content").trigger("create");
    }).animate({left: "0px"}, 0, function() {
        $("#transition").animate({left: "-"+parseInt($(window).width()) + "px",  opacity: 1}, 600);
        $('#transition').html("");
    });
    $("#transition").animate({left: "0px", top: '' + parseInt($("#content").position().top) + 'px', height: '' + parseInt($("#content").height()) + 'px',  opacity: 1}, 600);
}


var menuShowing = false;
function showMenu()
{
    if (!menuShowing)
    {
        $("#menu").animate({
            top: '70px', opacity: 1}, 600, function() {
        });

    }
    else
    {
        $("#menu").animate({
            top: '-2000px', opacity: 1}, 600, function() {
        });
    }
    menuShowing = !menuShowing;
}
function closeDialog()
{
    $("#dlg").css("display", "none");
    $("#page").trigger("create");
}

function backClick()
{
    history.back();
}
function XMLtoString(document)
{
    return (new XMLSerializer()).serializeToString(document);
}
function showSpinner(method) {

    if (method) {
        $("#spin").css("display", "block");
        $("#spin").trigger("create");
    } else {
        $("#spin").css("display", "none");
    }
}
function install()
{
    return true;
}


