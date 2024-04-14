const messageInput = document.getElementById('message-input');
const messageList = document.getElementById('message-list');

let stompClient = null;

function connect() {
    const socket = new SockJS('/ws'); // Your WebSocket endpoint
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function (messageOutput) {
            showMessage(JSON.parse(messageOutput.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendMessage() {
    let messageContent = messageInput.value.trim();
    if (messageContent && stompClient) {
        const chatMessage = {
            message: messageContent,
            timestamp: new Date().toISOString(),
            // sender information, etc.
        };
        stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
}

function showMessage(messageOutput) {
    const messageElement = document.createElement('li');
    messageElement.innerText = messageOutput.from + ": " + messageOutput.text;
    messageList.appendChild(messageElement);
}

var socket = new SockJS('/chat');
var stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/messages', function (messageOutput) {
        console.log(JSON.parse(messageOutput.body));
    });
});

stompClient.subscribe('/topic/messages', function (messageOutput) {
    var message = JSON.parse(messageOutput.body);
    document.getElementById('messages').innerHTML += '<p>' + message.from + ': ' + message.text + '</p>';
});


window.addEventListener('load', connect);
window.addEventListener('beforeunload', disconnect);


