<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/layui/layui.js"></script>
    <link rel="stylesheet" type="text/css" href="../js/layui/css/layui.css" />
    <script type="text/javascript" src="../js/week/circulatingPump.js?version=1.0"></script>
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
        .configure{
            width: 370px;
            margin: 10px auto;
            display: none;
        }
    </style>
</head>
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
                <a class="layui-btn layui-btn-xs" lay-event="edit">配置</a>
            </script>
        </div>
        <div class="configure">
            <form class="layui-form" action="">
                <input type="hidden" id="id1">
                <input type="hidden" id="addressListHidden1">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">设备名称</label>
                        <div class="layui-input-inline">
                            <select name="modules" lay-verify="required" lay-filter="addressList1" lay-search="" id="addressList1" disabled>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">测点</label>
                        <input type="text" id="measuringPoint" style="width: 193px;height: 38px;border: 1px solid #e6e6e6;" disabled>
                    </div>
                </div>
                <input type="hidden" id="equSysListHidden">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">系统名称</label>
                        <div class="layui-input-inline">
                            <select name="modules" lay-verify="required" lay-filter="equSysList" lay-search="" id="equSysList">
                            </select>
                        </div>
                    </div>
                </div>
                <input type="hidden" id="equListHidden">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">设备名称</label>
                        <div class="layui-input-inline">
                            <select name="modules" lay-verify="required" lay-filter="equList" lay-search="" id="equList">
                            </select>
                        </div>
                    </div>
                </div>
                <input type="hidden" id="unitListHidden">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">单位</label>
                        <div class="layui-input-inline">
                            <select name="modules" lay-verify="required" lay-filter="unitList" lay-search="" id="unitList">
                            </select>
                        </div>
                    </div>
                </div>
                <input type="hidden" id="unitPointListHidden">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">测点类型</label>
                        <div class="layui-input-inline">
                            <select name="modules" lay-verify="required" lay-filter="unitPointList" lay-search="" id="unitPointList">
                            </select>
                        </div>
                    </div>
                </div>
                <div style="text-align: center">
                    <button type="button" class="layui-btn" onclick="updConfigure()">确定</button>&nbsp;&nbsp;<button type="button" class="layui-btn" onclick="cancel()">取消</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
