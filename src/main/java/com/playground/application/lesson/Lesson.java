package com.playground.application.lesson;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate deliveredOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDeliveredOn() {
        return deliveredOn;
    }

    public void setDeliveredOn(LocalDate deliveredOn) {
        this.deliveredOn = deliveredOn;
    }
}
