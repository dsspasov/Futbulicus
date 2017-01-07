//package com.fmi.futbulicus.repository;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.stereotype.Repository;
//
//import com.fmi.futbulicus.model.User;
//
//@Repository
//@Transactional
//public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
//
//	User findByUsername(String username);
//
//	User findByUsernameAndPassword(String username, String password);
//	
//	List<User> findByUsernameContainingOrderByUsername(String username);
//	
//	List<User> findAllByOrderByUsername();
//}
