$("#btnsubmit").click(function(){
    let form = $("form")[0];
    let btn = $("#btnsubmit");
    if(btn.attr("disabled") != null) return;
    form.submit();
});