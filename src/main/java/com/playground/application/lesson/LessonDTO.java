package com.playground.application.lesson;


public record LessonDTO(Long id, String name, String deliveredOn) {


    public static Lesson map(LessonDTO lessonDTO) {
        Lesson lesson = new Lesson();
        lesson.setId(lessonDTO.id());
        lesson.setName(lessonDTO.name());
        lesson.setDeliveredOn(lessonDTO.deliveredOn());
        return lesson;
    }
}
