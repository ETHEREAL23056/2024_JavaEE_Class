package com.my.finalwork.controller;

import com.my.finalwork.entity.Owner;
import com.my.finalwork.service.IOwnerService;
import com.my.finalwork.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("owner")
public class OwnerController extends BaseController {
    @Autowired
    private IOwnerService ownerService;

    @RequestMapping("/add")
    public JsonResult<Void> add(Owner owner) {
        ownerService.addOwner(owner);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/update")
    public JsonResult<Void> update(Owner owner) {
        ownerService.updateOwner(owner);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/delete")
    public JsonResult<Void> delete(Integer oid) {
        ownerService.deleteOwner(oid);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/get_by_id")
    public JsonResult<Owner> getById(Integer oid) {
        Owner owner = ownerService.getOwner(oid);
        return new JsonResult<>(OK, owner);
    }

    @RequestMapping("/get_by_id_number")
    public JsonResult<Owner> getByIdNumber(String IDNumber) {
        Owner owner = ownerService.getOwnerByID(IDNumber);
        return new JsonResult<>(OK, owner);
    }

    @RequestMapping("/get_by_location")
    public JsonResult<Owner> getByLocation(Integer buildingNumber, Integer floorNumber) {
        Owner owner = ownerService.getOwnerByLocation(buildingNumber, floorNumber);
        return new JsonResult<>(OK, owner);
    }

    @RequestMapping("/get_by_user")
    public JsonResult<Owner> getByUser(Integer uid) {
        Owner owner = ownerService.getOwnerByUser(uid);
        return new JsonResult<>(OK, owner);
    }

    @RequestMapping("/bound_user")
    public JsonResult<Void> boundUser(Integer oid, Integer uid) {
        ownerService.boundUser(oid, uid);
        return new JsonResult<>(OK);
    }

}
