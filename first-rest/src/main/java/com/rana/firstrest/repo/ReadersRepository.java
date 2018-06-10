package com.rana.firstrest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rana.firstrest.model.Readers;

@Repository
public interface ReadersRepository extends JpaRepository<Readers, Integer> {

}
