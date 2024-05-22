document.addEventListener('DOMContentLoaded', () => {
    let username = 'anonymous';

    fetch('/api/username')
        .then(response => response.text())
        .then(data => {
            username = data;
            console.log('Username:', username);
            updateChatHeader(username);
            initializeChat(username);
        })
        .catch(error => {
            console.error('Error fetching username:', error);
            updateChatHeader(username);
            initializeChat(username);
        });

    function updateChatHeader(username) {
        const chatHeader = document.querySelector('.chat-header h2');
        chatHeader.classList.add('username-h2')
        chatHeader.textContent = username + "'s";
    }

    function initializeChat(username) {
        const socket = new SockJS('/chat');
        const stompClient = Stomp.over(socket);

        stompClient.connect({}, (frame) => {
            console.log('Connected:', frame);
            stompClient.subscribe('/topic/messages', (messageOutput) => {
                console.log("subscribed to /topic/messages");
                console.log("Message Received: ", messageOutput);
                if(messageOutput.body != null){
                    showMessage(JSON.parse(messageOutput.body), username);
                }
            });
        });

        const messageInput = document.getElementById('message-input');
        const sendButton = document.getElementById('send-button');
        const messageList = document.getElementById('message-list');
        const logoutButton = document.getElementById('logout-button');

        messageInput.addEventListener('keydown', (event) => {
            if (event.key === 'Enter') {
                sendMessage();
            }
        });

        sendButton.addEventListener('click', sendMessage);
        logoutButton.addEventListener('click', logout);

        function sendMessage() {
            const messageContent = messageInput.value;
            console.log("Message Content: ",messageContent);
            if (messageContent && stompClient) {
                const chatMessage = {
                    message: messageContent,
                    sender: username
                };
                console.log("Sending Message: ", chatMessage);
                stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
                messageInput.value = '';
            }
        }

        function showMessage(messageOutput, username) {
            const messageElement = document.createElement('li');

            if (messageOutput.sender === username) {
                messageElement.classList.add('message', 'sent');
            } else {
                messageElement.classList.add('message', 'received');
            }

            const usernameElement = document.createElement('span');
            usernameElement.classList.add('username');
            usernameElement.textContent = messageOutput.sender + ": ";

            messageElement.appendChild(usernameElement);
            messageElement.appendChild(document.createTextNode(messageOutput.message));
            messageList.appendChild(messageElement);

            messageList.scrollTop = messageList.scrollHeight;
            scrollToBottom();
        }

        function logout() {
            window.location.href = "/logout";
        }
        function scrollToBottom() {
            const messageArea = document.getElementById('message-area');
            messageArea.scrollTop = messageArea.scrollHeight;
        }
    }
});
