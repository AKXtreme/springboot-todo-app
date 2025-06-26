package com.akxtreme.todolistapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("username")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // Show the list of todos for the current user
    @RequestMapping("todos")
    public String todos(ModelMap model) {
        String username = getLoggedInUsername(model);
        List<Todo> todos = todoService.findByUsername(username);
        model.addAttribute("todos", todos);
        return "todos";
    }

    // Show form to add a new todo
    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        String username = getLoggedInUsername(model);
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "add-todo";
    }

    // Process the form to add a new todo
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "add-todo";
        }

        String username = getLoggedInUsername(model);
        todo.setUsername(username); // ✅ Ensure the username is always set

        todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
        return "redirect:todos";
    }

    // Delete a todo
    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id, ModelMap model) {
        String username = getLoggedInUsername(model);
        todoService.deleteById(username, id);
        return "redirect:todos";
    }

    // Show form to update a todo
    @GetMapping("/update-todo")
    public String showUpdateTodoForm(@RequestParam int id, ModelMap model) {
        String username = getLoggedInUsername(model);
        Todo todo = todoService.findById(username, id);
        model.put("todo", todo);
        return "add-todo";
    }

    // Process the form to update a todo
    @PostMapping("/update-todo")
    public String updateTodo(@ModelAttribute("todo") Todo todo, ModelMap model) {
        String username = getLoggedInUsername(model);
        todo.setUsername(username); // ✅ Force correct user on update
        todoService.updateTodo(username, todo);
        return "redirect:/todos";
    }

    // Helper method to get the logged-in username securely
    private String getLoggedInUsername(ModelMap model) {
        Object username = model.get("username");
        if (username != null) return username.toString();

        // Fall back to Spring Security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
