
var xmlhttp = new getXMLObject();

//XML OBJECT
function getXMLObject() {
    var xmlHttp = false;
    try {
        xmlHttp = new ActiveXObject("Msxml2.XMLHTTP") // For Old Microsoft
        // Browsers
    } catch (e) {
        try {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP") // For Microsoft
            // IE 6.0+
        } catch (e2) {
            xmlHttp = false // No Browser accepts the XMLHTTP Object then false
        }
    }
    if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
        xmlHttp = new XMLHttpRequest(); // For Mozilla, Opera Browsers
    }
    return xmlHttp; // Mandatory Statement returning the ajax object created
}


function locationUrl(url,activeId){
    //'/standard/my/0','home'
    goUrl(url,null);
}

function goUrl(url,params) {
    //'/standard/my/0'
    if(xmlhttp) {
        //var params = "";
        xmlhttp.open("POST",url,true);
        xmlhttp.onreadystatechange = handleServerResponse;
        xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8');
        xmlhttp.send(params);
    }
}



function handleServerResponse() {
    if (xmlhttp.readyState == 4) {
        var text = xmlhttp.responseText;
        if(text.indexOf("<title>error</title>") >= 0){
            window.location.href="/error.html";
        }else{
            $("#content").html(xmlhttp.responseText);
        }
    }
}