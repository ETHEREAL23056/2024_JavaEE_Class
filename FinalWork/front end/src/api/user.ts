import axios from '../axios';

export function register(username: any, password: any, role: any, roleKey: any) {
    return axios({
        url: "/user/register",
        method: 'post',
        params: { username, password, role, roleKey }
    })
}

export function login(username: any, password: any) {
    return axios({
        url: "/user/login",
        method: 'get',
        params: { username, password }
    })
}

export function changePassword(oldPassword: string, newPassword: string) {
    return axios({
        url: "/user/changePassword",
        method: 'post',
        params: { oldPassword, newPassword }
    })
}

export function getUser() {
    return axios({
        url: "/user/get_by_id",
        method: 'get'
    })
}

export function fetchUser() {
    return axios({
        url: "/user/get_user",
        method: 'get'
    })
}