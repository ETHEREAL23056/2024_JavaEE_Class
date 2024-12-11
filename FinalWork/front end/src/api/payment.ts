import axios from "../axios";

export function addPayment(oid: any, year: any, month: any, isPayed: any, waterCost: any, electricityCost: any, gasCost: any, heaterCost: any, estateCost: any) {
    return axios({
        url: "/payment/add",
        method: 'post',
        params: { oid, year, month, isPayed, waterCost, electricityCost, gasCost, heaterCost, estateCost }
    })
}

export function updatePayment(id: any, year: any, month: any, isPayed: any, waterCost: any, electricityCost: any, gasCost: any, heaterCost: any, estateCost: any) {
    return axios({
        url: "/payment/update",
        method: 'post',
        params: { id, year, month, isPayed, waterCost, electricityCost, gasCost, heaterCost, estateCost }
    })
}

export function deletePayment(pid: any) {
    return axios({
        url: "/payment/delete",
        method: 'post',
        params: { pid }
    })
}

export function getPayment(pid: any) {
    return axios({
        url: "/payment/get_by_id",
        method: 'get',
        params: { pid }
    })
}

export function selectMonthData(year: any, month: any) {
    return axios({
        url: "/payment/select_month_data",
        method: 'get',
        params: { year, month }
    })
}

export function selectSeasonData(year: any, season: any) {
    return axios({
        url: "/payment/select_season_data",
        method: 'get',
        params: { year, season }
    })
}

export function selectYearData(year: any) {
    return axios({
        url: "/payment/select_year_data",
        method: 'get',
        params: { year }
    })
}

export function getUnpaid() {
    return axios({
        url: "/payment/get_unpaid",
        method: 'get'
    })
}

export function getPaid() {
    return axios({
        url: "/payment/get_paid",
        method: 'get'
    })
}

export function selectOwnerPaid(oid: any) {
    return axios({
        url: "/payment/select_owner_paid",
        method: 'get',
        params: { oid }
    })
}

export function selectOwnerUnpaid(oid: any) {
    return axios({
        url: "/payment/select_owner_unpaid",
        method: 'get',
        params: { oid }
    })
}

export function selectOwnerByYear(oid: any, year: any) {
    return axios({
        url: "/payment/select_owner_yearly",
        method: 'get',
        params: { oid, year }
    })
}

export function getPaymentByMonthData(oid: any, year: string, month:string) {
    return axios({
        url: "/payment/get_by_data",
        method: 'get',
        params: { oid, year, month }
    })
}