function b64_to_utf8( str ) {
    return decodeURIComponent(escape(window.atob( str )));
}

function showCTF(obj){
    $("#ctftitle").text(b64_to_utf8(obj.name)+" ("+b64_to_utf8(obj.winpoints)+" points)");
    $("#ctfdesc").text(b64_to_utf8(obj.description));
    $("#btnsubmit1 > a").attr("href", b64_to_utf8(obj.path));
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

});

