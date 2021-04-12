$(function() {
    $('#weather').datagrid({
        url: '/ai/weather/getWeatherList',
        method: 'get',
        title: '巡检模板',
        //width: 'auto',
        height: 600,
        //fitColumns: true,//自适应列
        loadMsg: '正在加载信息...',
        pagination: true,//允许分页
        //singleSelect: true,//单行选中。
        pageSize: 10,
        pageNumber: 1,
        pageList: [10, 15, 20, 30, 50],
        //queryParams: { type: 'yes' }, //往后台传参数用的。
        columns: [[
            {field: 'id', title: '编号', align: 'center', hidden: true},
            {field: 'address', title: '设备名称',width:40, align: 'center'},
            {field: 'time', title: '时间',width:40, align: 'center'},
            {field: 'temperature', title: '温度/℃',width:40},
            {field: 'humidity', title: '湿度/%', width:40,align: 'center'},
            {field: 'wind_direction', title: '风向',width:40, align: 'center'},
            {field: 'wind_speed', title: '风速m/s',width:40, align: 'center'},
            {field: 'rainfall', title: '降雨量/mm',width:40, align: 'center'},
            {field: 'atmospheric', title: '大气压/Kpa',width:40, align: 'center'},
            {field: 'ip', title: '来源',width:40, align: 'center', hidden: true}
        ]],
        onClickRow: function (rowIndex, rowData) {
            $('#weather').datagrid('clearSelections');
        },
        onLoadSuccess: function (data) {
            if (data.total == 0) {

            }
            else $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').show();
        },
    });
});


