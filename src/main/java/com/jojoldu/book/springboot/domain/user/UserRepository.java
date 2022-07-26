package com.jojoldu.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {         //기본적으로 JpaRepository<Entity클래스, PK타입>을 상속하면 기본적인 CRUD가 가능하다.
    Optional<User> findByEmail(String email);

}
