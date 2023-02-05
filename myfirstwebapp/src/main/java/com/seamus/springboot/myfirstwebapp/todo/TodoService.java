package com.seamus.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
    // Use of static here indicates shared object space (all instances share this list)
    private static List<Todo> todos = new ArrayList<>();
    
    // static list of todos
    static {
        todos.add(new Todo(1, "Seamus", 
            "Learn AWS", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(2, "Seamus", 
            "Learn DevOps", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(3, "Seamus", 
            "Learn Fullstack", LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUsername(String username) {
        return todos;
    }
}
