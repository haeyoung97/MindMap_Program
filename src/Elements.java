import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;



class Elements extends JMenuBar{
	Font basicFont = new Font("DialogInput", Font.PLAIN,20);
	// Bar
	JButton[] toolOp = new JButton[7];
	// Mindmap
	JPanel mindMapPanel;
	JDrawPanel drawNodePanel;
	JLabel [] Node;
	// Attribute
	public JPanel attributePanel;
	JPanel attributeFieldPane;
	String[] labelStr = {"  TEXT : ", "  X : ", "  Y : ", "  W : ", "  H : ", "  Color : "};
	JLabel[] MindMapNode;
	JTextField[] fields = new JTextField[7];
	// Text
	JTextArea textEditor = new JTextArea(30, 15);
	public JPanel textEditorPanel;
	SaveButtonListener saveListener = new SaveButtonListener();
	
}



class Bar extends Elements{
	private JPanel menuPanel;
	private JMenuBar MenuBar;		
	private JToolBar ToolBar;
	private String[] arrMenu = {"New", "Open", "Save", "Save As..", "Apply", "Change", "Close"};
	JMenu menu = new JMenu("Menu");
	JMenu help = new JMenu("help");
	JMenuItem[] menuOp = new JMenuItem[7];
	JMenuItem helpOp = new JMenuItem("TextEditor form");
	JMenuItem SaveOp = new JMenuItem("Using save Option");
	JMenuItem ColorOp = new JMenuItem("Using Color Change");
	
	Bar(Container c, Mindmap mindmapSection) {
		// menuBar & toolBar ����
		MenuBar = new JMenuBar();
		ToolBar = new JToolBar("Tool Bar");
		
		for(int i = 0; i < arrMenu.length; i++) {
			menuOp[i] = new JMenuItem(arrMenu[i]);
			menuOp[i].setFont(basicFont);
			menu.add(menuOp[i]);
			toolOp[i] = new JButton(arrMenu[i]);
			toolOp[i].setFont(basicFont);
			ToolBar.add(toolOp[i]);
			ToolBar.addSeparator();
		}
		help.add(helpOp);
		help.add(SaveOp);
		help.add(ColorOp);
		MenuBar.add(menu);
		MenuBar.add(help);
		
		helpOp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String st="�������. Park�� ���ε� �� ���α׷� �Դϴ�.\r\n" + 
						"\r\n" + 
						"<�Է� ���� ����>\r\n" +
						"�ϳ��� ��忡 �ϳ��� ���� �ش�˴ϴ�.\r\n" + 
						"�Է��ϰ��� �ϴ� ���ڿ����� Tab�� �Է��ؼ��� �ȵ˴ϴ�.\r\n" + 
						"Tab�� ���� ������ �����ϴ� �뵵�� �Է����ֽʽÿ�.\r\n" + 
						"��Ʈ ���� �ϳ��� �����մϴ�.\r\n" + 
						"���� ��ư�� ���� �Է°��� Mind Map ���� �ð�ȭ�˴ϴ�.\r\n";
				JOptionPane.showMessageDialog(null,
					    st,
					    "How to input datas to TextEditor",
					    JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		SaveOp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String st="Save Button�� Ȱ��ȭ ���� ���� ���\r\n" + 
						"\r\n" + 
						"<Ȱ��ȭ �ϱ� ���� ���>\r\n" +
						"TextEditor�� data�� �ۼ��� ��\r\n" + 
						"���� or Apply Button�� Ŭ���ϼž�\r\n" + 
						"Save �Ǵ� Save As Button�� ����ϽǼ� �ֽ��ϴ�.\r\n" + 
						"�̴� ����ڰ� data�� �Է��ؾ߸� �����մϴ�.\r\n" + 
						"(�Է� data�� ���ٸ� ������� �ʽ��ϴ�.)\r\n";
				JOptionPane.showMessageDialog(null,
					    st,
					    "How to using save button",
					    JOptionPane.PLAIN_MESSAGE);
			}
		});

		ColorOp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String st="Color Change input form\r\n" + 
						"\r\n" + 
						"<Color input form>\r\n" +
						"-------------------------------\r\n" + 
						"color : 6�ڸ� 16����(hex)\r\n" + 
						" ( user input form : 0x______ ) \r\n" ;
				JOptionPane.showMessageDialog(null,
					    st,
					    "How to input color to Attribute Editor",
					    JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		ToolBar.setFloatable(false);
		menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(2,1));
		menuPanel.add(MenuBar);
		menuPanel.add(ToolBar);
		
		CloseButtonListener closeListener = new CloseButtonListener();
		menuOp[6].addActionListener(closeListener);
		toolOp[6].addActionListener(closeListener);
		
		c.add(menuPanel, BorderLayout.NORTH);
		
	}
	
	JMenuItem getMenuItem(int index) {
		return menuOp[index];
	}
	JButton getToolButton(int index) {
		return toolOp[index];
	}
}
	
class Mindmap extends Elements{
	
