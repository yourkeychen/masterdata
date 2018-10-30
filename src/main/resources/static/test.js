$(function () {
   var data = {
       tableId: "tab",
       columns : [
           {field:"xuhao",title:"序号",width:150},
           {field:"xitongbiaoshi",title:"系统标识",width:150},
           {field:"xitongmingcheng",title:"系统名称",width:150},
           {field:"xitongmiaoshu",title:"系统描述",width:150},
           {field:"xitongip",title:"系统ip",width:150},
           {field:"caozuo",title:"操作",width:150,formatter: function (value,row,index) {
                   var e ="<button class=\"layui-btn layui-btn-sm\">修改</button>&nbsp&nbsp&nbsp<button class=\"layui-btn layui-btn-sm\">删除</button>";
                   return e;
               }},
       ],
       tableList: [
           {xuhao:1, xitongbiaoshi:"2", xitongmingcheng:"3", xitongmiaoshu:"4" ,xitongip:"5"},
           {xuhao:1, xitongbiaoshi:"2", xitongmingcheng:"3", xitongmiaoshu:"4" ,xitongip:"5"},
           {xuhao:1, xitongbiaoshi:"2", xitongmingcheng:"3", xitongmiaoshu:"4" ,xitongip:"5"},
           {xuhao:1, xitongbiaoshi:"2", xitongmingcheng:"3", xitongmiaoshu:"4" ,xitongip:"5"},
           {xuhao:1, xitongbiaoshi:"2", xitongmingcheng:"3", xitongmiaoshu:"4" ,xitongip:"5"}
       ]
   };
    setTable(data);
});