public class Grading extends Task implements MarkingDone {

    // this class implement the interface and inheret from Task class,
    // here we achieved 2 OOD concepts which are the Inheratance and the Interface
    // also encapsulation shown here

    private boolean completionState;
    private String TYPE = "Grading";
    private int aveGrades;

    public Grading(String requesterName, String requesterEmail, int id, String courseName_and_type, String comments) {
        super(requesterName, requesterEmail, id, courseName_and_type, comments);
        this.aveGrades = 0;
        this.completionState = false;
    }

    public String getTYPE() {
        return TYPE;
    }

    public int getAveGrades() {
        return aveGrades;
    }

    public boolean getCompletionState() {
        return completionState;
    }

    public void setCompletionState(boolean completionState) {
        this.completionState = completionState;
    }

    @Override
    public String toString() {
        return "Grading " + super.toString();
    }

    @Override
    public void markTaskDone(Task task) {
        if (task.getAssigned_status() == true) {
            if (getAveGrades() != 0) {
                task.setCompletionState(true);
        
            } else {
                System.out.println("Grading not completed yet");
            }
        } else{
            System.out.println("Task not assigned yet");
        }

    }

    @Override
    public void setCompletionRequirment(int x) {
        this.aveGrades = x;
    }


}
