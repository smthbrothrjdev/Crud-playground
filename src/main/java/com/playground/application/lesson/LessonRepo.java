package com.playground.application.lesson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LessonRepo extends JpaRepository<Lesson, Long> {
    Optional<Lesson> findLessonByName(String lessonName);
}
