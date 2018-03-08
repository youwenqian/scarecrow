//date格式化
Date.prototype.pattern=function(fmt) {   
	var o = {   
		"M+" : this.getMonth()+1, //月份   
		"d+" : this.getDate(), //日   
//		"h+" : this.getHours()== 0 ? 12 : this.getHours()%12, //小时    十二小时制
		"h+" : this.getHours(), //二十四小时制
		"H+" : this.getUTCHours(), //小时   
		"m+" : this.getMinutes(), //分   
		"s+" : this.getSeconds(), //秒   
		"q+" : Math.floor((this.getMonth()+3)/3), //季度   
		"S" : this.getMilliseconds() //毫秒   
	};   
	var week = {   
		"0" : "	",   
		"1" : "/u4e00",   
		"2" : "/u4e8c",   
		"3" : "/u4e09",   
		"4" : "/u56db",   
		"5" : "/u4e94",   
		"6" : "/u516d" 
	};   
	if(/(y+)/.test(fmt)){   
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	}   
	if(/(E+)/.test(fmt)){   
		fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);   
	}   
	for(var k in o){   
		if(new RegExp("("+ k +")").test(fmt)){   
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		}   
	}   
	return fmt;   
}

function defaultValue(val)
{
	if(val == null || val == "null")
	{
		return "";
	}
	else
	{
		return val;
	}
}

function GetDateTimeDiffer(startTime, endTime) {  
	if(startTime==null){ 
		return "";
	}
	var retValue = {}; 
	//时间差的毫秒数
	var date3 = endTime - startTime;  
	//计算出相差天数
	var days = Math.floor(date3 / (24 * 3600 * 1000)); 
	retValue.Days = days;
	var years = Math.floor(days / 365);
	retValue.Years = years; 
	var months = Math.floor(days / 30);
	retValue.Months = months; 
	//计算出小时数 
	var leave1 = date3 % (24 * 3600 * 1000); 
	//计算天数后剩余的毫秒数 
	var hours = Math.floor(leave1 / (3600 * 1000)); 
	retValue.Hours = hours; 
	//计算相差分钟数
	var leave2 = leave1 % (3600 * 1000); 
	//计算小时数后剩余的毫秒数 
	var minutes = Math.floor(leave2 / (60 * 1000)); 
	retValue.Minutes = minutes; 
	//计算相差秒数 
	var leave3 = leave2 % (60 * 1000); 
	//计算分钟数后剩余的毫秒数 
	var seconds = Math.round(leave3 / 1000); 
	retValue.Seconds = seconds;
	var years=0;
	if(retValue.Years>0){
		if(retValue.Years<10){
			years="0"+retValue.Years+"年";
		}else{
			years=retValue.Years+"年";
		} 
	} else{
		years="";
	}
	var months=0;
	if(retValue.Months>0){
		if(retValue.Months<10){
			months="0"+retValue.Months+"月";
		}else{
			months=retValue.Months+"月";
		} 
	} else{
		months="";
	}
	var days = 0;
	if(retValue.Days>0){
		if(retValue.Days<10){
			days="0"+retValue.Days+"天";
		}else{
			days=retValue.Days+"天";
		} 
	}else{
		days="";
	}
	var hours=0;
	if(retValue.Hours>0){
		if(retValue.Hours<10){
			hours="0"+retValue.Hours+"小时";
		}else{
			hours=retValue.Hours+"小时";
		}
	}else{
		hours="";
	}
	var minutes=0;
	if(retValue.Minutes>0){
		if(retValue.Minutes<10){
			minutes="0"+retValue.Minutes+"分钟";
		}else{
			minutes=retValue.Minutes+"分钟";
		} 
	}else{
		minutes="";
	}
	var seconds=0;
	if(retValue.Seconds>0){
		if(retValue.Seconds<10){
			seconds="0"+retValue.Seconds+"秒";
		}else{
			seconds=retValue.Seconds+"秒";
		}  
	}else{
		seconds="";
	}  
	//return years+months+days+" "+hours+minutes+seconds ;
	var returnTime=(days+" "+hours+minutes);
	return returnTime;
}

function layerOpen(openUrl,areaValue,titleValue,submitUrl,functionName,jsonArgs)
{
	$.get(openUrl,function(data)
	{
		layerCloseIndex=layer.open({
			type:1,
		    area:  areaValue,
		    //offset: ['250px', '1325px'],
		    scrollbar:false,
			title: titleValue,
			btn:["提交","取消"],
			yes:function(index, layero)
			{
				//先接触绑定，再重新绑定提交事件
				$("form:last").off('success.form.bv').on('success.form.bv', function(e) {
			        // Prevent form submission
			        e.preventDefault();
			        // Get the form instance
			        var $form = $(e.target);
			        // Get the BootstrapValidator instance
			        var bv = $form.data('bootstrapValidator');
			        var formData = new FormData($("form:last")[0]);
			        // Use Ajax to submit form data
			        $.ajax({
			            type:'POST',
			            url:submitUrl,
			            cache: false,
			            data: formData,
			            contentType: false,  
			            processData: false,
			            async: true,
			            success: function(data){
			            	try {
			                    fn = eval(functionName);
			                } catch(e) {
			                    console.log(e);
			                    console.log(fn+'方法不存在！');
			                }
			                if (typeof fn === 'function'){
			                    fn.call(this,index,data,formData,jsonArgs); 
			                } 
			            },
			            error: function(){
			            	layer.msg('操作失败，请联系管理员。', {
			  				  icon: 5,
			  				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
			  				}, function(){
			  				  //do something
			  				});
			            }
			        });
			    });
				$("form:last").submit();
			},	
			btn2:function(index, layero)
			{
			},
			content: data
		});
	});
}

//得到     年-月-日   的毫秒数
function currentDate(){
	   var date = new Date();
	   var str =date.getFullYear()+"-";
	   str +=(date.getMonth()+1)+"-";
	   str += date.getDate()+"";
	   var day=new Date(str.replace("-", "/").replace("-", "/"));
	   var millisecond=day.getTime();
	  // console.log(millisecond); 
	   return millisecond;
}

//将时间戳转换可视化时间字符
function formatDateTransform(dateTime) {    
    var date = new Date(dateTime);  
    var y = date.getFullYear();    
    var m = date.getMonth() + 1;    
    m = m < 10 ? ('0' + m) : m;    
    var d = date.getDate();    
    d = d < 10 ? ('0' + d) : d;    
    var h = date.getHours();  
    h = h < 10 ? ('0' + h) : h;  
    var minute = date.getMinutes();  
    var second = date.getSeconds();  
    minute = minute < 10 ? ('0' + minute) : minute;    
    second = second < 10 ? ('0' + second) : second;   
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;    
};


//查看手动录入的告警详情信息
function findRegisterMessageLayer(id,url,titleValue){   
    var loading = layer.load(1);
	$.get(url,{"notificationId":id},function(data) { 
		 	 layer.close(loading);
			 layer.open({
					title:titleValue||"提示",
					content: data,
					area:["600px","500px"], 
                    btn:['关闭'],
					yes: function(index, layero){
						layer.close(index);
					}
				});
   });  
}