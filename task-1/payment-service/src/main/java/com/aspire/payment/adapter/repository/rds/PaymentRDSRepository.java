package com.aspire.payment.adapter.repository.rds;

import com.aspire.payment.adapter.repository.PaymentRepository;
import com.aspire.payment.adapter.repository.rds.entity.PaymentEntity;
import com.aspire.payment.model.Payment;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class PaymentRDSRepository implements PaymentRepository {

    private final PaymentJpaRepository jpaRepository;

    private final ModelMapper modelMapper;

    public PaymentRDSRepository(PaymentJpaRepository jpaRepository, ModelMapper modelMapper) {
        this.jpaRepository = jpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Payment insertPayment(Payment payment) {
        PaymentEntity entity = jpaRepository
                .save(mapToEntity(payment));
        return mapToModel(entity);
    }

    @Override
    public Optional<Payment> getPayment(long cartId) {
        return jpaRepository
                .findById(cartId)
                .map(this::mapToModel);
    }

    private Payment mapToModel(PaymentEntity entity) {
        return modelMapper.map(entity, Payment.class);
    }

    private PaymentEntity mapToEntity(Payment payment) {
        return modelMapper.map(payment, PaymentEntity.class);
    }
}
