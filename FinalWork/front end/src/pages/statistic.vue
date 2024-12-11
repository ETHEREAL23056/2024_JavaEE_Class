<!-- 统计界面 -->
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
                    <el-menu-item-group v-if="isAdmin">
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
                    </el-icon><template #title>账户信息</template></el-menu-item>
            </el-menu>
        </el-aside>
        <el-main>
            <!-- 主页内容设计 -->
            <div v-if="isAdmin">
                <div v-if="isPayAll" class="flex flex-col items-center justify-center">
                    <el-text class="m-3 text-3xl text-black font-bold">实际收取的各项费用</el-text>
                    <el-text class="m-3 text-1xl text-black font-bold">统计各个业主的缴费情况</el-text>
                    <!-- 实收费用汇总 -->
                    <el-radio-group class="m-3" v-model="radio" size="large" @change="handleRadioChange">
                        <el-radio-button label="月度报表" value="0" />
                        <el-radio-button label="季度报表" value="1" />
                        <el-radio-button label="年度报表" value="2" />
                    </el-radio-group>
                    <el-date-picker v-if="radio == '0'" v-model="month" type="month" placeholder="Pick a month" />
                    <el-date-picker v-if="radio == '1'" v-model="monthRange" type="monthrange" unlink-panels
                        range-separator="To" start-placeholder="Start month" end-placeholder="End month"
                        :disabled-date="disabledDateMonth" @change="onMonthChange" />
                    <el-date-picker v-if="radio == '2'" v-model="year" type="year" placeholder="Pick a year" />
                    <el-button class="m-1" @click="payhandleSelect" text>查看</el-button>
                    <!-- 报表内容 -->
                    <el-table v-if="isDisplay" :data="payFormData" style="width: 100%">
                        <el-table-column prop="year" label="年份" sortable />
                        <el-table-column prop="month" label="月份" sortable />
                        <el-table-column prop="name" label="业主姓名" sortable />
                        <el-table-column prop="address" label="地址" sortable />
                        <el-table-column prop="total" label="总费用" sortable />
                        <el-table-column prop="paied" label="已缴费用" sortable />
                    </el-table>
                    <el-empty v-if="!isDisplay" description="尚未选择特定时间" />
                </div>
                <div v-if="isUnPayAll" class="flex flex-col items-center justify-center">
                    <!-- 应收未收费用汇总 -->
                    <el-text class="m-3 text-3xl text-black font-bold">尚未收取的各项费用</el-text>
                    <el-text class="m-3 text-1xl text-black font-bold">尚未收取的费用情况</el-text>
                    <el-radio-group class="m-3" v-model="radio" size="large" @change="handleRadioChange">
                        <el-radio-button label="月度报表" value="0" />
                        <el-radio-button label="季度报表" value="1" />
                        <el-radio-button label="年度报表" value="2" />
                    </el-radio-group>
                    <el-date-picker v-if="radio == '0'" v-model="month" type="month" placeholder="Pick a month" />
                    <el-date-picker v-if="radio == '1'" v-model="monthRange" type="monthrange" unlink-panels
                        range-separator="To" start-placeholder="Start month" end-placeholder="End month"
                        :disabled-date="disabledDateMonth" @change="onMonthChange" />
                    <el-date-picker v-if="radio == '2'" v-model="year" type="year" placeholder="Pick a year" />
                    <el-button class="m-1" @click="unpayhandleSelect" text>查看</el-button>
                    <!-- 报表内容 -->
                    <el-table v-if="isDisplay" :data="unpayFormData" style="width: 100%">
                        <el-table-column prop="year" label="年份" sortable />
                        <el-table-column prop="month" label="月份" sortable />
                        <el-table-column prop="name" label="业主姓名" sortable />
                        <el-table-column prop="address" label="地址" sortable />
                        <el-table-column prop="total" label="总费用" sortable />
                        <el-table-column prop="unpaid" label="未缴费用" sortable />
                    </el-table>
                    <el-empty v-if="!isDisplay" description="尚未选择特定时间" />
                </div>
                <div v-if="isUnpaidOwnerAll" class="flex flex-col items-center justify-center">
                    <!-- 未缴费业主汇总 -->
                    <el-text class="m-3 text-3xl text-black font-bold">未缴费业主汇总</el-text>
                    <el-text class="m-3 text-1xl text-black font-bold">尚未对订单进行任何缴费操作的业主名单</el-text>
                    <el-radio-group class="m-3" v-model="radio" size="large" @change="handleRadioChange">
                        <el-radio-button label="月度报表" value="0" />
                        <el-radio-button label="季度报表" value="1" />
                        <el-radio-button label="年度报表" value="2" />
                    </el-radio-group>
                    <el-date-picker v-if="radio == '0'" v-model="month" type="month" placeholder="Pick a month" />
                    <el-date-picker v-if="radio == '1'" v-model="monthRange" type="monthrange" unlink-panels
                        range-separator="To" start-placeholder="Start month" end-placeholder="End month"
                        :disabled-date="disabledDateMonth" @change="onMonthChange" />
                    <el-date-picker v-if="radio == '2'" v-model="year" type="year" placeholder="Pick a year" />
                    <el-button class="m-1" @click="unpayOwnerhandleSelect" text>查看</el-button>
                    <!-- 报表内容 -->
                    <el-table v-if="isDisplay" :data="unpayOwnerFormData" style="width: 100%">
                        <el-table-column type="expand">
                            <template #default="props">
                                <el-table :data="props.row.children">
                                    <el-table-column label="year" prop="year" sortable />
                                    <el-table-column label="month" prop="month" sortable />
                                    <el-table-column label="total" prop="total" sortable />
                                </el-table>
                            </template>
                        </el-table-column>
                        <el-table-column prop="name" label="业主姓名" sortable />
                        <el-table-column prop="address" label="地址" sortable />
                        <el-table-column prop="tag" label="操作" width="100">
                            <template #default="{ row }">
                                <el-button :type="'success'" disable-transitions plain
                                    @click="btnNotify(row)">提醒</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <el-empty v-if="!isDisplay" description="尚未选择特定时间" />
                </div>
                <div v-if="isMonthlyPaid">
                    <!-- 月度还款明细 -->
                    <el-text class="m-3 text-3xl text-black font-bold">月度还款情况汇总</el-text>
                    <el-text class="m-3 text-1xl text-black font-bold">包含所有在本月有消费记录的业主及其缴费情况</el-text>
                    <el-date-picker v-model="month" type="month" placeholder="Pick a month" />
                    <el-button class="m-1" @click="monthlyhandleSelect" text>查看</el-button>
                    <!-- 报表内容 -->
                    <el-table v-if="isDisplay" :data="monthlyFormData" style="width: 100%">
                        <el-table-column prop="name" label="业主姓名" sortable />
                        <el-table-column prop="address" label="地址" sortable />
                        <el-table-column prop="year" label="年份" sortable />
                        <el-table-column prop="month" label="月份" sortable />
                        <el-table-column prop="isPaied" label="缴费情况" sortable />
                        <el-table-column prop="tag" label="操作" width="100">
                            <template #default="{ row }">
                                <el-button :disabled="row.isPaied != '未支付'" :type="'success'" disable-transitions plain
                                    @click="btnNotify(row)">提醒</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <el-empty v-if="!isDisplay" description="尚未选择特定时间" />
                </div>
            </div>
            <div v-if="!isAdmin" class="flex flex-col items-center w-full">
                <el-text class="m-3 text-3xl text-black font-bold">年度各项指标的消费量统计报表</el-text>
                <el-date-picker v-model="year" type="year" placeholder="选择年份" :disabled-date="disabledDateYear"
                    class="mt-3" />
                <el-button text class="mt-1 mb-3 text-1xl text-black font-bold" @click="handleCheck">查看</el-button>
                <div v-if="isShow" class="flex flex-col items-center w-full">
                    <!-- 报表内容 -->
                    <el-text class="text-2xl text-black font-bold">{{ formData.year }}年度{{ isConsume ? "消费" :
                        "支付" }}记录</el-text>
                    <el-row class="w-full h-1/3 m-1" justify="space-around">
                        <el-col :span="8" class="items-center">
                            <el-card style="max-width: 480px" shadow="always" class="flex flex-col items-center">
                                <template #header>
                                    <div class="text-2xl text-black font-bold">
                                        <span>姓名</span>
                                    </div>
                                </template>
                                <el-text>{{ formData.name }}</el-text>
                                <template #footer> <el-text class="text-sm"> 业主姓名</el-text></template>
                            </el-card>
                        </el-col>
                        <el-col :span="8">
                            <el-card style="max-width: 480px" shadow="always" class="flex flex-col items-center">
                                <template #header>
                                    <div class="text-2xl text-black font-bold">
                                        <span>地址</span>
                                    </div>
                                </template>
                                <el-text>{{ formData.address }}</el-text>
                                <template #footer> <el-text class="text-sm"> 楼号 - 层号</el-text></template>
                            </el-card>
                        </el-col>
                    </el-row>
                    <el-row class="w-full h-1/3 m-1" justify="space-around">
                        <el-col :span="8">
                            <el-card v-if="isConsume" style="max-width: 480px" shadow="always"
                                class="flex flex-col items-center">
                                <template #header>
                                    <div class="text-2xl text-black font-bold">
                                        <span>用水量</span>
                                    </div>
                                </template>
                                <el-text>{{ formData.water }}</el-text>
                                <template #footer> <el-text class="text-sm"> 单位： 立方米</el-text></template>
                            </el-card>
                            <el-card v-if="isPay" style="max-width: 480px" shadow="always"
                                class="flex flex-col items-center">
                                <template #header>
                                    <div class="text-2xl text-black font-bold">
                                        <span>水费</span>
                                    </div>
                                </template>
                                <el-text>{{ formData.water }}</el-text>
                                <template #footer> <el-text class="text-sm"> 单位： 元</el-text></template>
                            </el-card>
                        </el-col>
                        <el-col :span="8">
                            <el-card v-if="isConsume" style="max-width: 480px" shadow="always"
                                class="flex flex-col items-center">
                                <template #header>
                                    <div class="text-2xl text-black font-bold">
                                        <span>用电量</span>
                                    </div>
                                </template>
                                <el-text>{{ formData.electricity }}</el-text>
                                <template #footer> <el-text class="text-sm"> 单位： 千瓦时</el-text></template>
                            </el-card>
                            <el-card v-if="isPay" style="max-width: 480px" shadow="always"
                                class="flex flex-col items-center">
                                <template #header>
                                    <div class="text-2xl text-black font-bold">
                                        <span>电费</span>
                                    </div>
                                </template>
                                <el-text>{{ formData.electricity }}</el-text>
                                <template #footer> <el-text class="text-sm"> 单位： 元</el-text></template>
                            </el-card>
                        </el-col>
                    </el-row>
                    <el-row class="w-full h-1/3 m-1" justify="space-around">
                        <el-col :span="isConsume ? 8 : 6">
                            <el-card v-if="isConsume" style="max-width: 480px" shadow="always"
                                class="flex flex-col items-center">
                                <template #header>
                                    <div class="text-2xl text-black font-bold">
                                        <span>燃气用量</span>
                                    </div>
                                </template>
                                <el-text>{{ formData.gas }}</el-text>
                                <template #footer> <el-text class="text-sm"> 单位： 立方米</el-text></template>
                            </el-card>
                            <el-card v-if="isPay" style="max-width: 480px" shadow="always"
                                class="flex flex-col items-center">
                                <template #header>
                                    <div class="text-2xl text-black font-bold">
                                        <span>燃气费</span>
                                    </div>
                                </template>
                                <el-text>{{ formData.gas }}</el-text>
                                <template #footer> <el-text class="text-sm"> 单位： 元</el-text></template>
                            </el-card>
                        </el-col>
                        <el-col :span="isConsume ? 8 : 6">
                            <el-card v-if="isConsume" style="max-width: 480px" shadow="always"
                                class="flex flex-col items-center">
                                <template #header>
                                    <div class="text-2xl text-black font-bold">
                                        <span>暖气用量</span>
                                    </div>
                                </template>
                                <el-text>{{ formData.heater }}</el-text>
                                <template #footer> <el-text class="text-sm"> 单位： 立方米</el-text></template>
                            </el-card>
                            <el-card v-if="isPay" style="max-width: 480px" shadow="always"
                                class="flex flex-col items-center">
                                <template #header>
                                    <div class="text-2xl text-black font-bold">
                                        <span>暖气费</span>
                                    </div>
                                </template>
                                <el-text>{{ formData.heater }}</el-text>
                                <template #footer> <el-text class="text-sm"> 单位： 元</el-text></template>
                            </el-card>
                        </el-col>
                        <el-col v-if="isPay" :span="6">
                            <el-card style="max-width: 480px" shadow="always" class="flex flex-col items-center">
                                <template #header>
                                    <div class="text-2xl text-black font-bold">
                                        <span>物业管理费</span>
                                    </div>
                                </template>
                                <el-text>{{ formData.estate }}</el-text>
                                <template #footer> <el-text class="text-sm"> 单位： 元</el-text></template>
                            </el-card>
                        </el-col>
                    </el-row>
                    <div v-if="isConsume" style="height: 300px; width: 100%;" class="mt-2">
                        <el-carousel :interval="4000" type="card" style="height: 100%;">
                            <el-carousel-item v-for="([key, value], index) in filteredFormData" :key="index"
                                style="background-color: #e0e0e0; display: flex; justify-content: center; align-items: center; text-align: center;">
                                <el-statistic :value="value" :precision="2">
                                    <template #title>
                                        <div style="display: inline-flex; align-items: center">
                                            {{ key.split('_').join(' ') }}
                                            <el-tooltip effect="dark"
                                                :content="'单位：元 / ' + (key.startsWith('electricity') ? '千瓦时' : (key.startsWith('estate') ? '平方米' : '立方米'))"
                                                placement="top">
                                                <el-icon style="margin-left: 4px" :size="12">
                                                    <Warning />
                                                </el-icon>
                                            </el-tooltip>
                                        </div>
                                    </template>
                                </el-statistic>
                            </el-carousel-item>
                        </el-carousel>
                    </div>
                </div>
            </div>
        </el-main>
    </el-container>
