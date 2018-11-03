$(function () {
    $('#pg-pg-submit-login').bind('click',submitLoginData);
});

function submitLoginData() {
    var userName=$("#fd-user-name").val();
    var pwd=$("#fd-pwd").val();

    $.ajax({
        url : "/ilogin",
        type: "POST",
        dataType: 'json',
        async: false,
        data: {
            username:userName,
            password:pwd
        },
        success: function (data) {
            if (data.result==true){
                window.location.href="/homePage";
            }

        }
    });
}