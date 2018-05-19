import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

class Bar extends JMenuBar{
	private JPanel menuPanel;
	private JMenuBar MenuBar;		
	private JToolBar ToolBar;
	
	
	Bar(Container c) {
		// menuBar & toolBar ����
		MenuBar = new JMenuBar();
		ToolBar = new JToolBar("Tool Bar");
		JMenu newMenu = new JMenu("New");
		JMenu openMenu = new JMenu("Open");
		JMenu saveMenu = new JMenu("Save");
		JMenu saveAsMenu = new JMenu("Save As..");
		JMenu applyMenu = new JMenu("Apply");
		JMenu changeMenu = new JMenu("Change");
		MenuBar.add(newMenu);
		MenuBar.add(openMenu);
		MenuBar.add(saveMenu);
		MenuBar.add(saveAsMenu);
		MenuBar.add(applyMenu);
		MenuBar.add(changeMenu);
		ToolBar.add(new JButton("New"));
		ToolBar.add(new JButton("Open"));
		ToolBar.add(new JButton("Save"));
		ToolBar.add(new JButton("Save As.."));
		ToolBar.add(new JButton("Apply"));
		ToolBar.add(new JButton("Change"));
		
		menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(2,1));
		menuPanel.add(MenuBar);
		menuPanel.add(ToolBar);
		
		c.add(menuPanel, BorderLayout.NORTH);
	}
	
}
	
class Mindmap {
	JPanel mindMapPanel;
	JPanel drawNodePanel;


	JLabel [] Node;
	
	Mindmap(){		
		mindMapPanel = new JPanel();
		mindMapPanel.setLayout(new BorderLayout(10, 0));
		JTextField mindMapEdit = new JTextField("Mind Map",40);
		mindMapEdit.setEditable(false);
//		mindMapEdit.setFont(basicFont);
		mindMapEdit.setFont(new Font("basicFont", Font.PLAIN,15));
		mindMapEdit.setHorizontalAlignment(SwingConstants.CENTER);
		
		drawNodePanel = new JPanel();//�߰�
		drawNodePanel.setLayout(new FlowLayout());
		drawNodePanel.setBackground(Color.white);
		
		mindMapPanel.add(mindMapEdit, BorderLayout.NORTH);
		mindMapPanel.add(drawNodePanel,BorderLayout.CENTER);
//		mindMapPanel.add(drawNodePanel,BorderLayout.CENTER);
		drawNodePanel.setVisible(true);
	}
	
	void drawNodes() {
		for(int i=0;i<Node.length;i++) {
			System.out.println("cnt : " + i);
			drawNodePanel.add(Node[i]);
			System.out.println("������?~~"+drawNodePanel.getComponentCount());
		}
		System.out.println("dygh");
	}
	
	
		
}

class Attribute {

	public JPanel attributePanel;
	private JPanel attributeFieldPane;
	private String[] labelStr = {"  TEXT : ", "  X : ", "  Y : ", "  W : ", "  H : ", "  Color : "};
	private JLabel[] MindMapNode;
	
	Attribute(Mindmap mindmapSection){
		attributePanel = new JPanel();
		attributePanel.setLayout(new BorderLayout(10, 0));
		JTextField attributeEdit = new JTextField("Attribute Editor",15);
		attributeEdit.setEditable(false);
		attributeEdit.setFont(new Font("basicFont", Font.PLAIN,15)); //��Ʈ
		attributeEdit.setHorizontalAlignment(SwingConstants.CENTER);
		attributePanel.add(attributeEdit, BorderLayout.NORTH);
		
		// attribute Pane TextField ����
		attributeFieldPane = new JPanel();
		attributeFieldPane.setLayout(new GridLayout(6, 2, 10, 5));
		SetingAttributeField();	//6���� �ؽ�Ʈ�ʵ� ����
		attributePanel.add(attributeFieldPane, BorderLayout.CENTER);
		
		JButton changeBtn = new JButton("����");
		changeBtn.addActionListener(new ButtonListener(attributeFieldPane,mindmapSection)); //��ư �̺�Ʈ
		
		
		changeBtn.setFont(new Font("basicFont", Font.PLAIN,15)); //��Ʈ
		changeBtn.setHorizontalAlignment(SwingConstants.CENTER);
		attributePanel.add(changeBtn, BorderLayout.SOUTH);
	}
	
	private void SetingAttributeField() {
		JLabel[] labels = new JLabel[6];
		JTextField[] fields = new JTextField[6];
		for(int i=0;i<6;i++) {
			labels[i] = new JLabel(labelStr[i]);
			labels[i].setFocusable(false);
			labels[i].setFont(new Font("basicFont", Font.PLAIN,15)); //��Ʈ
			labels[i].setHorizontalAlignment(SwingConstants.LEFT);
			attributeFieldPane.add(labels[i]);
			fields[i] = new JTextField(6);
			fields[i].setFont(new Font("basicFont", Font.PLAIN,15)); //��Ʈ
			if(i==0)
				fields[i].setEditable(false);
			fields[i].setHorizontalAlignment(SwingConstants.CENTER);
			attributeFieldPane.add(fields[i]);		
		}
	
	}
}

class Text{
	private JTextArea textEditor = new JTextArea(20, 15);
	public JPanel textEditorPanel;
	private JLabel[] MindMapNode;
	
	Text(Mindmap mindmapSection){
		textEditorPanel = new JPanel();
		textEditorPanel.setLayout(new BorderLayout(10, 0));
		JTextField textEdit = new JTextField("Text Editor",15);
		textEdit.setEditable(false);
		textEdit.setFont(new Font("basicFont", Font.PLAIN,15));//��Ʈ
		textEdit.setHorizontalAlignment(SwingConstants.CENTER);
		textEditorPanel.add(textEdit, BorderLayout.NORTH);
		textEditor.setTabSize(2);
		textEditorPanel.add(new JScrollPane(textEditor), BorderLayout.CENTER);
		
		JButton applyBtn = new JButton("����");			
		
		applyBtn.setFont(new Font("basicFont", Font.PLAIN,15)); //��Ʈ
		applyBtn.setHorizontalAlignment(SwingConstants.CENTER);
		textEditorPanel.add(applyBtn, BorderLayout.SOUTH);

		applyBtn.addActionListener(new ButtonListener(textEditor,mindmapSection));  //��ư �̺�Ʈ  �̻��ϴ�. mindMapPanel���� ������. ���� ��ġ�� ���� ��
		
		
	}
}



	
	

