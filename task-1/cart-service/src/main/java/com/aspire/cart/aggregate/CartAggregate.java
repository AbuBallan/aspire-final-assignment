package com.aspire.cart.aggregate;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

public class CartAggregate {

    private String userId;

    private Long cartId;

    private CartStatus cartStatus;

    private List<LineItem> lineItems;

    public CartAggregate(List<LineItem> lineItems, String userId, Long cartId, CartStatus cartStatus) {
        this.lineItems = lineItems == null ? emptyList() : unmodifiableList(lineItems);
        this.userId = userId;
        this.cartId = cartId;
        this.cartStatus = cartStatus;
    }

    public CartAggregate(List<LineItem> lineItems, String userId, CartStatus cartStatus) {
        this.lineItems = lineItems == null ? emptyList() : unmodifiableList(lineItems);
        this.userId = userId;
        this.cartStatus = cartStatus;
    }

    public void addItem(long itemNo, int qty, int price) {

        LineItem lineItem1 =
                lineItems
                        .stream()
                        .filter(lItem ->
                                lItem.getItemNo() == itemNo)
                        .findFirst()
                        .map(lineItem -> lineItem
                                .toBuilder()
                                .qty(lineItem.getQty() + qty)
                                .build())
                        .orElse(new LineItem(itemNo, price, qty));

        List<LineItem> itemList = lineItems
                .stream()
                .filter(lItem -> lItem.getItemNo() != itemNo)
                .collect(LinkedList::new, LinkedList::add, LinkedList::addAll);

        itemList.add(lineItem1);

        this.lineItems = unmodifiableList(itemList);

    }

    public void removeItem(long itemNo) {
        List<LineItem> newList = lineItems.stream()
                .filter(lItem -> lItem.getItemNo() != itemNo)
                .collect(Collectors.toList());
        this.lineItems = unmodifiableList(newList);
    }

    public void clearCart(){
        this.lineItems = Collections.emptyList();
        this.cartStatus = CartStatus.CLEARED;
    }

    public void checkoutCart() {
        this.cartStatus = CartStatus.CHECKED_OUT;
    }

    public void payCart() {
        this.cartStatus = CartStatus.PENDING;
    }

    public int getTotalPrice() {
        return lineItems.stream()
                .mapToInt(item -> item.getPrice() * item.getQty())
                .sum();
    }

    public CartStatus getCartStatus() {
        return this.cartStatus;
    }


    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public String getUserId() {
        return userId;
    }

    public Long getCartId() {
        return cartId;
    }
}
