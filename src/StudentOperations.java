import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StudentOperations { // this class designed to accomplish the single responcibility princible where all student 
                                // related operations are performed here

    public void markTaskDone(Task task, Student studen) { // to mark the task dine for student and task, this is the main one
        task.markTaskDone(task);
        if (task.getAssigned_status() == true && task.getCompletionState() == true) {
            studen.setAssignedTask(null);
            studen.setAssigned(false);
        }
    }

    public void assignTask(Task taskName, ArrayList<Student> student_list, Course course, ArrayList<AssignedTasks> assigned_Tasks_List) {

        // this to assign task to student
        int best_categotynum_1_id = 0;
        int best_categotynum_2_id = 0;
        int counter = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(
                "C://Users//Lenovo//Desktop//Maching_Jobs//Sheets//sheet2_studentsSurveyResults(1).csv"))) {
            String line;
            br.readLine(); // Skip header row

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                int studentID = Integer.parseInt(values[1]);
                String category = values[4];
                String courseChosen = values[course.getOrder()+1];

                if (category.equals("Category I") && best_categotynum_1_id == 0) {
                    if (courseChosen.equals("Expert") || courseChosen.equals("Advanced")) {
                        if (student_list.get(counter).isAssigned() == false) { // assuming the student order in the
                                                                               // survay is the same as in sheet 1
                            best_categotynum_1_id = studentID;
                        }
                    }
                } else {
                    if (category.equals("Category II") && best_categotynum_2_id == 0) {
                        if (courseChosen.equals("Expert") || courseChosen.equals("Advanced")) {
                            if (student_list.get(counter).isAssigned() == false) {
                                best_categotynum_2_id = studentID;
                            }
                        }
                    }
                }

                counter++;

            }

            if (best_categotynum_2_id != 0) {
                for (int i = 0; i < student_list.size(); i++) {
                    if (student_list.get(i).getId() == best_categotynum_2_id) {
                        student_list.get(i).setAssigned(true);
                        student_list.get(i).setAssignedTask(taskName);
                        taskName.setAssigned_status(true);
                        taskName.setStudent_woking(student_list.get(i));
                        assigned_Tasks_List.add(new AssignedTasks(taskName, student_list.get(i).getName(), student_list.get(i).getId()));
                    }
                }
            } else if (best_categotynum_1_id != 0) {
                for (int i = 0; i < student_list.size(); i++) {
                    if (student_list.get(i).getId() == best_categotynum_1_id) {
                        student_list.get(i).setAssigned(true);
                        student_list.get(i).setAssignedTask(taskName);
                        taskName.setAssigned_status(true);
                        taskName.setStudent_woking(student_list.get(i));
                        assigned_Tasks_List.add(new AssignedTasks(taskName, student_list.get(i).getName(), student_list.get(i).getId()));
                    }
                }
            } else {
                System.out.println("No students assigned");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}