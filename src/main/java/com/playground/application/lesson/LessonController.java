package com.playground.application.lesson;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class LessonController {

    LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("")
    public List<Lesson> getAllLessons(){
        return lessonService.getAll();
    }

    @PostMapping("/")
    public Lesson addLesson( @RequestBody LessonDTO lessonDTO){


        return lessonService.addLesson(LessonDTO.map(lessonDTO));
    }


    @GetMapping("/lessons/{id}")
    public Lesson getLessonById(@PathVariable Long id){

        return lessonService.getLessonById(id);
    }
 @DeleteMapping("lessons/{id}")
    public Lesson deleteLessonById(@PathVariable Long id){
        return lessonService.deleteLessonById(id);
 }

 @ExceptionHandler(NoSuchElementException.class)
public ResponseEntity<String> boopHandler(Exception e){
        return new ResponseEntity<>( e.getMessage(),HttpStatus.NOT_FOUND);
    }
}

