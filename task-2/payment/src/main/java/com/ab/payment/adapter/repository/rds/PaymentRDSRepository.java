package com.ab.payment.adapter.repository.rds;

import com.ab.payment.adapter.repository.PaymentRepository;
import com.ab.payment.adapter.repository.rds.entity.PaymentEntity;
import com.ab.payment.model.Payment;
import com.ab.payment.model.PaymentState;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaymentRDSRepository implements PaymentRepository {

    private final PaymentJpaRepository jpaRepository;

    private final ModelMapper mapper;

    public PaymentRDSRepository(PaymentJpaRepository jpaRepository, ModelMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Payment insert(Payment payment) {
        PaymentEntity paymentEntity = jpaRepository.save(mapToEntity(payment));
        return mapToModel(paymentEntity);
    }

    @Override
    public Optional<Payment> findById(String paymentId) {
        return jpaRepository
                .findById(paymentId)
                .map(this::mapToModel);
    }

    @Override
    public List<Payment> findAll() {
        return jpaRepository
                .findAll()
                .stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Payment updatePaymentStateById(String paymentId, PaymentState paymentState) {
        PaymentEntity entity = jpaRepository.getOne(paymentId);
        entity.setPaymentState(paymentState);
        return mapToModel(jpaRepository.save(entity));
    }

    private PaymentEntity mapToEntity(Payment payment) {
        return mapper.map(payment, PaymentEntity.class);
    }

    private Payment mapToModel(PaymentEntity paymentEntity) {
        return mapper.map(paymentEntity, Payment.class);
    }
}
