package vn.viettuts.qlsv.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Arrays;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.BorderFactory;
import java.awt.Insets;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import vn.viettuts.qlsv.controller.EmployeeController;
import vn.viettuts.qlsv.dao.EmployeeDao;

import vn.viettuts.qlsv.entity.Employee;

public class EmployeeView extends JFrame implements ActionListener, ListSelectionListener {

    private static final long serialVersionUID = 2L;
    private JButton addEmployeeBtn;
    private JButton editEmployeeBtn;
    private JButton deleteEmployeeBtn;
    private JButton clearBtn;
    private JButton sortEmployeeSalaryBtn;
    private JButton sortEmployeeNameBtn;
    private JButton sortEmployeeIDBtn;
    protected JButton buttonSearch;

    private JScrollPane jScrollPaneEmployeeTable;
    private JScrollPane jScrollPaneAddress;
    private JTable EmployeeTable;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel addressLabel;
    private JLabel salaryLabel;
    private JLabel dpmLabel;
    private JLabel expLabel;
    private JLabel positionLabel;
    private JLabel searchLabel;

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextArea addressTA;
    private JTextField salaryField;
    private JTextField departmentField;
    private JTextField expField;
    private JComboBox positionField;
    private JTextField searchField;

    // định nghĩa các cột của bảng student
    private String[] columnNames = new String[]{
        "ID", "Tên", "Tuổi", "Địa Chỉ", "Chức vụ", "Phòng", "Lương", "Kinh nghiệm"};
    // định nghĩa dữ liệu mặc định của bẳng student là rỗng
    private Object data = new Object[][]{};

    public EmployeeView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        addEmployeeBtn = new JButton("Thêm");
        editEmployeeBtn = new JButton("Sửa");
        deleteEmployeeBtn = new JButton("Xóa");
        clearBtn = new JButton("Clear");
        sortEmployeeSalaryBtn = new JButton("Sắp xếp theo lương");
        sortEmployeeNameBtn = new JButton("Sắp xếp theo tên");
        sortEmployeeIDBtn = new JButton("Sắp xếp theo ID");
        buttonSearch = new JButton("Search");

        // khởi tạo bảng student
        // khởi tạo các label
        idLabel = new JLabel("Id");
        nameLabel = new JLabel("Tên");
        ageLabel = new JLabel("Tuổi");
        addressLabel = new JLabel("Địa chỉ");
        salaryLabel = new JLabel("Lương");
        dpmLabel = new JLabel("Phòng");
        expLabel = new JLabel("Kinh nghiệm");
        positionLabel = new JLabel("Chức vụ");
        searchLabel = new JLabel("Tìm kiếm theo tên, địa chỉ, chức vụ, phòng");

        // khởi tạo các trường nhập dữ liệu cho student
        idField = new JTextField(7);
        idField.setEditable(false);
        nameField = new JTextField(15);
        ageField = new JTextField(6);
        addressTA = new JTextArea();
        addressTA.setColumns(15);
        addressTA.setRows(3);
        jScrollPaneAddress = new JScrollPane();
        jScrollPaneAddress.setViewportView(addressTA);
//        positionField = new JTextField(15);
//        positionField = new JComboBox<>
        List<String> positions = Arrays.asList("Giám đốc", "Trưởng phòng", "Nhân viên");
        positionField = new JComboBox<>(positions.toArray(new String[0]));
        salaryField = new JTextField(10);
        departmentField = new JTextField(15);
        expField = new JTextField(6);
        //search
        searchField = new JTextField(20);
        searchField.setHorizontalAlignment(JTextField.LEFT);
        searchField.setPreferredSize(new Dimension(280, 30));
        searchField.setToolTipText("Tìm kiếm thông tin");
        searchField.setMargin(new Insets(0, 5, 0, 5));
        searchField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        searchField.setCaretColor(Color.BLUE);
        searchField.setForeground(Color.GRAY);
        searchField.setText("Tìm kiếm");
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchField.setText("");
                searchField.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Tìm kiếm...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
        sortEmployeeIDBtn.addActionListener((ActionEvent e) -> {
            EmployeeDao employeeDao = new EmployeeDao();
            List<Employee> sortId;
            sortId = employeeDao.sortAllStudentByID();
            showListEmployee(sortId);
        });
        // khởi tạo bảng student
        jScrollPaneEmployeeTable = new JScrollPane();
        EmployeeTable = new JTable();

