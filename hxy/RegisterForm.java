package hxy;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class RegisterForm extends JFrame {
	private static final long serialVersionUID = 1L;

	public RegisterForm(String username) {
		setVisible(true);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("InformationRecord");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 16));
		lblNewLabel.setBounds(266, 36, 180, 30);
		getContentPane().add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("ËÎÌו", Font.PLAIN, 16));
		lblName.setBounds(57, 97, 75, 24);
		getContentPane().add(lblName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 489);
	}

}
