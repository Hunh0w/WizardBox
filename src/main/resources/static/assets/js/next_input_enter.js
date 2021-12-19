$("input").each(function(index, value){
    $(this).keyup(function(e){
        if(e.keyCode != 13) return;
        let max = $("input").length - 1;
        if(index < max){
            $("input")[index + 1].focus();
        }else {
            $("form")[0].submit();
        }
    });
});