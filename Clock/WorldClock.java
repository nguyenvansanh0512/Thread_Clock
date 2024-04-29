import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class WorldClock {
    private JFrame mainFrame;
    private JTextField timeZoneField;
    private JButton showClockButton;
    private JLabel timeLabel;

    public WorldClock() {
        mainFrame = new JFrame("World Clock");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("World Clock");
        mainFrame.add(titleLabel);

        timeZoneField = new JTextField(10);
        mainFrame.add(timeZoneField);

        showClockButton = new JButton("Show Clock");
        showClockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showClock();
            }
        });
        mainFrame.add(showClockButton);
        
        timeLabel = new JLabel("Time");
        mainFrame.add(timeLabel);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void showClock() {
        String timeZoneId = timeZoneField.getText().trim();
        if (!timeZoneId.isEmpty()) {
            JFrame clockFrame = new JFrame("World Clock - " + timeZoneId);
            clockFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JLabel clockLabel = new JLabel();
            clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
            clockLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            clockFrame.add(clockLabel);

            clockFrame.setSize(300, 100);
            clockFrame.setLocationRelativeTo(null);

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(timeZoneId));
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    sdf.setTimeZone(cal.getTimeZone());
                    String time = sdf.format(cal.getTime());
                    clockLabel.setText(time);
                    timeLabel.setText(time); // Cập nhật label thời gian từ textfield
                }
            }, 0, 1000);

            clockFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Please enter a valid time zone.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WorldClock();
            }
        });
    }
}
