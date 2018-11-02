$(function () {
   // var data = {
   //     tableId: "tab",
   //     columns : [
   //         {field:"xuhao",title:"序号",width:150},
   //         {field:"xitongbiaoshi",title:"系统标识",width:150},
   //         {field:"xitongmingcheng",title:"系统名称",width:150},
   //         {field:"xitongmiaoshu",title:"系统描述",width:150},
   //         {field:"xitongip",title:"系统ip",width:150},
   //         {field:"caozuo",title:"操作",width:150,formatter: function (value,row,index) {
   //                 var e ="<button class=\"layui-btn layui-btn-sm\">修改</button>&nbsp&nbsp&nbsp<button class=\"layui-btn layui-btn-sm\">删除</button>";
   //                 return e;
   //             }},
   //     ],
   //     tableList: [
   //         {xuhao:1, xitongbiaoshi:"2", xitongmingcheng:"3", xitongmiaoshu:"4" ,xitongip:"5"},
   //         {xuhao:1, xitongbiaoshi:"2", xitongmingcheng:"3", xitongmiaoshu:"4" ,xitongip:"5"},
   //         {xuhao:1, xitongbiaoshi:"2", xitongmingcheng:"3", xitongmiaoshu:"4" ,xitongip:"5"},
   //         {xuhao:1, xitongbiaoshi:"2", xitongmingcheng:"3", xitongmiaoshu:"4" ,xitongip:"5"},
   //         {xuhao:1, xitongbiaoshi:"2", xitongmingcheng:"3", xitongmiaoshu:"4" ,xitongip:"5"}
   //     ]
   // };
   //  setTable(data);

    layuiSetTable();
});


function layuiSetTable() {
    layui.use("table",function () {
        var table = layui.table;

        table.render({
            elem: "#tab",
            url: "/permission/list",
            cellMinWidth: 80,
            page:true,
            cols: [[
                {field:"id",title:"ID",width:150,sort:true},
                {field:"userName",title:"用户名",width:150,sort:true},
                {field:"password",title:"密码",width:150,sort:true},
                {field:"type",title:"类型",width:150,sort:true},
                {field:"createTime",title:"创建时间",width:150,sort:true},
                {field:"updateTime",title:"修改时间",width:150,sort:true}
            ]]
        });
    })
}

function toAddWindow() {
    $('#xtbs').val('');//清空写入内容
    $('#xtmc').val('');
    $('#xtms').val('');
    $('#xtip').val('');
    add('添加');
}

function add(ts){
    layui.use('layer',function(){
        var layer = layui.layer;
        layer.open({
            type: 1,
            title: ts,
            closeBtn: '1',
            bthAlign: 'c',
            area:['550px','310px'],
            align:'center',
            shadeClose:true,
            content:$('#contextMessage123'),
            success: function(layero){
                var mask = $(".layui-layer-shade");
                mask.appendTo(layero.parent());
            },
            end:function(){
                $('#contextMessage123').hide();
            }
        })
    });
}

function upd(ts){
    var ths = $(ts).parent().siblings();
}

function del(){

}