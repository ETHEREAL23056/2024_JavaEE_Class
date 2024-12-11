import { Expand } from "@element-plus/icons-vue";
import axios from "../axios";


export function addOwner(IDNumber: string, buildingNumber: string, floorNumber: string, name: string, workingUnit: string, telephoneNumber: string, area: string) {
    return axios({
        url: "/owner/add",
        method: 'post',
        params: {
            IDNumber, buildingNumber, floorNumber, name, workingUnit, telephoneNumber, area
        }
    })
}

export function updateOwner(id: any, IDNumber: any, buildingNumber: any, floorNumber: any, name: any, workingUnit: any, telephoneNumber: any, area: any) {
    return axios({
        url: "/owner/update",
        method: 'post',
        params: {
            id, IDNumber, buildingNumber, floorNumber, name, workingUnit, telephoneNumber, area
        }
    })
}

export function getOwner(oid: any) {
    return axios({
        url: "/owner/get_by_id",
        method: 'get',
        params: { oid }
    })
}

export function getOwnerByID(IDNumber: string) {
    return axios({
        url: "/owner/get_by_id_number",
        method: 'get',
        params: { IDNumber }
    })
}

export function getOwnerByLoc(buildingNumber: string, floorNumber: string) {
    return axios({
        url: "/owner/get_by_location",
        method: 'get',
        params: { buildingNumber, floorNumber }
    })
}


export function getUserOwner(uid: any) {
    return axios({
        url: "/owner/get_by_user",
        method: 'get',
        params: { uid }
    })
}

export function deleteOwner(oid: any) {
    return axios({
        url: "/owner/delete",
        method: 'post',
        params: { oid }
    })
}

export function bound(oid: any, uid: any) {
    return axios({
        url: "/owner/bound_user",
        method: 'post',
        params: { oid, uid }
    })
}