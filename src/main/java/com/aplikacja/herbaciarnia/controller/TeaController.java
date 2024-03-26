package com.aplikacja.herbaciarnia.controller;

import com.aplikacja.herbaciarnia.exception.ResourceNotFoundException;
import com.aplikacja.herbaciarnia.model.Tea;
import com.aplikacja.herbaciarnia.service.TeaService;
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
public class TeaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeaController.class);
    private TeaService teaService;

    @Autowired
    public TeaController(TeaService teaService) {
        this.teaService = teaService;
    }


    @GetMapping("/teas/all")
    public ResponseEntity<List<Tea>> getAllTeas(){
        return ResponseEntity.ok(teaService.getAllTeas());
    }

    @GetMapping("/teas/{id}")
    public ResponseEntity<Tea> getTeaById(@PathVariable("id") Long id){
       Optional<Tea> teaOptional = teaService.getTeaById(id);
       return teaOptional.map(tea -> ResponseEntity.ok(tea)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/teas")
    public ResponseEntity<Tea>createTea(@RequestBody Tea tea){
        try{
            Tea createdTea = teaService.createTea(tea);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTea);
        } catch (Exception e){
            LOGGER.error("Exception occurred while creating order: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/teas/{id}")
    public ResponseEntity<Tea> updateTea(@PathVariable("id") Long id, @RequestBody Tea tea)
                                         throws ResourceNotFoundException {

        Tea updatedTea = teaService.updateTea(id, tea);
       return ResponseEntity.ok(updatedTea);
    }

    @PostMapping("/teas/chooseTea")
    public ResponseEntity<String> chooseTea(@RequestParam String teaName, @RequestParam String teaDescription){

        String selectedTea = teaService.selectTea(teaName, teaDescription);
        if(selectedTea != null){
            return ResponseEntity.ok("Selected tea: " + selectedTea);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to select tea.");
        }
    }

    @DeleteMapping("/teas")
    public ResponseEntity<Tea> deleteTea(@PathVariable("id") Long id){
        teaService.deleteTea(id);
        return ResponseEntity.noContent().build();
    }

}

