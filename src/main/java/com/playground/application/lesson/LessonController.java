package com.playground.application.lesson;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class LessonController {

    LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("")
    public List<Lesson> getAllLessons() {
        return lessonService.getAll();
    }

    @PostMapping("/")
    public Lesson addLesson(@RequestBody LessonDTO lessonDTO) {


        return lessonService.addLesson(LessonDTO.map(lessonDTO));
    }


    @PatchMapping("/lessons/{id}")
    public Lesson partialUpdateLesson (@PathVariable Long id, @RequestBody Map<String, Object> g)  {

      return lessonService.patchLessonbyID(id, g);

    }


    @GetMapping("/lessons/between")
    public List<Lesson> getLessonsBetweenDate(@RequestParam String date1, String date2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //convert String to LocalDate
        LocalDate startDate = LocalDate.parse(date1, formatter);
        LocalDate endDate = LocalDate.parse(date2, formatter);

      return  lessonService.findLessonsBetween(startDate, endDate);

    }

    @GetMapping("/lessons/find/{title}")
    public Lesson findbyName(@PathVariable String title){
        return lessonService.findByName(title);
    }

    @GetMapping("/lessons/{id}")
    public Lesson getLessonById(@PathVariable Long id) {

        return lessonService.getLessonById(id);
    }

    @DeleteMapping("lessons/{id}")
    public Lesson deleteLessonById(@PathVariable Long id) {
        return lessonService.deleteLessonById(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> boopHandler(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<String> handleNoObject(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArguments(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

    }

}

