public interface MarkingDone { // this class design to accomplish the decuppling or the open closed princible of the Task class

    public void markTaskDone(Task task);
    public void setCompletionRequirment(int x);
    public String getTYPE();
}
