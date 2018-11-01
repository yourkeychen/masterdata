$(function () {
    var currentPage = 1;
    var pageSize = 10;
    showPageAndTable(currentPage,pageSize)
    $('#xt-add').bind('click',toAddWindow);
    $('#resetBtn').bind('click',resetBtn);
});
function updateWindow(ts) {
    addWindow('修改');
    var ths=$(ts).parent().siblings();
    $('#xtbs').val(ths.eq(1).html());//弹出框写入默认值
    $('#xtmc').val(ths.eq(2).html());
    $('#xtms').val(ths.eq(3).html());
    $('#xtip').val(ths.eq(4).html());
    $('.layui-layer-title').data('id',ths.eq(0).html())
}
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
                showPageAndTable(1,10);//跟新表数据
                if (data.result.success) {
                    showTs('添加成功');
                }
            }
        });
    }else{
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
                showPageAndTable(1,10);//跟新表数据
                if (data.result.success) {
                    showTs('修改成功');
                }
            }
        });
    }
}
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
            content:'<div id="contextMessage" style="text-align: center;">\n' +
                '    <div class="layui-form-item center" style="width: 371px;margin: auto;margin-bottom: 10px;">\n' +
                '        <label class="layui-form-label" style="width: 100px" >系统标识</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="text" name="name" required value="" style="width: 240px" lay-verify="required" placeholder="请输入系统标识" autocomplete="off" class="layui-input" id="xtbs">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item" style="width: 371px;margin: auto;margin-bottom: 10px;">\n' +
                '        <label class="layui-form-label" style="width: 100px">系统名称</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="text" name="type" required style="width: 240px" lay-verify="required" placeholder="请输入系统名称" autocomplete="off" class="layui-input" id="xtmc">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item" style="width: 371px;margin: auto;margin-bottom: 10px;">\n' +
                '        <label class="layui-form-label" style="width: 100px">系统描述</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="text" name="value" required style="width: 240px" lay-verify="required" placeholder="请输入系统描述" autocomplete="off" class="layui-input" id="xtms">\n' +
                '            <!-- <input type="hidden" name="id" style="width: 240px" autocomplete="off" class="layui-input"> -->\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item" style="width: 371px;margin: auto;margin-bottom: 30px;">\n' +
                '        <label class="layui-form-label" style="width: 100px">系统IP</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="text" name="value" required style="width: 240px" lay-verify="required" placeholder="请输入系统IP" autocomplete="off" class="layui-input" id="xtip">\n' +
                '            <!-- <input type="hidden" name="id" style="width: 240px" autocomplete="off" class="layui-input"> -->\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item">\n' +
                '        <div>\n' +
                '            <button class="layui-btn" lay-submit lay-filter="save" id="savebutton" onclick="insertObjects()">确定</button>\n' +
                '            <button type="reset" class="layui-btn layui-btn-primary" id="resetBtn" onclick="resetBtn()">重置</button>\n' +
                '        </div>\n' +
                '    </div>\n' +
                '</div>',
            end:function(){
                $('#contextMessage').hide();
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
            ,url:'/masterData/showObjectsq?currentPage='+currentPage+'&pageSize='+pageSize
            ,width:1340
            ,skin:'line'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'id', width:190, title: '序号'}
                ,{field:'sysCode', width:190, title: '系统标识'}
                ,{field:'sysName', width:190, title: '系统名称'}
                ,{field:'sysDesc', title: '系统描述', width: 190} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                ,{field:'sysIp', title: '系统IP',width:190}
                ,{field:'createTime', title: '注册时间',width:190}
                ,{field:'create_time', title: '操作', toolbar: '#barDemo'}
            ]]
            ,page:false
            ,done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                laypage.render({
                    elem:'xtzc-laypage'
                    ,count:count
                    ,curr:currentPage
                    ,limit:pageSize
                    ,layout: ['count', 'prev', 'page', 'next', 'skip']
                    ,jump:function (obj,first) {
                        if(!first){
                            curnum = obj.curr;
                            limitcount = obj.limit;
                            showPageAndTable(curnum,limitcount)
                        }
                    }
                })
            }
        });
        //监听工具条
        table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                addWindow('修改');
                $('#xtbs').val(data.sysCode);//弹出框写入默认值
                $('#xtmc').val(data.sysName);
                $('#xtms').val(data.sysDesc);
                $('#xtip').val(data.sysIp);
                $('.layui-layer-title').data('id',data.id);
            } else if(layEvent === 'del'){
                deleteObject(data.id);
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
function deleteObject(id) {
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
                        showPageAndTable(1,10);//跟新表数据
                    }
                }
            });
        });

        //layer.closeAll('dialog');
    });
}