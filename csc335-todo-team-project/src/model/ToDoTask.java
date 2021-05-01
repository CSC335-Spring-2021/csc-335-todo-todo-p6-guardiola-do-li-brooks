package model;

import java.io.Serializable;


//TODO: IMPLEMENT FIELDS AND FUNCTIONALITY FOR FIELDS: deadline, important

public class ToDoTask implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private String deadline;  //leaving as string for now
	private String important;
	private String location;
    
	public ToDoTask() {
	    this.name = "untitled task";
	    this.description = null;
	}
	
	public ToDoTask(String name) {
	    this.name = name;
	    this.description = null;
	}
	
	public ToDoTask(String name, String description,String deadline,String importance,String location) {
	    this.name = name;
	    this.description = description;
	    this.deadline=deadline;
	    this.important=importance;
	    this.location=location;
	    //System.out.println("Name:"+this.name+"\nDescription:"+this.description);  //TESTING PURPOSES
	}
	
	public void rename(String newName) {  //TODO: implement somehow when functionality is added to GUI
	    if(newName == null) {
	    	System.out.println("Don't rename as null");
	    } else {
	    	this.name=newName;
	    }
	}
	
	public String getName() {
	    return this.name;
	}
	
	public String getDescription() {
	    return this.description;
	}
	public String getDeadline() {
	    return this.deadline;
	}
	public String getImportance() {
	    return this.important;
	}
	public String getLocation(){
	    return this.location;
	}
	
}
