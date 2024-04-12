import java.util.Date;
import java.util.Scanner;

// Task class
class Task {
    private int taskId;
    private String description;
    private Date dueDate;
    private String priority;
    private boolean completed;

    // Constructor
    public Task(int taskId, String description, Date dueDate, String priority) {
        this.taskId = taskId;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false; // By default, task is not completed
    }

    // Getter and setter methods
    public int getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        this.completed = true;
    }
}

// TaskManager class
class TaskManager {
    private Task[] tasks;
    private int numTasks;

    // Constructor
    public TaskManager(int capacity) {
        this.tasks = new Task[capacity];
        this.numTasks = 0;
    }

    // Method to add a new task
    public void addTask(Task task) {
        try {
            if (numTasks < tasks.length) {
                tasks[numTasks] = task;
                numTasks++;
                System.out.println("Task added successfully.");
            } else {
                throw new IllegalStateException("Task Manager is full. Cannot add more tasks.");
            }
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to mark a task as completed
    public void markTaskAsCompleted(int taskId) {
        try {
            boolean found = false;
            for (Task task : tasks) {
                if (task != null && task.getTaskId() == taskId) {
                    task.markAsCompleted();
                    System.out.println("Task marked as completed.");
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("Task not found with ID: " + taskId);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to delete a task
    public void deleteTask(int taskId) {
        try {
            boolean found = false;
            for (int i = 0; i < numTasks; i++) {
                if (tasks[i] != null && tasks[i].getTaskId() == taskId) {
                    tasks[i] = null;
                    System.out.println("Task with ID " + taskId + " deleted successfully.");
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("Task not found with ID: " + taskId);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to generate report of tasks
    public void generateReport() {
        System.out.println("Task Report:");
        for (Task task : tasks) {
            if (task != null) {
                System.out.println("Task ID: " + task.getTaskId());
                System.out.println("Description: " + task.getDescription());
                System.out.println("Due Date: " + task.getDueDate());
                System.out.println("Priority: " + task.getPriority());
                System.out.println("Completed: " + task.isCompleted());
                System.out.println("-------------------------");
            }
        }
    }
}

// Main class for testing
public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager(10); // Capacity of 10 tasks
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("------ Task Management System ------");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. Delete Task");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Add Task ---");
                    System.out.print("Enter task ID: ");
                    int taskId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter due date: ");
                    String dueDate = scanner.nextLine();
                    System.out.print("Enter priority: ");
                    String priority = scanner.nextLine();
                    Task task = new Task(taskId, description, new Date(), priority);
                    taskManager.addTask(task);
                    break;
                case 2:
                    System.out.println("\n--- Mark Task as Completed ---");
                    System.out.print("Enter task ID to mark as completed: ");
                    int taskIdToComplete = scanner.nextInt();
                    taskManager.markTaskAsCompleted(taskIdToComplete);
                    break;
                case 3:
                    System.out.println("\n--- Delete Task ---");
                    System.out.print("Enter task ID to delete: ");
                    int taskIdToDelete = scanner.nextInt();
                    taskManager.deleteTask(taskIdToDelete);
                    break;
                case 4:
                    System.out.println("\n--- Generate Report ---");
                    taskManager.generateReport();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
            System.out.println();
        }
    }
}
