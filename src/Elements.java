import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;

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
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

class Elements extends JMenuBar{
	Font basicFont = new Font("DialogInput", Font.PLAIN,20);
	// Bar
//	JMenu[] menuOp = new JMenu[6];
	JButton[] toolOp = new JButton[6];
	// Mindmap
	JPanel mindMapPanel;
	JPanel drawNodePanel;
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
		// menuBar & toolBar ����
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
		
//		ButtonListener listener = new ButtonListener(textEditor, mindmapSection);
//		
//		menuOp[4].addActionListener(listener);  //��ư �̺�Ʈ  �̻��ϴ�. mindMapPanel���� ������. ���� ��ġ�� ���� ��
//		toolOp[4].addActionListener(listener);  //��ư �̺�Ʈ  �̻��ϴ�. mindMapPanel���� ������. ���� ��ġ�� ���� ��

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
	Mindmap(){		
		mindMapPanel = new JPanel();
		mindMapPanel.setLayout(new BorderLayout(10, 0));
		JTextField mindMapEdit = new JTextField("Mind Map",40);
		mindMapEdit.setEditable(false);
		mindMapEdit.setFont(basicFont);
		mindMapEdit.setHorizontalAlignment(SwingConstants.CENTER);
		
		drawNodePanel = new JPanel();//�߰�
		drawNodePanel.setLayout(new FlowLayout());
		drawNodePanel.setBackground(Color.white);

		mindMapPanel.add(mindMapEdit, BorderLayout.NORTH);
		mindMapPanel.add(drawNodePanel,BorderLayout.CENTER);
		drawNodePanel.setVisible(true);
	}

}

class Attribute extends Elements{
	Attribute(Mindmap mindmapSection){
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
		SetingAttributeField();	//6���� �ؽ�Ʈ�ʵ� ����
		attributePanel.add(attributeFieldPane, BorderLayout.CENTER);
		
		JButton changeBtn = new JButton("����");
//		changeBtn.addActionListener(new ButtonListener(attributeFieldPane,mindmapSection)); //��ư �̺�Ʈ
		
		changeBtn.setFont(basicFont); //��Ʈ
		changeBtn.setHorizontalAlignment(SwingConstants.CENTER);
		attributePanel.add(changeBtn, BorderLayout.SOUTH);
	}
	
	private void SetingAttributeField() {
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
	
	Text(Mindmap mindmapSection,Bar b){
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
		
		ButtonListener listener = new ButtonListener(textEditor, mindmapSection);
		
		applyBtn.addActionListener(listener);  //��ư �̺�Ʈ  �̻��ϴ�. mindMapPanel���� ������. ���� ��ġ�� ���� ��
		
		JMenuItem menuItem = b.getMenuItem(4);
		JButton toolBtn = b.getToolButton(4);
		menuItem.addActionListener(listener);  //��ư �̺�Ʈ  �̻��ϴ�. mindMapPanel���� ������. ���� ��ġ�� ���� ��
		toolBtn.addActionListener(listener);  //��ư �̺�Ʈ  �̻��ϴ�. mindMapPanel���� ������. ���� ��ġ�� ���� ��

		
	}
}

class Data{
	private Data child;		//�ڽ�
	private Data sibling;	//����
	private Data parent;	//�θ�
	private String value;	//������
	private int h;
	
	public Data(String value) {this.value=value; child=null; sibling=null; parent=null;}
	
	public void setChild(Data obj) {child=obj;}
	public Data getChild() {return child;}
	
	public void setSibling(Data obj) {sibling=obj;}
	public Data getSibling() {return sibling;}
	
	public void setParent(Data obj) {parent=obj;}
	public Data getParent() {return parent;}
	
	public String toString() {return value;}
	
	void setHeight(int h) {this.h=h;}
	int getHeight() {return this.h;}
}

class Make2Label extends Elements {
	private Color[] labelColor = {Color.MAGENTA, Color.ORANGE, Color.GREEN, Color.CYAN, Color.RED, Color.BLUE,  Color.PINK};
	Color getColor(int h) {return labelColor[h];}
	
	private JLabelListener labelListen = new JLabelListener();
	
