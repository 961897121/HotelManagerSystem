var vm = new Vue({
    el: '#app',
    data() {
        return {
            selRowData: {
                id: null,
                menuName: null,
                menuDescribe: null,
                status: null,
                cookId: null,
                menuImagePath: null,
                typeName: null,
                typePrice: null,
                typeId:null,
                sort: null
            },
            cooker: {
                id: '',
                cookName: '',
                cookImagePath: '',
                age: '',
                sex: '',
                sarlary: '',
                status: '',
                menuList: '',
                menuListStr: ''
            },
            menuType: {
                id: '',
                typeName: '',
                typePrice: ''
            },
            tableDate: '',
            tableDate1: '',
            tableDate2: '',
            loading: false,
            centerDialogVisible: false,
            centerDialogVisible1: false,
            centerDialogVisible2: false,
            pageSize: 10,
            pageNum: 1,
            pageSize1: 10,
            pageNum1: 1,
            visible: false,
            // fileList: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}, {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}],
            fileList: [],
            file:'',
            cookId:'',
            flag:false
        }
    },
    mounted: function () {
        this.getTableDate();
    },
    methods: {
        //获取菜单类型价格
        getTypePrice:function(){
            var _that = this;
            _that.menuType.typeName = _that.selRowData.typeName;
            var _url = ctx + 'menu/menuType.do';
            $.ajax({
                url: _url,
                type: 'post',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(_that.menuType),
                success: function (data) {
                    _that.loading = false;
                    _that.showSuccess('查找菜单价格成功');
                    _that.selRowData.typeName = data.typeName;
                    _that.selRowData.typePrice = data.typePrice;
                    _that.selRowData.typeId = data.id;
                },
                error: function (data) {
                    _that.loading = false;
                    _that.showError('网络异常');
                }
            })
        },
        delCooker:function(rowData){
            var _that = this;
            var src = _that.cookId.indexOf(rowData.id);
            //如果该id是字符串中间 2,3,4的3
            if (_that.cookId.charAt(src + 1) == ',' && _that.cookId.charAt(src - 1) == ','){
                var idStr =  _that.cookId.substr(0,src - 1) + _that.cookId.substr(src + 1 , _that.cookId.length);
                console.log(idStr);
            }
            //如果改id是结尾 2,3,4的2
            else if (_that.cookId.charAt(src + 1) == ',' && _that.cookId.charAt(src - 1) != ',') {
                var idStr =  _that.cookId.substr(src + 2,_that.cookId.length);
                console.log(idStr);
            }
            //如果改id是结尾 2,3,4的4
            else if (_that.cookId.charAt(src + 1) != ',' && _that.cookId.charAt(src - 1) == ',') {
                var idStr =  _that.cookId.substr(0,src - 1);
                console.log(idStr);
            }
            _that.selRowData.cookId = idStr;
            //刷新数据
            _that.getCookTable(_that.selRowData);
        },
        //文件上传
        submitUpload:function(event){
            event.preventDefault();
            this.$refs.upload.submit();
            this.selRowData.menuImagePath = 'E:\\images\\' + this.file;
        },
        //获取菜单类型列表
        getTypeTableDate: function (id) {
            var _that = this;
            var _url = ctx + 'menu/selectTypeByTypeId.do';
            $.ajax({
                url: _url,
                type: 'post',
                data: JSON.stringify(id),
                success: function (data) {
                    _that.menuType = data;
                },
                error: function (data) {
                    _that.showError("获取菜单类型信息失败");
                }
            })
        },
        //添加菜单
        save: function () {
            var _that = this;
            //上传图片
            _that.submitUpload(event);
            var _url = ctx + 'menu/save.do';
            $.ajax({
                url: _url,
                type: 'post',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(_that.selRowData),
                success: function (data) {
                    if (data == true) {
                        _that.loading = false;
                        _that.showSuccess('添加菜单成功');
                        _that.centerDialogVisible2 = false;
                        _that.getTableDate();
                    } else {
                        _that.loading = false;
                        _that.showError('添加菜单失败');
                        _that.getTableDate();
                    }
                },
                error: function (data) {
                    _that.loading = false;
                    _that.showError('网络异常');
                }
            })
        },
        //开启添加菜单对话框
        toAddDialog: function () {
            var _that = this;
            _that.centerDialogVisible2 = true;
            _that.selRowData.cookId = null;
        },
        //选择厨师
        chooseCooker: function (rowData) {
            var _that = this;
            if (_that.selRowData.cookId == null){
                _that.selRowData.cookId =  rowData.id + ',';
            } else{
                _that.selRowData.cookId = _that.selRowData.cookId + rowData.id + ',';
            }
            _that.getCookTable(_that.selRowData);
            _that.centerDialogVisible1 = false;
        },
        //获取厨师列表
        getTableDate1: function () {
            var _that = this;
            var _data = {
                pageNum: _that.pageNum1,
                pageSize: _that.pageSize1,
                cooker: _that.cooker
            };
            var _url = ctx + 'cooker/queryList.do';
            $.ajax({
                url: _url,
                type: 'post',
                data: _data,
                success: function (data) {
                    _that.tableDate1 = data;
                },
                error: function (data) {
                    _that.showError("获取厨师信息失败");
                }
            })
        },
        //删除菜单
        delRow: function (rowData) {
            var _url = ctx + 'menu/delete,do';
            var _that = this;
            $.ajax({
                url: _url,
                type: 'post',
                data: rowData,
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
        //开启选择厨师
        showCooker: function (rowData) {
            var _that = this;
            _that.centerDialogVisible1 = true;
            _that.getTableDate1();
        },
        //修改菜单
        changeMenu: function () {
            var _that = this;
            //提交图片
            if (_that.flag){
                _that.submitUpload(event);
            }

            var _url = ctx + 'menu/update.do';
            $.ajax({
                url: _url,
                type: 'post',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(_that.selRowData),
                success: function (data) {
                    if (data == true) {
                        _that.loading = false;
                        _that.showSuccess('修改菜单成功');
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
        //查看菜单详情
        editRow: function (rowData) {
            var _that = this;
            //开启编辑菜单弹框
            _that.centerDialogVisible = true;
            _that.selRowData = rowData;
            _that.cookId = _that.selRowData.cookId;

            //配置img路径
            if (rowData.menuImagePath != null) {
                var path = rowData.menuImagePath.split(":")[1];
                $("img").attr("src", path);
            }
            if (rowData.id != null){
                _that.getCookTable(rowData);
            }
        },
        //获取厨师信息放入数组
        getCookTable: function (rowData) {
            var _that = this;
            if ( rowData.cookId != null){
                $.ajax({
                    url: ctx + 'menu/getCookerList.do',
                    type: 'post',
                    contentType: "application/json;charset=UTF-8",
                    data: rowData.cookId,
                    success: function (data) {
                        _that.loading = false;
                        _that.tableDate2 = data;
                        _that.showSuccess('获取厨师信息成功');
                    },
                    error: function (data) {
                        _that.loading = false;
                        _that.showError('网络异常');
                    }
                })
            }
        },
        //获取菜单列表
        getTableDate: function () {
            var _that = this;
            var _data = {
                pageNum: _that.pageNum,
                pageSize: _that.pageSize,
                menu: _that.selRowData
            };
            var _url = ctx + 'menu/queryList.do';
            $.ajax({
                url: _url,
                type: 'post',
                data: _data,
                success: function (data) {
                    for (var i = 0 ; i < data.list.length ; i++){
                        if (data.list[i].menuImagePath != null){
                            data.list[i].menuImagePath = data.list[i].menuImagePath.substr(data.list[i].menuImagePath.indexOf("\\") , data.list[i].menuImagePath.length);
                        }
                    }
                    _that.tableDate = data;
                },
                error: function (data) {
                    _that.showError("获取菜单信息失败");
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
        handleSizeChange1(val) {
            this.pageSize1 = val;
            this.getTableDate1();
        },
        handleCurrentChange1(val) {
            this.pageNum1 = val;
            this.getTableDate1();
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
            this.file = ''
        },
        handlePreview(file) {
            console.log(file);
            this.flag = true;
        },
        handleChange(file) {
            console.log(file.name);
            this.file = file.name;
        },
        resetEdit:function(){
            var _that = this;
            _that.file = '';
            _that.selRowData = {
                id: '',
                menuName: '',
                menuDescribe: '',
                status: '',
                cookId: '',
                menuImagePath: '',
                typeName: '',
                typePrice: '',
                sort: ''
            };
            _that.cooker = {
                id: '',
                cookName: '',
                cookImagePath: '',
                age: '',
                sex: '',
                sarlary: '',
                status: '',
                menuList: '',
                menuListStr: ''
            };
            _that.tableDate2 = '';
        },
        resetAdd: function () {
            var _that = this;
            _that.file = '';
            _that.selRowData = {
                id: '',
                menuName: '',
                menuDescribe: '',
                status: '',
                cookId: '',
                menuImagePath: '',
                typeName: '',
                typePrice: '',
                sort: ''
            };
            _that.cooker = {
                id: '',
                cookName: '',
                cookImagePath: '',
                age: '',
                sex: '',
                sarlary: '',
                status: '',
                menuList: '',
                menuListStr: ''
            };
            _that.tableDate2 = '';
        }
    }
});