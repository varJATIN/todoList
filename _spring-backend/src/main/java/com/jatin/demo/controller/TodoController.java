package com.jatin.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.jatin.demo.dao.TodoRepo;
import com.jatin.demo.model.PostDataFormat;
import com.jatin.demo.model.Todo;
// this will add an header that  'Access-control-allow -origin:http://localhost:8080'
@CrossOrigin(origins = "http://localhost:4200" )
@RestController
public class TodoController {
	@Autowired
	TodoRepo repo;
	
    private static final Logger logger = LogManager.getLogger(TodoController.class);

	
	@GetMapping("/todos")
	@ResponseBody
	public JSONObject getTodo() {
		Iterable<Todo> todos=repo.findAll();
		JSONObject obj = new JSONObject();
		List<JSONObject> todos_1 =new ArrayList<>();
		// covert todos array to desired ember Rest format
		for (Todo todo : todos) {
			JSONObject todo_obj = new JSONObject();
			todo_obj.put("id", todo.getId());
			todo_obj.put("type", "todo");
			JSONObject attrs = new JSONObject();
			attrs.put("title", todo.getTitle());
			attrs.put("body", todo.getBody());
			attrs.put("created-at", todo.getDate());
			todo_obj.put("attributes",attrs);
			todos_1.add(todo_obj);
		}
		obj.put("data", todos_1);
		obj.put("meta",null);
		return obj;
	}
	@GetMapping("/todos/{id}")
	@ResponseBody
	public JSONObject getTodo(@PathVariable("id") int id) {
		Todo todo=repo.findById(id).get();
		JSONObject obj = new JSONObject();
		JSONObject todo_obj = new JSONObject();
		todo_obj.put("id", todo.getId());
		todo_obj.put("type", "todo");
		JSONObject attrs = new JSONObject();
		attrs.put("title", todo.getTitle());
		attrs.put("body", todo.getBody());
		todo_obj.put("attributes",attrs);
		obj.put("data", todo_obj);
		return obj;
	}
	@PostMapping("/todos")
	public Iterable<Todo> addTodo(@RequestBody PostDataFormat postData ) {
		//retrive the recived object and convert them to string
		Object data=postData.getData().get("attributes");
		String jsonInString = new Gson().toJson(data);
		System.out.println(jsonInString);
//		JSONObject oo= new JSONObject(jsonInString);
		JSONObject oo=(JSONObject) JSONValue.parse(jsonInString);
		String title=new Gson().toJson(oo.get("title"));
		String date=new Gson().toJson(oo.get("created-at"));
		String body=new Gson().toJson(oo.get("body"));
//		System.out.println(date.substring(1, date.length()-1));
		repo.save(new Todo(title.substring(1, title.length()-1),date.substring(1, date.length()-1),body.substring(1, body.length()-1)));
		return repo.findAll();
	}
	// this is not changed as per ember data for-now
	@PutMapping("/todos/{id}")
	public Iterable<Todo> markComplete(Todo todo) {
		System.out.println("put mapping"+todo);
		logger.info("put mapping"+todo);
		repo.save(todo);
		return repo.findAll();
	}
	@DeleteMapping("/todos/{id}")
	public Iterable<Todo> delete(@PathVariable("id") int id) {
		Todo todo = repo.findById(id).orElse(new Todo());
		if (todo != null) {
			repo.deleteById(id);
		}
		return repo.findAll();
	}
}
