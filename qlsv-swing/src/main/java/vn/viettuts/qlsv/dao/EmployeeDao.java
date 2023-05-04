package vn.viettuts.qlsv.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vn.viettuts.qlsv.entity.Employee;
import vn.viettuts.qlsv.entity.EmployeeXML;
import vn.viettuts.qlsv.utils.FileUtils;

/**
 * StudentDao class
 *
 * @author viettuts.vn
 */
public class EmployeeDao {

    private static final String STUDENT_FILE_NAME = "student.xml";
    private List<Employee> listEmployees;

    public EmployeeDao() {
        this.listEmployees = readListEmployee();
        if (listEmployees == null) {
            listEmployees = new ArrayList<Employee>();
        }
    }

    /**
     * Lưu các đối tượng student vào file student.xml
     *
     * @param students
     */
    public void writeListEmployee(List<Employee> Employees) {
        EmployeeXML EmployeeXML = new EmployeeXML();
        EmployeeXML.setEmoloyees(Employees);
        FileUtils.writeXMLtoFile(STUDENT_FILE_NAME, EmployeeXML);
    }

    /**
     * Đọc các đối tượng student từ file student.xml
     *
     * @return list student
     */
    public List<Employee> readListEmployee() {
        List<Employee> list = new ArrayList<Employee>();
        EmployeeXML EmployeeXML = (EmployeeXML) FileUtils.readXMLFile(
                STUDENT_FILE_NAME, EmployeeXML.class);
        if (EmployeeXML != null) {
            list = EmployeeXML.getEmployees();
        }
        return list;
    }

    /**
     * thêm student vào listStudents và lưu listStudents vào file
     *
     */
    public void add(Employee Employee) {
        int id = 1;
        if (listEmployees != null && listEmployees.size() > 0) {
            id = listEmployees.size() + 1;
        }
        Employee.setId(id);
        listEmployees.add(Employee);
        writeListEmployee(listEmployees);
    }

    /**
     * cập nhật student vào listStudents và lưu listStudents vào file
     *
     */
    public void edit(Employee Employee) {
        int size = listEmployees.size();
        for (int i = 0; i < size; i++) {
            if (listEmployees.get(i).getId() == Employee.getId()) {
                listEmployees.get(i).setName(Employee.getName());
                listEmployees.get(i).setAge(Employee.getAge());
                listEmployees.get(i).setAddress(Employee.getAddress());
                listEmployees.get(i).setPosition(Employee.getPosition());
                listEmployees.get(i).setDepartment(Employee.getDepartment());
                listEmployees.get(i).setSalary(Employee.getSalary());
                listEmployees.get(i).setExp(Employee.getExp());
                writeListEmployee(listEmployees);
                break;
            }
        }
    }

    /**
     * xóa student từ listStudents và lưu listStudents vào file
     *
     * @param
     */
    public boolean delete(Employee Employee) {
        boolean isFound = false;
        int size = listEmployees.size();
        for (int i = 0; i < size; i++) {
            if (listEmployees.get(i).getId() == Employee.getId()) {
                Employee = listEmployees.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listEmployees.remove(Employee);
            writeListEmployee(listEmployees);
            return true;
        }
        return false;
    }

    /**
     * sắp xếp danh sách student theo name theo tứ tự tăng dần
     */
    public void sortByName() {
        Collections.sort(listEmployees, new Comparator<Employee>() {
            public int compare(Employee Employee1, Employee Employee2) {
                return Employee1.getName().compareTo(Employee2.getName());
            }
        });
    }

    /**
     * sắp xếp danh sách student theo luong theo tứ tự tăng dần
     */
    public void sortEmployeeBySalary() {
        Collections.sort(listEmployees, new Comparator<Employee>() {
            public int compare(Employee Employee1, Employee Employee2) {
//                if (Integer.parseInt(Employee1.getSalary()) > Integer.parseInt(Employee2.getSalary())) {
                if (Employee1.getSalary() > Employee2.getSalary()) {
                    return 1;
                }
                return -1;
            }
        });
    }

    public void sortEmployeeByID() {
        Collections.sort(listEmployees, new Comparator<Employee>() {
            public int compare(Employee Employee1, Employee Employee2) {
                if (Employee1.getId() > Employee2.getId()) {
                    return 1;
                }
                return -1;
            }
        });
    }

    public List<Employee> getListEmployees() {
        return listEmployees;
    }

    public void setListEmployees(List<Employee> listEmployees) {
        this.listEmployees = listEmployees;
    }

    public List<Employee> sortAllStudentByID() {
        List<Employee> sortID = new ArrayList<>(listEmployees);
        Collections.sort(sortID, new Comparator<Employee>() {
            public int compare(Employee employee1, Employee employee2) {
                return Integer.compare(employee1.getId(), employee2.getId());
            }
        });
        return sortID;
    }

    //search by name, address, deparment, position
    public List<Employee> search(String keyword) {
        List<Employee> results = new ArrayList<>();
        for (Employee s : listEmployees) {
            if (s.getName().contains(keyword) || s.getAddress().contains(keyword) || s.getDepartment().contains(keyword) || s.getPosition().contains(keyword)) {
                results.add(s);
            }
        }
        return results;
    }

}
