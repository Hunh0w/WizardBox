$("document").ready(function(){
    $("form .form__group input").focusin(function () {
        $(this).removeClass("inputerror");
    });

    $("form .form__group input").focusout(function () {
        $(this).next().text($(this).attr("placeholder"));
    });
});