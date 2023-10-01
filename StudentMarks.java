import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentMarks {
    private JButton calculateButton;
    private JFormattedTextField txtStname;
    private JFormattedTextField txtMarks1;
    private JFormattedTextField txtMarks2;
    private JFormattedTextField txtMarks3;
    private JFormattedTextField txtTotal;
    private JFormattedTextField txtAvg;
    private JFormattedTextField txtGrade;
    private JPanel Main;

    public static void main(String[] args) {
        JFrame frame = new JFrame("StudentMarks");
        frame.setContentPane(new StudentMarks().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public StudentMarks() {
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int m1,m2,m3,total;
                double avg;

                m1 = Integer.parseInt(txtMarks1.getText());
                m2 = Integer.parseInt(txtMarks2.getText());
                m3 = Integer.parseInt(txtMarks3.getText());

                total =m1+m2+m3;
                txtTotal.setText(String.valueOf(total));

                avg = total/3;
                txtAvg.setText(String.valueOf(avg));

                if (avg > 50) {
                    txtGrade.setText("Pass");
                }else {
                    txtGrade.setText("Fail");
                }

            }
        });
    }
}
