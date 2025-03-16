# **TaskAdemic System**

## **📌 Project Overview**
TaskAdemic is a **Task Matching and Management System** designed to streamline the process of **assigning and tracking tasks for students**. The system ensures **efficient task distribution** based on student skills and availability, following **object-oriented design principles** for maintainability and scalability.

## **🚀 Features**
- 📌 **Automated Task Assignment** – Matches students with tasks based on their skills and availability.  
- 📁 **CSV Data Handling** – Reads and writes student, task, and assignment data.  
- ✅ **Task Completion Tracking** – Allows users to mark tasks as done.  
- 📊 **Report Generation** – Produces reports on assigned and completed tasks.  
- 🔒 **User Authentication** – Ensures secure system access.  

## **🛠️ System Architecture**
The system follows a **layered architecture**:  

1. **Application Layer (User Interface)**
   - Provides interaction with users (e.g., assigning tasks, marking tasks complete).  

2. **Business Logic Layer**
   - Implements **task assignment logic**, **grading operations**, and **student task management**.  

3. **Data Layer**
   - Manages **CSV file operations** and **data storage for students and tasks**.  

## **📌 Key Components**
### **Classes and Responsibilities**
| Class | Responsibility |
|-------|--------------|
| `Task` | Abstract class representing tasks |
| `Student` | Stores student details and assigned tasks |
| `CSVHandler` | Reads and writes CSV data |
| `TaskOperations` | Handles task assignments |
| `StudentOperations` | Manages student-related operations |
| `MarkDone` | Interface for marking tasks as completed |
| `App` | Main application logic |

## **📊 Requirements Matrix**
| Feature | Implemented In |
|---------|--------------|
| Marking Task Done | `MarkDone`, `TaskOperations` |
| Reading Data | `CSVHandler` |
| Writing Reports | `CSVHandler` |
| Assigning Tasks | `TaskOperations`, `StudentOperations` |
| User Authentication | `App` |

## **🔧 Technologies Used**
- **Programming Language:** Java ☕  
- **Data Storage:** CSV Files 📄  
- **Design Principles:** Object-Oriented Programming (OOP) and Object Oriented Design and SOLID Principles

## **⚡ Installation & Usage**
1. Clone the repository:  
   ```sh
   git clone https://github.com/YOUR-USERNAME/TaskAdemic.git
   cd TaskAdemic
   javac -d bin src/*.java
   java -cp bin App
## **🛠️ Future Enhancements**
- 📌 **Database Integration** – Replace CSV files with a database for better data management.  
- 📌 **Graphical User Interface (GUI)** – Develop a user-friendly interface for better interaction.  
- 📌 **Advanced Task Prioritization** – Implement intelligent algorithms to prioritize students based on performance.  

## **📄 License**
This project is open-source under the **MIT License**.  

