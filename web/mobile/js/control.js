var user;
var currentevent = 0;
var currentpase = 0;

$.fn.extend({
    disableSelection: function() {
        this.each(function() {
            this.onselectstart = function() {
                return false;
            };
            this.unselectable = "on";
            $(this).css('-moz-user-select', 'none');
            $(this).css('-webkit-user-select', 'none');
        });
    }
});


$(function() {
    $(this).disableSelection();
});

$(document).ready(function() {
    $('.content').disableSelection();
    $.mobile.buttonMarkup.hoverDelay = 0;
    var img = new Image();
    img.src = 'http://shoppingstage.net/mobile/resources/icon_bn.png';
    $("#content").css("min-height", parseInt($(window).height() - 70) + "px");
    $(".colorbackground").css("min-height", parseInt($(window).height() - 70) + "px");
    if (install()) {
        autologin();
    }
    return true;
});
$(function() {
    FastClick.attach(document.body);
});
function savelogin(usval, pwval)
{
    try {
        window.localStorage.setItem("ssuser", usval);
        window.localStorage.setItem("sspwd", pwval);
    }
    catch (err) {
    }
}
function clearUser()
{
    try {
        window.localStorage.setItem("ssuser", "");
        window.localStorage.setItem("sspwd", "");
    }
    catch (err) {
    }
    showLogin();
}
function autologin()
{
    var us = '';
    var pw = '';
    try {
        us = window.localStorage.getItem("ssuser");
        pw = window.localStorage.getItem("sspwd");
        if ((us !== null) && (pw !== null) && (us.length > 0) && (pw.length > 0))
        {
            var formData = {user: us, pwd: pw};
            //showSpinner(true);
            $.ajax(
                    {
                        type: "POST",
                        url: 'mlogin.do',
                        data: formData,
                        dataType: "json",
                        success: function(resp)
                        {
                            //showSpinner(false);
                            if (resp.status === 'OK')
                            {
                                user = resp.data;
                                if (user.usertype === 'A')
                                {
                                    $("#header").show();
                                    showControl();
                                }
                                else
                                {
                                    $("#header").show();
                                    $('#content').html($('#loginPage').html());
                                    $("#colorbackground").css("height", parseInt($("#body").height()));
                                    $("#page").trigger("create");
                                }
                            }
                            else
                            {
                                $("#header").show();
                                $('#content').html($('#loginPage').html());
                                $("#colorbackground").css("height", parseInt($("#body").height()));
                                $("#page").trigger("create");
                            }
                        },
                        error: function(result) {
                            //showSpinner(false);
                            $("#header").show();
                            $('#content').html($('#loginPage').html());
                            $("#colorbackground").css("height", parseInt($("#body").height()));
                            $("#page").trigger("create");
                        }
                    });

        }
        else
        {
            $("#header").show();
            $('#content').html($('#loginPage').html());
            $("#colorbackground").css("height", parseInt($("#body").height()));
            $("#page").trigger("create");
        }
    }
    catch (err) {
        $("#header").show();
        $('#content').html($('#loginPage').html());
        $("#colorbackground").css("height", parseInt($("#body").height()));
        $("#page").trigger("create");
    }
    return true;
}

