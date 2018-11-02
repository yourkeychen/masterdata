/*主数据展示*/
$(function () {
  /*tab*/
    layui.use('table', function(){
        var table = layui.table;
        var menuId=6;
        table.render({
             elem: '#fd-master-data'
            ,height:312
            ,url:'/getData'
            ,where:{
                 menuId:menuId
            }
            ,page: true
            ,width:1010
            ,cols: [[
                 {field:'id', width:80, title: '序号', sort: true}
                ,{field:'code', width:120, title: '编码',sort: true}
                ,{field:'conentName', width:120, title: '内容名称'}
                ,{field:'desc', width:120, title: '描述',minWidth: 150}
                ,{field:'status', width:120,title: '审核状态',sort: true }
                ,{field:'effect', width:120, title: '是否生效', sort: true}
                ,{field:'creator', width:120, title: '创建人', sort: true}
                ,{field:'createTime', width:200, title: '创建时间',sort: true}
            ]]
        });
    });




});

