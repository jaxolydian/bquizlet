import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

/**
 * @author Jackson Philips
 * @since 12/3/2024
 * @apiNote Implement this rewriting into my main StudentOrTeacher code; this is structured so much better. 
 * Everything looks cleaner here and doesn't rely on unnecessary paneling. However, I still want to keep the opening screen.
 * My main challenge in the future will be to implement data features (writing values to text files, sending that to
 * other computers, etc.
 * 
 */
public class App implements ActionListener
{
	JFrame frame = new JFrame();
	
	JLabel title = new JLabel("Login Menu");
	JPanel bigHolder = new JPanel();
	JPanel holder = new JPanel(new GridBagLayout());
	JPanel titlePanel = new JPanel(new GridBagLayout());
	JPanel inputFieldPanel = new JPanel(new GridBagLayout());
	JPanel confirmationPanel = new JPanel(new GridBagLayout());
	JLabel usernameLabel = new JLabel("Username: ");
	JLabel passwordLabel = new JLabel("Password: ");
	JLabel schoolLabel = new JLabel("School: ");
	JTextField usernameField = new JTextField("");
	JPasswordField passwordField = new JPasswordField("");
	JTextField schoolField = new JTextField("");
	JButton login = new JButton("Login");
	GridBagConstraints c = new GridBagConstraints();
	GridBagConstraints c1 = new GridBagConstraints();
	GridBagConstraints c2 = new GridBagConstraints();
	GridBagConstraints c3 = new GridBagConstraints();
	GridBagConstraints c4 = new GridBagConstraints();
	// fonts and ui colors and prettiness
	
	Border border = BorderFactory.createLineBorder(Color.black,1,true);
	Font titleFont = new Font("Franklin Gothic Medium", Font.BOLD, 28);
	Font labelFont = new Font("Arial", Font.PLAIN, 18);
	Font buttonFont = new Font("Arial", Font.ITALIC, 14);
	Color mainColor1 = new Color(164,106,176);
	Color innerPanelColor1 = new Color(245,239,247);
	Color buttonColor = new Color(226, 220, 227);
	
	//users
	

	public App()
	{
		ConstraintEditor.setConstraints(c, 0, 0, 0, 0);
		inputFieldPanel.add(usernameLabel, c);
		ConstraintEditor.setConstraints(c, 1, 0, 0, 0);
		inputFieldPanel.add(usernameField, c);
		ConstraintEditor.setConstraints(c, 0, 1, 0, 0);
		inputFieldPanel.add(passwordLabel, c);
		ConstraintEditor.setConstraints(c, 1, 1, 0, 0);
		inputFieldPanel.add(passwordField, c);
		ConstraintEditor.setConstraints(c, 0, 2, 0, 0);
		inputFieldPanel.add(schoolLabel, c);
		ConstraintEditor.setConstraints(c, 1, 2, 0, 0);
		inputFieldPanel.add(schoolField, c);
		
		ConstraintEditor.setConstraints(c1, 0, 0, 0, 0);
		titlePanel.add(title, c1);
		
		ConstraintEditor.setConstraints(c2, 0, 0, 0, 0);
		confirmationPanel.add(login, c2);
		
		ConstraintEditor.setConstraints(c3, 0, 0, 0, 0);
		holder.add(titlePanel, c3);
		ConstraintEditor.setConstraints(c3, 0, 1, 0, 0);
		holder.add(inputFieldPanel, c3);
		ConstraintEditor.setConstraints(c3, 0, 2, 0, 0);
		holder.add(confirmationPanel, c3);
		
		bigHolder.setLayout(new GridBagLayout());
		bigHolder.add(holder, new GridBagConstraints());
		
		title.setFont(titleFont);
		usernameLabel.setFont(labelFont);
		passwordLabel.setFont(labelFont);
		schoolLabel.setFont(labelFont);
		login.setFont(buttonFont);
		
		
		usernameField.setPreferredSize(new Dimension(200,28));
		passwordField.setPreferredSize(new Dimension(200,28));
		schoolField.setPreferredSize(new Dimension(200,28));
		
		login.setPreferredSize(new Dimension(85, 35));
		
		titlePanel.setPreferredSize(new Dimension(300,100));
		inputFieldPanel.setPreferredSize(new Dimension(300,100));
		confirmationPanel.setPreferredSize(new Dimension(300,100));
		holder.setPreferredSize(new Dimension(500, 350));
		
		
		titlePanel.setBackground(innerPanelColor1);
		inputFieldPanel.setBackground(innerPanelColor1);
		confirmationPanel.setBackground(innerPanelColor1);
		holder.setBackground(innerPanelColor1);
		bigHolder.setBackground(mainColor1);
		holder.setBorder(border);
		
		login.addActionListener(this);
		
		frame.add(bigHolder);
		frame.setTitle("Login Application");
		frame.setSize(650, 550);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void instance()
	{
		new App();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(login)) {

			String usernameData = usernameField.getText();
			String schoolData = schoolField.getText();
		        try {
		            FileWriter writer = new FileWriter("userinfo.txt", true);
		            FileReader reader = new FileReader("userinfo.txt");
		    
		            for (int i = 0; i < writer.toString().length(); i++) {
		            	reader.read();
		            	if	(Files.readAllLines(Paths.get("userinfo.txt")).get(0).equals("user="+usernameData) && 
		            		Files.readAllLines(Paths.get("userinfo.txt")).get(1).equals("pass="+String.valueOf(passwordField.getPassword())) &&
		            		Files.readAllLines(Paths.get("userinfo.txt")).get(2).equals("school="+schoolData)) 
		            	{
		            		System.out.println("Matching user credentials!");
		            		System.out.println("Logging in as "+Files.readAllLines(Paths.get("userinfo.txt")).get(0).substring(5)+"...");
		            		break;
		            	}
		            }

		            writer.close();
                    reader.close();

		        } catch (IOException e21) {
		            e21.printStackTrace();
		        }

		}
		
	}

}