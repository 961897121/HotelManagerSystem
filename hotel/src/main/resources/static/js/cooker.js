var vm = new Vue({
    el:'#app',
    data(){
        return{
            pageSize: 10,
            pageNum: 1,
            selRowData:{
                id:null,
                cookName:null,
                cookImagePath:null,
                age:null,
                sex:null,
                sarlary:null,
                status:null,
                menuList:null,
                menuListStr:null
            },
            selRowData1:{
                id:null,
                cookName:null,
                cookImagePath:null,
                age:null,
                sex:null,
                sarlary:null,
                status:null,
                menuList:null,
                menuListStr:null
            },
            tableDate:'',
            visible:false,
            loading:false,
            centerDialogVisible:false,
            centerDialogVisible1:false,
            centerDialogVisible2:false,
            file:'',
            fileList: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}, {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}]
        }
    },
    mounted:function(){
        this.getTableDate();
    },
    methods:{
        showUpLoad:function(){
            var _that = this;
            _that.centerDialogVisible2 = true;
        },
        //文件上传
        submitUpload:function(event){
            event.preventDefault();
            this.$refs.upload.submit();
            this.selRowData.cookImagePath = 'E:\\images\\' + this.file;
            //更新厨师信息
            this.changeCooker();
            //关闭弹窗
            this.centerDialogVisible2 = false;
        },
        //删除厨师
        delRow:function(rowData){
            var _url = ctx + 'cooker/delete.do';
            var _that = this;
            $.ajax({
                url: _url,
                type: 'post',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(rowData),
                success: function (data) {
                    if (data == true) {
                        _that.loading = false;
                        _that.showSuccess('删除菜单成功');
                        _that.centerDialogVisible = false;
                        _that.visible = false;
                        _that.getTableDate();
                    } else {
                        _that.loading = false;
                        _that.showError('删除菜单失败');
                        _that.visible = false;
                    }
                },
                error: function (data) {
                    _that.loading = false;
                    _that.showError('网络异常');
                }
            })
        },
        //添加厨师
        save:function(){
            var _that = this;
            var _url = ctx + 'cooker/save.do';
            $.ajax({
                url: _url,
                type: 'post',
                contentType: "application/json;charset=UTF-8",
                data:  JSON.stringify(_that.selRowData1),
                success: function (data) {
                    if (data == true) {
                        _that.loading = false;
                        _that.showSuccess('添加厨师成功');
                        _that.centerDialogVisible1 = false;
                    } else {
                        _that.loading = false;
                        _that.showError('添加厨师失败');
                        _that.getTableDate();
                    }
                },
                error: function (data) {
                    _that.loading = false;
                    _that.showError('网络异常');
                }
            })
        },
        //开启添加厨师对话框
        toAddDialog:function(){
            var _that = this;
            _that.centerDialogVisible1 = true;
        },
        changeCooker:function(){
            var _that = this;
            var _url = ctx + 'cooker/update.do';
            $.ajax({
                url: _url,
                type: 'post',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(_that.selRowData),
                success: function (data) {
                    if (data == true) {
                        _that.loading = false;
                        _that.showSuccess('修改菜单成功');
                        _that.getTableDate();
                        _that.centerDialogVisible = false;
                    } else {
                        _that.loading = false;
                        _that.showError('修改菜单失败');
                    }
                },
                error: function (data) {
                    _that.loading = false;
                    _that.showError('网络异常');
                }
            })
        },
        //查看厨师详情
        editRow:function(rowData){
            var _that = this;
            //开启编辑菜单弹框
            _that.centerDialogVisible = true;
            _that.selRowData = rowData;
            //配置img路径
            // $("img").attr("src" , '/image/' + rowData.menuName + '.jpg');
            if (rowData.cookImagePath != null && rowData.cookImagePath != ''){
                var path = rowData.cookImagePath.split(":")[1];
                $("img").attr("src" , path);
            }
        },
        //获取菜单列表
        getTableDate:function(){
            var _that = this;
            var _data = {
                pageNum:_that.pageNum,
                pageSize: _that.pageSize,
                cooker:_that.selRowData
            };
            var _url = ctx + 'cooker/queryList.do';
            $.ajax({
                url : _url,
                type : 'post',
                data : _data,
                success:function (data) {
                    _that.tableDate = data;
                },
                error:function (data) {
                    _that.showError("获取厨师信息失败");
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
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        handleChange(file) {
            console.log(file.name);
            this.file = file.name;
        },
        handleExceed(files, fileList) {
            this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
        },
        beforeRemove(file, fileList) {
            return this.$confirm(`确定移除 ${ file.name }？`);
        }
    }
});