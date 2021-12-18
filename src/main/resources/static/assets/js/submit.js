var btnsubmit = document.getElementById("btnsubmit");
btnsubmit.addEventListener("click", function (){
    let form = document.forms[0];
    let mdp = document.getElementById("password");
    let mdpconf = document.getElementById("password_confirm");
    let title = document.getElementById("titleauth");

    if(mdp != null && mdpconf != null){
        if(mdp.value != mdpconf.value){
            title.innerText = "Les mots de passes ne correspondent pas";
            title.setAttribute("style", "color: red;");
            mdp.value = "";
            mdpconf.value = "";
            return;
        }
    }

    /*
    let inputs = form.getElementsByTagName("input");
    let vide = false;
    for(let i = 0; i < inputs.length; i++){
        if(inputs[i].value.replaceAll(" ", "") == "") {
            vide = true;
            break;
        }
    }
    if(vide){
        title.innerText = "Veuillez renseigner tous les champs";
        title.setAttribute("style", "color: red;");
        return;
    }
    */


    form.submit();
});