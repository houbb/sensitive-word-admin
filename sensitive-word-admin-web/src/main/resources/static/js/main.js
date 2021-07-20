Vue.component('my-footer', {
    template: '<div>\n' +
        '<el-divider></el-divider>\n' +
        '<el-footer>由 <el-link :underline="false" href="https://github.com/houbb/sensitive-word-admin" type="primary">sensitive-word-admin</el-link> 强力支持</el-footer>\n' +
        '</div>',
    data() {
        return {
            info: '2133'
        }
    }
});

Vue.component('my-header', {
    template: '<div>\n' +
        '    <el-header>\n' +
        '        <a href="/">\n' +
        '            <el-image style="width: 100px; height: 58px; display: inline-block;"\n' +
        '                      src="/img/logo.PNG"\n' +
        '                      fit="cover">\n' +
        '            </el-image>\n' +
        '        </a>\n' +
        '\n' +
        '        <el-menu class="el-menu-demo" mode="horizontal" @select="handleSelect" style="float: right;">\n' +
        '            <el-menu-item index="/index">\n' +
        '                <a>首页</a>\n' +
        '            </el-menu-item>\n' +
        '            <el-submenu index="manage">\n' +
        '                <template slot="title">功能管理</template>\n' +
                        '                <el-menu-item index="/lcEnumMapping/index">\n' +
        '                    <i class="el-icon-user-solid"></i>枚举映射表\n' +
        '                </el-menu-item>\n' +
        '                <el-menu-item index="/tag/index">\n' +
        '                    <i class="el-icon-user-solid"></i>标签表\n' +
        '                </el-menu-item>\n' +
        '                <el-menu-item index="/word/index">\n' +
        '                    <i class="el-icon-user-solid"></i>敏感词表\n' +
        '                </el-menu-item>\n' +
        '                <el-menu-item index="/wordLog/index">\n' +
        '                    <i class="el-icon-user-solid"></i>敏感词操作日志表\n' +
        '                </el-menu-item>\n' +
        '                <el-menu-item index="/wordTagMapping/index">\n' +
        '                    <i class="el-icon-user-solid"></i>标签单词映射表\n' +
        '                </el-menu-item>\n' +
        '            </el-submenu>\n' +
        '            <el-submenu index="log">\n' +
        '                <template slot="title">审计日志</template>\n' +
        '                <el-menu-item index="/loginLog/index">登入日志</el-menu-item>\n' +
        '                <el-menu-item index="/logoutLog/index">登出日志</el-menu-item>\n' +
        '                <el-menu-item index="/operateLog/index">操作日志</el-menu-item>\n' +
        '            </el-submenu>\n' +
        '            <el-submenu index="more">\n' +
        '                <template slot="title">更多</template>\n' +
        '                <el-menu-item index="/aboutUs">关于我们</el-menu-item>\n' +
        '                <el-menu-item index="/changeLog/index">变更日志</el-menu-item>\n' +
        '                <el-menu-item index="/version">版本信息</el-menu-item>\n' +
        '                <el-menu-item index="/login/logout">\n' +
        '                    <i class="el-icon-switch-button"></i>登出\n' +
        '                </el-menu-item>\n' +
        '            </el-submenu>\n' +
        '        </el-menu>\n' +
        '    </el-header>\n' +
        '</div>',
    data() {
        return {
            info: 'my header!'
        }
    },
    methods: {
        handleSelect(key, keyPath) {
            this.formValue = localStorage.getItem('padminJwt');
            this.action = keyPath[0];
            if(keyPath.length === 2) {
                this.action = keyPath[1];
            }

            //登出
            if(this.action === '/login/logout') {
                axios.post('/login/logout/').then(function (response) {
                    if (response.data.respCode === '0000') {
                        //清空 token 信息
                        localStorage.removeItem('padminJwt');
                        window.location.href='/login/index';
                    } else {
                        ELEMENT.Message.error(response.data.respMessage);
                    }
                }).catch(function (error) {
                    ELEMENT.Message.error("请求失败");
                });
            } else {
                window.location.href=this.action+'?t='+this.formValue;
            }
        }
    }
});

// 设置全局信息
// 可以优化为使用拦截器：https://www.jianshu.com/p/a9471f62b8f9
var authToken = localStorage.getItem('padminJwt');
axios.defaults.headers.common['Authorization'] = 'Bearer '+authToken;