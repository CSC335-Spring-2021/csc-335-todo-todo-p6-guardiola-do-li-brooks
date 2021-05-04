package test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Observable;
import java.util.Observer;

import org.junit.jupiter.api.Test;

import controller.ToDoController;
import model.ToDoList;
import model.ToDoModel;
import model.ToDoTask;

public class ToDoTest implements Observer {
	private ToDoList list;

    /**
     * Test method for adding tasks to the List.
     */
    @Test
    void testaddTask() {
    	// Creates an empty ToDoModel
    	ToDoModel model = new ToDoModel();
    	// Creates a controller using that model and adds this test as an observer.
    	ToDoController control = new ToDoController(model);
    	control.addObserver(this);
    	control.addTask("Task 1", "First", "01/01/2005", "Important!!!", "Tucson");
    	ToDoTask task1 = list.getTask(0);
    	ToDoTask test = new ToDoTask("Task 1", "First", "01/01/2005", 
    			"Important!!!", "Tucson");
    	// Checks to ensure that the task was successfully added as the first element
    	// in the list.
    	assertEquals(test.getName(), task1.getName());
    	assertEquals(test.getDescription(), task1.getDescription());
    	assertEquals(test.getDeadline(), task1.getDeadline());
    	assertEquals(test.getImportance(), task1.getImportance());
    	assertEquals(test.getLocation(), task1.getLocation());
    	assertEquals(test.getCompletion(), task1.getCompletion());
    	// TODO: Test adding multiple tasks.
    }
    
    /**
     *  Used to access the ToDoList to check that controller methods work.
     */
	@Override
	public void update(Observable o, Object arg) {
		list = ((ToDoList) arg);
	}
}
