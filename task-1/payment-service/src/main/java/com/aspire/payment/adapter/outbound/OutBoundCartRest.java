package com.aspire.payment.adapter.outbound;

import com.aspire.payment.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@FeignClient(value = "cart-service")
public interface OutBoundCartRest {

    @GetMapping("/carts/{cartId}")
    Optional<Payment> loadCart(@PathVariable("cartId") long cartId);

    @PutMapping("/carts/{cartId}/paid")
    void paid(@PathVariable("cartId") long cartId);

}