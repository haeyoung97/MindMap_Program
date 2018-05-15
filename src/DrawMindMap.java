import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JMenu;

class BasicFramework extends JFrame {
	private JTextArea textEditor = new JTextArea(20, 15);
	private JPanel menePanel, textEditorPanel, mindMapPanel, attributePanel, attributeFieldPane;
	private JSplitPane splitPaneOne, splitPaneTwo;
	private JMenuBar MenuBar;
	private JToolBar ToolBar;
	private String[] labelStr = {"  TEXT : ", "  X : ", "  Y : ", "  W : ", "  H : ", "  Color : "};
	private Font basicFont = new Font("basicFont", Font.PLAIN,15);
	
	private void CreateBar() {
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
		
		menePanel = new JPanel();
		menePanel.setLayout(new GridLayout(2,1));
		menePanel.add(MenuBar);
		menePanel.add(ToolBar);
		
	}
	
	private void SetingAttributeField() {
		JLabel[] labels = new JLabel[6];
		JTextField[] fields = new JTextField[6];
		for(int i=0;i<6;i++) {
			labels[i] = new JLabel(labelStr[i]);
			labels[i].setFocusable(false);
			labels[i].setFont(basicFont);
			labels[i].setHorizontalAlignment(SwingConstants.LEFT);
			attributeFieldPane.add(labels[i]);
			fields[i] = new JTextField(6);
			fields[i].setFont(basicFont);
			if(i==0)
				fields[i].setEditable(false);
			fields[i].setHorizontalAlignment(SwingConstants.CENTER);
			attributeFieldPane.add(fields[i]);		
		}
	
	}
	
	private void CreateMainWork(Container c) {
		// JSplitpane
		splitPaneOne = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		textEditorPanel = new JPanel();
		textEditorPanel.setLayout(new BorderLayout(10, 0));
		JTextField textEdit = new JTextField("Text Editor",15);
		textEdit.setEditable(false);
		textEdit.setFont(basicFont);
		textEdit.setHorizontalAlignment(SwingConstants.CENTER);
		textEditorPanel.add(textEdit, BorderLayout.NORTH);
		textEditor.setTabSize(2);
		textEditorPanel.add(new JScrollPane(textEditor), BorderLayout.CENTER);
		JButton applyBtn = new JButton("����");
		applyBtn.setFont(basicFont);
		applyBtn.setHorizontalAlignment(SwingConstants.CENTER);
		textEditorPanel.add(applyBtn, BorderLayout.SOUTH);
		
		splitPaneTwo = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		mindMapPanel = new JPanel();
		mindMapPanel.setLayout(new BorderLayout(10, 0));
		JTextField mindMapEdit = new JTextField("Mind Map",40);
		mindMapEdit.setEditable(false);
		mindMapEdit.setFont(basicFont);
		mindMapEdit.setHorizontalAlignment(SwingConstants.CENTER);
		mindMapPanel.add(mindMapEdit, BorderLayout.NORTH);
		
		attributePanel = new JPanel();
		attributePanel.setLayout(new BorderLayout(10, 0));
		JTextField attributeEdit = new JTextField("Attribute Editor",15);
		attributeEdit.setEditable(false);
		attributeEdit.setFont(basicFont);
		attributeEdit.setHorizontalAlignment(SwingConstants.CENTER);
		attributePanel.add(attributeEdit, BorderLayout.NORTH);
		
		// attribute Pane TextField ����
		attributeFieldPane = new JPanel();
		attributeFieldPane.setLayout(new GridLayout(6, 2, 10, 5));
		SetingAttributeField();	
		attributePanel.add(attributeFieldPane, BorderLayout.CENTER);
		
		JButton changeBtn = new JButton("����");
		changeBtn.setFont(basicFont);
		changeBtn.setHorizontalAlignment(SwingConstants.CENTER);
		attributePanel.add(changeBtn, BorderLayout.SOUTH);
	
		splitPaneOne.setContinuousLayout(true); //�������� ���̾ƿ� ��� Ȱ��ȭ
		splitPaneOne.setLeftComponent(textEditorPanel); //���� ������Ʈ ����
		splitPaneOne.setRightComponent(splitPaneTwo); //���� ������Ʈ ����
//		splitPaneOne.setDividerLocation((int)c.getSize().getWidth()/2); //����̴�(�и���) ��ġ ����     
		splitPaneOne.setDividerSize(15); //����̴�(�и���) ���� ����
			
		splitPaneTwo.setContinuousLayout(true); //�������� ���̾ƿ� ��� Ȱ��ȭ
		splitPaneTwo.setLeftComponent(mindMapPanel); //���� ������Ʈ ����
		splitPaneTwo.setRightComponent(attributePanel); //���� ������Ʈ ����
//		splitPaneTwo.setDividerLocation((int)c.getSize().getWidth()/2); //����̴�(�и���) ��ġ ����     
		splitPaneOne.setDividerSize(15); //����̴�(�и���) ���� ����
		
	}
	
	public BasicFramework() {
		setTitle("DrawMindMap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		CreateBar();  // menuBar &  toolBar ����
		c.add(menePanel, BorderLayout.NORTH);
		
		CreateMainWork(c);  // JSplitpane ����
		c.add(splitPaneOne, BorderLayout.CENTER);
		
		setSize(1000, 500);
		setVisible(true);
	}
}

public class DrawMindMap {

	public static void main(String[] args) {
		new BasicFramework();
	}

}
