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

    void search(int filter, String searchTerm, String dataMember, int maxName, int maxDept, int maxDes, int maxReportTo) {
        dataMember = dataMember.toLowerCase();
        switch(filter) {
            case 1:
                if(dataMember.equalsIgnoreCase(searchTerm))
                    display(maxName, maxDept, maxDes, maxReportTo);
                break;
            case 2:
                if(!(dataMember.equalsIgnoreCase(searchTerm)))
                    display(maxName, maxDept, maxDes, maxReportTo);
                break;
            case 3:
                if(dataMember.startsWith(searchTerm.toLowerCase()))
                    display(maxName, maxDept, maxDes, maxReportTo);
                break;
            case 4:
                if(dataMember.endsWith(searchTerm.toLowerCase()))
                    display(maxName, maxDept, maxDes, maxReportTo);
                break;
            case 5:
                if(dataMember.contains(searchTerm.toLowerCase()))
                    display(maxName, maxDept, maxDes, maxReportTo);
                break;
            case 6:
                if(!(dataMember.contains(searchTerm.toLowerCase())))
                    display(maxName, maxDept, maxDes, maxReportTo);
                break;
        }
    }

    void search(int filter, int searchTerm, int dataMember, int upper, int lower, int maxName, int maxDept, int maxDes, int maxReportTo) {
        switch(filter) {
            case 1:
                if(dataMember == searchTerm)
                    display(maxName, maxDept, maxDes, maxReportTo);
                break;
            case 2:
                if(dataMember != searchTerm)
                    display(maxName, maxDept, maxDes, maxReportTo);
                break;
            case 3:
                if(dataMember > searchTerm)
                    display(maxName, maxDept, maxDes, maxReportTo);
                break;
            case 4:
                if(dataMember < searchTerm)
                    display(maxName, maxDept, maxDes, maxReportTo);
                break;
            case 5:
                {
                    // Scanner input = new Scanner(System.in);
                    // input.close();
                    if(dataMember <= upper && dataMember >= lower)
                        display(maxName, maxDept, maxDes, maxReportTo);
                }
                break;
        }
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

    static void search(int fieldType, String searchTerm, int filter, ArrayList<employee> emp, int maxName, int maxDept, int maxDes, int maxReportTo) {
        Scanner input = new Scanner(System.in);
        int upper = -1;
        int lower = -1;
        BufferedReader stringInput = new BufferedReader(new InputStreamReader(System.in));
        if (fieldType == -1) {
            System.out.print("Enter the field to search with:\n1. Name\n2. Age\n3. Department\n4. Designation\n5. Reports To\nEnter choice: ");
            fieldType = input.nextInt();
            if (fieldType == 2) 
                System.out.print("Select the search filter:\n1. Equal to\n2. Not equal to\n3. Greater than\n4. Less than\n5. Range\nEnter choice: ");

            else
                System.out.print("Select the search filter:\n1. Exactly matches\n2. Does not match\n3. Starts with\n4. Ends with\n5. Contains\n6. Does not contain\nEnter choice: ");
            filter = input.nextInt();
            if (fieldType == 2 && filter == 5) {
                System.out.print("Enter the upper value of the range: ");
                upper = input.nextInt();
                System.out.print("Enter the lower value of the range: ");
                lower = input.nextInt();
                searchTerm = "-1";
            }
            else {
                System.out.print("Enter search term: ");
                try {
                    searchTerm = stringInput.readLine();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        tableHeaders(maxName, maxDept, maxDes, maxReportTo);
        if (fieldType == 2) {
            
            for (employee record : emp) {
                record.search(filter, Integer.parseInt(searchTerm), record.Age, upper, lower, maxName, maxDept, maxDes, maxReportTo);
            }    
        }
        else {        
            switch(fieldType) {
                case 1:
                {
                    for (employee record : emp) {
                        record.search(filter, searchTerm, record.Name, maxName, maxDept, maxDes, maxReportTo);
                    }
                    break;
                }

                case 3:
                {
                    for (employee record : emp) {
                        record.search(filter, searchTerm, record.Dept, maxName, maxDept, maxDes, maxReportTo);
                    }
                    break;
                }

                case 4:
                {
                    for (employee record : emp) {
                        record.search(filter, searchTerm, record.Des, maxName, maxDept, maxDes, maxReportTo);
                    }
                    break;
                }

                case 5:
                {
                    for (employee record : emp) {
                        record.search(filter, searchTerm, record.RepTo, maxName, maxDept, maxDes, maxReportTo);
                    }
                    break;
                }

            }

        }
        // input.close();

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
            System.out.println("1. Display\n2. Enter new record\n3. Search\n4. Manager Report (TODO)\n5. Employee Tree(TODO)\n6. Summary(TODO)\n7. Perform Employee Type Specific Task(TODO)\n0. Exit");
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
                search(-1, "", -1, e, maxName, maxDept, maxDes, maxReportTo);
                printBorder(maxName, maxDept, maxDes, maxReportTo);
                break;
                }

                // case 7:
                // {
                    
                //     System.out.println("Enter employee's department: ");
                //     // String empDept = stringInput.readLine();
                //     // if (empDept == "Development") {
                        
                //     // }
                //     break;
                // }

                default:
                    System.out.println("Please enter a valid option!");
            }
        }
        // input.close();

    }
}
