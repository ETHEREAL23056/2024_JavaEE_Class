<!-- 用量主界面 -->
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
                <div v-if="isWE" class="flex flex-col items-center justify-center">
                    <!-- 水电用量 -->
                    <el-text class="m-3 text-3xl text-black font-bold">水电用量统计</el-text>
                    <el-text class="m-3 text-1xl text-black font-bold">仅显示当年的内容，更多数据请在历年数据页面中查询</el-text>
                    <el-table :data="WE_tableData" style="width: 100%">
                        <el-table-column type="expand">
                            <template #default="props">
                                <div m="2" class="flex flex-col items-center space-y-4">
                                    <h3 class="text-lg text-black font-bold">用量详情</h3>
                                    <el-table :data="props.row.children">
                                        <el-table-column label="业主姓名" prop="name" sortable />
                                        <el-table-column label="住址" prop="address" sortable />
                                        <el-table-column label="用水量" prop="water" sortable />
                                        <el-table-column label="用电量" prop="electricity" sortable />
                                        <!-- 添加操作按钮 -->
                                    </el-table>
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column label="抄录时间" prop="date" sortable />
                        <el-table-column label="水费单价" prop="water_price" sortable />
                        <el-table-column label="电费单价" prop="electricity_price" sortable />
                    </el-table>
                </div>
                <div v-if="isG" class="flex flex-col items-center justify-center">
                    <!-- 燃气用量 -->
                    <el-text class="m-3 text-3xl text-black font-bold">燃气用量统计</el-text>
                    <el-text class="m-3 text-1xl text-black font-bold">仅显示当年的内容，更多数据请在历年数据页面中查询</el-text>
                    <el-table :data="G_tableData" style="width: 100%">
                        <el-table-column type="expand">
                            <template #default="props">
                                <!-- 标注单价信息 -->
                                <div m="2" class="flex flex-col items-center space-y-4">
                                    <h3 class="text-lg text-black font-bold">用量详情</h3>
                                    <el-table :data="props.row.children">
                                        <el-table-column label="业主姓名" prop="name" sortable />
                                        <el-table-column label="住址" prop="address" sortable />
                                        <el-table-column label="燃气用量" prop="gas" sortable />
                                        <!-- 添加操作按钮 -->
                                    </el-table>
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column label="抄录时间" prop="date" sortable />
                        <el-table-column label="燃气费单价" prop="gas_price" sortable />
                    </el-table>
                </div>
                <div v-if="isH" class="flex flex-col items-center justify-center">
                    <!-- 暖气用量 -->
                    <el-text class="m-3 text-3xl text-black font-bold">暖气用量统计</el-text>
                    <el-text class="m-3 text-1xl text-black font-bold">仅显示当年的内容，更多数据请在历年数据页面中查询</el-text>
                    <el-table :data="H_tableData" style="width: 100%">
                        <el-table-column type="expand">
                            <template #default="props">
                                <!-- 标注单价信息 -->
                                <div m="2" class="flex flex-col items-center space-y-4">
                                    <h3 class="text-lg text-black font-bold">用量详情</h3>
                                    <el-table :data="props.row.children">
                                        <el-table-column label="业主姓名" prop="name" sortable />
                                        <el-table-column label="住址" prop="address" sortable />
                                        <el-table-column label="暖气用量" prop="heater" sortable />
                                        <!-- 添加操作按钮 -->
                                    </el-table>
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column label="抄录时间" prop="date" sortable />
                        <el-table-column label="暖气费单价" prop="heater_price" sortable />
                    </el-table>
                </div>
                <div v-if="isPre" class="flex flex-col items-center justify-center">
                    <!-- 历年数据查询 -->
                    <el-text class="m-3 text-3xl text-black font-bold">历年数据查询</el-text>
                    <div class="m-3 space-x-2">
                        <el-date-picker v-model="dateRange" type="monthrange" unlink-panels range-separator="到"
                            start-placeholder="开始月份" end-placeholder="结束月份" :shortcuts="shortcuts" />
                        <el-button type="primary" @click="applyFiltersAdmin">筛选</el-button>
                    </div>
                    <el-table :data="selectData" style="width: 100%" :row-class-name="tableRowClassName">
                        <el-table-column prop="date" label="日期" sortable />
                        <el-table-column prop="name" label="业主姓名" sortable />
                        <el-table-column prop="address" label="地址" sortable />
                        <el-table-column label="用水量" prop="water" sortable />
                        <el-table-column label="用电量" prop="electricity" sortable />
                        <el-table-column label="燃气用量" prop="gas" sortable />
                        <el-table-column label="暖气用量" prop="heater" sortable />
                    </el-table>
                </div>
                <div v-if="isAdd" class="flex flex-col items-center justify-center">
                    <!-- 抄表记录添加 -->
                    <el-text class="m-3 text-3xl text-black font-bold">抄表记录添加</el-text>
                    <el-text
                        class="m-3 text-1xl text-black font-bold">请在下方填写抄录的用户消费信息，请确保检查无误后再进行提交，避免产生不必要的分歧</el-text>
                    <div class="m-3 w-3/4 border border-gray-200 rounded-md flex flex-col items-center">
                        <el-form :model="form" label-width="auto" class="m-3 w-3/4">
                            <el-form-item label="地址">
                                <el-cascader v-model="value" :options="options" :props="props" @change="addAddress" />
                            </el-form-item>
                            <el-form-item label="时间">
                                <el-date-picker v-model="form.date" type="month" placeholder="选择抄表时间" />
                            </el-form-item>
                            <el-form-item label="用水量">
                                <el-input-number v-model="form.waterConsumption" :min="0" :max="1000" :precision="2"
                                    :step="0.1" />
                                <el-text class="mx-2">立方米</el-text>
                                <el-text class="mx-2">单价</el-text>
                                <el-input-number v-model="form.WATER_PRICE" :min="0" :max="10" :precision="2"
                                    :step="0.1" />
                                <el-text class="mx-2">元/立方米</el-text>
                            </el-form-item>
                            <el-form-item label="用电量">
                                <el-input-number v-model="form.electricityConsumption" :min="0" :max="1000"
                                    :precision="2" :step="0.1" />
                                <el-text class="mx-2">千瓦时</el-text>
                                <el-text class="mx-2">单价</el-text>
                                <el-input-number v-model="form.ELECTRICITY_PRICE" :min="0" :max="10" :precision="2"
                                    :step="0.1" />
                                <el-text class="mx-2">元/千瓦时</el-text>
                            </el-form-item>
                            <el-form-item label="燃气用量">
                                <el-input-number v-model="form.gasConsumption" :min="0" :max="1000" :precision="2"
                                    :step="0.1" />
                                <el-text class="mx-2">立方米</el-text>
                                <el-text class="mx-2">单价</el-text>
                                <el-input-number v-model="form.GAS_PRICE" :min="0" :max="10" :precision="2"
                                    :step="0.1" />
                                <el-text class="mx-2">元/立方米</el-text>
                            </el-form-item>
                            <el-form-item label="暖气用量">
                                <el-input-number v-model="form.heaterConsumption" :min="0" :max="1000" :precision="2"
                                    :step="0.1" />
                                <el-text class="mx-2">立方米</el-text>
                                <el-text class="mx-2">单价</el-text>
                                <el-input-number v-model="form.HEATER_PRICE" :min="0" :max="10" :precision="2"
                                    :step="0.1" />
                                <el-text class="mx-2">元/立方米</el-text>
                            </el-form-item>
                            <el-form-item label="物业费用">
                                <el-input-number v-model="form.ESTATE_PRICE" :min="0" :max="10" :precision="2"
                                    :step="0.1" />
                                <el-text class="mx-2">元/平方米</el-text>
                            </el-form-item>
                        </el-form>
                        <div class="mb-3">
                            <el-button type="primary" @click="onSubmit">绑定</el-button>
                            <el-button @click="resetForm">重置</el-button>
                        </div>
                    </div>
                </div>
            </div>
            <div v-if="!isAdmin">
                <div v-if="isWE" class="flex flex-col items-center justify-center">
                    <!-- 水电用量 -->
                    <el-text class="m-3 text-3xl text-black font-bold">水电用量统计</el-text>
                    <el-table :data="WE_tableData" style="width: 100%">
                        <el-table-column type="expand">
                            <template #default="props">
                                <div m="2" class="flex flex-col items-center space-y-4">
                                    <h3 class="text-lg text-black font-bold">用量详情</h3>
                                    <el-row class="w-3/4">
                                        <el-col :span="12">
                                            <div class="statistic-card">
                                                <el-statistic :value="props.row.children[0].water">
                                                    <template #title>
                                                        <div style="display: inline-flex; align-items: center">
                                                            您当月的生活用水量
                                                            <el-tooltip effect="dark" content="单位——立方米" placement="top">
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
                                                <el-statistic :value="props.row.children[0].electricity">
                                                    <template #title>
                                                        <div style="display: inline-flex; align-items: center">
                                                            您当月的生活用电量
                                                            <el-tooltip effect="dark" content="单位——千瓦时" placement="top">
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
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column label="抄录时间" prop="date" sortable />
                        <el-table-column label="水费单价" prop="water_price" sortable />
                        <el-table-column label="电费单价" prop="electricity_price" sortable />
                    </el-table>
                </div>
                <div v-if="isG" class="flex flex-col items-center justify-center">
                    <!-- 燃气用量 -->
                    <el-text class="m-3 text-3xl text-black font-bold">燃气用量统计</el-text>
                    <el-table :data="G_tableData" style="width: 100%">
                        <el-table-column type="expand">
                            <template #default="props">
                                <!-- 标注单价信息 -->
                                <div m="2" class="flex flex-col items-center space-y-4">
                                    <h3 class="text-lg text-black font-bold">用量详情</h3>
                                    <div class="statistic-card">
                                        <el-statistic :value="props.row.children[0].gas">
                                            <template #title>
                                                <div style="display: inline-flex; align-items: center">
                                                    您当月的生活燃气量
                                                    <el-tooltip effect="dark" content="单位——立方米" placement="top">
                                                        <el-icon style="margin-left: 4px" :size="12">
                                                            <Warning />
                                                        </el-icon>
                                                    </el-tooltip>
                                                </div>
                                            </template>
                                        </el-statistic>
                                    </div>
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column label="抄录时间" prop="date" sortable />
                        <el-table-column label="燃气费单价" prop="gas_price" sortable />
                    </el-table>
                </div>
                <div v-if="isH" class="flex flex-col items-center justify-center">
                    <!-- 暖气用量 -->
                    <el-text class="m-3 text-3xl text-black font-bold">暖气用量统计</el-text>
                    <el-table :data="H_tableData" style="width: 100%">
                        <el-table-column type="expand">
                            <template #default="props">
                                <!-- 标注单价信息 -->
                                <div m="2" class="flex flex-col items-center space-y-4">
                                    <h3 class="text-lg text-black font-bold">用量详情</h3>
                                    <div class="statistic-card">
                                        <el-statistic :value="props.row.children[0].heater">
                                            <template #title>
                                                <div style="display: inline-flex; align-items: center">
                                                    您当月的生活暖气量
                                                    <el-tooltip effect="dark" content="单位——立方米" placement="top">
                                                        <el-icon style="margin-left: 4px" :size="12">
                                                            <Warning />
                                                        </el-icon>
                                                    </el-tooltip>
                                                </div>
                                            </template>
                                        </el-statistic>
                                    </div>
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column label="抄录时间" prop="date" sortable />
                        <el-table-column label="暖气费单价" prop="heater_price" sortable />
                    </el-table>
                </div>
                <div v-if="isPre" class="flex flex-col items-center justify-center">
                    <!-- 历年数据查询 -->
                    <el-text class="m-3 text-3xl text-black font-bold">历年数据查询</el-text>
                    <div class="m-3 space-x-2">
                        <el-date-picker v-model="dateRange" type="monthrange" unlink-panels range-separator="到"
                            start-placeholder="开始月份" end-placeholder="结束月份" :shortcuts="shortcuts" />
                        <el-button type="primary" @click="applyFiltersUser">筛选</el-button>
                    </div>
                    <el-table :data="selectData" style="width: 100%" :row-class-name="tableRowClassName">
                        <el-table-column prop="date" label="日期" sortable />
                        <el-table-column label="用水量" prop="water" sortable />
                        <el-table-column label="用电量" prop="electricity" sortable />
                        <el-table-column label="燃气用量" prop="gas" sortable />
                        <el-table-column label="暖气用量" prop="heater" sortable />
                    </el-table>
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
import { getOwner, getOwnerByLoc, getUserOwner } from '../api/owner';
import { addMonthData, selectMonthData, selectOwnerMonth, selectOwnerYear, selectYearData } from '../api/monthData';

