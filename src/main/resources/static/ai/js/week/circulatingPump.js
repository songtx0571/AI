var path = "";
$(function () {
    getAddress();
});
//获取设备名称下拉框
function getAddress() {
    layui.use(['form'], function() {
        var form = layui.form;
        $.ajax({
            type: "GET",
            url: path + "/ai/dataCon/getCompanyMap",
            dataType: "json",
            success: function (data) {
                $("#departListList").empty();
                var option = "<option value='0' >请选择部门名称</option>";
                for (var i = 0; i < data.length; i++) {
                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                }
                $('#departListList').append(option);
                form.render();//菜单渲染 把内容加载进去
            }
        });
        form.on('select(departListList)', function (data) {
            $("#departListListHidden").val(data.value);//得到被选中的值
            showEquUnityMap($("#departListListHidden").val());
            $.ajax({
                type: "GET",
                url: path + "/ai/dataCon/getAddressList?departmentId="+$("#departListListHidden").val(),
                dataType: "json",
                success: function (data) {
                    $("#addressList").empty();
                    $("#addressList1").empty();
                    var option = "<option value='0' >请选择设备名称</option>";
                    for (var i = 0; i < data.length; i++) {
                        option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                    }
                    $('#addressList').append(option);
                    $('#addressList1').append(option);
                    form.render();//菜单渲染 把内容加载进去
                }
            });
            form.on('select(addressList)', function (data) {
                $("#addressListHidden").val(data.value);//得到被选中的值
                showCirculatingPump();
            });
        });
    })
}
//显示表格
function showCirculatingPump() {
    var address = $("#addressListHidden").val();
    var top = $(".top").css("height");
    var win = $(window).height();
    var tp = top.indexOf("p");
    var topHeight = top.substring(0,tp);
    var height = win-topHeight-35;
    layui.use('table', function() {
        var table = layui.table;
        table.render({
            elem: '#demo'
            , height: height
            , toolbar: true
            , url: path + "/ai/dataCon/getDataConfigurationList?address=" + address //数据接口
            , cols: [[ //表头
                {field: 'id', title: '编号', width: 80, hide: true}
                , {field: 'addressName', title: '设备名称', sort: true, align: 'center'}
                , {field: 'measuringPoint', title: '测点', sort: true, align: 'center'}
                , {field: 'equipment', title: '设备', sort: true, align: 'center'}
                , {field: 'measuringType', title: '测点类型', sort: true, align: 'center'}
                , {field: 'unit', title: '单位', sort: true, align: 'center'}
                , {fixed: 'right', title: '操作', width: 80, align: 'center', toolbar: '#barDemo'}
            ]]
            , parseData: function (res) {
            }
            , done: function (res, curr, count) {
            }
        });
        table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            $("#id1").val(data.id);
           var equipment = data.equipment;
           layui.use('form', function(){
               var form = layui.form;
               $("#addressList1").val(data.address);//地址
               $("#measuringPoint").val(data.measuringPoint);//测点
               $("#unitList").val(data.unit);//单位
               $("#unitPointList").val(data.measuringType);//测点类型
               $("#addressListHidden1").val(data.address);
               $("#unitListHidden").val(data.unit);
               $("#unitPointListHidden").val(data.measuringType);//测点类型
               if (equipment) {
                   var dou = equipment.indexOf(",");
                   var system = equipment.substring(0,dou);
                   var equ = equipment.substring(dou+1);
                   $("#equSysList").val(system);//系统
                   $("#equList").val(equ);//设备
                   $("#equSysListHidden").val(system);
                   $("#equListHidden").val(equ);
               }else{
                   $("#equSysList").val("");//系统
                   $("#equList").val("");//设备
               }
               form.render('select');
               form.render(); //更新全部
           });

            if (obj.event === 'edit') {// 修改权限
                layui.use('layer', function () { //独立版的layer无需执行这一句
                    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
                    layer.open({
                        type: 1
                        , id: 'configure' //防止重复弹出
                        , content: $(".configure")
                        , btnAlign: 'c' //按钮居中
                        , shade: 0.1 //不显示遮罩
                        , area: ['100%', '100%']
                        , yes: function () {
                        }
                    });
                });
            }
        });
    })
}
//显示系统，设备，单位，测点
function showEquUnityMap(departmentId) {
    layui.use(['form'], function() {
        var form = layui.form;
        $.ajax({
            type: "GET",
            url: path + "/ai/dataCon/getEquMap",
            data: {type: 1,departmentId:departmentId},
            dataType: "json",
            success: function (data) {
                $("#equSysList").empty();
                var option = "<option value='0' >请选择系统名称</option>";
                for (var i = 0; i < data.length; i++) {
                    option += "<option value='" + data[i].text + "'>" + data[i].text + "</option>";
                }
                $('#equSysList').append(option);
                form.render();//菜单渲染 把内容加载进去
            }
        });
        form.on('select(equSysList)', function (data) {
            $("#equSysListHidden").val(data.value);//得到被选中的值
        });
        $.ajax({
            type: "GET",
            url: path + "/ai/dataCon/getEquMap",
            data: {type: 2,departmentId:departmentId},
            dataType: "json",
            success: function (data) {
                $("#equList").empty();
                var option = "<option value='0' >请选择设备名称</option>";
                for (var i = 0; i < data.length; i++) {
                    option += "<option value='" + data[i].text + "'>" + data[i].text + "</option>";
                }
                $('#equList').append(option);
                form.render();//菜单渲染 把内容加载进去
            }
        });
        form.on('select(equList)', function (data) {
            $("#equListHidden").val(data.value);//得到被选中的值
        });
        $.ajax({
            type: "GET",
            url: path + "/ai/dataCon/getUnityMap",
            data: {mold: 1,departmentId:departmentId},
            dataType: "json",
            success: function (data) {
                $("#unitList").empty();
                var option = "<option value='0' >请选择单位名称</option>";
                for (var i = 0; i < data.length; i++) {
                    option += "<option value='" + data[i].text + "'>" + data[i].text + "</option>";
                }
                $('#unitList').append(option);
                form.render();//菜单渲染 把内容加载进去
            }
        });
        form.on('select(unitList)', function (data) {
            $("#unitListHidden").val(data.value);//得到被选中的值
        });
        $.ajax({
            type: "GET",
            url: path + "/ai/dataCon/getUnityMap",
            data: {mold: 2,departmentId:departmentId,bothType:2},
            dataType: "json",
            success: function (data) {
                $("#unitPointList").empty();
                var option = "<option value='0' >请选择测点类型</option>";
                for (var i = 0; i < data.length; i++) {
                    option += "<option value='" + data[i].text + "'>" + data[i].text + "</option>";
                }
                $('#unitPointList').append(option);
                form.render();//菜单渲染 把内容加载进去
            }
        });
        form.on('select(unitPointList)', function (data) {
            $("#unitPointListHidden").val(data.value);//得到被选中的值
        });
    })
}
//修改
function updConfigure() {
    var id = $("#id1").val();
    var departmentId=$("#departListListHidden").val();
    var address = $("#addressListHidden1").val();//地址
    var measuringPoint = $("#measuringPoint").val();//测点
    var equipment = $("#equSysListHidden").val()+","+$("#equListHidden").val();
    var unit = $("#unitListHidden").val();
    var measuringType = $("#unitPointListHidden").val();//测点类型
    if ( $("#equSysListHidden").val() == "" ||  $("#equSysListHidden").val() == "0") {
        layer.alert("请选择系统名称");
        return;
    }
    if ($("#equListHidden").val() == "" || $("#equListHidden").val() == "0") {
        layer.alert("请选择设备名称");
        return;
    }
    if (unit == "" || unit == "0") {
        layer.alert("请选择单位");
        return;
    }
    if (measuringType == "" || measuringType == "0") {
        layer.alert("请选择测点类型");
        return;
    }
    $.ajax({
        type: "GET",
        url: path + "/ai/dataCon/updateDataConfiguration",
        dataType: "json",
        data: {id: id, address: address, measuringPoint: measuringPoint, equipment: equipment, unit: unit, measuringType: measuringType,departmentId:departmentId},
        success: function (data) {
            showCirculatingPump();
            layer.closeAll();
        }
    });
}
//取消
function cancel() {
    layer.closeAll();
}