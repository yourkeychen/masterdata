$(function () {
    showPageAndTable(currentPage,pageSize)
    $('#xt-add').bind('click',toAddWindow);

});

function toAddWindow() {
    $('#xtbs').val('');//清空写入内容
    $('#xtmc').val('');
    $('#xtms').val('');
    $('#xtip').val('');
    addWindow('添加');
}
//弹出窗口
function addWindow(ts,id) {
    var url= id===undefined? "/masterData/toAddWindow":"/masterData/toAddWindow?id="+id;
    layui.use('layer', function(){
        var layer = layui.layer;
        parent.layer.open({
            type:2,
            title:ts,
            closeBtn:'1',
            bthAlign:'c',
            area:['550px','360px'],
            align:'center',
            shadeClose:true,
            content:url,
            success: function(layero,index){
                $(':focus').blur();
            },
            end:function(){
            }
        })
    });
}
function showPageAndTable(currentPage,pageSize){
    layui.use(['table','laypage'], function(){
        var table = layui.table;
        laypage = layui.laypage;
        table.render({
            elem: '#xtzc-table'
            ,url:'/masterData/showObjectsq'
            ,width:1440
            ,skin:'line'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,page:{
                layout: ['count','prev','page','next','limit','skip'],
                prev: "上一页",
                next: "下一页"
            }
            ,cols: [[
                {width:80,type:'numbers', title: '序号'}
                ,{field:'sysCode', width:190, title: '系统标识'}
                ,{field:'sysName', width:190, title: '系统名称'}
                ,{field:'sysDesc', title: '系统描述', width: 190} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                ,{field:'sysIp', title: '系统IP'}
                ,{field:'createTime', title: '注册时间',width:190}
                ,{field:'create_time', title: '操作', toolbar: '#barDemo',width:200}
            ]]
        });
        //监听工具条
        table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                addWindow('修改',data.id);
            } else if(layEvent === 'del'){
                deleteObject(data.id);
            }
        });
    });
}
function deleteObject(id) {
    layui.use('layer', function(){
        parent.layer.confirm('您确定要删除这条数据吗？', {
            btn: ['确定','取消'], //按钮
            skin:'my-skin'
        }, function()
        {
            $.ajax({
                type: "post",
                url: "/masterData/deleteObject",
                data: {
                    "id": id
                },
                success : function(data){
                    if(data.result.success){
                        parent.layer.closeAll('dialog');
                        //tableReload('xtzc-table');
                        showPageAndTable();
                        parent.layer.msg('删除成功',{
                            time:3000
                        })
                    }
                }
            });
        });
    });
}
function closeOpen(data,msg){
    parent.layer.close(parent.layer.index);//关闭弹出窗口
    tableReload('xtzc-table');
    if (data.code == 'success') {
        // parent.showTs(msg);
        parent.layer.msg(msg,{
            time:3000
        })
    }
}
function tableReload(id){
    layui.use("table",function () {
        var table = layui.table;
        table.reload(id);
    });
}
/*
function showTs(content) {
    layui.use('layer', function(){
        var layer = layui.layer;
        layer.alert(content,{
            icon:1,
            skin:'my-skin',
            title:"提示"});
    });
}*/
