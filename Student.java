import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
    import java.sql.*;

public class Student {
    private JPanel table_1;
    private JTextField txtName;
    private JTextField txtRollNo;
    private JTextField txtGrade;
    private JButton saveButton;
    private JTable table1;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField txtid;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Student");
        frame.setContentPane(new Student().table_1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    Connection con;
    PreparedStatement pst;

    public void connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/studentdatabase", "root", "");
            System.out.println("Database connected successfully");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void table_load(){
        try
        {
            pst = con.prepareStatement("select * from Student");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public Student() {
        connect();
        table_load();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String studentname,rollno,grade;

                studentname = txtName.getText();
                rollno = txtRollNo.getText();
                grade = txtGrade.getText();

                try {
                    pst = con.prepareStatement("insert into Student(studentname,rollno,grade)values(?,?,?)");
                    pst.setString(1, studentname);
                    pst.setString(2, rollno);
                    pst.setString(3, grade);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Added !");
                    txtName.setText("");
                    txtRollNo.setText("");
                    txtGrade.setText("");
                    txtName.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String studentid = txtid.getText();

                    pst = con.prepareStatement("select studentname,rollno,grade  from student where id = ?");
                    pst.setString(1, studentid);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String studentname = rs.getString(1);
                        String rollno = rs.getString(2);
                        String grade = rs.getString(3);

                        txtName.setText(studentname);
                        txtRollNo.setText(rollno);
                        txtGrade.setText(grade);

                    }
                    else
                    {
                        txtName.setText("");
                        txtRollNo.setText("");
                        txtGrade.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid Student No please enter valid ID");

                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }

            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentid,studentname,rollno,grade;

                studentname = txtName.getText();
                rollno = txtRollNo.getText();
                grade = txtGrade.getText();
                studentid = txtid.getText();


                try {
                    pst = con.prepareStatement("update student set studentname = ?,rollno = ?,grade = ? where id = ?");
                    pst.setString(1, studentname);
                    pst.setString(2, rollno);
                    pst.setString(3, grade);
                    pst.setString(4, studentid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updated !");
                    table_load();
                    txtName.setText("");
                    txtRollNo.setText("");
                    txtGrade.setText("");
                    txtName.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentid;
                studentid = txtid.getText();

                try {
                    pst = con.prepareStatement("delete from student  where id = ?");

                    pst.setString(1, studentid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleted !");
                    table_load();
                    txtName.setText("");
                    txtRollNo.setText("");
                    txtGrade.setText("");
                    txtName.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
            }


        });
    }
}
