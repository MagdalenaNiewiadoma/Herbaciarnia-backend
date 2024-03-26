package com.aplikacja.herbaciarnia.service;


import com.aplikacja.herbaciarnia.exception.ResourceNotFoundException;
import com.aplikacja.herbaciarnia.model.Tea;
import com.aplikacja.herbaciarnia.repository.TeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class TeaService {


    private TeaRepository teaRepository;
    private final List<Tea> availableTeas = Arrays.asList();

    @Autowired
    public TeaService(TeaRepository teaRepository) {
        this.teaRepository = teaRepository;
    }


    public List<Tea> getAllTeas() {
        return teaRepository.findAll();
    }

    public Optional<Tea> getTeaById(Long id) {
        return teaRepository.findById(id);
    }

    public Tea createTea(Tea tea) {
        return teaRepository.save(tea);
    }

    public Tea updateTea(Long id, Tea updatedTea) throws ResourceNotFoundException {
        Tea existingTea = teaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Tea not found with id: " + id));
        existingTea.setTeaName(updatedTea.getTeaName());
        existingTea.setTeaDescription(updatedTea.getTeaDescription());

        return teaRepository.save(updatedTea);
    }
    public void deleteTea(Long id) {
        teaRepository.deleteById(id);
    }
    public String selectTea(String teaName, String teaDescription) {
        Optional<Tea> selectedTea = availableTeas.stream()
           .filter(tea -> tea.getTeaName().equalsIgnoreCase(teaName) && tea.getTeaDescription().equalsIgnoreCase(teaDescription))
                .findFirst();
        return selectedTea.map(Tea::getTeaName).orElse(null);
    }
}
