var user;
var timer = 0;
var currentmodel = 0;
var currentprod = 0;
var currentuserid = 0;
var currentprod = 0;
var staticmodel = -1;
var encontradolike = false;
var encontradodislike = false;
var encontradoneutro = true;
var encontradofav = false;
var models;
var marcas;
var likestatus;
var countries = ["Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cote d\u0027Ivoire", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Faeroe Islands", "Falkland Islands", "Fiji", "Finland", "Former Yugoslav Republic of Macedonia", "France", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard Island and McDonald Islands", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea", "Northern Marianas", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn Islands", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russia", "Rwanda", "Sqo Tome and Principe", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "South Korea", "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard and Jan Mayen", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "The Bahamas", "The Gambia", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Virgin Islands", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Wallis and Futuna", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"];

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
    $("#menu").css("top", "-" + parseInt($("#menu").height() + 70) + "px");
    $("#content").css("min-height", parseInt($(window).height() - 70) + "px");
    $("#transition").css("min-height", parseInt($(window).height() - 70) + "px");
    $("#transition").css("left", "-" + parseInt($(window).width()) + "px");
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
function enterEvent(idevt)
{
    var date = new Date();
    var formData = {idevent: idevt};
    showSpinner(true);
    $.ajax({
        type: "POST",
        url: "mevent.do?" + date.getDay() + date.getHours() + date.getMinutes() + date.getMilliseconds(),
        data: formData,
        dataType: 'json',
        success: function(result) {
            if (result.status === "KO")
            {
                showSpinner(false);
                showDialog('Error de conexión', 'Por favor, revise su conexión a internet.', "Aceptar", function() {
                });
            }
            else
            {
                showSpinner(false);
                models = jQuery.parseJSON(result.data);
                marcas = jQuery.parseJSON(result.status);
                likestatus = jQuery.parseJSON(result.like);
                favstatus = jQuery.parseJSON(result.fav);
                preloadImg();
                $("#btnmenu").show();
                $("#btnbuy").show();
                showLive();
            }
        },
        error: function(result) {
            showSpinner(false);
            showDialog('Error de conexión', 'Por favor, revise su conexión a internet.', "Aceptar", function() {
            });
        }
    });
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
                                $("#header").show();
                                showEventmenu();
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
function slide(mydiv) {
    $('#' + mydiv).slideToggle(200);
    if (mydiv === 'compRow') {
        $('#descRow').slideUp(200);
    }
    else {
        $('#compRow').slideUp(200);
    }
}
function checkModelChange()
{
    var date = new Date();
    var uri = "mcurrentpase.do?d=" + date.getDay() + date.getHours() + date.getMinutes() + date.getMilliseconds();

    var formData = {evt: '' + models[0].idEvento};
    $.ajax(
            {
                type: "POST",
                url: uri,
                data: formData,
                dataType: "json",
                success: function(resp)
                {
                    if ((parseInt(resp.data) !== currentmodel) && (parseInt(resp.data) < models.length))
                    {
                        currentmodel = parseInt(resp.data);
                        if ((staticmodel < 0) && (models !== null))
                        {
                            scrtransition($('#mainPage').html(), function() {
                                composemodel(currentmodel);
                            }, true);

                            /*$("#content").animate({
                             opacity: 0}, 200, function() {
                             composemodel(currentmodel);
                             }).animate({opacity: 1}, 200);*/
                        }
                    }
                },
                error: function(result) {
                }
            });

}
function composemodel(index)

{
    if (index < 0) {
        index = 0;
    }
    $("#model").attr('src', 'http://shoppingstage.net/events/' + models[index].idEvento + '/models/' + models[index].idModel + '.jpg');
    $("#modeltitle").html(models[index].modelName);
    var buttons = '';
    for (var i = 0; i < models[index].products.length; i++)
    {
        buttons += '<tr"><td style="width: 60%;">&nbsp;</td><td style="width: 30%;" class="prodbutton" onclick=\"return showProd(' + i + ');\">' + models[index].products[i].btnText + '</td><td style="width: 10%;">&nbsp;</td></tr>';
    }
    $("#modelrow").css("height", parseInt($("#modelrow").width()) + "px");
    $("#itemList").html(buttons);
    $("#content").trigger("create");
    $("#page").trigger("create");
}
function preloadImg()
{
    for (var i = 0; i < models.length; i++)
    {
        var img = new Image();
        img.src = 'http://shoppingstage.net/events/' + models[i].idEvento + '/models/' + models[i].idModel + '.jpg';
        for (var j = 0; j < models[i].products.length; j++)
        {
            var img = new Image();
            img.src = 'http://shoppingstage.net/events/' + models[i].products[j].evento + '/products/' + models[i].products[j].idProd + '.jpg';
        }
    }
}

function showLive()
{
    staticmodel = -1;
    if (!timer) {
        timer = setInterval(function() {
            checkModelChange();
        }, 3000);
    }
    showMain();
    checkModelChange();
    return true;

}
function showModel(index)
{
    staticmodel = index;
    showMain();
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
                            $("#loginPassword").val('');
                            showDialogLogin(userval, password);
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
function pwdReminder()
{
    var nemail = $("#reminderemail").val();
    if (nemail.length === 0) {
        showDialog('Rellene los campos', 'Por favor, rellene su email.', "Aceptar", function() {
        });
    }
    else
    {
        var formData = {email: nemail};
        showSpinner(true);
        $.ajax(
                {
                    type: "POST",
                    url: 'mreminder.do',
                    data: formData,
                    dataType: "json",
                    success: function(resp)
                    {
                        showSpinner(false);
                        if (resp.status === 'OK')
                        {
                            showDialog('Contraseña restablecida', 'Le hemos enviado un email con su contraseña de acceso. Podrá cambiar su contraseña desde su menú de Cuenta. No olvide revisar el SPAM.', "Aceptar", function() {
                                showLogin();
                            });
                        }
                        else
                        {
                            showDialog('Error restablecimiento', 'Se ha producido un error restableciendo su contraseña.', "Aceptar", function() {
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
function addChart()
{
    var scolor = $("#prodColors").val();
    var ssize = $("#prodSizes").val();
    if ((scolor === null) || (ssize === null) || (scolor === ' ') || (ssize === ' '))
    {
        showDialog('Error', 'Por favor, seleccione color y talla.', "Aceptar", function() {
        });
    }
    else
    {
        var formData = {iduser: user.id, idevt: models[0].idEvento, idprod: currentprod, color: scolor, size: ssize};
        showSpinner(true);
        $.ajax(
                {
                    type: "POST",
                    url: 'addchart.do',
                    data: formData,
                    dataType: "json",
                    success: function(resp)
                    {
                        showSpinner(false);
                        if (resp.status === 'OK')
                        {
                            showDialog('Producto añadido', 'Se ha añadido el producto a su carrito de la compra.', "Aceptar", function() {
                            });
                        }
                        else
                        {
                            showDialog('No se ha podido añadir', 'El producto seleccionado no ha podido ser añadido al carrito.', "Aceptar", function() {
                            });
                        }
                    }, error: function(result) {
                        showSpinner(false);
                        showDialog('Error de conexión', 'Por favor, revise su conexión a internet.', "Aceptar", function() {
                        });
                    }
                });
    }
    return true;
}
function deleteChart(IdRow)
{
    var formData = {iduser: user.id, idrow: IdRow, idevento: models[0].idEvento};
    showSpinner(true);
    $.ajax(
            {
                type: "POST",
                url: 'deletechart.do',
                data: formData,
                dataType: "json",
                success: function(resp)
                {
                    showSpinner(false);
                    if (resp.status === 'OK')
                    {
                        composeChart(resp.data);
                    }
                    else
                    {
                        if (resp.data === 'Empty') {
                            showDialog('Vacio', 'No ha agregado ningun producto a su carrito', "Aceptar", function() {
                                showMain();
                            });
                        }
                        else if (resp.data === 'DBError') {
                            showDialog('Error', 'Se ha producido un error recuperando su carrito.', "Aceptar", function() {
                                showMain();
                            });
                        }
                    }
                },
                error: function(result) {
                    showSpinner(false);
                    showDialog('Error de conexión', 'Por favor, revise su conexión a internet.', "Aceptar", function() {
                    });
                }
            });
    return true;
}


function confirmChart(userId,idEvento)
{
    
    var mensaje = "Pago confirmado del usuario " + userId + " , el evento: " + idEvento;

    showDialog('Información', mensaje, "Aceptar", function() {
    });
     
   //OLD: var formData = {iduser: user.id, evtid: models[0].idEvento};
    var formData = {iduser: userId, evtid: idEvento};
    showSpinner(true);
    $.ajax(
            {
                type: "POST",
                url: 'confirmchart.do',
                data: formData,
                dataType: "json",
                success: function(resp)
                {
                    showSpinner(false);
                    if (resp.status === 'OK')
                    {
                        showDialog('Confirmado', 'Su carrito ha sido confirmado', "Aceptar", function() {
                            showPayPal(formData);
                        });
                    }
                    else
                    {
                        showDialog('No Confirmado', 'Su carrito ha podido ser confirmado', "Aceptar", function() {
                            showMain();
                        });
                    }
                },
                error: function(result) {
                    showSpinner(false);
                    showDialog('Error de conexión', 'Por favor, revise su conexión a internet.', "Aceptar", function() {
                    });
                }
            });
    return true;
}
function getChart()
{
    var formData = {iduser: user.id, idevento: models[0].idEvento};
    showSpinner(true);
    $.ajax(
            {
                type: "POST",
                url: 'getchart.do',
                data: formData,
                dataType: "json",
                success: function(resp)
                {
                    showSpinner(false);
                    if (resp.status === 'OK')
                    {
                        composeChart(resp.data);
                    }
                    else
                    {
                        if (resp.data === 'Empty') {
                            showDialog('Vacio', 'No ha agregado ningun producto a su carrito', "Aceptar", function() {
                                showMain();
                            });
                        }
                        else if (resp.data === 'DBError') {
                            showDialog('Error', 'Se ha producido un error recuperando su carrito.', "Aceptar", function() {
                                showMain();
                            });
                        }
                    }
                },
                error: function(result) {
                    showSpinner(false);
                    showDialog('Error de conexión', 'Por favor, revise su conexión a internet.', "Aceptar", function() {
                    });
                }
            });
    return true;
}

/**
 * Funcion que se encarga de componer el carrito de la compra montando 
 * el formulario que luego será usado para plataformas de pago como paypal
 * @param {type} chart
 * @returns {undefined}
 */
function composeChart(chart)
{
    var pedidos = '';
    var total = 0.0;
    var contador = 1;
    //pedidos += '<form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">';
    pedidos += '<input type="hidden" name="cmd" value="_cart">';
    pedidos += '<input type="hidden" name="upload" value="1">';
    pedidos += '<input type="hidden" name="bn" value="ShoppingStage_BuyNow_WPS_ES">';
    pedidos += '<input type="hidden" name="business" value="dani.lopez@gmail.com">';
    pedidos += '<input type="hidden" name="currency_code" value="EUR">';
    pedidos += '<input name = "handling_cart" value = "0" type = "hidden">';
   // pedidos += '<input name = "return" value = "http://www.shoppingstage.net/mobile/paymentok.html?user=' + user.id + '&evento=' + models[0].idEvento+ '" type = "hidden">';
    pedidos += '<input name = "return" value = "http://www.shoppingstage.net/mobile/ipnhandler.do?" type = "hidden">';
    pedidos += '<input name = "cbt" value = "Return to My Site" type = "hidden">';
    pedidos += '<input name = "cancel_return" value = "http://www.shoppingstage.net/mobile/cancelpayment.html" type = "hidden">';
    pedidos += '<input name = "custom" value = "" type = "hidden">';
    for (var i = 0; i < chart.length; i++)
    {
        pedidos += '<tr><td style="height:120px;width:20%;border-bottom:2pt solid #ff0eaa;vertical-align: middle;text-align: center;background-color:white;"><img src="http://shoppingstage.net/events/' + models[0].idEvento + '/products/' + chart[i].idprod + '.jpg" style="width:auto;height:auto;max-width: 100%;max-height: 100%;"/></td>';
        pedidos += '<td style="width:60%;border-bottom:2pt solid white;vertical-align: middle;"><table><tr><td style="font-weight: bold;">' + chart[i].prodname + '</td></tr><tr><td>Color:&nbsp;' + chart[i].color + '</td></tr><tr><td>Talla:' + chart[i].size + '</td></tr><tr><td>Precio:' + chart[i].precio + '</td></tr></table></td>';
        pedidos += '<td style="width:20%;border-bottom:2pt solid white;vertical-align: middle;color:#6e193b;" onclick="deleteChart(' + chart[i].idrow + ');"><img id="btnmenu" src="resources/del2.png" alt="Eliminar" style="cursor: pointer;height:50px;"/></td></tr>';
        pedidos += '<input type="hidden" name="item_name_' + contador + '" value="' + chart[i].prodname + '">';
        pedidos += '<input type="hidden" name="item_number_' + contador + '" value="' + chart[i].idrow + '">';
        pedidos += '<input type="hidden" name="quantity_' + contador + '" value="1">';
        pedidos += '<input type="hidden" name="amount_' + contador + '" value="' + chart[i].precio + '">';
        total += Number(chart[i].precio);
        contador++;
    }

    pedidos += '<tr style="width:100%;height:100%;vertical-align: middle;"><td style="align:left;width:100%;height:100%;vertical-align: middle;" colspan="3">Total: ' + total + '€</td></tr>';
    //pedidos += '<tr style="width:100%;height:100%;vertical-align: middle;"><td style="width:100%;height:100%;vertical-align: middle;" colspan="3">Seleccione la forma de pago:</td></tr>';



   // pedidos += '<input type="image" height="62" width="31" src="http://www.paypal.com/es_XC/i/btn/x-click-but01.gif" name="submit" alt="PayPal secure payment!">';
   // pedidos += '<img alt="" border="0" width="1" height="1" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" >';
    // pedidos += '</form>';
    $("#chartForm").html(pedidos);
    $("#page").trigger("create");
}
function composeEvents()
{
    var eventos = '';
    //Cabecera de nuestros eventos
    eventos += '<tr><td><div class="botonmenutitle"><p>Desfiles en vivo</p></div></td></tr>';
   
    
    for (var i = 0; i < user.eventos.length; i++)
    {
        eventos += '<tr><td><button onclick="return enterEvent(' + user.eventos[i].idEvt + ');">' + user.eventos[i].eventname + '</button></td></tr>';
    }
    $("#evts").html(eventos);
    $("#page").trigger("create");
}
function composeMarcas()
{
    var buttons = '';
    for (var i = 0; i < marcas.length; i++)
    {
        buttons += '<tr><td><button onclick="return showMarca(' + marcas[i].idMarca + ');">' + marcas[i].marcaName + '</button></td></tr>';
    }
    $("#evts").html(buttons);
    $("#colorbackground").css("height", parseInt($("#body").height()));
    $("#page").trigger("create");
}
function showProd(idprod)
{
    scrtransition($('#productPage').html(), function() {
        composeProd(idprod);
    }, true);
}
function showEventmenu()
{
    scrtransition($('#buttonmenu').html(), function() {
        composeEvents();
    }, true);
}
function showMarcasmenu()
{
    clearInterval(timer);
    timer = 0;
    scrtransition($('#buttonmenu').html(), function() {
        composeMarcas();
    }, true);
}
function showpic(ismodel) {
    var index = currentmodel;
    if (staticmodel >= 0) {
        index = staticmodel;
    }

    //Obtengo el ancho y el alto de la pantalla:
    //alto = screen.height;
    //ancho = screen.width;
    //alto_ventana = $(window).height();   // returns height of browser viewport
    alto_navegador = $(document).height(); // returns height of HTML document
    //ancho_ventana = $(window).width();   // returns width of browser viewport
    ancho_navegador = $(document).width();
    //Tenemos un par de anchos buenos,smarthpones y luego pcs:
    if (ancho_navegador <= 640) {
        anchoImg = 88;
    } else {
        anchoImg = 25;
    }


    if (ismodel) {
        $("#picPage").css("display", "block");
        $("#picTable").css("left", "-" + $(window).width() + 'px');
        $("#picview").attr('src', 'http://shoppingstage.net/events/' + models[index].idEvento + '/models/' + models[index].idModel + '.jpg');
        $("#picPage").trigger("create");
        //$("#pictitle").html(models[index].modelName);
        $("#picTable").animate({top: '' + parseInt($("#model").position().top + 30) + 'px', left: '' + parseInt($("#model").position().left) + 'px', width: '' + parseInt($("#model").width()) + 'px'}, 0, function() {
        }).animate({top: '5%', left: '5%', width: anchoImg + '%'}, 600, function() {
        });

    } else {

        $("#picPage").css("display", "block");
        $("#picTable").css("top", parseInt($("#prodimg").position().top + 25) + 'px');
        $("#picview").attr('src', 'http://shoppingstage.net/events/' + models[index].idEvento + '/products/' + currentprod + '.jpg');
        $("#picPage").trigger("create");
        //$("#pictitle").html("");
        $("#picTable").animate({top: '' + parseInt($("#prodimg").position().top + 30) + 'px', left: '' + parseInt($("#prodimg").position().left) + 'px', width: '' + parseInt($("#prodimg").width()) + 'px'}, 0, function() {
        }).animate({top: '5%', left: '5%', width: anchoImg + '%'}, 600, function() {
        });
    }
}
function closepic() {
    $("#picPage").css("display", "none");
}

function composeProd(idprod) {
    //Aquí componemos también los botones de feedback
    var index = currentmodel;
    if (staticmodel >= 0) {
        index = staticmodel;
    }
    currentprod = models[index].products[idprod].idProd;
    var colors = models[index].products[idprod].colors.split(',');
    var sizes = models[index].products[idprod].sizes.split(',');
    var output = '<option value=" ">Talla</option>';
    for (var i = 0; i < sizes.length; i++) {
        output += '<option value=\"' + sizes[i] + '\">' + sizes[i] + '</option>';
    }
    $("#prodSizes").html(output);
    output = '<option value=" ">Color</option>';
    for (var i = 0; i < colors.length; i++) {
        output += '<option value=\"' + colors[i] + '\">' + colors[i] + '</option>';
    }
    $("#prodColors").html(output);
    $("#compRow").slideUp(0);
    $("#descRow").slideUp(0);
    $("#prodName").html(models[index].products[idprod].prodName);
    //$("#prodRef").html(models[index].products[idprod].reference);
    $("#prodPrize").html(models[index].products[idprod].price + '&nbsp;&#8364;');
    $("#descRow").html(models[index].products[idprod].description);
    $("#compRow").html(models[index].products[idprod].composition);
    $("#prod_details").trigger("create");
    $("#prodimgcell").css("height", "" + $("#prod_details").outerHeight() + "px");
    $("#prodimg").css("max-width", "" + parseInt($(window).width() * 0.45) + "px");
    $("#prodimg").attr('src', 'http://shoppingstage.net/events/' + models[index].products[idprod].evento + '/products/' + models[index].products[idprod].idProd + '.jpg');
    $("#colorbackground").css("height", parseInt($("#body").height()));

    //Compongo los botones de feedback:
    currentuserid = user.id;
    encontradolike = false;
    encontradodislike = false;
    encontradofav = false;
    encontradoneutro = true;
    document.getElementById("fav").src = 'resources/FAVORITO_BLANCO.svg';
    document.getElementById("like").src = 'resources/MEGUSTA_BLANCO.svg';
    document.getElementById("dislike").src = 'resources/NOMEGUSTA_BLANCO.svg';
    for (var i = 0; i < favstatus.length; i++) {
        if ((favstatus[i].idprod == currentprod) && (favstatus[i].idusuario == currentuserid)) {
            if (favstatus[i].favstatus == "0") {
                encontradofav = true;
                //document.getElementById("fav").style.backgroundImage = 'url(resources/LOGO-FAVORITO_SEL.png)';
                document.getElementById("fav").src = 'resources/FAVORITO_NEGRO.svg';
                break;

            } else {
                encontradofav = false;
                document.getElementById("fav").src = 'resources/FAVORITO_BLANCO.svg';
                break;
            }

        }

    }

    for (var i = 0; i < likestatus.length; i++) {
        if ((likestatus[i].idprod == currentprod) && (likestatus[i].idusuario == currentuserid)) {
            if (likestatus[i].likestatus == "0") {
                encontradolike = true;
                encontradodislike = false;
                encontradoneutro = false;
                break;

            } else if (likestatus[i].likestatus == "1") {
                encontradodislike = true;
                encontradolike = false;
                encontradoneutro = false;
                break;

            } else {
                encontradodislike = false;
                encontradolike = false;
                encontradoneutro = true;
                break;

            }

        }

    }
    if (encontradolike) {
        document.getElementById("like").src = 'resources/MEGUSTA_NEGRO.svg';
        document.getElementById("dislike").src = 'resources/NOMEGUSTA_BLANCO.svg';
    } else if (encontradodislike) {
        document.getElementById("like").src = 'resources/MEGUSTA_BLANCO.svg';
        document.getElementById("dislike").src = 'resources/NOMEGUSTA_NEGRO.svg';
    } else if (encontradoneutro) {
        document.getElementById("like").src = 'resources/MEGUSTA_BLANCO.svg';
        document.getElementById("dislike").src = 'resources/NOMEGUSTA_BLANCO.svg';
    }
    $("#page").trigger("create");
}
function showMain()
{
    var index = currentmodel;
    if (staticmodel >= 0) {
        index = staticmodel;
    }
    else {
    }
    scrtransition($('#mainPage').html(), function() {
        composemodel(index);
    }, false);
}
function composeEvent()
{
    var cont = '';
    var newLine = true;
    for (var i = 0; i < models.length; i++)
    {
        if (newLine) {
            cont += "<tr>";
        }
        cont += '<td style="width:50%;"><table class="prodbox" style="width:90%;" onclick="return showModel(' + i + ');"><tr><td class="modeltitle">' + models[i].modelName + '</td></tr>';
        cont += '<tr><td class="modelrow"><img alt="The Shopping Stage" src="http://shoppingstage.net/events/' + models[i].idEvento + '/models/' + models[i].idModel + '.jpg" style="width:100%;display:block;"/></td></tr></table></td>';
        newLine = !newLine;
        if ((newLine) || (i === models.length - 1)) {
            cont += "</tr>";
        }
    }
    $('.modelrow').css("height", parseInt($(window).width() * 0.45) + "px");
    $('#content').html($('#eventPage').html());
    $("#content").css("min-height", parseInt($(window).height() - 70) + "px");
    $('#tablemodels').html(cont);
    $("#page").trigger("create");
}
function composeMarca(idmarca)
{
    var cont = '';
    var newLine = true;
    for (var i = 0; i < models.length; i++)
    {
        if (models[i].marca === idmarca) {
            if (newLine) {
                cont += "<tr>";
            }
            cont += '<td style="width:50%;"><table class="prodbox" style="width:90%;" onclick="return showModel(' + i + ');"><tr><td class="modeltitle">' + models[i].modelName + '</td></tr>';
            cont += '<tr><td class="modelrow"><img alt="The Shopping Stage" src="http://shoppingstage.net/events/' + models[i].idEvento + '/models/' + models[i].idModel + '.jpg" style="width:100%"/></td></tr></table></td>';
            newLine = !newLine;
            if ((newLine) || (i === models.length - 1)) {
                cont += "</tr>";
            }
        }
    }
    $('#content').html($('#eventPage').html());
    $("#content").css("min-height", parseInt($(window).height() - 70) + "px");
    $('#tablemodels').html(cont);
    $("#page").trigger("create");
}
function showMarca(idmarca)
{
    scrtransition($('#eventPage').html(), function() {
        composeMarca('' + idmarca);
    }, false);
}
function showEvent()
{
    clearInterval(timer);
    timer = 0;
    scrtransition($('#eventPage').html(), function() {
        composeEvent();
    }, false);
}
function showChart()
{
    clearInterval(timer);
    timer = 0;
    scrtransition($('#chartPage').html(), function() {
        getChart();
    }, false);
}



function showLogin()
{
    user = null;
    $("#header").show();
    $("#btnmenu").hide();
    $("#btnbuy").hide();

    $('#content').html($('#loginPage').html());
    $("#content").css("min-height", parseInt($(window).height() - 70) + "px");
    $("#colorbackground").css("height", parseInt($("#body").height()));
    $("#page").trigger("create");
}
function showNewUser()
{
    user = null;
    scrtransition($('#newuserPage').html(), function() {
        var paises = '<option value="Espa&ntilde;a">Espa&ntilde;a</option>';
        for (var i = 0; i < countries.length; i++)
        {
            paises += '<option value="' + countries[i] + '">' + countries[i] + '</option>';
        }
        $("#newpais").html(paises);
        var edad = '<option value=" ">Edad</option><option value="<18">&lt;18</option>';
        for (var i = 19; i < 60; i++)
        {
            edad += '<option value="' + i + '">' + i + '</option>';
        }
        edad += '<option value=">60">&gt;60</option>';
        $("#newedad").html(edad);
    }, true);
    $("#colorbackground").css("height", parseInt($("#body").height()));

}
function showStreaming()
{
    clearInterval(timer);
    timer = 0;
    scrtransition('<iframe width="320" height="240" src="http://www.youtube.com/embed/alsefa0qeZk" style="margin-top:40px;" allowfullscreen></iframe>', function() {
        $("#content").trigger("create");
        var video_block = $('#stream');
        video_block.load();
    }, false);
    /*scrtransition('<video autobuffer autoplay id="stream" style="width:80%;padding-top:20px;"><source src="data/streaming.webm" type="video/webm;"/><source src="data/streaming.mp4" type="video/mp4;"/><source src="data/streaming.ogv" type="video/ogg;"/></video>', function() {
     $("#content").trigger("create");
     var video_block = $('#stream');
     video_block.load();
     }, false);*/
    /*scrtransition($('#streaming').html(), function() {
     $("#page").trigger("create");
     $('#stream').attr({'autoplay':'autoplay'});
     $("#page").trigger("create");
     }, false);*/
}
function composeAccount()
{
    $('#editemail').val(user.email);
    $('#editusername').val(user.username);
    $('#edituserfname').val(user.userfname);
    $('#editpoblacion').val(user.poblacion);
    $('#editprovincia').val(user.provincia);
    $('#editaddress').val(user.address);
    $('#editcp').val(user.cp);
    $('#edittlf').val(user.tlf);
    $("#editsexo").val(user.sexo);
    if (user.publi) {
        $('#editpubli').attr('checked', true);
    }
    var edad = '<option value=" ">Edad</option><option value="<18">&lt;18</option>';
    for (var i = 19; i < 60; i++)
    {
        edad += '<option value="' + i + '">' + i + '</option>';
    }
    edad += '<option value=">60">&gt;60</option>';
    $("#editedad").html(edad);
    $("#editedad").val(user.edad);

    var paises = '<option value="Espa&ntilde;a">Espa&ntilde;a</option>';
    for (var i = 0; i < countries.length; i++)
    {
        paises += '<option value="' + countries[i] + '">' + countries[i] + '</option>';
    }
    $("#editpais").html(paises);
    $('#editpais').val(user.pais);
}
function showAccount()
{
    clearInterval(timer);
    timer = 0;
    scrtransition($('#accountPage').html(), function() {
        composeAccount();
    }, true);
}
function showReminder()
{
    scrtransition($('#reminderPage').html(), function() {
        $("#colorbackground").css("height", parseInt($("#body").height()));
    }, true);
}
function updateUser()
{
    user.username = $('#editusername').val();
    user.userfname = $('#edituserfname').val();
    user.pais = $('#editpais').val();
    user.poblacion = $('#editpoblacion').val();
    user.provincia = $('#editprovincia').val();
    user.address = $('#editaddress').val();
    user.cp = $('#editcp').val();
    user.tlf = $('#edittlf').val();
    user.sexo = $('#editsexo').val();
    user.edad = $('#editedad').val();
    user.publi = $('#editpubli').is(':checked');
    var formData = {us: JSON.stringify(user)};
    showSpinner(true);
    $.ajax(
            {
                type: "POST",
                url: 'medituser.do',
                data: formData,
                dataType: "json",
                success: function(data)
                {
                    showSpinner(false);
                    if (data.status === 'OK')
                    {
                        showDialog('Datos actualizados', 'Sus datos de usuario han sido correctamente actualizados.', "Aceptar", function() {
                            showAccount();
                        });
                    }
                    else
                    {
                        showDialog('Error de actualizacion', 'Sus datos no han podido ser actualizados.', "Aceptar", function() {
                            showAccount();
                        });
                    }
                },
                error: function(result) {
                    showSpinner(false);
                    showDialog('Error de conexión', 'Por favor, revise su conexión a internet.', "Aceptar", function() {
                        showAccount();
                    });
                }
            });
}
function changePwd()
{
    var cpwd = $('#currpass').val();
    var npwd = $('#newpass').val();
    var npwd2 = $('#newpass2').val();
    if (cpwd !== user.pwd)
    {
        showDialog('Contraseña erronea', 'La contraseña introducida no es válida', "Aceptar", function() {
        });
    }
    else if (npwd.length < 6)
    {
        showDialog('Contraseña erronea', 'La nueva contraseña debe tener al menos seis caracteres.', "Aceptar", function() {
        });
    }
    else if (npwd !== npwd2)
    {
        showDialog('Contraseña erronea', 'La contraseña y su confirmación no coinciden.', "Aceptar", function() {
        });
    }
    else
    {
        var formData = {userid: user.id, currpass: cpwd, newpass: npwd};
        showSpinner(true);
        $.ajax(
                {
                    type: "POST",
                    url: 'mchangepass.do',
                    data: formData,
                    dataType: "json",
                    success: function(data)
                    {
                        showSpinner(false);
                        if (data.status === 'OK')
                        {
                            showDialog('Contraseña actualizada', 'Sus datos de usuario han sido correctamente actualizados.', "Aceptar", function() {
                            });
                        }
                        else
                        {
                            showDialog('Error actualizando', 'Se ha producido un error al actualizar su contraseña', "Aceptar", function() {
                            });
                        }
                    },
                    error: function(result) {
                        showSpinner(false);
                        showDialog('Error de conexión', 'Por favor, revise su conexión a internet.', "Aceptar", function() {
                            showAccount();
                        });
                    }
                });
    }
}
function newUser()
{
    var pub = 0;
    if ($('#newpubli').is(':checked')) {
        pub = 1;
    }
    var pwd = $('#newpwd').val();
    var pwd2 = $('#newpwd2').val();
    var email = $('#newemail').val();
    if ((email.length === 0) || (pwd.length === 0) || (pwd2.length === 0)) {
        showDialog('Rellene los campos', 'Por favor, rellene al menos su email y contraseña de acceso por duplicado.', "Aceptar", function() {
        });
    }
    else if (pwd !== pwd2) {
        showDialog('Confirmacion de contraseña', 'La contraseña y su confirmación no coinciden.', "Aceptar", function() {
        });
    }
    else if (pwd.length < 6) {
        showDialog('Rellene los campos', 'La contraseña debe tener una longitud minima de seis caracteres.', "Aceptar", function() {
        });
    }
    else
    {
        showSpinner(true);
        var formData = {id: 0, username: $('#newusername').val(), userfname: $('#newuserfname').val(), pwd: $('#newpwd').val(), email: $('#newemail').val(), cp: $('#newcp').val(),
            address: $('#newaddress').val(), poblacion: $('#newpoblacion').val(), provincia: $('#newprovincia').val(), pais: $('#newpais').val(), tlf: $('#newtlf').val(), usertype: 'U',
            publi: pub, sexo: $('#newsexo').val(), edad: $('#newedad').val()};
        $.ajax(
                {
                    type: "POST",
                    url: 'mnewuser.do',
                    data: formData,
                    dataType: "json",
                    success: function(resp)
                    {
                        showSpinner(false);
                        if (resp.status === 'OK')
                        {
                            showDialog('Se ha dado de alta', 'Por favor, revise su correo electronico para completar la activación. Tenga en cuenta que el correo que le hemos enviado puede haber sido tratado como SPAM.', "Aceptar", function() {
                                showLogin();
                            });
                        }
                        else
                        {
                            if (resp.data === 'exist')
                            {
                                showDialog('Ya fue dado de alta', 'El email que ha introducido ya fue dado de alta en el sistema.', "Aceptar", function() {
                                    showLogin();
                                });
                            }
                        }
                    },
                    error: function(result) {
                        showSpinner(false);
                        showDialog('Error de conexión', 'Por favor, revise su conexión a internet.', "Aceptar", function() {
                        });
                    }
                });
    }

}
function sendRequest(formData, url)
{
    var result = false;
    var counter = 0;
    while ((counter < 3) && (!result))
    {
        $.ajax(
                {
                    type: "POST",
                    url: url,
                    data: formData,
                    dataType: "json",
                    success: function(resp)
                    {
                        result = true;
                        return resp;
                    },
                    error: function(result) {
                        result = false;
                    }
                });
        count += 1;
    }
    return jQuery.parseJSON({status: 'KO', data: 'ERROR'});
}
function showDialogLogin(userval, password) {
    $("#dialog2").css('left', parseInt($(window).width() / 2 - 150) + "px");
    $("#dlg2 .dlg-1").text('The Shopping Stage');
    $("#dlg2 .dlg-2").text('¿Desea que la aplicación recuerde su usuario y contraseña?');
    $("#dlg2 .dlg-do").text('Aceptar');
    $("#dlg2 .dlg-do").unbind('click');
    $("#dlg2 .dlg-do").click(function() {
        savelogin(userval, password);
        showEventmenu();
        closeDialogLogin();
    });
    $("#dlg2 .dlg-cancel").unbind('click');
    $("#dlg2 .dlg-cancel").click(function() {
        showEventmenu();
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

/**
 * Funcion para añadir un like 
 * @returns {Boolean}
 */
function addLike(status)
{
    //Antes de enviar nada, vemos si ya ha hecho click DEPRECATED
    if ((encontradolike) && (status == "0")) {
        //Hay que ponerlo a neutro
        status = "2";

    } else if ((encontradodislike) && (status == "1")) {

        status = "2";

    }
    //else {*/
    var megusta = "" + status
    var formData = {iduser: user.id, idevt: models[0].idEvento, idprod: currentprod, like: megusta};
    showSpinner(true);
    $.ajax(
            {
                type: "POST",
                url: 'addlike.do',
                data: formData,
                dataType: "json",
                success: function(resp)
                {
                    showSpinner(false);
                    if (resp.status === 'OK')
                    {
                        if (status == "1") {
                            document.getElementById("like").src = 'resources/MEGUSTA_BLANCO.svg';
                            document.getElementById("dislike").src = 'resources/NOMEGUSTA_NEGRO.svg';
                            encontradolike = false;
                            encontradodislike = true;
                            encontradoneutro = false;
                            showDialog('No Me gusta', 'No Me gusta.', "Aceptar", function() {
                            });
                        } else if (status == "0") {
                            document.getElementById("like").src = 'resources/MEGUSTA_NEGRO.svg';
                            document.getElementById("dislike").src = 'resources/NOMEGUSTA_BLANCO.svg';
                            encontradolike = true;
                            encontradodislike = false;
                            encontradoneutro = false;
                            showDialog('Me gusta', 'Me gusta.', "Aceptar", function() {
                            });
                        } else if (status == "2") {
                            document.getElementById("like").src = 'resources/MEGUSTA_BLANCO.svg';
                            document.getElementById("dislike").src = 'resources/NOMEGUSTA_BLANCO.svg';
                            encontradolike = false;
                            encontradodislike = false;
                            encontradoneutro = true;
                        }

                        //Actualizamos el objeto JSON que se encarga del feedback
                        for (var i = 0; i < likestatus.length; i++) {
                            if ((likestatus[i].idprod == currentprod) && (likestatus[i].idusuario == currentuserid)) {
                                likestatus[i].likestatus = status;
                                break;

                            }


                        }

                    }
                    else
                    {
                        showDialog('Error', 'Error.', "Aceptar", function() {
                        });
                    }
                }, error: function(result) {
                    showSpinner(false);
                    showDialog('Error de conexión', 'Por favor, revise su conexión a internet.', "Aceptar", function() {
                    });
                }
            });

    return true;
    //}


}

/**
 * Funcion para añadir favorito
 * @returns {Boolean}
 */
function addFav()
{

    //Antes de enviar nada, vemos si ya ha hecho click
    if (encontradofav) {
        //showDialog('Favorito', 'Ya está en tu lista de favoritos', "Aceptar", function() {
        //});
        //return false;
        statusfav = "1";
    }
    else {
        statusfav = "0";

    }
    var favorito = "" + statusfav;
    var formData = {iduser: user.id, idevt: models[0].idEvento, idprod: currentprod, fav: favorito};
    showSpinner(true);
    $.ajax(
            {
                type: "POST",
                url: 'addfav.do',
                data: formData,
                dataType: "json",
                success: function(resp)
                {
                    showSpinner(false);
                    if (resp.status === 'OK')
                    {
                        if (statusfav == "1") {
                            document.getElementById("fav").src = 'resources/FAVORITO_BLANCO.svg';
                            encontradofav = false;
                            showDialog('Favorito', 'Eliminado', "Aceptar", function() {
                            });
                        } else {
                            document.getElementById("fav").src = 'resources/FAVORITO_NEGRO.svg';
                            encontradofav = true;
                            showDialog('Favorito', 'Añadido', "Aceptar", function() {
                            });
                        }

                        //Actualizamos el objeto JSON que se encarga del feedback
                        for (var i = 0; i < favstatus.length; i++) {
                            if ((favstatus[i].idprod == currentprod) && (favstatus[i].idusuario == currentuserid)) {
                                favstatus[i].favstatus = statusfav;
                                break;

                            }


                        }

                    }
                    else
                    {
                        showDialog('Error', 'Error.', "Aceptar", function() {
                        });
                    }
                }, error: function(result) {
                    showSpinner(false);
                    showDialog('Error de conexión', 'Por favor, revise su conexión a internet.', "Aceptar", function() {
                    });
                }
            });

    return true;



}

function parseURLParams(url) {
    var queryStart = url.indexOf("?") + 1,
            queryEnd = url.indexOf("#") + 1 || url.length + 1,
            query = url.slice(queryStart, queryEnd - 1),
            pairs = query.replace(/\+/g, " ").split("&"),
            parms = {}, i, n, v, nv;

    if (query === url || query === "") {
        return;
    }

    for (i = 0; i < pairs.length; i++) {
        nv = pairs[i].split("=");
        n = decodeURIComponent(nv[0]);
        v = decodeURIComponent(nv[1]);

        if (!parms.hasOwnProperty(n)) {
            parms[n] = [];
        }

        parms[n].push(nv.length === 2 ? v : null);
    }
    return parms;
}

/**
 * Obtiene el parámetro por name desde una url. 
 * @param {type} name
 * @returns {String}
 */
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}






