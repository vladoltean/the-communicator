
//TODO: Transform into class ??

/**
 * @returns the ws connection
 */
function intialiseWebSocketConnectionToSignalingServer(){
    let webSocketConnection = new WebSocket('wss://the-communicator.herokuapp.com/socket');
    webSocketConnection.onopen = () => {
        console.log("Connected to signaling server!")
        /// whoami
        msg = new SignalServerMessage("identify", "", whoami, "");
        sendMessageToSignalingServer(msg)
        initialize();
    }


    webSocketConnection.onmessage = function(msg) {
        console.log("[WS-via signaling server] Got message", msg.data);
        var content = JSON.parse(msg.data);
        var data = content.data;
        switch (content.event) {
        // when somebody wants to call us
        case "offer":
            handleOffer(data);
            break;
        case "answer":
            handleAnswer(data);
            break;
        // when a remote peer sends an ice candidate to us
        case "candidate":
            handleCandidate(data);
            break;
        default:
            break;
        }
    };

    return webSocketConnection;
}

/**
 * @param {*} message :
 *      must have: event, senderId
 *      optional: recipientId
 *   
 * If recipientId is present, the message is forwarded by the server to the recipient.
 * If that recipient does not have an open connection, it throws an error.
 * 
 * @param {*} webSocketConnection 
 */
function sendMessageToSignalingServer(message, webSocketConnection) {
    console.log("Sending message to signaling server ", Json.stringify(message));
    webSocketConnection.send(JSON.stringify(message));
}