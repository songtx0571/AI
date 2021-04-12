var path = "";
$(function () {
    showDispose();
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
            $.ajax({
                type: "GET",
                url: path + "/ai/dataCon/getAddressList?departmentId="+$("#departListListHidden").val(),
                dataType: "json",
                success: function (data) {
                    $("#addressList").empty();
                    var option = "<option value='0' >请选择设备名称</option>";
                    for (var i = 0; i < data.length; i++) {
                        option += "<option value='" + data[i].name + "'>" + data[i].name + "</option>";
                    }
                    $('#addressList').append(option);
                    form.render();//菜单渲染 把内容加载进去
                }
            });
            form.on('select(addressList)', function (data) {
                $("#addressListHidden").val(data.value);//得到被选中的值
                showDispose();
            });
        });
    })
}
//显示表格
function showDispose() {
    var address = "";
    if ($("#addressListHidden").val() == "" || $("#addressListHidden").val() == "0"){
    } else {
        address = $("#addressListHidden").val();
    }
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#demo'
            ,height: 'full'
            ,toolbar: true
            ,url: path + "/ai/aiConfigure/getAiConfigureList?address="+address //数据接口
            ,page: true //开启分页
            ,limit: 50
            ,limits: [50, 100, 150]
            ,cols: [[ //表头
                {field: 'id', title: '编号', width:80, hide: true}
                ,{field: 'address', title: '设备名称', sort: true, align:'center'}
                ,{field: 'inquiry', title: '问询语句', sort: true, edit: 'text', align:'center'}
                ,{field: 'type', title: '类型', sort: true,align:'center'}
                ,{field: 'worked', title: '工作状态', sort: true, edit: 'text', align:'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150,align:'center'}
            ]]
            , parseData: function(res) {
            }
            ,done: function(res, curr, count){
            }
        });
        //监听单元格编辑
        table.on('edit(test)', function(obj){
        });
        //工具栏事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            var address = data.address;
            var type = data.type;
            var worked = data.worked;
            var inquiry = data.inquiry;
            if (obj.event === 'edit') {
                $.ajax({
                    type: "GET",
                    url: path + "/ai/aiConfigure/updAiConfigure",
                    dataType: "json",
                    data: {worked: worked, inquiry: inquiry, address: address, type: type},
                    success: function (data) {
                        alert("修改成功");
                        showDispose("");
                    }
                });
            }
        });
    });
}