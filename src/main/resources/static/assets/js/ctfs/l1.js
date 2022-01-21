$("#btnsubmit").click(function(){
    let form = $("form")[0];
    let btn = $("#btnsubmit");
    if(btn.hasAttr("disabled")) return;
    form.submit();
});