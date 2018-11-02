$(function () {
    /*主数据查询tab显示*/
    showMasterDataTab();
    /*主数据弹框*/
    $('#pg-add-master-data').bind('click',toAddMasterData);
    //提交数据
    $('#pg-submit-data').bind('click',submitMasterData);
    /*主数据审核*/
    showMasterDataExamineTab()

});

/*主数据查询tab显示*/
function showMasterDataTab(){
    layui.use('table', function(){
        var table = layui.table;
        var menuId=$("#pg-menu-id").val();
        table.render({
            elem: '#fd-master-data'
            ,url:'/getData'
            ,where:{
                menuId:menuId
            },
            cellMinWidth: 80,
            width: 1500,
            page:{
                layout: ['count','prev','page','next','limit','skip'],
                prev: "上一页",
                next: "下一页"
            }
            ,cols: [[
                {field:'id', title: '序号', sort: true}
                ,{field:'code', title: '编码',sort: true}
                ,{field:'conentName', title: '内容名称'}
                ,{field:'desc',  title: '描述',minWidth: 150}
                ,{field:'status', title: '审核状态',sort: true }
                ,{field:'effect', title: '是否生效', sort: true}
                ,{field:'creator', title: '创建人', sort: true}
                ,{field:'createTime', title: '创建时间',sort: true}
            ]]
        });
    });
}


function toAddMasterData() {
 /*   $('#xtbs').val('');//清空写入内容
    $('#xtmc').val('');
    $('#xtms').val('');
    $('#xtip').val('');*/
    addMasterData('添加');
};

//弹出窗口
function addMasterData(ts) {
    layui.use('layer', function(){
        var layer = layui.layer;
        layer.open({
            type:1,
            title:ts,
            closeBtn:'1',
            bthAlign:'c',
            area:['550px','420px'],
            align:'center',
            shadeClose:true,
            content:$('#pg-add-data-windows'),
            end:function(){
                $('#pg-add-data-windows').hide();
            }
        })
    });
}

//提交数据
function submitMasterData() {
    var saveMasterDataUrl="/saveMasterData"
    var code=$("#pg-code").val();
    var conentName=$("#pg-conentName").val();
    var desc=$("#pg-desc").val();
    var reason=$("#pg-reason").val();
    var menuId=$("#pg-menu-id").val();
    var effect=$("input:radio:checked").val();

    $.ajax({
        url : saveMasterDataUrl,
        type: "POST",
        dataType: 'json',
        async: false,
        data: {
            code:code,
            conentName:conentName,
            desc:desc,
            effect:effect,
            menuId:menuId,
            reason:reason
        },
        success: function (result) {
            if (result==1){
                showTs('添加成功');
                layer.close(layer.index);
                window.parent.location.reload();
            }else {
                showTs('添加失败');
            }
        }
    });
}

//提示
function showTs(content) {
    layui.use('layer', function(){
        var layer = layui.layer;
        layer.alert(content,{
            icon:1,
            skin:'my-skin',
            title:"提示"});
    });
}

/*主数据审核tab显示*/
function showMasterDataExamineTab() {
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#fd-data-examine'
            ,url:'/getExamineMasterData'
            ,cellMinWidth: 80,
            width: 1500,
            page:{
                layout: ['count','prev','page','next','limit','skip'],
                prev: "上一页",
                next: "下一页"
            }
            ,cols: [[
                 {field:'id',title: '序号', sort: true}
                ,{field:'code',title: '编号', sort: true}
                ,{field:'conent_name',title: '内容名称'}
                ,{field:'description',title: '描述'}
                ,{field:'effect', title: '是否生效', sort: true} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                ,{field:'zsjname', title: '主数据'}
                ,{field:'zsjkname', title: '主数据库'}
                ,{field:'create_time', title: '申请时间', sort: true}
                ,{field:'applicant', title: '申请人', sort: true}
                ,{field:'reason', title: '申请理由'}
                ,{field:"caozuo",title:"审核",toolbar:'#pg-examine', width:160}
            ]]
        });
    });
}
