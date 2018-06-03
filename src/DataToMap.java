import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

class JDrawPanel extends JPanel{
	JLabel P,C;
	
	JDrawPanel(){
		super();
		System.out.println("�����մ�!");
	}
	
	void getLabels2drawing(JLabel P,JLabel C) {System.out.println("��ȭ?"); this.P=P; this.C=C;	}
	
//	JDrawing(JLabel P,JLabel C) {this.P=P; this.C=C;}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("��ȭ!");
		int	width,height,startAngle, arcAngle;

//		super.paintComponent(g);
		if(this.P==null||this.C==null) {
			return;
		}
		
		System.out.println("////"+P.getText());
		System.out.println(C.getText());
		width=P.getWidth()*2;
		height=P.getHeight()*2;
		
		int x,y;
		
		
		g.setColor(Color.RED);
			JLabel k=this.C;
			x=C.getLocation().x;
			y=C.getLocation().y;
			if(x<P.getLocation().x) {
				if(y<P.getLocation().y) {
					startAngle=0;
					arcAngle=90;
					x=x-200+C.getSize().width/2;
					y=y+C.getSize().height/2;
					
				}
				else {
					startAngle=90;
					arcAngle=90;
					y=y-200+C.getSize().height/2;	
					x=x+C.getSize().width/2;
				}
			}
			else {
				x=P.getLocation().x;
				y=P.getLocation().y;
				if(P.getLocation().y<y) {
					x=2*x-P.getLocation().x+P.getSize().width/2;
					y=y-height+P.getSize().height/2;
					
					startAngle=0;
					arcAngle=-90;
				}
				else {
					startAngle=180;
					arcAngle=90;
					x=P.getLocation().x+C.getSize().width/2;
					y=P.getLocation().y-200+C.getSize().height/2;
				}
			}
			
			g.drawArc(x, y,width,height,startAngle, arcAngle);
		
		//	public void DrawingLine(Graphics g) {
//		System.out.println("I'm drawing");
//		int	width,height,startAngle, arcAngle;
//		
//		width=200*2;
//		height=200*2;
//		
//		int x,y;
//		
//		super.paintComponent(g);
//		g.setColor(Color.RED);
//			JLabel k=this.C;
//			x=C.getLocation().x;
//			y=C.getLocation().y;
//			if(x<P.getLocation().x) {
//				if(y<P.getLocation().y) {
//					startAngle=0;
//					arcAngle=90;
//					x=x-200+C.getSize().width/2;
//					y=y+C.getSize().height/2;
//					
//				}
//				else {
//					startAngle=90;
//					arcAngle=90;
//					y=y-200+C.getSize().height/2;	
//					x=x+C.getSize().width/2;
//				}
//			}
//			else {
//				x=P.getLocation().x;
//				y=P.getLocation().y;
//				if(P.getLocation().y<y) {
//					x=2*x-P.getLocation().x+P.getSize().width/2;
//					y=y-height+P.getSize().height/2;
//					
//					startAngle=0;
//					arcAngle=-90;
//				}
//				else {
//					startAngle=180;
//					arcAngle=90;
//					x=P.getLocation().x+C.getSize().width/2;
//					y=P.getLocation().y-200+C.getSize().height/2;
//				}
//			}
//			
//			g.drawArc(x, y,width,height,startAngle, arcAngle);
	}
}

class Data{
	private Data child;		//�ڽ�
	private Data sibling;	//����
	private Data parent;	//�θ�
	private String value;	//������
	private int x, y;
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
	
	String getValue() { return value.trim(); }
	
	void setX(int x) { this.x = x; }
	void setY(int y) { this.y = y; }
	
	int getX() { return x; }
	int getY() { return y; }
	
}

class MakeToLabel extends Elements {
	private JPanel panel;
	private Color[] labelColor = {Color.MAGENTA, Color.ORANGE, Color.GREEN, Color.CYAN, Color.RED, Color.BLUE,  Color.PINK};
	Color getColor(int h) {return labelColor[h];}
//	private JLabelListener labelListen = new JLabelListener(panel);
	private JLabelListener labelListen;
	
//	new JDrawing(new JLabel("A"),new JLabel("B"));
	
	MakeToLabel(JDrawPanel panel){
		this.panel=panel;
		labelListen=new JLabelListener(panel);
	}
	

	JLabel Make2Label(Data k) {
		JLabel lb = new JLabel(k.toString(), SwingConstants.CENTER);
//		CompoundBorder comBd = new CompoundBorder(new LineBorder(getColor(k.getHeight()), 10, true), new EmptyBorder(5,10,5,10));
		lb.setBackground(getColor(k.getHeight()%7));
//		lb.setBorder(comBd);
//		lb.setBorder(new LineBorder(getColor(k.getHeight()), 5, true));
		lb.setBorder(new EmptyBorder(5,10,5,10));
		lb.setFont(basicFont);
//		lb.setSize(k.toString().length()*20, 35);
		lb.setSize(100, 35);
	    lb.setOpaque(true);
	    lb.addMouseListener(labelListen);
		lb.addMouseMotionListener(labelListen);
		return lb;
	}
}

