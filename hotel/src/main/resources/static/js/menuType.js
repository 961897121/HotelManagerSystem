var vm = new Vue({
    el:'#app',
    data(){
        return{
            pageSize: 10,
            pageNum: 1,
            selRowData:{
                id:'',
                typeId:'',
                typeName:'',
                typePrice:''
            },
            selRowData1:{
                id:null,
                typeId:null,
                typeName:null,
                typePrice:null
            },
            tableDate:'',
            visible:false,
            loading:false,
            centerDialogVisible:false,
            centerDialogVisible1:false,
        }
    },
    mounted:function(){
        this.getTableDate();
    },
    methods:{
        //添加价格类型
        save:function(){
            var _that = this;
            var _url = ctx + 'menu/saveType.do';
            $.ajax({
                url: _url,
                type: 'post',
                contentType: "application/json;charset=UTF-8",
                data:  JSON.stringify(_that.selRowData1),
                success: function (data) {
                    if (data == true) {
                        _that.loading = false;
                        _that.showSuccess('添加价格类型成功');
                        _that.centerDialogVisible1 = false;
                        _that.getTableDate();
                    } else {
                        _that.loading = false;
                        _that.showError('添加价格类型失败');
                        _that.getTableDate();
                    }
                },
                error: function (data) {
                    _that.loading = false;
                    _that.showError('网络异常');
                }
            })
        },
        //开启添加价格类型对话框
        toAddDialog:function(){
            var _that = this;
            _that.centerDialogVisible1 = true;
        },
        //删除菜单价格类型
        delRow:function(rowData){
            var _url = ctx + 'menu/deleteType.do';
            var _that = this;
            $.ajax({
                url: _url,
                type: 'post',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(rowData),
                success: function (data) {
                    if (data == true) {
                        _that.loading = false;
                        _that.showSuccess('删除菜单类型成功');
                        _that.centerDialogVisible = false;
                        _that.visible = false;
                        _that.getTableDate();
                    } else {
                        _that.loading = false;
                        _that.showError('删除菜单类型失败');
                        _that.visible = false;
                    }
                },
                error: function (data) {
                    _that.loading = false;
                    _that.showError('网络异常');
                }
            })
        },
        //查看价格类型详情
        editRow:function(rowData){
            var _that = this;
            //开启编辑菜单弹框
            _that.centerDialogVisible = true;
            _that.selRowData = rowData;
        },
        //获取价格类型列表
        getTableDate:function(){
            var _that = this;
            var _data = {
                pageNum:_that.pageNum,
                pageSize: _that.pageSize,
                menuType:_that.selRowData
            };
            var _url = ctx + 'menu/selectTypeList.do';
            $.ajax({
                url : _url,
                type : 'post',
                data : _data,
                success:function (data) {
                    _that.tableDate = data;
                },
                error:function (data) {
                    _that.showError("获取菜单价格信息失败");
                }
            })
        },
        //显示成功提示消息
        showSuccess(msg) {
            this.$message({
                message: msg,
                type: 'success'
            });
        },
        //显示失败提示消息
        showError(msg) {
            this.$message.error(msg);
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.getTableDate();
        },
        handleCurrentChange(val) {
            this.pageNum = val;
            this.getTableDate();
        },
    }
});