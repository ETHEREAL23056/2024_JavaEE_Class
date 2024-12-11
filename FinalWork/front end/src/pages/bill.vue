<!-- 费用界面 -->
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
                <div v-if="isNo" class="flex flex-col items-center justify-center">
                    <!-- 缴费通知 -->
                    <el-text class="m-3 text-3xl text-black font-bold">缴费通知</el-text>
                    <el-text class="m-3 text-1xl text-black font-bold">尚未发出缴费通知的抄表数据</el-text>
                    <el-table :data="uninfoData" style="width: 100%">
                        <el-table-column prop="date" label="抄表日期" sortable />
                        <el-table-column prop="name" label="业主姓名" sortable />
                        <el-table-column prop="address" label="地址" sortable />
                        <el-table-column prop="water" label="用水量" sortable />
                        <el-table-column prop="electricity" label="用电量" sortable />
                        <el-table-column prop="gas" label="燃气用量" sortable />
                        <el-table-column fixed="heater" label="暖气用量" sortable />
                        <el-table-column fixed="right" label="操作" min-width="120">
                            <template #default="{ row }">
                                <el-button link type="success" size="large" @click="handleClick(row)">
                                    通知
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
                <div v-if="isSe" class="flex flex-col items-center justify-center">
                    <!-- 缴费情况查询 -->
                    <el-text class="m-3 text-3xl text-black font-bold">缴费情况</el-text>
                    <el-text class="m-3 text-1xl text-black font-bold">仅列出当前未支付或尚未完成全部支付的订单</el-text>
                    <el-table :data="unpaidData" style="width: 100%">
                        <el-table-column type="expand">
                            <template #default="props">
                                <div m="2" class="flex flex-col items-center space-y-4">
                                    <h3 class="text-lg text-black font-bold">支付详情</h3>
                                    <el-row v-if="props.row.installments != -1" class="w-3/4">
                                        <el-col :span="12">
                                            <div class="statistic-card">
                                                <el-statistic :value="props.row.installments">
                                                    <template #title>
                                                        <div style="display: inline-flex; align-items: center">
                                                            用户采用的分期数目
                                                            <el-tooltip effect="dark" content="单位——期" placement="top">
                                                                <el-icon style="margin-left: 4px" :size="12">
                                                                    <Warning />
                                                                </el-icon>
                                                            </el-tooltip>
                                                        </div>
                                                    </template>
                                                </el-statistic>
                                            </div>
                                        </el-col>
                                        <el-col :span="12">
                                            <div class="statistic-card">
                                                <el-statistic :value="props.row.payedInstallments">
                                                    <template #title>
                                                        <div style="display: inline-flex; align-items: center">
                                                            用户已支付的分期数目
                                                            <el-tooltip effect="dark" content="单位——期" placement="top">
                                                                <el-icon style="margin-left: 4px" :size="12">
                                                                    <Warning />
                                                                </el-icon>
                                                            </el-tooltip>
                                                        </div>
                                                    </template>
                                                </el-statistic>
                                            </div>
                                        </el-col>
                                    </el-row>
                                    <div v-if="props.row.installments == -1" class="statistic-card">
                                        <el-empty description="业主尚未对该订单进行支付操作" />
                                    </div>
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column prop="name" label="业主姓名" sortable />
                        <el-table-column prop="address" label="地址" sortable />
                        <el-table-column prop="date" label="时间" sortable />
                        <el-table-column prop="water" label="水费" sortable />
                        <el-table-column prop="electricity" label="电费" sortable />
                        <el-table-column prop="gas" label="燃气费" sortable />
                        <el-table-column prop="heater" label="暖气费" sortable />
                        <el-table-column prop="total" label="费用合计" sortable />
                    </el-table>
                </div>
            </div>
            <div v-if="!isAdmin" class="payment-table-wrapper">
                <div v-if="isU" class="flex flex-col items-center justify-center">
                    <!-- 未支付 -->
                    <el-card class="box-card">
                        <template #header>
                            <div class="card-header">
                                <el-icon>
                                    <Coin />
                                </el-icon>
                                <span class="title">未支付费用</span>
                            </div>
                        </template>
                        <div class="card-content">
                            <el-scrollbar max-height="600px">
                                <el-table :data="ownerUnpaidData" border stripe style="width: 100%"
                                    :cell-style="{ height: '40px' }">
                                    <el-table-column type="expand">
                                        <template #default="props">
                                            <div m="2" class="flex flex-col items-center space-y-4">
                                                <h3 class="text-lg text-black font-bold">支付详情</h3>
                                                <el-row v-if="props.row.installments != -1" class="w-3/4">
                                                    <el-col :span="8">
                                                        <div class="statistic-card">
                                                            <el-statistic
                                                                :value="(props.row.total / props.row.installments).toFixed(2)">
                                                                <template #title>
                                                                    <div
                                                                        style="display: inline-flex; align-items: center">
                                                                        您一次需要的支付最小金额
                                                                        <el-tooltip effect="dark" content="单位——元/期"
                                                                            placement="top">
                                                                            <el-icon style="margin-left: 4px"
                                                                                :size="12">
                                                                                <Warning />
                                                                            </el-icon>
                                                                        </el-tooltip>
                                                                    </div>
                                                                </template>
                                                            </el-statistic>
                                                        </div>
                                                    </el-col>
                                                    <el-col :span="8">
                                                        <div class="statistic-card">
                                                            <el-statistic :value="props.row.payedInstallments">
                                                                <template #title>
                                                                    <div
                                                                        style="display: inline-flex; align-items: center">
                                                                        您已支付的分期数目
                                                                        <el-tooltip effect="dark" content="单位——期"
                                                                            placement="top">
                                                                            <el-icon style="margin-left: 4px"
                                                                                :size="12">
                                                                                <Warning />
                                                                            </el-icon>
                                                                        </el-tooltip>
                                                                    </div>
                                                                </template>
                                                            </el-statistic>
                                                        </div>
                                                    </el-col>
                                                    <el-col :span="8">
                                                        <div class="statistic-card">
                                                            <el-statistic
                                                                :value="props.row.installments - props.row.payedInstallments">
                                                                <template #title>
                                                                    <div
                                                                        style="display: inline-flex; align-items: center">
                                                                        您还需支付的分期数目
                                                                        <el-tooltip effect="dark" content="单位——期"
                                                                            placement="top">
                                                                            <el-icon style="margin-left: 4px"
                                                                                :size="12">
                                                                                <Warning />
                                                                            </el-icon>
                                                                        </el-tooltip>
                                                                    </div>
                                                                </template>
                                                            </el-statistic>
                                                        </div>
                                                    </el-col>
                                                </el-row>
                                                <div v-if="props.row.installments == -1" class="statistic-card">
                                                    <el-empty description="您尚未进行支付操作" />
                                                </div>
                                            </div>
                                        </template>
                                    </el-table-column>
                                    <el-table-column prop="year" label="年份" width="160" />
                                    <el-table-column prop="month" label="月份" width="140" />
                                    <el-table-column prop="water" label="水费" sortable />
                                    <el-table-column prop="electricity" label="电费" sortable />
                                    <el-table-column prop="gas" label="燃气费" sortable />
                                    <el-table-column prop="heater" label="暖气费" sortable />
                                    <el-table-column prop="total" label="合计" sortable />
                                    <el-table-column label="操作" width="100">
                                        <template #default="{ row }">
                                            <el-button size="small" type="success" @click="handlePay(row)">
                                                支付
                                            </el-button>
                                        </template>
                                    </el-table-column>
                                </el-table>
                            </el-scrollbar>
                        </div>
                    </el-card>
                    <el-drawer v-model="addDrawer" :direction="direction" :before-close="cancelClick" :size="'40%'">
                        <template #header>
                            <h4>支付详情</h4>
                        </template>
                        <template #default>
                            <div>
                                <el-form style="max-width: 600px" :model="formData" label-width="auto"
                                    class="demo-ruleForm" :size="formSize" status-icon>
                                    <el-form-item label="年份" prop="year">
                                        <el-input v-model="formData.year" disabled />
                                    </el-form-item>
                                    <el-form-item label="月份" prop="month">
                                        <el-input v-model="formData.month" disabled />
                                    </el-form-item>
                                    <el-form-item label="总费用" prop="total">
                                        <el-input v-model="formData.total" disabled />
                                    </el-form-item>
                                    <div v-if="isFirst">
                                        <el-form-item label="分期数目" prop="installments">
                                            <el-select v-model="formData.installments" placeholder="请选择分期数目">
                                                <el-option label="不分期" :value="1" />
                                                <el-option label="三期" :value="3" />
                                                <el-option label="六期" :value="6" />
                                                <el-option label="十二期" :value="12" />
                                            </el-select>
                                        </el-form-item>
                                        <el-form-item label="支付分期数目" prop="payedInstallments">
                                            <el-input-number v-model="formData.payedInstallments" :min="1"
                                                :max="formData.installments - 1" :precision="0" :step="1" />
                                        </el-form-item>
                                    </div>
                                    <div v-if="!isFirst">
                                        <el-form-item label="分期数目" prop="installments">
                                            <el-input v-model="formData.installments" disabled />
                                        </el-form-item>
                                        <el-form-item label="支付分期数目" prop="payedInstallments">
                                            <el-input-number v-model="formData.payedInstallments" :min="1"
                                                :max="formData.leftInstallments" :precision="0" :step="1" />
                                        </el-form-item>
                                    </div>
                                    <el-form-item label="本次需要支付的费用" prop="cost">
                                        <el-input
                                            :value="(formData.total / formData.installments * formData.payedInstallments).toFixed(2)"
                                            disabled />
                                    </el-form-item>
                                </el-form>
                            </div>
                        </template>
                        <template #footer>
                            <div style="flex: auto">
                                <el-button @click="cancelClick">取消</el-button>
                                <el-button type="primary" @click="confirmClick">支付</el-button>
                            </div>
                        </template>
                    </el-drawer>
                </div>
                <div v-if="isP" class="flex flex-col items-center justify-center">
                    <!-- 已支付 -->
                    <el-card class="box-card">
                        <template #header>
                            <div class="card-header">
                                <el-icon>
                                    <Coin />
                                </el-icon>
                                <span class="title">已支付费用</span>
                            </div>
                        </template>
                        <div class="card-content">
                            <el-scrollbar max-height="400px">
                                <el-table :data="ownerPaidData" border stripe style="width: 100%"
                                    :cell-style="{ height: '40px' }">
                                    <el-table-column type="expand">
                                        <template #default="props">
                                            <div m="2" class="flex flex-col items-center space-y-4">
                                                <h3 class="text-lg text-black font-bold">费用详情</h3>
                                                <el-row class="w-3/4 h-1/2">
                                                    <el-col :span="8">
                                                        <div class="statistic-card">
                                                            <el-statistic :value="props.row.water" :precision="2">
                                                                <template #title>
                                                                    <div
                                                                        style="display: inline-flex; align-items: center">
                                                                        水费
                                                                        <el-tooltip effect="dark" content="单位——元"
                                                                            placement="top">
                                                                            <el-icon style="margin-left: 4px"
                                                                                :size="12">
                                                                                <Warning />
                                                                            </el-icon>
                                                                        </el-tooltip>
                                                                    </div>
                                                                </template>
                                                            </el-statistic>
                                                        </div>
                                                    </el-col>
                                                    <el-col :span="8">
                                                        <div class="statistic-card">
                                                            <el-statistic :value="props.row.electricity" :precision="2">
                                                                <template #title>
                                                                    <div
                                                                        style="display: inline-flex; align-items: center">
                                                                        电费
                                                                        <el-tooltip effect="dark" content="单位——元"
                                                                            placement="top">
                                                                            <el-icon style="margin-left: 4px"
                                                                                :size="12">
                                                                                <Warning />
                                                                            </el-icon>
                                                                        </el-tooltip>
                                                                    </div>
                                                                </template>
                                                            </el-statistic>
                                                        </div>
                                                    </el-col>
                                                    <el-col :span="8">
                                                        <div class="statistic-card">
                                                            <el-statistic :value="props.row.gas" :precision="2">
                                                                <template #title>
                                                                    <div
                                                                        style="display: inline-flex; align-items: center">
                                                                        燃气费
                                                                        <el-tooltip effect="dark" content="单位——元"
                                                                            placement="top">
                                                                            <el-icon style="margin-left: 4px"
                                                                                :size="12">
                                                                                <Warning />
                                                                            </el-icon>
                                                                        </el-tooltip>
                                                                    </div>
                                                                </template>
                                                            </el-statistic>
                                                        </div>
                                                    </el-col>
                                                </el-row>
                                                <el-row class="w-3/4 h-1/2">
                                                    <el-col :span="12">
                                                        <div class="statistic-card">
                                                            <el-statistic :value="props.row.heater" :precision="2">
                                                                <template #title>
                                                                    <div
                                                                        style="display: inline-flex; align-items: center">
                                                                        暖气费
                                                                        <el-tooltip effect="dark" content="单位——元"
                                                                            placement="top">
                                                                            <el-icon style="margin-left: 4px"
                                                                                :size="12">
                                                                                <Warning />
                                                                            </el-icon>
                                                                        </el-tooltip>
                                                                    </div>
                                                                </template>
                                                            </el-statistic>
                                                        </div>
                                                    </el-col>
                                                    <el-col :span="12">
                                                        <div class="statistic-card" :precision="2">
                                                            <el-statistic :value="props.row.estate">
                                                                <template #title>
                                                                    <div
                                                                        style="display: inline-flex; align-items: center">
                                                                        物业费
                                                                        <el-tooltip effect="dark" content="单位——元"
                                                                            placement="top">
                                                                            <el-icon style="margin-left: 4px"
                                                                                :size="12">
                                                                                <Warning />
                                                                            </el-icon>
                                                                        </el-tooltip>
                                                                    </div>
                                                                </template>
                                                            </el-statistic>
                                                        </div>
                                                    </el-col>
                                                </el-row>
                                            </div>
                                        </template>
                                    </el-table-column>
                                    <el-table-column prop="year" label="年份" width="160" sortable />
                                    <el-table-column prop="month" label="月份" width="140" sortable />
                                    <el-table-column prop="installments" label="分期数目" width="140" sortable />
                                    <el-table-column prop="total" label="缴费金额/元" width="120" sortable />
                                </el-table>
                            </el-scrollbar>
                        </div>
                    </el-card>
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
import { ComponentSize, DrawerProps, ElMessage, ElMessageBox } from 'element-plus';
import { getOwner, getUserOwner } from '../api/owner';
import { getAllMonthData } from '../api/monthData';
import { addPayment, getPaymentByMonthData, getUnpaid, selectOwnerPaid, selectOwnerUnpaid, updatePayment } from '../api/payment';
import { addOwnerPayment, selectByPayment, updateOwnerPayment } from '../api/ownerPayment';

