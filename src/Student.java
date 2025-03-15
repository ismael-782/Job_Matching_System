public class Student { // Encapsulation achived by making the variables private. 
    private int id;
    private String name;
    private String email;
    private Task assignedTask;
    private int salary;
    private String category;
    private boolean assigned;

    public Student(int id, String name, String email,String category) {
        this.name = name;
        this.email = email;
        this.category = category;
        this.id = id;
        this.setAssigned(false);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Task getAssignedTask() {
        return assignedTask;
    }

    public String getCategort() {
        return category;
    }

    public int getSalary() {
        return salary;
    }


    public boolean isAssigned(){
        return assigned;
    }

    @Override
    public String toString() {
        return getName() + " " + getId() + " " + getEmail() + " " + getCategort() + isAssigned();
    }


    // this class only responsible for initiating the student needed
    // information and adjust the important once, also provides getters
    // ------------------------------------------------------------//

    public void setAssignedTask(Task assignesTask) {
        this.assignedTask = assignesTask;
    }

    public void setAssigned(boolean state) {
        this.assigned = state;
    }

}
