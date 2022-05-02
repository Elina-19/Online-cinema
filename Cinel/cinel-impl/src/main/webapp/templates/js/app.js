let stompClient = null;

function connect() {
    let socket = new SockJS("cinel/chat");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe("/messages", function (message) {
            console.log(message);
            let json = JSON.parse(message.body);
            showGreeting(json['message']);
        })
    });
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function sendMessage(message) {
    let json = {
        "message" : message
    }

    // stompClient.send("/messages", {}, JSON.stringify(json));
    stompClient.send("/app/news", {}, JSON.stringify(json));
}
