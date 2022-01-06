package com.ecommerce.repositories.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ecommerce.entities.UserEntity;


public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>{

	UserEntity findByEmail(String email);

	UserEntity findByUserId(String userId);
	
	// native sql
	@Query(value = "select * from users", nativeQuery = true)
	Page<UserEntity> findAllUsers(Pageable pageableRequest);
	
	//JPQL
//	@Query(value = "SELECT user FROM UserEntity user" ,nativeQuery = false)
//	Page<UserEntity> findAllUsers(Pageable pageableRequest);
	
	
//	@Query(value = "SELECT * FROM users u where u.first_name= ?1 OR u.last_name= ?1 ", nativeQuery = true)
//	Page<UserEntity> findAllUserByCriteria(Pageable pageableRequest,String search);
	
	@Query(value = "SELECT * FROM users u where u.first_name= :search OR u.last_name= :search ", nativeQuery = true)
	Page<UserEntity> findAllUserByCriteria(Pageable pageableRequest, @Param("search") String search);
}
