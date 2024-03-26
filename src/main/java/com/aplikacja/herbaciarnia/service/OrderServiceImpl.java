package com.aplikacja.herbaciarnia.service;


import com.aplikacja.herbaciarnia.model.Address;
import com.aplikacja.herbaciarnia.model.Order;
import com.aplikacja.herbaciarnia.model.Tea;
import com.aplikacja.herbaciarnia.model.Customer;
import com.aplikacja.herbaciarnia.repository.AddressRepository;
import com.aplikacja.herbaciarnia.repository.OrderRepository;
import com.aplikacja.herbaciarnia.repository.TeaRepository;
import com.aplikacja.herbaciarnia.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;


@Transactional
@Service
public class OrderServiceImpl extends OrderService {


    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final TeaRepository teaRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            CustomerRepository customerRepository, AddressRepository addressRepository,
                            TeaRepository teaRepository) {
        super(orderRepository, customerRepository, addressRepository, teaRepository);
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.teaRepository = teaRepository;


        Address address1 = new Address();
        address1.setAddressId(1L);
        address1.setCity("Wrocław");
        address1.setStreet("Tęczowa 10");
        address1.setZipCode("55-555");

        Address address2 = new Address();
        address2.setAddressId(2L);
        address2.setCity("Kołobrzeg");
        address2.setStreet("Wyspiańskiego 12/8");
        address2.setZipCode("17-751");

        Customer customer1 = new Customer();
        customer1.setCustomerId(1L);
        customer1.setFirstName("Halina");
        customer1.setLastName("Kiepska");
        customer1.setAddress(address1);

        Customer customer2 = new Customer();
        customer2.setCustomerId(2L);
        customer2.setFirstName("Ferdynand");
        customer2.setLastName("Kiepski");
        customer2.setAddress(address2);

        Order order1 = new Order();
        order1.setOrderId(1L);
        order1.setLocalDate(LocalDate.of(2023,12,12));
        order1.setAvailableQuantity(100);
        order1.setPriceOfSelling(35);
        order1.setCustomer(customer1);

        Order order2 = new Order();
        order2.setOrderId(2L);
        order2.setLocalDate(LocalDate.of(2024, 02,14));
        order2.setAvailableQuantity(200);
        order2.setPriceOfSelling(50);
        order2.setCustomer(customer2);

        orderRepository.saveAll(Arrays.asList(order1, order2));

        Tea tea1 = new Tea();
        tea1.setTeaId(1L);
        tea1.setTeaName("SmerfoweLato");
        tea1.setTeaDescription("black");
        tea1.setOrder(order1);

        Tea tea2 = new Tea();
        tea2.setTeaId(2L);
        tea2.setTeaName("Orchidflower");
        tea2.setTeaDescription("green");
        tea2.setOrder(order2);

        teaRepository.saveAll(Set.of(tea1, tea2));

    }
}
