package com.aplikacja.herbaciarnia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cacheable
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "teas")
public class Tea {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teaId;

    @Column(name = "tea_name")
    private String teaName;

    @Column(name = "tea_description")
    private String teaDescription;

    public Tea(String teaName, String teaDescription) {
        this.teaName = teaName;
        this.teaDescription = teaDescription;
    }


    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonIgnore
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tea{")
                .append("teaId=").append(teaId)
                .append(", teaName='").append(teaName).append('\'')
                .append(", teaDescription='").append(teaDescription).append('\'');

        if (order != null) {
            sb.append(", orderId=").append(order.getOrderId()); // Include orderId to avoid recursion
        } else {
            sb.append(", orderId=null");
        }

        sb.append('}');
        return sb.toString();
    }
}

