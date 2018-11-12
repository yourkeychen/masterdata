$(function () {
});

// function insertOrUpd() {
//     $("#savebutton").attr("onclick","");
//     setTimeout(function(){
//         $("#savebutton").attr("onclick","insertOrUpd()");
//     },1000);
//     var user = getUserValue();
//     if($("#id").val() == "") {
//         $.ajax({
//             type: 'post',
//             url: '/permission/insert',
//             data: user,
//             async: false,
//             dataType: 'json',
//             success: function (data) {
//                 $(window.parent.document).contents().find("#fd-page-setting")[0].contentWindow.closeOpen(data,"添加成功");
//             }
//         });
//     }else{
//         $.ajax({
//             type:'post',
//             url:'/permission/update',
//             data:user,
//             dataType : 'json',
//             success:function(data){
//                 $(window.parent.document).contents().find("#fd-page-setting")[0].contentWindow.closeOpen(data,"修改成功");
//             }
//         });
//     }
// }

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

layui.use('form',function () {
    var form =layui.form;
    form.verify({
        username:function (value) {
            var checkValue = '';
            if($("#id").val() == "") {
                $.ajax({
                    type: 'post',
                    url: '/permission/getUsername',
                    data: {
                        username: value
                    },
                    async: false,
                    dataType: 'json',
                    success: function (data) {
                        if (data.result == true) {
                            checkValue = '该用户已被注册';
                        }
                    }
                });
            }
            if(checkValue != ''){
                return checkValue;
            }
            if (value.length>20){
                return '字符长度不能超过20'
            }

        },
        password:function (value) {
            if (value.length>20){
                return '字符长度不能超过20'
            }
        }
    })
    form.on('submit(save)',function () {
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
        return false;
    })
})