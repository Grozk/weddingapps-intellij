function initVideo(){
	// Grab elements, create settings, etc.
	var video = document.getElementById('video');
	
	// Get access to the camera!
	if(navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
		// Not adding `{ audio: true }` since we only want video now
		navigator.mediaDevices.getUserMedia({ video: true }).then(function(stream) {
			try {
				  video.srcObject = stream;
			} catch (error) {
				// old browsers
				video.src = window.URL.createObjectURL(mediaSource);
			}
			video.play();
		});
	}

	// Elements for taking the snapshot
	var canvas = document.getElementById('canvas');
	var context = canvas.getContext('2d');
	
	// Trigger photo take
	video.addEventListener("click", function() {
		if (!video.paused){
			video.pause();
			
			video.style.display = "none";
			canvas.style.display = "block";
			context.drawImage(video, 0, 0, 640, 480);
		}
	});
	
	canvas.addEventListener("click", function() {
		if (video.paused){
			
			video.style.display = "block";
			canvas.style.display = "none";
			
			video.play();
		}
	});
	
}

function sendMessageAndVideo() {

	var obj = new Object();
	obj.message = $('#textarea_message').val();
	obj.image  = canvas.toDataURL('image/jpeg');
	obj.numberSender = $('#text_number').val();
	obj.nameSender = $('#text_name').val();
	obj.idPublic = $('#publicId').val();
	var jsonString= JSON.stringify(obj);
	
	$.ajax({
		type: 'POST',
	    url: 'http://51.75.25.62:8080/message',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    context: document.body,
	    data: jsonString,
	    success: function(){
	    	alert('OK');
	    }
	});
}

function wedding() {
	$('#mainDiv').load('/index/wedding');
}

function message() {

	var publicID = prompt("Entrez l'identifiant du mariage : ");
	$('#publicId').val(publicID);
		
	$.ajax({
		url : '/index/message',
		type : "GET",
		async : false, // Mode synchrone
		dataType : "json",
		complete : function(data) {
			$("#mainDiv").html(data.responseText);
			initVideo();
		}
	});

}

function submitNewWedding(){

	var obj = new Object();
	obj.partner1 = $('#name_partner1').val();
	obj.partner2 = $('#name_partner2').val();
	obj.number1 = $('#num_partner1').val();
	obj.number2 = $('#num_partner2').val();
	obj.weddingDate = $('#weddingDate').val();
	obj.mailCreator = $('#creationMail').val();
	var jsonString= JSON.stringify(obj);
	
	$.ajax({
		type: 'POST',
		async: false,
	    url: 'http://51.75.25.62:8080/wedding',
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    context: document.body,
	    data: jsonString,
	    success: function(data){
	    	$('#label_idPublic_part_variable').text(data.idPublic);
	    	$('#resultLabel_idPublic').css('display','block');
	    }
	});
}

