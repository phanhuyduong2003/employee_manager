package vn.viettuts.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTextField;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vn.viettuts.qlsv.dao.EmployeeDao;
import vn.viettuts.qlsv.entity.Employee;
import vn.viettuts.qlsv.view.EmployeeView;

public class EmployeeController {

    private EmployeeDao EmployeeDao;
    private EmployeeView EmployeeView;

    public EmployeeController(EmployeeView view) {
        this.EmployeeView = view;
        EmployeeDao = new EmployeeDao();

        view.addEmployeeListener(new AddEmployeeListener());
        view.addEditEmployeeListener(new EditEmployeeListener());
        view.addDeleteEmployeeListener(new DeleteEmployeeListener());
        view.addClearListener(new ClearStudentListener());
        view.addSortEmployeeSalaryListener(new SortEmployeeSalaryListener());
        view.addSortEmployeeNameListener(new SortEmployeeNameListener());
        view.addSortEmployeeIDListener(new SortEmployeeIDListener());
        view.addListEmployeeSelectionListener(new ListStudentSelectionListener());
        SearchEmployeeListener searchListener = new SearchEmployeeListener(EmployeeView.getSearchField());
        view.addSearchListener(searchListener);
    }

    public void showEmployeeView() {
        List<Employee> EmployeeList = EmployeeDao.getListEmployees();
        EmployeeView.setVisible(true);
        EmployeeView.showListEmployee(EmployeeList);
    }

    /**
     * Lớp AddStudentListener chứa cài đặt cho sự kiện click button "Add"
     *
     * @author viettuts.vn
     */
    class AddEmployeeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Employee Employee = EmployeeView.getEmployeeInfo();
            if (Employee != null) {
                EmployeeDao.add(Employee);
                EmployeeView.showEmployee(Employee);
                EmployeeView.showListEmployee(EmployeeDao.getListEmployees());
                EmployeeView.showMessage("Thêm thành công!");
            }
        }
    }

    /**
     * Lớp EditStudentListener chứa cài đặt cho sự kiện click button "Edit"
     *
     * @author viettuts.vn
     */
    class EditEmployeeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Employee Employee = EmployeeView.getEmployeeInfo();
            if (Employee != null) {
                EmployeeDao.edit(Employee);
                EmployeeView.showEmployee(Employee);
                EmployeeView.showListEmployee(EmployeeDao.getListEmployees());
                EmployeeView.showMessage("Cập nhật thành công!");
            }
        }
    }

    /**
     * Lớp DeleteStudentListener chứa cài đặt cho sự kiện click button "Delete"
     *
     * @author viettuts.vn
     */
    class DeleteEmployeeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Employee Employee = EmployeeView.getEmployeeInfo();
            if (Employee != null) {
                EmployeeDao.delete(Employee);
                EmployeeView.clearEmployeeInfo();
                EmployeeView.showListEmployee(EmployeeDao.getListEmployees());
                EmployeeView.showMessage("Xóa thành công!");
            }
        }
    }

    /**
     * Lớp ClearStudentListener chứa cài đặt cho sự kiện click button "Clear"
     *
     * @author viettuts.vn
     */
    class ClearStudentListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            EmployeeView.clearEmployeeInfo();
        }
    }

    /**
     * Lớp SortStudentGPAListener chứa cài đặt cho sự kiện click button "Sort By GPA"
     *
     * @author viettuts.vn
     */
    class SortEmployeeSalaryListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            EmployeeDao.sortEmployeeBySalary();
            EmployeeView.showListEmployee(EmployeeDao.getListEmployees());
        }
    }

    /**
     * Lớp SortStudentGPAListener chứa cài đặt cho sự kiện click button "Sort By Name"
     *
     * @author viettuts.vn
     */
    class SortEmployeeNameListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            EmployeeDao.sortByName();
            EmployeeView.showListEmployee(EmployeeDao.getListEmployees());
        }
    }

    class SortEmployeeIDListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            EmployeeDao.sortEmployeeByID();
            EmployeeView.showListEmployee(EmployeeDao.getListEmployees());
        }
    }

    /**
     * Lớp ListStudentSelectionListener chứa cài đặt cho sự kiện chọn student trong bảng student
     *
     * @author viettuts.vn
     */
    class ListStudentSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            EmployeeView.fillEmployeeFromSelectedRow();
        }
    }
    /**
     * Lớp SearchEmployeeListener chứa cài đặt cho sự kiện enter ở trường dữ liệu "searchField"
     *
    */
    class SearchEmployeeListener implements ActionListener {

        JTextField searchField = EmployeeView.getSearchField();

        public SearchEmployeeListener() {
        }

        public SearchEmployeeListener(JTextField searchField) {
            this.searchField = searchField;
        }

        public void actionPerformed(ActionEvent e) {
            String keyword = searchField.getText();
            List<Employee> result = EmployeeDao.search(keyword);
            EmployeeView.showListEmployee(result);
        }
    }
}
