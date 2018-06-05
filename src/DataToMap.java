import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

class JDrawPanel extends JPanel{
	private Data P,C;
	private Point location=new Point();
	private Point size=new Point();
	private Point angle=new Point();
	
	private Vector<Point> vlocation=new Vector<Point>();
	private Vector<Point> vsize=new Vector<Point>();
	private Vector<Point> vangle=new Vector<Point>();
	
	JDrawPanel(){
		super();
		System.out.println("�����մ�!");

	}
	
	void reset() {vlocation=new Vector<Point>(); vsize=new Vector<Point>(); vangle=new Vector<Point>();}
	
	void getLabels2drawing(Data P,Data C) {
		
		System.out.println("��ȭ?"); this.P=P; this.C=C;	
		System.out.println("��ȭ!");
		int	width,height,startAngle, arcAngle,Fx,Fy;
		if(this.P==null||this.C==null) {
			return;
		}
		
		int px=P.getX();
		int py=P.getY();		
		int x=C.getX();
		int y=C.getY();
		int L=P.getLabel().getWidth();
		int H=P.getLabel().getHeight();//����//
		
		
			if(x<px) {
				if(y<py) {
					startAngle=0;
					arcAngle=90;
				
					width=(px-x)*2;
					height=(py-y)*2;
					Fx=px-width;
					Fy=py-height/2;
					
					System.out.println("�׸�2 "+P.getValue()+"�� �ڽ�: "+C.getValue());
				}
				else {
					startAngle=90;
					arcAngle=90;
					width=(px-x)*2;
					height=(y-py)*2;
					Fx=x;
					Fy=py;
					
					System.out.println("�׸�3 "+P.getValue()+"�� �ڽ�: "+C.getValue());
				}
				
			}
			else {
				
				if(py<y) {
					startAngle=180;
					arcAngle=90;
					width=(x-px)*2;
					height=(y-py)*2;
					Fx=px;
					Fy=py-height/2;
					
					System.out.println("�׸�4 "+P.getValue()+"�� �ڽ�: "+C.getValue());
				}
				else {
					startAngle=270;
					arcAngle=90;
					width=(x-px)*2+L;
					height=(py-y)*2-H;
					Fx=px-width/2;
					Fy=y-height/2;
					
					System.out.println("�׸�1 "+P.getValue()+"�� �ڽ�: "+C.getValue());
				}
	
			}
			
			System.out.println("�̻��ϴ� "+Fx+ " "+ startAngle);
			
			location.setLocation(Fx,Fy);
			System.out.println("��¥������ �ФФФ� "+Fx+ " "+ Fy);
			System.out.println("�� ������ ��?? "+location.getLocation());
			angle.setLocation(startAngle,arcAngle);
			size.setLocation(width, height);
			vlocation.add(location.getLocation());
			vsize.add(size.getLocation());
			vangle.add(angle.getLocation());
			
			for(int i=0; i<vlocation.size(); i++) {
				System.out.println("�������Ĥ�"+i);
				 Point s = vlocation.elementAt(i);
				 Point e = vsize.elementAt(i);
				 Point a= vangle.elementAt(i);
				 System.out.println((int)s.getX()+ " "+(int)s.getY()+ " "+(int)e.getX()+ " "+(int)e.getY()+" "+(int)a.getX()+" "+ (int)a.getY());
//				 g.drawArc((int)s.getX(), (int)s.getY(),(int)e.getX(),(int)e.getY(),(int)a.getX(), (int)a.getY());
			}
			
//			repaint();
		
		}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.setColor(Color.black);
		System.out.println("//***?"+vlocation.size());
		
		
		for(int i=0; i<vlocation.size(); i++) {
			 Point s = vlocation.elementAt(i);
			 Point e = vsize.elementAt(i);
			 Point a= vangle.elementAt(i);
			 System.out.println((int)s.getX()+ " "+(int)s.getY()+ " "+(int)e.getX()+ " "+(int)e.getY()+" "+(int)a.getX()+" "+ (int)a.getY());
			 g.drawArc((int)s.getX(), (int)s.getY(),(int)e.getX(),(int)e.getY(),(int)a.getX(), (int)a.getY());
		}
	}
}