// ----------------------------------------------
// 折叠面板逻辑
const isCollapse = ref(false)
const collapseMode = () => {
    isCollapse.value = !isCollapse.value
}

// ----------------------------------------------
// 导航栏
// 第一级选择采用页面跳转，第二级选择采用页面内切换
const currentPage = ref('2-1')
const isNo = ref(true)
const isSe = ref(false)
const isU = ref(true)
const isP = ref(false)
const pageSelect = (key: any) => {
    switch (key.charAt(0)) {
        case '1':
            router.push('/usage')
            break
        case '3':
            router.push('/statistic')
            break
        case '4':
            router.push('/main')
            break
        default:
            isNo.value = key.slice(-1) == '1'
            isSe.value = key.slice(-1) == '2'
            isU.value = key.slice(-1) == '1'
            isP.value = key.slice(-1) == '2'
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

// ----------------------------------------------
// 页面加载时获取用户信息
// 注意并发！！！！！
onMounted(async () => {
    try {
        await fetchData()
        if (isAdmin.value) {
            await fetchUnNoData()
            await fetchUnPaData()
        } else {
            await fetchOwnerUnPaData()
            await fetchOwnerPaData()
        }
    } catch (error) {
        console.error('初始化数据时发生错误:', error)
    }
})

// ----------------------------------------------
// 获取所有没有发出订单的抄表数据并整合呈现
const handleClick = (row: { oid: any; year: any; month: any; water: number; water_price: number; electricity: number; electricity_price: number; gas: number; gas_price: number; heater: number; heater_price: number; area: number; estate_price: number; }) => {
    ElMessageBox.confirm(
        '确认通知？',
        '添加订单信息',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            addPayment(row.oid, row.year, row.month, 0,
                row.water * row.water_price, row.electricity * row.electricity_price,
                row.gas * row.gas_price, row.heater * row.heater_price,
                row.area * row.estate_price).then(response => {
                    if (response.data.state == 200) {
                        ElMessage({
                            type: 'success',
                            message: '添加成功',
                        })
                        window.location.reload();
                    }
                })
                .catch(response => {
                    console.log(response)
                    ElMessage({
                        type: 'error',
                        message: '添加异常',
                    })
                })
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '取消通知',
            })
        })
}
interface Data {
    oid: string
    year: string
    month: string
    date: string
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
    televisionConsumption: number
    area: number
    estate_price: number
}
const uninfoData = ref<Data[]>([])
const fetchUnNoData = async () => {
    try {
        const monthDataResponse = await getAllMonthData();
        if (monthDataResponse.data.state === 200) {
            const monthDataList = monthDataResponse.data.data
            // 使用 Promise.all 并行请求
            const requests = monthDataList.map(async (monthData: { waterConsumption?: any; WATER_PRICE?: any; electricityConsumption?: any; ELECTRICITY_PRICE?: any; gasConsumption?: any; GAS_PRICE?: any; heaterConsumption?: any; HEATER_PRICE?: any; televisionConsumption?: any; ESTATE_PRICE?: any; year?: any; month?: any; oid?: any; }) => {
                let { year, month, oid } = monthData
                const paymentResponse = await getPaymentByMonthData(oid, year, month)
                if (paymentResponse.data.state != 200 || paymentResponse.data.state == 200 && paymentResponse.data.data == null) {
                    const ownerResponse = await getOwner(oid)
                    if (ownerResponse.data.state == 200) {
                        let area = ownerResponse.data.data.area
                        let name = ownerResponse.data.data.name
                        let building = ownerResponse.data.data.buildingNumber
                        let floor = ownerResponse.data.data.floorNumber
                        uninfoData.value.push({
                            oid: oid,
                            year: year,
                            month: month,
                            water: monthData.waterConsumption,
                            water_price: monthData.WATER_PRICE,
                            electricity: monthData.electricityConsumption,
                            electricity_price: monthData.ELECTRICITY_PRICE,
                            gas: monthData.gasConsumption,
                            gas_price: monthData.GAS_PRICE,
                            heater: monthData.heaterConsumption,
                            heater_price: monthData.HEATER_PRICE,
                            televisionConsumption: monthData.televisionConsumption,
                            area: area,
                            estate_price: monthData.ESTATE_PRICE,
                            date: year + '-' + month,
                            name: name,
                            address: building + '-' + floor
                        })
                    } else {
                        console.error('Owner response failed', ownerResponse)
                    }
                }
            })
            // 等待所有请求完成
            await Promise.all(requests)
        } else {
            console.error(monthDataResponse);
            ElMessage({
                type: 'error',
                message: '信息获取异常',
            });
            router.push('/')
        }
    } catch (error) {
        console.error(error)
        ElMessage({
            type: 'error',
            message: '信息获取异常',
        });
        router.push('/')
    }
}

