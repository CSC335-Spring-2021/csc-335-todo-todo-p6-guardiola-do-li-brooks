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
    void testAddTask() {
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
    	control.addTask("Task 2", "", "01/01/2006", "", "Name/Address");
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
     * Test method for changing color of the list. 
     */
    @Test
    void testChangeColor() {
    	// Creates an empty ToDoModel
    	ToDoModel model = new ToDoModel();
    	// Creates a controller using that model and adds this test as an observer.
    	ToDoController control = new ToDoController(model);
    	control.addObserver(this);
    	// Tests that the current list was changed to the color red.
    	control.changeColor("Red");
    	assertEquals("Red", list.getColor());
    	// Tests that the current list can have it's color changed again.
    	control.changeColor("Blue");
    	assertEquals("Blue", list.getColor());
    	// Tests that another list can have a different color.
    	control.addList("List 2");
    	control.changeColor("Tan");
    	assertEquals("Tan", list.getColor());
    	// Tests that previous list still has the color correctly stored.
    	control.nextList();
    	assertEquals("Blue", list.getColor());
    }
    
    /**
     * Tests method for manually sorting a task through the
     * moveUp and moveTop methods.
     */
    @Test
    void testMoveUpAndTop() {
    	// Creates an empty ToDoModel
    	ToDoModel model = new ToDoModel();
    	// Creates a controller using that model and adds this test as an observer.
    	ToDoController control = new ToDoController(model);
    	control.addObserver(this);
    	// Adds three tasks
    	control.addTask("First", "", "01/01/2001", "", "");
    	control.addTask("Second", "", "01/01/2021", "", "");
    	control.addTask("Third", "", "01/01/2001", "", "");
    	// Moves the task named Third up, so that the task named Second is
    	// placed after it. First should still be the first task.
    	control.moveUp(2);
    	// Checks that Third is now the 2nd task in the list.
    	assertEquals("Third", list.getTask(1).getName());
    	// Checks that Second is now the 3rd task in the list.
    	assertEquals("Second", list.getTask(2).getName());
    	// Checks that First is still the 1st task in the list.
    	assertEquals("First", list.getTask(0).getName());
    	// Checks that currentSorting for the list has been set to Custom
    	assertEquals("Custom", list.getCurrentSorting());
    	// Moves the task named Second to be the top of the list (first task),
    	// First should now be behind Second and Thrid should be behind First.
    	control.moveTop(2);
    	// Checks that Second is now the 1st task in the list.
    	assertEquals("Second", list.getTask(0).getName());
    	// Checks that First is now the 2nd task in the list.
    	assertEquals("First", list.getTask(1).getName());
    	// Checks that Third is now the 3rd task in the list.
    	assertEquals("Third", list.getTask(2).getName());
    }
    
    /**
     *  Used to access the ToDoList to check that controller methods work.
     */
	@Override
	public void update(Observable o, Object arg) {
		list = ((ToDoList) arg);
	}
}
