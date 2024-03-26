package com.aplikacja.herbaciarnia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "orders")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "local_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    @Column(name = "price_of_selling")
    private int priceOfSelling;

    @Column(name = "available_quantity")
    private int availableQuantity;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Tea> teas = new HashSet<>();


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Tea> getTeas() {
        return teas;
    }

    public void setTeas(Set<Tea> teas) {
        this.teas = teas;
    }

    @Override
    public String toString() {
        String customerString = (customer != null) ?
                "Customer{" +
                        "customerId=" + customer.getCustomerId() +
                        ", firstName='" + customer.getFirstName() + '\'' +
                        ", lastName='" + customer.getLastName() + '\'' +
                        ", city='" + customer.getAddress().getCity() + '\'' +
                        ", street='" + customer.getAddress().getStreet() + '\'' +
                        ", zipCode='" + customer.getAddress().getZipCode() +'\'' +
                        '}' :
                "null";

        return "Order{" +
                "orderId=" + orderId +
                ", localDate=" + localDate +
                ", priceOfSelling=" + priceOfSelling +
                ", availableQuantity=" + availableQuantity +
                ", customer=" + customerString +
                ", teas=" + teas +
                '}';
    }
}


