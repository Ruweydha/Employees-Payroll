package com.example.payroll2.Services;

import com.example.payroll2.Dto.PostsCreate;
import com.example.payroll2.Entities.Employee;
import com.example.payroll2.Entities.Posts;
import com.example.payroll2.Repositories.EmployeeRepository;
import com.example.payroll2.Repositories.PostsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final EmployeeRepository employeeRepository;

    public PostsService(PostsRepository postsRepository, EmployeeRepository employeeRepository) {
        this.postsRepository = postsRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Posts> getAllPosts(){
        var posts = postsRepository.findAll();
        return posts;
    }

    public Posts createNewPost(PostsCreate postsCreate){
        Employee employee = employeeRepository.getReferenceById(postsCreate.getEmployeeId());
        Posts newPost = new Posts();
        newPost.setBody(postsCreate.getBody());
        newPost.setTitle(postsCreate.getTitle());
        newPost.setEmployee(employee);
        postsRepository.save(newPost);
        return newPost;
    }



}
