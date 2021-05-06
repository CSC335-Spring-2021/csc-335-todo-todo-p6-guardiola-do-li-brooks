package model;

/**
 * Used to compare the string names of two tasks for sorting by implementing
 * Comparator. 
 * 
 * @author Henry Do, Gerry Guardiola, Mauricio Brooks, Xin Li
 *
 */
public class TaskNameCompare implements java.util.Comparator<ToDoTask> {
	/**
	 * Used to compare the string names of two tasks for sorting. 
	 * 
	 * @param  t1 ToDoTask object to compare name with other task.
	 * @param  t2 ToDoTask object to compare name with other task.
	 * @return integer based on if first string precedes the second string or
	 * 		   not.
	 */
    @Override
    public int compare(ToDoTask t1, ToDoTask t2) {
        return t1.getName().compareTo(t2.getName());
    }

}