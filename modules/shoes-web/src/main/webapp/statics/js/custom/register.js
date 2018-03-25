var laydate,form,upload,element,layer;
$(document).ready(function () {
    layui.use(['laydate', 'form' , 'upload', 'element', 'layer'], function(){
        laydate = layui.laydate;
        form = layui.form;
        upload = layui.upload; //上传
        element = layui.element; //元素操作
        layer = layui.layer;

        upload.render({
            elem: '#weixinPicture'
            , url: 'images'
            , method: 'post'
            , field: 'file'
            , accept: 'images'
            , done: function (res, index, upload) {
                //获取当前触发上传的元素，一般用于 elem 绑定 class 的情况，注意：此乃 layui 2.1.0 新增
                var item = this.item;
                console.log(res);
                if(res.code=="true"){
                    $("input[name='imageAddress']").val(res.message);
                }
                console.log("上传完成");
            }
        });
//将日期直接嵌套在指定容器中
        laydate.render({
            elem: '#date1'
        });
        $.ajax({
            type: "POST",
            url: "init",
            dataType:"json",
            contentType: "application/json",
            success: function (data) {
                for (var i = 0; i < data.stockList.length; i++) {
                    $("#stockid").append('<option value="' + data.stockList[i].id + '">' + data.stockList[i].name + '</option>');
                }
                form.render('select');
            }
        });
    });

});

var DataDeal = {
    //将从form中通过$('#refer').serialize()获取的值转成json
    formToJson: function (data) {
        data=data.replace(/&/g,"\",\"");
        data=data.replace(/=/g,"\":\"");
        data="{\""+data+"\"}";
        return data;
    }
};
function registerBase() {
    var dataCollect = $('#userForm').serialize();
    dataCollect = decodeURIComponent(dataCollect,true);//防止中文乱码
    var paramesterJson =DataDeal.formToJson(dataCollect);//转化为json
    console.log(paramesterJson);
    $.ajax({
        type: "POST",
        url: "registerUser",
        data: paramesterJson,
        dataType:"json",
        contentType: 'application/json',
        success: function (data) {
            console.log(data);
            if(data.code=="true"){
                $("#erweima").val(data.imageUrl);
                layer.open({
                    type: 1,
                    title:'tongtong',
                    content: 'tongtong'//$('#id') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                });
                // $("input[name='isPayment']").val(data.imageUrl);
            }
        }
    });
};
