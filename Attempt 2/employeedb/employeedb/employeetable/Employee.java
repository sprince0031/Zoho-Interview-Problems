package employeedb.employeetable;

public class Employee {
    public int Id, Age;
    public String Name, Dept, Des, RepTo;
    public Employee(Integer id, String name, Integer age, String dept, String des, String reportTo) {
        Id = id;
        Name = name;
        Age = age;
        Dept = dept;
        Des = des;
        RepTo = reportTo;
    }

    public void display(int maxName, int maxDept, int maxDes, int maxReportTo) {
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

    public boolean search(int filter, String searchTerm, String dataMember, int maxName, int maxDept, int maxDes, int maxReportTo) {
        dataMember = dataMember.toLowerCase();
        switch(filter) {
            case 1:
                return dataMember.equalsIgnoreCase(searchTerm);
            case 2:
                return !(dataMember.equalsIgnoreCase(searchTerm));
            case 3:
                return dataMember.startsWith(searchTerm.toLowerCase());
            case 4:
                return dataMember.endsWith(searchTerm.toLowerCase());
            case 5:
                return dataMember.contains(searchTerm.toLowerCase());
            case 6:
                return !(dataMember.contains(searchTerm.toLowerCase()));
        }
        return false;
    }

    public boolean search(int filter, int searchTerm, int dataMember, int upper, int lower, int maxName, int maxDept, int maxDes, int maxReportTo) {
        switch(filter) {
            case 1:
                return dataMember == searchTerm;

            case 2:
                return dataMember != searchTerm;

            case 3:
                return dataMember > searchTerm;

            case 4:
                return dataMember < searchTerm;

            case 5:
                return dataMember <= upper && dataMember >= lower;
        }
        return false;
    }

}