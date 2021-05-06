package model;

import java.io.Serializable;
import java.util.Date;

/**
 * This class implements Serializable for saving and contains the fields,
 * setter, and getter methods for the tasks. The List class accesses these 
 * to change aspects about each individual task or to obtain information for
 * sorting and displaying to the GUI. 
 * 
 * @author Henry Do, Gerry Guardiola, Mauricio Brooks, Xin Li
 *
 */
public class ToDoTask implements Serializable {
	/**
	 * From Serializable uses default ID 
	 */
    private static final long serialVersionUID = 1L;
    
    /**
	 * String name of the task. 
	 */
    private String name;
    
    /**
	 * Optional description of the task. 
	 */
    private String description;
    
    /**
	 * Deadline of task (mm/dd/year).
	 */
    private String deadline;
    
    /**
	 * Creation date of task for the sake of sorting
	 */
    private Date createTime;
    
    /**
	 * String location given by user.
	 */
    private String location;
    
    /**
	 * String signifying if the task is important or not.
	 */
    private String important;
    
    /**
	 * Boolean representing the completion status of a task.
	 */
    private boolean complete;
    
    /**
	 * Constructor for a task that takes in users input to set fields to the
	 * given strings 
	 * 
	 * @param name        The name of the task.
     * @param description The description/notes about the task.
     * @param deadline    The deadline for the task.
     * @param importance  Indication of whether the task is important or not.
     * @param location    The location of the task.
	 */
    public ToDoTask(String name, String description, String deadline, String importance, String location) {
    	this.name = name;
    	this.description = description;
    	this.deadline = deadline;
    	this.important = importance;
    	this.location = location;
    	this.createTime = new Date();
    	this.complete = false;
    }

    /**
	 * Takes a users input as a String to reset the name of a task to.
	 * 
	 * @param newName A String of the users input to change the task name to. 
	 */
    public void rename(String newName) { // TODO: implement somehow when functionality is added to GUI
    	if (newName.equals("")) {
    		this.name = "untitled task";
    	} else {
    		this.name = newName;
    	}
    }
    
    /**
	 * Getter for the name of the task.
	 * 
	 * @return String representing the name of the task.
	 */
    public String getName() {
    	return this.name;
    }

    /**
	 * Getter for the description of a task.
	 * 
	 * @return String representing the description of the task.
	 */
    public String getDescription() {
    	return this.description;
    }

    /**
	 * Getter for the deadline of the task.
	 * 
	 * @return String representing the deadline of the task.
	 */
    public String getDeadline() {
    	return this.deadline;
    }

    /**
	 * Getter for the importance of the task.
	 * 
	 * @return String representing the importance of the task.
	 */
    public String getImportance() {
    	return this.important;
    }

    /**
	 * Getter for the location of the task.
	 * 
	 * @return String representing the location of the task.
	 */
    public String getLocation() {
    	return this.location;
    }

    /**
	 * Getter for the completion status of the task.
	 * 
	 * @return boolean representing the completion status of the task.
	 */
    public boolean getCompletion() {
    	return this.complete;
    }

    /**
	 * Setter for the importance value of the task.
	 * 
	 * @param importance A String for the new importance value.
	 */
    public void setImportance(String importance) {
    	this.important = importance;
    }

    /**
	 * Setter for the completion value of the task.
	 * 
	 * @param complete A boolean for the new completion value.
	 */
    public void setCompletion(boolean complete) {
    	this.complete = complete;
    }

    /**
	 * Getter for the creation time of a task.
	 * 
	 * @return Date time of the task object. 
	 */
    public Date getCreateTime() {
    	return this.createTime;
    }
}
