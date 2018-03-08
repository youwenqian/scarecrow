function Flow(cfg) {
	var config = cfg || {};
	var flow = {};
	this.params = config.params || {};
	this.url = config.url || "";
	this.DEFAULT_FONT=config.newfontSize || "";
	this.DEFAULT_W = config.DEFAULT_W || 100;
	this.DEFAULT_H = config.DEFAULT_H || 85;
	this.DEFAULT_X = config.DEFAULT_X || 0;
	this.DEFAULT_Y = config.DEFAULT_Y || 0;
	this.LINE_WIDTH = config.LINE_WIDTH || 2;
	this.OUT_WIDTH = config.OUT_WIDTH || 40;
	this.LINE_COLOR = config.LINE_COLOR || "#3c3c3c";
	this.wrapLength = config.wrapLength || 7;
	this.canvas = config.canvas;
	this.getNodeInfo = function(node){
		var me = this;
		var config = {};
		var location = node.children("location");     
		var content = node.children("content");
		config.id = node.attr("id");
		config.type = node.attr("type"); 
		config.color = node.attr("color");
		if(me.DEFAULT_FONT != ""){
			config.fontSize=me.DEFAULT_FONT;
		}else{
//			config.fontSize = content.attr("fontSize");
			config.fontSize ="12px FangSong";
		} 
		config.fontColor = content.attr("fontColor");
		config.description= content.text();
		//节点内容超过wrapLength*4 个字时，默认每行字体11个，文本宽度150px
		if(config.description.length > 28){
			me.wrapLength=11; 
			me.DEFAULT_W=150; 
		}else if(14 < config.description.length <= 28){
			if(config.type == "start" || config.type == "stop"){
				if(config.description.length > 14 ){
					me.wrapLength=9; 
					me.DEFAULT_W=135;
				}else{
					me.wrapLength=7; 
					me.DEFAULT_W=100;
				}
			}else{
				me.wrapLength=7; 
				me.DEFAULT_W=100;
			}
		}else{
			me.wrapLength=7; 
			me.DEFAULT_W=100;
		}
		config.x = parseInt(location.attr("x") || me.DEFAULT_X);
		config.y = parseInt(location.attr("y") || me.DEFAULT_Y);
		config.w = parseInt(location.attr("w")|| me.DEFAULT_W);
		config.h = parseInt(location.attr("h") || me.DEFAULT_H);
 		return config;
	}
	this.getLineInfo = function(line) {
		var me = this;
		var config = {};
		var content = line.children("content");
		config.from = line.attr("from");
		config.to = line.attr("to");
		config.direction = line.attr("direction");
		config.fontSize = content.attr("fontSize");
		config.fontColor = content.attr("fontColor");
		config.description= content.text();
		//线条内容超过默认值长度时，设置文本内容为每行五个
		if(config.description.length > parseInt(me.wrapLength)){
			me.wrapLength=5;
			me.DEFAULT_W=70;
		}
		config.lineColor = line.attr("color") ||  me.LINE_COLOR;
		return config;
	}
	this.drawFlow = function(xml,ctx) {
		
		var me = this; 
		if(me.DEFAULT_FONT != ""){
			//水平方向放大1.25倍,垂直方向上放大1.12倍
			ctx.scale(1.25,1.12); 
		}
		//绘制图形，填充内容
		$(xml).children("nodes").find("node").each(function(i) {
			
			var node = $(this); 
			var config = me.getNodeInfo(node);
	    	var type = config.type;    	
	    	flow[config.id] = $.extend(true,{},config);
	    	var gap = 10;
	    	
	    	if(type == "start" || type == "stop") {	
	    		drawRoundRect(ctx,config);	    		
	    	} else if(type == "step") {
	    		
	    		drawRect(ctx,config);
	    	} else if(type == "condition") {
	    		drawRhombus(ctx,config);
	    		config.x = config.x + 10;
	    		gap = 30;
	    	}
//	    	ctx.addHitRegion(config);
	    	drawText(ctx,config,true,me.wrapLength,gap);
	    	me.getPositionOnCanvas(ctx,config.x,config.y)
		});
		//绘制线条，填充文本
		$(xml).children("lines").find("line").each(function(i) {
			var config = me.getLineInfo($(this));
			var fromNodeInfo = flow[config.from];
			var toNodeInfo = flow[config.to];
			var gap = -10;
			var fromX,fromY,toX,toY;
			var points = new Array();
			var directions = config.direction.trim().split("-");
			var fromPoint,toPoint;
			fromPoint = autoMatch(fromNodeInfo,directions[0]);
			fromX = fromPoint.x;
			fromY = fromPoint.y;
			toPoint = autoMatch(toNodeInfo,directions[directions.length-1]);
			toX = toPoint.x;
			toY = toPoint.y;
			config.x= parseInt((fromX+toX)/2 -30);
			config.y = parseInt((fromY+toY)/2+10);
			//1个拐点
			if(directions.length == 3) {
				var x1,y1;
				x1 = (directions[0] == "top" || directions[0] == "bottom" ? fromX : toX);
				y1 = (directions[2] == "top" || directions[2] == "bottom" ? fromY : toY);
				points.push({"x":x1,"y":y1});
				config.x = points[0].x - 30;
				config.y = points[0].y+30;
			}
			//2个拐点
			else if(directions.length == 4) {	
				//left-to-to-left	
				if(directions[0] == "left" && directions[3] == "left") {
					me.OUT_WIDTH = (me.OUT_WIDTH > 0 ? -me.OUT_WIDTH : me.OUT_WIDTH);
					points.push({"x":fromX + me.OUT_WIDTH,"y":fromY});
					points.push({"x":fromX + me.OUT_WIDTH,"y":toY});
				}
				//right-to-to-right
				else if(directions[0] == "right" && directions[3] == "right") {
					me.OUT_WIDTH = (me.OUT_WIDTH > 0 ? me.OUT_WIDTH : -me.OUT_WIDTH);
					points.push({"x":fromX + me.OUT_WIDTH,"y":fromY});
					points.push({"x":fromX + me.OUT_WIDTH,"y":toY});
				}
				//bottom-to-bottom
				else if(directions[0] == "bottom" && directions[3] == "bottom"){
					me.OUT_WIDTH = (me.OUT_WIDTH > 0 ? me.OUT_WIDTH : -me.OUT_WIDTH);
					points.push({"x":fromX,"y":fromY+me.OUT_WIDTH});
					points.push({"x":toX,"y":fromY+me.OUT_WIDTH}); 
				}
				else if(directions[0] == "bottom" && directions[3] == "top"){
					me.OUT_WIDTH = (me.OUT_WIDTH > 0 ? me.OUT_WIDTH : -me.OUT_WIDTH);
					points.push({"x":fromX,"y":fromY+me.OUT_WIDTH});
					points.push({"x":toX,"y":fromY+me.OUT_WIDTH}); 
				}
				//top-to-to-top
				else if(directions[0] == "top" && directions[3] == "top"){ 
					me.OUT_WIDTH = (me.OUT_WIDTH > 0 ? -me.OUT_WIDTH : me.OUT_WIDTH);
					points.push({"x":fromX,"y":fromY+me.OUT_WIDTH});
					points.push({"x":toX,"y":fromY+me.OUT_WIDTH});
				}
				//right-to-to-left
				else if(directions[0] == "right" && directions[3] == "left"){
					me.OUT_WIDTH = (me.OUT_WIDTH > 0 ? me.OUT_WIDTH : -me.OUT_WIDTH);
					points.push({"x":fromX + me.OUT_WIDTH/2 ,"y":fromY});
					points.push({"x":fromX + me.OUT_WIDTH/2 ,"y":toY});
				} 
				//left-to-to-right
				else if(directions[0] == "left" && directions[3] == "right"){
					me.OUT_WIDTH = (me.OUT_WIDTH > 0 ? -me.OUT_WIDTH : me.OUT_WIDTH);
					points.push({"x":fromX + me.OUT_WIDTH/2,"y":fromY});
					points.push({"x":fromX + me.OUT_WIDTH/2,"y":toY});
				}
				config.x = (points[0].x + points[1].x)/2;
				config.y = (points[0].y + points[1].y)/2;
			}		
			
			drawArrow(ctx,fromX,fromY,toX,toY,15,15,me.LINE_WIDTH,config.lineColor,points);	
			config.fontTextAlign = "left";
			config.fontTextBaseline = "bottom";
			drawText(ctx,config,true,me.wrapLength,gap);	
		});
	}
	
	this.getPositionOnCanvas=function(ctx,x,y){
//		var rect = canvas.getBoundingClientRect();   
		var obj={
			x:x,
			y:y
		};
		return obj;
	}
	/**
	 * 请求加载数据
	 * 绘制流程
	 */
	this.load = function(){
		var me = this;
		var canvas = me .canvas;
		var context = canvas.getContext('2d');
		canvas.onmousedown = canvasClick;
		$.ajax({  
		        url: me.url,  
		        type: 'post',  
		        dataType: 'json',      
		        data:me.params,
		        error: function(xml) {  
//		            alert("服务器请求异常!");  
		        },  
		        timeout: 1000,
		        success:function(data) {
		        	if(data.success == true) {
		        		context.clearRect(0,0,canvas.width,canvas.height); 
		        		me.drawFlow(data.xml,context); 
		        	} else if(data.success == false) {
//		        		alert("流程加载异常!");  
		        	}       	
		        }    
		 }); 
	}
	/**
	 * 更新流程
	 */
	this.update = function() {
		var me = this;
		me.load();
	} 
	
	
	function canvasClick(e) {
	      // 取得画布上被单击的点
	      var clickX = e.pageX - canvas.offsetLeft;
	      var clickY = e.pageY - canvas.offsetTop;
	 
	      console.log(clickX+"-------"+clickY);
	}
	
	
	this.click=function(){
		var me=this;
		var canvas=me.canvas;
		canvas.click(function (){
             me.getPositionOnCanvas()
             CanvasRenderingContext2D.addHitRegion() ;
             me.addHitRegion();
		});
	}
}