        // cài đặt các cột và data cho bảng student
        EmployeeTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneEmployeeTable.setViewportView(EmployeeTable);
        jScrollPaneEmployeeTable.setPreferredSize(new Dimension(800, 380));

        // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý Student
        JPanel panel = new JPanel();
        panel.setSize(800, 420);
        panel.setLayout(layout);
        panel.add(jScrollPaneEmployeeTable);

        panel.add(addEmployeeBtn);
        panel.add(editEmployeeBtn);
        panel.add(deleteEmployeeBtn);
        panel.add(clearBtn);
        panel.add(sortEmployeeSalaryBtn);
        panel.add(sortEmployeeNameBtn);
        panel.add(sortEmployeeIDBtn);

        panel.add(idLabel);
        panel.add(nameLabel);
        panel.add(ageLabel);
        panel.add(addressLabel);
        panel.add(positionLabel);
        panel.add(salaryLabel);
        panel.add(dpmLabel);
        panel.add(expLabel);
        panel.add(searchLabel);

        panel.add(idField);
        panel.add(nameField);
        panel.add(ageField);
        panel.add(jScrollPaneAddress);
        panel.add(positionField);
        panel.add(salaryField);
        panel.add(departmentField);
        panel.add(expField);
        panel.add(searchField);

        // cài đặt vị trí các thành phần trên màn hình login
        layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ageLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ageLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, addressLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addressLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, positionLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, positionLabel, 170, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, dpmLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, dpmLabel, 200, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, salaryLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, salaryLabel, 230, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, expLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, expLabel, 260, SpringLayout.NORTH, panel);
        //search label
        layout.putConstraint(SpringLayout.WEST, searchLabel, 30, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchLabel, 400, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, idField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ageField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ageField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneAddress, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneAddress, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, positionField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, positionField, 170, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, departmentField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, departmentField, 200, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, salaryField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, salaryField, 230, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, expField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, expField, 260, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchField, 60, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchField, 430, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneEmployeeTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneEmployeeTable, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addEmployeeBtn, 30, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addEmployeeBtn, 330, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editEmployeeBtn, 70, SpringLayout.WEST, addEmployeeBtn);
        layout.putConstraint(SpringLayout.NORTH, editEmployeeBtn, 330, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteEmployeeBtn, 60, SpringLayout.WEST, editEmployeeBtn);

        layout.putConstraint(SpringLayout.NORTH, clearBtn, 330, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 60, SpringLayout.WEST, deleteEmployeeBtn);

        layout.putConstraint(SpringLayout.NORTH, deleteEmployeeBtn, 330, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortEmployeeSalaryBtn, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortEmployeeSalaryBtn, 400, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortEmployeeNameBtn, 152, SpringLayout.WEST, sortEmployeeSalaryBtn);
        layout.putConstraint(SpringLayout.NORTH, sortEmployeeNameBtn, 400, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortEmployeeIDBtn, 135, SpringLayout.WEST, sortEmployeeNameBtn);
        layout.putConstraint(SpringLayout.NORTH, sortEmployeeIDBtn, 400, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.NORTH, buttonSearch, 600, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, buttonSearch, 800, SpringLayout.WEST, panel);
        this.add(panel);
        this.pack();
        this.setTitle("Employee Information");
        this.setSize(800, 420);
        this.setVisible(true);
        // disable Edit and Delete buttons
        editEmployeeBtn.setEnabled(false);
        deleteEmployeeBtn.setEnabled(false);
        // enable Add button
        addEmployeeBtn.setEnabled(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * hiển thị list student vào bảng studentTable
     *
     * @param list
     */
    public void showListEmployee(List<Employee> list) {
        int size = list.size();
        // với bảng studentTable có 7 cột, 
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student 
        // số cột: là 7
        Object[][] employee = new Object[size][8];
        for (int i = 0; i < size; i++) {
            employee[i][0] = list.get(i).getId();
            employee[i][1] = list.get(i).getName();
            employee[i][2] = list.get(i).getAge();
            employee[i][3] = list.get(i).getAddress();
            employee[i][4] = list.get(i).getPosition();
            employee[i][5] = list.get(i).getDepartment();
//            employee[i][6] = list.get(i).getSalary();
            employee[i][6] = list.get(i).getFormattedSalary();
            employee[i][7] = list.get(i).getExp();
        }
        EmployeeTable.setModel(new DefaultTableModel(employee, columnNames));
    }

    /**
     * điền thông tin của hàng được chọn từ bảng student vào các trường tương ứng của student.
     */
    public void fillEmployeeFromSelectedRow() {
        // lấy chỉ số của hàng được chọn 
        int row = EmployeeTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(EmployeeTable.getModel().getValueAt(row, 0).toString());
            nameField.setText(EmployeeTable.getModel().getValueAt(row, 1).toString());
            ageField.setText(EmployeeTable.getModel().getValueAt(row, 2).toString());
            addressTA.setText(EmployeeTable.getModel().getValueAt(row, 3).toString());
            String selectedPosition = EmployeeTable.getModel().getValueAt(row, 4).toString();
            positionField.setSelectedItem(selectedPosition);
            departmentField.setText(EmployeeTable.getModel().getValueAt(row, 5).toString());
            // Lấy giá trị tiền lương kiểu String
            String salary = EmployeeTable.getModel().getValueAt(row, 6).toString();
            // Bỏ hết tất cả dấu phẩy ngăn cách ở tiền lương
            salary = salary.replaceAll(",", "");
            // Integer.parseInt(salary) để chuyển dữ liệu tiền lương về kiểu Integer
            // Integer.toString để chuyển dữ liệu tiền lương về kiểu String rồi đưa vào trường salaryField
//            salaryField.setText(Integer.toString(Integer.parseInt(salary)));
salaryField.setText(Integer.toString(Integer.parseInt(salary)));
            expField.setText(EmployeeTable.getModel().getValueAt(row, 7).toString());
            // enable Edit and Delete buttons
            editEmployeeBtn.setEnabled(true);
            deleteEmployeeBtn.setEnabled(true);
            // disable Add button
            addEmployeeBtn.setEnabled(false);
        }
    }

    /**
     * xóa thông tin student
     */
    public void clearEmployeeInfo() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        addressTA.setText("");
