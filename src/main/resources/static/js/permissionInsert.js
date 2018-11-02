$(function () {
});

function insertOrUpd() {
    var user = getUserValue();
    if($("#id").val() == "") {
        $.ajax({
            type: 'post',
            url: '/permission/insert',
            data: user,
            async: false,
            dataType: 'json',
            success: function (data) {
                $(window.parent.document).contents().find("#fd-page-setting")[0].contentWindow.closeOpen(data,"添加成功");
            }
        });
    }else{
        $.ajax({
            type:'post',
            url:'/permission/update',
            data:user,
            dataType : 'json',
            success:function(data){
                $(window.parent.document).contents().find("#fd-page-setting")[0].contentWindow.closeOpen(data,"修改成功");
            }
        });
    }
}

function getUserValue(){
    var username=$('#username').val();
    var password=$('#password').val();
    var type=$('#type').val();
    var user = {};
    user.username = username;
    user.password = password;
    user.type = type;
    user.id = $("#id").val();
    return user;
}

function resetBtn(){
    $('#username').val('');
    $('#password').val('');
    $('#type').val('');
}