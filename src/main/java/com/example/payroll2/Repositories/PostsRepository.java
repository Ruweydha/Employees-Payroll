package com.example.payroll2.Repositories;

import com.example.payroll2.Entities.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

    List<Posts> findAllByEmployeeId(Long id);
}