//        positionField.setText("");
//        positionField.setSelectedItem("");
        salaryField.setText("");
        departmentField.setText("");
        expField.setText("");
        // disable Edit and Delete buttons
        editEmployeeBtn.setEnabled(false);
        deleteEmployeeBtn.setEnabled(false);
        // enable Add button
        addEmployeeBtn.setEnabled(true);
    }

    /**
     * hiện thị thông tin student
     *
     * @param Employee
     */
    public void showEmployee(Employee Employee) {
        idField.setText("" + Employee.getId());
        nameField.setText(Employee.getName());
        ageField.setText("" + Employee.getAge());
        addressTA.setText(Employee.getAddress());
//        positionField.setSelectedItem("" + Employee.getPosition());
        departmentField.setText("" + Employee.getDepartment());
        salaryField.setText("" + Employee.getSalary());
        expField.setText("" + Employee.getExp());
        // enable Edit and Delete buttons
        editEmployeeBtn.setEnabled(true);
        deleteEmployeeBtn.setEnabled(true);
        // disable Add button
        addEmployeeBtn.setEnabled(false);
    }

    /**
     * lấy thông tin student
     *
     * @return
     */
    public Employee getEmployeeInfo() {
        // validate student
        if (!validateName() || !validateAge() || !validateAddress() || !validateDepartment() || !validateExp() || !validateSalary()) {
            return null;
        }
        try {
            Employee Employee = new Employee();
            if (idField.getText() != null && !"".equals(idField.getText())) {
                Employee.setId(Integer.parseInt(idField.getText()));
            }
            Employee.setName(nameField.getText().trim());
            Employee.setAge(Byte.parseByte(ageField.getText().trim()));
            Employee.setAddress(addressTA.getText().trim());
            String selectedPosition = positionField.getSelectedItem().toString();
            Employee.setPosition(selectedPosition);
//            Employee.setPosition(positionField.getText().trim());
            Employee.setDepartment(departmentField.getText().trim());
            Employee.setSalary(Integer.parseInt(salaryField.getText().trim()));
            Employee.setExp(Byte.parseByte(expField.getText().trim()));
            return Employee;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    /**
     * Trả về giá trị từ trường tìm kiếm (searchField)
     */
    public JTextField getSearchField() {
        return searchField;
    }

    private boolean validateName() {
        String name = nameField.getText();
        if (name == null || "".equals(name.trim())) {
            nameField.requestFocus();
            showMessage("Tên không được trống.");
            return false;
        }
        return true;
    }

//    private boolean validatePosition() {
//        String position = positionField.getText();
//        if (position == null || "".equals(position.trim())) {
//            positionField.requestFocus();
//            showMessage("Chức vụ không được trống.");
//            return false;
//        }
//        return true;
//    }
    private boolean validateAddress() {
        String address = addressTA.getText();
        if (address == null || "".equals(address.trim())) {
            addressTA.requestFocus();
            showMessage("Địa chỉ không được trống.");
            return false;
        }
        return true;
    }

    private boolean validateDepartment() {
        String name = nameField.getText();
        if (name == null || "".equals(name.trim())) {
            nameField.requestFocus();
            showMessage("Phòng không được trống.");
            return false;
        }
        return true;
    }

    private boolean validateAge() {
        try {
            Integer age = Integer.parseInt(ageField.getText().trim());
            if (age < 0 || age > 100) {
                ageField.requestFocus();
                showMessage("Tuổi không hợp lệ, age nên trong khoảng 0 đến 100.");
                return false;
            }
        } catch (Exception e) {
            ageField.requestFocus();
            showMessage("Tuổi không hợp lệ!");
            return false;
        }
        return true;
    }

    private boolean validateSalary() {
        try {
            Integer salary = Integer.parseInt(salaryField.getText().trim());
            if (salary < 0 || salary > 1000000000) {
                salaryField.requestFocus();
                showMessage("Lương không hợp lệ, Lương nên trong khoảng 0 đến 1000000000.");
                return false;
            }
        } catch (Exception e) {
            salaryField.requestFocus();
            showMessage("Lương không hợp lệ!");
            return false;
        }
        return true;
    }

    private boolean validateExp() {
        try {
            Integer exp = Integer.parseInt(expField.getText().trim());
            if (exp < 0 || exp > 60) {
                expField.requestFocus();
                showMessage("Kinh nghiệm không hợp lệ, Kinh nghiệm nên trong khoảng 0 đến 60.");
                return false;
            }
        } catch (Exception e) {
            expField.requestFocus();
            showMessage("Kinh nghiệm không hợp lệ!");
            return false;
        }
        return true;
    }

    /* private boolean validateGPA() {
        try {
            Float gpa = Float.parseFloat(gpaField.getText().trim());
            if (gpa < 0 || gpa > 10) {
                gpaField.requestFocus();
                showMessage("GPA không hợp lệ, gpa nên trong khoảng 0 đến 10.");
                return false;
            }
        } catch (Exception e) {
            gpaField.requestFocus();
            showMessage("GPA không hợp lệ!");
            return false;
        }
        return true;
    }*/
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

    public void addEmployeeListener(ActionListener listener) {
        addEmployeeBtn.addActionListener(listener);
    }

    public void addEditEmployeeListener(ActionListener listener) {
        editEmployeeBtn.addActionListener(listener);
    }

    public void addDeleteEmployeeListener(ActionListener listener) {
        deleteEmployeeBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }

    public void addSortEmployeeSalaryListener(ActionListener listener) {
        sortEmployeeSalaryBtn.addActionListener(listener);
    }

    public void addSortEmployeeNameListener(ActionListener listener) {
        sortEmployeeNameBtn.addActionListener(listener);
    }

    public void addSortEmployeeIDListener(ActionListener listener) {
        sortEmployeeIDBtn.addActionListener(listener);
    }

    public void addListEmployeeSelectionListener(ListSelectionListener listener) {
        EmployeeTable.getSelectionModel().addListSelectionListener(listener);
    }

    public void addSearchListener(ActionListener listener) {
        searchField.addActionListener(listener);
    }
}
