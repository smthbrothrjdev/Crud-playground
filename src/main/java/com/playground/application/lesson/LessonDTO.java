package com.playground.application.lesson;


public class LessonDTO {

    
      
        private final Long id;
        private final String name;

        private final String deliveredOn;


        public LessonDTO(Long id, String name, String deliveredOn) {
                this.id = id;
                this.name = name;
                this.deliveredOn = deliveredOn;
        }

        public Long getId() {
                return id;
        }



        public String getName() {
                return name;
        }



        public String getDeliveredOn() {
                return deliveredOn;
        }



        public static Lesson map(LessonDTO lessonDTO) {
                Lesson lesson = new Lesson();
                lesson.setId(lessonDTO.getId());
                lesson.setName(lessonDTO.getName());
                lesson.setDeliveredOn(lessonDTO.getDeliveredOn());
        return lesson;
        }
}
