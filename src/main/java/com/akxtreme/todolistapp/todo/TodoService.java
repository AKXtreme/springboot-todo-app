package com.akxtreme.todolistapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    // Return all todos for the given username
    public List<Todo> findByUsername(String username) {
        validateUsername(username);
        return repository.findByUsername(username);
    }

    // Add a new todo for the user
    public void addTodo(String username, String description, LocalDate targetDate, boolean completed) {
        validateUsername(username);
        Todo todo = new Todo();
        todo.setUsername(username);
        todo.setDescription(description);
        todo.setTargetDate(targetDate);
        todo.setCompleted(completed);
        repository.save(todo);
    }

    // Delete a todo by ID, only if it belongs to the current user
    public void deleteById(String username, int id) {
        validateUsername(username);
        Todo todo = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Todo not found with id: " + id));
        if (!username.equals(todo.getUsername())) {
            throw new SecurityException("You cannot delete another user's todo.");
        }
        repository.deleteById(id);
    }

    // Find a todo by ID, but only return it if it belongs to the user
    public Todo findById(String username, int id) {
        validateUsername(username);
        Todo todo = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Todo not found with id: " + id));
        if (!username.equals(todo.getUsername())) {
            throw new SecurityException("You cannot view another user's todo.");
        }
        return todo;
    }

    // Update a todo, only if it belongs to the user
    public void updateTodo(String username, Todo updatedTodo) {
        validateUsername(username);
        if (!username.equals(updatedTodo.getUsername())) {
            throw new SecurityException("You cannot update another user's todo.");
        }
        repository.save(updatedTodo);
    }

    // Utility method to ensure the username is valid
    private void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username must not be null or empty.");
        }
    }
}
