import java.util.Scanner;

public class StudentManagementSystemCLI {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentManagementSystem sms = new StudentManagementSystem();

    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    isRunning = false;
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nStudent Management System Menu");
        System.out.println("1. Add a new student");
        System.out.println("2. Edit an existing student's information");
        System.out.println("3. Search for a student");
        System.out.println("4. Display all students");
        System.out.println("5. Exit the application");
        System.out.print("Enter your number of the operation you want to perform: ");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void addStudent() {
        System.out.print("Enter the student's name: ");
        String name = scanner.nextLine();
        while (name.trim().isEmpty()) {
            System.out.println("Name cannot be empty. Please try again.");
            System.out.print("Enter the student's name: ");
            name = scanner.nextLine();
        }

        int rollNumber = -1;
        boolean isValidRollNumber = false;
        while (!isValidRollNumber) {
            System.out.print("Enter the student's roll number: ");
            try {
                rollNumber = Integer.parseInt(scanner.nextLine());
                isValidRollNumber = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid roll number format. Please enter a valid integer.");
            }
        }

        System.out.print("Enter the student's grade: ");
        String grade = scanner.nextLine();
        while (grade.trim().isEmpty()) {
            System.out.println("Grade cannot be empty. Please try again.");
            System.out.print("Enter the student's grade in Digit For eg:1,2,3 etc : ");
            grade = scanner.nextLine();
        }

        Student newStudent = new Student(name, rollNumber, grade);
        sms.addStudent(newStudent);

        System.out.println("Student added successfully!");
    }

    private static void editStudent() {
        System.out.print("Enter the roll number of the student whose info you wish to edit: ");
        int rollNumber = Integer.parseInt(scanner.nextLine());

        Student foundStudent = sms.searchStudentByRollNumber(rollNumber);
        if (foundStudent != null) {
            System.out.println("Student found: " + foundStudent.getName());

            System.out.print("Enter the new name (press Enter to skip): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                foundStudent.setName(name);
            }

            System.out.print("Enter the new grade (press Enter to skip): ");
            String grade = scanner.nextLine();
            if (!grade.isEmpty()) {
                foundStudent.setGrade(grade);
            }

            System.out.println("Student information updated successfully!");
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    private static void searchStudent() {
        System.out.print("Enter the roll number of the student you want to search for: ");
        int rollNumber = Integer.parseInt(scanner.nextLine());

        Student foundStudent = sms.searchStudentByRollNumber(rollNumber);
        if (foundStudent != null) {
            System.out.println("Student found: " + foundStudent.getName() +
                    ", Roll Number: " + foundStudent.getRollNumber() +
                    ", Grade: " + foundStudent.getGrade());
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    private static void displayAllStudents() {
        System.out.println("\nAll Students:");
        sms.displayAllStudents();
    }
}
