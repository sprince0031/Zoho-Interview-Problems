package employeedb;

import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import employeedb.employeetable.Employee;

public class EmployeeTable {

    static String employeeTree(String empTreeQuery, String treeString, ArrayList<Employee> emp, int maxName, int maxDept, int maxDes, int maxReportTo) {
        // System.out.println(treeString);
        ArrayList<Employee> searchResults = search(1, empTreeQuery, 1, emp, false, maxName, maxDept, maxDes, maxReportTo);
        Iterator<Employee> it = searchResults.listIterator();
        // System.out.println(result.RepTo);
        if (searchResults.isEmpty()) {
            return treeString;
        }
        else {
            Employee result = it.next();
            treeString += " -> " + result.RepTo; 
            return employeeTree(result.RepTo, treeString, emp, maxName, maxDept, maxDes, maxReportTo);
        }
    }

    static ArrayList<Employee> search(int fieldType, String searchTerm, int filter, ArrayList<Employee> emp, boolean displayFlag, int maxName, int maxDept, int maxDes, int maxReportTo) {
        Scanner input = new Scanner(System.in);
        int upper = -1;
        int lower = -1;
        BufferedReader stringInput = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Employee> searchResults = new ArrayList<>(emp);
        boolean moreFilters = true;
        boolean presetField = false;
        if (fieldType != -1) {
            presetField = true;
        }
        while (moreFilters) {
            if (fieldType == -1) {
                System.out.print("Enter the field to search with:\n1. Name\n2. Age\n3. Department\n4. Designation\n5. Reports To\nEnter choice: ");
                fieldType = input.nextInt();
            }
            if (filter == -1) {
                if (fieldType == 2) {
                    System.out.print("Select the search filter:\n1. Equal to\n2. Not equal to\n3. Greater than\n4. Less than\n5. Range\nEnter choice: ");
                }

                else {
                    System.out.print("Select the search filter:\n1. Exactly matches\n2. Does not match\n3. Starts with\n4. Ends with\n5. Contains\n6. Does not contain\nEnter choice: ");
                }
                filter = input.nextInt();
            }
            if (searchTerm.equals("")) {
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
                        e.printStackTrace();
                    }
                }
            }
            
            Iterator<Employee> it = searchResults.listIterator();
            if (fieldType == 2) {
                
                while (it.hasNext()) {
                    Employee record = it.next();
                    if(!(record.search(filter, Integer.parseInt(searchTerm), record.Age, upper, lower, maxName, maxDept, maxDes, maxReportTo))) {
                        it.remove();
                    }
                }    
            }
            else {        
                switch(fieldType) {
                    case 1:
                    {
                        while (it.hasNext()) {
                            Employee record = it.next();
                            if(!(record.search(filter, searchTerm, record.Name, maxName, maxDept, maxDes, maxReportTo))) {
                                it.remove();
                            }
                        }
                        break;
                    }

                    case 3:
                    {
                        while (it.hasNext()) {
                            Employee record = it.next();
                            if(!(record.search(filter, searchTerm, record.Dept, maxName, maxDept, maxDes, maxReportTo))) {
                                it.remove();
                            }
                        }
                        break;
                    }

                    case 4:
                    {
                        while (it.hasNext()) {
                            Employee record = it.next();
                            if(!(record.search(filter, searchTerm, record.Des, maxName, maxDept, maxDes, maxReportTo))) {
                                it.remove();
                            }
                        }
                        break;
                    }

                    case 5:
                    {
                        while (it.hasNext()) {
                            Employee record = it.next();
                            if(!(record.search(filter, searchTerm, record.RepTo, maxName, maxDept, maxDes, maxReportTo))) {
                                it.remove();
                            }
                        }
                        break;
                    }

                }

            }
            if (!displayFlag) {
                return searchResults;
            }
            // display results
            tableHeaders(maxName, maxDept, maxDes, maxReportTo);
            for (Employee result: searchResults) {
                result.display(maxName, maxDept, maxDes, maxReportTo);
            }
            printBorder(maxName, maxDept, maxDes, maxReportTo);

