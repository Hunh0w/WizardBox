var term = new Terminal();
var fit = new FitAddon.FitAddon();
var currentline = "";
var resize_bas = false;
const token = $("#token").text();
var connected = false;
var socket;

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

    if (key.domEvent.code === 'KeyC'){
        if (key.domEvent.ctrlKey) {
            socket.send("CMD::"+utf8_to_b64(String.fromCharCode(0x03)));
            return;
        }
    } else if (key.domEvent.code === 'KeyV'){
        if (key.domEvent.ctrlKey) {

        }
    }
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
        if(connected){
            socket.send("CMD::"+utf8_to_b64(currentline));
        }else {
            term.write("Vous n'êtes pas connecté");
            term.write("\r\n");
            printDef();
        }
        currentline = "";
        return;
    }
    term.write(key.key);
    currentline += key.key;
});


function initWSocket(){
    socket = new WebSocket("ws://192.168.1.18:8080");
    socket.onopen = function(e) {
        console.log("[WS Server] Connection established");
        socket.send("TOKEN::"+token);
        console.log("send 'TOKEN::"+token+"'");
    };

    socket.onmessage = function(event) {
        console.log(`[WS Server] MESSAGE: ${event.data}`);
        if(event.data == "AUTH_SUCCESS"){
            socket.send("CTF::1");
        }else if(event.data.startsWith("CTF_LINKED")) {
            connected = true;
        }else if(event.data == "CMDFIN"){
            console.log("CMDFIN!!!!");
            term.write("\r\n");
            printDef();
            term.write(currentline);
        }else if(event.data.startsWith("CMDRESP")){
            let data = event.data.split("::")[1];
            data = b64_to_utf8(data);
            term.write("\r\n");
            term.write(data);
        }
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
function utf8_to_b64( str ) {
    return window.btoa(unescape(encodeURIComponent( str )));
}

function b64_to_utf8( str ) {
    return decodeURIComponent(escape(window.atob( str )));
}