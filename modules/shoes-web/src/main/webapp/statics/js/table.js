$.fn.smartFloatTableHeader = function (container, removebody) {
	console.log(container);
    if (!container) container = window;
    console.log(container);
    if (typeof (container) == 'string') {
        container = document.getElementById(container);
    }
    function  temp(element,newTable) {               //监控容器及父元素滚动事件
         
        }
    var smartFloat = function (element) {
        var newTable = element.clone();                 //复制一个新的表格出来
        if (removebody) {
            newTable.find('tbody').remove();
        } else {
            newTable.find('tbody').hide();                      //表体隐藏
        }
        $(newTable).insertBefore(element).hide();               //先不显示复制出来的表格
        $(element).parent().scroll(function(){
        	   var containerTop = container == window ? 0 : $(container).offset().top,  //容器顶部位置
                       elementTop = element.offset().top,          //最少滚动多少才到隐藏位置
                       scrolls = $(this).scrollTop(),              //滚动高度
                       emementHeight = $(element).outerHeight(),   //元素总高度，考虑可能是动态的，每次滚动时判断
                       offset = $(element).offset();               //源控件位置
                   //源表头进入隐藏区域并且表尾未进入隐藏区域时显示复制内容
                   if (containerTop > elementTop && containerTop < elementTop + emementHeight) {
                       newTable.css({ width: element.outerWidth() }).show();    //设置复制出来的表格与源表格同宽度（某些控件生成的表格未设置宽度）
                       newTable.css({
                           position: "fixed",
                           top: containerTop,
                           left: offset.left
                       });
                   } else {
                       newTable.hide();
                   }
        });
        $(window).resize(temp(element,newTable));
    };
    return $(this).each(function () {
        smartFloat($(this));
    });
};