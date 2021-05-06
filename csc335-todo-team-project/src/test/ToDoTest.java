package test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Observable;
import java.util.Observer;

import org.junit.jupiter.api.Test;

import controller.ToDoController;
import model.ToDoList;
import model.ToDoModel;
import model.ToDoTask;

/**
 * This class tests coverage for both the controller and model classes
 * 
 * Uses JUnit to test the total coverage of classes and implements Observer
 * for testing to allow for testing of the model class. Tests the expected
 * task outcomes to achieve above 90% coverage.
 * 
 * Note: ToDoController class reaches 93% coverage if GUI elements are omitted
 * which includes just two GUI alerts to the view which must be included for 
 * functionality of the program which is why the controller is currently at 
 * 74.1% coverage.
 * 
 * @author Henry Do, Gerry Guardiola, Mauricio Brooks, Xin Li
 *
 */
public class ToDoTest implements Observer {
	
	/**
     * ToDoList object to hold tasks.
     */
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
    	
    	int retVal = control.addTask("Task 1", "First", "01/01/2005", 
    			"Important!!!", "Tucson");
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
    	assertEquals(0, retVal);
    	
    	// Tests that multiple tasks can be added to the list.
    	control.addTask("Task 2", "", "01/01/2006", "", "Name/Address");
    	ToDoTask task2 = list.getTask(1);
    	ToDoTask test2 = new ToDoTask("Task 2", "", "01/01/2006", "", "");
    	control.addTask("Task 3", "", "05/25/2006", "", "");
    	ToDoTask task3 = list.getTask(2);
    	ToDoTask test3 = new ToDoTask("Task 3", "", "05/25/2006", "", "");
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
    	
