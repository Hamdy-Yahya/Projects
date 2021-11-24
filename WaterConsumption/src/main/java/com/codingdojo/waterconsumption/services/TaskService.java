package com.codingdojo.waterconsumption.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.waterconsumption.models.Task;
import com.codingdojo.waterconsumption.repos.TaskRepository;

@Service
public class TaskService {
	@Autowired
	TaskRepository taskRepository;
	
	//return all tasks
	public List<Task> allTasks(){
		return taskRepository.findAll();
	}
	
	//Return all task with specific 
	public List<Task> findAllUsersTasks(Long id){
		return taskRepository.findAllByUserId(id);
	}
	//Return task by month
	public List<Task> findAllByMonth(String month){
		return taskRepository.findAllByMonth(month);
	}
	
	//return a task by id
	public Task findTask(Long id) {
		Optional<Task> optionalTask = taskRepository.findById(id);
		if(optionalTask.isPresent()) {
			return optionalTask.get();
		}else {
			return null;
		}
	}
	
	//create a task 
	public Task create(Task t) {
		return taskRepository.save(t);
	}
	//update or edit a task
	public Task updateTask(Task t) {
		return taskRepository.save(t);
		}
	//delete a task
	public void delete(Long id) {
		taskRepository.deleteById(id);
	}
}
