package com.gerhotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerhotel.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{

}
