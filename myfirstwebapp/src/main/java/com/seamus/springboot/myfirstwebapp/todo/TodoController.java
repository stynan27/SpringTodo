package com.seamus.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoController {

    private TodoService todoService;
    
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // /list-todos
    @RequestMapping("/list-todos")
    public String listAllTodos(ModelMap model) {
        List<Todo> todos = todoService.findByUsername("Seamus");
        model.addAttribute("todos", todos);

        return "listTodos";
    }

    @RequestMapping(value="/add-todo", method=RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        // Get username using model 
        // (original model.put() in LoginController and added to current Session: @SessionAttributes("name"))
        String username = (String)model.get("name");
        // Create an intial Todo for 2-way data binding (provides form with initial values)
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);

        return "todo";
    }

    @RequestMapping(value="/add-todo", method=RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        // Check for validation errors with the Todo model
        // such that an error page won't be displayed
        if(result.hasErrors()) {
            return "todo";
        }
        // Get username using model 
        // (original model.put() in LoginController and added to current Session: @SessionAttributes("name"))
        String username = (String)model.get("name");
        todoService.addTodo(username, todo.getDescription(), LocalDate.now().plusYears(1), false);
        // if you just return the JSP "listTodos" it will be EMPTY
        // Instead of re-populating the list, you can re-use /list-todos
        // ... via a redirect to that url endpoint.
        return "redirect:list-todos";
    }

    @RequestMapping("/delete-todo")
    public String deleteTodo(@RequestParam int id) {
        // /todoService.findById();
        todoService.deleteById(id);
        return "redirect:list-todos";
    }
}
