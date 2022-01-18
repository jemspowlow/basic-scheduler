package controller;

import model.Task;
import dto.TaskDto;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;
public class SchedulerController {
       

   private Scanner scanner;

   private TaskController taskController = new TaskController();
        
   public void main() {
      int input = 0;
      scanner = new Scanner(System.in);
      do { 
           // get input from terminal
           printMenu();
           
           input = selectId();
         switch(input) {
         
                case 1: System.out.println("=== Create Task ===");
                        TaskDto taskDto = createTask();
                        taskController.createTask(taskDto);               
                        break;
                
                case 2: System.out.println("=== List Tasks ===");
                        taskController.listTasks();
                        break;        
                
                case 3: taskController.listTasks();
                        System.out.println("=== Edit Task ==="); 

                        taskController.editTask(getTaskId());
                        
                        break;
                case 4: System.out.println("=== Compute Schedule ===");

                        taskController.computeSchedule(getTaskId());
                default:
                        break;
         }
      
      } while(input != 5);
       
        System.out.println("Bye!");
   }
  
  private void printMenu() {
  
        System.out.println("=== Project Plan ===");
        System.out.println("[1] Create Task");
        System.out.println("[2] List Task");
        System.out.println("[3] Edit Task");
        System.out.println("[4] Compute Schedule");
        System.out.println("[5] Exit");
        System.out.print("Enter option: ");        
  }
  
  private TaskDto createTask() {
         // create a task dto, get title, description, and duration
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Duration: ");
        int duration = Integer.valueOf(scanner.nextLine());
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(title);
        taskDto.setDescription(description);
        taskDto.setDuration(duration);
        
        return taskDto;
                
  }
  
  private Integer getTaskId() {
        System.out.print("Select task id: ");
        Integer taskId = selectId();
        return taskId;
  }
  
  
 
 private void renderTask(TaskDto taskDto) {
        System.out.println(taskDto.toString());
 }
  
 
 private Integer selectId() {
        Integer option = Integer.valueOf(scanner.nextLine());
        return option;
 } 
}
