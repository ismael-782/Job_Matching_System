import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;
import java.util.HashSet;



public abstract class CSVHandler { // this class is responcible to do all file reading and writting things, here I used the concept 
    // of abstract class to make it more flexible and easy to extend 

    public static ArrayList<Student> readStudent(String path){
        
        ArrayList<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine(); // Skip header row

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                int studentID = Integer.parseInt(values[1]);
                String fullName = values[2];
                String category = values[7];
                String email = values[10];

                students.add(new Student(studentID, fullName, email,category));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        return students;
    }

    //=========================================================

    public static ArrayList<Task> readTasks(String path){

        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine(); // Skip header row

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                int id = Integer.parseInt(values[0]);
                String reqEmail = values[1];
                String reqName = values[2];
                String taskType = values[3];
                String course = values[4];
                String comment = values[5];


                if (taskType.equals("Grading")) {
                    tasks.add(new Grading(reqName, reqEmail, id, course, comment));
                } else {
                    tasks.add(new Teaching(reqName, reqEmail, id, course, comment));
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return tasks;

    }

    //====================================================

    public static ArrayList<AssignedTasks> readAssignedTasks(String path, ArrayList<Task> task, ArrayList<Student> student){
        
        ArrayList<AssignedTasks> assignedTasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine(); // Skip header row

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                int id = Integer.parseInt(values[0]);
                String taskType = values[3];
                int studentID = Integer.parseInt(values[6]);

                for (int i = 0; i < task.size(); i++) {
                    if (task.get(i).getId() == id && task.get(i).getTYPE().equals(taskType)) {
                        task.get(i).setAssigned_status(true);
                        for (int j = 0; j < student.size(); j++) {
                            if (student.get(j).getId() == studentID) {
                                student.get(j).setAssigned(true);
                                student.get(j).setAssignedTask(task.get(i));
                                task.get(i).setStudent_woking(student.get(j));
                                assignedTasks.add(new AssignedTasks(task.get(i), student.get(j).getName(), student.get(j).getId()));
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return assignedTasks;
    
    }

    //==============================================

    public static ArrayList<Course> readCourses(String path){

        ArrayList<Course> courses = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int temp = 5;
            line = br.readLine(); // Skip header row
            String[] values = line.split(",");

            for (int i = 0; i < values.length - 4; i++) {
                courses.add(new Course(values[temp + 1], temp));
                if (temp == 16) {
                    break;
                }
                temp++;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }


        return courses;
    }

    //=============================================================

    public static void updatData(String path, ArrayList<Student> students, ArrayList<Task> tasks){
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            // Read the rest of the lines
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Parse the values from the CSV
                int studentId = Integer.parseInt(values[0]); // Student ID
                int taskId = Integer.parseInt(values[1]);    // Task ID

                // Find the student with the matching ID
                for (Student student : students) {
                    if (student.getId() == studentId) {
                        // Update the student: Set the assigned task to null and assigned status to false
                        student.setAssignedTask(null);  // Set assigned task to null
                        student.setAssigned(false);     // Set assigned status to false
                        break;  // Exit the loop once the student is found and updated
                    }
                }

                // Find the task with the matching ID
                for (Task task : tasks) {
                    if (task.getId() == taskId) {
                        // Update the task: Set the completion status in the subclass (using taskdone)
                        task.setCompletionState(true);  // Assuming taskdone handles completion
                        task.setAssigned_status(true);  // Set assigned status to false
                        break;  // Exit the loop once the task is found and updated
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //=====================================================================

    public static synchronized  void writFinishedTasks(ArrayList<Task> taskFinished, String filePath,String completionRemark){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            // Iterate over the tasks and write to the file
            for (Task task : taskFinished) {
                if (task.getAssigned_status() == true && task.getCompletionState() == true) {

                    int studentId = task.getStudent_woking().getId();
                    int taskId = task.getId();
                    
                    // Write the task information to the file in CSV format
                    String line = studentId + "," + taskId + "," + completionRemark;
                    bw.write(line);
                    bw.newLine();  // Move to the next line after writing each task
                }
            }
            System.out.println("The data now in Sheet5");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeAssignedTasks(ArrayList<Task> taskAssigned, String path){

        HashSet<String> existingTasks = new HashSet<>();
        
        // Read the existing tasks from the file
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine(); // Skip the header
            
            while ((line = br.readLine()) != null) {
                existingTasks.add(line); // Store each line in the set (as a string)
            }
        } catch (IOException e) {
            // If file doesn't exist, ignore this step (it will be created when writing)
            if (!(new File(path)).exists()) {
                System.out.println("File does not exist, new one will be created.");
            } else {
                e.printStackTrace();
            }
        }

        // Now, append only new tasks that do not already exist
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            for (Task task : taskAssigned) {
                if (task.getAssigned_status()) {
                    // Extract task details
                    int taskId = task.getId();
                    String requesterEmail = task.getRequesterEmail();
                    String requesterName = task.getRequesterName();
                    String taskType = (task instanceof Grading) ? "Grading" : "Teaching";  // Task type
                    String course = task.getCourseName();
                    String comment = task.getComments();
                    
                    // Get assigned student details
                    Student student = task.getStudent_woking();
                    int studentId = student.getId();
                    String studentName = student.getName();

                    // Construct the CSV line
                    String newLine = taskId + "," + requesterEmail + "," + requesterName + "," + taskType + "," + course + "," + comment + "," + studentId + "," + studentName;

                    // Only write the task if it does not already exist in the file
                    if (!existingTasks.contains(newLine)) {
                        bw.write(newLine);
                        bw.newLine();  // Move to the next line after writing each task
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}