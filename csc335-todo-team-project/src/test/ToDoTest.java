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
    	ToDoTask test1 = new ToDoTask("Task 1", "First", "01/01/2005", 
    			"Important!!!", "Tucson");
    	// Checks to ensure that the task was successfully added as the first element
    	// in the list.
    	assertEquals(test1.getName(), task1.getName());
    	assertEquals(test1.getDescription(), task1.getDescription());
    	assertEquals(test1.getDeadline(), task1.getDeadline());
    	assertEquals(test1.getImportance(), task1.getImportance());
    	assertEquals(test1.getLocation(), task1.getLocation());
    	assertEquals(test1.getCompletion(), task1.getCompletion());
    	// Tests that multiple tasks can be added to the list.
    	control.addTask("Task 2", "", "01/01/2006", "", "");
    	ToDoTask task2 = list.getTask(1);
    	ToDoTask test2 = new ToDoTask("Task 2", "", "01/01/2006", "", "");
    	control.addTask("", "", "05/25/2006", "", "");
    	ToDoTask task3 = list.getTask(2);
    	ToDoTask test3 = new ToDoTask("unnamed task", "", "05/25/2006", "", "");
    	// Checks to ensure that the two extra tasks was successfully added.
    	assertEquals(test2.getName(), task2.getName());
    	assertEquals(test2.getDescription(), task2.getDescription());
    	assertEquals(test2.getDeadline(), task2.getDeadline());
    	assertEquals(test2.getImportance(), task2.getImportance());
    	assertEquals(test2.getLocation(), task2.getLocation());
    	assertEquals(test2.getCompletion(), task2.getCompletion());
    	assertEquals(test3.getName(), task3.getName());
    	assertEquals(test3.getDescription(), task3.getDescription());
    	assertEquals(test3.getDeadline(), task3.getDeadline());
    	assertEquals(test3.getImportance(), task3.getImportance());
    	assertEquals(test3.getLocation(), task3.getLocation());
    	assertEquals(test3.getCompletion(), task3.getCompletion());
    }
    
    /**
     *  Used to access the ToDoList to check that controller methods work.
     */
	@Override
	public void update(Observable o, Object arg) {
		list = ((ToDoList) arg);
	}
}
