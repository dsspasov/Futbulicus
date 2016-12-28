package com.fmi.futbulicus.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fmi.futbulicus.model.Footballer;

public interface FootballerRepository extends CrudRepository<Footballer, Long> {

}
