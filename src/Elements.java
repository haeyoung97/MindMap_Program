import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;





class Elements extends JMenuBar{
	Font basicFont = new Font("DialogInput", Font.PLAIN,20);
	// Bar
	JButton[] toolOp = new JButton[6];
	// Mindmap
	JPanel mindMapPanel;
	JDrawPanel drawNodePanel;
	JLabel [] Node;
	// Attribute
	public JPanel attributePanel;
	JPanel attributeFieldPane;
	String[] labelStr = {"  TEXT : ", "  X : ", "  Y : ", "  W : ", "  H : ", "  Color : "};
	JLabel[] MindMapNode;
	JTextField[] fields = new JTextField[6];
	// Text
	JTextArea textEditor = new JTextArea(30, 15);
	public JPanel textEditorPanel;
	
}



class Bar extends Elements{
	private JPanel menuPanel;
	private JMenuBar MenuBar;		
	private JToolBar ToolBar;
	private String[] arrMenu = {"New", "Open", "Save", "Save As..", "Apply", "Change"};
	JMenu menu = new JMenu("Menu");
	JMenuItem[] menuOp = new JMenuItem[6];
	
	Bar(Container c, Mindmap mindmapSection) {
		// menuBar & toolBar 구성
		MenuBar = new JMenuBar();
		ToolBar = new JToolBar("Tool Bar");
		
		for(int i = 0; i < menuOp.length; i++) {
			menuOp[i] = new JMenuItem(arrMenu[i]);
			menuOp[i].setFont(basicFont);
			menu.add(menuOp[i]);
			toolOp[i] = new JButton(arrMenu[i]);
			toolOp[i].setFont(basicFont);
			ToolBar.add(toolOp[i]);
			ToolBar.addSeparator();
		}
		MenuBar.add(menu);

		ToolBar.setFloatable(false);
		menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(2,1));
		menuPanel.add(MenuBar);
		menuPanel.add(ToolBar);
		
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
	JLabel P,C;
	
	
	
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
		mindMapPanel.add(new JScrollPane(drawNodePanel),BorderLayout.CENTER);
	
		Mouser mouser=new Mouser(drawNodePanel);
		drawNodePanel.addMouseListener(mouser);
		drawNodePanel.addMouseMotionListener(mouser);
		drawNodePanel.setVisible(true);
		
	}
	


}

class Attribute extends Elements{
	Attribute(Mindmap mindmapSection){
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
		SetingAttributeField();	//6개의 텍스트필드 구성
		attributePanel.add(attributeFieldPane, BorderLayout.CENTER);
		
		JButton changeBtn = new JButton("변경");
//		changeBtn.addActionListener(new ButtonListener(attributeFieldPane,mindmapSection)); //버튼 이벤트
		
		changeBtn.setFont(basicFont); //폰트
		changeBtn.setHorizontalAlignment(SwingConstants.CENTER);
		attributePanel.add(changeBtn, BorderLayout.SOUTH);
	}
	
	private void SetingAttributeField() {
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
	
	Text(Mindmap mindmapSection,Bar b){
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
		
		ButtonListener listener = new ButtonListener(textEditor, mindmapSection);
		NewButtonListener listener2=new NewButtonListener(mindmapSection.drawNodePanel,textEditor);
		applyBtn.addActionListener(listener);  //버튼 이벤트  이상하다. mindMapPanel에서 막힌다. 행의 위치에 따라 다
		
		JMenuItem menuItem = b.getMenuItem(4);
		JButton toolBtn = b.getToolButton(4);
		menuItem.addActionListener(listener);  //버튼 이벤트  이상하다. mindMapPanel에서 막힌다. 행의 위치에 따라 다
		toolBtn.addActionListener(listener);  //버튼 이벤트  이상하다. mindMapPanel에서 막힌다. 행의 위치에 따라 다

		JMenuItem menuItem2 = b.getMenuItem(0);
		JButton toolBtn2 = b.getToolButton(0);
		menuItem2.addActionListener(listener2);  //버튼 이벤트  이상하다. mindMapPanel에서 막힌다. 행의 위치에 따라 다
		toolBtn2.addActionListener(listener2);  //

	}
}