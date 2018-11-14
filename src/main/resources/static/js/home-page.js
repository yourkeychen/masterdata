$(function () {
    var menuId=parent.$(".layui-nav-child dd:first a").attr("menu-id");
    var childUrl=parent.$(".layui-nav-child dd:first a").attr("value");
    var childMenuURL=childUrl+"?menuId="+menuId;
    window.location.href=childMenuURL;
    parent.$(".layui-nav-child dd:first").addClass("layui-this");
});