var term = new Terminal();
var fit = new FitAddon.FitAddon();
var currentline = "";
var resize_bas = false;
const token = $("#token").text();

term.loadAddon(fit);
term.open(document.getElementById('terminal'));
fit.fit();

initWSocket();
/*
$(window).resize(function(){
    let width = $(window).width();
    if(width < 730 && !resize_bas) {
        resize_bas = true;
        term.resize(6, 24);
        reloadTerm();
    }else if(width > 730 && resize_bas){
        resize_bas = false;
        term.resize(75, 24);
        reloadTerm();
    }
});
*/

printDef();

term.onKey(function(key, evt){
    let code = (key.key).charCodeAt(0);
    if(code == 27 || code == 9) return;
    //console.log(key.key);
    //console.log("char code:"+code);
    if(code == 127){
        if(currentline.length <= 0) return;
        term.write("\b \b");
        currentline = currentline.slice(0, -1);
        return;
    }
    if(code == 13){
        term.write("\r\n");
        term.write("Line: '"+currentline+"'");
        term.write("\r\n");
        printDef();
        currentline = "";
        return;
    }
    term.write(key.key);
    currentline += key.key;
});


function initWSocket(){
    let socket = new WebSocket("ws://127.0.0.1:3000");
    socket.onopen = function(e) {
        console.log("[WS Server] Connection established");
        socket.send("TOKEN::"+token);
    };

    socket.onmessage = function(event) {
        console.log(`[WS Server] MESSAGE: ${event.data}`);
    };
    socket.onerror = function(error) {
        console.log(`[WS Server] ERROR: ${error.message}`);
    };
}
function reloadTerm(){
    fit.fit();
    term.reset();
    printDef();
    term.write(currentline);
}
function printDef(){
    term.write('\x1B[31mWizardBox\x1B[0m@\x1B[33mCTF\x1B[0m -> ');
}