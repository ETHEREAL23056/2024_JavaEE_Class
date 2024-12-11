<template>
    <div>
        <el-col :sm="12" :lg="6">
            <el-result icon="error" title="404" sub-title="请求的页面不存在">
                <template #extra>
                    <el-button type="primary" @click="$router.push('/')">回到首页</el-button>
                </template>
            </el-result>
        </el-col>
    </div>
</template>