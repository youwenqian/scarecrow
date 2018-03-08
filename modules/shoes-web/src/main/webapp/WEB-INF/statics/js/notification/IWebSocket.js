(function(window, $) {
	window.IWebSocket = function(paramObj) {
		var options = {
			//socketURL: getSocketURL(),
	        onOpen:function(event) {
	        },
	        onMessage:function(event) {
	        },
	        onError:function(event) {
	        },
	        onClose:function(event) {
	        }
		};
		
		$.extend(true, options, paramObj);
		
	    var websocket = null;
	    if (window.WebSocket) {
	        websocket = new WebSocket(options.socketURL);
	    } else if (window.MozWebSocket) {
	        websocket = new MozWebSocket(options.socketURL);
	    } else {
	    	alert("当前版本浏览器不支持WebSocket，请升级你的浏览器。");
	    }
	    
	    if(websocket) {
	    	websocket.onopen = function(evnt) {
	    		options.onOpen(evnt);
	    	};
	    	websocket.onmessage = function(evnt) {
	    		options.onMessage(evnt);
	    	};
	    	websocket.onerror = function(evnt) {
	    		options.onError(evnt);
	    	};
	    	websocket.onclose = function(evnt) {
	    		options.onClose(evnt);
	    	};
	    	
	    	window.onbeforeunload = function(){
	    		websocket.close();
	    	};
	    }
	    
	    return websocket;
	};
})(window, $);