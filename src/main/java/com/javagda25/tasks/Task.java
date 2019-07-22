package com.javagda25.tasks;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Task {
    private Long id;
    private String name;
    private LocalDateTime creationTime;
    private boolean done;
    private String creator;
}
