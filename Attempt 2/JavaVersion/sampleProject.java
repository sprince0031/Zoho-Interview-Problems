import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class employee {
    int Id, Age;
    String Name, Dept, Des, RepTo;
    public employee(Integer id, String name, Integer age, String dept, String des, String reportTo) {
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

    boolean search(int filter, String searchTerm, String dataMember, int maxName, int maxDept, int maxDes, int maxReportTo) {
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

    boolean search(int filter, int searchTerm, int dataMember, int upper, int lower, int maxName, int maxDept, int maxDes, int maxReportTo) {
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

    static String employeeTree(String empTreeQuery, String treeString, ArrayList<employee> emp, int maxName, int maxDept, int maxDes, int maxReportTo) {
        // System.out.println(treeString);
        ArrayList<employee> searchResults = search(1, empTreeQuery, 1, emp, false, maxName, maxDept, maxDes, maxReportTo);
        Iterator<employee> it = searchResults.listIterator();
        // System.out.println(result.RepTo);
        if (searchResults.isEmpty())
            return treeString;
        else {
            employee result = it.next();
            treeString += " -> " + result.RepTo; 
            return employeeTree(result.RepTo, treeString, emp, maxName, maxDept, maxDes, maxReportTo);
        }
    }

    static ArrayList<employee> search(int fieldType, String searchTerm, int filter, ArrayList<employee> emp, boolean displayFlag, int maxName, int maxDept, int maxDes, int maxReportTo) {
        Scanner input = new Scanner(System.in);
        int upper = -1;
        int lower = -1;
        BufferedReader stringInput = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<employee> searchResults = new ArrayList<employee>();
        searchResults.addAll(emp);
        boolean moreFilters = true;
        boolean presetField = false;
        if (fieldType != -1)
            presetField = true;
        while (moreFilters == true) {
            if (fieldType == -1) {
                System.out.print("Enter the field to search with:\n1. Name\n2. Age\n3. Department\n4. Designation\n5. Reports To\nEnter choice: ");
                fieldType = input.nextInt();
            }
            if (filter == -1) {
                if (fieldType == 2) 
                    System.out.print("Select the search filter:\n1. Equal to\n2. Not equal to\n3. Greater than\n4. Less than\n5. Range\nEnter choice: ");

                else
                    System.out.print("Select the search filter:\n1. Exactly matches\n2. Does not match\n3. Starts with\n4. Ends with\n5. Contains\n6. Does not contain\nEnter choice: ");
                filter = input.nextInt();
            }
            if (searchTerm == "") {
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
            
            Iterator<employee> it = searchResults.listIterator();
            if (fieldType == 2) {
                
                while (it.hasNext()) {
                    employee record = it.next();
                    if(!(record.search(filter, Integer.parseInt(searchTerm), record.Age, upper, lower, maxName, maxDept, maxDes, maxReportTo)))
                        it.remove();
                }    
            }
            else {        
                switch(fieldType) {
                    case 1:
                    {
                        while (it.hasNext()) {
                            employee record = it.next();
                            if(!(record.search(filter, searchTerm, record.Name, maxName, maxDept, maxDes, maxReportTo)))
                                it.remove();
                        }
                        break;
                    }

                    case 3:
                    {
                        while (it.hasNext()) {
                            employee record = it.next();
                            if(!(record.search(filter, searchTerm, record.Dept, maxName, maxDept, maxDes, maxReportTo)))
                                it.remove();
                        }
                        break;
                    }

                    case 4:
                    {
                        while (it.hasNext()) {
                            employee record = it.next();
                            if(!(record.search(filter, searchTerm, record.Des, maxName, maxDept, maxDes, maxReportTo)))
                                it.remove();
                        }
                        break;
                    }

                    case 5:
                    {
                        while (it.hasNext()) {
                            employee record = it.next();
                            if(!(record.search(filter, searchTerm, record.RepTo, maxName, maxDept, maxDes, maxReportTo)))
                                it.remove();
                        }
                        break;
                    }

                }

            }
            if (displayFlag == false)
                return searchResults;
            // display results
            tableHeaders(maxName, maxDept, maxDes, maxReportTo);
            for (employee result: searchResults)
                result.display(maxName, maxDept, maxDes, maxReportTo);
            printBorder(maxName, maxDept, maxDes, maxReportTo);

            System.out.print("\nDo you want to\n1. Add more filters?\n2. Clear all filters?\n3. Stop search?\nEnter choice: ");
            int choice = input.nextInt();
            if (choice == 1) {
                if (!presetField)
                    fieldType = -1;
                searchTerm = "";
                filter = -1;
            }
            else if (choice == 2) {
                searchResults.clear();
                searchResults.addAll(emp);
                if (!presetField)
                    fieldType = -1;
                searchTerm = "";
                filter = -1;
            }
            else
                moreFilters = false;
        }
        
        return searchResults;
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
        ArrayList<employee> emp = new ArrayList<employee>();
        ArrayList<employee> searchResults = new ArrayList<employee>();
        File file = new File("employee_table.csv");
        if (!file.exists())
            file.createNewFile();
        String line = null;
        String[] data = null;   
        try (BufferedReader fileRead = new BufferedReader(new FileReader("employee_table.csv"))) {
            while ((line = fileRead.readLine()) != null) {
                data = line.split(",");
                System.out.println(data);
                emp.add(new employee(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), data[3], data[4], data[5]));
            }
            id = Integer.parseInt(data[0]);
            // fileRead.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Welcome to the Employee Database Manager!");
        Scanner input = new Scanner(System.in);
        BufferedReader stringInput = new BufferedReader(new InputStreamReader(System.in));
        int ch = -1;
        while (ch != 0) {
            System.out.println("1. Display\n2. Enter new record\n3. Search\n4. Manager Report\n5. Employee Tree\n6. Summary\n7. Perform Employee Type Specific Tasks(TODO)\n0. Exit");
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
                    for (int i = 0; i < emp.size(); i++) {
                        emp.get(i).display(maxName, maxDept, maxDes, maxReportTo);
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
                    emp.add(new employee(++id, name, age, dept, des, reportTo));
                    String toAppend = Integer.toString(id)+","+name+","+Integer.toString(age)+","+dept+","+des+","+reportTo;
                    try (BufferedWriter fileWrite = new BufferedWriter(new FileWriter(file, true))) {
                        System.out.println(toAppend);
                        fileWrite.write(toAppend, 0, toAppend.length());
                        fileWrite.newLine();
                    }
                    
                    System.out.println("Record successfully created!");
                    break;
                }

                case 3: {
                    searchResults = search(-1, "", -1, emp, true, maxName, maxDept, maxDes, maxReportTo);
                    tableHeaders(maxName, maxDept, maxDes, maxReportTo);
                    for (employee result : searchResults) {
                        result.display(maxName, maxDept, maxDes, maxReportTo);
                    }
                    printBorder(maxName, maxDept, maxDes, maxReportTo);
                    break;
                }

                case 4: {
                    String manReportQuery;
                    System.out.print("Enter the name of the manager: ");
                    manReportQuery = stringInput.readLine();
                    searchResults = search(5, manReportQuery, 1, emp, false, maxName, maxDept, maxDes, maxReportTo);
                    if (searchResults.isEmpty()) {
                        System.out.println("Sorry! No results. Do you want to do an advanced search? (Y|n): ");
                        String choice = stringInput.readLine();
                        if (choice.toLowerCase().startsWith("y"))
                            searchResults = search(5, "", -1, emp, true, maxName, maxDept, maxDes, maxReportTo);
                    }
                    else 
                        System.out.println("The employee(s) managed by "+manReportQuery+" is/are:");
                    tableHeaders(maxName, maxDept, maxDes, maxReportTo);
                    for (employee result : searchResults) 
                        result.display(maxName, maxDept, maxDes, maxReportTo);
                    printBorder(maxName, maxDept, maxDes, maxReportTo);
                    break;
                }

                case 5:
                {
                    String empTreeQuery, treeString;
                    System.out.print("Enter the name of the employee: ");
                    empTreeQuery = stringInput.readLine();
                    searchResults = search(1, empTreeQuery, 1, emp, false, maxName, maxDept, maxDes, maxReportTo);
                    if (searchResults.isEmpty()) {
                        System.out.println("Sorry! No such employee in database. Try again!");
                        break;
                    }
                    Iterator<employee> it = searchResults.listIterator();
                    empTreeQuery = it.next().Name;
                    treeString = employeeTree(empTreeQuery, empTreeQuery, emp, maxName, maxDept, maxDes, maxReportTo);
                    if (!(treeString.isEmpty()))
                        System.out.println("The employee tree is:\n" + treeString + "\n");
                    break;
                }

                case 6:
                {
                    System.out.println("\nEmployee Summary:\n");
                    Map<String, Integer> DeptMap = new HashMap<String, Integer>();
                    Map<String, Integer> DesMap = new HashMap<String, Integer>();
                    Map<String, Integer> RepToMap = new HashMap<String, Integer>();
                    for (employee record: emp) {
                        if (DeptMap.containsKey(record.Dept))
                            DeptMap.replace(record.Dept, DeptMap.get(record.Dept), DeptMap.get(record.Dept)+1);
                        else
                            DeptMap.put(record.Dept, new Integer(1));
                        if (DesMap.containsKey(record.Des))
                            DesMap.replace(record.Des, DesMap.get(record.Des), DesMap.get(record.Des)+1);
                        else
                            DesMap.put(record.Des, new Integer(1));
                        if (RepToMap.containsKey(record.RepTo))
                            RepToMap.replace(record.RepTo, RepToMap.get(record.RepTo), RepToMap.get(record.RepTo)+1);
                        else
                            RepToMap.put(record.RepTo, new Integer(1));
                    }
                    System.out.println("Departments:");
                    System.out.println("------------");
                    int count = 1;
                    for (String key: DeptMap.keySet()) 
                        System.out.print(count++ + ". "+key+": "+DeptMap.get(key)+"\n");
                    System.out.println("\nDesignations:");
                    System.out.println("-------------");
                    count = 1;
                    for (String key: DesMap.keySet()) 
                        System.out.print(count++ + ". "+key+": "+DesMap.get(key)+"\n");
                    System.out.println("\nManagers:");
                    System.out.println("---------");
                    count = 1;
                    for (String key: RepToMap.keySet()) 
                        System.out.print(count++ + ". "+key+": "+RepToMap.get(key)+"\n");
                    System.out.print("\n");
                    break;
                }

                default:
                    System.out.println("Please enter a valid option!");
            }
        }
        // input.close();
        // input.close();
        // fileWrite.close();

    }
}
