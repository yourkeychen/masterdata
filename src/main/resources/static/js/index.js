$(function () {
    layui.use('layer', function() {
        var layer = layui.layer;
    });
    // $('#toxtzc').bind('click',toXtzc)
    // var data = {
    //     tableId: "tab",
    //     pageId: "page",
    //     pagination: true,
    //     isAjax: true,
    //     url: "/permission/list",
    //     pageList: [1,2,3],
    //     columns : [
    //         {field:"id",title:"ID",width:150},
    //         {field:"userName",title:"用户名",width:150},
    //         {field:"password",title:"密码",width:150},
    //         {field:"type",title:"类型",width:150},
    //         {field:"createTime",title:"创建时间",width:150},
    //         {field:"updateTime",title:"修改时间",width:150},
    //         {field:"caozuo",title:"操作",width:150,formatter: function (value,row,index) {
    //             var e ="<button class=\"layui-btn layui-btn-sm\">修改</button>&nbsp&nbsp&nbsp<button class=\"layui-btn layui-btn-sm\">删除</button>";
    //             return e;
    //         }}
    //     ]
    // };
    // // setTable(data);
});


// function load(){
//     $(".layui-body").load("test");
// }

/*function test(){
    var data = {};
    data.url = "a";
    data.method = "GET";
    data.columns = [
        {field:"xuhao",title:"序号",width:150},
        {field:"xitongbiaoshi",title:"系统标识",width:150},
        {field:"xitongmingcheng",title:"系统名称",width:150},
        {field:"xitongmiaoshu",title:"系统描述",width:150},
        {field:"xitongip",title:"系统ip",width:150},
        {field:"caozuo",title:"操作",width:150,formatter: function (value,row,index) {
                var e ="<button class=\"layui-btn layui-btn-sm\">修改</button>&nbsp&nbsp&nbsp<button class=\"layui-btn layui-btn-sm\">删除</button>";
                return e;
            }},
    ];
    data.tableList = [
        {xuhao:1, xitongbiaoshi:"2", xitongmingcheng:"3", xitongmiaoshu:"4" ,xitongip:"5"},
        {xuhao:1, xitongbiaoshi:"2", xitongmingcheng:"3", xitongmiaoshu:"4" ,xitongip:"5"},
        {xuhao:1, xitongbiaoshi:"2", xitongmingcheng:"3", xitongmiaoshu:"4" ,xitongip:"5"},
        {xuhao:1, xitongbiaoshi:"2", xitongmingcheng:"3", xitongmiaoshu:"4" ,xitongip:"5"},
        {xuhao:1, xitongbiaoshi:"2", xitongmingcheng:"3", xitongmiaoshu:"4" ,xitongip:"5"}
    ]
    setTable(data);
}*/

function delbyindex(id,url){
    layer.confirm("你确定删除这条数据吗？",{
        offset:'300px',
        btn:['确认','取消'],
        success: function (layero,index) {
            $(':focus').blur();
            this.enterConfirm = function (event){
                if(event.keyCode == 13){
                    $(".layui-layer-btn0").click();
                    return false;
                }else if(event.keyCode == 27){
                    $(".layui-layer-btn1").click();
                    return false;
                }
            };
            $(document).on("keydown",this.enterConfirm);
        },
        end: function () {
            $(document).off("keydown",this.enterConfirm);
        }
    }, function () {
        $.ajax({
            type: 'post',
            url: url,
            data: {id: id},
            async: false,
            dataType: 'json',
            success: function (data) {
                layer.close(layer.index);//关闭弹出窗口
                $(window.document).contents().find("#fd-page-setting")[0].contentWindow.tableReload("permissionTable");
                if (data.code == 'success') {
                    showTs('删除成功');
                }
            }
        });
    });
}

function showTs(content) {
    layui.use('layer', function(){
        var layer = layui.layer;
        layer.msg(content,{offset:'50px'});
    });
}

function beforeSend(){
    $.ajax({
        type: 'post',
        url: '/islogin',
        async: false,
        dataType: 'json',
        success: function (data) {
            if(data.result != true){
                layer.confirm("会话已过期，请重新登陆",function () {
                    window.location.href = "/goLogin";
                    return false;
                })
            }
        }
    });
    return true;
}