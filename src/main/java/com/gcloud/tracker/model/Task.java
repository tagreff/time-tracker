package com.gcloud.tracker.model;
import lombok.Data;
import java.time.LocalDate;

@Data
public class Task {
    Integer id;
    Integer userId;
    LocalDate date;
    String description;
    Integer hours;
    Integer minutes;
}
