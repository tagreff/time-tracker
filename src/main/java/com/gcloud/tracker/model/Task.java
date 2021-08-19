package com.gcloud.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Class Task
 *
 * @author Igor Rogachev
 * @version 0.0.2
 * created on 18.08.2021
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private Integer id;
    private Integer userId;
    private LocalDate date;
    private String description;
    private Integer hours;
    private Integer minutes;

}