package model;

import java.io.Serializable;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Observable;

/**
 * This class extends Observable and implements Serializable for saving.
 * Compiles task objects into contained lists and allows for updating of 
 * specified task fields and sorting. 
 * 
 * @author Henry Do, Gerry Guardiola, Mauricio Brooks, Xin Li
 *
 */
@SuppressWarnings("deprecation")
public class ToDoList extends Observable implements Serializable {
	/**
	 * From Serializable uses default ID 
	 */
    private static final long serialVersionUID = 1L;
    
    /**
	 * ArrayList that holds all tasks of current list
	 */
    private ArrayList<ToDoTask> tasks;
    
    /**
	 * ArrayLists that holds all tasks marked as completed
	 */
    private ArrayList<ToDoTask> completedTasks;
    
    /**
	 * Name of the current list
	 */
    private String name;
    
    /**
	 * Background color of the current list
	 */
    private String color;
    
    /**
	 * Current sorting format used in current list
	 */
    private String currentSorting;
    
    /**
	 * Boolean determining if the completed tasks should be hidden/shown
	 */
    private boolean hideComplete;

    /**
     * Creates a ToDoList with the name List 1 and the color gray.
     */
    public ToDoList() {
    	this.tasks = new ArrayList<ToDoTask>();
    	this.name = "List 1";
    	this.color = "beige";
    	this.currentSorting = "Custom";
    	this.hideComplete = false;
    }

    /**
     * Creates a ToDoList with the given name. List is automatically colored biege.
     * 
     * If given name is empty then the list will be automatically named unamed list.
     * 
     * @param name The name of the list.
     */
    public ToDoList(String name) {
    	this.tasks = new ArrayList<ToDoTask>();
    	this.name = name;
    	this.color = "beige";
    	this.currentSorting = "Custom";
    	this.hideComplete = false;
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
     * Renames the list to a new name and notifies the Observers that the list has
     * been renamed.
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
    
    /**
	 * Receives String from input to rename specified task to the new string.
	 * 
	 * @param name String containing the new task name.
	 * @param pos  Integer position of the current task being renamed.
	 */
    public void renameTask(String name, int pos) {
    	ToDoTask temp=tasks.get(pos);
    	temp.rename(name);
    }

    /**
     * Adds a new task to the current viewable list.
	 * 
	 * @param taskName    The name of the task.
     * @param description The notes/description about the task.
     * @param deadline    The deadline for the task. (Should be (m/d/yr))
     * @param importance  Indicates whether the task is important or not.
     * @param location    The String representing the location of the task.
	 */
    public void addTask(String taskName, String description, String deadline, String importance, String location) {
    	ToDoTask newTask = new ToDoTask(taskName, description, deadline, importance, location);
    	this.tasks.add(newTask);
    }
    
    /**
	 * Removes task at a specified index from the ArrayList.
	 * 
	 * @param index Integer position of the task being removed.
	 */
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
     * Assumes that index given is a valid index. Can make sure it is a valid index
     * if the index is smaller than the int returned by the amountTasks method.
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

    /**
     * Sorts the list of tasks by the deadline. The sooner a deadline is
     * the higher the task will be placed on the list.
     */
    public void sortByDeadline() {
    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    	Date d1;
    	Date d2;
    	String timeStr1;
    	String timeStr2;
    	ToDoTask temp;
    	currentSorting = "Deadline";

    	for (int i = 0; i < tasks.size() - 1; i++) {
    		for (int j = i + 1; j < tasks.size(); j++) {
    			ParsePosition pos1 = new ParsePosition(0);
    			ParsePosition pos2 = new ParsePosition(0);
    			timeStr1 = tasks.get(i).getDeadline();
    			timeStr2 = tasks.get(j).getDeadline();
    			d1 = sdf.parse(timeStr1, pos1);
    			d2 = sdf.parse(timeStr2, pos2);
    			if (d2 == null) {
    				continue;
    			} else if (d1 == null || d2.before(d1)) {
    				temp = tasks.get(i);
    				tasks.set(i, tasks.get(j));
    				tasks.set(j, temp);
    			}
    		}
    	}

    }

    /**
     * Sorts the list of tasks by importance and also name. (Meaning letter A
     * important tasks will be higher on the list than Z important tasks)
     */
    public void sortByImportance() {
    	ArrayList<ToDoTask> important = new ArrayList<>();
    	ArrayList<ToDoTask> unimportant = new ArrayList<>();

    	sortByName();
    	// Must be placed after or else currentSorting will be set to Name
    	currentSorting = "Importance";

    	for (ToDoTask task : tasks) {
    		if (task.getImportance().contains("Important!!!")) {
    			important.add(task);
    		} else {
    			unimportant.add(task);
    		}
    	}

    	tasks.clear();
    	tasks.addAll(important);
    	tasks.addAll(unimportant);
    }

    /**
     * Sorts the list of tasks by the name alphabetically.
     */
    public void sortByName() {
    	currentSorting = "Name";
    	Collections.sort(tasks, new TaskNameCompare());
    }

    /**
     * Sorts the list of tasks by the date that the task was created.
     * The earlier the task was created the higher it will be placed on the
     * list.
     */
    public void sortByCreateTime() {
    	currentSorting = "Create time";
    	Date d1, d2;
    	ToDoTask temp;
    	for (int i = 0; i < tasks.size() - 1; i++) {
    		for (int j = i + 1; j < tasks.size(); j++) {
    			d1 = tasks.get(i).getCreateTime();
    			d2 = tasks.get(j).getCreateTime();
    			if (d2 == null) {
    				return;
    			} else if (d1 == null || d2.before(d1)) {
    				temp = tasks.get(i);
    				tasks.set(i, tasks.get(j));
    				tasks.set(j, temp);
    			}
    		}
    	}

    }

    /**
     * hide completed task
     */
    public void hideCompleted() {
    	hideComplete = true;
    	if (completedTasks == null) {
    		completedTasks = new ArrayList<>();
    	}
    	for (ToDoTask task : tasks) {
    		if (task.getCompletion()) {
    			completedTasks.add(task);
    		}
    	}
    	tasks.removeAll(completedTasks);

    }

    /**
     * show completed task
     */
    public void showCompleted() {
    	hideComplete = false;
    	tasks.addAll(completedTasks);
    	completedTasks.clear();
    }

    /**
     * Move the task up
     * 
     * @param pos The index position of the Task to be moved up.
     */
    public void moveUp(int pos) {
    	currentSorting = "Custom";
    	ToDoTask temp;
    	if (pos != 0) {
    		temp = tasks.get(pos);
    		tasks.set(pos, tasks.get(pos - 1));
    		tasks.set(pos - 1, temp);
    	}	

    }

    /**
     * Move the task to top
     * 
     * @param pos The index position of the Task to be moved to the top.
     */
    public void moveTop(int pos) {
    	currentSorting = "Custom";
    	ToDoTask temp;
    	if (pos != 0) {
    		temp = tasks.get(pos);
    		tasks.remove(pos);
	    	tasks.add(0, temp);
    	}

    }

    /**
     * Returns how the list is currently sorted.
     * 
     * Possible sortings are Name, Deadline, Importance, Create time, Custom
     * 
     * @return The way the list is being sorted currently.
     */
    public String getCurrentSorting() {
    	return currentSorting;
    }

    /**
     * @return True if the completed tasks are currently being hidden. False
     *         otherwise.
     */
    public boolean getHideComplete() {
    	return hideComplete;
    }
}
