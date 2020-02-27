package com.task.ms.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.task.ms.users.model.UserModel;

public interface UsersRepository extends JpaRepository<UserModel, String> {

	@Query("select count(u) from UserModel u where u.email = :email")
	long countUsersByEmail(@Param("email") String email);

	@Query("select u from UserModel u where u.email = :email")
	UserModel findUserByEmail(@Param("email") String email);
}