// ----------------------------------------------
// 获取所有未完成的支付数据并整合呈现
interface Payment {
    name: string
    address: string
    date: string
    water: number
    electricity: number
    gas: number
    heater: number
    estate: number
    total: number
    installments: number
    payedInstallments: number
}
const unpaidData = ref<Payment[]>([])
const fetchUnPaData = async () => {
    try {
        const paymentResponse = await getUnpaid();
        if (paymentResponse.data.state === 200) {
            const paymentList = paymentResponse.data.data
            // 使用 Promise.all 并行请求
            const requests = paymentList.map(async (payment: { isPayed: number; id: any; oid: any; year: any; month: any; waterCost: any; electricityCost: any; gasCost: any; heaterCost: any; estateCost: any; }) => {
                if (payment.isPayed == 0) {
                    let id = payment.id
                    const ownerPaymentResponse = await selectByPayment(id)
                    if (ownerPaymentResponse.data.state == 200) {
                        const ownerPayment = ownerPaymentResponse.data.data
                        const ownerResponse = await getOwner(payment.oid)
                        if (ownerResponse.data.state == 200) {
                            const owner = ownerResponse.data.data
                            unpaidData.value.push({
                                date: payment.year + '-' + payment.month,
                                water: payment.waterCost,
                                electricity: payment.electricityCost,
                                gas: payment.gasCost,
                                heater: payment.heaterCost,
                                estate: payment.estateCost,
                                total: (payment.waterCost + payment.electricityCost + payment.gasCost + payment.heaterCost + payment.estateCost).toFixed(2),
                                installments: ownerPayment == null ? -1 : ownerPayment.installments,
                                payedInstallments: ownerPayment == null ? -1 : ownerPayment.payedInstallments,
                                name: owner.name,
                                address: owner.buildingNumber + '-' + owner.floorNumber
                            })
                        } else {
                            console.error('Owner response failed', ownerResponse)
                        }
                    } else {
                        console.error('OwnerPayment response failed', ownerPaymentResponse)
                    }
                }
            })
            // 等待所有请求完成
            await Promise.all(requests)
        } else {
            console.error(paymentResponse);
            ElMessage({
                type: 'error',
                message: '信息获取异常',
            });
            router.push('/')
        }
    } catch (error) {
        console.error(error)
        ElMessage({
            type: 'error',
            message: '信息获取异常',
        });
        router.push('/')
    }
}

