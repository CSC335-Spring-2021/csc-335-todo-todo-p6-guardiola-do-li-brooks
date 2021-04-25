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

    public void addTask(String taskName, String description, String deadline, String importance) {
		if (taskName != null) {
		    ToDoTask newTask=new ToDoTask(taskName,description);
		    this.tasks.add(newTask);  //TODO: IMPLEMENT DEADLINE AND IMPORTANCE FIELDS (additional constructors)
		    System.out.println("ITEM ADDED"); //TODO: DELETE print statements. FOR TESTING
		    // For loop is for testing purposes. Delete later.
		    for (ToDoTask task : tasks) {
		    	System.out.println(task.getName());
		    }
		    setChanged();
		    notifyObservers((Object)newTask);
		} else {
		    System.out.println("***TASK NAME IS NULL. ENTER VALID NAME"); // for debugging
		}

    }

    public void removeTask(ToDoTask task) {
    	boolean taskSeen = this.tasks.remove(task);
    	if (!taskSeen) {
    		System.out.println("***Task entered is not in the To-Do List"); // for debugging
    	}else {
    		setChanged();
    		notifyObservers();
    	}
    }
}
