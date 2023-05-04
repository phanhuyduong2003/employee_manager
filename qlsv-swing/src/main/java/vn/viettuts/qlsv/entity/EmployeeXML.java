package vn.viettuts.qlsv.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "students")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeXML {
    
    private List<Employee> Employee;

    public List<Employee> getEmployees() {
        return Employee;
    }

    public void setEmoloyees(List<Employee> Employee) {
        this.Employee = Employee;
    }
}
