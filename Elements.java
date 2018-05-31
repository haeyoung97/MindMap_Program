import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;

//import javax.swing.JFrame;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
//import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.MenuListener;

import java.math.*;													//�ϴ��� �̷���.

class Elements extends JMenuBar{
	static Font basicFont = new Font("DialogInput", Font.PLAIN,20);           ///////////////////////////////////////static���� �ϴ�...Make2Label ���� ��������.
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
//		drawNodePanel.setLayout(new FlowLayout());
		 drawNodePanel.setLayout(null);
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
		JTextField[] fields = new JTextField[6];
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

class NewJLabel extends JLabel{	///////////////////////////////////���ο� JLabel ���� --- NewJLabel	///////////////////////////////////���ο� JLabel ���� --- NewJLabel
	private String name;
	private int x,y,w,d;
	private int dimention;
	
	private NewJLabel parentlabel;
	
	
	void setParentLabel(NewJLabel parentlabel) {this.parentlabel=parentlabel;}
	int getDimention() {return dimention;}
	
//	void setDimention(NewJLabel parentlabel) {
//		switch(parentlabel.getDimention()) {
//		case 0:
//			while
//			break;
//		
//		case 1:
//			break;
//			
//		case 2:
//			break;
//			
//		case 3:
//			break;
//		
//		
//		
//		
//		}
//	}
	
	
	
}	///////////////////////////////////���ο� JLabel ���� --- NewJLabel	///////////////////////////////////���ο� JLabel ���� --- NewJLabel



class Data extends JLabel{ //Data�� JLabel�� ����ϴ���, �ƴϸ� JLabel�� ����� ���ο� newJLabel�� ������.........................................����̴�.
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
	public int getHeight() {return this.h;}
	
	String getValue() {return value.trim();}
}

class Tree{
	Data start=null, last=null, obj=null;
	int [] NumberOfNodes=new int[5];
	
	int i=0;
	
	JLabel Make2Label(Data k) {					//////////////////////////////////////////////////////////////////
		//////////////���� ���/////////////////
		JLabel Label = new JLabel(k.toString());
		Label.setOpaque(true);
		int h=k.getHeight();
		switch(h) {								//////////////�׷���????////////////////////
			case 0:
				Label.setBackground(Color.RED);
				break;
			case 1:
				Label.setBackground(Color.YELLOW);
				break;
			case 2:
				Label.setBackground(Color.GREEN);
				break;
			case 3:
				Label.setBackground(Color.CYAN);
				break;
			case 4:
				Label.setBackground(Color.MAGENTA);
				break;
			default:
				Label.setBackground(Color.PINK);
		}
		
		
		
		Label.setFont(Elements.basicFont);
		
		return Label;
	}			
	

											//////////////////////////////////////////////////////////////////
	
	
	boolean MakeTree(String [] member) {
		int k=1;
		int h=0;
		for(int i=0;i<member.length;i++) {
			if(start==null && member[0].charAt(0)!='\t') { //ù ������ ��Ʈ (\t���� ���� ���Ѵ�.)
				start=new Data(member[0]);
				start.setHeight(0);
				last=start;
				NumberOfNodes[0]++;
				
			}
			else {
//				System.out.println("make tree ;  " + last.toString());
				try {	
					
					obj=new Data(member[i]);
				
					int lastTab=last.toString().lastIndexOf('\t');
					int nowTab=obj.toString().lastIndexOf('\t');
					
					//���⼭ �������� �з�. \t�� ������ ���̴�...���� \t�� �������� �ʴ´ٸ� ���ο� Ʈ���� ����� �� , ����� ��� X
					
					if(obj.toString().charAt(0)!='\t') {
						JOptionPane.showMessageDialog(null, "���Ŀ� ���߾� �ۼ����ֽʽÿ�(�ټ��� ��Ʈ)", "��� �޽��� ����", JOptionPane.WARNING_MESSAGE);
						System.out.println("�ι�° ��Ʈ");
						this.Default();
						return false;						
					}
					
					else if(lastTab==nowTab) { //���� �߰��� ��尡 �ֱ� ������ ���� ����
						obj.setParent(last.getParent());
						obj.setHeight(last.getHeight());
						NumberOfNodes[obj.getHeight()]++;
						last.setSibling(obj);
						System.out.println("L==t");
					} 	
					
					else if(nowTab-lastTab==1) { //�ڽĳ�� �߰�
						obj.setParent(last);
						obj.setHeight(last.getHeight()+1);
						NumberOfNodes[obj.getHeight()]++;
						last.setChild(obj);
					}
					else { //last�� ������ �ڽ�, ���� �߰��� �༮�� ... last���� ���� ����
						
						while(true) {
							last=last.getParent();
							h=last.getHeight();
							if(last.toString().lastIndexOf('\t')==obj.toString().lastIndexOf('\t')) {
								last.setSibling(obj);
								obj.setHeight(last.getHeight());
								NumberOfNodes[obj.getHeight()]++;
								obj.setParent(last.getParent());
								break;
							}
						}	
					}
					k++;
					last=obj;
					
					
				}
					
				
				catch(NullPointerException e) {					//����ó�� : Ʈ�� �̻��ϰ� ���� ��� ����� NullPointerException
					JOptionPane.showMessageDialog(null, "���Ŀ� ���߾� �ۼ����ֽʽÿ�(�߸��� ����)", "��� �޽��� ����", JOptionPane.WARNING_MESSAGE);
					this.Default();
					return false;
					
				}
				
				
				

			}
			
		}
		return true;
		
	}
	
