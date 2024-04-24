package com.apirest.jogorpg.repository;

import com.apirest.jogorpg.model.Batalha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatalhaRepository extends JpaRepository<Batalha, Long> {

}
