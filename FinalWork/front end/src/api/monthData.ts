import axios from "../axios";


export function addMonthData(year: number, month: number, oid: string, waterConsumption: string, electricityConsumption: string, gasConsumption: string, heaterConsumption: string,
    WATER_PRICE: string, ELECTRICITY_PRICE: string, GAS_PRICE: string, HEATER_PRICE: string, ESTATE_PRICE: string) {
    return axios({
        url: "/month_data/add",
        method: 'post',
        params: {
            year, month, oid, waterConsumption, electricityConsumption, gasConsumption, heaterConsumption,
            WATER_PRICE, ELECTRICITY_PRICE, GAS_PRICE, HEATER_PRICE, ESTATE_PRICE
        }
    })
}

export function updateMonthData(id: any, year: string, month: string, waterConsumption: string, electricityConsumption: string, gasConsumption: string, televisionConsumption: string) {
    return axios({
        url: "/month_data/update",
        method: 'post',
        params: {
            id, year, month, waterConsumption, electricityConsumption, gasConsumption, televisionConsumption
        }
    })
}

export function getMonthData(mdid: any) {
    return axios({
        url: "/month_data/get_by_id",
        method: 'get',
        params: { mdid }
    })
}

export function getAllMonthData() {
    return axios({
        url: "/month_data/get_all",
        method: 'get'
    })
}

export function selectYearData(year: string) {
    return axios({
        url: "/month_data/select_year_data",
        method: 'get',
        params: { year }
    })
}

export function selectMonthData(year: string, month: string) {
    return axios({
        url: "/month_data/select_month_data",
        method: 'get',
        params: { year, month }
    })
}

export function selectOwnerYear(oid: any, year: any) {
    return axios({
        url: "/month_data/select_owner_year",
        method: 'get',
        params: { oid, year }
    })
}

export function selectOwnerMonth(oid: any, year: string, month: string) {
    return axios({
        url: "/month_data/select_owner_month",
        method: 'get',
        params: { oid, year, month }
    })
}

export function deleteMonthData(mdid: any) {
    return axios({
        url: "/month_data/delete",
        method: 'post',
        params: { mdid }
    })
}