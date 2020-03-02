var vm = new Vue({
    el:'#app',
    data(){
        return{
            selRowData:{
                id:'',
                level:'',
                menuList:'',
                totalPrice:'',
                createTime:'',
                appraise:'',
                appraiseTime:'',
                cookerList:'',
                userId:'',
                menus:'',
                cookers:''
            },
            pageSize: 10,
            pageNum: 1,
            tableDate:'',
            labelPosition: 'left',
            loading:false,
            centerDialogVisible:false,
            colors: ['#99A9BF', '#F7BA2A', '#FF9900']  // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
        }
    },
    mounted(){
        this.getTableDate();
    },
    methods:{
        //获取菜单列表
        getTableDate:function(){
            var _that = this;
            var _data = {
                pageNum:_that.pageNum,
                pageSize: _that.pageSize,
                orderInfo:_that.selRowData
            };
            var _url = ctx + 'order/queryList.do';
            $.ajax({
                url : _url,
                type : 'post',
                data : _data,
                success:function (data) {
                    //时间转换
                    for (var i = 0 ; i < data.list.length ; i++){
                        if (data.list[i].appraiseTime != null){
                            data.list[i].appraiseTime = data.list[i].appraiseTime.split('.')[0];
                        }
                        if (data.list[i].createTime != null){
                            data.list[i].createTime = data.list[i].createTime.split('.')[0];
                        }
                    }
                    _that.tableDate = data;
                },
                error:function (data) {
                    _that.showError("获取订单信息失败");
                }
            })
        },
        //查看订单详情
        showDetail:function(rowData){
            var _that = this;
            _that.centerDialogVisible = true;
            var _url = 'order/selectOne.do';
            $.ajax({
                url:_url,
                contentType: "application/json;charset=UTF-8",
                data:JSON.stringify(rowData.id),
                method:'post',
                success:function (data) {
                    _that.showSuccess('查找订单信息成功');
                    _that.selRowData = data;
                },
                error:function (data) {
                    _that.showError('查找订单信息失败');
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
        }
    }
});