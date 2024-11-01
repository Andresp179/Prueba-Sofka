package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

}