            System.out.print("\nDo you want to\n1. Add more filters?\n2. Clear all filters?\n3. Stop search?\nEnter choice: ");
            int choice = input.nextInt();
            if (choice == 1) {
                if (!presetField) {
                    fieldType = -1;
                }
                searchTerm = "";
                filter = -1;
            }
            else if (choice == 2) {
                searchResults.clear();
                searchResults.addAll(emp);
                if (!presetField) {
                    fieldType = -1;
                }
                searchTerm = "";
                filter = -1;
            }
            else {
                moreFilters = false;
            }
        }
        
        return searchResults;
        // input.close();

    }

    static void printBorder(int maxName, int maxDept, int maxDes, int maxReportTo) {
        // Prints the table's horizontal borders
        System.out.print("+----+");
        for (int i = 0; i < maxName + 2; i++) {
            System.out.print("-");
        }
        System.out.print("+-----+");
        for (int i = 0; i < maxDept + 2; i++) {
            System.out.print("-");
        }
        System.out.print("+");
        for (int i = 0; i < maxDes + 2; i++) {
            System.out.print("-");
        }
        System.out.print("+");
        for (int i = 0; i < maxReportTo + 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }

    static void tableHeaders(int maxName, int maxDept, int maxDes, int maxReportTo) {
        int flag = 5;
        if (maxDes % 2 == 0) {
            flag = 6;
        }
        printBorder(maxName, maxDept, maxDes, maxReportTo);

        // Headers
        System.out.print("| ID |");
        for (int i = 0; i < maxName / 2 - 1; i++) {
            System.out.print(" ");
        }
        System.out.print("NAME");
        if ((maxName & 1) == 1) {
            System.out.print(" ");
        }
        for (int i = 0; i < maxName / 2 - 1; i++) {
            System.out.print(" ");
        }
        System.out.print("| AGE |");
        for (int i = 0; i < (maxDept + 2) / 2 - 5; i++) {
            System.out.print(" ");
        }
        System.out.print("DEPARTMENT");
        if ((maxDept & 1) == 1) {
            System.out.print(" ");
        }
        for (int i = 0; i < (maxDept + 2) / 2 - 5; i++) {
            System.out.print(" ");
        }
        System.out.print("|");
        for (int i = 0; i < (maxDes + 2) / 2 - 5; i++) {
            System.out.print(" ");
        }
        System.out.print("DESIGNATION");
        // if (maxDes & 1 == 1) System.out.print(" ");
        for (int i = 0; i < ((maxDes + 2) / 2 - flag); i++) {
            System.out.print(" ");
        }
        System.out.print("|");
        for (int i = 0; i < (maxReportTo + 2) / 2 - 5; i++) {
            System.out.print(" ");
        }
        System.out.print("REPORTS TO");
        if ((maxReportTo & 1) == 1) {
            System.out.print(" ");
        }
        for (int i = 0; i < (maxReportTo + 2) / 2 - 5; i++) {
            System.out.print(" ");
        }
        System.out.println("|");

        printBorder(maxName, maxDept, maxDes, maxReportTo);

    }

    public static void main(String[] args) throws IOException {
        int id = 0, age, maxName = 17, maxDept = 20, maxDes = 28, maxReportTo = 8;
        String name, dept, des, reportTo;
        ArrayList<Employee> emp = new ArrayList<>();
        ArrayList<Employee> searchResults;
        File file = new File("./employee_table.csv");
        if (!file.exists()) {
            file.createNewFile();
        }
        String line;
        String[] data = null;   
        try (BufferedReader fileRead = new BufferedReader(new FileReader("./employee_table.csv"))) {
            while ((line = fileRead.readLine()) != null) {
                data = line.split(",");
//                System.out.println(Arrays.toString(data));
                maxName = Math.max(data[1].length(), maxName);
                maxDept = Math.max(data[3].length(), maxDept);
                maxDes = Math.max(data[4].length(), maxDes);
                maxReportTo = Math.max(data[5].length(), maxReportTo);
                emp.add(new Employee(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), data[3], data[4], data[5]));
            }
            assert data != null;
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
            System.out.println("1. Display\n2. Enter new record\n3. Search\n4. Manager Report\n5. Employee Tree\n6. Summary\n0. Exit");
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
                    for (Employee employee : emp) {
                        employee.display(maxName, maxDept, maxDes, maxReportTo);
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
                    maxName = Math.max(name.length(), maxName);
                    maxDept = Math.max(dept.length(), maxDept);
                    maxDes = Math.max(des.length(), maxDes);
                    maxReportTo = Math.max(reportTo.length(), maxReportTo);
                    emp.add(new Employee(++id, name, age, dept, des, reportTo));
                    String toAppend = id +","+name+","+ age +","+dept+","+des+","+reportTo;
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
                    System.out.println("Final results of search are:");
                    tableHeaders(maxName, maxDept, maxDes, maxReportTo);
                    for (Employee result : searchResults) {
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
                        if (choice.toLowerCase().startsWith("y")) {
                            searchResults = search(5, "", -1, emp, true, maxName, maxDept, maxDes, maxReportTo);
                        }
                    }
                    else {
                        System.out.println("The employee(s) managed by "+manReportQuery+" is/are:");
                    }
                    tableHeaders(maxName, maxDept, maxDes, maxReportTo);
                    for (Employee result : searchResults) {
                        result.display(maxName, maxDept, maxDes, maxReportTo);
                    }
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
                    Iterator<Employee> it = searchResults.listIterator();
                    empTreeQuery = it.next().Name;
                    treeString = employeeTree(empTreeQuery, empTreeQuery, emp, maxName, maxDept, maxDes, maxReportTo);
                    if (!(treeString.isEmpty())) {
                        System.out.println("The employee tree is:\n" + treeString + "\n");
                    }
                    break;
                }

                case 6:
                {
                    System.out.println("\nEmployee Summary:\n");
                    Map<String, Integer> DeptMap = new HashMap<>();
                    Map<String, Integer> DesMap = new HashMap<>();
                    Map<String, Integer> RepToMap = new HashMap<>();
                    for (Employee record: emp) {
                        if (DeptMap.containsKey(record.Dept)) {
                            DeptMap.replace(record.Dept, DeptMap.get(record.Dept), DeptMap.get(record.Dept)+1);
                        }
                        else {
                            DeptMap.put(record.Dept, 1);
                        }
                        if (DesMap.containsKey(record.Des)) {
                            DesMap.replace(record.Des, DesMap.get(record.Des), DesMap.get(record.Des)+1);
                        }
                        else {
                            DesMap.put(record.Des, 1);
                        }
                        if (RepToMap.containsKey(record.RepTo)) {
                            RepToMap.replace(record.RepTo, RepToMap.get(record.RepTo), RepToMap.get(record.RepTo)+1);
                        }
                        else {
                            RepToMap.put(record.RepTo, 1);
                        }
                    }
                    System.out.println("Departments:");
                    System.out.println("------------");
                    int count = 1;
                    for (String key: DeptMap.keySet()) {
                        System.out.print(count++ + ". "+key+": "+DeptMap.get(key)+"\n");
                    }
                    System.out.println("\nDesignations:");
                    System.out.println("-------------");
                    count = 1;
                    for (String key: DesMap.keySet()) {
                        System.out.print(count++ + ". "+key+": "+DesMap.get(key)+"\n");
                    }
                    System.out.println("\nManagers:");
                    System.out.println("---------");
                    count = 1;
                    for (String key: RepToMap.keySet()) {
                        if (!(key.equals("--"))) {
                            System.out.print(count++ + ". "+key+": "+RepToMap.get(key)+"\n");
                        }
                    }
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