class Tree extends MakeToLabel{
	Data start=null, last=null, obj=null;
	private int rootX, rootY;
	private int totalH;
	int i=0;
	JDrawPanel panel;
	
	JLabel rootLabel;
	
	Tree(JDrawPanel panel) {
		super(panel);
	}
	
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
						JOptionPane.showMessageDialog(null, "obj.toString().charAt(0)!='\\t'", "��Ʈ�� 2��", JOptionPane.WARNING_MESSAGE);
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
						setTotalH(obj.getHeight());
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
					JOptionPane.showMessageDialog(null, "���Ŀ� ���߾� �ۼ����ֽʽÿ�(�߸��� ����)", "NullPointerException", JOptionPane.WARNING_MESSAGE);
					this.Default();
					return false;
				}
			}
		}
		return true;
	}

	void print() {
		Data k=start;
		System.out.println(k.getHeight()+" "+start.toString() + "    " + getSiblingIndex(k));
		
		
		
		while(true) {
			if(k.getChild()!=null) {
				k=k.getChild();
				System.out.println(k.getHeight()+" "+k.toString()+ "    " + getSiblingIndex(k));
			}
			else if(k.getSibling()!=null) {
				k=k.getSibling();
				System.out.println(k.getHeight()+" "+k.toString()+ "    " + getSiblingIndex(k));
			}
			else {
				if(k==last) {
					break;
				}
				while(true) {
					k=k.getParent();
					if(k.getSibling()!=null) {
						k=k.getSibling();
						System.out.println(k.getHeight()+" "+k.toString()+ "    " + getSiblingIndex(k));
						break;
					}
				}
			}

		}
	}

	void setRootX(JPanel Panel){
		this.rootX = Panel.getSize().width/2-Panel.getComponent(0).getWidth()/2;
	}

	void setRootY(JPanel Panel){
		this.rootY = Panel.getSize().height/2-Panel.getComponent(0).getHeight()/2;
	}
	
	int getRootX(){ return this.rootX; }

	int getRootY(){	return this.rootY; }
	
	void setTotalH(int totalH) {
		if(this.totalH < totalH)
			this.totalH = totalH;
	}
	
	void AddLabel(JDrawPanel Panel) {
		Panel.setSize(new Dimension(600*totalH/2, 400*totalH/2));
		System.out.println(600*totalH/2 + "@@@@@@"+  400*totalH/2);
		Data k=start;
		rootLabel = Make2Label(k);
		Panel.add(rootLabel);
		
		setRootX(Panel);
		setRootY(Panel);
		k.setX(getRootX());
		k.setY(getRootY());
		
		rootLabel.setLocation(k.getX(), k.getY());
		System.out.println("181@@@@x : " + k.getX() + " y : " + k.getY());
		ChildAddLabel(Panel, k.getX(), k.getY(), k, 0);

	}
	
	int getSiblingIndex(Data nowK) {
		Data k = start;
		int cnt = 0;
		int height = nowK.getHeight();
		
		
		while(k.getHeight() != height) {
	
			k = k.getChild();
			if(k== null) { //////////////////////////////////�̺κп��� �ڲ� �̻��� �������� ���� �������� ������; Ȯ������ �̻������..
				System.out.println("sibling is null"); 		//a
				return cnt--;								// 	b
			}								
		}													//	c	
		if(k.getSibling() == null)							//	 	ee   //�̷��� �ǰų� �Ǵٸ� ��Ȳ������ �������°�.. 
			return cnt;
		cnt++;
		
		while(!k.getValue().equals(nowK.getValue())) {
			cnt++;
			k = k.getSibling();
			if(k.getSibling() == null) {
				break;	
			}
		}
		return cnt;
	}

	void ChildAddLabel(JDrawPanel Panel, int x, int y, Data k, int s){
		if(k == last){
			JLabel childLabel;
			childLabel = Make2Label(last);
			Panel.add(childLabel);/////////////////////�󺧿ø���
			
			childLabel.setLocation(10, 10);
			return;
		}
		if(k.getChild() != null) {  /// �ڽ��� 1��и鿡 �׷�����
			k = k.getChild();
			if(getSiblingIndex(k) == 1 && s == 3) {
				x -= x/2;
				y += y*3/2;
			}
			else if(getSiblingIndex(k) == 1 && s == 2) {
				x -= x/2;
				y -= y/2;
			}
			else if(getSiblingIndex(k) == 1 && s == 4) {
				x -= x/2;
				y += y*3/2;
			}
			else {
				x += x/2;
				y -= y/2;
			}
			JLabel childLabel;
			childLabel = Make2Label(k);
			Panel.add(childLabel);////////////////////////////////�󺧿ø���
			k.setX(x);
			k.setY(y);
			childLabel.setLocation(x, y);
			
//			if(rootLabel!=null || childLabel!=null) {
//				System.out.println(.getText()+" ______ "+childLabel.getText());
//				panel.getLabels2drawing(rootLabel,childLabel);}
//			
			
			System.out.println("242 #######@@@@x : " + x + " y : " + y);
			ChildAddLabel(Panel, x, y, k, 1);

		}
		else if(k.getSibling() != null){
			k = k.getSibling();
			if(getSiblingIndex(k) != 0) {
				if((getSiblingIndex(k) == 2 && (s == 1 || s == 2 )) || (getSiblingIndex(k) == 1 && s == 3)) { // 2��и鿡 �׸���
					x = x - Math.abs((x-getRootX()))*2;
//					y = y - Math.abs((getRootY(Panel)-y))*2/3;
					s = 2;
				}
				else if((getSiblingIndex(k) == 3 && s == 2 ) || (getSiblingIndex(k) == 2 && (s == 3 || s == 4 ))) { // 3��и鿡 �׸���
//					x = x - Math.abs((x-getRootX(Panel)))/2;
					y = y + Math.abs((y-getRootY()))*2;
					s = 3;
				}
				else if(getSiblingIndex(k) == 3 && (s == 1 || s == 2 || s == 3)) { // 4��и鿡 �׸���
					x = x + Math.abs((x-getRootX()))*2;
//					y = y + Math.abs((y-getRootY(Panel)))/2;
					s = 4;
				}
				
				JLabel childLabel;
				childLabel = Make2Label(k);
				Panel.add(childLabel);
				childLabel.setLocation(x, y);
				
				System.out.println("270 *********#######@@@@x : " + x + " y : " + y);
				ChildAddLabel(Panel, x, y, k, s);
			}
			else {  
				k = k.getParent();
				System.out.println("test getParent : " + k.toString());
				
				if(k.getSibling() != null){
					int siblingIndex = getSiblingIndex(k);
					k = k.getSibling();
					if(s == 1) {
						if(siblingIndex == 1) {
							x = x - Math.abs((x-getRootX()))*5/3;
							y = y + Math.abs((y-getRootY()))/3;
						}
						if(siblingIndex == 2) {
							x = x - Math.abs((x-getRootX()))*2;
							y = y + Math.abs((y-getRootY()))/3;
						}
						if(siblingIndex == 3) {
							x = x - Math.abs((x-getRootX()))*5/3;
							y = y - Math.abs((y-getRootY()))/2;
						}
						
					}
					else if(s == 2) {
						y = y + Math.abs((y-getRootY()))*2;
					}
					else if(s == 3) {
						x = x + Math.abs((x-getRootX()))*2;
					}
					else {
						return;
					}
					s++;
					// k�� �׸��� ���� �Լ� ȣ��
					JLabel childLabel;
					childLabel = Make2Label(k);
					Panel.add(childLabel);
					childLabel.setLocation(x, y);
					System.out.println("298 ++++++++*********#######@@@@x : " + x + " y : " + y);
					ChildAddLabel(Panel, x, y, k, s);
				}
				else
					k = k.getParent();
				
			}
		}
		else{
			k = k.getParent();
			System.out.println("test getParent : " + k.toString());
			
			if(k.getSibling() != null){
				int siblingIndex = getSiblingIndex(k);
				k = k.getSibling();
				if(s == 1) {
					if(siblingIndex == 1) {
						System.out.println("298 ++++++++*********#######@@@@x : " + x + " y : " + y);
						
						x = x - Math.abs((x-getRootX()))/3*5;
						y = y + Math.abs((y-getRootY()))/3;
					}
					if(siblingIndex == 2) {
						x = x - Math.abs((x-getRootX()))*2;
						y = y + Math.abs((y-getRootY()))/3;
					}
					if(siblingIndex == 3) {
						x = x - Math.abs((x-getRootX()))*5/3;
						y = y - Math.abs((y-getRootY()))/2;
					}
					
				}
				else if(s == 2) {
					y = y + Math.abs((y-getRootY()))*2;
				}
				else if(s == 3) {
					x = x + Math.abs((x-getRootX()))*2;
				}
				else {
					return;
				}
				s++;
				// k�� �׸��� ���� �Լ� ȣ��
				JLabel childLabel;
				childLabel = Make2Label(k);
				Panel.add(childLabel);
				childLabel.setLocation(x, y);
				System.out.println("331 /////////////++++++++*********#######@@@@x : " + x + " y : " + y);
				ChildAddLabel(Panel, x, y, k, s);
			}
			else
				k = k.getParent();
		}

	}
	
	
	
	
	
	
	

	void Default() {
		start=null;
		last=null;
		obj=null;
	}



}
