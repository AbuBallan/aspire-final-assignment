package com.aspire.payment.adapter.repository;

import com.aspire.payment.model.Payment;

import java.util.Optional;

public interface PaymentRepository {

    Payment insertPayment(Payment payment);

    Optional<Payment> getPayment(long cartId);

}
