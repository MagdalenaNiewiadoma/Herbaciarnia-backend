package com.aplikacja.herbaciarnia.repository;

import com.aplikacja.herbaciarnia.model.Tea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeaRepository extends JpaRepository<Tea, Long> {

}
