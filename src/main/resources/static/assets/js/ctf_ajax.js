function b64_to_utf8( str ) {
    return decodeURIComponent(escape(window.atob( str )));
}

function showCTF(obj){
    $("#ctftitle").text(b64_to_utf8(obj.name)+" ("+b64_to_utf8(obj.winpoints)+" points)");
    $("#ctfdesc").text(b64_to_utf8(obj.description));
    $("#btnsubmit1 > a").attr("href", b64_to_utf8(obj.path));
    $("#btnsubmit2").attr("ctfId", b64_to_utf8(obj.id));
    let cont = $("#container_content");
    if(cont.attr("style")) cont.removeAttr("style");
}

$("button.ctfbtn").click(function(){
    let id = $(this).attr("ctfId");
    $.ajax({
        url: "/api/CTF/"+id
    }).done(function(data){

        let jsonObj = JSON.parse(data);
        showCTF(jsonObj);
    });
});

$("#btnsubmit2").click(function(){
    let id = $(this).attr("ctfId");
    let flag = $("#flag").val();
    $.ajax({
        url: "/api/CTF/"+id+"/validate/"+flag
    }).done(function(data){
        let num = parseInt(data);
        if(num > 0){
            $("#ctftitle").text("+"+data+" points !");
            $("#ctftitle").attr("style", "color: green;");
        }else if(num == -2){
            $("#ctftitle").text("Ce CTF est déjà validé");
            $("#ctftitle").attr("style", "color: green;");
        }else if(num == 0){
            $("#ctftitle").text("Flag Incorrect !");
            $("#ctftitle").attr("style", "color: red;");
        }
    });
});

