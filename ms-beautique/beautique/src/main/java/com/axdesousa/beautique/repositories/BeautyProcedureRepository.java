package com.axdesousa.beautique.repositories;

import com.axdesousa.beautique.entities.BeautyProcedureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeautyProcedureRepository extends JpaRepository<BeautyProcedureEntity,Long> {
}
