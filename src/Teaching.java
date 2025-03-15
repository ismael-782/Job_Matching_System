public class Teaching extends Task implements MarkingDone {

    // this class implement the interface and inheret from Task class,
    // here we achieved 2 OOD concepts which are the Inheratance and the Interface
    // also encapsulation shown here

    private boolean completionState;
    private String TYPE = "Teaching";
    private int numOfAtteders;

    public Teaching(String requesterName, String requesterEmail ,int id, String courseName_and_type, String comments) {
        super(requesterName, requesterEmail, id, courseName_and_type, comments);
        this.numOfAtteders = 0;
        this.completionState = false;
    }

    public boolean getCompletionState(){
        return completionState;
    }

    public String getTYPE() {
        return TYPE;
    }

    public int getNumOfAtteders() {
        return numOfAtteders;
    }

    @Override
    public String toString() {
        return "Teaching " + super.toString();
    }


    @Override
    public void markTaskDone(Task task) {
        if (task.getAssigned_status() == true) {
            if (getNumOfAtteders() != 0) {
                task.setCompletionState(true);
                
            } else{
                System.out.println("You must have at least one attender to mark the task done");
            }
        } else{
            System.out.println("Task not assigned yet");
        }
        
    }

    @Override
    public void setCompletionState(boolean completionState) {
        this.completionState = completionState;
    }

    @Override
    public void setCompletionRequirment(int x) {
        this.numOfAtteders = x;
    }

    
}