// ----------------------------------------------
// 折叠面板逻辑
const isCollapse = ref(false)
const collapseMode = () => {
    isCollapse.value = !isCollapse.value
}

// ----------------------------------------------
// 导航栏
const currentPage = ref('1-1')
const isWE = ref(true)
const isG = ref(false)
const isH = ref(false)
const isPre = ref(false)
const isAdd = ref(false)
const pageSelect = (key: any) => {
    switch (key.charAt(0)) {
        case '2':
            router.push('/bill')
            break
        case '3':
            router.push('/statistic')
            break
        case '4':
            router.push('/main')
            break
        default:
            isWE.value = key.slice(-1) == '1'
            isG.value = key.slice(-1) == '2'
            isH.value = key.slice(-1) == '3'
            isPre.value = key.slice(-1) == '4'
            isAdd.value = key.slice(-1) == '5'
    }
}

// ----------------------------------------------
// 获取用户信息
const userId = ref<string>('')
const username = ref<string>('')
const isAdmin = ref<boolean>(false)
const hasBound = ref<boolean>(false)
const ownerId = ref<string>('')
const monthDataList = ref<any>([])
interface WandEcur {
    date: string
    name: string
    water: string
    electricity: string
    address: string
}
interface WE {
    date: string
    water_price: string
    electricity_price: string
    hasChildren?: boolean
    children?: WandEcur[]
}
interface Gcur {
    date: string
    name: string
    gas: string
    address: string
}
interface G {
    date: string
    gas_price: string
    hasChildren?: boolean
    children?: Gcur[]
}
interface Hcur {
    date: string
    name: string
    heater: string
    address: string
}
interface H {
    date: string
    heater_price: string
    hasChildren?: boolean
    children?: Hcur[]
}
const WE_tableData = ref<WE[]>([])
const G_tableData = ref<G[]>([])
const H_tableData = ref<H[]>([])
const fetchData = async () => {
    try {
        const userResponse = await fetchUser();
        if (userResponse.data.state == 200) {
            const user = userResponse.data.data
            userId.value = user.uid
            username.value = user.username
            isAdmin.value = user.role === 'admin'

            const ownerResponse = await getUserOwner(userId.value)
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

            const monthDataResonse = isAdmin.value ? await selectYearData('2024') : await selectOwnerYear(ownerId.value, '2024')
            if (monthDataResonse.data.state == 200) {
                monthDataList.value = monthDataResonse.data.data
                const WEdict = {}
                const Gdict = {}
                const Hdict = {}
                const promises = monthDataList.value.map(async (item: any) => {
                    const ownerResponse = await getOwner(item.oid);
                    if (ownerResponse.data.state === 200) {
                        const owner = ownerResponse.data.data
                        const WE: WandEcur = {
                            date: item.year + '-' + item.month,
                            name: owner.name,
                            water: item.waterConsumption,
                            electricity: item.electricityConsumption,
                            address: owner.buildingNumber + '-' + owner.floorNumber,
                        }
                        if (WE.date in WEdict) {
                            WEdict[WE.date].children.push(WE)
                        } else {
                            WEdict[WE.date] = {
                                date: WE.date,
                                water_price: item.WATER_PRICE,
                                electricity_price: item.ELECTRICITY_PRICE,
                                children: [WE]
                            }
                        }
                        const G: Gcur = {
                            date: item.year + '-' + item.month,
                            name: owner.name,
                            gas: item.gasConsumption,
                            address: owner.buildingNumber + '-' + owner.floorNumber,
                        }
                        if (G.date in Gdict) {
                            Gdict[G.date].children.push(G)
                        } else {
                            Gdict[G.date] = {
                                date: G.date,
                                gas_price: item.GAS_PRICE,
                                children: [G]
                            }
                        }
                        const H: Hcur = {
                            date: item.year + '-' + item.month,
                            name: owner.name,
                            heater: item.heaterConsumption,
                            address: owner.buildingNumber + '-' + owner.floorNumber,
                        }
                        if (H.date in Hdict) {
                            Hdict[H.date].children.push(H)
                        } else {
                            Hdict[H.date] = {
                                date: H.date,
                                heater_price: item.HEATER_PRICE,
                                children: [H]
                            }
                        }
                    } else {
                        console.error(ownerResponse)
                        ElMessage({
                            type: 'error',
                            message: '数据信息获取异常',
                        });
                        router.push('/')
                    }
                });

                // 等待所有的异步请求完成
                await Promise.all(promises)
                for (const key in WEdict) {
                    if (WEdict.hasOwnProperty(key)) {
                        WE_tableData.value.push(WEdict[key])
                    }
                }
                for (const key in Gdict) {
                    if (Gdict.hasOwnProperty(key)) {
                        G_tableData.value.push(Gdict[key])
                    }
                }
                for (const key in Hdict) {
                    if (Hdict.hasOwnProperty(key)) {
                        H_tableData.value.push(Hdict[key])
                    }
                }
            } else {
                console.error(monthDataResonse)
                ElMessage({
                    type: 'error',
                    message: '数据信息获取异常',
                });
                router.push('/')
            }
        } else {
            console.error(userResponse)
            ElMessage({
                type: 'error',
                message: '用户信息获取异常',
            });
            router.push('/')
        }
    } catch (error) {
        console.error(error)
        ElMessage({
            type: 'error',
            message: '用户信息获取异常',
        });
        router.push('/')
    }
}

