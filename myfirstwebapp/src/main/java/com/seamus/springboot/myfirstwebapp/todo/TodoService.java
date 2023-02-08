package com.seamus.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
    // Use of static here indicates shared object space (all instances share this list)
    private static List<Todo> todos = new ArrayList<>();

    private static int todosCount = 0;
    
    // static list of todos
    static {
        todos.add(new Todo(++todosCount, "Seamus", 
            "Learn AWS 1", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "Seamus", 
            "Learn DevOps 1", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todosCount, "Seamus", 
            "Learn Fullstack 1", LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUsername(String username) {
        // Only return TODOS by this logged in username
        Predicate<? super Todo> predicate
            = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++todosCount, username, description, targetDate, done);
        todos.add(todo);
    }

    public void deleteById(int id) {
        // if condition matches, remove specific Todo
        Predicate<? super Todo> predicate
            // bean name -> for every bean check id
            = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate
            // bean name -> for every bean check id
            = todo -> todo.getId() == id;
        // filter list of todos to one todo
        Todo todo = todos.stream().filter(predicate).findFirst().get();

        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
