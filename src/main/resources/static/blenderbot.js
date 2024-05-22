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
        chatHeader.classList.add('username-h2');
        chatHeader.textContent = username + "'s homie";
    }

    function initializeChat(username) {
        const messageInput = document.getElementById('message-input');
        const sendButton = document.querySelector('.send-button');
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
            const userMessage = messageInput.value;
            if (userMessage.trim() === "") return;

            const userMessageElement = document.createElement('li');
            userMessageElement.className = 'user-message';
            userMessageElement.classList.add('message', 'sent');
            userMessageElement.innerHTML = `<strong>${username}: </strong> ${userMessage}`;
            messageList.appendChild(userMessageElement);
            messageInput.value = "";

            showTypingIndicator();

            query({
                inputs: userMessage,
                options: { wait_for_model: true }
            }).then(response => {
                removeTypingIndicator();
                const botMessage = response[0].generated_text || "Sorry, I don't have an answer for that.";
                const botMessageElement = document.createElement('li');
                botMessageElement.className = 'bot-message';
                botMessageElement.classList.add('message', 'received');
                botMessageElement.innerHTML = `<strong>Homie:</strong> ${botMessage}`;
                messageList.appendChild(botMessageElement);
                messageList.scrollTop = messageList.scrollHeight;
            }).catch(error => {
                const errorMessageElement = document.createElement('li');
                errorMessageElement.className = 'error-message';
                errorMessageElement.innerHTML = `<strong>Bot: </strong> Error: ${error.message}`;
                messageList.appendChild(errorMessageElement);
            });
        }

        function showTypingIndicator() {
            const typingIndicatorElement = document.createElement('li');
            typingIndicatorElement.className = 'typing-indicator';
            typingIndicatorElement.textContent = '...';
            messageList.appendChild(typingIndicatorElement);
            messageList.scrollTop = messageList.scrollHeight;
        }

        function removeTypingIndicator() {
            const typingIndicator = document.querySelector('.typing-indicator');
            if (typingIndicator) {
                messageList.removeChild(typingIndicator);
            }
        }

        function logout() {
            window.location.href = "/logout";
        }
    }

    async function query(data) {
        const response = await fetch(
            "https://api-inference.huggingface.co/models/facebook/blenderbot-3B",
            {
                headers: { Authorization: "Bearer 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" },
                method: "POST",
                body: JSON.stringify(data),
            }
        );
        const result = await response.json();
        return result;
    }
});