	JLabel Make2Label(Data k, String value) {
		JLabel lb = new JLabel(value);
		CompoundBorder comBd = new CompoundBorder(new LineBorder(getColor(k.getHeight()), 10, true), new EmptyBorder(5,10,5,10));
		lb.setBackground(getColor(k.getHeight()%7));
		lb.setBorder(comBd);
//		lb.setBorder(new LineBorder(Color.BLUE, 5, true));
//		lb.setBorder(new EmptyBorder(5,10,5,10));
		lb.setFont(basicFont);
	    lb.setOpaque(false); 
	    lb.addMouseListener(labelListen);
		return lb;
	}
}

class Tree extends Make2Label{
	Data start=null, last=null, obj=null;
	
	int i=0;

	boolean MakeTree(String [] member) {
		int k=1;
		int h=0;
		for(int i=0;i<member.length;i++) {
			if(start==null && member[0].charAt(0)!='\t') { //ù ������ ��Ʈ (\t���� ���� ���Ѵ�.)
				start=new Data(member[0]);
				start.setHeight(0);
				last=start;
			}
			else {
				
				try {	
					
					obj=new Data(member[i]);
				
					int lastTab=last.toString().lastIndexOf('\t');
					int nowTab=obj.toString().lastIndexOf('\t');
					
					//���⼭ �������� �з�. \t�� ������ ���̴�...���� \t�� �������� �ʴ´ٸ� ���ο� Ʈ���� ����� �� , ����� ��� X
					
					if(obj.toString().charAt(0)!='\t') {
						JOptionPane.showMessageDialog(null, "obj.toString().charAt(0)!='\\t'", "��� �޽��� ����", JOptionPane.WARNING_MESSAGE);
						System.out.println("�ι�° ��Ʈ");
						this.Default();
						return false;
					}
					
					else if(lastTab==nowTab) { //���� �߰��� ��尡 �ֱ� ������ ���� ����
						obj.setParent(last.getParent());
						obj.setHeight(last.getHeight());
						last.setSibling(obj);
					} 	
					
					else if(nowTab-lastTab==1) { //�ڽĳ�� �߰�
						obj.setParent(last);
						obj.setHeight(last.getHeight()+1);
						last.setChild(obj);
					}
					else { //last�� ������ �ڽ�, ���� �߰��� �༮�� ... last���� ���� ����
						h=last.getHeight();
						while(true) {
							last=last.getParent();
							h--;
							if(last.toString().lastIndexOf('\t')==obj.toString().lastIndexOf('\t')) {
								last.setSibling(obj);
								obj.setHeight(last.getHeight());
								break;
							}
						}	
					}
					k++;
					last=obj;
					System.out.println("make tree ;  " + k);
					
				}
				catch(NullPointerException e) {					//����ó�� : Ʈ�� �̻��ϰ� ���� ��� ����� NullPointerException
					JOptionPane.showMessageDialog(null, "NullPointerException", "��� �޽��� ����", JOptionPane.WARNING_MESSAGE);
					this.Default();
					return false;
					
				}
			}
			
		}
		return true;
		
	}
	
	void print() {
		Data k=start;
		System.out.println(k.getHeight()+" "+start.toString());
//		System.out.println(H+" "+k.toString());
		while(true) {
			
			if(k.getChild()!=null) {
				k=k.getChild();
				System.out.println(k.getHeight()+" "+k.toString());
			}
			
			else if(k.getSibling()!=null) {
				k=k.getSibling();
				System.out.println(k.getHeight()+" "+k.toString());
			}
			
			else {
				if(k==last) {
					break;
				}
				while(true) {
					k=k.getParent();
					if(k.getSibling()!=null) {
						k=k.getSibling();
						System.out.println(k.getHeight()+" "+k.toString());
						break;
					}
				}
			}
			
		}
	}
	
	void AddLabel(JPanel Panel) {
		
		Data k=start;

		Panel.add(Make2Label(k, k.toString()));
		
		while(true) {
				if(k==last) {
					break;
				}
				
				else {
					if(k.getChild()!=null) {
					k=k.getChild();
					Panel.add(Make2Label(k, k.toString()));
				}
				
				else if(k.getSibling()!=null) {
					k=k.getSibling();
					Panel.add(Make2Label(k, k.toString()));
				}
				
				else {
					while(true) {
						k=k.getParent();
						if(k.getSibling()!=null) {
							k=k.getSibling();
							Panel.add(Make2Label(k, k.toString()));
							break;
						}
					}
				}
			}
		}
	}
	
	void Default() {
		start=null;
		last=null;
		obj=null;
	}
	
	
	
}