    	// Test that a task can't be created if no name is supplied.
    	retVal = control.addTask("", "", "01/01/2001", "", "");
    	assertEquals(1, retVal);
    	// Test that a task can't be created if no deadline is supplied.
    	retVal = control.addTask("Name", "", "mm/dd/year", "", "");
    	assertEquals(2, retVal);
    	retVal = control.addTask("Name", "", "", "", "");
    	assertEquals(2, retVal);
    }
    
    /**
     * Test method for renaming existing tasks.
     */
    @Test
    void testRenameTask() {
    	// Creates an empty ToDoModel
    	ToDoModel model = new ToDoModel();
		// Creates a controller using that model and adds this test as an observer.
		ToDoController control = new ToDoController(model);
		control.addObserver(this);
		control.addTask("Task 1", "First", "01/01/2005", "Important!!!", "Tucson");
    	control.addTask("Task 2", "Second", "02/02/2005", "Important!!!", "Tucson");
    	control.addTask("Task 3", "Third", "03/03/2005", "Important!!!", "Tucson");
    	control.renameTask("First Task",0);
    	control.renameTask("Last Task", 2);
    	assertEquals("First Task",list.getTask(0).getName());
    	assertEquals("Last Task",list.getTask(2).getName());
    }
    
    /**
     *Test method for changing color of the list. 
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
     * Test method for renaming existing tasks.
     */
    @Test
    void testRenameList() {
    	// Creates an empty ToDoModel
    	ToDoModel model = new ToDoModel();
    	// Creates a controller using that model and adds this test as an observer.
    	ToDoController control = new ToDoController(model);
    	
    	control.addObserver(this);
    	control.addTask("Task 1", "First", "01/01/2005", "Important!!!", "Tucson");
    	String originalName=list.getNameList();
    	control.renameList("Grocery List");
    	assertFalse(list.getNameList().equals(originalName));
    	assertTrue(list.getNameList().equals("Grocery List"));
    }
    
    
    /**
     * Test method for deleting lists.
     */
    @Test
    void testDeleteList() {
    	// Creates an empty ToDoModel
    	ToDoModel model = new ToDoModel();
    	// Creates a controller using that model and adds this test as an observer.
    	ToDoController control = new ToDoController(model);
    	control.addObserver(this);
    	control.addTask("Task 1", "First", "01/01/2005", "Important!!!", "Tucson");
    	assertFalse(control.deleteList());
    	control.addList("Second List");
    	control.nextList();
    	String origListName=control.getNameList();
    	control.prevList();
    	assertTrue(control.deleteList());
    	assertTrue(control.getNameList().equals(origListName));
    }
    
    /**
     * Test method for switching to next list.
     */
    @Test
    void testNextList() {
    	// Creates an empty ToDoModel
    	ToDoModel model = new ToDoModel();
		// Creates a controller using that model and adds this test as an observer.
		ToDoController control = new ToDoController(model);
		control.addObserver(this);
    	control.addList("Second List");
    	control.addList("Third List");
    	control.addList("Fourth List");
    	control.nextList();
    	assertTrue(control.getNameList().equals("List 1"));
    	control.nextList();
    	assertTrue(control.getNameList().equals("Second List"));
    	assertFalse(control.getNameList().equals("List 1"));
    	control.nextList();
    	assertTrue(control.getNameList().equals("Third List"));
    	assertFalse(control.getNameList().equals("List 1"));
    	control.nextList();
    	assertTrue(control.getNameList().equals("Fourth List"));
    	assertFalse(control.getNameList().equals("List 1"));
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
    	// First should now be behind Second and Third should be behind First.
    	control.moveTop(2);
    	// Checks that Second is now the 1st task in the list.
    	assertEquals("Second", list.getTask(0).getName());
    	// Checks that First is now the 2nd task in the list.
    	assertEquals("First", list.getTask(1).getName());
    	// Checks that Third is now the 3rd task in the list.
    	assertEquals("Third", list.getTask(2).getName());



    }
    
    /**
     * Test method for switching to previous list.
     */
    @Test
    void testPrevList() {
    	// Creates an empty ToDoModel
    	ToDoModel model = new ToDoModel();
    	// Creates a controller using that model and adds this test as an observer.
    	ToDoController control = new ToDoController(model);
    	control.addObserver(this);
    	
    	control.addTask("Task 1", "First", "01/01/2005", "Important!!!", "Tucson");
    	control.addList("Second List");
    	control.addList("Third List");
    	control.addList("Fourth List");
    	for(int i=0;i<3;i++) {
    	    control.prevList();
    	}
    	assertTrue(control.getNameList().equals("List 1"));
    	control.prevList();
    	assertTrue(control.getNameList().equals("Fourth List"));
    	assertFalse(control.getNameList().equals("List 1"));
    	control.prevList();
    	assertTrue(control.getNameList().equals("Third List"));
    	assertFalse(control.getNameList().equals("List 1"));
    	control.prevList();
    	assertTrue(control.getNameList().equals("Second List"));
    	assertFalse(control.getNameList().equals("List 1"));
    }
    
    /**
     * Tests the sorting method works.
     */
    @Test
    void testSort() {
    	// Creates an empty ToDoModel
    	ToDoModel model = new ToDoModel();
    	// Creates a controller using that model and adds this test as an observer.
    	ToDoController control = new ToDoController(model);
    	control.addObserver(this);
    	
    	// Adds three tasks
    	// Thread sleep, so that the tasks don't have the same create time.
    	// Without thread sleep it is possible the computer runs fast enough
    	// that the creation time is too close that it ends up being seen as
    	// the same by the sort("Create time") code.
    	// When users manually addTask to the program through the GUI, the user
    	// adds them much slower than a computer which is why there is not
    	// thread sleep in the regular code.
    	control.addTask("Cloud", "", "08/11/1986", "Important!!!", "");
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("Shouldn't run.");
		}
    	control.addTask("Bob", "", "01/01/2001", "", "");
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("Shouldn't run.");
		}
    	control.addTask("Aerith", "", "02/07/1985", "Important!!!", "");
    	
    	// Sorts the tasks so that they are sorted alphabetically.
    	control.sort("Name");
    	// Checks that the tasks are in alphabetical order.
    	assertEquals("Aerith", list.getTask(0).getName());
    	assertEquals("Bob", list.getTask(1).getName());
    	assertEquals("Cloud", list.getTask(2).getName());
    	
    	// Sorts the tasks so that they are sorted by deadline.
    	control.sort("Deadline");
    	// Checks that the tasks are sorted by their deadlines.
    	// Correct order should be Aerith, Cloud, Bob
    	assertEquals("Aerith", list.getTask(0).getName());
    	assertEquals("Cloud", list.getTask(1).getName());
    	assertEquals("Bob", list.getTask(2).getName());
    	
    	// Sorts the tasks so that they are sorted by Create time.
    	control.sort("Create time");
    	// Checks that the tasks are sorted by their creation date.
    	// The sooner they were created the higher on the list they should be.
    	assertEquals("Cloud", list.getTask(0).getName());
    	assertEquals("Bob", list.getTask(1).getName());
    	assertEquals("Aerith", list.getTask(2).getName());
    	
    	// Sorts the tasks so that they are sorted by importance.
    	control.sort("Importance");
    	// Checks that the tasks are sorted by their importance.
    	// Also sort by importance sorts by alphabetically as well, so
    	// Aerith should come before Cloud as she is important and
    	// alphabetically first.
    	assertEquals("Aerith", list.getTask(0).getName());
    	assertEquals("Cloud", list.getTask(1).getName());
    	assertEquals("Bob", list.getTask(2).getName());
    	
    	// Sorting by custom does nothing as custom sort indicates that
    	// the sorting will be done through the moveUp and moveDown methods.
    	// If custom is sent to sort it should do nothing.
    	control.sort("Custom");
    	// Checks that nothing changed the sorting.
    	assertEquals("Aerith", list.getTask(0).getName());
    	assertEquals("Cloud", list.getTask(1).getName());
    	assertEquals("Bob", list.getTask(2).getName());
    }
    
    /**
     * Test method for removing a task.
     */
    @Test
    void testRemoveTask() {
    	// Creates an empty ToDoModel
    	ToDoModel model = new ToDoModel();
    	// Creates a controller using that model and adds this test as an observer.
    	ToDoController control = new ToDoController(model);
    	control.addObserver(this);
    	
    	control.addTask("Task 1", "First", "01/01/2005", "Important!!!", "Tucson");
    	control.removeTask(0); 
    	// task count should be 0 since 1 added and 1 removed
    	assertEquals(list.amountTasks(), 0);
    }
    
    /**
     * Test method for removing 1 out of multiple tasks.
     */
    @Test
    void testRemoveTask2() {
    	// Creates an empty ToDoModel
    	ToDoModel model = new ToDoModel();
    	// Creates a controller using that model and adds this test as an observer.
    	ToDoController control = new ToDoController(model);
    	control.addObserver(this);
    	
    	control.addTask("Task 1", "First", "01/01/2005", "Important!!!", "Tucson");
    	control.addTask("Task 2", "Second", "01/01/2005", "", "Tucson");
    	control.addTask("Task 3", "Third", "01/02/2005", "", "Tucson");
    	control.removeTask(1);
    	
    	// checks that list removed only the 2nd task putting task 3 at index 1
    	assertEquals(list.getTask(0).getName(), "Task 1");
    	assertEquals(list.getTask(1).getName(), "Task 3");
    }
    
    /**
     * Test method for changing the importance of a task.
     */
    @Test
    void testChangeImportance() {
    	// Creates an empty ToDoModel
    	ToDoModel model = new ToDoModel();
    	// Creates a controller using that model and adds this test as an observer.
    	ToDoController control = new ToDoController(model);
    	control.addObserver(this);
    	
    	control.addTask("Task 1", "First", "01/01/2005", "Important!!!", "Tucson");
    	control.addTask("Task 2", "Second", "01/01/2005", "", "Tucson");
    	
    	// swapped importance values
    	control.changeImportance("", 0);
    	control.changeImportance("important!!!", 1);
    	
    	assertEquals(list.getTask(0).getImportance(), "");
    	assertEquals(list.getTask(1).getImportance(), "important!!!");
    }
    
    /**
     * Test method for changing the completion of a task.
     */
    @Test
    void testChangeCompletion() {
    	// Creates an empty ToDoModel
    	ToDoModel model = new ToDoModel();
    	// Creates a controller using that model and adds this test as an observer.
    	ToDoController control = new ToDoController(model);
    	control.addObserver(this);
    	
    	control.addTask("Task 1", "First", "01/01/2005", "Important!!!", "Tucson");
    	control.addTask("Task 2", "Second", "01/01/2005", "", "Tucson");
    	control.changeCompletion(true, 0);
    	control.changeCompletion(true, 1);
    	
    	assertEquals(list.getTask(0).getCompletion(), true);
    	assertEquals(list.getTask(1).getCompletion(), true);
    	
    	control.changeCompletion(false, 0);
    	assertEquals(list.getTask(0).getCompletion(), false);
    }
    
    /**
     * Test method for changing the completion of a task.
     */
    @Test
    void testHideCompleted() {
    	// Creates an empty ToDoModel
    	ToDoModel model = new ToDoModel();
    	// Creates a controller using that model and adds this test as an observer.
    	ToDoController control = new ToDoController(model);
    	control.addObserver(this);
    	
    	control.addTask("Task 1", "First", "01/01/2005", "Important!!!", "Tucson");
    	control.addTask("Task 2", "Second", "01/01/2005", "", "Tucson");
    	control.changeCompletion(true, 0);
    	control.changeCompletion(true, 1);
    	
    	control.hideCompletedTask();
    	
    	// All tasks are completed and hidden so no tasks should be seen 
    	assertEquals(list.amountTasks(), 0);
    }
    
    /**
     * Test method for changing the completion of a task.
     */
    @Test
    void testShowCompleted() {
    	// Creates an empty ToDoModel
    	ToDoModel model = new ToDoModel();
    	// Creates a controller using that model and adds this test as an observer.
    	ToDoController control = new ToDoController(model);
    	control.addObserver(this);
    	
    	control.addTask("Task 1", "First", "01/01/2005", "Important!!!", "Tucson");
    	control.addTask("Task 2", "Second", "01/01/2005", "", "Tucson");
    	control.changeCompletion(true, 0);
    	control.changeCompletion(true, 1);
    	
    	// hide all tasks and check if any are found
    	control.hideCompletedTask();
    	assertEquals(list.amountTasks(), 0);
    	
    	// now show all tasks and see if they are now present
    	control.showCompletedTask();
    	assertEquals(list.amountTasks(), 2);
    }
    
    /**
     * Used to test the saving function of ToDo
     */
    @Test
    void testSaveLists() {
    	// Creates an empty ToDoModel
    	ToDoModel model = new ToDoModel();
    	// Creates a controller using that model and adds this test as an observer.
    	ToDoController control = new ToDoController(model);
    	control.addObserver(this);
    	
    	// Adds two tasks and a new list with one task and then saves the ToDo
    	// lists. Also changes color of list to make sure that is saved too.
    	control.addTask("Yellow", "Color", "01/01/2001", "Important!!!", "Tucson");
    	control.addTask("Bellow", "", "05/05/2021", "", "");
    	control.changeColor("Blue");
    	control.renameList("First");
    	control.addList("List 2");
    	control.addTask("Task", "", "01/05/2021", "", "");
    	control.changeColor("Red");
    	try {
			control.saveLists();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Shouldn't run!");
		}
    	// At this point the list should be saved in save.dat
    	
    	// Loads the file and creates a new control object using that save.
    	// This code is taken straight from the GUI.
    	ToDoModel modelToBeSent = null;
    	try {
    		// Case where there is/are existing ToDoLists saved.
    		FileInputStream file = new FileInputStream("save.dat");
    		ObjectInputStream ois = new ObjectInputStream(file);
    		modelToBeSent = (ToDoModel) ois.readObject();
    		ois.close();
    		file.close();
    	} catch (FileNotFoundException e) {
    		// Case where there is no existing ToDoLists saved.
    		modelToBeSent = new ToDoModel();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		System.out.println("I/O error while reading ObjectInputStream");
    	} catch (ClassNotFoundException e) {
    		System.out.println("Serialization class could not be found!");
    	}
    	ToDoController control2 = new ToDoController(modelToBeSent);
    	control2.addObserver(this);
    	
    	// Checks that the current list still has the name, color, and tasks
    	assertEquals("List 2", list.getNameList());
    	assertEquals("Red", list.getColor());
    	assertEquals("Task", list.getTask(0).getName());
    	assertEquals("", list.getTask(0).getDescription());
    	assertEquals("01/05/2021", list.getTask(0).getDeadline());
    	assertEquals("", list.getTask(0).getImportance());
    	assertEquals("", list.getTask(0).getLocation());
    	
    	// Iterates to the first list.
    	control2.prevList();
    	// Checks that the previous list was also saved.
    	assertEquals("First", list.getNameList());
    	assertEquals("Blue", list.getColor());
    	assertEquals("Yellow", list.getTask(0).getName());
    	assertEquals("Color", list.getTask(0).getDescription());
    	assertEquals("01/01/2001", list.getTask(0).getDeadline());
    	assertEquals("Important!!!", list.getTask(0).getImportance());
    	assertEquals("Tucson", list.getTask(0).getLocation());
    	assertEquals("Bellow", list.getTask(1).getName());
    	assertEquals("", list.getTask(1).getDescription());
    	assertEquals("05/05/2021", list.getTask(1).getDeadline());
    	assertEquals("", list.getTask(1).getImportance());
    	assertEquals("", list.getTask(1).getLocation());
    	
    }
    
    /**
     *  Used to access the ToDoList to check that controller methods work.
     */
	@Override
	public void update(Observable o, Object arg) {
		list = ((ToDoList) arg);
	}
}
