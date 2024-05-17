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


//chat

let stompClient = null;

function connect() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function (messageOutput) {
            showMessage(JSON.parse(messageOutput.body));
        });
    });
}

function sendMessage() {
    const messageInput = document.getElementById('message-input');
    const messageContent = messageInput.value.trim();

    if (messageContent && stompClient) {
        const message = {
            sender: 'User', // Replace with the actual username
            content: messageContent,
            type: 'CHAT'
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(message));
        messageInput.value = '';
    }
}

function showMessage(message) {
    const messageList = document.getElementById('message-list');
    const messageElement = document.createElement('li');
    messageElement.className = 'message';
    messageElement.textContent = message.sender + ": " + message.content;
    messageList.appendChild(messageElement);
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    // Display message in the chat area
}


connect();
