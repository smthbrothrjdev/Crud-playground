package com.playground.application.lesson;


import com.playground.application.LogiePoo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class LessonService {

    LessonRepo lessonRepo;

    public LessonService(LessonRepo lessonRepo) {
        this.lessonRepo = lessonRepo;
    }

    public List<Lesson> getAll() {
       return lessonRepo.findAll();
    }

    public Lesson addLesson(Lesson lesson) {
        if (Objects.isNull(lesson)){
            LogiePoo.log("====Error adding lesson to DB: lesson is null =====");
            return (null);
        } else if (lessonRepo.findLessonByName(lesson.getName()).isPresent()){
            LogiePoo.log("=====Error adding lesson to DB: Lesson already exists====");
            return (null);
        }else{
            lessonRepo.save(lesson);
        }
        return lesson;
    }

    public Lesson getLessonById(Long id)  {
        return lessonRepo.findById(id).orElseThrow(()->new NoSuchElementException("No lesson matching " +id+"found"));
    }

    public Lesson deleteLessonById(Long id) {

       Lesson deleteTarget = lessonRepo.findById(id).orElseThrow(
               ()->new NoSuchElementException("ERROR Deleting "+id+": does not exist"));


        lessonRepo.deleteById(deleteTarget.getId());

     return deleteTarget;
    }
}
