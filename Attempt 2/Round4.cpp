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
            cout << Id << ", " << Name << ", " << Age << ", " << Dept << ", " << Des << ", " << ReportTo << endl;
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
    int id = 0, age, maxName = 10, maxDept = 10, maxDes = 11, maxReportTo = 10;
	string name, dept, des, reportTo; 
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

        }
    }
	
	return 0;
}