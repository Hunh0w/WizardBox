function b64_to_utf8( str ) {
    return decodeURIComponent(escape(window.atob( str )));
}

function showChall(obj){
    $("#challtitle").text(b64_to_utf8(obj.name)+" ("+b64_to_utf8(obj.winpoints)+" points)");
    $("#challdesc").text(b64_to_utf8(obj.description));
    $("#btnsubmit1 > a").attr("href", b64_to_utf8(obj.path));
    $("#btnsubmit2").attr("challId", b64_to_utf8(obj.id));
    let cont = $("#container_content");
    if(cont.attr("style")) cont.removeAttr("style");
}

$("button.ctfbtn").click(function(){
    let id = $(this).attr("challId");
    $.ajax({
        url: "/api/challenges/"+id
    }).done(function(data){
        let jsonObj = JSON.parse(data);
        showChall(jsonObj);
    });
});

$("#btnsubmit2").click(function(){
    let id = $(this).attr("challId");
    let flag = $("#flag").val();
    $.ajax({
        url: "/api/challenges/"+id+"/validate/"+flag
    }).done(function(data){
        let num = parseInt(data);
        if(num > 0){
            $("#challtitle").text("+"+data+" points !");
            $("#challtitle").attr("style", "color: green;");
            location.reload();
        }else if(num == -2){
            $("#challtitle").text("Ce Challenge est déjà validé");
            $("#challtitle").attr("style", "color: green;");
        }else if(num == 0){
            $("#challtitle").text("Flag Incorrect !");
            $("#challtitle").attr("style", "color: red;");
        }
    });
});

