package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class ToDoList extends Observable implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ArrayList<ToDoTask> tasks;

    public ToDoList() {
	this.tasks = new ArrayList<ToDoTask>();
    }

    public void addTask(ToDoTask task) {
	if (task != null) {
	    this.tasks.add(task);
	} else {
	    System.out.println("***TASK ENTERED IS NULL"); // for debugging
	}
    }

    public void removeTask(ToDoTask task) {
	boolean taskSeen = this.tasks.remove(task);
	if (!taskSeen) {
	    System.out.println("***Task entered is not in the To-Do List"); // for debugging
	}
    }

    public void saveList() {
	// TODO: Implement
    }

    public void loadList() {
	// TODO: Implement
    }
}
