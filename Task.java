import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Task implements Comparable<Task>{
   
   private Integer id;
   private String title;
   private String description;  
   private Integer duration;        // duration in number of days
   private List<Integer> dependencies = new ArrayList<Integer>();
   private LocalDate startDate;
   private LocalDate endDate;
   
   public Task(Integer id) {
        this.id = id;
   }
   
   public Integer getId() {
        return id;
   }
   
   public String getTitle() {
        return title;
   }
   
   public String getDescription() {
        return description;
   }
   
   public Integer getDuration() {
        return duration;
   }
   
   public List<Integer> getDependencies() {
        return dependencies;
   }
   
   public LocalDate getStartDate() {
        return startDate;
   }
   
   public LocalDate getEndDate() {
        return endDate;
   }
   
   public void setTitle(String title) {
        this.title = title;
   }
   
   public void setDescription(String description) {
        this.description = description;
   }
   
   public void setDuration(Integer duration) {
        this.duration = duration;
   }
   
   public void setDependencies(List<Integer> dependencies) {
        this.dependencies = dependencies;
   }
   
   public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
   }
   
   public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
   }
   
   public String toString() {
        return "{ \n" +    
                 " id: " + String.valueOf(id) + "\n" + 
                 " title: " + title +"\n" +
                 " description: " + description + "\n" + 
                 " duration: " + String.valueOf(duration) + "\n" +
                 " dependencies: " + dependencies.toString() + "\n" +
                 " startDate: " + startDate +"\n" +
                 " endDate: " + endDate + "\n" +
                 "}";
                                
   }
   
   @Override
   public int compareTo(Task otherTask) {
        if(startDate == null) return -1;
        
        return startDate.compareTo(otherTask.getStartDate());
   }
  
}