class Data{
	private Data child;		//�ڽ�
	private Data sibling;	//����
	private Data parent;	//�θ�
	private String value;	//������
	private int x, y, s;	// ��ǥ, ����
	private int h;
	private JLabel label;

	public Data(String value) {this.value=value; child=null; sibling=null; parent=null;}

	public JLabel getLabel() {return label;}
	
	public void setLabel(JLabel label) {this.label=label;}

	
	
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
	
	void setS(int s) { this.s = s; }
	
	int getS() { return s; }
	
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
		this.panel=panel;
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
								obj.setParent(last.getParent());
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
		this.rootX = 600*(totalH+1)/2;
	}

	void setRootY(JPanel Panel){
		this.rootY = 400*(totalH+1)/2;
	}
	
	int getRootX(){ return this.rootX; }

	int getRootY(){	return this.rootY; }
	
	void setTotalH(int totalH) {
		if(this.totalH < totalH)
			this.totalH = totalH;
	}
	
	void AddLabel(JDrawPanel Panel) {
		Panel.setPreferredSize(new Dimension(600*(totalH+1), 400*(totalH+1)));
		
		Data k=start;
		rootLabel = Make2Label(k);
		start.setLabel(rootLabel);
		Panel.add(rootLabel);
		
		setRootX(Panel);
		setRootY(Panel);
		k.setX(getRootX());
		k.setY(getRootY());
		k.setS(0);
		
		rootLabel.setLocation(k.getX(), k.getY());
		System.out.println("##### root�� x : " + k.getX() + " y : " + k.getY());
		System.out.println("##### test�� x : " + 600*(totalH+1)/2 + " y : " + 400*(totalH+1)/2);
		
		if(k == last)
			return;
		ChildAddLabel(Panel, k.getX(), k.getY(), k, 0);

	}
	
	int getSiblingIndex(Data nowK) {
		Data k = start;
		int cnt = 0;
		int height = nowK.getHeight();
		
		if(nowK == start) {
			return 0;
		}
		
		while(true) {
			if(height == 1) {
				k = k.getChild();
				break;
			}
			if(k.getChild()!=null) {
				k=k.getChild();
			}
			else if(k.getSibling()!=null) {
				k=k.getSibling();
			}
			else {
				if(k==last) {
					break;
				}
				while(true) {
					k=k.getParent();
					if(k.getSibling()!=null) {
						k=k.getSibling();
						break;
					}
				}
			}
			if(k.getHeight() == nowK.getHeight()) {
				if(k.getParent().getValue().equals(nowK.getParent().getValue())) {
					break;
				}
			}
		}
		
		if(k.getSibling() == null)	{
			return cnt;
		}	
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
		
		
		System.out.println("���޹��� �� : " + x + "__" + y + "__" + s + "____total__" + totalH);
//		panel.getLabels2drawing(k.getParent(),k);
//		if(k == last){
//			JLabel childLabel;
//			childLabel = Make2Label(last);
//			Panel.add(childLabel);/////////////////////�󺧿ø���
//			
//			childLabel.setLocation(10, 10);
//			return;
//		}
		if(k.getChild() != null) {  /// �ڽ��� 1��и鿡 �׷�����
			k = k.getChild();
			if((getSiblingIndex(k) == 1 || getSiblingIndex(k) == 0) && k.getParent().getS() == 3) {
				System.out.println("123456789123456789"+k.getValue());
				x = k.getParent().getX();
				y = k.getParent().getY();
				x -= 400*(1-k.getHeight()/100)/2;
				y -= 300*(1-k.getHeight()/100)/2;
				s = 2;
			}
			else {
				x += 400*(1-k.getHeight()/100)/2;
				y -= 300*(1-k.getHeight()/100)/2;
				s = 1;
			}
			JLabel childLabel;
			childLabel = Make2Label(k);
			Panel.add(childLabel);////////////////////////////////�󺧿ø���
			k.setLabel(childLabel);
			k.setX(x);
			k.setY(y);
			childLabel.setLocation(x, y);			
			
			System.out.println(k.getParent().getValue()+ " "+ k.getValue());
//			panel.getLabels2drawing(k.getParent(),k);
			
//			if(rootLabel!=null || childLabel!=null) {
//				System.out.println(.getText()+" ______ "+childLabel.getText());
//				panel.getLabels2drawing(rootLabel,childLabel);}
			k.setS(1);
			ChildAddLabel(Panel, x, y, k, s);

		}
		else if(k.getSibling() != null){
			k = k.getSibling();
			if((getSiblingIndex(k) == 2 && (s == 1 )) || (getSiblingIndex(k) == 1 && s == 3)) { // 2��и鿡 �׸���
				x = x - 400*(1-k.getHeight()/100);
				s = 2;
			}
			else if((getSiblingIndex(k) == 3 && s == 2 ) || (getSiblingIndex(k) == 2 && (s == 3 || s == 4 || s == 2 ))) { // 3��и鿡 �׸���
				System.out.println("�ʳ�!!!!");
				y = y + 300*(1-k.getHeight()/100)*(totalH+1 - k.getHeight());
				if(getSiblingIndex(k) == 3) {
					if(s != 2) {
						x = x + 400*(1-k.getHeight()/100);
						s = 4;
					}
				}
				else
					s = 3;
			}
			else if(getSiblingIndex(k) == 3 && (s == 1 || s == 2 || s == 3)) { // 4��и鿡 �׸���
				x = x + 400*(1-k.getHeight()/100);
				s = 4;
			}
			else if(getSiblingIndex(k) == 4) {
				x = x + 400*(1-k.getHeight()/100);
			}
			JLabel childLabel;
			childLabel = Make2Label(k);
			Panel.add(childLabel);
			k.setLabel(childLabel);
			k.setX(x);
			k.setY(y);
			childLabel.setLocation(x, y);
			System.out.println(k.getParent().getValue()+ " "+ k.getValue());
			
//			panel.getLabels2drawing(k.getParent(),k);
			
			k.setS(s);
			ChildAddLabel(Panel, x, y, k, s);
			
		}
		else{
			if(k == last) {
				panel.repaint();
				System.out.println("last ��");
				return;
			}

			k = k.getParent();
			x = k.getX();
			y = k.getY();
			s = k.getS();
			
			System.out.println(k.getValue() +"_____"+getSiblingIndex(k.getSibling()) + "____" + x + "____" + y + "____" + s  );
			
			if(k.getSibling() != null){
				k = k.getSibling();
				if(getSiblingIndex(k) == 1 && (s == 0 || s == 2 || s == 1 || s == 4)) {
					x = x - 400*(1-k.getHeight()/100);
				}
				if((getSiblingIndex(k) == 2 || getSiblingIndex(k) == 3 ) && (s == 0 || s == 2 || s == 1 || s == 4)) {
					System.out.println("�ɸ������������!!!");
					if(getSiblingIndex(k) == 3) {
						y = y + 300*(1-k.getHeight()/100);
					}
					else
						x = x - 400*(1-k.getHeight()/100);
					
//					if(s == 4) {
//						s++;
//					}
				}
				if(getSiblingIndex(k) == 3 && (s == 0 || s == 3 || s == 4 || s == 1)) {
					x = x + 400*(1-k.getHeight()/100);
					if(s == 1) {
						s++;
					}
				}
				if(getSiblingIndex(k) == 4) {
					x = x + 400*(1-k.getHeight()/100);
				}
				s++;
				// k�� �׸��� ���� �Լ� ȣ��
				JLabel childLabel;
				childLabel = Make2Label(k);
				Panel.add(childLabel);
				k.setLabel(childLabel);
				k.setX(x);
				k.setY(y);
				k.setS(s);
				childLabel.setLocation(x, y);
				if(k == last) {
					 return;
				}
				
//				panel.getLabels2drawing(k.getParent(),k);
				
				
				ChildAddLabel(Panel, x, y, k, s);
			}
		}
		panel.getLabels2drawing(k.getParent(),k);
	}
	

	void Default() {
		start=null;
		last=null;
		obj=null;
	}



}
