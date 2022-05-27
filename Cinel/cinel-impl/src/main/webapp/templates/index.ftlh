<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<#--    <script src="/cinel/templates/js/app.js"></script>-->
    <title>Document</title>
</head>
<body>
<div id="main-content" class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="form-inline">
                <div class="form-group">
                    <label for="connect">WebSocket connection:</label>
                    <button id="connect" class="btn btn-default" onclick="connect()">Connect</button>
                    <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Disconnect
                    </button>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-inline">
                <div class="form-group">
                    <label for="message">Message</label>
                    <input type="text" id="message" class="form-control" placeholder="Your message...">
                </div>
                <button id="send" class="btn btn-default" onclick="sendMessage($('#message').val())">Send</button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="conversation" class="table table-striped">
                <thead>
                <tr>
                    <th>Greetings</th>
                </tr>
                </thead>
                <tbody id="greetings">
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    let stompClient = null;

    function connect() {
        let socket = new SockJS("/cinel/websocket");
        stompClient = Stomp.over(socket);

        var headers = {
            Authorization: "Bearer eyJhbGciOiJIUzUxMiJ9.eyJST0xFIjoiVVNFUiIsInN1YiI6ImVsaW5hXzNzaGFAbWFpbC5ydSIsImV4cCI6MTY1MzIzMzAwNCwiaWF0IjoxNjUzMjMyNDA0fQ.YE34irUSKzwQ5U0ohJ_5a8_nVMeXvTqYLMQfUHmnGHfKG7cFTgJCzLa5ktOI7AWfvx36UoPdOyBz8NxRO-iDyQ"
        };
        stompClient.connect(headers, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe("/chat/room/123e4567-e89b-12d3-a456-556642440000", function (message) {;
                console.log(message);
                let json = JSON.parse(message.body);
                showGreeting(json['text']);
            })
        });
    }

    function showGreeting(message) {
        $("#greetings").append("<tr><td>" + message + "</td></tr>");
    }

    function sendMessage(message) {
        let json = {
            "text" : message
        }

        stompClient.send("/app/room/123e4567-e89b-12d3-a456-556642440000", {}, JSON.stringify(json));
    }
</script>
</body>
</html>
