// P2P connection
var peerConnection;
var dataChannel;

function initialize() {
    const configuration = {'iceServers': [{'urls': 'stun:stun.l.google.com:19302'}]};

    peerConnection = new RTCPeerConnection(configuration, {
        optional: [{
            RtpDataChannels: true
        }]
    });

    // Setup ice handling
    peerConnection.onicecandidate = function(event) {
        console.log("onicecandidate ", event);
        if (event.candidate) {
            sendMessageToSignalingServer({
                event : "candidate",
                data : event.candidate
            });
        }
    };

    peerConnection.addEventListener("connectionstatechange", (event) => {
        console.log("Connection state changed ", event);
        console.log("State: ", peerConnection.connectionState);
    });

    peerConnection.addEventListener('negotiationneeded', (event) => {
        console.log("Negotiation needed event", event);
    });

    createDataChannel();

    listenForPeerConnectionTrackEvent();

}

function createDataChannel(){
    // creating data channel
    dataChannel = peerConnection.createDataChannel("dataChannel", {
        reliable : true
    });

    dataChannel.onerror = function(error) {
        console.log("Error occured on datachannel:", error);
    };

    // when we receive a message from the other peer, printing it on the console
    dataChannel.onmessage = function(event) {
        console.log("message:", event.data);
    };

    dataChannel.onclose = function() {
        console.log("data channel is closed");
    };

}


async function createOffer() {
    try {
        let offer = await peerConnection.createOffer();
        sendMessageToSignalingServer({
            event: "offer",
            data: offer
        });
        peerConnection.setLocalDescription(new RTCSessionDescription(offer))

    } catch (err){
        console.log("Error creating offer ", err);
    }
}


function handleOffer(offer) {
    console.log("Handling offer:", offer);
    peerConnection.setRemoteDescription(new RTCSessionDescription(offer));

    // create and send an answer to an offer
    peerConnection.createAnswer(function(answer) {
        peerConnection.setLocalDescription(answer);
        console.log("Sending Answer:", answer);
        sendMessageToSignalingServer({
            event : "answer",
            data : answer
        });
    }, function(error) {
        alert("Error creating an answer");
    });

};

function handleCandidate(candidate) {
    console.log("Handling candidate:", candidate);
    peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
};

function handleAnswer(answer) {
    console.log("Handling answer:", answer);
    peerConnection.setRemoteDescription(new RTCSessionDescription(answer));
};

function sendMessage() {
    dataChannel.send(input.value);
    input.value = "";
}

async function addVideo() {
    try {
         let constraints = {video: true, audio: true};
         let localStream = null;
         localStream = await navigator.mediaDevices.getUserMedia(constraints);

         localStream.getTracks().forEach( track => {
            console.log("Adding track to connection: ", track);
            peerConnection.addTrack(track, localStream);
         });

    } catch(err){
        console.error("getUserMedia error:", err)
    }

}

function listenForPeerConnectionTrackEvent(){
    const remoteVideo = document.querySelector('#remoteVideo');
    let remoteStream = null;


    peerConnection.ontrack = (event) => {
        console.log("Track event: ", event);

        if(remoteStream === null) {
            remoteStream = new MediaStream();
        }

        remoteStream.addTrack(event.track, remoteStream);

        if(remoteVideo.srcObject === null) {
            remoteVideo.srcObject = remoteStream;
        }


    };

    console.log("Track event listener set up!")
}

async function connect() {
    const videoAndAudioCheckbox = document.querySelector('#videoAndAudioCheckbox');
    recipientId = recipientInput.value;

    if(videoAndAudioCheckbox.checked === true){
      await addVideo();
    }

    createOffer();
}
