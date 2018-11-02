$(function () {
    var currentPage = 1;
    var pageSize = 2;
    showPageAndTable(currentPage,pageSize)

});
function showPageAndTable(currentPage,pageSize){
    layui.use(['table','laypage'], function(){
        var table = layui.table;
        laypage = layui.laypage;
        table.render({
            elem: '#log-table'
            ,url:'/loginform/showLogObject?currentPage='+currentPage+'&pageSize='+pageSize
            ,width:1340
            ,skin:'line'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'id', title: '序号', sort: true}
                ,{field:'code', title: '编号', sort: true}
                ,{field:'conent_name', title: '内容名称'}
                ,{field:'description', title: '描述'}
                ,{field:'effect', title: '是否生效', sort: true} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                ,{field:'zsjname', title: '主数据'}
                ,{field:'zsjkname', title: '主数据库'}
                ,{field:'create_time', title: '申请时间', sort: true}
                ,{field:'applicant',title: '申请人'}
                ,{field:'reason', title: '申请理由'}
                ,{field:'status', title: '审核状态', sort: true}
                ,{field:'audit_optnion',  title: '审核意见'}
                ,{field:'review_time',  title: '审核时间'}

            ]]
            ,page:false
            ,done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                laypage.render({
                    elem:'log-laypage'
                    ,count:count
                    ,curr:currentPage
                    ,limit:pageSize
                    ,layout: ['count', 'prev', 'page', 'next', 'skip']
                    ,jump:function (obj,first) {
                        if(!first){
                            curnum = obj.curr;
                            limitcount = obj.limit;
                            showPageAndTable(curnum,limitcount)
                        }
                    }
                })
            }
        });
    });
}