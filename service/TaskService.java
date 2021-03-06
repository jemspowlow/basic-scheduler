package service;

import model.Task;
import dto.TaskDto;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import java.time.LocalDate;

public class TaskService {
        private List<Task> tasks = new ArrayList<Task>();
        private Scanner scanner = new Scanner(System.in);
   
        public void listTasks() {
                for(int i = 0; i < tasks.size() ; i++) {
                  if(tasks.get(i) != null) {
                    System.out.println(tasks.get(i).toString());
                  }
                }
        }
      
        public Task createTask(TaskDto taskDto) {
                // create a task, get title, description, and duration
                
                int id = tasks.size();
                // Create task
                Task task = new Task(id);
                task.setTitle(taskDto.getTitle());
                task.setDescription(taskDto.getDescription());
                task.setDuration(taskDto.getDuration());

                return task;
        }
   
   
   
        public void addDependency(Task task) {
                // add the selected dependency id to the task's dependencies;
                System.out.println("=== Add Dependencies ===");
                System.out.print("Enter id of selected dependency: ");
                Integer dependencyId = selectId();

                if(task.getDependencies().contains(dependencyId)) {
                        System.out.println("Selected task is already a dependency.");
                } else {
                        task.getDependencies().add(dependencyId);
                }
        }
   
   
 
        private void removeDependency(Task task) {
                // remove the specified dependency from the master task list
                System.out.println("=== Remove Dependecies ===");
                listTasks();
                System.out.print("Enter id of selected dependency: ");
                Integer dependencyId = selectId();
                if(task.getDependencies().contains(dependencyId)) {
                     task.getDependencies().remove(dependencyId);
                } else {
                     System.out.println("Selected task is not a dependency.");   
                }
        }
        
        public void editTask(Task task) {
       
                if(task != null) {
                        System.out.println("=== Edit Task ===");
                        System.out.println("[1] Add Dependency");
                        System.out.println("[2] Remove Dependency");
                        System.out.println("[3] Exit");
                        System.out.print("Enter option: ");
                        int input = Integer.valueOf(scanner.nextLine());

                        switch(input) {

                                case 1: addDependency(task);
                                        break;
                                case 2: removeDependency(task);
                                        break;
                                default:
                                        break;

                        }
                }
                
        }
        
        public void printTaskSchedule() {
                //Task task = selectTaskById(id);

                for(Task task: tasks) {
                  LocalDate startDate = LocalDate.now();
                  scheduleTask(task, startDate);  
                }
                
                tasks.sort(null);

                System.out.println("Done scheduling task!");
        }
        
        public void add(Task task) {
                this.tasks.add(task);
        }

    
        public Task selectTaskById(Integer id) {
                // filter the list of tasks according to the given id
                List<Task> filteredTasks = Optional.ofNullable(tasks).orElse(new ArrayList<Task>()).stream().filter( (task) -> task.getId().equals(id) ).collect(Collectors.toList());
                Task selectedTask = null;
                
                if(filteredTasks.isEmpty()) {
                        System.out.println("Task not found.");
                } else {
                         // if filtered task is not empty, get first instance;
                        System.out.println("Task found.");
                        System.out.println(filteredTasks.get(0).toString());
                        selectedTask = filteredTasks.get(0);
                }

                return selectedTask;
        }
 
  
        private Integer selectId() {
                // just an input scanner
                Integer option = Integer.valueOf(scanner.nextLine());
                return option;
        } 
        

        
        public List<Task> getTasks() {
                return tasks;
        }
        
        
        public LocalDate scheduleTask(Task task,  LocalDate startDate) {
                // if task has dependencies, keep digging
                if(!task.getDependencies().isEmpty()) {
                        // for each dependency, dig for dependencies
                        // find a task with no dependency
                        for(int x = 0; x < task.getDependencies().size(); x++) {
                                // get task by Id from master list of task
                                Task dependencyTask = selectTaskById(task.getDependencies().get(x));
                                // call the function recursively
                                startDate = scheduleTask(dependencyTask, startDate);
                         
                        }
                } 
                // once a task with no dependency is found, set today as the start date
                task.setStartDate(startDate);
                
                // add the duration of each task to the startDate and set it as the endDate;
                startDate = startDate.plusDays(task.getDuration());
                task.setEndDate(startDate);
                // return new endDate as the next startDate of the task
                return startDate;
        }
        
}
