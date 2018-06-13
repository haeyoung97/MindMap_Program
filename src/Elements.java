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
		// menuBar & toolBar 구성
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
				String st="어서오세요. Park의 마인드 맵 프로그램 입니다.\r\n" + 
						"\r\n" + 
						"<입력 주의 사항>\r\n" +
						"하나의 노드에 하나의 행이 해당됩니다.\r\n" + 
						"입력하고자 하는 문자열에는 Tab을 입력해서는 안됩니다.\r\n" + 
						"Tab은 오직 계층을 구분하는 용도로 입력해주십시오.\r\n" + 
						"루트 노드는 하나만 가능합니다.\r\n" + 
						"적용 버튼을 통해 입력값이 Mind Map 위에 시각화됩니다.\r\n";
				JOptionPane.showMessageDialog(null,
					    st,
					    "How to input datas to TextEditor",
					    JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		SaveOp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String st="Save Button이 활성화 되지 않을 경우\r\n" + 
						"\r\n" + 
						"<활성화 하기 위한 방안>\r\n" +
						"TextEditor에 data를 작성한 후\r\n" + 
						"적용 or Apply Button을 클릭하셔야\r\n" + 
						"Save 또는 Save As Button을 사용하실수 있습니다.\r\n" + 
						"이는 사용자가 data를 입력해야만 가능합니다.\r\n" + 
						"(입력 data가 없다면 저장되지 않습니다.)\r\n";
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
						"color : 6자리 16진수(hex)\r\n" + 
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
		
		drawNodePanel = new JDrawPanel();//추가
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
		attributeEdit.setFont(basicFont); //폰트
		attributeEdit.setHorizontalAlignment(SwingConstants.CENTER);
		attributePanel.add(attributeEdit, BorderLayout.NORTH);
		
		// attribute Pane TextField 구성
		attributeFieldPane = new JPanel();
		attributeFieldPane.setLayout(new GridLayout(6, 2, 10, 5));
		SettingAttributeField();	//6개의 텍스트필드 구성
		attributePanel.add(attributeFieldPane, BorderLayout.CENTER);
		
		ButtonListener changeListener = new ButtonListener(attributeFieldPane, mindmapSection, b);
		
		JButton changeBtn = new JButton("변경");
		changeBtn.addActionListener(changeListener); //버튼 이벤트
		// Change 버튼
		JMenuItem menuItemChange = b.getMenuItem(5);
		JButton toolBtnChange = b.getToolButton(5);
		menuItemChange.addActionListener(changeListener); 
		toolBtnChange.addActionListener(changeListener);  
		
		changeBtn.setFont(basicFont); //폰트
		changeBtn.setHorizontalAlignment(SwingConstants.CENTER);
		attributePanel.add(changeBtn, BorderLayout.SOUTH);
	}
	
	private void SettingAttributeField() {
		JLabel[] labels = new JLabel[6];
		for(int i=0;i<6;i++) {
			labels[i] = new JLabel(labelStr[i]);
			labels[i].setFocusable(false);
			labels[i].setFont(basicFont); //폰트
			labels[i].setHorizontalAlignment(SwingConstants.LEFT);
			attributeFieldPane.add(labels[i]);
			fields[i] = new JTextField();
			fields[i].setFont(basicFont); //폰트
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
		textEdit.setFont(basicFont);//폰트
		textEditor.setFont(basicFont);//폰트
		
		textEdit.setHorizontalAlignment(SwingConstants.CENTER);
		textEditorPanel.add(textEdit, BorderLayout.NORTH);
		textEditor.setTabSize(2);
		textEditorPanel.add(new JScrollPane(textEditor), BorderLayout.CENTER);
		
		JButton applyBtn = new JButton("적용");			
		
		applyBtn.setFont(basicFont); //폰트
		applyBtn.setHorizontalAlignment(SwingConstants.CENTER);
		textEditorPanel.add(applyBtn, BorderLayout.SOUTH);
		
		ButtonListener applyListener = new ButtonListener(attributeSection.attributePanel, textEditor, mindmapSection, b, true);
		NewButtonListener newListener=new NewButtonListener(attributeSection.attributePanel, mindmapSection.drawNodePanel,textEditor);
		OpenButtonListener openListener = new OpenButtonListener(attributeSection.attributePanel, textEditor, mindmapSection, b,applyListener);
		
		// 적용 버튼
		applyBtn.addActionListener(applyListener); 
		JMenuItem menuItemApply = b.getMenuItem(4);
		JButton toolBtnApply = b.getToolButton(4);
		menuItemApply.addActionListener(applyListener);  
		toolBtnApply.addActionListener(applyListener);  
		// New 버튼
		JMenuItem menuItemNew = b.getMenuItem(0);
		JButton toolBtnNew = b.getToolButton(0);
		menuItemNew.addActionListener(newListener); 
		toolBtnNew.addActionListener(newListener);  
		// Open 버튼
		JMenuItem menuItemOpen = b.getMenuItem(1);
		JButton toolBtnOpen = b.getToolButton(1);
		menuItemOpen.addActionListener(openListener); 
		toolBtnOpen.addActionListener(openListener);  

	}
}