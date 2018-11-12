$(function () {

    layuiSetTable();
});


function layuiSetTable() {
    layui.use("table",function () {
        var table = layui.table;
        var aaa = {
            elem: "#tab",
            id:'permissionTable',
            url: "/permission/list",
            cellMinWidth: 80,
            width: 1500,
            page:{
                layout: ['count','prev','page','next','limit','skip'],
                prev: "上一页",
                next: "下一页"
            },
            cols: [[
                {field:"id",width:80,type:'numbers',title:"序号"},
                {field:"userName",title:"用户名",sort:true},
                {field:"password",title:"密码",sort:true},
                {field:"type",title:"角色",sort:true,templet: function(res){
                   if(res.type == 0){
                       return "数据审核";
                   }
                   if(res.type == 1){
                       return "数据维护";
                   }
                   if(res.type == 2){
                       return "数据查看";
                   }

                }},
                {field:"createTime",title:"创建时间",sort:true},
                {field:"updateTime",title:"修改时间",sort:true}
            ]]
        };
        var bbb = {field:"caozuo",title:"操作",toolbar:"#xiugai",width: 200};
        aaa.cols[0].push(bbb);
        table.render(aaa);

        table.on('tool(test)',function(obj){
            var data = obj.data;
            if(obj.event == 'edit'){
                add('修改',data.id);
            }else if(obj.event == 'del'){
                parent.delbyindex(data.id,'/permission/delete');
            }
        })
    });
}

function toAddWindow() {
    add('添加');
}

function add(ts,id){
    var url =id === undefined ? "/permission/toInsert" : "/permission/toInsert?id="+id;
    layui.use('layer',function(){

        parent.layer.open({
            id: 'insert-form',
            type: 2,
            title: ts,
            closeBtn: '1',
            bthAlign: 'c',
            area:['550px','310px'],
            align:'center',
            shadeClose:true,
            content:url,
            success: function(layero,index){
            },
            end:function(){
            }
        })
    });
}

function closeOpen(data,msg){
    parent.layer.close(parent.layer.index);//关闭弹出窗口
    tableReload('permissionTable');
    if (data.code == 'success') {
        parent.showTs(msg);
    }
}



function tableReload(id){
    layui.use("table",function () {
        var table = layui.table;

        table.reload(id);
    });

}

function ilogin() {
    $.ajax({
        url : "/ilogin",
        type: "POST",
        data: {
            username : "123",
            password : "123"
        },
        async: false,
        dataType: 'json',
        success: function (data) {
            console.dir(data);
        }
    });
}

function toHidden() {
    $("button[name='btn']").css("display","none");
}