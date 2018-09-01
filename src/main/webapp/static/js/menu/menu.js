
$(function() {
    var setting = {
        callback: {
            onClick: zTreeOnClick
        },
        data : {
            key : {
                name: "menuName",
                level: "level"
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

    //添加校验
    $("#menu-form").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            menuName: {
                validators: {
                    notEmpty: {
                        message: '请输入菜单名称'
                    }
                }
            }
        }
    });
});

//菜单树点击事件
function zTreeOnClick(event, treeId, treeNode) {
    debugger;
    if (!treeNode.menuId) {
        resetForm("menu-form");
        $("#parentId").val(treeNode.parentId);
        $("#level").selectpicker('val',treeNode.level);
        return;
    }
    $.ajax({
        type : "post",
        data: {
            "menuId" : treeNode.menuId
        },
        url : contextPath+'/menu/getMenuOne',
        success : function(d) {
            debugger;
            if (d.code == 'ok') {
                $("#menuId").val(d.data.menuId);
                $("#parentId").val(d.data.parentId);
                $("#menuName").val(d.data.menuName);
                $("#url").val(d.data.url);
                $("#level").selectpicker('val',d.data.level);
                $("#icon").val(d.data.icon);
                $("#mid").val(d.data.mid);
            }else {
                window.toastr.error(d.msg,"",{"positionClass": "toast-top-center"});
            }
        },
        error : function() {
            window.toastr.error("请求失败","",{"positionClass": "toast-top-center"});
        }
    });
}
//鼠标移入显示“+”按钮
function addHoverDom(treeId, treeNode) {
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
    if (!$("#parentId").val()) {
        return;
    }
    $("#menu-form").bootstrapValidator('validate');//提交验证
    if ($("#menu-form").data('bootstrapValidator').isValid()) {
        var obj = $("#menu-form").serializeObject();
        $.ajax({
            type : "post",
            data: {"json":JSON.stringify(obj)},
            url : contextPath+'/menu/addMenu',
            success : function(text) {
                if (text.code == 'ok') {
                    window.toastr.success(text.msg,"",{"positionClass": "toast-top-center"});
                    var zTree = $.fn.zTree.getZTreeObj("menuTree");
                    var nodes = zTree.getSelectedNodes();
                    nodes[0].menuId=text.data.menuId;
                    nodes[0].menuName=text.data.menuName;
                    nodes[0].parentId=text.data.parentId;
                    zTree.updateNode(nodes[0]);
                }else {
                    window.toastr.error(text.msg,"",{"positionClass": "toast-top-center"});
                }
            },
            error : function() {
                window.toastr.error("系统异常","",{"positionClass": "toast-top-center"});
            }
        });
    }
}


/**
 * 刷新当前节点
 */
function refreshNode() {
    /*根据 treeId 获取 zTree 对象*/
    var zTree = $.fn.zTree.getZTreeObj("menuTree"),
    type = "refresh",
    silent = false,
    /*获取 zTree 当前被选中的节点数据集合*/
    nodes = zTree.getSelectedNodes();
    /*强行异步加载父节点的子节点。[setting.async.enable = true 时有效]*/
    zTree.reAsyncChildNodes(nodes[0], type, silent);
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
