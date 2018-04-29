<%--
  Created by IntelliJ IDEA.
  User: youwenqian
  Date: 18-3-6
  Time: 下午11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>shoes circle</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/default.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/common.css">
    <!--[if IE]>
    <script src="<%=basePath%>js/html5shiv/html5shiv.min.js"></script>
    <![endif]-->
    <!-- layui -->
    <link rel="stylesheet" href="<%=basePath%>js/layui/css/layui.css"  media="all">
    <script src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>js/layui/layui.js" charset="utf-8"></script>
</head>
<body>
<div class="htmleaf-container">
    <div class="wrapper">
        <div class="layui-row bodyContent">
            <div id = "userName" style="width: 50%; height: 40px; text-align: left; float: left"><span>欢迎：${userName}</span></div>
            <div style="margin-right: 10px; height: 40px; text-align: right;"><a href="">修改密码</a>  <a href="">退出登录</a></div>
        </div>
        <%--<div class="layui-row">
            <div class="bodyContent">
                <div class="layui-col-xs3">
                    <div class="layui-collapse" style="height: 300px;" lay-accordion="">
                        <div class="layui-colla-item">
                            <div class="">
                                <img src="<%=basePath%>css/iconfont/notification.png" style="float:left;width: 20px;height: 20px; margin-left: 10px; text-align: left;">
                                <h2 style="float:left;height: 20px; width: 180px; font-size: 14px; padding-left: 5px; text-align: left;">通知公告</h2>
                                <a href="http://kgzg.caacmooc.org:80/cms/newsIndex/30/1.shtml" style="height : 20px;width:100px;text-align: right">更多>></a>
                                <div class="layui-colla-content layui-show">
                                    <ul>
                                        <li>2018-04-01 10:59:00 aaa发起求购Nick白色42码帆布鞋一双</li>
                                        <li>2018-04-02 10:59:00 bbb发起求购Nick白色42码帆布鞋一双</li>
                                        <li>2018-04-03 10:59:00 ccc发起求购Nick白色42码帆布鞋一双</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-col-xs9" style="padding-left: 10px;">
                    <div class="layui-carousel" id="test10">
                        <div carousel-item="">
                            <div><img src="<%=basePath%>image/111.jpg" style = "width: 100%; height: 100%;"></div>
                            <div><img src="<%=basePath%>image/222.jpg" style = "width: 100%; height: 100%;"></div>
                            <div><img src="<%=basePath%>image/333.jpg" style = "width: 100%; height: 100%;"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>--%>
        <div class="layui-row bodyContent" style="padding-top: 10px;">
            <ul class="layui-nav layui-bg-green">
                <li class="layui-nav-item"><a href="<%=basePath%>merchant/mechantMain?path=goods" target="_blank">商品管理</a></li>
                <li class="layui-nav-item layui-this"><a href="<%=basePath%>merchant/mechantMain?path=buyGoods" target="_blank">调货查找</a></li>
                <li class="layui-nav-item"><a href="<%=basePath%>merchant/mechantMain?path=sellGoods" target="_blank">出库管理</a></li>
                <!--<li class="layui-nav-item"><a href="<%=basePath%>merchant/mechantMain?path=score" target="_blank">商家打分</a></li>-->
                <li class="layui-nav-item"><a href="<%=basePath%>merchant/mechantMain?path=analaysisMain" target="_blank">智能分析</a></li>
            </ul>
        </div>
        <div class="layui-row bodyContent" style="margin-top: 10px;">
                <div class="layui-carousel" id="test10">
                    <div carousel-item="">
                        <div><img src="<%=basePath%>image/111.jpg" style = "width: 100%; height: 100%;"></div>
                        <div><img src="<%=basePath%>image/222.jpg" style = "width: 100%; height: 100%;"></div>
                        <div><img src="<%=basePath%>image/333.jpg" style = "width: 100%; height: 100%;"></div>
                    </div>
                </div>
        </div>
        </div>
        <div class="layui-row bodyContent" style="padding-top: 10px;">
            <iframe name="content" style="width:100%;height:-webkit-fill-available;" >
            </iframe>
        </div>
    </div>
</div>

<script>
    layui.use(['carousel', 'form'], function(){
        var carousel = layui.carousel
            ,form = layui.form;

        //图片轮播
        carousel.render({
            elem: '#test10'
            ,width: '100%'
            ,height: '300px'
            ,interval: 5000
        });

    });
</script>
</body>
</html>
