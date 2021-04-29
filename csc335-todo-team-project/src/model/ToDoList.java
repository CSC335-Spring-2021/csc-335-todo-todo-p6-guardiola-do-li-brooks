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
    private String name;
    
    /**
     * Creates a ToDoList with the name List 1.
     */
    public ToDoList() {
    	this.tasks = new ArrayList<ToDoTask>();
    	name = "List 1";
    }
    
    /**
     * Creates a ToDoList with the given name.
     * 
     * If given name is empty then the list will be automatically
     * named unamed list.
     * 
     * @param name The name of the list.
     */
    public ToDoList(String name) {
    	this.tasks = new ArrayList<ToDoTask>();
    	if (name.equals("")) {
    		this.name = "unamed list";
    	} else {
    		this.name = name;
    	}
    }
    
    /**
     * Renames the list to a new name and notifies the Observers
     * that the list has been renamed.
     * 
     * @param name The new name for the ToDoList.
     */
    public void renameList(String name) {
    	this.name = name;
    }
    
    /**
     * @return The name of the list.
     */
    public String getNameList() {
    	return name;
    }

    public void addTask(String taskName, String description, String deadline, String importance) {
		if (taskName != null) {
		    ToDoTask newTask=new ToDoTask(taskName,description);
		    this.tasks.add(newTask);  //TODO: IMPLEMENT DEADLINE AND IMPORTANCE FIELDS (additional constructors)
		    loadView();
		} else {
		    System.out.println("***TASK NAME IS NULL. ENTER VALID NAME"); // for debugging
		}

    }

    public void removeTask(int index) {
    	boolean taskSeen = this.tasks.remove(index) != null;
    	if (!taskSeen) {
    		System.out.println("***Task entered is not in the To-Do List"); // for debugging
    	}else {
    		loadView();
    	}
    }
    
    /**
     * @return The amount of tasks in the ToDoList.
     */
    public int amountTasks() {
    	return tasks.size();
	}
    
    /**
     * Gets the ToDoTask at the specified index.
     * 
     * Assumes that index given is a valid index. Can make sure it is
     * a valid index if the index is smaller than the int returned
     * by the amountTasks method.
     * 
     * @param index The index of the task.
     * @return The ToDoTask at the specified index.
     */
    public ToDoTask getTask(int index) {
    	return tasks.get(index);
    }
    
    /**
     * Notifys all observers by passing this List to it.
     */
	public void loadView() {
		setChanged();
		notifyObservers(this);
	}
	
}
