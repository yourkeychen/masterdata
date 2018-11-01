$(function () {
    var currentPage = 1;
    var pageSize = 1;
    showPageAndTable(currentPage,pageSize)

});
function showPageAndTable(currentPage,pageSize){
    layui.use(['table','laypage'], function(){
        var table = layui.table;
        laypage = layui.laypage;
        table.render({
            elem: '#test'
            ,url:'/loginform/showLogObject?currentPage='+currentPage+'&pageSize='+pageSize
            ,width:1300
            ,skin:'line'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'id', width:80, title: '序号'}
                ,{field:'code', width:80, title: '编号'}
                ,{field:'conent_name', width:130, title: '内容名称'}
                ,{field:'desc', width:80, title: '描述'}
                ,{field:'effect', title: '是否生效', width: 80, minWidth: 100} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                ,{field:'zsjname', title: '主数据',width:80}
                ,{field:'zsjkname', title: '主数据库',width:80}
                ,{field:'create_time', title: '申请时间'}
                ,{field:'applicant', width:137, title: '申请人'}
                ,{field:'reason', width:137, title: '申请理由'}
                ,{field:'status', width:137, title: '审核状态'}
                ,{field:'audit_optnion', width:137, title: '审核意见'}
            ]]
            ,page:false
            ,done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                console.log(res);
                console.log(curr);
                console.log(count);
                laypage.render({
                    elem:'laypage'
                    ,count:count
                    ,curr:currentPage
                    ,limit:pageSize
                    ,layout: ['count', 'prev', 'page', 'next', 'skip']
                    ,jump:function (obj,first) {
                        if(!first){
                            curnum = obj.curr;
                            limitcount = obj.limit;
                            //console.log("curnum"+curnum);
                            //console.log("limitcount"+limitcount);
                            //layer.msg(curnum+"-"+limitcount);
                            //productsearch(productGroupId,curnum,limitcount);
                            showPageAndTable(curnum,limitcount)
                        }
                    }
                })
            }
        });
    });
}