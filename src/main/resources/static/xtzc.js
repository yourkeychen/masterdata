$(function () {
    showPage();
    $('#xt-add').bind('click',toAddWindow);
    /*$('.xt-update').bind('click',updateWindow);*/
    $('#savebutton').bind('click',insertObjects)
    $('#resetBtn').bind('click',resetBtn);
});
function resetBtn() {
    $('#xtbs').val('');//清空写入内容
    $('#xtmc').val('');
    $('#xtms').val('');
    $('#xtip').val('');
}
function toAddWindow() {
    $('#xtbs').val('');//清空写入内容
    $('#xtmc').val('');
    $('#xtms').val('');
    $('#xtip').val('');
    addWindow('添加');
}
function showPage() {
    var countpage=getcount();
    pagination(countpage);
}
function getcount() {
    var pagesize;
    $.ajax({
        type:'post',
        url:'/masterData/selectCount',
        async:false,
        dataType : 'json',
        success:function(data){
            pagesize= data.result;
        }
    });
    return pagesize;
}
function updateWindow(ts) {
    addWindow('修改');
    var ths=$(ts).parent().siblings();
    $('#xtbs').val(ths.eq(1).html());//弹出框写入默认值
    $('#xtmc').val(ths.eq(2).html());
    $('#xtms').val(ths.eq(3).html());
    $('#xtip').val(ths.eq(4).html());
    $('.layui-layer-title').data('id',ths.eq(0).html())
}
//获取弹出框中值
function getValues() {
    var xtbs=$('#xtbs').val();
    var xtmc=$('#xtmc').val();
    var xtms=$('#xtms').val();
    var xtip=$('#xtip').val();

    var xtxx={}
    xtxx.sysCode=xtbs;
    xtxx.sysName=xtmc;
    xtxx.sysDesc=xtms;
    xtxx.sysIp=xtip;
    xtxx.id=$('.layui-layer-title').data('id');
    return xtxx;
}
function insertObjects() {
    if($('.layui-layer-title').html()=='添加') {
        var xtxx = getValues();
        if(!xtxx.sysCode&&!xtxx.sysName&&!xtxx.sysDesc&&!xtxx.sysIp)return;
        $.ajax({
            type: 'post',
            url: '/masterData/insertObject',
            data: xtxx,
            async: false,
            dataType: 'json',
            success: function (data) {
                layer.close(layer.index);//关闭弹出窗口
                /*$('#contextMessage').css('display',none)*/
                $('#xtbs').val('');//清空写入内容
                $('#xtmc').val('');
                $('#xtms').val('');
                $('#xtip').val('');
                showPage();//跟新表数据
                if (data.result.success) {
                    showTs('添加成功');
                }
            }
        });
    }else{
        alert('xg')
        var xtxx = getValues();
        if(!xtxx.sysCode&&!xtxx.sysName&&!xtxx.sysDesc&&!xtxx.sysIp)return;
        $.ajax({
            type:'post',
            url:'/masterData/updateObject',
            data:xtxx,
            dataType : 'json',
            success:function(data){
                layer.close(layer.index);//关闭弹出窗口
                /*$('#contextMessage').css('display',none)*/
                $('#xtbs').val('');//清空写入内容
                $('#xtmc').val('');
                $('#xtms').val('');
                $('#xtip').val('');
                showPage();//跟新表数据
                if (data.result.success) {
                    showTs('修改成功');
                }
            }
        });
    }
}
function deleteObject(ts) {
    var ths=$(ts).parent().siblings();
    var id=ths.eq(0).html();
    layui.use('layer', function(){
        layer.confirm('您确定要删除这条数据吗？', {
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
                        layer.closeAll('dialog');
                        showPage();//跟新表数据
                    }
                }
            });
        });

        //layer.closeAll('dialog');
    });
}
function showObjects(pagexx) {
    $.ajax({
        type:'post',
        url:"/masterData/showObjects",
        async:false,
        data:pagexx,
        dataType : 'json',
        success:function(data){
            var table=$('#tbody1');
            table.empty();
            if (data.result){
                for(var i=0; i<data.result.length;i++){
                    table.append('<tr class="psy-tr">\n' +
                        '            <th>'+data.result[i].id+'</th>\n' +
                        '            <th>'+data.result[i].sysCode+'</th>\n' +
                        '            <th>'+data.result[i].sysName+'</th>\n' +
                        '            <th>'+data.result[i].sysDesc+'</th>\n' +
                        '            <th>'+data.result[i].sysIp+'</th>\n' +
                        '            <th>'+new Date(data.result[i].createTime).toLocaleString()+'</th>\n' +
                        '            <th><button class="layui-btn layui-btn-sm xt-update" onclick="updateWindow(this)">修改</button>&nbsp&nbsp&nbsp<button class="layui-btn layui-btn-sm" onclick="deleteObject(this)">删除</button></th>\n' +
                        '        </tr>')
                }
            }else {
                table.append('暂无数据');
            }
        },
    });
}
//弹出窗口
function addWindow(ts) {
    layui.use('layer', function(){
        var layer = layui.layer;
        layer.open({
            type:1,
            title:ts,
            closeBtn:'1',
            bthAlign:'c',
            area:['550px','310px'],
            align:'center',
            shadeClose:true,
            content:$('#contextMessage'),
            end:function(){
                $('#contextMessage').hide();
            }
        })
    });
}
//画分页
function pagination(countpage) {
    layui.use(['laypage','layer'] ,function(){
        var laypage = layui.laypage,
        layer=layui.layer;
        //完整功能
        laypage.render({
            elem: 'pagination'
            , count: countpage //数据总数，从服务端得到
            , limit: 10
            , curr: 1
            , layout: ['count', 'prev', 'page', 'next', 'skip']
            , jump: function (obj, first) {
                //obj包含了当前分页的所有参数，比如：
                //首次不执行
                var pagexx={}
                if(first){
                    pagexx.pageNum=1;
                    pagexx.pageSize=10;
                    showObjects(pagexx);
                }
                if (!first) {
                    pagexx.pageNum=obj.curr;
                    pagexx.pageSize=obj.limit;
                    showObjects(pagexx);
                }
            }
        });
    });

}
function showTs(content) {
    layui.use('layer', function(){
        var layer = layui.layer;
        layer.alert(content,{
            icon:1,
            skin:'my-skin',
            title:"提示"});
    });

}
