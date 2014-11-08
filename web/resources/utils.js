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
