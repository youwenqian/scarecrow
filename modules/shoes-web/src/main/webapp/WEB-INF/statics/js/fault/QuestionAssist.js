$(function(){ 
	 
	//选项卡 
		$(".tab_content").hide();  
		$("ul.tabs li:first").addClass("active").show();   
		$(".tab_content:first").show();    
		$("ul.tabs li").click(function() {    
			$("ul.tabs li").removeClass("active");   
			$(this).addClass("active");  
			$(".tab_content").hide();  
			var activeTab = $(this).find("a").attr("href");  
			$(activeTab).fadeIn();     
			return false;    
		});  
});  		
    //tab显示背景颜色
	 function backcolor1(){
	   $(".act1").css("background-color","rgb(87,89,114)");
	   $(".act").css("border-radius","0px"); 
	   $(".act1").css("border-radius","7px");
	   $(".act").css("background-color","rgb(108,109,141)");   
	 }
	 function backcolor2(){
	  $(".act").css("background-color","rgb(87,89,114)"); 
	  $(".act1").css("border-radius","0px"); 
	  $(".act").css("border-radius","7px");
	  $(".act1").css("background-color","rgb(108,109,141)"); 
	 }
	//tab选项卡切换
    function tab1(pid){   
	  var tabs=["tab1","tab2" ];
	  for(var i=0;i<2;i++){
		   if(tabs[i]==pid){
			document.getElementById(tabs[i]).style.display="block";  
			backcolor1();
		  }else{
			document.getElementById(tabs[i]).style.display="none"; 
			backcolor2();		
	      }
	  }
	 } 