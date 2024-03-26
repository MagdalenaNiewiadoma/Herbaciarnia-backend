package com.aplikacja.herbaciarnia.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer{")
                .append("customerId=").append(customerId)
                .append(", firstName='").append(firstName).append('\'')
                .append(", lastName='").append(lastName).append('\'');

        if (address != null) {
            sb.append(", address=").append(address.getAddressId()); // Only include address ID to avoid recursion
        } else {
            sb.append(", address=null");
        }

        sb.append('}');
        return sb.toString();
    }
}
