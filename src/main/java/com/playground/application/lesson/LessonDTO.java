package com.playground.application.lesson;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record LessonDTO(Long id, String name, String deliveredOn) {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static Lesson map(LessonDTO lessonDTO) {
        Lesson lesson = new Lesson();
        lesson.setId(lessonDTO.id());
        lesson.setName(lessonDTO.name());
        lesson.setDeliveredOn(LocalDate.parse( lessonDTO.deliveredOn(), formatter));
        return lesson;
    }
}
