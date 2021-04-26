package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import view.ToDoView;

public class ToDoModel implements Serializable {
	private static final long serialVersionUID = 1L;
	// Can change way to hold lists.
	private ArrayList<ToDoList> lists;
	private int curList;
	
	/**
	 * Creates a List of ToDoLists that has only one ToDoList with no Tasks.
	 */
	public ToDoModel() {
	    this.lists = new ArrayList<ToDoList>();
	    this.lists.add(new ToDoList());
	    curList = 0;
	}
	
	public void addList() {
	    //TODO: IMPLEMENT
	}
	
	public void switchList() {
		//TODO: IMPLEMENT
	}
	
	public void addTask(String name, String description, String deadline, 
			String importance) { 
		//assuming only one list. always adds to index 0 for now.
	    //TODO Later: CHECK DEADLINE AND IMPORTANCE VALIDITY BEFORE ENTERING MODEL.
	    //deadline and importance are ignored and uninitialized for now.
	    if(lists.size() > 0) {
	    	lists.get(curList).addTask(name, description, deadline, importance);
	    } else {
	    	System.out.println("THIS LIST IS EMPTY");
	    }
	}
	
	public void removeTask(int index) {
	    this.lists.get(curList).removeTask(index);
	}
	
	@SuppressWarnings("deprecation")
	public void addObserver(ToDoView view) {
	    lists.get(curList).addObserver(view);
	}
	
	/**
	 * Saves this controller which houses all the lists for the ToDo lists
	 * in the file save.dat
	 * 
	 * @throws IOException Means that the file was not able to be written to.
	 */
	public void saveLists() throws IOException {
		FileOutputStream file = new FileOutputStream("save.dat");
		ObjectOutputStream oos = new ObjectOutputStream(file);
		oos.writeObject(this);
		oos.close();
	}
	
	public void loadView() {
		lists.get(curList).loadView();
	}
}
