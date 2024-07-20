package com.assignment.string_manipulation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.string_manipulation.entities.StringManipulationEntity;

@Repository
public interface StringManipulationRepository extends JpaRepository<StringManipulationEntity, Integer> {

}
