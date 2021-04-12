<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>浩维运行引导管理平台</title>
</head>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/layui/layui.js"></script>
<script type="text/javascript" src="../js/week/alert.js"></script>
<link rel="stylesheet" type="text/css" href="../js/layui/css/layui.css" />
<script type="text/javascript" src="../js/week/dispose.js"></script>
<style>
    *{
        margin: 0;
        padding: 0;
    }
    .top{
        padding-top: 10px;
        box-sizing: border-box;
    }
    .content{
        padding:  0px 5px;
        box-sizing: border-box;
    }
    body::-webkit-scrollbar{
        display: none;
    }
</style>
<body>
<div class="warp">
    <div class="top">
        <input type="hidden" id="departListListHidden">
        <input type="hidden" id="addressListHidden">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">设备名称</label>
                    <div class="layui-input-inline" style="margin-right: 0px;">
                        <select name="modules" lay-verify="required" lay-filter="departListList" lay-search="" id="departListList">
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select name="modules" lay-verify="required" lay-filter="addressList" lay-search="" id="addressList">
                        </select>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="content">
        <table id="demo" lay-filter="test"></table>
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        </script>
    </div>
</div>
</body>
</html>
