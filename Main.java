
import java.util.Scanner;

class Student {
    int rollNo;
    String name;
    double marks;
    int rank;

    Student(int rollNo, String name, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }
}

public class Main {

    static Student[] students = new Student[50];
    static int count = 0;
    static Scanner sc = new Scanner(System.in);

   
    static void addStudent() {
        if (count == 50) {
            System.out.println("Array is full!");
            return;
        }

        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();
        sc.nextLine();

        
        for (int i = 0; i < count; i++) {
            if (students[i].rollNo == roll) {
                System.out.println("Roll number already exists!");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.print("Enter Marks (0-100): ");
        double marks = sc.nextDouble();

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks!");
            return;
        }

        students[count] = new Student(roll, name, marks);
        count++;

        System.out.println("Student added successfully!");
    }

   
    static void sortByMarks() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {

                if (students[j].marks < students[j + 1].marks) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }

    
    static void assignRanks() {
        for (int i = 0; i < count; i++) {

            if (i == 0) {
                students[i].rank = 1;
            } 
            else if (students[i].marks == students[i - 1].marks) {
                students[i].rank = students[i - 1].rank;
            } 
            else {
                students[i].rank = i + 1;
            }
        }
    }

    
    static void displayRankList() {
        if (count == 0) {
            System.out.println("No students available!");
            return;
        }

        sortByMarks();
        assignRanks();

        System.out.println("\n--- STUDENT RANK LIST ---");
        System.out.println("Rank\tRoll No\tName\tMarks");

        for (int i = 0; i < count; i++) {
            System.out.println(students[i].rank + "\t"
                    + students[i].rollNo + "\t"
                    + students[i].name + "\t"
                    + students[i].marks);
        }
    }

  
    static void searchByName() {
        sc.nextLine();

        System.out.print("Enter student name: ");
        String searchName = sc.nextLine().trim();

        sortByMarks();
        assignRanks();

        for (int i = 0; i < count; i++) {
            if (students[i].name.equalsIgnoreCase(searchName)) {
                displayStudent(students[i]);
                return;
            }
        }

        System.out.println("Student not found.");
    }

   
    static void sortByRollNo() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {

                if (students[j].rollNo > students[j + 1].rollNo) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }

  
    static void searchByRollNo() {
        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();

        sortByMarks();
        assignRanks();

        sortByRollNo();

        int low = 0;
        int high = count - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (students[mid].rollNo == roll) {
                displayStudent(students[mid]);
                return;
            } 
            else if (students[mid].rollNo < roll) {
                low = mid + 1;
            } 
            else {
                high = mid - 1;
            }
        }

        System.out.println("Student not found.");
    }

    static void displayStudent(Student s) {
        System.out.println("\nStudent Found!");
        System.out.println("Roll Number: " + s.rollNo);
        System.out.println("Name: " + s.name);
        System.out.println("Marks: " + s.marks);
        System.out.println("Rank: " + s.rank);
    }

   
    static void showTop3() {
        if (count == 0) {
            System.out.println("No students available!");
            return;
        }

        sortByMarks();
        assignRanks();

        System.out.println("\n--- TOP 3 STUDENTS ---");

        for (int i = 0; i < count && i < 3; i++) {
            displayStudent(students[i]);
        }
    }

   
    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== STUDENT RANKING SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Show Rank List");
            System.out.println("3. Search by Roll Number");
            System.out.println("4. Search by Name");
            System.out.println("5. Show Top 3 Students");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;

                case 2:
                    displayRankList();
                    break;

                case 3:
                    searchByRollNo();
                    break;

                case 4:
                    searchByName();
                    break;

                case 5:
                    showTop3();
                    break;

                case 6:
                    System.out.println("Thank you for using Student Ranking System!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }
}
