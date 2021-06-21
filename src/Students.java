import java.util.*;

public class Students {

    private Integer studentsTotal = 0;
    private String id;
    private Integer baseId = 0001;
    private String studentName;
    private Integer studentYear;
    private Integer studentBalance;
    private Integer tuitionCost;
    private Integer totalCourses = 0;

    // TODO: 21/6/2021 fix try/catch InputMismatchException when balance input are letters + id++ won't work

    @Override
    public String toString() {
        return String.format(
                "Student { " +
                        "Name: " + studentName +
                        "\nYear: " + studentYear +
                        "\nId: " + id +
                        "\nBalance: " + studentBalance +
                        "\nCourses enrolled: " + totalCourses + " }"
        );
    }

    Scanner keyboard = new Scanner(System.in);

    Students[] students;


    public void mainMenu() {
        System.out.println("##### Welcome to the Student Database Application #####");
        askStudentsTotal();

        {
            students = new Students[studentsTotal];
        }

        for (int i = 0; i < studentsTotal; i++) {
            students[i] = new Students();
            students[i].askStudentName();
            students[i].askStudentYear();
            students[i].generateStudentId();

            students[i].enrollInCourses();
            students[i].checkStudentBalance();
            students[i].calculateTuition();
            students[i].payTuition();
        }

        showStudentStatus();

    }


    private Integer askStudentsTotal() {
        System.out.println("Please state the students total:");
        studentsTotal = keyboard.nextInt();

        return studentsTotal;
    }

    private Integer askStudentYear() {
        System.out.println("Please state student's year.");
        studentYear = keyboard.nextInt();
        return studentYear;
    }

    private String askStudentName() {
        System.out.println("Please state student's name and lastname.");
        studentName = keyboard.nextLine();

        return studentName;

    }

    private String generateStudentId() {
        id = studentYear + "" + baseId;
        System.out.println("Student's ID is: " + id);
        baseId++;
        return id;

    }

    private Integer calculateTuition() {
        tuitionCost = totalCourses * 600;
        return tuitionCost;
    }

    private Integer checkStudentBalance() {

        do {
            try {
                System.out.println("Please state your balance");
                studentBalance = keyboard.nextInt();
            } catch (InputMismatchException exception){
                System.out.println("Please enter numbers only");

            }
        } while (studentBalance == null);

        return studentBalance;
    }

    private Integer payTuition() {
        if (tuitionCost < studentBalance) {
            studentBalance = studentBalance - tuitionCost;
            System.out.println("The tuition cost is covered.");
        } else {
            System.out.println("Insufficient funds to pay tuition.");
        }
        return null;
    }

    private Integer enrollInCourses() {
        int optionMenu;

        do {
            System.out.println("Please choose the student's course/s: ");
            System.out.println("| 1 | History 101");
            System.out.println("| 2 | Mathematics 101");
            System.out.println("| 3 | English 101");
            System.out.println("| 4 | Chemistry 101");
            System.out.println("| 5 | Computer Science 101");
            System.out.println("| 6 | Exit");

            optionMenu = keyboard.nextInt();

            if (totalCourses < 6) {
                switch (optionMenu) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        totalCourses = totalCourses + 1;
                        break;
                    case 6:
                        totalCourses = totalCourses + 0;
                        optionMenu = 6;
                        break;
                    default:
                        System.out.println("Invalid number, please try again.");
                        break;
                }
            } else {
                optionMenu = 6;
            }
        } while (optionMenu != 6);

        return totalCourses;
    }

    private void showStudentStatus() {
        for (int i = 0; i < studentsTotal; i++) {
            System.out.println(students[i].toString());

        }
    }
}

