// 对样本类的axios请求
import axios from "../axios";

export function addOwnerPayment(pid: any, paymentType: any, installments: any, payedInstallments: any) {
    return axios({
        url: "/owner_payment/add",
        method: 'post',
        params: { pid, paymentType, installments, payedInstallments }
    })
}

export function updateOwnerPayment(pid: any, paymentType: any, installments: any, payedInstallments: any) {
    return axios({
        url: "/owner_payment/update",
        method: 'post',
        params: { pid, paymentType, installments, payedInstallments }
    })
}

export function getOwnerPayment(opid: any) {
    return axios({
        url: "/owner_payment/get_by_id",
        method: "get",
        params: { opid }
    })
}

export function selectByPayment(pid: any) {
    return axios({
        url: "/owner_payment/select_by_pid",
        method: 'get',
        params: { pid }
    })
}

export function selectUnfinished() {
    return axios({
        url: "/owner_payment/select_unfinished",
        method: 'get'
    })
}

export function deleteOwnerPayment(opid: any) {
    return axios({
        url: "/owner_payment/delete",
        method: 'post',
        params: { opid }
    })
}