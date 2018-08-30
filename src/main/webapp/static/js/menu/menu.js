
$(function() {
    var setting = {
        callback: {
            onClick: zTreeOnClick
        },
        data : {
            key : {
                name: "menuName"
            },
            simpleData : {
                enable: true,
                idKey: "menuId",
                pIdKey: "parentId",
                rootPId: 0
            }
        },
        //页面上的显示效果
        view: {
            addHoverDom: addHoverDom, //当鼠标移动到节点上时，显示用户自定义控件
            removeHoverDom: removeHoverDom //离开节点时的操作
        },
        edit: {
            // enable: true, //单独设置为true时，可加载修改、删除图标
            editNameSelectAll: true
            // showRemoveBtn: showRemoveBtn,
            // showRenameBtn: showRenameBtn
        }
    };
    var treeNodes;
    $.ajax({
        type : "post",
        url : contextPath+'/menu/getMenuTree',
        success : function(data) {
            treeNodes = data;
            $.fn.zTree.init($("#menuTree"), setting, treeNodes);
            var treeObj = $.fn.zTree.getZTreeObj("menuTree");
            treeObj.expandAll(true);
        },
        error : function() {
            window.toastr.error("请求失败","",{"positionClass": "toast-top-center"});
        }
    });
});

//菜单树点击事件
function zTreeOnClick(event, treeId, treeNode) {
    if (!treeNode.menuId) {
        resetForm("menu-form");
        $("#parentId").val(treeNode.parentId);
        return;
    }
    $.ajax({
        type : "post",
        data: {
            "menuId" : treeNode.menuId
        },
        url : contextPath+'/menu/getMenuOne',
        success : function(data) {
            $("#menuId").val(data.menuId);
            $("#parentId").val(data.parentId);
            $("#menuName").val(data.menuName);
            $("#url").val(data.url);
            $("#level").val(data.level);
            $("#icon").val(data.icon);
            $("#mid").val(data.mid);
        },
        error : function() {
            window.toastr.error("请求失败","",{"positionClass": "toast-top-center"});
        }
    });
}
//鼠标移入显示“+”按钮
function addHoverDom(treeId, treeNode) {
    debugger;
    var sObj = $("#" + treeNode.tId + "_span"); //获取节点信息
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;

    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='add node' onfocus='this.blur();'></span>"; //定义添加按钮
    sObj.after(addStr); //加载添加按钮
    var btn = $("#addBtn_"+treeNode.tId);

    //绑定添加事件，并定义添加操作
    if (btn) {
        btn.bind("click", function(){
            var zTree = $.fn.zTree.getZTreeObj("menuTree");
            var newNode = zTree.addNodes(treeNode, {menuId:'', parentId:treeNode.menuId, menuName: '新增节点'}); //页面上添加节点
            zTree.selectNode(newNode); //让新添加的节点处于选中状态
        });
    }
};
//鼠标移出，移除“+”按钮
function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
};

//保存菜单
function fncAdd() {
    
}



// $(function() {
//     // initTable();
//
//
// var $tb = $('#table1');
//
// function initTable() {
//     var setting = {
//         // search: true,//显示搜索框
//         url: contextPath+'/menu/getList',
//         height: 850,
//         striped: true,
//         sidePagination: 'server',
//         idField: 'id',
//         // expandAll: true,
//         // expandColumn: "1",
//         columns: [{
//             field: '',
//             checkbox: true
//         }, {
//             field: 'name',
//             title: '菜单名称',
//             align: 'center',
//             valign: 'middle',
//         }, {
//             field: 'status',
//             title: '状态',
//             sortable: true,
//             align: 'center',
//             formatter: function (value, row, index) {
//                 if (value === 1) {
//                     return '<span class="label label-success">正常</span>';
//                 } else {
//                     return '<span class="label label-default">锁定</span>';
//                 }
//             }
//         }, {
//             field: 'permissionValue',
//             title: '权限值',
//             sortable: true,
//             align: 'center',
//             valign: 'middle',
//         }],
//         treeShowField: 'name',
//         parentIdField: 'pid',
//         onLoadSuccess: function(data) {
//             console.log(data);
//             // jquery.treegrid.js
//             $tb.treegrid({
//                 // initialState: 'collapsed',
//                 treeColumn: 1,
//                 // expanderExpandedClass: 'glyphicon glyphicon-minus',
//                 // expanderCollapsedClass: 'glyphicon glyphicon-plus',
//                 onChange: function() {
//                     // $tb.bootstrapTable('resetWidth');
//                 }
//             });
//         }
//     };
//
//     $tb.bootstrapTable(setting);
//     // $B_table.initTable('table1', setting);
// }
//
//
// //查询
// $(document).on('click', "#btn_search",function(){
//     searchTable('table1','query_form');
// });
//
// //重置
// $(document).on('click',"#btn_rest",function () {
//     resetForm("query_form");
// });
//
// //修改
// $(document).on('click',"#btn_edit",function () {
//     var row = $("#table1").bootstrapTable('getSelections');
//     console.log(row);
// });
//
//
//
//
