$("#btnsubmit").click(function(){


    let form = $("form#loginform");
    let mdp = $("input#password");
    let mdpconf = $("input#password_confirm");
    let title = $("#titleauth");

    if(mdp != null && mdpconf != null){
        if(mdp.val() != mdpconf.val()){
            title.text("Les mots de passes ne correspondent pas");
            title.attr("style", "color: red;");
            mdpconf.val("");
            mdp.val("");
            return;
        }
    }

    form.submit();
});
