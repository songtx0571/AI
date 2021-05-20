var path = "";
$(function () {
    showTable();
});
function showTable () {
    var win = $(window).height();
    var height = win - 20;
    layui.use(['table', 'form'], function() {
        var table = layui.table;
        table.render({
            elem: '#demo',
            height: height,
            url: path + '',
            page: true //开启分页
            ,
            limit: 50,
            limits: [50, 100, 150],
            id: 'demoInfo',
            cols: [[ //表头
                {field: 'id', title: 'ID', align:'center', hide: true}
                ,{field: 'address', title: '设备名称', sort: true, align:'center'}
                ,{field: 'time', title: '时间', sort: true, edit: 'text', align:'center'}
                ,{field: 'pump1_A', title: '电机振动1', sort: true, align:'center'}
                ,{field: 'pump2_A', title: '电机振动2', sort: true, align:'center'}
                ,{field: 'pump3_A', title: '电机振动3', sort: true, align:'center'}
                ,{field: 'pump4_A', title: '电机振动4', sort: true, align:'center'}
                ,{field: 'pump1_B', title: '泵体振动1', sort: true, align:'center'}
                ,{field: 'pump2_B', title: '泵体振动2', sort: true, align:'center'}
                ,{field: 'pump3_B', title: '泵体振动3', sort: true, align:'center'}
                ,{field: 'pump4_B', title: '泵体振动4', sort: true, align:'center'}
                // ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150,align:'center'}
            ]],
            parseData: function(res) {
            },
            done: function(res, curr, count) {}
        });
    })
}