// ----------------------------------------------
// 获取用户未支付的数据-所有未完成的支付均会显示
interface OwnerPayment {
    pid: string
    year: string
    month: string
    water: number
    electricity: number
    gas: number
    heater: number
    estate: number
    total: number
    installments: number
    payedInstallments: number
    leftInstallments: number
}
const ownerUnpaidData = ref<OwnerPayment[]>([])
const fetchOwnerUnPaData = async () => {
    try {
        console.log(ownerId.value)
        const paymentResponse = await selectOwnerUnpaid(ownerId.value);
        if (paymentResponse.data.state === 200) {
            const paymentList = paymentResponse.data.data
            // 使用 Promise.all 并行请求
            const requests = paymentList.map(async (payment: { isPayed: number; id: any; oid: any; year: any; month: any; waterCost: any; electricityCost: any; gasCost: any; heaterCost: any; estateCost: any; }) => {
                let id = payment.id
                const ownerPaymentResponse = await selectByPayment(id)
                if (ownerPaymentResponse.data.state == 200) {
                    const ownerPayment = ownerPaymentResponse.data.data
                    ownerUnpaidData.value.push({
                        year: payment.year,
                        month: payment.month,
                        water: payment.waterCost,
                        electricity: payment.electricityCost,
                        gas: payment.gasCost,
                        heater: payment.heaterCost,
                        estate: payment.estateCost,
                        total: (payment.waterCost + payment.electricityCost + payment.gasCost + payment.heaterCost + payment.estateCost).toFixed(2),
                        installments: ownerPayment == null ? -1 : ownerPayment.installments,
                        payedInstallments: ownerPayment == null ? -1 : ownerPayment.payedInstallments,
                        pid: id,
                        leftInstallments: ownerPayment == null ? -1 : ownerPayment.installments - ownerPayment.payedInstallments
                    })
                } else {
                    console.error('OwnerPayment response failed', ownerPaymentResponse)
                }
            })
            // 等待所有请求完成
            await Promise.all(requests)
        } else {
            console.error(paymentResponse);
            ElMessage({
                type: 'error',
                message: '信息获取异常',
            });
            router.push('/')
        }
    } catch (error) {
        console.error(error)
        ElMessage({
            type: 'error',
            message: '信息获取异常',
        });
        router.push('/')
    }
}

