package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Movimientos;

public interface MovimientosRepository extends JpaRepository<Movimientos, Long> {

}
