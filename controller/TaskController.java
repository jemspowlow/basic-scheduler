package controller;


import service.TaskService;
import model.Task;
import dto.TaskDto;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;
public class TaskController {
       

        private Scanner scanner = new Scanner(System.in);
        private TaskService taskService = new TaskService();
        
        public void createTask(TaskDto taskDto) {
                
                Task task = taskService.createTask(taskDto);
                addDependencyOption(task);
                taskService.add(task);   
        }
        
        public TaskDto getById(Integer id) {
                Task task = taskService.selectTaskById(id);
                return task.toDto();
        }
        

        public void listTasks() {
                taskService.listTasks();
        }
        
        public void editTask(Integer id) {
                Task task = taskService.selectTaskById(id);
                taskService.editTask(task);
        }
        
        public void computeSchedule() {
                taskService.printTaskSchedule();
                
        }
    
 
        private Integer selectId() {
                Integer option = Integer.valueOf(scanner.nextLine());
                return option;
        } 
        
        private void addDependencyOption(Task task) {
        // if there are more than 1 tasks, prompt to add dependency when adding a new task
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
}
