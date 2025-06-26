package com.akxtreme.todolistapp.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    @Size(min = 5, message = "Enter at least 5 characters for description")
    private String description;

    @NotNull(message = "Please pick a target date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    private boolean completed;

    public Todo() {
        // Default constructor for JPA
    }

    public Todo(int id, String username, String description, LocalDate targetDate, boolean completed) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.completed = completed;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getDescription() {
        return description;
    }
    public LocalDate getTargetDate() {
        return targetDate;
    }
    public boolean isCompleted() {
        return completed;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
