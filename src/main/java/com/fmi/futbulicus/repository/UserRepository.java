package com.fmi.futbulicus.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fmi.futbulicus.model.User;

@Repository
@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Integer>{

	User findByName(String name);
}
