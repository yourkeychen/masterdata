$(function(){
})
function getValues() {
    var xtbs=$.trim($('#xtbs').val());
    var xtmc=$.trim($('#xtmc').val());
    var xtms=$.trim($('#xtms').val());
    var xtip=$.trim($('#xtip').val());
    var id=$.trim($('#xtzc-id').val());
    var xtxx={}
    xtxx.sysCode=xtbs;
    xtxx.sysName=xtmc;
    xtxx.sysDesc=xtms;
    xtxx.sysIp=xtip;
    xtxx.id=id;
    return xtxx;
}
layui.use('form',function () {
    var form =layui.form;
    form.verify({
        xtbs:function (value,o) {
            if (value.length>50){
                return '字符长度不能超过50'
            }
        },
        xtmc:function (value) {
            if (value.length>60){
                return '字符长度不能超过60'
            }
        },
        xtms:function (value) {
            if (value.length>200){
                return '字符长度不能超过200'
            }
        },
        xtip:function (value) {
            if (value.length>50){
                return '字符长度不能超过50'
            }
        }
    })
    form.on('submit(*)',function () {
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
                    $(window.parent.document).contents().find("#fd-page-setting")[0].contentWindow.closeOpen(data,"添加成功");
                }
            });
        }else{
            var xtxx = getValues();
            if(!xtxx.sysCode&&!xtxx.sysName&&!xtxx.sysDesc&&!xtxx.sysIp)return;
            $.ajax({
                type:'get',
                url:'/masterData/updateObject',
                data:xtxx,
                dataType : 'json',
                success:function(data){
                    $(window.parent.document).contents().find("#fd-page-setting")[0].contentWindow.closeOpen(data,"修改成功");
                }
            });
        }
        return false;
    })
})
