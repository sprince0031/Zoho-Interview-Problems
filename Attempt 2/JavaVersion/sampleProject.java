import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

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

public class sampleProject {
    public static void main(String[] args) throws IOException {
        int id = 0, age, maxName = 17, maxDept = 20, maxDes = 28, maxReportTo = 8;
        String name, dept, des, reportTo;
        ArrayList<employee> e = new ArrayList<employee>();
        e.add(new employee(++id, "Pricilla Wilson", 45, "HR", "HR Manager", "John Doe"));
        e.add(new employee(++id, "Manoj. M", 39, "Software Development", "Software Development Manager", "Jan Doe"));
        e.add(new employee(++id, "Kiruthiga. R", 41, "QA", "QA Manager", "John Doe"));
        e.add(new employee(++id, "Siddharth Prince", 35, "Software Development", "Lead Developer", "Manoj. M"));
        System.out.println("Welcome to the Employee Database Manager!");
        Scanner input = new Scanner(System.in);
        int ch = -1;
        while (ch!=0) {
            System.out.println("1. Display\n2. Enter new record\n3. Search\n4. Manager Report\n5. Employee Tree\n6. Summary\n0. Exit");
            System.out.print( "Enter choice: ");
            ch = input.nextInt();
            switch(ch) {
                case 0:
                {
                    System.out.println("Exiting now...");
                    System.exit(0);;
                }

                case 1:
                {
                    for (int i = 0; i < e.size(); i++) {
                        e.get(i).display(maxName, maxDept, maxDes, maxReportTo);
                    }
                }

                default:
                    System.out.println("Please enter a valid option!");
            }
        }
        input.close();

    }
}