function login()
{
    var userval = $("#loginUser").val();
    var password = $("#loginPassword").val();
    if ((userval.length === 0) || (password.length === 0)) {
        showDialog('Rellene los campos', 'Por favor, rellene su email y contraseña de acceso.', "Aceptar", function() {
        });
    }
    else
    {
        var formData = {user: userval, pwd: password};
        showSpinner(true);
        $.ajax(
                {
                    type: "POST",
                    url: 'mlogin.do',
                    data: formData,
                    dataType: "json",
                    success: function(resp)
                    {
                        showSpinner(false);
                        if (resp.status === 'OK')
                        {
                            user = resp.data;
                            if(user.usertype==='A')
                            {
                                $("#loginPassword").val('');
                                showDialogLogin(userval, password);
                            }
                            else
                            {
                                showDialog('Error de autenticación', 'El usuario o contraseña introducido no es correcto.', "Aceptar", function() {});
                            }
                        }
                        else if (resp.data === 'activation')
                        {
                            showDialog('Error de activación', 'El usuario introducido no ha activado su cuenta. Le hemos enviado otro email para completar la activación. Por favor, tenga en cuenta revisar la carpeta de SPAM.', "Aceptar", function() {
                            });
                        }
                        else if (resp.data === 'wrong')
                        {
                            showDialog('Error de autenticación', 'El usuario o contraseña introducido no es correcto.', "Aceptar", function() {
                            });
                        }
                    },
                    error: function(result) {
                        showSpinner(false);
                        showDialog('Error de conexión', 'Por favor, revise su conexión a internet.', "Aceptar", function() {
                        });
                    }
                });
    }
    return true;
}
function showDialogLogin(userval, password) {
    $("#dialog2").css('left', parseInt($(window).width() / 2 - 150) + "px");
    $("#dlg2 .dlg-1").text('The Shopping Stage');
    $("#dlg2 .dlg-2").text('Desea que la aplicación recuerde su usuario y contraseña?');
    $("#dlg2 .dlg-do").text('Aceptar');
    $("#dlg2 .dlg-do").unbind('click');
    $("#dlg2 .dlg-do").click(function() {
        savelogin(userval, password);
        showControl();
        closeDialogLogin();
    });
    $("#dlg2 .dlg-cancel").unbind('click');
    $("#dlg2 .dlg-cancel").click(function() {
        showControl();
        closeDialogLogin();
    });
    $("#dlg2").css("display", "block");
    $("#dlg2").trigger("create");
}
function closeDialogLogin()
{
    $("#dlg2").css("display", "none");
    $("#page").trigger("create");
}
function showControl()
{
    $("#content").html($("#control").html());
    var eventos = "<option value='0'></option>";
    for (var i = 0; i < user.eventos.length; i++)
    {
        eventos += '<option value="' + user.eventos[i].idEvt + '">' + user.eventos[i].eventname + '</option>';
    }
    $("#evts").html(eventos);
    $("#content").trigger("create");
}
function setEvt()
{
    if (checkEvt())
    {
        readVal();
    }
}
function checkEvt()
{
    currentevent = $("#evts").val();
    if (currentevent === '0')
    {
        showDialog('Error', 'Seleccione un evento.', "Aceptar", function() {
        });
        $("#infoPase").html("Pase&nbsp;actual: NA");
        return false;
    }
    else {
        return true;
    }
}
function readVal()
{
    if (checkEvt())
    {
        var date = new Date();
        var uri = "mcurrentpase.do?d=" + date.getDay() + date.getHours() + date.getMinutes() + date.getMilliseconds();

        var formData = {evt: '' + $("#evts").val()};
        $.ajax(
                {
                    type: "POST",
                    url: uri,
                    data: formData,
                    dataType: "json",
                    success: function(resp)
                    {
                        currentpase = resp.data;
                        $("#infoPase").html("Pase&nbsp;actual:&nbsp;" + currentpase);

                    },
                    error: function(result) {
                    }
                });
    }
    return true;
}
function setVal(pasenum)
{
    if (checkEvt())
    {
        var date = new Date();
        var uri = "updatecurrent.do?d=" + date.getDay() + date.getHours() + date.getMinutes() + date.getMilliseconds();

        var formData = {evento: '' + $("#evts").val(), pase: pasenum};
        $.ajax(
                {
                    type: "POST",
                    url: uri,
                    data: formData,
                    dataType: "json",
                    success: function(resp)
                    {
                        currentpase = resp.data;
                        $("#infoPase").html("Pase&nbsp;actual:&nbsp;" + currentpase);
                        $("#content").trigger("create");
                    },
                    error: function(result) {
                    }
                });
    }
    return true;
}
function nextpase() {
    setVal(parseInt(currentpase) + 1);
}
function prevpase() {
    if (currentpase > 0) {
        setVal(parseInt(currentpase) - 1);
    }
}