</template>

<script lang="ts" setup>
import { computed, onMounted, reactive, ref } from 'vue'
import {
    Document,
    Menu as IconMenu,
} from '@element-plus/icons-vue'
import router from '../router';
import { fetchUser } from '../api/user';
import { ElCol, ElMessage } from 'element-plus';
import { getOwner, getUserOwner } from '../api/owner';
import { selectOwnerYear } from '../api/monthData';
import { selectMonthData, selectOwnerByYear, selectSeasonData, selectYearData } from '../api/payment';
import { selectByPayment } from '../api/ownerPayment';

// ----------------------------------------------
// 折叠面板逻辑
const isCollapse = ref(false)
const collapseMode = () => {
    isCollapse.value = !isCollapse.value
}

// ----------------------------------------------
// 导航栏
// 第一级选择采用页面跳转，第二级选择采用页面内切换
const currentPage = ref('3')
const isPayAll = ref(true)
const isUnPayAll = ref(false)
const isUnpaidOwnerAll = ref(false)
const isMonthlyPaid = ref(false)
const isConsume = ref(true)
const isPay = ref(false)
const pageSelect = (key: any) => {
    switch (key.charAt(0)) {
        case '1':
            router.push('/usage')
            break
        case '2':
            router.push('/bill')
            break
        case '4':
            router.push('/main')
            break
        default:
            isConsume.value = key.slice(-1) == '1'
            isPay.value = key.slice(-1) == '2'
            isShow.value = false
            isPayAll.value = key.slice(-1) == '1'
            isUnPayAll.value = key.slice(-1) == '2'
            isUnpaidOwnerAll.value = key.slice(-1) == '3'
            isMonthlyPaid.value = key.slice(-1) == '4'
    }
}

