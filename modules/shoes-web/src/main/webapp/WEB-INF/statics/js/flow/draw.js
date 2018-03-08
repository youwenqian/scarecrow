
//画椭圆
function drawRoundRect(ctx,X, Y, W, H,FILL) {
	var x,y,w,h,fillColor;
	if(typeof X == "object") {
		x = X.x;
		y = X.y;
		w = X.w;
		h = X.h;
		fillColor = X.color;
	} else {
		x = X;
		y = Y;
		w = W;
		h = H;
		fillColor = FILL;
	}
    var r = h / 2;
    ctx.beginPath();
    ctx.moveTo(x + r, y);
    ctx.arcTo(x + w, y, x + w, y + h, r);
    ctx.arcTo(x + w, y + h, x, y + h, r);
    ctx.arcTo(x, y + h, x, y, r);
    ctx.arcTo(x, y, x + w, y, r);
    ctx.closePath();  
    ctx.stroke(); 
    if(fillColor) {
    	ctx.fillStyle = fillColor;
    	ctx.fill();
    }
}
//画矩形
function drawRect(ctx,X, Y, W, H,FILL) {
	var x,y,w,h,fillColor;
	if(typeof X == "object") {
		x = X.x;
		y = X.y;
		w = X.w;
		h = X.h;
		fillColor = X.color;
	} else {
		x = X;
		y = Y;
		w = W;
		h = H;
		fillColor = FILL;
	}
	ctx.beginPath();
	ctx.moveTo(x,y);
    ctx.lineTo(x+w,y);
    ctx.lineTo(x+w,y+h);
    ctx.lineTo(x,y+h);
    ctx.lineTo(x,y);
    ctx.closePath();  
    ctx.stroke(); 
	if(fillColor) {
	    ctx.fillStyle = fillColor;
	    ctx.fill();
	}
}
//画菱形
function drawRhombus(ctx,X, Y, W, H,FILL) {
	var x,y,w,h,fillColor;
	if(typeof X == "object") {
		x = X.x;
		y = X.y;
		w = X.w;
		h = X.h;
		fillColor = X.color;
	} else {
		x = X;
		y = Y;
		w = W;
		h = H;
		fillColor = FILL;
	}
    ctx.beginPath();
    ctx.moveTo(x + w/2 , y);
    ctx.lineTo(x + w, y + h/2);
    ctx.lineTo(x + w/2 , y + h);
    ctx.lineTo(x, y + h/2);
    ctx.lineTo(x + w/2,y);
    ctx.closePath(); 
    ctx.stroke(); 
	if(fillColor) {
	    ctx.fillStyle = fillColor;
	    ctx.fill();
	}
}
/**
 * 绘制文本
 * @returns
 */
function drawText(ctx,config,autoWrap,wrapLength,gap) {
	
	var $label="";
	if(config.id != "" && config.id != "undefined" && config.id != null){
		$label=config.id;
	} else{
		$label="";
	}
	var text = config.description;
	var color = config.fontColor;
	var size = config.fontSize;
	var x = config.x + 10;
	var y = config.y + (gap || config.h/5);
	ctx.font = size;
	ctx.strokeStyle = color;
	ctx.textAlign = config.fontTextAlign || "left";
	ctx.textBaseline = config.fontTextBaseline || "top";
	ctx.lineWidth = 1;
	if(text == "") return;
	ctx.beginPath();
	if(autoWrap && autoWrap == true) {
		if($label != ""  && $label != "undefined" && $label != null){ 
		    var circle = { 
		    	x1:x-5,
		    	y1:y-4,
		        r : 10      //圆的半径
		    }; 
		    ctx.strokeStyle = "red"; 
		    //绘制编号圆圈
//		    ctx.arc(circle.x1,circle.y1, circle.r, 0, Math.PI * 2, true);   
		    //编号文本
			ctx.strokeText($label,x-10,y-10);
		} 
			
		for (var i = 0; i < text.length; ) {
			ctx.strokeStyle = color; 
			ctx.strokeText(text.substr(i,wrapLength),x,y);
			y += 20;
			i += wrapLength;
		}
	} else {
		ctx.strokeStyle = color; 
		ctx.strokeText(text,x,y);
	}
	var options={
			
	}
//	ctx.addHitRegion(options);
	ctx.stroke();
	ctx.closePath(); 
	
}
/**
 * 绘制连线
 * @param ctx
 * @param fromX
 * @param fromY
 * @param toX
 * @param toY
 * @param theta
 * @param headlen
 * @param width
 * @param color
 * @returns
 */
function drawArrow(ctx, fromX, fromY, toX, toY,theta,headlen,width,color,points) {	 
	width = typeof(width) != 'undefined' ? width : 1;
	color = typeof(color) != 'color' ? color : '#000';
	var tempX = fromX,tempY = fromY;
	if(points) {
		if(points instanceof Array) {
			if(points.length != 0) {
				var lastPoint = points[points.length-1];
				tempX = lastPoint.x;
				tempY = lastPoint.y;
			}
		}
	}
	theta = typeof(theta) != 'undefined' ? theta : 30; 
	headlen = typeof(theta) != 'undefined' ? headlen : 10; 
	// 计算各角度和对应的P2,P3坐标
	var angle = Math.atan2(tempY - toY, tempX - toX) * 180 / Math.PI, 
	angle1 = (angle + theta) * Math.PI / 180, 
	angle2 = (angle - theta) * Math.PI / 180,
	topX = headlen * Math.cos(angle1), 
	topY = headlen * Math.sin(angle1), 
	botX = headlen * Math.cos(angle2), 
	botY = headlen * Math.sin(angle2);
	ctx.beginPath();
	ctx.moveTo(fromX, fromY); 
	if(tempX != fromX || tempY != fromY) {
		for (var i = 0; i < points.length; i++) {
			ctx.lineTo(points[i].x,points[i].y);
		}
	}
	ctx.lineTo(toX, toY); 
	var arrowX = toX + topX; 
	var arrowY = toY + topY; 
	ctx.moveTo(arrowX, arrowY); 
	ctx.lineTo(toX, toY); 	
	arrowX = toX + botX;
	arrowY = toY + botY; 
	ctx.lineTo(arrowX, arrowY); 
	ctx.strokeStyle = color; 
	ctx.lineWidth = width; 
	ctx.stroke(); 
	ctx.closePath();
}
/**
 * 自动获取节点方位坐标
 * @param base
 * @param target
 * @returns
 */
function autoMatch(base,target){
	var x = base.x, y = base.y, w = base.w,h = base.h;
	var points = {}
	points.top = {"x":x + w/2,"y":y};
	points.right = {"x":x + w,"y":y + h/2};
	points.bottom = {"x":x + w/2,"y":y + h};
	points.left = {"x":x ,"y":y + h/2};
	return points[target];
}	