// ----------------------------------------------
// 支付操作
const addDrawer = ref(false)
const formData = reactive<OwnerPayment>({
    water: 0,
    electricity: 0,
    gas: 0,
    heater: 0,
    estate: 0,
    total: 0,
    installments: 0,
    payedInstallments: 0,
    year: '',
    month: '',
    pid: '',
    leftInstallments: 0
})
const formSize = ref<ComponentSize>('default')
const direction = ref<DrawerProps['direction']>('rtl')
const isFirst = ref<boolean>(false)
const handlePay = (row: OwnerPayment) => {
    addDrawer.value = true
    Object.assign(formData, row)
    isFirst.value = formData.installments == -1
    formData.installments = formData.installments == -1 ? 1 : formData.installments
    formData.payedInstallments = 0
    formData.leftInstallments = formData.leftInstallments == -1 ? 1 : formData.leftInstallments
}
function cancelClick() {
    ElMessageBox.confirm('确定要离开吗?您做出的修改将不会被保存！', '退出支付', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
    })
        .then(() => {
            addDrawer.value = false
        })
}
function confirmClick() {
    ElMessageBox.confirm(`确定要提交支付信息吗?`, "提交", {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'info',
    })
        .then(() => {
            console.log(formData)
            if (isFirst.value) {
                // 创建记录
                addOwnerPayment(formData.pid, 2, formData.installments, formData.payedInstallments).then(response => {
                    if (response.data.state == 200) {
                        ElMessage({
                            type: 'success',
                            message: '支付成功',
                        })
                        window.location.reload()
                    } else {
                        console.log(response)
                        ElMessage({
                            type: 'error',
                            message: '支付失败',
                        })
                    }
                })
                    .catch(() => {
                        ElMessage({
                            type: 'error',
                            message: '支付异常，请稍后重试',
                        })
                    })
            } else {
                // 更新记录
                updateOwnerPayment(formData.pid, '', '', formData.installments - formData.leftInstallments + formData.payedInstallments).then(response => {
                    if (response.data.state == 200) {
                        ElMessage({
                            type: 'success',
                            message: '支付成功',
                        })
                        if (formData.leftInstallments == formData.payedInstallments) {
                            // 更新支付状态
                            updatePayment(formData.pid, '', '', 1, '', '', '', '', '').catch(response => {
                                console.log(response)
                                ElMessage({
                                    type: 'error',
                                    message: '支付异常，请稍后重试',
                                })
                            })
                        }
                        window.location.reload()
                    } else {
                        console.log(response)
                        ElMessage({
                            type: 'error',
                            message: '支付失败',
                        })
                    }
                })
                    .catch(() => {
                        ElMessage({
                            type: 'error',
                            message: '支付异常，请稍后重试',
                        })
                    })
            }
        })
}

