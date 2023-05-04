package vn.viettuts.qlsv.entity;

import java.io.Serializable;
import java.util.Locale;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int age;
    private String address;
    private String position;//chức vụ
    private int salary;
    private String department;
    private int exp;

    public Employee() {
    }

    public Employee(int id, String name, int age, String address,String position, int salary, String department, int exp) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.position=position;
        this.salary = salary;
        this.department=department;
        this.exp=exp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
     public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public String getFormattedSalary() {
        NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
        return nf.format(salary);
    }
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
    
}
