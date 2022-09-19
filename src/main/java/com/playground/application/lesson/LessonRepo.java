package com.playground.application.lesson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepo extends JpaRepository<Lesson, Long> {
    Optional<Lesson> findLessonByName(String lessonName);
    List<Lesson> findByDeliveredOnBetween(LocalDate start, LocalDate end);
}
