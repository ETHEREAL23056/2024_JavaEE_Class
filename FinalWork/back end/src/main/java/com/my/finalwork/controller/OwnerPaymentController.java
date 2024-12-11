package com.my.finalwork.controller;

import com.my.finalwork.entity.OwnerPayment;
import com.my.finalwork.service.IOwnerPaymentService;
import com.my.finalwork.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("owner_payment")
public class OwnerPaymentController extends BaseController {
    @Autowired
    private IOwnerPaymentService ownerPaymentService;

    @RequestMapping("/add")
    public JsonResult<Void> add(OwnerPayment ownerPayment) {
        ownerPaymentService.addOwnerPayment(ownerPayment);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/update")
    public JsonResult<Void> update(OwnerPayment ownerPayment) {
        ownerPaymentService.updateOwnerPayment(ownerPayment);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/delete")
    public JsonResult<Void> delete(Integer opid) {
        ownerPaymentService.deleteOwnerPayment(opid);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/get_by_id")
    public JsonResult<OwnerPayment> getById(Integer opid) {
        OwnerPayment ownerPayment = ownerPaymentService.getOwnerPayment(opid);
        return new JsonResult<>(OK, ownerPayment);
    }

    @RequestMapping("select_by_pid")
    public JsonResult<OwnerPayment> selectByPid(Integer pid) {
        OwnerPayment ownerPayment = ownerPaymentService.getOwnerByPaymentId(pid);
        return new JsonResult<>(OK, ownerPayment);
    }

    @RequestMapping("/select_unfinished")
    public JsonResult<List<OwnerPayment>> selectUnfinished() {
        List<OwnerPayment> ownerPaymentList = ownerPaymentService.getAllUnfinishedOwnerPayment();
        return new JsonResult<>(OK, ownerPaymentList);
    }
}
