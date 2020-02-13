package com.ab.cart.adapter.repository.cart.rds.entity;

import com.ab.cart.adapter.repository.cartitem.rds.entity.CartItemEntity;
import com.ab.cart.model.CartState;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "Cart")
public class CartEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String cartId;

    @Enumerated(EnumType.STRING)
    private CartState cartState;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartItemEntity> items;

}
