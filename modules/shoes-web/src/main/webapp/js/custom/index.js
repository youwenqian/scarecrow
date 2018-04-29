var flag = false;
// init layui
layui.use('layer', function(){
    layer = layui.layer;
});
// form submit
$('#login-button').click(function (event) {
    // 取消冒泡
    event.preventDefault();
    // 设置登陆按钮单机状态
    flag = true;
    // from validate
    var userName = $("._container_ > form input[name='userName']").val();
    var password = $("._container_ > form input[name='passWord']").val();
    if(userName == "" || userName == null ) {
        $("._container_ > form input[name='userName']").focus();
        var that = $("._container_ > form input[name='userName']");
        layer.tips("请输入用户名",that);
        return false;
    } else if(password == "" || password == null) {
        $("._container_ > form input[name='passWord']").focus();
        var that = $("._container_ > form input[name='passWord']");
        layer.tips("请输入密码",that);
        return false;
    } else {
        $("._container_ > form input[name='userName']").blur();
        $("._container_ > form input[name='passWord']").blur();
    }
    // form btn disabled
    $("#login-button").attr({"disabled":"disabled"});
    // remove class shake_effect
    $("._container_ > form").removeClass('shake_effect');
    // form data serialize
    var formData = $("._container_ > form").serialize();
    // form ajax
    $.ajax({
        type:'POST',
        url:"index/login",
        data: formData,
        dataType:'json',
        success: function(data){
            if(data.flag == true){
                $('form').fadeOut(500);
                $('.wrapper').addClass('form-success');
                // success
                $('.container h1 > span').css('opacity','1');
                $('.container h1 > span i').css('animation-play-state','running');
                window.location.href = "index/main";
            }
        },
        error: function(data){
            // 401：服务端认证失败
            if(401 == data.status){
                // add class shake_effect
                $("._container_ > form").addClass('shake_effect');
                // layer msg
                layer.msg('用户名或密码错误，请重试。', {
                    offset: ['58%'],
                    icon: 5,
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                }, function(){
                    //do something
                    $("#login-button").removeAttr("disabled");
                });
            }
        }
    });
    // 还原登陆按钮状态
    flag = false;
});
// 绑定键盘按下事件
$(document).keypress(function(e) {
    // 回车键事件
    if(e.which == 13) {
        if(!flag){
            $('#login-button').click();
        }
    }
});