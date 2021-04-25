package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Observable;
import java.util.Observer;

import controller.ToDoController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.ToDoModel;
import model.ToDoTask;

public class ToDoView extends Application implements Observer {
    private ToDoController control;
    private String curName;
    private String curDescription;
    private String curDeadline;
    private String curImportant;

    public void start(Stage stage) {
	stage.setTitle("ToDo");
	BorderPane window = new BorderPane();
	// If anyone else wants a different window size mention it.
	Scene scene = new Scene(window, 400, 600); // 400 px wide, 600 px tall

	// VBox will be used to showoff all the tasks to the user
	VBox taskSection = new VBox(10); // 10 px spacing between rows
	taskSection.setPadding(new Insets(10)); // 10px padding around VBox
	window.setCenter(taskSection);

	// Below code might be needed to change depending on how ToDoController is
	// implemented.
	// Creates the model to be used by the controller.
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
	control = new ToDoController(modelToBeSent);
	control.addObserver(this);

	// Buttons to be used to add tasks
	Button addTask = new Button("Add Task");
	window.setTop(addTask);
	// Event handler when button is clicked.
	EventHandler<ActionEvent> taskHandler = new NewTaskHandler();
	addTask.setOnAction(taskHandler);
	
	// Code to save all the lists when the window is closed.
	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		@Override
		public void handle(WindowEvent arg0) {
			try {
				control.saveLists();
			} catch (IOException e) {
				System.out.println("Error: Could not save Lists!");
			}
		}
	});

	stage.setScene(scene);
	stage.show();
}

    /**
     * Class deals with adding new tasks to the list when the add task button is
     * clicked.
     * 
     * @author Henry Do
     *
     */
    private class NewTaskHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
		    // Opens a new window for the user to implement info about
		    // a new task for.
		    GridPane window2 = new GridPane();
		    // Sets up 2nd window, so that there is enough space for labels
		    // and textfield
		    for (int i = 0; i < 5; i++) {
				RowConstraints row = new RowConstraints();
				row.setPercentHeight(25);
				window2.getRowConstraints().add(row);
		    }
		    ColumnConstraints col1 = new ColumnConstraints();
		    col1.setPercentWidth(50);
		    window2.getColumnConstraints().add(col1);
		    ColumnConstraints col2 = new ColumnConstraints();
		    col2.setPercentWidth(50);
		    window2.getColumnConstraints().add(col2);
	
		    // Sets up area for user to input name of New Task
		    Label name = new Label("Name: ");
		    TextField nameInput = new TextField();
		    window2.add(name, 0, 0);
		    window2.add(nameInput, 1, 0);
		    
		    // Sets up area for user to input description of New Task
		    Label description = new Label("Description: ");
		    TextField descriptionInput = new TextField();
		    window2.add(description, 0, 1);
		    window2.add(descriptionInput, 1, 1);
		    
		    // Sets up area for user to input deadline date of New Task
		    Label deadline = new Label("Deadline: ");
		    TextField deadlineInput = new TextField("m/d/yr");
		    window2.add(deadline, 0, 2);
		    window2.add(deadlineInput, 1, 2);
		    
		    // Sets up area for user to input importance
		    // Maybe make this a button or just don't have the user able
		    // to set importance here. Have them set it in main window?
		    Label importance = new Label("Important: ");
		    TextField importanceInput = new TextField("yes/no");
		    window2.add(importance, 0, 3);
		    window2.add(importanceInput, 1, 3);
	
		    // Sets up button to close the window and create the new task
		    Button enter = new Button("Create New Task");
		    window2.add(enter, 1, 4);
	
		    Scene scene2 = new Scene(window2, 250, 300);
	
		    Stage stage2 = new Stage();
		    stage2.setTitle("New Task");
		    stage2.setScene(scene2);
		    
		    // Implementation of New Task button
		    enter.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					curName = nameInput.getText();
					curDescription = descriptionInput.getText();
					curDeadline = deadlineInput.getText();
					curImportant = importanceInput.getText();
					// TODO: curDeadline and curImportant not implemented currently.
					control.addTask(curName, curDescription, curDeadline, curImportant);
					
					stage2.close();
				}
		    	
		    });
	
		    stage2.showAndWait();
		}

    }

    private class TaskAddedHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent enter) {
		    control.addTask(curName, curDescription, curDeadline, curImportant); // TODO: LAST TWO UNIMPLEMENTED ARGS
		}

    }

    @Override
    public void update(Observable o, Object newTask) {
    	// Currently ToDoTask is passed as an object. However whoever implements this method
    	// may change that. It might be easier to pass ToDoList and have methods in ToDoList
    	// ToDoTask that allow access to a task.
		// TODO: visually change GUI here
		if(newTask==null) {
		    //TODO: MEANS SOMETHING WAS REMOVED. update gui
		} else {  //means new task added
		    ToDoTask task=(ToDoTask)newTask;
		    //TODO: ADD NEW TASK TO GUI
		}

    }
}
