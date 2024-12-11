package com.my.finalwork.controller;

import com.my.finalwork.entity.Payment;
import com.my.finalwork.service.IPaymentService;
import com.my.finalwork.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController extends BaseController {
    @Autowired
    private IPaymentService paymentService;

    @RequestMapping("/add")
    public JsonResult<Void> add(Payment payment) {
        paymentService.addPayment(payment);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/update")
    public JsonResult<Void> update(Payment payment) {
        paymentService.updatePayment(payment);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/delete")
    public JsonResult<Void> delete(Integer pid) {
        paymentService.deletePayment(pid);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/get_by_id")
    public JsonResult<Payment> getById(Integer pid) {
        Payment payment = paymentService.getPaymentById(pid);
        return new JsonResult<>(OK, payment);
    }

    @RequestMapping("/select_month_data")
    public JsonResult<List<Payment>> selectMonthData(Integer year, Integer month) {
        List<Payment> paymentList = paymentService.getPaymentsByMonth(year, month);
        return new JsonResult<>(OK, paymentList);
    }

    @RequestMapping("/select_season_data")
    public JsonResult<List<Payment>> selectSeasonData(Integer year, Integer season) {
        List<Payment> paymentList = paymentService.getPaymentBySeason(year, season);
        return new JsonResult<>(OK, paymentList);
    }

    @RequestMapping("/select_year_data")
    public JsonResult<List<Payment>> selectYearData(Integer year) {
        List<Payment> paymentList = paymentService.getPaymentsByYear(year);
        return new JsonResult<>(OK, paymentList);
    }

    @RequestMapping("/get_unpaid")
    public JsonResult<List<Payment>> getUnpaid() {
        List<Payment> paymentList = paymentService.getUnpaidPayments();
        return new JsonResult<>(OK, paymentList);
    }

    @RequestMapping("/get_paid")
    public JsonResult<List<Payment>> getPaid() {
        List<Payment> paymentList = paymentService.getPaidPayments();
        return new JsonResult<>(OK, paymentList);
    }

    @RequestMapping("/select_owner_paid")
    public JsonResult<List<Payment>> selectOwnerPaid(Integer oid) {
        List<Payment> paymentList = paymentService.getPaidPaymentsByOwner(oid);
        return new JsonResult<>(OK, paymentList);
    }

    @RequestMapping("/select_owner_unpaid")
    public JsonResult<List<Payment>> selectOwnerUnpaid(Integer oid) {
        List<Payment> paymentList = paymentService.getUnpaidPaymentsByOwner(oid);
        return new JsonResult<>(OK, paymentList);
    }

    @RequestMapping("/select_owner_yearly")
    public JsonResult<List<Payment>> selectOwnerYearly(Integer oid, Integer year) {
        List<Payment> paymentList = paymentService.getYearlyPaymentsByOwner(oid, year);
        return new JsonResult<>(OK, paymentList);
    }

    @RequestMapping("/get_by_data")
    public JsonResult<Payment> getByData(Integer oid, Integer year, Integer month) {
        Payment payment = paymentService.getPayment(oid, year, month);
        return new JsonResult<>(OK, payment);
    }

}
