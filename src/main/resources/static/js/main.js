var conn = new WebSocket('ws://localhost:8080/socket');

function send(message) {
    conn.send(JSON.stringify(message));
}
