package systems.taskmanagementsystemlikejira;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TaskManager {
    private static TaskManager instance;
    private Map<String, Task> tasks;

    // userToTaskMapping can be added

    private TaskManager() {
        tasks = new ConcurrentHashMap<>();
    }

    public synchronized  TaskManager getInstance() {
        if(instance == null) {
            instance = new TaskManager();
        }

        return instance;
    }

    // addTask
    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    // getTask
    public Task getTask(String taskId) {
        return tasks.get(taskId); // error handling can be added
    }

    // searchTasksByTitle
    // filterTasksByStatus
    // filterTasksByAssignee

    // addTask/createTask
    // getTask
    // getTaskByStatus
    // searchTask (go over all the task and search by title, or create)
}
