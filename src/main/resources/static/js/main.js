var conn = new WebSocket('ws://localhost:8080/socket');

function send(message) {
    conn.send(JSON.stringify(message));
}

//dataChannel.onerror = function(error) {
//    console.log("Error:", error);
//};
//dataChannel.onclose = function() {
//    console.log("Data channel is closed");
//};