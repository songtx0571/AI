var path = "";
$(function () {
    getProDepart();
    $("#statusHidden").val("1");
});
//获取设备名称下拉框
function getProDepart() {
    layui.use(['form'], function() {
        var form = layui.form;
        $.ajax({
            type: "GET",
            url: path + "/ai/equipment/getCompanyMap",
            dataType: "json",
            success: function (data) {
                $("#proDepartList").empty();
                $("#proDepartList1").empty();
                $("#updProDepartList").empty();
                // $("#testSelect").empty();
                var option = "<option value='0' >请选择项目部名称</option>";
                for (var i = 0; i < data.length; i++) {
                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                }
                $('#proDepartList').append(option);
                $('#proDepartList1').append(option);
                $('#updProDepartList').append(option);
                // $('#testSelect').append(option);
                form.render();//菜单渲染 把内容加载进去
            }
        });
        form.on('select(proDepartList)', function (data) {
            $("#proDepartListHidden").val(data.value);//得到被选中的值
            $(".addBTn").css("display","contents");
            showEquipment();
        });
        form.on('select(proDepartList1)', function (data) {
            $("#proDepartListHidden1").val(data.value);//得到被选中的值
        });
        form.on('select(updProDepartList)', function (data) {
            $("#updProDepartListHidden").val(data.value);//得到被选中的值
        });
    })
}
function showEquipment() {
    var departmentId = $("#proDepartListHidden").val();
    var top = $(".top").css("height");
    var win = $(window).height();
    var tp = top.indexOf("p");
    var topHeight = top.substring(0,tp);
    var height = win-topHeight-35;
    layui.use(['table','form'], function() {
        var table = layui.table;
        var form = layui.form;
        table.render({
            elem: '#demo'
            , height: height
            , toolbar: true
            , url: path + "/ai/equipment/getAiEquipemntList?departmentId="+departmentId //数据接口
            , cols: [[ //表头
                {field: 'id', title: '编号', width: 80, hide: true}
                , {field: 'name', title: '设备名称'}
                , {field: 'departmentName', title: '项目部', align: 'center'}
                , {field: 'status', title: '状态', toolbar: '#barDemo1', align: 'center'}
                , {fixed: '', title: '操作', toolbar: '#barDemo3', align: 'center'}
            ]]
        });
        form.on('select(testSelect)', function (data) {
            var proDepartTd = data.value;
            $("#updProDepartHidden").val(proDepartTd);
        });
        table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            if (obj.event === 'edit') {// 修改权限
                $("#updressHidden").val(data.name);
                $("#updProDepartListHidden").val(data.departmentId);
                $("#updStatusHidden").val(data.status);
                $("#updIdHidden").val(data.id);
                layui.use('form', function(){
                    var form = layui.form;
                    if(data.status == '1'){
                        $("input[name=updStatus][value='1']").prop("checked", "true");
                    }else{
                        $("input[name=updStatus][value='0']").prop("checked", "true");
                    }
                    $("#updProDepartList").val(data.departmentId);
                    form.render('select');
                    form.render(); //更新全部
                });
                layui.use('layer', function () { //独立版的layer无需执行这一句
                    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
                    layer.open({
                        type: 1
                        , id: 'updEquipmentDiv' //防止重复弹出
                        , content: $(".updEquipmentDiv")
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
//打开添加页面
function showAddEquipment() {
    $("#addressHidden").val("");
    layui.use('layer', function () { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
        layer.open({
            type: 1
            , id: 'addEquipmentDiv' //防止重复弹出
            , content: $(".addEquipmentDiv")
            , btnAlign: 'c' //按钮居中
            , shade: 0.1 //不显示遮罩
            , area: ['100%', '100%']
            , yes: function () {
            }
        });
    });
    layui.use(['form'], function () {
        var upload = layui.upload;
        var form = layui.form;
        //此处即为 radio 的监听事件
        form.on('radio(status)', function(data){
            var status = data.value;//被点击的radio的value值
            $("#statusHidden").val(status)
        });
    });
}
//添加
function addEquipment() {
    var aiEquipment = {};
    aiEquipment.departmentId = $("#proDepartListHidden").val();
    aiEquipment.name = $("#addressHidden").val();
    aiEquipment.status = $("#statusHidden").val();
    $.ajax({
        type: "POST",
        url: path + "/ai/equipment/addAiEquipemnt",
        dataType: "json",
        data: JSON.stringify(aiEquipment),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            layer.closeAll();
            if (data == "ERROR") {
                layer.alert("无id")
            }else if (data == "CANCEL"){
                layer.alert("同名/操作不成功")
            } else{
                showEquipment();
            }
        }
    });
}
//修改
function updEquipment() {
    layui.use(['form'], function () {
        var upload = layui.upload;
        var form = layui.form;
        //此处即为 radio 的监听事件
        form.on('radio(updStatus)', function(data){
            var updStatus = data.value;//被点击的radio的value值
            $("#updStatusHidden").val(updStatus)
        });
    });
    var aiEquipment = {};
    aiEquipment.departmentId = $("#updProDepartListHidden").val();
    aiEquipment.name = $("#updressHidden").val();
    aiEquipment.status = $("#updStatusHidden").val();
    aiEquipment.id = $("#updIdHidden").val();
    $.ajax({
        type: "POST",
        url: path + "/ai/equipment/updAiEquipemnt",
        dataType: "json",
        data: JSON.stringify(aiEquipment),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            layer.closeAll();
            if (data == "ERROR") {
                layer.alert("无id")
            }else if (data == "CANCEL"){
                layer.alert("同名/操作不成功")
            } else{
                showEquipment();
            }
        }
    });
}
//取消
function cancel() {
    layer.closeAll();
}