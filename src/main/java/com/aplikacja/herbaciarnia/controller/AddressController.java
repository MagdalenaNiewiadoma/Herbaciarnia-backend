package com.aplikacja.herbaciarnia.controller;

import com.aplikacja.herbaciarnia.exception.ResourceNotFoundException;
import com.aplikacja.herbaciarnia.model.Address;
import com.aplikacja.herbaciarnia.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class AddressController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/addresses/all")
    public ResponseEntity<List<Address>> getAllAddresses(){
        return ResponseEntity.ok(addressService.getAllAddresses());

    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable ("id") Long addressId ){
        Optional<Address> addressOptional = addressService.getAddressById(addressId);
        return addressOptional.map(address -> ResponseEntity.ok(address)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addresses")
    public ResponseEntity<Address> createAddress(@RequestBody Address address){
       try{
            Address createdAddress = addressService.createAddress(address);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
        } catch ( Exception e){
            LOGGER.error("Exception occurred while creating order: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/addresses/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable("id") Long addressId, @RequestBody Address address)
                                                 throws ResourceNotFoundException {
        Address updatedAddress = addressService.updateAddress(addressId, address);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<Address> deleteAddress(@PathVariable("id") Long addressId){
        addressService.deleteById(addressId);
        return ResponseEntity.noContent().build();
    }

}
