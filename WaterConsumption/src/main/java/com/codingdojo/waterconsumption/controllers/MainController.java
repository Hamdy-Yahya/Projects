package com.codingdojo.waterconsumption.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.waterconsumption.models.Task;
import com.codingdojo.waterconsumption.models.User;
import com.codingdojo.waterconsumption.services.TaskService;
import com.codingdojo.waterconsumption.services.UserService;

@Controller
public class MainController {

	@Autowired
	UserService userServ;
	@Autowired
	TaskService taskService;

    
    //Home page
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("user_id");
    	if(userId == null) {
    		return "redirect:/";
    	}else {
    		User thisUser = userServ.findOne(userId);
    		model.addAttribute("thisUser", thisUser);
    		List<Task> tasks = taskService.findAllUsersTasks(userId);
//    		System.out.println(tasks.get(0).getMonth());
    		Integer listSize = tasks.size();
    		model.addAttribute("tasks",tasks);
    		model.addAttribute("listsize",listSize);
    		ArrayList<String> theMonths = new ArrayList<String>();
    		ArrayList<String> theActivity = new ArrayList<String>();
    		ArrayList<Integer> theTime = new ArrayList<Integer>();
    		for(int i=0; i<listSize; i++) {
    			theMonths.add("'" + tasks.get(i).getMonth() + "'");
    			theActivity.add("'" + tasks.get(i).getTask() + "'");
    			theTime.add(tasks.get(i).getTime());
    		}
    		model.addAttribute("monthlist",theMonths);
    		model.addAttribute("activitylist",theActivity);
    		model.addAttribute("timelist",theTime);
//    		System.out.println(months.length); //12
//    		System.out.println(tasks.size()); //3
//    		System.out.println(tasks.get(0).getMonth()); //January
//    		System.out.println(months[0]); //January
    		return "home.jsp";
    	}
    }
    //change graph
    
    
    //Create page
    @GetMapping("/create")
    public String create(@ModelAttribute("newTask") Task task, HttpSession session) {
    	Long userId = (Long) session.getAttribute("user_id");
    	if(userId==null) {
    		return "redirect:/";
    	}else {
    		return "create.jsp";
    	}
    }
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("newTask") Task newTask,
    		BindingResult result, HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("user_id");
    	User thisUser = userServ.findOne(userId);
    	if(result.hasErrors()) {
    		return "create.jsp";
    	}else {
    		newTask.setUser(thisUser);
    		taskService.create(newTask);
    		return "redirect:/home";
    	}
    }
  //Delete a task
  	@GetMapping("/delete/{id}")
  	public String delete(@PathVariable("id") Long id) {
  		taskService.delete(id);
  		return "redirect:/home";
  	}
  	
  //Edit page
  	@GetMapping("/edit/{id}")
  	public String edit(@PathVariable("id")Long id, Model model, HttpSession session) {
  		Long userId = (Long) session.getAttribute("user_id");
    	if(userId==null) {
    		return "redirect:/";
    	}else {
    		Task task = taskService.findTask(id);
    		model.addAttribute("editTask", task);
    		return "edit.jsp";
    	}
  	}
  //Receives the updated form
  	@PostMapping("/update/{id}")
  	public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("editTask") Task task,
  			BindingResult result, Model model, HttpSession session) {
  		Long userId = (Long) session.getAttribute("user_id");
  		User thisUser = userServ.findOne(userId);
  		if(result.hasErrors()) {
  			return "edit.jsp";
  		}else {
  			task.setUser(thisUser);
  			taskService.updateTask(task);
  		return "redirect:/home";
  		}
  	}
}