// ----------------------------------------------
// 获取用户信息
const userId = ref<string>('');
const username = ref<string>('');
const isAdmin = ref<boolean>(false);
const hasBound = ref<boolean>(false);
const ownerId = ref<string>('')
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
            if (!isAdmin.value && ownerResponse.data.state == 200) {
                if (ownerResponse.data.data != null) {
                    hasBound.value = true
                    ownerId.value = ownerResponse.data.data.id
                } else {
                    ElMessage({
                        type: 'error',
                        message: '尚未绑定用户',
                    });
                    router.push('/main')
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
}

// 页面加载时获取用户信息
onMounted(() => {
    fetchData();
});

// ----------------------------------------------
// 年份筛选
const year = ref(0)
const disabledDateYear = (date: { getFullYear: () => number; }) => {
    return date.getFullYear() > new Date().getFullYear();
};
const isShow = ref<boolean>(false)
interface DisplayData {
    year: number
    name: string
    address: string
    water: number
    water_price: number
    electricity: number
    electricity_price: number
    gas: number
    gas_price: number
    heater: number
    heater_price: number
    estate: number
    estate_price: number
}
const formData = reactive<DisplayData>({
    year: 0,
    name: '',
    address: '',
    water: 0,
    electricity: 0,
    gas: 0,
    heater: 0,
    estate: 0,
    water_price: 0,
    electricity_price: 0,
    gas_price: 0,
    heater_price: 0,
    estate_price: 0
})
const filteredFormData = computed(() => {
    return Object.entries(formData).filter(([key]) => key.includes('price'))
})
const handleCheck = async () => {
    Object.assign(formData, {
        year: 0,
        name: '',
        address: '',
        water: 0,
        water_price: 0,
        electricity: 0,
        electricity_price: 0,
        gas: 0,
        gas_price: 0,
        heater: 0,
        heater_price: 0,
        estate: 0,
        estate_price: 0
    })
    try {
        const date = new Date(year.value);
        isShow.value = true;
        formData.year = date.getFullYear();
        if (isConsume.value) {
            const monthDataResponse = await selectOwnerYear(ownerId.value, formData.year);
            if (monthDataResponse.data.state === 200) {
                const monthDataList = monthDataResponse.data.data;
                for (const monthData of monthDataList) {
                    formData.water += monthData.waterConsumption;
                    formData.electricity += monthData.electricityConsumption;
                    formData.gas += monthData.gasConsumption;
                    formData.heater += monthData.heaterConsumption;
                    formData.water_price = monthData.WATER_PRICE
                    formData.electricity_price = monthData.ELECTRICITY_PRICE
                    formData.gas_price = monthData.GAS_PRICE
                    formData.heater_price = monthData.HEATER_PRICE
                    formData.estate_price = monthData.ESTATE_PRICE
                }
                const ownerResponse = await getOwner(ownerId.value);
                if (ownerResponse.data.state === 200) {
                    const owner = ownerResponse.data.data;
                    formData.name = owner.name;
                    formData.address = `${owner.buildingNumber}-${owner.floorNumber}`;
                } else {
                    console.error(ownerResponse);
                    ElMessage({
                        type: 'error',
                        message: '用户信息获取异常',
                    });
                    router.push('/');
                }
            } else {
                console.log(monthDataResponse);
                ElMessage({
                    type: 'error',
                    message: '消费信息获取异常',
                });
                router.push('/');
            }
        } else {
            const paymentResponse = await selectOwnerByYear(ownerId.value, formData.year);
            if (paymentResponse.data.state === 200) {
                const paymentList = paymentResponse.data.data;
                for (const payment of paymentList) {
                    formData.water += payment.waterCost;
                    formData.electricity += payment.electricityCost;
                    formData.gas += payment.gasCost;
                    formData.heater += payment.heaterCost;
                    formData.estate += payment.estateCost;
                }
                const ownerResponse = await getOwner(ownerId.value);
                if (ownerResponse.data.state === 200) {
                    const owner = ownerResponse.data.data;
                    formData.name = owner.name;
                    formData.address = `${owner.buildingNumber}-${owner.floorNumber}`;
                } else {
                    console.error(ownerResponse);
                    ElMessage({
                        type: 'error',
                        message: '用户信息获取异常',
                    });
                    router.push('/');
                }
            } else {
                console.log(paymentResponse);
                ElMessage({
                    type: 'error',
                    message: '支付信息获取异常',
                });
                router.push('/');
            }
        }
    } catch (error) {
        console.error(error);
        ElMessage({
            type: 'error',
            message: '操作异常',
        });
        router.push('/');
    }
}

// ----------------------------------------------------
// 报表时间筛选
const isDisplay = ref(false)
const handleRadioChange = () => {
    isDisplay.value = false
};
const radio = ref('0')
const month = ref(0)
const monthRange = ref([new Date(), new Date()]);
const disabledDateMonth = (date: Date) => {
    const month = date.getMonth();
    return month < 0 || month > 11;
}
const onMonthChange = (dates: Date[]) => {
    const selectedStartMonth = dates[0].getMonth();
    const year = dates[0].getFullYear();
    let startMonth = 0;
    let endMonth = 0;
    if (selectedStartMonth >= 0 && selectedStartMonth <= 2) {
        startMonth = 0;
        endMonth = 2;
    } else if (selectedStartMonth >= 3 && selectedStartMonth <= 5) {
        startMonth = 3;
        endMonth = 5;
    } else if (selectedStartMonth >= 6 && selectedStartMonth <= 8) {
        startMonth = 6;
        endMonth = 8;
    } else if (selectedStartMonth >= 9 && selectedStartMonth <= 11) {
        startMonth = 9;
        endMonth = 11;
    }
    monthRange.value = [
        new Date(year, startMonth, 1),
        new Date(year, endMonth, new Date(year, endMonth + 1, 0).getDate())
    ]
}

// ----------------------------------------------------
// 实收费用汇总
interface PayAllData {
    year: number
    month: number
    name: string
    address: string
    total: number
    paied: number
}
const payFormData = reactive<PayAllData[]>([])
const fetchOwnerData = async (oid: string, pid: string) => {
    try {
        const ownerResponse = await getOwner(oid);
        if (ownerResponse.data.state !== 200) {
            console.error(ownerResponse);
            return null;
        }
        const owner = ownerResponse.data.data;
        const ownerPaymentResponse = await selectByPayment(pid);
        if (ownerPaymentResponse.data.state !== 200) {
            console.error(ownerPaymentResponse);
            return null;
        }
        return { owner, ownerPayment: ownerPaymentResponse.data.data };
    } catch (error) {
        console.error('Error fetching owner data:', error);
        return null;
    }
}
const calculatePaymentSum = (payment: any) => {
    return payment.waterCost + payment.electricityCost + payment.gasCost + payment.heaterCost + payment.estateCost;
}
const payhandleSelect = async () => {
    try {
        payFormData.length = 0;
        isDisplay.value = true
        let paymentResponse: any
        if (radio.value == '0') {
            const date = new Date(month.value);
            paymentResponse = await selectMonthData(date.getFullYear(), date.getMonth() + 1);
        } else if (radio.value == '1') {
            const [start] = monthRange.value;
            const date = new Date(start)
            const startMonth = date.getMonth() + 1
            const startYear = date.getFullYear()
            let season: number
            if (startMonth >= 1 && startMonth <= 3) {
                season = 1
            } else if (startMonth >= 4 && startMonth <= 6) {
                season = 2
            } else if (startMonth >= 7 && startMonth <= 9) {
                season = 3
            } else {
                season = 4
            }
            paymentResponse = await selectSeasonData(startYear, season)
        } else {
            const date = new Date(year.value)
            paymentResponse = await selectYearData(date.getFullYear());
        }

        if (paymentResponse.data.state !== 200) {
            console.error(paymentResponse);
            return;
        }

        const paymentList = paymentResponse.data.data;
        const promises = paymentList.map(async (payment) => {
            const { id, oid } = payment;
            const data = await fetchOwnerData(oid, id);
            if (data) {
                const { owner, ownerPayment } = data;
                if (ownerPayment != null) {
                    const sum = calculatePaymentSum(payment).toFixed(2);
                    payFormData.push({
                        name: owner.name,
                        year: payment.year,
                        month: payment.month,
                        address: `${owner.buildingNumber}-${owner.floorNumber}`,
                        total: sum,
                        paied: parseFloat((sum / ownerPayment.installments * ownerPayment.payedInstallments).toFixed(2)),
                    });
                }

            }
        });

        // 等待所有的异步请求完成
        await Promise.all(promises);
    } catch (error) {
        console.error('Error handling select:', error);
    }
}

// ----------------------------------------------------
// 未收费用汇总
interface UnPayAllData {
    year: number
    month: number
    name: string
    address: string
    total: number
    unpaied: number
}
const unpayFormData = reactive<UnPayAllData[]>([])
const unpayhandleSelect = async () => {
    try {
        unpayFormData.length = 0;
        isDisplay.value = true
        let paymentResponse: any
        if (radio.value == '0') {
            const date = new Date(month.value);
            paymentResponse = await selectMonthData(date.getFullYear(), date.getMonth() + 1);
        } else if (radio.value == '1') {
            const [start] = monthRange.value;
            const date = new Date(start)
            const startMonth = date.getMonth() + 1
            const startYear = date.getFullYear()
            let season: number
            if (startMonth >= 1 && startMonth <= 3) {
                season = 1
            } else if (startMonth >= 4 && startMonth <= 6) {
                season = 2
            } else if (startMonth >= 7 && startMonth <= 9) {
                season = 3
            } else {
                season = 4
            }
            paymentResponse = await selectSeasonData(startYear, season)
        } else {
            const date = new Date(year.value)
            paymentResponse = await selectYearData(date.getFullYear());
        }

        if (paymentResponse.data.state !== 200) {
            console.error(paymentResponse);
            return;
        }

        const paymentList = paymentResponse.data.data;
        const promises = paymentList.map(async (payment) => {
            const { id, oid, isPayed } = payment;
            if (isPayed == 0) {
                const data = await fetchOwnerData(oid, id);
                if (data) {
                    const { owner, ownerPayment } = data;
                    if (ownerPayment != null) {
                        const sum = calculatePaymentSum(payment).toFixed(2);
                        console.log(sum)
                        unpayFormData.push({
                            name: owner.name,
                            year: payment.year,
                            month: payment.month,
                            address: `${owner.buildingNumber}-${owner.floorNumber}`,
                            total: sum,
                            unpaied: parseFloat((sum / ownerPayment.installments * (ownerPayment.installments - ownerPayment.payedInstallments)).toFixed(2)),
                        });
                    } else {
                        const sum = calculatePaymentSum(payment).toFixed(2);
                        console.log(sum)
                        unpayFormData.push({
                            name: owner.name,
                            year: payment.year,
                            month: payment.month,
                            address: `${owner.buildingNumber}-${owner.floorNumber}`,
                            total: sum,
                            unpaied: sum,
                        });
                    }

                }
            }
        });

        // 等待所有的异步请求完成
        await Promise.all(promises);
    } catch (error) {
        console.error('Error handling select:', error);
    }
}

// ----------------------------------------------------
// 未交业主汇总
interface Payment {
    year: number;
    month: number;
    total: number;
}
interface UnPayOwnerData {
    name: string;
    address: string;
    children: Payment[];
}
const unpayOwnerFormData = reactive<UnPayOwnerData[]>([]);
const unpayOwnerhandleSelect = async () => {
    try {
        // 清空当前数据
        unpayOwnerFormData.length = 0;
        isDisplay.value = true;

        let paymentResponse: any;

        // 根据 radio 选择不同的时间范围
        if (radio.value == '0') {
            const date = new Date(month.value);
            paymentResponse = await selectMonthData(date.getFullYear(), date.getMonth() + 1);
        } else if (radio.value == '1') {
            const [start] = monthRange.value;
            const date = new Date(start);
            const startMonth = date.getMonth() + 1;
            const startYear = date.getFullYear();
            let season: number;
            if (startMonth >= 1 && startMonth <= 3) {
                season = 1;
            } else if (startMonth >= 4 && startMonth <= 6) {
                season = 2;
            } else if (startMonth >= 7 && startMonth <= 9) {
                season = 3;
            } else {
                season = 4;
            }
            paymentResponse = await selectSeasonData(startYear, season);
        } else {
            const date = new Date(year.value);
            paymentResponse = await selectYearData(date.getFullYear());
        }

        if (paymentResponse.data.state !== 200) {
            console.error(paymentResponse);
            return;
        }

        const paymentList = paymentResponse.data.data;

        // 使用 Set 来记录已处理过的业主标识
        const processedOwners = new Map<string, UnPayOwnerData>();

        // 处理每一笔支付数据
        const promises = paymentList.map(async (payment) => {
            const { id, oid, isPayed } = payment;

            // 仅处理未支付的记录
            if (isPayed == 0) {
                const data = await fetchOwnerData(oid, id);
                if (data) {
                    const { owner, ownerPayment } = data;

                    // 如果业主支付信息为空，计算支付总额
                    if (ownerPayment == null) {
                        const sum = calculatePaymentSum(payment).toFixed(2);
                        const ownerKey = `${owner.name}-${owner.address}`;

                        // 如果该业主信息没有被处理过，则加入 unpayOwnerFormData
                        if (!processedOwners.has(ownerKey)) {
                            // 创建新的业主对象
                            const newOwnerData: UnPayOwnerData = {
                                name: owner.name,
                                address: `${owner.buildingNumber}-${owner.floorNumber}`,
                                children: [{
                                    year: payment.year,
                                    month: payment.month,
                                    total: sum
                                }]
                            };
                            processedOwners.set(ownerKey, newOwnerData);
                            unpayOwnerFormData.push(newOwnerData);
                        } else {
                            // 如果业主已存在，在已有的业主对象中添加新的支付记录
                            const existingOwner = processedOwners.get(ownerKey);
                            if (existingOwner) {
                                existingOwner.children.push({
                                    year: payment.year,
                                    month: payment.month,
                                    total: sum
                                });
                            }
                        }
                    }
                }
            }
        });

        // 等待所有的异步请求完成
        await Promise.all(promises);
    } catch (error) {
        console.error('Error handling select:', error);
    }
}
const btnNotify = (row: { name: string; }) => {
    ElMessage({
        type: 'success',
        message: '成功通知业主' + row.name,
    });
}

// ----------------------------------------------------
// 月度缴费汇总
interface OwnerPayData {
    year: string
    month: string
    name: string
    address: string
    total: number
    isPaied: string
}
const monthlyFormData = reactive<OwnerPayData[]>([]);
const monthlyhandleSelect = async () => {
    try {
        monthlyFormData.length = 0;
        isDisplay.value = true;
        const date = new Date(month.value);
        const lastYearMonths = getLastYearMonths(date);

        // 创建一个累积的 promises 数组
        const allPromises: Promise<void>[] = [];

        for (const timeStamp of lastYearMonths) {
            const [year, month] = timeStamp.split('-').map(Number);
            const pDate = new Date(year, month - 1);
            const paymentResponse = await selectMonthData(year, month);

            if (paymentResponse.data.state !== 200) {
                console.error(paymentResponse);
                return;
            }

            const paymentList = paymentResponse.data.data;

            const paymentPromises = paymentList.map(async (payment: { year?: any; month?: any; id?: any; oid?: any; isPayed?: any; }) => {
                const { id, oid, isPayed } = payment;
                const data = await fetchOwnerData(oid, id);

                if (data) {
                    const { owner, ownerPayment } = data;
                    if (isPayed == 0) {
                        if (ownerPayment != null) {
                            if (ownerPayment.installments - ownerPayment.unpaidInstallments > getMonthDiff(pDate, date)) {
                                const pre = parseFloat((calculatePaymentSum(payment) / ownerPayment.installments).toFixed(2));
                                monthlyFormData.push({
                                    name: owner.name,
                                    address: `${owner.buildingNumber}-${owner.floorNumber}`,
                                    total: pre,
                                    isPaied: "未支付",
                                    year: payment.year,
                                    month: payment.month
                                });
                            }
                        } else {
                            const pre = (calculatePaymentSum(payment).toFixed(2));
                            monthlyFormData.push({
                                name: owner.name,
                                address: `${owner.buildingNumber}-${owner.floorNumber}`,
                                total: pre,
                                isPaied: "未支付",
                                year: payment.year,
                                month: payment.month
                            });
                        }
                    } else if (date.getFullYear() == pDate.getFullYear() && date.getMonth() == pDate.getMonth()) {
                        if (ownerPayment != null) {
                            const sum = calculatePaymentSum(payment).toFixed(2);
                            monthlyFormData.push({
                                name: owner.name,
                                address: `${owner.buildingNumber}-${owner.floorNumber}`,
                                total: sum,
                                isPaied: "已支付",
                                year: payment.year,
                                month: payment.month
                            });
                        }
                    }
                }
            });

            allPromises.push(...paymentPromises);
        }

        // 等待所有的异步请求完成
        await Promise.all(allPromises);
    } catch (error) {
        console.error('Error handling select:', error);
    }
}
const getLastYearMonths = (currentDate: Date): string[] => {
    const months: string[] = [];
    let year = currentDate.getFullYear();
    let month = currentDate.getMonth();

    for (let i = 0; i < 12; i++) {
        const yearMonth = `${year}-${String(month + 1).padStart(2, '0')}`;
        months.push(yearMonth);
        month--;
        if (month < 0) {
            month = 11;
            year--;
        }
    }

    return months.reverse();
};
function getMonthDiff(date1: Date, date2: Date) {
    const year1 = date1.getFullYear();
    const month1 = date1.getMonth();
    const year2 = date2.getFullYear();
    const month2 = date2.getMonth();
    return (year2 - year1) * 12 + (month2 - month1);
}

</script>

<style>
.menu:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
}

.el-carousel__item h3 {
    color: #475669;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
    text-align: center;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
}
</style>