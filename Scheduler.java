import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;
public class Scheduler {
       

   private Scanner scanner;
   private TaskService taskService = new TaskService();
        
   public void main() {
      int input = 0;
      scanner = new Scanner(System.in);
      do { 
           // get input from terminal
           printMenu();
           
           input = selectId();
         switch(input) {
         
                case 1: System.out.println("=== Create Task ===");
                        Task task = taskService.createTask();
                        addDependencyOption(task);
                        taskService.add(task);                    
                        break;
                
                case 2: System.out.println("=== List Tasks ===");
                        taskService.listTasks();
                        break;        
                
                case 3: taskService.listTasks();
                        System.out.println("=== Edit Task ==="); 
                        taskService.editTask();       
                        break;
                case 4: System.out.println("=== Compute Schedule ===");
                        taskService.printTaskSchedule();
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
  
  
 private void addDependencyOption(Task task) {
        if(taskService.getTasks().size() > 0) {
                String choice = new String();
                System.out.print("Add dependency? [y/n]");
                choice = scanner.nextLine();
                if(choice != null && choice.toLowerCase().equals("y")) {
                    taskService.listTasks();    
                    taskService.addDependency(task);
                }
        }
 } 
  

 private Integer selectId() {
        Integer option = Integer.valueOf(scanner.nextLine());
        return option;
 } 
}
