
public abstract class Task {  // Encapsulation achieved by making variables private

    private String requesterName;
    private String requesterEmail;
    private String comments;
    private int id;
    private MarkingDone taskdone;
    private String courseName;
    private boolean assigned_status;
    private Student student_woking;

    public Task(String requesterName, String requesterEmail ,int id, String courseName, String comments){
        this.id = id;
        this.comments = comments;
        this.requesterName = requesterName;
        this.requesterEmail = requesterEmail;
        this.courseName = courseName;
        //----
        this.assigned_status = false;
    }
    

    public String getCourseName() {
        return courseName;
    }

    public boolean getAssigned_status(){
        return assigned_status;
    }

    public int getId() {
        return id;
    }

    public String getRequesterEmail() {
        return requesterEmail;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public String getComments() {
        return comments;
    }

    public Student getStudent_woking() {
        return student_woking;
    }

    public void setAssigned_status(boolean assigned_status) {
        this.assigned_status = assigned_status;
    }

    public String getTYPE(){
        return taskdone.getTYPE();
    }

    public void setStudent_woking(Student student_woking) {
        this.student_woking = student_woking;
    }

    //============= Abstraction Princible achieved here ===============//
    public abstract boolean getCompletionState();
    public abstract void setCompletionState(boolean completionState);
    //=================================================================//

    public void markTaskDone(Task task){ // this method will implement different implementation based on the type of the task
        taskdone.markTaskDone(task);
    }

    public void setCompletionRequirment(int x){ // this method will implement different implementation based on the type of the task
        taskdone.setCompletionRequirment(x);
    }

    @Override
    public String toString() {
        return "Task{" +
                "reqName='" + requesterName + '\'' +
                ", reqEmail='" + requesterEmail + '\'' +
                ", id=" + id +
                ", course='" + courseName + '\'' +
                ", comment='" + comments + '\'' +
                ", assigned status ='" + assigned_status + '\'' +
                '}';
    }


    
}