// 页面加载时获取信息
onMounted(() => {
    fetchData()
})

// ----------------------------------------------
// 月份筛选
const shortcuts = [{
    text: '这个月',
    value: [new Date(), new Date()],
}, {
    text: '今年',
    value: () => {
        const end = new Date()
        const start = new Date(new Date().getFullYear(), 0)
        return [start, end]
    },
}, {
    text: '过去六个月',
    value: () => {
        const end = new Date()
        const start = new Date()
        start.setMonth(start.getMonth() - 6)
        return [start, end]
    },
}, {
    text: '过去一年',
    value: () => {
        const end = new Date()
        const start = new Date()
        start.setMonth(start.getMonth() - 12)
        return [start, end]
    },
}, {
    text: '过去一年半',
    value: () => {
        const end = new Date()
        const start = new Date()
        start.setMonth(start.getMonth() - 18)
        return [start, end]
    },
}, {
    text: '过去两年',
    value: () => {
        const end = new Date()
        const start = new Date()
        start.setMonth(start.getMonth() - 24)
        return [start, end]
    },
}, {
    text: '过去三年',
    value: () => {
        const end = new Date()
        const start = new Date()
        start.setMonth(start.getMonth() - 36)
        return [start, end]
    },
}, {
    text: '过去五年',
    value: () => {
        const end = new Date()
        const start = new Date()
        start.setMonth(start.getMonth() - 60)
        return [start, end]
    },
}
]
interface Total {
    date: string
    name: string
    water: string
    electricity: string
    gas: string
    heater: string
    address: string
}
const tableRowClassName = ({
    row,
    rowIndex,
}: {
    row: Total
    rowIndex: number
}) => {
    if (rowIndex === 1) {
        return 'warning-row'
    } else if (rowIndex === 3) {
        return 'success-row'
    }
    return ''
}
const selectData = ref<Total[]>([])
const dateRange = ref<[string, string] | null>(null)
const applyFiltersAdmin = async () => {
    selectData.value.length = 0; // 清空旧数据

    if (dateRange.value) {
        const [start, end] = dateRange.value;
        const startDate = new Date(start);
        const endDate = new Date(end);
        const months = getAllMonthsBetweenDates(startDate, endDate);

        // 创建所有月份的数据请求 Promise
        const promises = months.map(async (timeStamp) => {
            const [year, month] = timeStamp.split('-');
            try {
                const monthDataResponse = await selectMonthData(year, month);

                if (monthDataResponse.data.state == 200 && monthDataResponse.data.data != null) {
                    const monthDataList = monthDataResponse.data.data;
                    for (const monthData of monthDataList) {
                        try {
                            const ownerResponse = await getOwner(monthData.oid);
                            if (ownerResponse.data.state == 200) {
                                const owner = ownerResponse.data.data;
                                selectData.value.push({
                                    date: timeStamp,
                                    name: owner.name,
                                    water: monthData.waterConsumption,
                                    electricity: monthData.electricityConsumption,
                                    gas: monthData.gasConsumption,
                                    heater: monthData.heaterConsumption,
                                    address: `${owner.buildingNumber}-${owner.floorNumber}`,
                                });
                            } else {
                                console.error(ownerResponse);
                                ElMessage({
                                    type: 'error',
                                    message: '数据信息获取异常',
                                });
                            }
                        } catch (error) {
                            console.error(error);
                            ElMessage({
                                type: 'error',
                                message: '业主信息获取异常',
                            });
                        }
                    }
                } else {
                    console.error(monthDataResponse);
                    ElMessage({
                        type: 'error',
                        message: '月度数据获取异常',
                    });
                }
            } catch (error) {
                console.error(error);
                ElMessage({
                    type: 'error',
                    message: '月度数据获取异常',
                });
            }
        });

        // 等待所有异步请求完成
        await Promise.all(promises);
    }
}

