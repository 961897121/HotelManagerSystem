var vm = new Vue({
    el: '#app',
    data() {
        return {
            selRowData: {
                userId: '',
                userName: '',
                loginName: '',
                loginPwd: '',
                age: '',
                sex: '',
                identity: '',
                createTime: '',
                userLock: '',
                tel: '',
                addr: '',
                qq: '',
                weChat: ''
            },
            activeIndex1: '1',
            centerDialogVisible: false,
        }
    },
    mounted: function(){
        this.getCurrentUser();
    },
    methods: {
        chooseIframePage:function(pageName){
            switch (pageName) {
                case '菜单列表':
                    $("#myFrame").attr('src' , '/menuInfo.do');
                    break;
                case '个人信息':
                    $("#myFrame").attr('src' , '/userInfo.do');
                    break;
                case '厨师列表':
                    $("#myFrame").attr('src','/cookerInfo.do');
                    break;
                case '价格管理':
                    $("#myFrame").attr('src','/menuTypeInfo.do');
                    break;
                case '订单管理':
                    $("#myFrame").attr('src','/order.do');
                    break;
                default:break;
            }
        },
        //退出登录的方法
        logout:function(){
            var _that = this;
            var _url = ctx + 'user/logout.do';
            $.ajax({
                url:_url,
                type:'post',
                success:function (data) {
                    _that.showSuccess("正在退出...");
                    window.location.href = ctx + "login.do";
                },
                error:function () {
                    _that.showError("退出失败");
                }
            })
        },
        //获取用户信息
        getCurrentUser: function () {
            var _that = this;
            var _url = ctx + 'getCurrentUser.do';
            $.ajax({
                url: _url,
                type: 'post',
                success: function (data) {
                    _that.selRowData = data;
                },
                error: function (data) {
                    _that.showError("连接失败");
                }
            })
        },
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        //显示成功提示消息
        showSuccess(msg) {
            this.$message({
                message: msg,
                type: 'success'
            });
        }
    }
});