	Mindmap(){		
		mindMapPanel = new JPanel();
		mindMapPanel.setLayout(new BorderLayout(10, 0));
		JTextField mindMapEdit = new JTextField("Mind Map",40);
		mindMapEdit.setEditable(false);
		mindMapEdit.setFont(basicFont);
		mindMapEdit.setHorizontalAlignment(SwingConstants.CENTER);
		
		drawNodePanel = new JDrawPanel();//�߰�
		drawNodePanel.setLayout(null);
		drawNodePanel.setBackground(Color.white);

		mindMapPanel.add(mindMapEdit, BorderLayout.NORTH);
		mindMapPanel.add(new JScrollPane(drawNodePanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS),BorderLayout.CENTER);
		
		drawNodePanel.setVisible(true);
		mindMapPanel.setVisible(true);
		
	}

}

class Attribute extends Elements{
	Attribute(Mindmap mindmapSection, Bar b){
		attributePanel = new JPanel();
		attributePanel.setLayout(new BorderLayout(10, 0));
		JTextField attributeEdit = new JTextField("Attribute Editor",15);
		attributeEdit.setEditable(false);
		attributeEdit.setFont(basicFont); //��Ʈ
		attributeEdit.setHorizontalAlignment(SwingConstants.CENTER);
		attributePanel.add(attributeEdit, BorderLayout.NORTH);
		
		// attribute Pane TextField ����
		attributeFieldPane = new JPanel();
		attributeFieldPane.setLayout(new GridLayout(6, 2, 10, 5));
		SettingAttributeField();	//6���� �ؽ�Ʈ�ʵ� ����
		attributePanel.add(attributeFieldPane, BorderLayout.CENTER);
		
		ButtonListener changeListener = new ButtonListener(attributeFieldPane, mindmapSection, b);
		
		JButton changeBtn = new JButton("����");
		changeBtn.addActionListener(changeListener); //��ư �̺�Ʈ
		// Change ��ư
		JMenuItem menuItemChange = b.getMenuItem(5);
		JButton toolBtnChange = b.getToolButton(5);
		menuItemChange.addActionListener(changeListener); 
		toolBtnChange.addActionListener(changeListener);  
		
		changeBtn.setFont(basicFont); //��Ʈ
		changeBtn.setHorizontalAlignment(SwingConstants.CENTER);
		attributePanel.add(changeBtn, BorderLayout.SOUTH);
	}
	
	private void SettingAttributeField() {
		JLabel[] labels = new JLabel[6];
		for(int i=0;i<6;i++) {
			labels[i] = new JLabel(labelStr[i]);
			labels[i].setFocusable(false);
			labels[i].setFont(basicFont); //��Ʈ
			labels[i].setHorizontalAlignment(SwingConstants.LEFT);
			attributeFieldPane.add(labels[i]);
			fields[i] = new JTextField();
			fields[i].setFont(basicFont); //��Ʈ
			if(i==0)
				fields[i].setEditable(false);
			fields[i].setHorizontalAlignment(SwingConstants.CENTER);
			attributeFieldPane.add(fields[i]);		
		}
	}
}

class Text extends Elements {
	
	Text(Attribute attributeSection, Mindmap mindmapSection, Bar b){
		textEditorPanel = new JPanel();
		textEditorPanel.setLayout(new BorderLayout(10, 0));
		JTextField textEdit = new JTextField("Text Editor",15);
		textEdit.setEditable(false);
		textEdit.setFont(basicFont);//��Ʈ
		textEditor.setFont(basicFont);//��Ʈ
		
		textEdit.setHorizontalAlignment(SwingConstants.CENTER);
		textEditorPanel.add(textEdit, BorderLayout.NORTH);
		textEditor.setTabSize(2);
		textEditorPanel.add(new JScrollPane(textEditor), BorderLayout.CENTER);
		
		JButton applyBtn = new JButton("����");			
		
		applyBtn.setFont(basicFont); //��Ʈ
		applyBtn.setHorizontalAlignment(SwingConstants.CENTER);
		textEditorPanel.add(applyBtn, BorderLayout.SOUTH);
		
		ButtonListener applyListener = new ButtonListener(attributeSection.attributePanel, textEditor, mindmapSection, b, true);
		NewButtonListener newListener=new NewButtonListener(attributeSection.attributePanel, mindmapSection.drawNodePanel,textEditor);
		OpenButtonListener openListener = new OpenButtonListener(attributeSection.attributePanel, textEditor, mindmapSection, b,applyListener);
		
		// ���� ��ư
		applyBtn.addActionListener(applyListener); 
		JMenuItem menuItemApply = b.getMenuItem(4);
		JButton toolBtnApply = b.getToolButton(4);
		menuItemApply.addActionListener(applyListener);  
		toolBtnApply.addActionListener(applyListener);  
		// New ��ư
		JMenuItem menuItemNew = b.getMenuItem(0);
		JButton toolBtnNew = b.getToolButton(0);
		menuItemNew.addActionListener(newListener); 
		toolBtnNew.addActionListener(newListener);  
		// Open ��ư
		JMenuItem menuItemOpen = b.getMenuItem(1);
		JButton toolBtnOpen = b.getToolButton(1);
		menuItemOpen.addActionListener(openListener); 
		toolBtnOpen.addActionListener(openListener);  

	}
}