package controller;

import java.io.IOException;
import java.util.Observer;

import javafx.scene.control.Alert;
import model.ToDoModel;

/**
 * Takes inputs from the view to send changes to the model
 * 
 * Creates a model object to send updates to that originate from the view 
 * class. Can also load in models if a save file is found. Methods possible 
 * includes loading the view when an update occurs, adding tasks/lists,
 * removing tasks/lists along with changing attributes of the tasks. 
 * 
 * @author Henry Do, Gerry Guardiola, Mauricio Brooks, Xin Li
 *
 */
public class ToDoController {
	/** 
	 * model object used to communicate view interactions/updates
	 */
    private ToDoModel model;

    /**
     * Creates a ToDoController from the given ToDoModel.
     * 
     * Note: View must use the method addObserver after this method in order for it
     * to become an Observer of the first list.
     * 
     * @param model The ToDoModel which will store the data for the ToDoList and its
     *              tasks.
     */
    public ToDoController(ToDoModel model) {
    	this.model = model;
    }

    /**
     * Loads the current List into view.
     * 
     * Must be used after the controller was created in order to load the data into
     * the view correctly.
     */
    public void loadView() {
    	model.loadView();
    }

    /**
     * Changes the list color to the given String.
     * 
     * @param color The new list color.
     */
    public void changeColor(String color) {
    	model.changeColor(color);
    }

    /**
     * Creates a new list and switches to it.
     * 
     * Note: addObserver method must be used after this method in order for the view
     * to become an Observer of the newly created list.
     * 
     * @param name The name of the new list.
     */
    public void addList(String name) {
    	model.addList(name);
    }

    /**
     * Renames the current list to the given name.
     * 
     * @param name The given name.
     */
    public void renameList(String name) {
    	model.renameList(name);
    }

    /**
     * Deletes the current list.
     * 
     * In this program it is not possible to delete the current list if the current
     * list is the only list. Boolean is used so view can recognize a failed delete
     * due to there only being one list.
     * 
     * @return True if the list was successfully deleted. False if the list was not
     *         able to be deleted because the current list is the only list.
     */
    public boolean deleteList() {
    	if (!model.moreThanOneList()) {
    		return false; // Case where current list should not be deleted.
    	}
    	model.deleteList();

    	return true;
    }

    /**
     * Iterates to the next list.
     */
    public void nextList() {
    	model.nextList();
    }

    /**
     * Iterates to the previous list.
     */
    public void prevList() {
    	model.prevList();
    }
    /**
     * Rename the task at a specified index to the new string.
     * 
     * @param name The string that the task will be renamed to. 
     * @param pos  The index of the task that will be renamed.
     */
    public void renameTask(String name,int pos) {
    	model.renameTask(name,pos);
    }

    /**
     * Adds a new task to the current viewable list.
     * 
     * @param name        The name of the task.
     * @param description The notes/description about the task.
     * @param deadline    The deadline for the task. (Should be (m/d/yr))
     * @param importance  Indicates whether the task is important or not.
     */
    public void addTask(String name, String description, String deadline, String importance, String location) {
    	if (name.equals("")) {
    		Alert error = new Alert(Alert.AlertType.INFORMATION);
    		error.setTitle("ERROR");
    		error.setHeaderText("No Name Entered");
    		error.setContentText("Must Enter a Name");
    		error.showAndWait();
    		return;
    	}
    	String[] deadlineArr=deadline.split("/");
    	if (deadline.equals("mm/dd/year") || deadlineArr.length!=3) {
    		Alert error = new Alert(Alert.AlertType.INFORMATION);
    		error.setTitle("ERROR");
    		error.setHeaderText("Invalid Date Entered");
    		error.setContentText("Use Format 'mm/dd/year'.");
    		error.showAndWait();
    		return;
    	}
    	if (location.equals("Name/Address")) {
    		location = "";
    	}
    	model.addTask(name, description, deadline, importance, location);
    }

    /**
     * Removes the given task from the current viewable list.
     * 
     * @param index The ToDoTask to be removed.
     */
    public void removeTask(int index) {
    	model.removeTask(index);
    }
    
    /**
     * Changes the importance field of a task to the input string.
     * 
     * @param important String containing the importance value of the task.
     * @param curr      Integer index of the task thats importance value is 
     * 					being changed.
     */
    public void changeImportance(String important, int curr) {
    	model.changeImportance(important, curr);
    }

    /**
     * Changes the complete field of a task to the boolean input.
     * 
     * @param complete Boolean containing the completion state of the task.
     * @param curr     Integer index of the task thats completion state is 
     * 				   being changed.
     */
    public void changeCompletion(boolean complete, int curr) {
    	model.changeCompletion(complete, curr);
    }

    /**
     * Adds the view as an observer to the current list.
     * 
     * Must be called whenever a new list is being added in order for the GUI to be
     * able to correctly display the new list.
     * 
     * @param view The GUI view that will be the observer.
     */
    public void addObserver(Observer view) {
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
    
    /**
     * Sorts the current list of task the way the given String mentions to do it.
     * 
     * sortBy can be Name, Deadline, Importance, Create time, Custom
     * Any other given sortBy will do nothing.
     * 
     * @param sortBy The way the list will be sorted.
     */
    public void sort(String sortBy) {
    	model.sort(sortBy);
    }

    /**
     * Calls model method to hide the currently completed tasks from the view.
     */
    public void hideCompletedTask() {
    	model.hideCompletedTask();
    }

    /**
     * Calls model method to show the currently completed tasks from the view.
     */
    public void showCompletedTask() {
    	model.showCompletedTask();
    }

    /**
     * Changes the position of a task up one in the list.
     * 
     * @param pos Integer of the current task being moved. 
     */
    public void moveUp(int pos) {
    	model.moveUp(pos);
    }

    /**
     * Changes the position of a task to the top of the list.
     * 
     * @param pos Integer of the current task being moved.
     */
    public void moveTop(int pos) {
    	model.moveTop(pos);
    }
    
    /**
     * For JUnit testing to check list changing.
     */
    public String getNameList() {
    	return model.getNameList();
    }
}
