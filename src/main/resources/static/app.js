document.addEventListener('DOMContentLoaded', () => {
    let username = 'anonymous';

    fetch('/api/username')
        .then(response => response.text())
        .then(data => {
            username = data;
            console.log('Username:', username);

            initializeChat(username);
        })
        .catch(error => {
            console.error('Error fetching username:', error);
            initializeChat(username);
        });

    function initializeChat(username) {
        const socket = new SockJS('/chat');
        const stompClient = Stomp.over(socket);

        stompClient.connect({}, (frame) => {
            console.log('Connected:', frame);
            stompClient.subscribe('/topic/messages', (messageOutput) => {
                console.log("subscribed to /topic/messages");
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
            if (messageContent && stompClient) {
                const chatMessage = {
                    content: messageContent,
                    sender: username
                };
                stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
                messageInput.value = '';
                showMessage(chatMessage, username);
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
            messageElement.appendChild(document.createTextNode(messageOutput.content));
            messageList.appendChild(messageElement);

            messageList.scrollTop = messageList.scrollHeight;
        }

        function logout() {
            window.location.href = "/logout";
        }
    }
});
