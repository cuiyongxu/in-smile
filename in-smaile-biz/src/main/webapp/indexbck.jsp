<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello MUI</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <!--标准mui.css-->
    <link rel="stylesheet" href="/css/mui.min.css">
    <!--App自定义的css-->
    <link rel="stylesheet" type="text/css" href="/css/app.css"/>
</head>

<body>
<header class="mui-bar mui-bar-nav">
    <h1 class="mui-title"> 笑一笑</h1>
</header>
<div class="mui-content">

    <div class="mui-card">
        <div class="mui-card-header mui-card-media">
            <img src="images/logo.png"/>
            <div class="mui-media-body">
                小M
                <p>发表于 2016-06-30 15:30</p>
            </div>
        </div>
        <div class="mui-card-content">
            <img src="images/yuantiao.jpg" alt="" width="100%"/>
        </div>
        <div class="mui-card-footer">
            <a class="mui-card-link">Like</a>
            <a class="mui-card-link">Comment</a>
            <a class="mui-card-link">Read more</a>
        </div>
    </div>

    <div class="mui-card">
        <div class="mui-card-header mui-card-media">
            <img src="images/logo.png"/>
            <div class="mui-media-body">
                小M
                <p>发表于 2016-06-30 15:30</p>
            </div>
        </div>
        <div class="mui-card-content">
            <img src="images/yuantiao.jpg" alt="" width="100%"/>
        </div>
        <div class="mui-card-footer">
            <a class="mui-card-link">Like</a>
            <a class="mui-card-link">Comment</a>
            <a class="mui-card-link">Read more</a>
        </div>
    </div>
</div>
<script src="/js/mui.min.js"></script>
</body>

<script>
    mui.init();

    mui.post('http://localhost:8080/in/smaile/get', {
        nowTime: new Date().getSeconds(),
    }, function (data) {
        console.log(data)

    }, 'json');
</script>
</html>
