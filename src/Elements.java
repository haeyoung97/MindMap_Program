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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
//import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;


class Elements {
	Font basicFont = new Font("DialogInput", Font.PLAIN,20);
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

class Bar extends JMenuBar{
	private Font basicFont = new Font("DialogInput", Font.BOLD,20);
	
	private JPanel menuPanel;
	private JMenuBar MenuBar;		
	private JToolBar ToolBar;
	private JMenu[] menuOp = new JMenu[6];
	private JButton[] toolOp = new JButton[6];	
	private String[] arrMenu = {"New", "Open", "Save", "Save As..", "Apply", "Change"};
	
	Bar(Container c) {
		// menuBar & toolBar 구성
		MenuBar = new JMenuBar();
		ToolBar = new JToolBar("Tool Bar");
		for(int i = 0; i < menuOp.length; i++) {
			menuOp[i] = new JMenu(arrMenu[i]);
			menuOp[i].setFont(basicFont);
			MenuBar.add(menuOp[i]);
			toolOp[i] = new JButton(arrMenu[i]);
			toolOp[i].setFont(basicFont);
			ToolBar.add(toolOp[i]);
			ToolBar.addSeparator();
		}
		ToolBar.setFloatable(false);
		menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(2,1));
		menuPanel.add(MenuBar);
		menuPanel.add(ToolBar);
		
		c.add(menuPanel, BorderLayout.NORTH);
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
		
		drawNodePanel = new JPanel();//추가
		drawNodePanel.setLayout(new FlowLayout());
		drawNodePanel.setBackground(Color.white);
//		mindMapPanel.add(drawNodePanel,BorderLayout.CENTER);
//		
		mindMapPanel.add(mindMapEdit, BorderLayout.NORTH);
		mindMapPanel.add(drawNodePanel,BorderLayout.CENTER);
		drawNodePanel.setVisible(true);
	}
	
	void drawNodes() {
		for(int i=0;i<Node.length;i++) {
			System.out.println("cnt : " + i);
			//drawNodePanel.add(Node[i]);
			System.out.println("컴포수?~~"+drawNodePanel.getComponentCount());
		}
		System.out.println("dygh");
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
		JTextField[] fields = new JTextField[6];
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
	
	Text(Mindmap mindmapSection){
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

		applyBtn.addActionListener(new ButtonListener(textEditor,mindmapSection));  //버튼 이벤트  이상하다. mindMapPanel에서 막힌다. 행의 위치에 따라 다
		
		
	}
}

class Data{
	private Data child;		//자식
	private Data sibling;	//형제
	private Data parent;	//부모
	private String value;	//실제값
	
	public Data(String value) {this.value=value; child=null; sibling=null; parent=null;}
	
	public void setChild(Data obj) {child=obj;}
	public Data getChild() {return child;}
	
	public void setSibling(Data obj) {sibling=obj;}
	public Data getSibling() {return sibling;}
	
	public void setParent(Data obj) {parent=obj;}
	public Data getParent() {return parent;}
	
	public String toString() {return value;}
}

class Tree{
	Data start=null, last=null, obj=null;
	
	
	int i=0;
	
	JLabel Make2Label(String value) {
		return new JLabel(value);
	}
	
	
	void MakeTree(String [] member) {

		int k=1;
		for(int i=0;i<member.length;i++) {
			if(start==null&&member[0].charAt(0)!='\t') { //첫 성분이 루트 (\t으로 시작 안한다.)
				start=new Data(member[0]);
				last=start;
			}
			
			
			else {
				obj=new Data(member[i]);
				
				int lastTab=last.toString().lastIndexOf('\t');
				int nowTab=obj.toString().lastIndexOf('\t');
				
				//여기서 계층구조 분류. \t로 시작할 것이다...만약 \t로 시작하지 않는다면 새로운 트리가 생기는 것 , 현재는 고려 X
			
				if(lastTab==nowTab) { //새로 추가된 노드가 최근 노드와의 같은 계층
					obj.setParent(last.getParent());
					last.setSibling(obj);
				} 	
				
				else if(nowTab-lastTab==1) { //자식노드 추가
					obj.setParent(last);
					last.setChild(obj);
					
				}
				
				else { //last가 마지막 자식, 새로 추가된 녀석은 ... last보다 높은 계층
					while(true) {
						last=last.getParent();
						if(last.toString().lastIndexOf('\t')==obj.toString().lastIndexOf('\t')) {
							last.setSibling(obj);
							break;
						}
						
					}
					
				}
				k++;
				last=obj;
				System.out.println(k);
			}
			
		}
		
	}
	
	void print() {
		int H=0;
		Data k=start;
		System.out.println(H+" "+k.toString());
		while(true) {
			
			if(k.getChild()!=null) {
				k=k.getChild();
				H++;
				System.out.println(H+" "+k.toString());
			}
			
			else if(k.getSibling()!=null) {
				k=k.getSibling();
				System.out.println(H+" "+k.toString());
			}
			
			else {
				if(k==last) {
					break;
				}
				while(true) {
					k=k.getParent();
					H--;
					if(k.getSibling()!=null) {
						k=k.getSibling();
						System.out.println(H+" "+k.toString());
						break;
					}
				}
			}
			
		}
	}
	
	void AddLabel(JPanel Panel) {
		
		Data k=start;

		Panel.add(Make2Label(k.toString()));
		
		while(true) {
			
			if(k.getChild()!=null) {
				k=k.getChild();
				Panel.add(Make2Label(k.toString()));
			}
			
			else if(k.getSibling()!=null) {
				k=k.getSibling();
				Panel.add(Make2Label(k.toString()));
			}
			
			else {
				if(k==last) {
					break;
				}
				while(true) {
					k=k.getParent();
					if(k.getSibling()!=null) {
						k=k.getSibling();
						Panel.add(Make2Label(k.toString()));
						break;
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