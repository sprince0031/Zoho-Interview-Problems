import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class employee {
    int Id, Age;
    String Name, Dept, Des, RepTo;
    public employee(int id, String name, int age, String dept, String des, String reportTo) {
        Id = id;
        Name = name;
        Age = age;
        Dept = dept;
        Des = des;
        RepTo = reportTo;
    }

    void display(int maxName, int maxDept, int maxDes, int maxReportTo) {
        System.out.print("| " + Id);
        if (Id < 10) System.out.print("  | " + Name) ;
        else System.out.print( " | " + Name) ;
        for (int i = 0; i < maxName - Name.length() + 1; i++)
            System.out.print(" ");
        System.out.print( "| " + Age + "  | " + Dept);
        for (int i = 0; i < maxDept - Dept.length() + 1; i++)
            System.out.print(" ");
        System.out.print ("| " + Des);
        for (int i = 0; i < maxDes - Des.length() + 1; i++)
            System.out.print( " ");
        System.out.print("| " + RepTo);
        for (int i = 0; i < maxReportTo - RepTo.length() + 1; i++)
            System.out.print(" ");
        System.out.println("|");

    }

}

class dev extends employee {

    public dev(int id, String name, int age, String dept, String des, String reportTo) {
        super(id, name, age, dept, des, reportTo);
    }



}

class tester extends employee {

    int lifeTimeNoOfBugsFound = 0;
    String CurrentProject, CurrentTeam, LatestTestType;
    
    public tester(int id, String name, int age, String dept, String des, String reportTo, String latestTestType) {
        super(id, name, age, dept, des, reportTo);
        LatestTestType = latestTestType;
    }

    // void latestTestDetails()

}

public class sampleProject {

    static void searchString(String searchTerm) {

    }

    static void searchNums(int searchTerm) {

    }

    static void search(int fieldType, String searchTerm) {
        if (fieldType == -1) {
            System.out.print(
                    "Enter the field to search with:\n1. Name\n2. Age\n3. Department\n4. Designation\n5. Reports To\nEnter choice: ");
            Scanner input = new Scanner(System.in);
            fieldType = input.nextInt();
            input.close();
        }
        if (fieldType != 2)
            searchString(searchTerm);

        else {
            searchNums(Integer.parseInt(searchTerm));
        }

    }

    static void printBorder(int maxName, int maxDept, int maxDes, int maxReportTo) {
        // Prints the table's horizontal borders
        System.out.print("+----+");
        for (int i = 0; i < maxName + 2; i++)
            System.out.print("-");
        System.out.print("+-----+");
        for (int i = 0; i < maxDept + 2; i++)
            System.out.print("-");
        System.out.print("+");
        for (int i = 0; i < maxDes + 2; i++)
            System.out.print("-");
        System.out.print("+");
        for (int i = 0; i < maxReportTo + 2; i++)
            System.out.print("-");
        System.out.println("+");
    }

    static void tableHeaders(int maxName, int maxDept, int maxDes, int maxReportTo) {
        int flag = 5;
        if (maxDes % 2 == 0)
            flag = 6;
        printBorder(maxName, maxDept, maxDes, maxReportTo);

        // Headers
        System.out.print("| ID |");
        for (int i = 0; i < maxName / 2 - 1; i++)
            System.out.print(" ");
        System.out.print("NAME");
        if ((maxName & 1) == 1)
            System.out.print(" ");
        for (int i = 0; i < maxName / 2 - 1; i++)
            System.out.print(" ");
        System.out.print("| AGE |");
        for (int i = 0; i < (maxDept + 2) / 2 - 5; i++)
            System.out.print(" ");
        System.out.print("DEPARTMENT");
        if ((maxDept & 1) == 1)
            System.out.print(" ");
        for (int i = 0; i < (maxDept + 2) / 2 - 5; i++)
            System.out.print(" ");
        System.out.print("|");
        for (int i = 0; i < (maxDes + 2) / 2 - 5; i++)
            System.out.print(" ");
        System.out.print("DESIGNATION");
        // if (maxDes & 1 == 1) System.out.print(" ");
        for (int i = 0; i < ((maxDes + 2) / 2 - flag); i++)
            System.out.print(" ");
        System.out.print("|");
        for (int i = 0; i < (maxReportTo + 2) / 2 - 5; i++)
            System.out.print(" ");
        System.out.print("REPORTS TO");
        if ((maxReportTo & 1) == 1)
            System.out.print(" ");
        for (int i = 0; i < (maxReportTo + 2) / 2 - 5; i++)
            System.out.print(" ");
        System.out.println("|");

        printBorder(maxName, maxDept, maxDes, maxReportTo);

    }

    public static void main(String[] args) throws IOException {
        int id = 0, age, maxName = 17, maxDept = 20, maxDes = 28, maxReportTo = 8;
        String name, dept, des, reportTo;
        ArrayList<employee> e = new ArrayList<employee>();
        e.add(new employee(++id, "Pricilla Wilson", 45, "HR", "HR Manager", "John Doe"));
        e.add(new employee(++id, "Manoj. M", 39, "Development", "Software Development Manager", "Jan Doe"));
        e.add(new employee(++id, "Kiruthiga. R", 41, "QA", "QA Manager", "John Doe"));
        e.add(new employee(++id, "Siddharth Prince", 35, "Development", "Lead Developer", "Manoj. M"));
        System.out.println("Welcome to the Employee Database Manager!");
        Scanner input = new Scanner(System.in);
        BufferedReader stringInput = new BufferedReader(new InputStreamReader(System.in));
        int ch = -1;
        while (ch != 0) {
            System.out.println(
                    "1. Display\n2. Enter new record\n3. Search\n4. Manager Report\n5. Employee Tree\n6. Summary\n7. Perform Employee Type Specific Task\n0. Exit");
            System.out.print("Enter choice: ");
            ch = input.nextInt();
            switch (ch) {
            case 0: {
                System.out.println("Exiting now...");
                System.exit(0);
                break;
            }

            case 1: {
                tableHeaders(maxName, maxDept, maxDes, maxReportTo);
                for (int i = 0; i < e.size(); i++) {
                    e.get(i).display(maxName, maxDept, maxDes, maxReportTo);
                    printBorder(maxName, maxDept, maxDes, maxReportTo);
                }
                break;
            }

            case 2: {
                System.out.print("Enter employee's name: ");
                name = stringInput.readLine();
                System.out.print("Enter employee's age: ");
                age = input.nextInt();
                System.out.print("Enter employee's department: ");
                dept = stringInput.readLine();
                System.out.print("Enter employee's designation: ");
                des = stringInput.readLine();
                System.out.print("Enter employee's manager to report to: ");
                reportTo = stringInput.readLine();
                maxName = (name.length() > maxName) ? name.length() : maxName;
                maxDept = (dept.length() > maxDept) ? dept.length() : maxDept;
                maxDes = (des.length() > maxDes) ? des.length() : maxDes;
                maxReportTo = (reportTo.length() > maxReportTo) ? reportTo.length() : maxReportTo;
                e.add(new employee(++id, name, age, dept, des, reportTo));
                System.out.print("Record successfully created!");
                break;
            }

            case 3: {
                search(-1, "");
                }

                case 7:
                {
                    
                    System.out.println("Enter employee's department: ");
                    // String empDept = stringInput.readLine();
                    // if (empDept == "Development") {
                        
                    // }
                }

                default:
                    System.out.println("Please enter a valid option!");
            }
        }
        input.close();

    }
}
