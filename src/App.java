import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        ArrayList<Student> student_List =  CSVHandler.readStudent("C://Users//Lenovo//Desktop//Maching_Jobs//Sheets//sheet1_studentsInfo(1).csv");
        ArrayList<Task> task_list = CSVHandler.readTasks("C://Users//Lenovo//Desktop//Maching_Jobs//Sheets//sheet3_taskOpenings(1).csv");
        ArrayList<AssignedTasks> assigned_Tasks_List = CSVHandler.readAssignedTasks("C://Users//Lenovo//Desktop//Maching_Jobs//Sheets//sheet4_assignedTasks(1).csv", task_list, student_List);
        ArrayList<Course> courses =  CSVHandler.readCourses("C://Users//Lenovo//Desktop//Maching_Jobs//Sheets//sheet2_studentsSurveyResults(1).csv");
        CSVHandler.updatData("C://Users//Lenovo//Desktop//Maching_Jobs//Sheets//sheet5_completedTasks.csv", student_List, task_list);

        String option = displayOptions();

        if (Integer.parseInt(option) == 1) {
            optionOne(student_List, task_list, courses, assigned_Tasks_List);
            CSVHandler.writeAssignedTasks(task_list, "C://Users//Lenovo//Desktop//Maching_Jobs//Sheets//sheet4_assignedTasks(1).csv");
            statistcs(student_List, task_list, assigned_Tasks_List);
        } else{
            optionTwo(student_List, task_list);
        }



    }

    public static String displayOptions(){
        System.out.println("Option#1: Run the automated Task Matching");
        System.out.println("Option#2: Mark a Task as Completed");

        Scanner input = new Scanner(System.in);
        String choice = input.nextLine().trim();

        return choice;
    }

    public static void optionOne(ArrayList<Student> student_List, ArrayList<Task> task_list, ArrayList<Course> courses, ArrayList<AssignedTasks> assigned_Tasks_List){

        StudentOperations operations = new StudentOperations();
        for (int i = 0; i < task_list.size(); i++) {
            if (task_list.get(i).getAssigned_status() == false && task_list.get(i).getCompletionState() == false) {
                for (int j = 0; j < courses.size(); j++) {
                    if (task_list.get(i).getCourseName().equals(courses.get(j).getName())) {
                        operations.assignTask(task_list.get(i), student_List , courses.get(j), assigned_Tasks_List);
                    }
                }
            }
        }
        System.out.println("Matching process is done");


    }

    public static void optionTwo(ArrayList<Student> student_List, ArrayList<Task> task_list){

        Scanner input = new Scanner(System.in);
        Student activeStudent = null;
        Task activeTask = null;
        int tracker = 0;

        System.out.println("Please write your ID");
        String student_ID = input.nextLine().trim();
        for(Student student : student_List){
            if(student.getId() == Integer.parseInt(student_ID)){
                System.out.println("The Incompleted task is: " + student.getAssignedTask().getCourseName() + ", and the type is: " + student.getAssignedTask().getTYPE());
                activeStudent = student;
                activeTask = student.getAssignedTask();
                tracker++;
                break;
            }
        }

        String remark;

        if (tracker == 0) {
            System.out.println("you have no assigned task");
        } else{
            System.out.println("Write 'Complete' to mark task completed or 'Quit' to terminate the program");
            String choice = input.nextLine().trim();
            if (choice.equals("Complete")) {
                if (activeTask.getTYPE().equals("Grading")) {
                    System.out.println("Write the Average Grades:");
                    int aveGrade = Integer.parseInt(input.nextLine().trim());
                    activeTask.setCompletionRequirment(aveGrade);
                    System.out.println("Write the remark: ");
                    remark = input.nextLine();
                    activeTask.markTaskDone(activeTask);
                } else{
                    System.out.println("Write the number of attenders: ");
                    int number_of_attend = Integer.parseInt(input.nextLine().trim());
                    activeTask.setCompletionRequirment(number_of_attend);
                    System.out.println("Write the remark: ");
                    remark = input.nextLine();
                    activeTask.markTaskDone(activeTask);
                }
                CSVHandler.writFinishedTasks(task_list, "C://Users//Lenovo//Desktop//Maching_Jobs//Sheets//sheet5_completedTasks.csv", remark);
            } else{
                System.out.println("You have terminated the program");
            }
        }

    }

    private static void statistcs(ArrayList<Student> student_List, ArrayList<Task> task_list, ArrayList<AssignedTasks> assigned_Tasks_List) {
        
        System.out.println("========================= Statistics =====================");
        System.out.println("Students Information --->");
        for(Student student : student_List){
            System.out.println("Student ID: " + student.getId() + ", Name: " + student 
            .getName() + ", Email: " + student.getEmail() + ", category: " +
            student.getCategort() + ", is Assigned Task? " + student.isAssigned());
            System.out.println("----------------------------------------------------------------------------");
        }

        System.out.println(" ");
        System.out.println("====================");
        System.out.println("Tasks Information --->");
        for(Task task : task_list){
            System.out.println("Task ID: " + task.getId() + ", Type: " + task.getTYPE() + "Course: " + task.getCourseName() + ", is Done? "
            + task.getCompletionState() + ", is Assigned? " + task.getAssigned_status());
            System.out.println("--------------------------------------------------------------");
        }

        System.out.println("Assigned Tasks Information --->");
        for(AssignedTasks assignedtasks : assigned_Tasks_List){
            System.out.println("Student ID: " + assignedtasks.getStudent_ID() + ", Task ID: " 
            + assignedtasks.getTask().getId() + ", is Done? " + assignedtasks.getTask().getCompletionState());
            System.out.println("--------------------------------------------------------------");
        }
    }

}
