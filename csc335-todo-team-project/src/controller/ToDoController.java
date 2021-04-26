package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import model.ToDoList;
import model.ToDoModel;
import model.ToDoTask;
import view.ToDoView;

public class ToDoController {
	private ToDoModel model;
	
	/**
	 * Creates a ToDoController from the given ToDoModel.
	 * 
	 * @param model The ToDoModel which will store the data for the ToDoList
	 *              and its tasks.
	 */
	public ToDoController(ToDoModel model) {
		this.model = model;
	}
	
	/**
	 * Creates a new list.
	 * 
	 * @param view The GUI view that will be the observer for the list.
	 */
	public void addList(ToDoView view) {
	    //TODO: IMPLEMENT Note: Whenever this function is called
		// should call addObserver to add the view to the new list.
		// Or it can be done in the view. If done in view, parameter
		// for this method is unecessary.
	}
	
	/**
	 * Adds a new task to the current viewable list.
	 * 
	 * @param name The name of the task.
	 * @param description The notes/description about the task.
	 * @param deadline The deadline for the task. (Should be (m/d/yr))
	 * @param importance Indicates whether the task is important or not.
	 */
	public void addTask(String name, String description, String deadline, 
			String importance) { 
		// TODO: Must have a way to check that deadline is a valid date
		// and that Importance is valid. Currently both are not really
		// implemented.
		model.addTask(name, description, deadline, importance);
	}
	
	/**
	 * Removes the given task from the current viewable list.
	 * 
	 * @param task The ToDoTask to be removed.
	 */
	public void removeTask(int index) {
		// It might be better to send the name of the task to be deleted
		// and have the model remove the task.
	    model.removeTask(index);
	}
	
	/**
	 * Adds the view as an observer to the current list.
	 * 
	 * Must be called whenever a new list is being added in order for
	 * the GUI to be able to correctly display the new list.
	 * 
	 * @param view The GUI view that will be the observer.
	 */
	public void addObserver(ToDoView view) {
	    model.addObserver(view);
	}
	
	/**
	 * Saves the ToDoModel which houses all of the data about the ToDoLists.
	 * 
	 * @throws IOException Means that the file was not able to be written to.
	 */
	public void saveLists() throws IOException {
		model.saveLists();
	}
}
