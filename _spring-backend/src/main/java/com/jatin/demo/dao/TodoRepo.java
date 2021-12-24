package com.jatin.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jatin.demo.model.Todo;

public interface TodoRepo extends JpaRepository<Todo, Integer>{

}
