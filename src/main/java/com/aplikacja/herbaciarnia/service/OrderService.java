package com.aplikacja.herbaciarnia.service;

import com.aplikacja.herbaciarnia.exception.ResourceNotFoundException;
import com.aplikacja.herbaciarnia.model.*;
import com.aplikacja.herbaciarnia.repository.AddressRepository;
import com.aplikacja.herbaciarnia.repository.OrderRepository;
import com.aplikacja.herbaciarnia.repository.CustomerRepository;
import com.aplikacja.herbaciarnia.repository.TeaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final TeaRepository teaRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository,
                        AddressRepository addressRepository, TeaRepository teaRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.teaRepository = teaRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public Order createOrder(Order order) {

        if (order.getCustomer() != null) {
            customerRepository.save(order.getCustomer());
        }
        if (order.getCustomer() != null && order.getCustomer().getAddress() != null) {
            addressRepository.save(order.getCustomer().getAddress());
        }
        if (order.getTeas() != null && !order.getTeas().isEmpty()) {
            for (Tea tea : order.getTeas()) {
                tea.setOrder(order);
                teaRepository.save(tea);
            }
        }
        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public Order updateOrder(Long orderId, Order updatedOrder) throws ResourceNotFoundException {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

        existingOrder.setLocalDate(updatedOrder.getLocalDate());
        existingOrder.setAvailableQuantity(updatedOrder.getAvailableQuantity());
        existingOrder.setPriceOfSelling(updatedOrder.getPriceOfSelling());
        existingOrder.setCustomer(updatedOrder.getCustomer());

        Set<Tea> existingTeas = existingOrder.getTeas();
        Set<Tea> updatedTeas = updatedOrder.getTeas();

        for (Tea updatedTea : updatedTeas) {
            boolean found = false;
            for (Tea existingTea : existingTeas) {
                if (existingTea.getTeaId().equals(updatedTea.getTeaId())) {
                    existingTea.setTeaName(updatedTea.getTeaName());
                    existingTea.setTeaDescription(updatedTea.getTeaDescription());
                    found = true;
                    break;
                }
            }
            if (!found) {
                updatedTea.setOrder(existingOrder);
                existingTeas.add(updatedTea);
            }
        }

        existingTeas.removeIf(existingTea -> !updatedTeas.contains(existingTea));

        return orderRepository.save(existingOrder);
    }
}
