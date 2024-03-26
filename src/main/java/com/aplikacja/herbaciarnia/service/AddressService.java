package com.aplikacja.herbaciarnia.service;

import com.aplikacja.herbaciarnia.exception.ResourceNotFoundException;
import com.aplikacja.herbaciarnia.model.Address;
import com.aplikacja.herbaciarnia.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {


    private static final Logger LOGGER = LoggerFactory.getLogger(AddressService.class);
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }
    public Optional<Address> getAddressById(Long addressId) {
        return addressRepository.findById(addressId);
    }
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }
    public Address updateAddress(Long addressId, Address updatedAddress) {
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(()-> new ResourceNotFoundException("Address not found with id: " + addressId));
        existingAddress.setStreet(updatedAddress.getStreet());
        existingAddress.setStreet(updatedAddress.getStreet());
        existingAddress.setZipCode(updatedAddress.getZipCode());

        return addressRepository.save(existingAddress);
    }

    public void deleteById(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}
