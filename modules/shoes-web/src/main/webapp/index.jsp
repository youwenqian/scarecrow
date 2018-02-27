<%--
  Created by IntelliJ IDEA.
  User: wangyucheng
  Date: 2018/2/11
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <link rel="stylesheet" href="../js/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="../js/bootstrap-validator/dist/css/bootstrapValidator.css"/>

    <script type="text/javascript" src="../js/jquery/jquery-1.11.0.min.js"></script>
    <!-- <script type="text/javascript" src="../js/jquery/slide.js"></script> -->
    <script type="text/javascript" src="../js/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="..js/bootstrap-validator/dist/js/bootstrapValidator.js"></script>
  </head>
  <body>
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-lg-offset-2">
        <div class="page-header">
          <h2>Using Ajax to submit data</h2>
        </div>

        <form id="defaultForm" method="post" class="form-horizontal" action="ajaxSubmit.php">
          <div class="form-group">
            <label class="col-lg-3 control-label">用户名</label>
            <div class="col-lg-5">
              <input type="text" class="form-control" name="username" />
            </div>
          </div>

          <div class="form-group">
            <label class="col-lg-3 control-label">Email address</label>
            <div class="col-lg-5">
              <input type="text" class="form-control" name="email" />
            </div>
          </div>

          <div class="form-group">
            <label class="col-lg-3 control-label">密码</label>
            <div class="col-lg-5">
              <input type="password" class="form-control" name="password" />
            </div>
          </div>

          <div class="form-group">
            <div class="col-lg-3 col-lg-offset-9">
              <span class="active">忘记密码</span>
            </div>
          </div>

          <div class="form-group">
            <div class="col-lg-3 col-lg-offset-3">
              <button type="submit" class="btn btn-primary">登录</button>
            </div>
            <div class="col-lg-3 col-lg-offset-9">
              <button type="button" class="btn btn-primary">注册</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  <script type="text/javascript" src="../js/index.js"></script>
  </body>
</html>
