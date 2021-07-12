import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.SwingConstants;

public class Gui {

	wiki obj = new wiki();
	Trie trie;
	void init() {
		try {
			List<String> words = obj.getTerms();
			trie = new Trie(words);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
	private JFrame frame;
	private JTextField textField;
	
    List<String> suggestions;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JTextArea textArea_1;
    private JScrollPane scrollPane_1;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
		init();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Open Sans", Font.PLAIN, 14));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String text = textField.getText().toLowerCase();
				textArea_1.setText("");
				if (!text.equals("")) {
					suggestions = trie.suggest(text);
					for (String s : suggestions)
						textArea_1.setText(textArea_1.getText()+s+"\n");
				}
			}
		});
		
		textField.setBounds(10, 127, 414, 36);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Go");
		btnNewButton.setFont(new Font("Open Sans", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
				try {
					textArea.setText(obj.getInfo(text));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(179, 181, 74, 36);
		frame.getContentPane().add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Open Sans", Font.PLAIN, 13));
		textArea.setBounds(23, 175, 390, 166);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(23, 239, 390, 220);
		frame.getContentPane().add(scrollPane);
		
		textArea_1 = new JTextArea();
		textArea_1.setColumns(5);
		textArea_1.setFont(new Font("Open Sans Semibold", Font.PLAIN, 14));
		textArea_1.setBounds(23, 24, 386, 28);
		textArea_1.setEditable(false);
		
		scrollPane_1 = new JScrollPane(textArea_1);
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setBounds(33, 26, 367, 72);
		frame.getContentPane().add(scrollPane_1);
		
	}
}