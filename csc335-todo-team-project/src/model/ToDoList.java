package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class ToDoList extends Observable implements Serializable {
	ArrayList<ToDoTask> tasks;
	
	public ToDoList() {
		// Implement
	}
	
	public void addTask() {
		// Implement
	}
	
	public void removeTask() {
		// Implement
	}
	
	public void saveList() {
		
	}
	
	public void loadList() {
		
	}
}
