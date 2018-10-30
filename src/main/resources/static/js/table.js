var tableData = {
    width: '100%',                      // table宽度
    height: '100%',                     // table高度
    method: 'POST',                     // ajax请求类型
    url: '',                            // ajax请求路径
    queryParams:{},                     // ajax请求参数
    columns: [],                        // 行属性
    pagination: false,                  // 是否分页
    pageList: [10,20,30,40,50],         // 分页参数
    tableList: [],                      // 表值
    isAjax: false,                      // 是否启用ajax请求
    tableId: "",                        // 表id
    tableClass: "",                     // 表class
    thClass: "",                        // 表头class
    trClass: "",                        // 表行class
    pageId: "",                         // 分页divId
    pageCount: "",                      // 分页条数
    pageCurr: 1,                        // 当前页页数
    pageNumber: ""                      // 当前页条数
}

function setTable(data){
    for(var opt in data){
        eval("tableData."+opt + " = data."+opt);
    }
    tableData.pageCurr = 1;
    tableData.pageNumber = tableData.pageList[0];
    innerHtml();
    if(tableData.pagination) layPage();
}

function innerHtml(){
    if(tableData.isAjax) setTableList(tableData.pageList[0],1);
    var html =setTh();
    html += setTr();
    $("#"+tableData.tableId).html(html);
    $("#"+tableData.tableId).height= tableData.height;
    $("#"+tableData.tableId).width = tableData.width;
    $("#"+tableData.tableId).addClass(tableData.tableClass);
}

function setTh(){
    var html = "";
    if(tableData.thClass != null) html += "<tr class='psy-thd "+tableData.thClass+"'>";
    else html += "<tr class='psy-thd' >";
    for(var column in tableData.columns){
        html += "<th>"+tableData.columns[column].title + "</th>";
    }
    html += "</tr>";
    return html;
}

function setTr(){
    var html = "";
    for(var i = 0 ; i< tableData.tableList.length;i++){
        if(tableData.trClass != null) html += "<tr class='psy-tr "+tableData.trClass+"'>";
        else html += "<tr class='psy-tr' >";
        for(var column in tableData.columns){
            var field = tableData.columns[column].field;
            html += "<td";
            if(tableData.columns[column].height){
                html += " height = '"+tableData.columns[column].height+"'";
            }
            if(tableData.columns[column].width){
                html += " width = '"+tableData.columns[column].width+"'";
            }
            if(tableData.columns[column].class){
                html += " class = '"+tableData.columns[column].class+"'";
            }
            html +=">";
            if(tableData.columns[column].formatter){
                html += tableData.columns[column].formatter(tableData.tableList[i][field],tableData.tableList[i],i);
            }else{
                html += tableData.tableList[i][field];
            }
            html += "</td>";
        }
        html += "</tr>";
    }
    return html;
}

function setTableList(){
    $.ajax({
        url : tableData.url,
        type: tableData.method,
        data: tableData.pagination?Object.assign(tableData.queryParams,{"pageNum":tableData.pageNumber,"pageStart":tableData.pageNumber*(tableData.pageCurr-1)}):tableData.queryParams,
        async: false,
        dataType: 'json',
        success: function (data) {
            tableData.tableList = data.list;
            tableData.pageCount = data.count;
        }
    });
}

function layPage(){
    layui.use(['laypage','layer'],function () {
        var laypage = layui.laypage,
            layer = layui.layer;
        laypage.render({
            elem: tableData.pageId,
            count: tableData.pageCount,
            layout: ['count','prev','page','next','limit','skip'],
            limit: tableData.pageList[0],
            limits: tableData.pageList,
            jump: function (obj) {
                //console.dir(obj);
                tableData.pageCurr = obj.curr;
                tableData.pageNumber = obj.limit;
                innerHtml();
            }
        });
    })
}

function getDateString(date){
    alert(date instanceof Date);
    var year = date.getFullYear(),
        month = date.getMonth()+1,
        day = date.getDate(),
        h = date.getHours(),
        m = date.getMinutes(),
        s = date.getSeconds();
    return year+"-"+month+"-"+day+"  "+h+":"+m+":"+s;
}