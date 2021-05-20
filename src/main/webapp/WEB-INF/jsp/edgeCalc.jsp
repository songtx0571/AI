<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/layui/layui.js"></script>
    <link rel="stylesheet" type="text/css" href="../js/layui/css/layui.css" />
    <script type="text/javascript" src="../js/week/edgeCalc.js"></script>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        .content{
            padding:  0px 5px;
            box-sizing: border-box;
        }
        body::-webkit-scrollbar{
            display: none;
        }
    </style>
</head>
<body>
<div class="warp">
    <div class="content">
        <table id="demo" lay-filter="test"></table>
    </div>
</div>
</body>
</html>
