<!-- 登录/注册页面 -->
<template>
    <el-row style="min-height: 100vh;">
        <!-- 左侧 -->
        <el-col :span="16" class="bg-purple-300 flex flex-col items-center justify-center">
            <el-text class="text-5xl text-white font-bold">物业管理信息系统</el-text>
            <el-text class="text-1xl text-black fond-bold">2024 企业Java课程大作业</el-text>
        </el-col>

        <!-- 右侧 -->
        <el-col :span="8" class="bg-white flex flex-col items-center justify-center gap-6">
            <el-text class="text-2xl text-black font-bold">欢迎使用物业管理信息系统</el-text>
            <!-- 根据 isRegister 切换显示的标题 -->
            <el-text class="text-3xl text-black font-bold">
                {{ isRegister ? '注册' : '登录' }}
            </el-text>

            <!-- 登录或注册表单 -->
            <div v-if="!isRegister" class="flex flex-col gap-4" style="width: 320px;">
                <!-- 登录界面 -->
                <div class="flex items-center gap-4">
                    <label for="username" class="text-gray-700 text-sm w-20 text-right">用户名</label>
                    <el-input id="username" v-model="username" placeholder="请输入用户名" clearable style="flex: 1;" />
                </div>
                <div class="flex items-center gap-4">
                    <label for="password" class="text-gray-700 text-sm w-20 text-right">密码</label>
                    <el-input id="password" v-model="password" type="password" placeholder="请输入密码" show-password
                        clearable style="flex: 1;" />
                </div>
                <el-button type="primary" @click="btn_login">登录</el-button>
            </div>

            <div v-else class="flex flex-col gap-4" style="width: 320px;">
                <!-- 注册界面 -->
                <div class="flex items-center gap-4">
                    <label for="username" class="text-gray-700 text-sm w-20 text-right">用户名</label>
                    <el-input id="username" v-model="username" placeholder="请输入用户名" clearable style="flex: 1;" />
                </div>
                <div class="flex items-center gap-4">
                    <label for="password" class="text-gray-700 text-sm w-20 text-right">密码</label>
                    <el-input id="password" v-model="password" type="password" placeholder="请输入密码" clearable
                        show-password style="flex: 1;" />
                </div>
                <div class="flex items-center gap-4">
                    <label for="confirmPassword" class="text-gray-700 text-sm w-20 text-right">确认密码</label>
                    <el-input id="confirmPassword" v-model="confirmPassword" type="password" placeholder="请再次输入密码"
                        show-password clearable style="flex: 1;" />
                </div>
                <div class="mb-2 ml-4 flex items-center justify-center">
                    <el-radio-group v-model="role">
                        <el-radio value="1" size="large">我是用户</el-radio>
                        <el-radio value="2" size="large">我是管理员</el-radio>
                    </el-radio-group>
                </div>
                <div v-if="role != 1" class="flex items-center gap-4">
                    <label for="roleKey" class="text-gray-700 text-sm w-20 text-right">管理员密钥</label>
                    <el-input id="roleKey" v-model="roleKey" type="password" placeholder="请输入管理员密钥" show-password
                        clearable style="flex: 1;" />
                </div>
                <el-button type="primary" @click="btn_register">注册</el-button>
            </div>

            <!-- 切换按钮 -->
            <el-button type="text" @click="toggleMode">
                {{ isRegister ? '已有账号？去登录' : '没有账号？去注册' }}
            </el-button>
        </el-col>
    </el-row>
</template>

<script lang="ts" setup>

import { ElMessage } from 'element-plus';
import { ref } from 'vue'
import { login, register } from '../api/user';
import router from '../router';
const username = ref('')
const password = ref('')
const confirmPassword = ref('')
const isRegister = ref(false)
const role = ref(1)
const roleKey = ref('')

// 切换登录和注册界面
const toggleMode = () => {
    isRegister.value = !isRegister.value
    username.value = ''
    password.value = ''
    confirmPassword.value = ''
    role.value = 1
    roleKey.value = ''
}
// 注册和登录按钮逻辑
const btn_login = () => {
    login(username.value, password.value).then(response => {
        if (response.data.state == 200) {
            ElMessage({
                type: 'success',
                message: '登录成功',
            })
            // 跳转到主页面
            router.push('/main')
        } else {
            console.log(response)
            ElMessage({
                type: 'error',
                message: response.data.message,
            })
        }
    })
        .catch((response) => {
            console.log(response)
            ElMessage({
                type: 'error',
                message: '登录异常',
            })
        })
}
const btn_register = () => {
    if (confirmPassword.value != password.value) {
        ElMessage({
            type: 'error',
            message: '两次输入的密码不一致',
        })
    } else {
        let userRole = role.value == 1 ? 'user' : 'admin'
        let userRoleKey = userRole == 'user' ? '' : roleKey.value
        register(username.value, password.value, userRole,  userRoleKey).then(response => {
            if (response.data.state == 200) {
                ElMessage({
                    type: 'success',
                    message: '注册成功',
                })
                toggleMode()
            } else {
                console.log(response)
                ElMessage({
                    type: 'error',
                    message: response.data.message,
                })
            }
        })
            .catch((response) => {
                console.log(response)
                ElMessage({
                    type: 'error',
                    message: '注册异常',
                })
            })
    }
}
</script>

<style></style>

<style scoped></style>
