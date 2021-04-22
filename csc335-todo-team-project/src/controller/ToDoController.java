package controller;

import java.util.ArrayList;

import model.ToDoList;
import model.ToDoTask;
import view.ToDoView;

public class ToDoController {
	// Can change way to hold lists.
	private ArrayList<ToDoList> lists;
	
	public ToDoController() {
	    this.lists=new ArrayList<ToDoList>();
	    this.lists.add(new ToDoList());
	}
	
	public void addList() {
	    //TODO: IMPLEMENT
	}
	
	public void addTask(String name, String description,String deadline,String importance) { //assuming only one list. always adds to index 0 for now.
	    //TODO Later: CHECK DEADLINE AND IMPORTANCE VALIDITY BEFORE ENTERING MODEL.
	    //deadline and importance are ignored and uninitialized for now.
	    if(lists.size()>0) {
		lists.get(0).addTask(name,description,deadline,importance);
	    }
	    else {
		System.out.println("THIS LIST IS EMPTY");
	    }
	}
	
	public void removeTask(ToDoTask task) {
	    this.lists.get(0).removeTask(task);
	}
	
	@SuppressWarnings("deprecation")
	public void addObserver(ToDoView view) {
	    lists.get(0).addObserver(view);
	}
}
