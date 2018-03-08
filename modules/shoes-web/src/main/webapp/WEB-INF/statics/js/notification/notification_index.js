var basePath;
$(function() {
	basePath = $("#basePath").val();
	submitSearchForm();
});

// 切换高级和普通查询
function toogleSearchMore() {
	$(".adv-search-view").toggle();
	$(".adv-search-input").val("");
	$("#isMoreSearch").val($("#isMoreSearch").val() =="1"? "0" : "1");
	$("#moreButton").toggleClass("glyphicon-menu-up glyphicon-menu-down");
}

// 提交查询
function submitSearchForm() {
	$("#pageIndex").val(1);
	refreshPageList();
}

// 刷新当前页数据
function refreshPageList() {
	$("#div_load").load(basePath + "notification/notification_list",$("#searchNotificationForm").serialize());
}

// 翻页
function pageTurning(pageIndex,pageSize) {
	$("#pageIndex").val(pageIndex);
	if(pageSize) {
		$("#pageSize").val(pageSize);
	}
	refreshPageList();
}

// 编辑和修改方法
function saveNotification(title, url) {
	$.get(basePath+url,function(data) {
		layer.open({
			type:1,
		    area:  ['600px', '600px'],
			title: title,
			btn:["提交","重置","取消"],
			yes:function(index, layero) {
				formAjaxSubmit(index);
			},	
			btn2:function(index, layero) {
				$("#saveNotificationForm").get(0).reset();
				return false;
			},
			btn3:function(index,layero) {
				
			},
			content: data
		});
		$("div.layui-layer-content").addClass("fixrow");
	});
}

// 显示详情
function showNotificationDetail(notificationId) {
	$.get(basePath+'notification/notification_detail?notificationId=' + notificationId, function(data) {
		layer.open({
			type:1,
		    area:  ['700px', '800px'],
			title: '详情',
			btn:["关闭"],
			yes:function(index, layero) {
				layer.close(index);
			},
			content: data
		});
	});
	$("div.layui-layer-content").addClass("fixrow");
}

//删除单条记录
function deleteNotification(notificationId) {
	layer.open({
		title:"提示",
		content: "你确定要删除吗？",
		btn: ['确认', '取消'],
		yes: function(index, layero){
			$.ajax({
				type:"post",
				url:basePath + "notification/delete",
				data: {
					ids:notificationId,
				},
				success:function(data) {
					var success = data.success;
					var message = '操作成功。'
					var iconValue = 6;

					layer.msg(message, {
					  icon: 6,
					  time: 2000 
					});
					layer.close(index);
					refreshPageList();
				},
				error:function() {
					layer.msg('操作失败，请联系管理员。', {
					  icon: 5,
					  time: 2000
					});   
				}
				
			});
		},
		btn2: function(index, layero){
			layer.close(index);
		}
	});
}

// 复核按钮功能
function notificationComplex(notificationId) {
	var loading = layer.load();
	$.ajax({
		type:"post",
		url:basePath + "notification/notification_complex",
		data:{notificationId:notificationId},
		async:true,
		success:function(data) { 
			layer.close(loading);
			layer.open({
				type:1,
				title:"复核",
				content: data,
				area:["800px","800px"],
                btn:['关闭'],
				yes: function(index, layero){
					layer.close(index);
				}
			});
		}
	});
}

// 导出数据
function exportNotification() {
	$("#searchNotificationForm").attr("action", basePath + "notification/export");
	$("#searchNotificationForm").attr("target", "exprotFrame");
	$("#searchNotificationForm").submit();
}

// 删除多条记录
function deleteMoreNotification() {
	var $chkItems = $("input[name=checkItem]:checked"), 
		len = $chkItems.size(),
		ids = [];
	if(len <= 0) {
		layer.msg('请选择要删除的记录！', {
		  icon: 0,
		  time: 2000
		});
		return;
	}
	$chkItems.each(function(index, item) {
		ids.push($(item).val());
	});
	layer.open({
		title:"提示",
		content: "你确定要删除选中的记录吗？",
		btn: ['确认', '取消'],
		yes: function(index, layero){
			$.ajax({
				type:"post",
				url:basePath + "notification/delete",
				data: {
					ids:ids.join(","),
				},
				success:function(data) {
					var success = data.success;
					var message = '操作成功。'
					var iconValue = 6;

					layer.msg(message, {
					  icon: 6,
					  time: 2000 
					});
					layer.close(index);
					refreshPageList();
				},
				error:function() {
					layer.msg('操作失败，请联系管理员。', {
					  icon: 5,
					  time: 2000
					});   
				}
				
			});
		},
		btn2: function(index, layero){
			layer.close(index);
		}
	});
}

// ajax提交表单
function formAjaxSubmit(index) {
	var $form = $("#saveNotificationForm");
    $.ajax({
        type:'POST',
        url:$form.attr('action'),
        data: $form.serialize(),
        async: true,
        success: function(data){
        	if(data.success == "true") {
        		layer.msg('操作成功。', {
				  icon: 6,
				  time: 2000
				});
        		layer.close(index);
            	refreshPageList();
    		} else {
        		layer.msg('操作失败，请联系管理员。', {
				  icon: 5,
				  time: 2000
				});   
    		}
        },
        error: function() {
        	layer.msg('操作失败，请联系管理员。', {
			  icon: 5,
			  time: 2000
			});    
        }
    });
}