const applyFiltersUser = async () => {
    selectData.value.length = 0;  // 清空旧数据
    if (dateRange.value) {
        const [start, end] = dateRange.value;
        const startDate = new Date(start);
        const endDate = new Date(end);
        const months = getAllMonthsBetweenDates(startDate, endDate);

        // 使用 Promise.all 来确保所有请求都完成
        const promises = months.map(async (timeStamp) => {
            const result = timeStamp.split('-');
            try {
                const response = await selectOwnerMonth(ownerId.value, result[0], result[1])

                if (response.data.state == 200 && response.data.data != null) {
                    const monthData = response.data.data
                    try {
                        const ownerResponse = await getOwner(monthData.oid);
                        if (ownerResponse.data.state == 200) {
                            const owner = ownerResponse.data.data;
                            selectData.value.push({
                                date: timeStamp,
                                name: owner.name,
                                water: monthData.waterConsumption,
                                electricity: monthData.electricityConsumption,
                                gas: monthData.gasConsumption,
                                heater: monthData.heaterConsumption,
                                address: `${owner.buildingNumber}-${owner.floorNumber}`
                            })
                        } else {
                            console.error(ownerResponse);
                            ElMessage({
                                type: 'error',
                                message: '数据信息获取异常',
                            });
                        }
                    } catch (error) {
                        console.error(error);
                        ElMessage({
                            type: 'error',
                            message: '业主信息获取异常',
                        });
                    }
                }
            } catch (error) {
                console.error(error);
                ElMessage({
                    type: 'error',
                    message: '月度数据获取异常',
                });
            }
        });

        // 等待所有异步请求完成
        await Promise.all(promises);
    }
}
function getAllMonthsBetweenDates(startDate: Date, endDate: Date): string[] {
    const months: string[] = [];
    let year = startDate.getFullYear();
    let month = startDate.getMonth();
    while (year < endDate.getFullYear() || (year === endDate.getFullYear() && month <= endDate.getMonth())) {
        months.push(`${year}-${String(month + 1).padStart(2, '0')}`);
        month++;
        if (month > 11) {
            month = 0;
            year++;
        }
    }
    return months;
}

