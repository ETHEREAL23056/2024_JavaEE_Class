<!-- 系统主界面 -->
<template>
    <el-container>
        <el-aside width="200px">
            <el-button class="w-full h-24" text @click="collapseMode">
                <img src="/src/assets/Date_Value-removebg-preview.png" alt="Element logo" />
            </el-button>
            <!-- 用户栏和用户的身份相关 -->
            <el-menu :default-active="currentPage" class="menu" :collapse="isCollapse" @select="pageSelect">
                <el-sub-menu index="1">
                    <template #title>
                        <el-icon><icon-menu /></el-icon>
                        <span>抄表数据</span>
                    </template>
                    <el-menu-item-group>
                        <template #title>
                            <span v-if="isAdmin">用量统计</span>
                            <span v-if="!isAdmin">用量查询</span>
                        </template>
                        <el-menu-item index="1-1">水电用量</el-menu-item>
                        <el-menu-item index="1-2">煤气用量</el-menu-item>
                        <el-menu-item index="1-3">暖气用量</el-menu-item>
                        <el-menu-item index="1-4">历年数据查询</el-menu-item>
                    </el-menu-item-group>
                    <el-menu-item-group  v-if="isAdmin">
                        <template #title>
                            <span>添加记录</span>
                        </template>
                        <el-menu-item index="1-5">抄表数据添加</el-menu-item>
                    </el-menu-item-group>
                </el-sub-menu>
                <el-sub-menu index="2">
                    <template #title>
                        <el-icon>
                            <document />
                        </el-icon>
                        <span>费用信息</span>
                    </template>
                    <el-menu-item-group>
                        <template #title>
                            <span v-if="isAdmin">缴费统计</span>
                            <span v-if="!isAdmin">费用支付</span>
                        </template>
                        <div v-if="isAdmin">
                            <el-menu-item index="2-1">缴费通知</el-menu-item>
                            <el-menu-item index="2-2">缴费情况查询</el-menu-item>
                        </div>
                        <div v-if="!isAdmin">
                            <el-menu-item index="2-1">未支付</el-menu-item>
                            <el-menu-item index="2-2">已支付</el-menu-item>
                        </div>
                    </el-menu-item-group>
                </el-sub-menu>
                <el-sub-menu index="3">
                    <template #title>
                        <el-icon>
                            <List />
                        </el-icon>
                        <span>使用统计</span>
                    </template>
                    <el-menu-item-group>
                        <template #title>
                            <span>报表记录</span>
                        </template>
                        <div v-if="isAdmin">
                            <el-menu-item index="3-1">实收费用汇总</el-menu-item>
                            <el-menu-item index="3-2">应收未收汇总</el-menu-item>
                            <el-menu-item index="3-3">未交业主汇总</el-menu-item>
                            <el-menu-item index="3-4">月度还款名细</el-menu-item>
                        </div>
                        <div v-if="!isAdmin">
                            <el-menu-item index="3-1">年度消费记录</el-menu-item>
                            <el-menu-item index="3-2">年度支付记录</el-menu-item>
                        </div>
                    </el-menu-item-group>
                </el-sub-menu>
                <el-menu-item index="4">
                    <el-icon>
                        <User />
                    </el-icon>
                    <template #title>账户信息</template></el-menu-item>
            </el-menu>
        </el-aside>
        <el-main class="flex flex-col items-center">
            <!-- 主页内容设计 -->
            <el-text class="m-3 text-3xl text-black font-bold">个人信息</el-text>
            <div class="flex flex-col items-center w-5/6" v-if="!hasBound && !isAdmin">
                <el-text class="m-3 text-1xl text-black font-bold">您尚未绑定个人信息，请在下方完成个人信息的注册和绑定</el-text>
                <div class="m-3 w-3/4">
                    <el-container class="items-center justify-center">
                        <el-aside class="w-1/2">
                            <el-text class="text-center">
                                个人信息以及用于管理这些信息的选项。您可以向他人公开部分信息（例如您的详细联系信息），以方便他们与您联系，我们会严格遵守隐私协议保护您的个人信息。
                            </el-text>
                        </el-aside>
                        <el-main><img src="/src/assets/info_img.png" /></el-main>
                    </el-container>
                </div>
                <div class="m-3 w-3/4 border border-gray-200 rounded-md flex flex-col items-center">
                    <el-form :model="form" label-width="auto" class="m-3 w-3/4">
                        <el-form-item label="姓名">
                            <el-input v-model="form.name" />
                        </el-form-item>
                        <el-form-item label="身份证号">
                            <el-input v-model="form.IDNumber" type="password" show-password class="w-1/2" />
                        </el-form-item>
                        <el-form-item label="地址">
                            <el-cascader v-model="value" :options="options" :props="props" @change="addAddress" />
                        </el-form-item>
                        <el-form-item label="联系电话">
                            <el-input v-model="form.telephoneNumber" clearable />
                        </el-form-item>
                        <el-form-item label="工作地址">
                            <el-input v-model="form.company" type="textarea" />
                        </el-form-item>
                        <el-form-item label="住房面积">
                            <el-input-number v-model="form.area" :min="10" :max="1000" :precision="2" :step="0.1" />
                            <el-text class="mx-2">平方米</el-text>
                        </el-form-item>
                    </el-form>
                    <div class="mb-3">
                        <el-button type="primary" @click="onSubmit">绑定</el-button>
                        <el-button @click="resetForm">重置</el-button>
                    </div>
                </div>
            </div>
            <div class="flex flex-col items-center w-5/6" v-if="hasBound || isAdmin">
                <el-text class="m-3 text-1xl text-black font-bold">我们将严格遵循隐私规定，确保您的个人信息安全</el-text>
                <div class="m-3 w-3/4">
                    <el-container class="items-center justify-center">
                        <el-aside class="w-1/2">
                            <el-text class="text-center">
                                个人信息以及用于管理这些信息的选项。您可以向他人公开部分信息（例如您的详细联系信息），以方便他们与您联系。您还可以查看您的个人资料的摘要。
                            </el-text>
                        </el-aside>
                        <el-main><img src="/src/assets/info_img.png" /></el-main>
                    </el-container>
                </div>
                <div class="m-3 w-3/4 border border-gray-200 rounded-md">
                    <el-descriptions class="m-2" :column="1" border v-if="!isAdmin">
                        <template #title>
                            <div class="m-1 text-2xl font-normal text-center text-black">基本信息</div>
                        </template>
                        <el-descriptions-item>
                            <template #label><el-icon>
                                    <user />
                                </el-icon>姓名</template>
                            <div class="text-center font-mono text-lg text-black">
                                {{ personalInfo.name }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template #label><el-icon>
                                    <iphone />
                                </el-icon>电话号码</template>
                            <div class="text-center font-mono text-lg text-black">
                                {{ personalInfo.phone }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template #label><el-icon>
                                    <Notification />
                                </el-icon>身份证号</template>
                            <div class="text-center font-mono text-lg text-black">
                                {{ personalInfo.idCard }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template #label><el-icon>
                                    <LocationInformation />
                                </el-icon>地址</template>
                            <div class="text-center font-mono text-lg text-black">
                                {{ personalInfo.address }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template #label><el-icon><office-building /></el-icon>工作单位</template>
                            <div class="text-center font-mono text-lg text-black">
                                {{ personalInfo.company }}</div>
                        </el-descriptions-item>
                    </el-descriptions>
                </div>
                <div class="m-3 w-3/4 border border-gray-200 rounded-md">
                    <el-descriptions class="m-3" :column="1" border>
                        <template #title>
                            <div class="m-1 text-2xl font-normal text-center text-black">账号信息</div>
                        </template>
                        <el-descriptions-item>
                            <template #label><el-icon>
                                    <user />
                                </el-icon>用户名</template>
                            <div class="text-center font-mono text-lg text-black">
                                {{ accountInfo.username }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template #label><el-icon>
                                    <Lock />
                                </el-icon>密码</template>
                            <div class="text-center font-mono text-lg text-black">
                                {{ accountInfo.password }}</div>
                        </el-descriptions-item>
                    </el-descriptions>
                </div>
            </div>
        </el-main>
    </el-container>
</template>

<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue'
import {
    Document,
    Menu as IconMenu,
} from '@element-plus/icons-vue'
import router from '../router';
import { fetchUser } from '../api/user';
import { ElMessage, ElMessageBox } from 'element-plus';
import { addOwner, bound, getOwnerByID, getUserOwner } from '../api/owner';

// ----------------------------------------------
// 折叠面板逻辑
const isCollapse = ref(false)
const collapseMode = () => {
    isCollapse.value = !isCollapse.value
}

// ----------------------------------------------
// 导航栏
// 第一级选择采用页面跳转，第二级选择采用页面内切换
const currentPage = ref('4')
const pageSelect = (key: any) => {
    switch (key.charAt(0)) {
        case '1':
            router.push('/usage')
            break
        case '2':
            router.push('/bill')
            break
        case '3':
            router.push('/statistic')
            break
        default:
            router.push('/main')
    }
}

// ----------------------------------------------
// 获取用户信息
const userId = ref<string>('')
const username = ref<string>('')
const isAdmin = ref<boolean>(false)
const hasBound = ref<boolean>(false)
const personalInfo = ref({
    name: "",
    phone: "",
    idCard: "",
    address: "",
    company: ""
})
const accountInfo = ref({
    username: username,
    password: "*** *** ***"
})
const fetchData = async () => {
    try {
        const userResponse = await fetchUser();
        if (userResponse.data.state === 200) {
            const user = userResponse.data.data;
            userId.value = user.uid;
            username.value = user.username;
            isAdmin.value = user.role === 'admin';
            // 获取绑定状态
            const ownerResponse = await getUserOwner(userId.value);
            if (ownerResponse.data.state === 200) {
                const owner = ownerResponse.data.data
                hasBound.value = owner != null;
                if (hasBound.value) {
                    personalInfo.value.name = owner.name
                    personalInfo.value.phone = owner.telephoneNumber
                    personalInfo.value.idCard = owner.idnumber
                    personalInfo.value.address = owner.buildingNumber + '-' + owner.floorNumber
                    personalInfo.value.company = owner.workingUnit
                }
            }
        } else {
            console.error(userResponse);
            ElMessage({
                type: 'error',
                message: '用户信息获取异常',
            });
            router.push('/');
        }
    } catch (error) {
        console.error(error);
        ElMessage({
            type: 'error',
            message: '用户信息获取异常',
        });
        router.push('/');
    }
};

// 页面加载时获取用户信息
onMounted(() => {
    fetchData();
});

// ----------------------------------------------
// 信息绑定表单
const form = reactive({
    name: '',
    IDNumber: '',
    buildingNumber: '',
    floorNumber: '',
    telephoneNumber: '',
    company: '',
    area: ''
})
const resetForm = () => {
    ElMessageBox.confirm(
        '确认重置信息？',
        '重置信息',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            // 重置表单中的所有信息
            Object.keys(form).forEach((key) => {
                form[key] = "";
            });
            value.value = []
            ElMessage({
                type: 'success',
                message: '重置成功',
            })
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '重置操作取消',
            })
        })
}
const onSubmit = () => {
    ElMessageBox.confirm(
        '确认提交？',
        '用户信息',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            addOwner(form.IDNumber, form.buildingNumber, form.floorNumber, form.name, form.company, form.telephoneNumber, form.area).then(response => {
                if (response.data.state == 200) {
                    getOwnerByID(form.IDNumber).then(response => {
                        if (response.data.state == 200) {
                            const owner = response.data.data
                            bound(owner.id, userId.value).then(response => {
                                if (response.data.state == 200) {
                                    ElMessage({
                                        type: 'success',
                                        message: '绑定成功',
                                    })
                                    window.location.reload();
                                } else {
                                    console.log(response)
                                    ElMessage({
                                        type: 'error',
                                        message: response.data.message,
                                    })
                                }

                            })
                                .catch(response => {
                                    console.log(response)
                                    ElMessage({
                                        type: 'error',
                                        message: '绑定异常',
                                    })
                                })
                        }
                    })
                        .catch(response => {
                            console.log(response)
                            ElMessage({
                                type: 'error',
                                message: '绑定异常',
                            })
                        })
                } else {
                    console.log(response)
                    ElMessage({
                        type: 'error',
                        message: '绑定异常',
                    })
                }
            })
                .catch(response => {
                    console.log(response)
                    ElMessage({
                        type: 'error',
                        message: '绑定异常',
                    })
                })
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '取消提交',
            })
        })

}
const value = ref([])
const props = {
    expandTrigger: 'hover' as const,
}
const addAddress = (value: string[]) => {
    form.buildingNumber = value[0]
    form.floorNumber = value[1].slice(-1)
}
const options = ref<any[]>([])
const initializeData = () => {
    const buildings = Array.from({ length: 10 }, (_, i) => i + 1)
    options.value = buildings.map((buildingNumber, index) => ({
        value: buildingNumber,
        label: 'building' + (index + 1),
        children: Array.from({ length: 6 }, (_, i) => ({
            value: `${index + 1}-${i + 1}`,
            label: i + 1,
        })),
    }))
}
initializeData()

</script>

<style>
.menu:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
}
</style>