$(function () {
    var data = {
        tableId: "tab",
        pageId: "page",
        pagination: true,
        isAjax: true,
        url: "/permission/list",
        pageList: [1,2,3],
        columns : [
            {field:"id",title:"序号",width:150},
            {field:"userName",title:"编号",width:150},
            {field:"password",title:"密码",width:150},
            {field:"type",title:"内容名称",width:150},
            {field:"createTime",title:"描述",width:150},
            {field:"updateTime",title:"是否生效",width:150},
            {field:"updateTime",title:"主数据",width:150},
            {field:"updateTime",title:"主数据库",width:150},
            {field:"updateTime",title:"申请时间",width:150},
            {field:"updateTime",title:"申请人",width:150},
            {field:"updateTime",title:"申请理由",width:150},
            {field:"updateTime",title:"审核状态",width:150},
            {field:"updateTime",title:"审核意见",width:150},
        ]
    };
    setTable(data);
});