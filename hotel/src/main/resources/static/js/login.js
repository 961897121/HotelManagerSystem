var vm = new Vue({
    el:'#app',
    data(){
        return{
            selRowData:{
                userId:'',
                userName:'',
                loginName:'',
                loginPwd:'',
                age:'',
                sex:'',
                identity:'',
                createTime:'',
                userLock:'',
                tel:'',
                addr:'',
                qq:'',
                weChat:''
            },
            userFlag:false,
            pwdFlag:false,
            loading:false,
            dialogVisible:false,
            dialogVisible1:false
        }
    },
    methods:{
        //验证登录名
        checkName:function(event){
            var _that = this;

            if (_that.selRowData.loginName == '' || _that.selRowData.loginName == null){
                event.currentTarget.setAttribute("style","border:1px solid red");
                _that.userFlag = false;
            } else {
                event.currentTarget.removeAttribute("style");
                _that.userFlag = true;
            }
        },
        //验证密码
        checkPwd:function(event){
            var _that = this;

            if (_that.selRowData.loginPwd == '' || _that.selRowData.loginPwd == null){
                event.currentTarget.setAttribute("style","border:1px solid red");
                _that.pwdFlag = false;
            } else {
                event.currentTarget.removeAttribute("style");
                _that.pwdFlag = true;
            }
        },
        //请求登录的方法
        toLogin:function (){
            var _that = this;
            if (_that.pwdFlag && _that.userFlag){
                _that.loading = true;
                var _url = ctx + 'user/manageLogin.do';
                $.ajax({
                    url:_url,
                    type:'post',
                    data:JSON.stringify(_that.selRowData),
                    contentType:"application/json;charset=UTF-8",
                    success:function (data) {
                        if (data == true){
                            console.log('登录成功' , data);
                            _that.loading = false;

                            //跳转主页
                            window.location.href = ctx + 'index.do';
                        }
                        else {
                            console.log('登录失败' , data);
                            _that.loading = false;
                            //弹框
                            _that.dialogVisible = true;
                        }
                    },
                    error:function (data) {
                        console.log('连接失败');
                    }
                })
            }
            else {
                _that.dialogVisible1 = true;
            }

        },
        //清空输入
        handleClose:function () {
            var _that = this;
            _that.selRowData = {
                userId:'',
                userName:'',
                loginName:_that.selRowData.loginName,
                loginPwd:'',
                age:'',
                sex:'',
                identity:'',
                createTime:'',
                userLock:'',
                idCard:'',
                addr:'',
                qq:'',
                weChat:''
            };
        }
    }
});