(function ($) {
    "use strict";

    $.fn.treegridData = function (options, param) {
        //如果是调用方法
        if (typeof options == 'string') {
            return $.fn.treegridData.methods[options](this, param);
        }
        
        //如果是初始化组件
        options = $.extend({}, $.fn.treegridData.defaults, options || {});
        var target = $(this);
//        debugger;
        //得到根节点
        target.getRootNodes = function (data) {
            var result = [];
            $.each(data, function (index, item) {
                if (!item[options.parentColumn]) {
                    result.push(item);
                }
            });
            return result;
        };
        var j = 0;
        //递归获取子节点并且设置子节点
        target.getChildNodes = function (data, parentNode, parentIndex, tbody) {
            $.each(data, function (i, item) {
                if (item[options.parentColumn] == parentNode[options.id]) {
                    var tr = $('<tr></tr>');
                    var nowParentIndex = (parentIndex + (j++) + 1);
                    tr.addClass('treegrid-' + nowParentIndex);
                    tr.addClass('treegrid-parent-' + parentIndex);
                    $.each(options.columns, function (index, column) {
                        
                        var td = '';
                		if(typeof(item[column.field]) != "undefined" && 2 == index){
                			var field_v = item[column.field];
                			if(0 < field_v.length){
	                			td = $("<td></td>");
	                			$(field_v.split(',')).each(function(i, v){
//	                				console.log(v);
//	                				var code = v.split('_')[1];
	                				var code = v.split('_')[0] + "_" + v.split('_')[1] + "_" + v.split('_')[3] + "_" + v.split('_')[4];
                    				// console.log(code);
//	                				console.log(v.split('_')[5]);
	                				var input_on = eval("(" + v.split('_')[5] + ")");
//	                				console.log(input_on);
	                				/*
	                				switch (i) {
									case 0:
										code += "_check"; break;
									case 1:
										code += "_insert"; break;
									case 2:
										code += "_update"; break;
									case 3:
										code += "_delete"; break;
									}
									*/
	                				var codeName = v.split('_')[2];
                    				code = code + "_" + codeName;
	                				td.append($("<div class='treegrid-checkbox layui-unselect layui-form-checkbox " + (input_on ? "layui-form-checked" : "") + "' lay-skin=''><input type='hidden' name='rolePermis' value='"+code+"'><span>" + codeName + "</span><i class='layui-icon'></i></div>"));
	                			});
                			}
                		}else{
                			td = $("<td></td>");
                			td.text(item[column.field]);
                		}
                        
                		tr.append(td);
                    });
                    tbody.append(tr);
                    target.getChildNodes(data, item, nowParentIndex, tbody);
                }
            });
        };
        target.addClass('table');
        target.addClass('treegrid');
        if (options.striped) {
            target.addClass('table-striped');
        }
        if (options.bordered) {
            target.addClass('table-bordered');
        }
        if (options.url) {
            $.ajax({
                type: options.type,
                url: options.url,
                data: options.ajaxParams,
                dataType: "JSON",
                success: function (data, textStatus, jqXHR) {
//                    debugger;
                    //构造表头
                    var thr = $('<tr></tr>');
                    $.each(options.columns, function (i, item) {
                        var th = '';
                        if(0 == i){
                        	th = $('<th class="col-lg-2" style="padding:10px;"></th>');
                        }else if(1 == i){
                        	th = $('<th class="col-lg-3" style="padding:10px;"></th>');
                        }else{
                        	th = $('<th class="col-lg-7" style="padding:10px;"></th>');
                        }
                        th.text(item.title);
                        thr.append(th);
                    });
                    var thead = $('<thead></thead>');
                    thead.append(thr);
                    target.append(thead);

                    //构造表体
                    var tbody = $('<tbody></tbody>');
                    var rootNode = target.getRootNodes(data);
                    // 构造默认
                    $.each(rootNode, function (i, item) {
                        var tr = $('<tr></tr>');
                        tr.addClass('treegrid-' + (j + i));
                        $.each(options.columns, function (index, column) {
                            var td = $('<td></td>');
                            td.text(item[column.field]);
                            tr.append(td);
                        });
                        tbody.append(tr);
                        target.getChildNodes(data, item, (j + i), tbody);
                    });
                    
                    // TODO 7.20 防止因为多重,下拉异常 
                    $.each(rootNode, function (i, item) {
                    	// 定义异常状态
                    	var tr = $('<tr class="c_tr_td"></tr>');
                    	tr.addClass('treegrid-' + (j + i));
                    	$.each(options.columns, function (index, column) {
                    		var td = '';
                    		if(typeof(item[column.field]) != "undefined" && 2 == index){
                    			var field_v = item[column.field];
                    			if(0 < field_v.length){
	                    			td = $("<td></td>");
	                    			$(field_v.split(',')).each(function(i, v){
//		                				console.log(v);
//	                    				var code = v.split('_')[1];
	                    				 var code = v.split('_')[0] + "_" + v.split('_')[1] + "_" + v.split('_')[3] + "_" + v.split('_')[4];

//		                				console.log(v.split('_')[5]);
		                				var input_on = eval("(" + v.split('_')[5] + ")");
	                    				
	                    				/*
	                    				switch (i) {
	    								case 0:
	    									code += "_check"; break;
	    								case 1:
	    									code += "_insert"; break;
	    								case 2:
	    									code += "_update"; break;
	    								case 3:
	    									code += "_delete"; break;
	    								}
	    								*/
	                    				var codeName = v.split('_')[2];
	                    				code = code + "_" + codeName;
	                    				td.append($("<div class='treegrid-checkbox layui-unselect layui-form-checkbox " + (input_on ? "layui-form-checked" : "") + "' lay-skin=''><input type='hidden' name='rolePermis' value='"+code+"'><span>" + codeName + "</span><i class='layui-icon'></i></div>"));		
	                    			});
                    			}
                    		}else{
                    			td = $("<td></td>");
                    			td.text(item[column.field]);
                    		}
                    		tr.append(td);
                    	});
                    	tbody.append(tr);
                    	target.getChildNodes(data, item, (j + i), tbody);
                    });
                    target.append(tbody);
                    
                    target.treegrid({
                        expanderExpandedClass: options.expanderExpandedClass,
                        expanderCollapsedClass: options.expanderCollapsedClass
                    });
                    
                    if (!options.expandAll) {
                        target.treegrid('collapseAll');
                    }
                    
                    // TODO 7.20 解决多重下拉异常问题,此处不是最优解决方案,如有后续业务需进行重构 
                    var c_i = 0;
                    target.treegrid('getAllNodes').each(function(i, v){
                    	if(!$(v).hasClass('c_tr_td')){
                    		if(0 == c_i){
                    			v.remove();
                    		}
                    	}else if($(v).hasClass('c_tr_td')){
                    		c_i++;
                    	}
                    });
                    
                }
            });
        }
        else {
            //也可以通过defaults里面的data属性通过传递一个数据集合进来对组件进行初始化....有兴趣可以自己实现，思路和上述类似
        }
        return target;
    };

    $.fn.treegridData.methods = {
        getAllNodes: function (target, data) {
            return target.treegrid('getAllNodes');
        },
        //组件的其他方法也可以进行类似封装........
    };

    $.fn.treegridData.defaults = {
        id: 'id',
        parentColumn: 'PARENTID',
        data: [],    //构造table的数据集合
        type: "GET", //请求数据的ajax类型
        url: null,   //请求数据的ajax的url
        ajaxParams: {}, //请求数据的ajax的data属性
        expandColumn: null,//在哪一列上面显示展开按钮
        expandAll: true,  //是否全部展开
        striped: false,   //是否各行渐变色
        bordered: false,  //是否显示边框
        columns: [],
        expanderExpandedClass: 'glyphicon glyphicon-chevron-down',//展开的按钮的图标
        expanderCollapsedClass: 'glyphicon glyphicon-chevron-right'//缩起的按钮的图标
        
    };
})(jQuery);