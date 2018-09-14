$(function() {
    initTable();
});

function initTable() {
    var setting = {
        // search: true,//显示搜索框
        url: contextPath+'/user/getList',
        // expandAll: true,
        // expandColumn: "2",
        columns: [{
            field: 'op',
            title: '选择',
            align: 'center',
            valign: 'middle',
            radio: true
        }, {
            title: '序号',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return index + 1;
            }
        }, {
            field: 'id',
            title: 'ID',
            align: 'center',
            valign: 'middle',
            visible: false
        }, {
            field: 'userId',
            title: '用户ID',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: 'username',
            title: '用户名',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: 'sex',
            title: '性别',
            align: 'center',
            valign: 'middle',
            sortable: true,
            // 格式化状态
            formatter: function statusFormatter(value, row, index) {
                if (value === 1) {
                    return '女';
                } else {
                    return '男';
                }
            }
        }, {
            field: 'deptId',
            title: '部门ID',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: 'email',
            title: '邮箱',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: 'mobile',
            title: '电话',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            field: 'status',
            title: '状态',
            align: 'center',
            valign: 'middle',
            sortable: true,
            // 格式化状态
            formatter: function statusFormatter(value, row, index) {
                if (value === 1) {
                    return '<span class="label label-success">正常</span>';
                } else {
                    return '<span class="label label-default">锁定</span>';
                }
            }
        },  {
            field: 'picture',
            title: '作者',
            align: 'center',
            valign: 'middle',
            sortable: true,
            formatter: function(value,row,index){
                if (value) {
                    var u = contextPath + "/static/assets/img/"+value;
                    return '<img  src="'+u+'"  class="img-circle">';
                }
            }
        }, {
            field: 'crateTime',
            title: '创建时间',
            align: 'center',
            valign: 'middle',
            sortable: true,
            //获取日期列的值进行转换
            formatter: function (value, row, index) {
                return timestampDateFormat(value);
            }
        }, {
            field: 'lastLoginTime',
            title: '登录时间',
            align: 'center',
            valign: 'middle',
            sortable: true,
            //获取日期列的值进行转换
            formatter: function (value, row, index) {
                return timestampDateFormat(value);
            }
        }, {
            field: 'remark',
            title: '备注',
            align: 'center',
            valign: 'middle',
            sortable: true
        }]
    };

    $B_table.initTable('table1', setting);
}


//查询
$(document).on('click', "#btn_search",function(){
    searchTable('table1','query_form');
});

//重置
$(document).on('click',"#btn_rest",function () {
    resetForm("query_form");
});

//修改
$(document).on('click',"#btn_edit",function () {
    var row = $("#table1").bootstrapTable('getSelections');
    console.log(row);
});

//新增
$(document).on('click',"#btn_add",function () {

});


