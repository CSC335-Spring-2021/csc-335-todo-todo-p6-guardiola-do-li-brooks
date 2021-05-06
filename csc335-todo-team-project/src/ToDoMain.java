import javafx.application.Application;
import view.ToDoView;

/**
 * This program launches the ToDo List GUI 
 * 
 * Allows users to create multiple ToDo lists and add tasks along with 
 * specifying the task name, description, due date, and importance users
 * can also delete any tasks or ToDo lists and can mark completion of tasks.
 * Other functionality included is a variety of list sorting (i.e. sorting 
 * based on importance, due date, manual sort, ...) hiding/showing completed
 * tasks, traversing multiple lists, renaming tasks/lists, and changing 
 * background colors. Through saving all settings when traversing lists will
 * be retained or if the GUI is closed. 
 * 
 * @author Henry Do, Gerry Guardiola, Mauricio Brooks, Xin Li
 *
 */
public class ToDoMain {

	/**
	 * Launches the GUI
	 * 
	 * @param args empty String array object. 
	 */
    public static void main(String args[]) {
    	Application.launch(ToDoView.class, args);
    }
}
