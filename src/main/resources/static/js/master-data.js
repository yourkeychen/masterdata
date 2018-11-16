$(function () {
    /*主数据查询tab显示*/
    showMasterDataTab();
    /*主数据弹框*/
    $('#pg-add-master-data').bind('click',toAddMasterData);
    /*主数据审核*/
    showMasterDataExamineTab();

});




/*主数据查询tab显示*/
function showMasterDataTab(){
    layui.use('table', function(){
        var table = layui.table;
        var menuId=$("#pg-menu-id-add").val();
        table.render({
            elem: '#fd-master-data'
            ,id:'masterDataTable'
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
                 {title: '序号', sort: true,width:80,type:'numbers'}
                ,{field:'code', title: '编码',sort: true,align:'center'}
                ,{field:'contentName', title: '内容名称',align:'center'}
                ,{field:'desc',  title: '描述',minWidth: 150,align:'center'}
                ,{field:'status', title: '审核状态',sort: true,align:'center',
                    templet:function(row){
                    return forStatus(row.status);
                    }}
                ,{field:'effect', title: '是否生效', sort: true,align:'center',
                    templet:function (row) {
                        return forEffect(row.effect);
                }}
                ,{field:'creator', title: '创建人', sort: true,align:'center'}
                ,{field:'createTime', title: '创建时间',sort: true,align:'center'}
            ]]
        });
    });
}

//审核状态转换
function forStatus(status) {
    if (status==0){
        return "待审核"
    }else if(status==1){
        return "通过"
    }else {
        return "不通过"
    }
}

//是否有效转换
function forEffect(effect) {
    if(effect==0){
      return "有效";
    }else {
      return "无效";
    }
}
function toAddMasterData() {
    addMasterData('添加');
};




/!*主数据审核tab显示*!/
function showMasterDataExamineTab() {
    layui.use('table', function(){
        var table = layui.table;

        var tab = {
            elem: '#fd-data-examine'
            ,url:'/getExamineMasterData'
            ,cellMinWidth: 80
            ,id:"examineMasterDataShow",
            width: 1500,
            page:{
                layout: ['count','prev','page','next','limit','skip'],
                prev: "上一页",
                next: "下一页"
            }
            ,cols: [[
                {title: '序号', sort: true,width:80,type:'numbers'}
                ,{field:'code',title: '编号', sort: true,align:'center'}
                ,{field:'content_name',title: '内容名称',align:'center'}
                ,{field:'description',title: '描述',align:'center'}
                ,{field:'effect', title: '是否生效', sort: true,align:'center'} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                ,{field:'zsjname', title: '主数据',align:'center'}
                ,{field:'zsjkname', title: '主数据库',align:'center'}
                ,{field:'create_time', title: '申请时间', sort: true,align:'center'}
                ,{field:'applicant', title: '申请人', sort: true,align:'center'}
                ,{field:'reason', title: '申请理由',align:'center'}
            ]]
        };
        if($(".userType").val()==0){
            tab.cols[0].push({field:"caozuo",title:"审核",toolbar:'#pg-examine', width:160,align:'center'});
        }
        table.render(tab);

        table.on('tool(fd-examine)',function(obj){
            var data = obj.data;
            $("#pg-app-id").val(data.id);
            if(obj.event == 'pass'){
                examineData(1,data.id); //通过
            }else if(obj.event == 'noPass'){
                examineData(2,data.id); //不通过
            }
        })
    });
}
//弹出窗口
function addMasterData(ts) {
    var menuId=$("#pg-menu-id-add").val();
    var redirectUrl="/redirectMasterDataAdd?menuId="+menuId;
    layui.use('layer', function(){
        var layer = layui.layer;
        parent.layer.open({
            type:2,
            title:ts,
            closeBtn:'1',
            bthAlign:'c',
            area:['550px','420px'],
            align:'center',
            shadeClose:true,
            content:redirectUrl,
            success: function(layero,index){
                $(':focus').blur();
            },
            end:function(){
            }
        })
    });
}

//审核意见 弹出窗口
function examineData(status,id) {
    var str;
    if (status==1){
        str="通过";
    }else if(status==2){
        str="不通过";
    }
    layui.use('layer', function(){
        var redirectExamineUrl="/redirectExamineShow?status="+status+"&id="+id;
        var layer = layui.layer;
        parent.layer.open({
            type:2,
            title:str,
            closeBtn:'1',
            bthAlign:'c',
            area:['500px','300px'],
            align:'center',
            shadeClose:true,
            content:redirectExamineUrl,
            success: function(layero,index){
                $(':focus').blur();
            },
            end:function(){
            },
        })
    });
}

/**
 * 关闭弹框
 * @param data
 * @param msg
 */
function closeOpenShow(data,idName){
    parent.layer.close(parent.layer.index);//关闭弹出窗口
    $(window.parent.document).contents().find("#fd-page-setting")[0].contentWindow.tableReload(idName);
   if (data.success ==true) {
        parent.showTs("添加成功");
    }else {
        parent.showTs("添加失败");
    }
}

function tableReload(id){
    layui.use("table",function () {
        var table = layui.table;

        table.reload(id);
    });

}

////////////////////////////////////////////////////////////////////


layui.use('form',function () {
    var form =layui.form;
    form.verify({ //校验
        optnionValid:function (value,o) {
            if (value.length>200){
                return '字符长度不能超过200'
            }
        },
        dataCode:function (value) {
            if (value.length>50){
                return '字符长度不能超过50'
            }
        },
        contentName:function (value) {
            if (value.length>100){
                return '字符长度不能超过100'
            }
        },
        dataDescrption:function (value) {
            if (value.length>255){
                return '字符长度不能超过255'
            }
        },
        dataReason:function (value) {
            if (value.length>200){
                return '字符长度不能超过200'
            }
        }
    })
    //提交审核通过、不通过意见
    form.on('submit(examineSubmit)',function () {
        if(parent.beforeSend()){
        var updateExaminUrl="/updateExaminStatus"
        var id=$("#pg-app-id").val();
        var status=$("#pg-examine-status").val();
        var auditOptnion=$("#pg-audit-optnion").val();

        $.ajax({
            url : updateExaminUrl,
            type: "POST",
            dataType: 'json',
            async: false,
            data: {
                id:id,
                status:status,
                auditOptnion:auditOptnion
            },
            success: function (data) {
                closeOpenShow(data,"examineMasterDataShow");
            }
        });
        }
        return false;
    });

    //提交新增主数据
    form.on('submit(addMDSubmit)',function () {
        if(parent.beforeSend()) {
            var saveMasterDataUrl = "/saveMasterData"
            var code = $("#pg-code").val();
            var contentName = $("#pg-contentName").val();
            var desc = $("#pg-desc").val();
            var reason = $("#pg-reason").val();
            var menuId = $("#pg-menu-id").val();
            var effect = $("input:radio:checked").val();
            if (code != "" || contentName != "") {
                $.ajax({
                    url: saveMasterDataUrl,
                    type: "POST",
                    dataType: 'json',
                    async: false,
                    data: {
                        code: code,
                        contentName: contentName,
                        desc: desc,
                        effect: effect,
                        menuId: menuId,
                        reason: reason
                    },
                    success: function (data) {
                        closeOpenShow(data, "masterDataTable");
                    }
                });
            }
        }
        return false;
    });


})

