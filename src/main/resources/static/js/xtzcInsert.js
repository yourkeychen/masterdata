$(function(){
    $('#resetBtn').bind('click',resetBtn);
})
function resetBtn() {
    $('#xtbs').val('');//清空写入内容
    $('#xtmc').val('');
    $('#xtms').val('');
    $('#xtip').val('');
}
function insertObjects() {
    if($('#xtzc-id').val()=='') {
        var xtxx = getValues();
        if(!xtxx.sysCode&&!xtxx.sysName&&!xtxx.sysDesc&&!xtxx.sysIp)return;
        $.ajax({
            type: 'post',
            url: '/masterData/insertObject',
            data: xtxx,
            async: false,
            dataType: 'json',
            success: function (data) {
               /* parent.layer.close(parent.layer.index);//关闭弹出窗口
                /!*$('#contextMessage').css('display',none)*!/
                $('#xtbs').val('');//清空写入内容
                $('#xtmc').val('');
                $('#xtms').val('');
                $('#xtip').val('');
                showPageAndTable(currentPage,pageSize);//跟新表数据
                if (data.result.success) {
                    showTs('添加成功');
                }*/
                $(window.parent.document).contents().find("#fd-page-setting")[0].contentWindow.closeOpen(data,"添加成功");
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
                $(window.parent.document).contents().find("#fd-page-setting")[0].contentWindow.closeOpen(data,"修改成功");
            }
        });
    }
}
function getValues() {
    var xtbs=$('#xtbs').val();
    var xtmc=$('#xtmc').val();
    var xtms=$('#xtms').val();
    var xtip=$('#xtip').val();
    var id=$('#xtzc-id').val();
    var xtxx={}
    xtxx.sysCode=xtbs;
    xtxx.sysName=xtmc;
    xtxx.sysDesc=xtms;
    xtxx.sysIp=xtip;
    xtxx.id=id;
    return xtxx;
}
