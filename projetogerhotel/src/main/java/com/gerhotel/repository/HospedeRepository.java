package com.gerhotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerhotel.entities.Hospede;

public interface HospedeRepository extends JpaRepository<Hospede, Long> {

}