	void print() {
		Data k=start;
		System.out.println(k.getHeight()+" "+start.getValue());
//		System.out.println(H+" "+k.getValue());
		while(true) {
			
			if(k.getChild()!=null) {
				k=k.getChild();
				System.out.println(k.getHeight()+" "+k.getValue());
			}
			
			else if(k.getSibling()!=null) {
				k=k.getSibling();
				System.out.println(k.getHeight()+" "+k.getValue());
			}
			
			else {
				if(k==last) {
					break;
				}
				while(true) {
					k=k.getParent();
					if(k.getSibling()!=null) {
						k=k.getSibling();
						System.out.println(k.getHeight()+" "+k.getValue());
						break;
					}
				}
			}
			
		}
	}
	
	void AddLabel(JPanel Panel) {
		
		Data k=start;
		Data tmp;
		int cnt=0;
		JLabel newtmpLabel,oldtmpLabel;
		
		int width=Panel.getSize().width;
		int heigth=Panel.getSize().height;
		newtmpLabel=Make2Label(k);
		Panel.add(newtmpLabel); //��Ʈ ��� �ø���
		newtmpLabel.setSize(25,25);
		newtmpLabel.setLocation(width/2-Panel.getComponent(0).getWidth()/2,heigth/2-Panel.getComponent(0).getHeight()/2); //���...���� ª�� �ϰ������ ���� ��� �𼭸��� �߽��̶�..

		
		while(true) {
			oldtmpLabel=newtmpLabel;
			if(k==last) {   //�������
					break;
			}
			else {
				if(k.getSibling()!=null) { //�����켱
				k=k.getSibling();
				newtmpLabel=Make2Label(k);
				Panel.add(newtmpLabel);
				}
				else if(k.getChild()!=null) { 
				k=k.getChild();
				newtmpLabel=Make2Label(k);
				Panel.add(newtmpLabel);
				newtmpLabel.setSize(25,25);
				newtmpLabel.setLocation(oldtmpLabel.getX()/2-newtmpLabel.getWidth()/2,oldtmpLabel.getY()/2-newtmpLabel.getHeight()/2);
				}
			

			
			else {
				
				while(true) {
					k=k.getParent();
						if(k.getSibling()!=null) {
						k=k.getSibling();
						newtmpLabel=Make2Label(k);
						Panel.add(newtmpLabel);
						
						break;
						}
					}
				}
			}
		}
	}
	void NewAddLabel(int width, int height,Data k,JPanel Panel) { //��ͷ� ���� AddLabel

//		
//		JPanel [] panel4node = new JPanel[4];
//		for(int i=0;i<4;i++) {
//			panel4node[i]=new JPanel();
//		}
//		


						/* ADDLABEL */
		JLabel label=new JLabel(k.getValue());
		
		if(k==start) {
			label.setLocation(width/2-20,height/2-10);
			width=width/2;
			height=height/2;
		}
		
		if(k.getChild()!=null) {	//�ڽĸ���
			k=k.getChild();
			NewAddLabel(width, height,k,Panel);
		}
		else if(k.getSibling()!=null) {
			k=k.getSibling();
			NewAddLabel(width, height,k,Panel);
		}
		else {
			
			while(true) {
				if(k==last) {
					return;
				}
					k=k.getParent();
					if(k.getSibling()!=null) {
						k=k.getSibling();
//						newtmpLabel=Make2Label(k);
//						Panel.add(newtmpLabel);
						NewAddLabel(width, height,k,Panel);

						break;
					}
			}
		}
		System.out.println("ADDING LABEL : "+k.getValue());
		Panel.add(label);
		label.setSize(50,20);
	}

	
	void Default() {
		start=null;
		last=null;
		obj=null;
	}
	
	int [] settingLocation(JLabel oldtmpLabel, JLabel newtmpLabel) {
		int [] pair=new int[2];
		
		
		
		return pair;
	}
	
	
	
}