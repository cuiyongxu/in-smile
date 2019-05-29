<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>销一笑</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <!--标准mui.css-->
    <link rel="stylesheet" href="${ctx}/css/mui.min.css">
    <!--App自定义的css-->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/app.css"/>
</head>

<body>

<div class="mui-content">
    <header class="mui-bar mui-bar-nav">
        <h1 class="mui-title"> 销一笑</h1>
    </header>
    <div id="pullrefresh" class="mui-content mui-scroll-wrapper">
        <div id='result'></div>
        <script id="t:_1234-abcd-1" type="text/html">

            (%if(dataType=='text') { %)
            (%for(var i=0;i
            <list.length;i++){%)
            <div class="mui-card">
                <div class="mui-card-header mui-card-media">
                    <img src="${ctx}/images/logo.gif"/>
                    <div class="mui-media-body">
                        (%=list[i].createor%)
                        <p>段子发表于 (%=list[i].createTime%)</p>
                    </div>
                </div>
                <div class="mui-card-content">
                    <div class="mui-card-content-inner">
                        (%=list[i].text%)
                    </div>
                </div>
                <div class="mui-card-footer">
                    <a class="mui-card-link">Like</a>
                    <a class="mui-card-link">Comment</a>
                    <a class="mui-card-link">Read more</a>
                </div>
            </div>
            (%}%)
            (%}else if(dataType='image'){%)
            (%for(var i=0;i
            <list.length;i++){%)
            <div class="mui-card">
                <div class="mui-card-header mui-card-media">
                    <img src="${ctx}/images/logo.gif"/>
                    <div class="mui-media-body">
                        (%=list[i].createor%)
                        <p>图文发表于 (%=list[i].createTime%)</p>
                    </div>
                </div>
                <div class="mui-card-content">
                    <div class="mui-card-content-inner">
                        (%:=list[i].text%)
                    </div>
                </div>
                <div class="mui-card-footer">
                    <a class="mui-card-link">Like</a>
                    <a class="mui-card-link">Comment</a>
                    <a class="mui-card-link">Read more</a>
                </div>
            </div>
            (%}%)
            (%}%)


        </script>
    </div>
</div>
<script src="${ctx}/js/mui.min.js"></script>
<script src="${ctx}/js/baiduTemplate.js"></script>
</body>

<script>

    mui.init({
        pullRefresh: {
            container: '#pullrefresh',
            down: {
                callback: function () {
                    setTimeout(function () {
                        mui.post('http://localost:8080/in/smaile/get', {
                            nowTime: new Date().getSeconds(),
                        }, function (data) {
                            console.log(data);
                            var html = bt('t:_1234-abcd-1', data);
                            document.getElementById('result').innerHTML = html;
                        }, 'json');
                        mui('#pullrefresh').pullRefresh().endPulldownToRefresh(); //refresh completed
                    }, 1500);
                }
            }
        }
    });

    var bt = baidu.template;


    mui.post('http://localost:8080/in/smaile/get', {
        nowTime: new Date().getSeconds(),
    }, function (data) {
        console.log(data);
        var html = bt('t:_1234-abcd-1', data);
        document.getElementById('result').innerHTML = html;
    }, 'json');


    //运动事件监听
    if (window.DeviceMotionEvent) {
        window.addEventListener('devicemotion', deviceMotionHandler, false);
    }

    //获取加速度信息
    //通过监听上一步获取到的x, y, z 值在一定时间范围内的变化率，进行设备是否有进行晃动的判断。
    //而为了防止正常移动的误判，需要给该变化率设置一个合适的临界值。
    var SHAKE_THRESHOLD = 4000;
    var last_update = 0;
    var x, y, z, last_x = 0, last_y = 0, last_z = 0;
    function deviceMotionHandler(eventData) {
        var acceleration = eventData.accelerationIncludingGravity;
        var curTime = new Date().getTime();
        if ((curTime - last_update) > 10) {
            var diffTime = curTime - last_update;
            last_update = curTime;
            x = acceleration.x;
            y = acceleration.y;
            z = acceleration.z;
            var speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
            if (speed > SHAKE_THRESHOLD) {

                mui.post('http://localost:8080/in/smaile/get', {
                    nowTime: new Date().getSeconds(),
                }, function (data) {
                    var html = bt('t:_1234-abcd-1', data);
                    document.getElementById('result').innerHTML = html;
                }, 'json');

            }
            last_x = x;
            last_y = y;
            last_z = z;
        }
    }


</script>
</html>
