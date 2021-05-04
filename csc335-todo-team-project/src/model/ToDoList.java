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
    private ArrayList<ToDoTask> completedTasks;
    private String name;
    private String color;
    
    /**
     * Creates a ToDoList with the name List 1 and the color gray.
     */
    public ToDoList() {
    	this.tasks = new ArrayList<ToDoTask>();
    	this.name = "List 1";
    	this.color = "beige";
    }
    
    /**
     * Creates a ToDoList with the given name. List is automatically colored
     * biege.
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
    	this.color = "beige";
    }
    
    /**
     * @return The color of the list.
     */
    public String getColor() {
    	return color;
    }
    
    /**
     * Changes the list's color to the given one.
     * 
     * @param color The new color.
     */
    public void setColor(String color) {
    	this.color = color;
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

    public void addTask(String taskName, String description, String deadline, String importance,String location) {
		if (taskName != null) {
		    ToDoTask newTask=new ToDoTask(taskName,description,deadline,importance,location);
		    this.tasks.add(newTask);  //TODO: IMPLEMENT DEADLINE AND IMPORTANCE FIELDS (additional constructors)
		    loadView();
		}
    }

    public void removeTask(int index) {
    	boolean taskSeen = this.tasks.remove(index) != null;
    	if (!taskSeen) {
    		System.out.println("***Task entered is not in the To-Do List"); // for debugging
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

    public void hideCompleted (){
        if (completedTasks==null){
            completedTasks = new ArrayList<>();
        }
        for (ToDoTask task : tasks){
            if (task.getCompletion()){
                completedTasks.add(task);
            }
        }
        tasks.removeAll(completedTasks);
    }

    /**
     * show completed task
     */
    public void showCompleted(){
        tasks.addAll(completedTasks);
        completedTasks.clear();
    }
    
    public void moveUp(int pos){
    	ToDoTask temp;
        if (pos!=0){
            temp = tasks.get(pos);
            tasks.set(pos, tasks.get(pos-1));
            tasks.set(pos-1, temp);
        }
    }

    /**
     * move the task to top
     * @param pos
     */
    public void moveTop(int pos){
        ToDoTask temp;
        if (pos!=0){
            temp = tasks.get(pos);
            tasks.remove(pos);
            tasks.add(0, temp);
        }
    }
    
}
