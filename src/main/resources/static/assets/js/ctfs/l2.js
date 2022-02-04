$("#btnsubmit").click(function(){

    let identifiant = $("#identifiant").val();
    let password = $("#password").val();
    if(identifiant == "ADM1N83" && password == "WIZARD38673"){
        let form = $("form")[0];
        form.submit();
    }else {
        $("#form_box > h1").text("Identifiant / Mot de passe Incorrect");
    }
});