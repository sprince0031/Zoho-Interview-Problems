#include <iostream>
#include <string>
#include <vector>
using namespace std;

class employee {
    private:
	    int Id, Age;
	    string Name, Dept, Des, ReportTo;

    public:
        employee(int id, string name, int age, string dept, string des, string reportTo) {
            Id = id;
            Name = name;
            Age = age;
            Dept = dept;
            Des = des;
            ReportTo = reportTo;
            // cout << Id << ", " << Name << ", " << Age << ", " << Dept << ", " << Des << ", " << ReportTo << endl;
        }

        void display(int maxName, int maxDept, int maxDes, int maxReportTo) {
            cout << "| " << Id;
            if (Id < 10) cout << "  | " << Name ;
            else cout << " | " << Name ;
            for (int i = 0; i < maxName - Name.length() + 1; i++)
                cout << " ";
            cout << "| " << Age << "  | " << Dept;
            for (int i = 0; i < maxDept - Dept.length() + 1; i++)
                cout << " ";
            cout << "| " << Des;
            for (int i = 0; i < maxDes - Des.length() + 1; i++)
                cout << " ";
            cout << "| " << ReportTo;
            for (int i = 0; i < maxReportTo - ReportTo.length() + 1; i++)
                cout << " ";
            cout << "|" << endl;
            
        }
};

void printBorder(int maxName, int maxDept, int maxDes, int maxReportTo) {
    // Prints the table's horizontal borders
    cout << "+----+";
    for (int i = 0; i < maxName + 2; i++)
        cout << "-";
    cout << "+-----+";
    for (int i = 0; i < maxDept + 2; i++)
        cout << "-";
    cout << "+";
    for (int i = 0; i < maxDes + 2; i++)
        cout << "-";
    cout << "+";
    for (int i = 0; i < maxReportTo + 2; i++)
        cout << "-";
    cout << "+" << endl;
}

void tableHeaders(int maxName, int maxDept, int maxDes, int maxReportTo) {
    int flag = 5;
    if (maxDes % 2 == 0)
        flag = 6;
    printBorder(maxName, maxDept, maxDes, maxReportTo);

    // Headers
    cout << "| ID |";
    for (int i = 0; i < maxName/2 - 1; i++)
        cout << " ";
    cout << "NAME";
    if (maxName & 1 == 1) cout << " ";
    for (int i = 0; i < maxName/2 - 1; i++)
        cout << " ";
    cout << "| AGE |";
    for (int i = 0; i < (maxDept+2)/2 - 5; i++)
        cout << " ";
    cout << "DEPARTMENT";
    if (maxDept & 1 == 1) cout << " ";
    for (int i = 0; i < (maxDept+2)/2 - 5; i++)
        cout << " ";
    cout << "|";
    for (int i = 0; i < (maxDes+2)/2 - 5; i++)
        cout << " ";
    cout << "DESIGNATION";
    // if (maxDes & 1 == 1) cout << " ";
    for (int i = 0; i < ((maxDes+2)/2 - flag); i++)
        cout << " ";
    cout << "|";
    for (int i = 0; i < (maxReportTo+2)/2 - 5; i++)
        cout << " ";
    cout << "REPORTS TO";
    if (maxReportTo & 1 == 1) cout << " ";
    for (int i = 0; i < (maxReportTo+2)/2 - 5; i++)
        cout << " ";
    cout << "|" << endl;

   printBorder(maxName, maxDept, maxDes, maxReportTo);
    

}

int main() {
	vector<employee> e;
    int id = 0, age, maxName = 31, maxDept = 20, maxDes = 28, maxReportTo = 31;
	string name, dept, des, reportTo;
    // Some hard-coded data so that I don't have to keep entering values at the command line every time I need to test.
    // TODO: Add functionality to read and write to file containing data.
    e.push_back(employee(++id, "Sundar Pichai", 42, "Management", "CEO", "--"));
    e.push_back(employee(++id, "Pricilla Cathrin Trichet Wilson", 45, "HR", "HR Manager", "Sundar Pichai"));
    e.push_back(employee(++id, "Manoj. M", 39, "Software Development", "Software Development Manager", "Sundar Pichai"));
    e.push_back(employee(++id, "Kiruthiga. R", 41, "QA", "QA Manager", "Sundar Pichai"));
    e.push_back(employee(++id, "Siddharth Prince", 35, "Software Development", "Lead Developer", "Manoj. M"));
    e.push_back(employee(++id, "Daniel Raj", 32, "QA", "Testing Lead", "Kiruthiga. R"));
    e.push_back(employee(++id, "Keerthana. T", 29, "HR", "HR Associate", "Pricilla Cathrin Trichet Wilson"));
    e.push_back(employee(++id, "Sharad Kumar Singh", 25, "Software Development", "Associate Developer", "Siddharth Prince"));
    int ch = 2;
    cout << "Welcome to the Employee Database Manager!\n\n";
    while(ch != 0) {
        cout << "1. Display\n2. Enter new record\n3. Search\n4. Manager Report\n5. Employee Tree\n6. Summary\n0. Exit" << endl;
        cout << "Enter choice: ";
        cin >> ch;
        switch(ch) {
            case 0:
            {
                cout << "Exiting Now..." << endl;
                return 0;
            }

            case 1:
            {
                tableHeaders(maxName, maxDept, maxDes, maxReportTo);
                for (int i = 0; i < e.size(); i++)
                {
                    e[i].display(maxName, maxDept, maxDes, maxReportTo);
                    printBorder(maxName, maxDept, maxDes, maxReportTo);
                }
                break;
                
            }

            case 2:
            {   
                cin.clear();
                cin.sync();

                cout << "Enter employee's name: ";
                cin.ignore();
                getline(cin, name);
                cout << "Enter employee's age: ";
                cin >> age;
                cout << "Enter employee's department: ";
                cin.ignore();
                getline(cin, dept);
                cout << "Enter employee's designation: ";
                getline(cin, des);
                cout << "Enter employee's manager to report to: ";
                getline(cin, reportTo);
                maxName = (name.length() > maxName)?name.length():maxName;
                maxDept = (dept.length() > maxDept)?dept.length():maxDept;
                maxDes = (des.length() > maxDes)?des.length():maxDes;
                maxReportTo = (reportTo.length() > maxReportTo)?reportTo.length():maxReportTo;
                // cout << maxName << ", " << maxDept << ", " << maxDes << ", " << maxReportTo << endl;
                e.push_back(employee(++id, name, age, dept, des, reportTo));
                cout << "Record successfully created!" << endl;
                break;
            }

            case 3:
            {
                int categoryCh;
                cout << "Enter category to search by (1. Name, 2. Age, 3. Department, 4. Designation, 5. Reporting to): ";
                cin >> categoryCh;

            }

            default:
                cout << "Please enter a valid option." << endl;

        }
    }
	
	return 0;
}