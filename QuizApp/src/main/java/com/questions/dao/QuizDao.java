package com.questions.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.questions.entities.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Long> {

}
