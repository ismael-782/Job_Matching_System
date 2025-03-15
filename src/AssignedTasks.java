public class AssignedTasks {

    // this class used to separate the assigned tasks from the unassigned, which implement the concept of single responcibility

    private Task task;
    private String student;
    private int student_ID;

    public AssignedTasks(Task task, String stuName, int stuId){
        this.task = task;
        this.student = stuName;
        this.student_ID = stuId;
    }

    public String getStudent() {
        return student;
    }

    public int getStudent_ID() {
        return student_ID;
    }

    public Task getTask() {
        return task;
    }

    @Override
    public String toString() {
        return getStudent() + " " + getStudent_ID() + " " + getTask().getCourseName();
    }
    
}
