package com.playground.application.lesson;


import com.playground.application.LogiePoo;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;

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
        if (Objects.isNull(lesson)) {

            LogiePoo.log("====Error adding lesson to DB: lesson is null =====");
            throw new ObjectNotFoundException(Lesson.class, "ERROR: Lesson does not exist");

        } else if (lessonRepo.findLessonByName(lesson.getName()).isPresent()) {
            LogiePoo.log("=====Error adding lesson to DB: Lesson already exists====");
            throw new IllegalArgumentException("Lesson " + lesson.getName() + " already exists");

        } else {
            lessonRepo.save(lesson);
        }
        return lesson;
    }

    public Lesson getLessonById(Long id) {
        return lessonRepo.findById(id).orElseThrow(() -> new NoSuchElementException("No lesson matching " + id + "found"));
    }

    public Lesson deleteLessonById(Long id) {

        Lesson deleteTarget = lessonRepo.findById(id).orElseThrow(
                () -> new NoSuchElementException("ERROR Deleting " + id + ": does not exist"));


        lessonRepo.deleteById(deleteTarget.getId());

        return deleteTarget;
    }


    public Lesson patchLessonbyID(Long id, Map<String, Object> g) throws  SecurityException
    {
        Lesson patchedLesson = lessonRepo.findById(id).orElseThrow(()-> new NoSuchElementException("error patchihg, cannot find element"));
        g.forEach((k,v)->{
            Field field = ReflectionUtils.findField(Lesson.class,k);
            if (Objects.isNull(field)){
                LogiePoo.log("nothing here");
            }else{
                String fieldName= field.getName();
                String methodName = ("set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1));
                LogiePoo.log("method name =========" +methodName);

                try {
                    ReflectionUtils.invokeMethod(patchedLesson.getClass().getMethod(methodName, String.class), patchedLesson, v);
                } catch (NoSuchMethodException e) {
                    try {
                        throw new NoSuchMethodException("oops");
                    } catch (NoSuchMethodException ex) {
                        LogiePoo.log("UH OH RUNTIME EXCEPTION");
                    }
                }


            }

        });


       return lessonRepo.save(patchedLesson);
    }

    public Lesson findByName(String title) {
        return (lessonRepo.findLessonByName(title).orElseThrow(()-> new NoSuchElementException("Cannot find by lesson, no element found")));

    }

    public List<Lesson> findLessonsBetween(LocalDate startDate, LocalDate endDate) {

        return lessonRepo.findByDeliveredOnBetween(startDate,endDate);
        }
    }

