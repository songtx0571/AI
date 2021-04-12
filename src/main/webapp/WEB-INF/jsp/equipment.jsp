<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/layui/layui.js"></script>
    <link rel="stylesheet" type="text/css" href="../js/layui/css/layui.css" />
    <script type="text/javascript" src="../js/week/equipment.js?version=1.0"></script>
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
        .addEquipmentDiv,.updEquipmentDiv{
            width: 380px;
            margin: 10px auto 0;
            display: none;
        }
        body::-webkit-scrollbar{
            display: none;
        }
    </style>
</head>
<body>
<div class="warp">
    <div class="top">
        <input type="hidden" id="proDepartListHidden">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">项目部</label>
                    <div class="layui-input-inline">
                        <select name="modules" lay-verify="required" lay-filter="proDepartList" lay-search="" id="proDepartList">
                        </select>
                    </div>
                </div>
                <div class="layui-inline addBTn" style="display: none;">
                    <button type="button" class="layui-btn" onclick="showAddEquipment()">添加</button>
                </div>
            </div>
        </form>
    </div>
    <div class="content">
        <table id="demo" lay-filter="test"></table>
        <script type="text/html" id="barDemo1">
            {{#  if(d.status== "1"){ }}
            <span>开启</span>
            {{#  } else { }}
            <span>关闭</span>
            {{#  } }}
        </script>
        <script type="text/html" id="barDemo2">
            <span>部门</span>
        </script>
        <script type="text/html" id="barDemo3">
            <a class="layui-btn layui-btn-xs" lay-event="edit" id="edit">修改</a>
        </script>
    </div>
    <div class="addEquipmentDiv">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">设备名称</label>
                    <input type="text" id="addressHidden" style="width: 193px;height: 38px;border: 1px solid #e6e6e6;">
                </div>
            </div>
            <div class="layui-form-item">
                <input type="hidden" id="statusHidden">
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-block">
                        <input type="radio" name="status" lay-filter="status" value="1" title="启用" checked="checked">
                        <input type="radio" name="status" lay-filter="status" value="0" title="禁用">
                    </div>
                </div>
            </div>
            <div style="text-align: center">
                <button type="button" class="layui-btn" onclick="addEquipment()">确定</button>&nbsp;&nbsp;<button type="button" class="layui-btn" onclick="cancel()">取消</button>
            </div>
        </form>
    </div>
    <div class="updEquipmentDiv">
        <input type="hidden" id="updIdHidden">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">设备名称</label>
                    <input type="text" id="updressHidden" style="width: 193px;height: 38px;border: 1px solid #e6e6e6;">
                </div>
            </div>
            <input type="hidden" id="updProDepartListHidden">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">项目部</label>
                    <div class="layui-input-inline">
                        <select name="modules" lay-verify="required" lay-filter="updProDepartList" lay-search="" id="updProDepartList">
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <input type="hidden" id="updStatusHidden">
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-block">
                        <input type="radio" name="updStatus" lay-filter="updStatus" value="1" title="启用">
                        <input type="radio" name="updStatus" lay-filter="updStatus" value="0" title="禁用">
                    </div>
                </div>
            </div>
            <div style="text-align: center">
                <button type="button" class="layui-btn" onclick="updEquipment()">确定</button>&nbsp;&nbsp;<button type="button" class="layui-btn" onclick="cancel()">取消</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
