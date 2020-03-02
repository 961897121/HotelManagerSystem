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
            confirmPwd: '',
            newPwd: '',
            loading: false,
            centerDialogVisible: false,
            pageSize: 10,
            pageNum: 1
        }
    },
    mounted: function () {
        this.getCurrentUser();
    },
    methods: {
        //获取用户信息
        getCurrentUser: function () {
            var _that = this;
            var _url = ctx + 'getCurrentUser.do';
            $.ajax({
                url: _url,
                type: 'post',
                success: function (data) {
                    _that.selRowData = data;
                    //清空初始密码
                    // _that.selRowData.loginPwd = '';
                },
                error: function (data) {
                    _that.showError("连接失败");
                }
            })
        },
        //修改用户信息
        toChangeUser: function () {
            var _that = this;
            var _url = ctx + 'user/update.do';
            $.ajax({
                url: _url,
                type: 'post',
                data: JSON.stringify(_that.selRowData),
                contentType: "application/json;charset=UTF-8",
                success: function (data) {
                    if (data == true) {
                        _that.loading = false;
                        _that.showSuccess('修改用户信息成功');
                        _that.centerDialogVisible = false;
                    } else {
                        _that.loading = false;
                        _that.showError('修改用户信息失败');
                    }
                },
                error: function (data) {
                    _that.loading = false;
                    _that.showError('网络异常');
                }
            })
        },
        changePwd:function(){
            var _that = this;
            var _url = ctx + 'user/update.do';
            if (_that.confirmPwd != '' && _that.newPwd != ''){
                if (_that.newPwd == _that.confirmPwd){
                    //将原密码改成新密码通知服务器密码发生变化
                    _that.selRowData.loginPwd = _that.newPwd;
                    $.ajax({
                        url: _url,
                        type: 'post',
                        data: JSON.stringify(_that.selRowData),
                        contentType: "application/json;charset=UTF-8",
                        success: function (data) {
                            if (data == true) {
                                _that.loading = false;
                                _that.showSuccess('修改用户信息成功');
                                _that.centerDialogVisible = false;
                            } else {
                                _that.loading = false;
                                _that.showError('修改用户信息失败');
                            }
                        },
                        error: function (data) {
                            _that.loading = false;
                            _that.showError('网络异常');
                        }
                    })
                }
                else {
                    _that.showError("两次密码输入不一致，请重新输入");
                }
            }
            else{
                _that.showError("密码不能为空");
            }
        },
        openDialog: function () {
            var _that = this;
            _that.centerDialogVisible = true;
        },
        showSuccess(msg) {
            this.$message({
                message: msg,
                type: 'success'
            });
        },
        showError(msg) {
            this.$message.error(msg);
        }
    }
});