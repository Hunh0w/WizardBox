$("#btnsubmit").click(function(){


    let form = $("form")[0];
    let mdp = $("input#password");
    let mdpconf = $("input#password_confirm");

    if(mdp != null && mdpconf != null && mdpconf.val() != null){
        if(mdp.val() != mdpconf.val()){
            $("#password,#password_confirm").next().text("Les mot de passes ne correspondent pas");
            $("#password,#password_confirm").addClass("inputerror")
            return;
        }
    }

    form.submit();
});
