package com.example.model;

import com.example.model.enums.Currency;
import com.example.model.enums.Status;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(generator = "order_generator")
    @SequenceGenerator(
            name = "order_generator",
            sequenceName = "order_sequence",
            initialValue = 1
    )
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreatedDate
    private LocalDateTime orderDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private Client owner;

    @NotNull
    @Column(precision=10, scale=2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Currency currency;

    public Order() {}

    public Order(@NotNull Client owner, @NotNull BigDecimal amount, @NotNull Currency currency) {
        this.owner = owner;
        this.amount = amount;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return status == order.status &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(owner, order.owner) &&
                Objects.equals(amount, order.amount) &&
                currency == order.currency;
    }

    @Override
    public int hashCode() {

        return Objects.hash(status, orderDate, owner, amount, currency);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", orderDate=" + orderDate +
                ", owner=" + owner +
                ", amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}