// ----------------------------------------------
// 获取用户完成支付的数据
const ownerPaidData = ref<OwnerPayment[]>([])
const fetchOwnerPaData = async () => {
    try {
        const paymentResponse = await selectOwnerPaid(ownerId.value);
        console.log(paymentResponse)
        if (paymentResponse.data.state === 200) {
            const paymentList = paymentResponse.data.data
            // 使用 Promise.all 并行请求
            const requests = paymentList.map(async (payment: { isPayed: number; id: any; oid: any; year: any; month: any; waterCost: any; electricityCost: any; gasCost: any; heaterCost: any; estateCost: any; }) => {
                let id = payment.id
                const ownerPaymentResponse = await selectByPayment(id)
                if (ownerPaymentResponse.data.state == 200) {
                    const ownerPayment = ownerPaymentResponse.data.data
                    ownerPaidData.value.push({
                        year: payment.year,
                        month: payment.month,
                        water: payment.waterCost,
                        electricity: payment.electricityCost,
                        gas: payment.gasCost,
                        heater: payment.heaterCost,
                        estate: payment.estateCost,
                        total: (payment.waterCost + payment.electricityCost + payment.gasCost + payment.heaterCost + payment.estateCost).toFixed(2),
                        installments: ownerPayment == null ? -1 : ownerPayment.installments,
                        payedInstallments: ownerPayment == null ? -1 : ownerPayment.payedInstallments,
                        pid: id,
                        leftInstallments: ownerPayment == null ? -1 : ownerPayment.installments - ownerPayment.payedInstallments
                    })
                } else {
                    console.error('OwnerPayment response failed', ownerPaymentResponse)
                }
            })
            // 等待所有请求完成
            await Promise.all(requests)
        } else {
            console.error(paymentResponse);
            ElMessage({
                type: 'error',
                message: '信息获取异常',
            });
            router.push('/')
        }
    } catch (error) {
        console.error(error)
        ElMessage({
            type: 'error',
            message: '信息获取异常',
        });
        router.push('/')
    }
}
</script>

<style>
.menu:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
}
</style>