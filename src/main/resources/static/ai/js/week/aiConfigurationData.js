var path = "";
$(function() {
    getAddress();
    showSelTime();
});
//显示时间
function showSelTime() {
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //查询所有数据日期
        //年月选择器
        laydate.render({
            elem: '#test1'
            , type: 'date'
            , trigger: 'click'//呼出事件改成click
            , done: function (value) {
                $("#selStartTime").val(value);
            }
        });
        laydate.render({
            elem: '#test2'
            , type: 'date'
            , trigger: 'click'//呼出事件改成click
            , showBottom: false
            , done: function (value) {
                $("#selEndTime").val(value);
            }
        });
    });
}
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
            $.ajax({
                type: "GET",
                url: path + "/ai/dataCon/getAddressList?departmentId="+$("#departListListHidden").val(),
                dataType: "json",
                success: function (data) {
                    $("#addressList").empty();
                    var option = "<option value='0' >请选择设备名称</option>";
                    for (var i = 0; i < data.length; i++) {
                        option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                    }
                    $('#addressList').append(option);
                    form.render();//菜单渲染 把内容加载进去
                }
            });
            form.on('select(addressList)', function (data) {
                $("#addressListHidden").val(data.value);//得到被选中的值
                $("#selectDetailsBtn").css("display","block");
                $(".content").css("display","block");
                showDispose($("#departListListHidden").val(),$("#addressListHidden").val(),"","","");
            });
        });
        $("#measuringPointListHidden").val("temp1")
        form.on('select(measuringPointList)', function (data) {
            $("#measuringPointListHidden").val(data.value);//得到被选中的值
        });
    })
}
//显示表格
function showDispose(departmentId,addressId,measuringPoint,startTime,endTime) {
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
            ,toolbar: true
            , url: path + "/ai/dataCon/getAiConfigureDataList" //数据接口
            ,where: {addressId: addressId,departmentId:departmentId,measuringPoint:measuringPoint,startTime:startTime,endTime:endTime}//传参
            , page: true //开启分页
            , limit: 50
            , limits: [50, 100, 150]
            , cols: [[ //表头
                {field: 'id', title: '编号', align: 'center', hide: true},
                // {field: 'addressName', title: '设备名称', align: 'center'},
                {field: 'data', title: '数据', align: 'center'},
                {field: 'unit', title: '单位', align: 'center'},
                {field: 'measuringPoint', title: '测点', align: 'center'}
            ]]
            , parseData: function (res) {
            }
            , done: function (res, curr, count) {
            }
        });
    });
}
//打开搜索页面
function selectDetails() {
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
        layer.open({
            type: 1
            , id: 'selectDetailsDiv' //防止重复弹出
            , content: $(".selectDetailsDiv")
            , btnAlign: 'c' //按钮居中
            , shade: 0.1 //不显示遮罩
            , area: ['100%', '100%']
            , yes: function () {
            }
        });
    });
    $("#selStartTime").val("");
    $("#selEndTime").val("");
    layui.use('form', function(){
        var form = layui.form;
        $("#test1").val("");//地址
        $("#test2").val("");//测点
        form.render('select');
        form.render(); //更新全部
    });
}
//确定搜索
function selOk() {
    layer.closeAll();
    if ($("#selStartTime").val() == ""){
        showDispose($("#departListListHidden").val(),$("#addressListHidden").val(),$("#measuringPointListHidden").val(),"",$("#selEndTime").val());
    } else if ($("#selEndTime").val() == "") {
        showDispose($("#departListListHidden").val(),$("#addressListHidden").val(),$("#measuringPointListHidden").val(),"",$("#selEndTime").val());
    } else{
        showDispose($("#departListListHidden").val(),$("#addressListHidden").val(),$("#measuringPointListHidden").val(),$("#selStartTime").val(),$("#selEndTime").val());
    }
}
//取消
function cancel() {
    layer.closeAll();
}


