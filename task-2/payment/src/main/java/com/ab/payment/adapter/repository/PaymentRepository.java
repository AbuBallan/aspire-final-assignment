package com.ab.payment.adapter.repository;

import com.ab.payment.model.Payment;
import com.ab.payment.model.PaymentState;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {

    Payment insert(Payment payment);

    Optional<Payment> findById(String paymentId);

    List<Payment> findAll();

    Payment updatePaymentStateById(String paymentId, PaymentState paymentState);

}