// ----------------------------------------------
// 抄表记录表单
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
const form = reactive({
    date: '',
    buildingNumber: '',
    floorNumber: '',
    waterConsumption: '',
    electricityConsumption: '',
    gasConsumption: '',
    heaterConsumption: '',
    WATER_PRICE: '',
    ELECTRICITY_PRICE: '',
    GAS_PRICE: '',
    HEATER_PRICE: '',
    ESTATE_PRICE: ''
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
        '抄表信息',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            const date = new Date(form.date)
            let year = date.getFullYear()
            let month = date.getMonth() + 1
            getOwnerByLoc(form.buildingNumber, form.floorNumber).then(response => {
                if (response.data.state == 200) {
                    addMonthData(year, month, response.data.data.id, form.waterConsumption, form.electricityConsumption, form.gasConsumption, form.heaterConsumption,
                        form.WATER_PRICE, form.ELECTRICITY_PRICE, form.GAS_PRICE, form.HEATER_PRICE, form.ESTATE_PRICE).then(response => {
                            if (response.data.state == 200) {
                                ElMessage({
                                    type: 'success',
                                    message: '添加成功',
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
                } else {
                    console.log(response)
                    ElMessage({
                        type: 'error',
                        message: '添加异常',
                    })
                }
            })
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '取消提交',
            })
        })

}
</script>

<style>
.menu:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
}

.el-statistic {
    --el-statistic-content-font-size: 28px;
}

.statistic-card {
    height: 100%;
    padding: 20px;
    border-radius: 4px;
    background-color: var(--el-bg-color-overlay);
}
</style>