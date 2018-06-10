import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
	private ArrayList<Data> datas;
	private ArrayList<Integer> group4s=new ArrayList<Integer>();
	
	JDrawPanel(){
		super();
		System.out.println("생서왕뇰!");
		datas=new ArrayList<Data>();
		System.out.println(datas.isEmpty());
	}
	
	int checkS(int x,int y, int px, int py) {
		int s;
		
		if(x<px) {
			if(y<py) {s=2;}
			else {s=3;}
		}
		else {
			if(y<py) {s=4;}
			else {s=1;}		
		}
		
		return s;
	}
	
	
	void reset() {vlocation=new Vector<Point>(); vsize=new Vector<Point>(); vangle=new Vector<Point>();}
	Vector<Point> getVlocation() {return vlocation;}
	Vector<Point> getVsize() {return vsize;}
	ArrayList<Integer> getSgroup() {return group4s;}
	
	void getLabels2drawing(Data P,Data C) {
		this.P=P; this.C=C;	

		int	width,height,startAngle, arcAngle,Fx,Fy;
		
		if(this.P==null||this.C==null) {
			return;
		}
		
		int px=P.getX();
		int py=P.getY();		
		int x=C.getX();
		int y=C.getY();
		int pL=P.getLabel().getWidth();
		int pH=P.getLabel().getHeight();
		int cL=C.getLabel().getWidth();
		int cH=C.getLabel().getHeight();
		
		if(P.getHeight()%2==0) {		
			if(x<px) {
				if(y<py) {
					startAngle=0;
					arcAngle=90;
					width=(px+pL/2-(x+cL))*2;
					height=(py-(y+cH/2))*2;
					Fx=px+pL/2-width;
					Fy=y+cH/2;
					System.out.println("그림2 "+P.getValue()+"의 자식: "+C.getValue());
				}
				else {
					startAngle=90;
					arcAngle=90;
					width=(px-x-cL/2)*2;
					height=(y-(py+pH/2))*2;
					Fx=x+cL/2;
					Fy=py+pH/2;
					System.out.println("그림3 "+P.getValue()+"의 자식: "+C.getValue());
				}
			}
			else {
				if(py<y) {
					startAngle=180;
					arcAngle=90;
					width=(x-px-pL/2)*2;
					height=(y+cH/2-py-pH)*2;
					Fx=px+pL/2;
					Fy=y+cH/2-height;
					System.out.println("그림4 "+P.getValue()+"의 자식: "+C.getValue());
				}
				else {
					startAngle=270;
					arcAngle=90;
					width=(x+cL/2-px-pL)*2;
					height=(py+pH/2-y-cH)*2;
					Fx=x+cL/2-width;
					Fy=py+pH/2-height;
					System.out.println("그림1 "+P.getValue()+"의 자식: "+C.getValue());
				}
			}		
		}
		else { //P의 계층 % 2==1
			if(x<px) {
				if(y<py) {
					startAngle=180;
					arcAngle=90;
					width=(px-(x+cL/2))*2;
					height=(py-(y+cH/2))*2;
					Fx=x+cL/2;
					Fy=py+pH/2-height;
					System.out.println("그림2 "+P.getValue()+"의 자식: "+C.getValue());
				}
				else {
					startAngle=270;
					arcAngle=90;
					width=(px+pL/2-(x+cL))*2;
					height=(y+cH/2-(py+pH))*2;
					Fx=px+pL/2-width;
					Fy=y+cH/2-height;
					System.out.println("그림3 "+P.getValue()+"의 자식: "+C.getValue());
				}
			}
			else {
				if(py<y) {
					startAngle=0;
					arcAngle=90;
					width=(x+cL/2-(px+pL))*2;
					height=(y-(py+pH/2))*2;
					Fx=x+cL/2-width;
					Fy=py+pH/2;
					System.out.println("그림4 "+P.getValue()+"의 자식: "+C.getValue());
				}
				else {
					startAngle=90;
					arcAngle=90;
					width=(x-(px+pL/2))*2;
					height=(py-(y+cH/2))*2;
					Fx=px+pL/2;
					Fy=y+cH/2;
					System.out.println("그림1 "+P.getValue()+"의 자식: "+C.getValue());
				}
			}
		}
		location.setLocation(Fx,Fy);		
		angle.setLocation(startAngle,arcAngle);
		size.setLocation(width, height);
		vlocation.add(location.getLocation());
		vsize.add(size.getLocation());
		vangle.add(angle.getLocation());
		
		for(int i=0; i<vlocation.size(); i++) {
			 Point s = vlocation.elementAt(i);
			 Point e = vsize.elementAt(i);
			 Point a= vangle.elementAt(i);
		}
	}
	
	ArrayList<Data> getArray(){return datas;}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
//		System.out.println("//***?"+vlocation.size());
		
		for(int i=0; i<vlocation.size(); i++) {
			 Point s = vlocation.elementAt(i);
			 Point e = vsize.elementAt(i);
			 Point a= vangle.elementAt(i);
//			 System.out.println((int)s.getX()+ " "+(int)s.getY()+ " "+(int)e.getX()+ " "+(int)e.getY()+" "+(int)a.getX()+" "+ (int)a.getY());
			 g.drawArc((int)s.getX(), (int)s.getY(),(int)e.getX(),(int)e.getY(),(int)a.getX(), (int)a.getY());
		}
	}
}

class Data{
	private Data child;		//자식
	private Data sibling;	//형제
	private Data parent;	//부모
	private String value;	//실제값
	private int x, y, s;	// 좌표, 차원
	private int h, nodeH, nodeW;
	private int r,g,b; 		// color RGB
	private String colorRGB;// color 문자열로 표현
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
	String getStrHeight() {return "" + this.h;}
	
	String getValue() { return value.trim(); }
	
	void setX(int x) { this.x = x; }
	void setY(int y) { this.y = y; }
	
	int getX() { return x; }
	int getY() { return y; }
	
	String getStrX() { return ""+x; }
	String getStrY() { return ""+y; }
	
	void setH(int nodeH) { this.nodeH = nodeH; }
	void setW(int nodeW) { this.nodeW = nodeW; }
	
	int getH() { return nodeH; }
	int getW() { return nodeW; }
	
	String getStrH() { return ""+nodeH; }
	String getStrW() { return ""+nodeW; }
	
	void setColorR(int r) { this.r = r; }
	void setColorG(int g) { this.g = g; }
	void setColorB(int b) { this.b = b; }
	void setColorStrRGB() { 
		String colorR, colorG, colorB;
		if(this.r<16) {
			colorR = "0"+Integer.toHexString(this.r);
		}
		else {
			colorR = Integer.toHexString(this.r);
		}
		if(this.g<16) {
			colorG = "0"+Integer.toHexString(this.g);
		}
		else {
			colorG = Integer.toHexString(this.g);
		}
		if(this.b<16) {
			colorB = "0"+Integer.toHexString(this.b);
		}
		else {
			colorB = Integer.toHexString(this.b);
		}
		this.colorRGB = colorR+colorG+colorB;	
	}
	
	int getColorR() { return r; }
	int getColorG() { return g; }
	int getColorB() { return b; }
	String getColorStrRGB() { return colorRGB; }
	
//	void setColor(int nodeH) { this.nodeH = nodeH; }
//	String getStrH() { return ""+nodeH; }
	
	void setS(int s) { this.s = s; }
	
	int getS() { return s; }
	String getStrS() { return ""+s; }
}

class MakeToLabel extends Elements {
	private JPanel panel;
	private Color[] labelColor = {Color.MAGENTA, Color.ORANGE, Color.GREEN, Color.CYAN, Color.RED, Color.BLUE,  Color.PINK};
	Color getColor(int h) {return labelColor[h];}
	private JLabelListener labelListen;
	
	MakeToLabel(JDrawPanel panel, JPanel attributeFieldPane){
		this.panel=panel;
		labelListen=new JLabelListener(panel, attributeFieldPane);
	}	

	JLabel Make2Label(Data k) {
		JLabel lb = new JLabel(k.toString(), SwingConstants.CENTER);
		lb.setBackground(getColor(k.getHeight()%7));
		lb.setBorder(new EmptyBorder(5,10,5,10));
		lb.setFont(basicFont);
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
	
	Tree(JDrawPanel panel, JPanel attributeFieldPane) {
		super(panel, attributeFieldPane);
		this.panel=panel;
	}
	
	boolean MakeTree(String [] member) {
		int k=1;
		int h=0;
		
		for(int i=0;i<member.length;i++) {
			if(start==null && member[0].charAt(0)!='\t') { //첫 성분이 루트 (\t으로 시작 안한다.)
				start=new Data(member[0]);
				start.setHeight(0);
				panel.getArray().add(start);
				last=start;
			}
			else {
				try {
					obj=new Data(member[i]);

					int lastTab=last.toString().lastIndexOf('\t');
					int nowTab=obj.toString().lastIndexOf('\t');

					//여기서 계층구조 분류. \t로 시작할 것이다...만약 \t로 시작하지 않는다면 새로운 트리가 생기는 것 , 현재는 고려 X
					if(obj.toString().charAt(0)!='\t') {
						JOptionPane.showMessageDialog(null, "obj.toString().charAt(0)!='\\t'", "루트가 2개", JOptionPane.WARNING_MESSAGE);
						this.Default();
						return false;
					}
					else if(lastTab==nowTab) { //새로 추가된 노드가 최근 노드와의 같은 계층
						obj.setParent(last.getParent());
						obj.setHeight(last.getHeight());
						last.setSibling(obj);
					}
					else if(nowTab-lastTab==1) { //자식노드 추가
						obj.setParent(last);
						obj.setHeight(last.getHeight()+1);
						setTotalH(obj.getHeight());
						last.setChild(obj);
					}
					else { //last가 마지막 자식, 새로 추가된 녀석은 ... last보다 높은 계층
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
					panel.getArray().add(obj);
					last=obj;
					System.out.println("make tree ;  " + k);
				}
				catch(NullPointerException e) {					//예외처리 : 트리 이상하게 적을 경우 생기는 NullPointerException
					JOptionPane.showMessageDialog(null, "형식에 맞추어 작성해주십시오(잘못된 계층)", "NullPointerException", JOptionPane.WARNING_MESSAGE);
					this.Default();
					return false;
				}
			}
		}
		return true;
	}
	
	public Data getStart() {
		return this.start;
	}
	
	public Data getLast() {
		return this.last;
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
		Color colorRGB = rootLabel.getBackground();
		k.setColorR(colorRGB.getRed());
		k.setColorG(colorRGB.getGreen());
		k.setColorB(colorRGB.getBlue());
		k.setColorStrRGB();
		System.out.println("RGB test : " + k.getColorR()+"  RGB test : " + k.getColorG()+"  RGB test : " + k.getColorB()+"  RGB test : " + k.getColorStrRGB());
		start.setLabel(rootLabel);
		Panel.add(rootLabel);
		
		setRootX(Panel);
		setRootY(Panel);
		k.setX(getRootX());
		k.setY(getRootY());
		k.setH(rootLabel.getHeight());
		k.setW(rootLabel.getWidth());
		k.setS(0);
		k.setLabel(rootLabel);/////////////////////////////////////////////////////////////////////////////
		
		rootLabel.setLocation(k.getX(), k.getY());
		System.out.println("##### root의 x : " + k.getX() + " y : " + k.getY());
		System.out.println("##### test의 x : " + 600*(totalH+1)/2 + " y : " + 400*(totalH+1)/2);
		
		if(k == last)
			return;
		ChildAddLabel(Panel, k);

	}
	
	private int getSiblingIndex(Data nowK) {
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
				if(k.getParent() == nowK.getParent()) {
					break;
				}
			}
		}
		
		if(k.getSibling() == null)	{
			return cnt;
		}	
		cnt++;
		
		while(k != nowK) {
			cnt++;
			k = k.getSibling();
			if(k.getSibling() == null) {
				break;	
			}
		}
		return cnt;
	}
	
	private Data showSibling(JPanel Panel, Data k) {
		int x, y, s;
		if(k.getParent() == start) {
			x = getRootX();
			y = getRootY();
			s = 0;
			k = k.getSibling();
		}
		else {
			System.out.println("test : " + k.getValue() + "____" + k.getSibling());
			x = k.getParent().getX();
			y = k.getParent().getY();
			s = k.getParent().getS();
			k = k.getSibling();		
		}
		if(getSiblingIndex(k) == 2) {
			x -= 400*(1-k.getParent().getHeight()/100)/(k.getHeight()+1);
			if(s == 0 || s == 1 || s == 2) {
				y -= 300*(1-k.getParent().getHeight()/100)/(k.getHeight()+1);
				k.setS(2);
			}
			else if(s == 4 || s == 3){
				y += 300*(1-k.getParent().getHeight()/100)/(k.getHeight()+1);
				k.setS(3);
			}
		}
		else if(getSiblingIndex(k) == 3) {
			y += 300*(1-k.getParent().getHeight()/100)/(k.getHeight()+1);
			if(s == 0 || s == 2 ) {
				x -= 400*(1-k.getParent().getHeight()/100)/(k.getHeight()+1);
				if(s == 2 || s == 0)
					k.setS(3);
				else
					k.setS(4);
			}
			else {
				x += 400*(1-k.getParent().getHeight()/100)/(k.getHeight()+1);
				k.setS(4);
			}
		}
		else { // getSiblingIndex(k) == 4
			x += 400*(1-k.getParent().getHeight()/100)/(k.getHeight()+1);
			y += 300*(1-k.getParent().getHeight()/100)/(k.getHeight()+1);
			k.setS(4);
		}
		JLabel childLabel;
		childLabel = Make2Label(k);
		Color colorRGB = childLabel.getBackground();
		k.setColorR(colorRGB.getRed());
		k.setColorG(colorRGB.getGreen());
		k.setColorB(colorRGB.getBlue());
		k.setColorStrRGB();
		System.out.println("RGB test : " + k.getColorR()+"  RGB test : " + k.getColorG()+"  RGB test : " + k.getColorB());
		Panel.add(childLabel);////////////////////////////////라벨올리기
		k.setX(x);
		k.setY(y);
		k.setH(childLabel.getHeight());
		k.setW(childLabel.getWidth());
		k.setLabel(childLabel);
		childLabel.setLocation(x, y);
		panel.getLabels2drawing(k.getParent(),k);
		return k;
	}

	private void ChildAddLabel(JPanel Panel, Data k){
		while(true) {
			if(k == last) {
				break;
			}
			else if(k.getChild() != null) {
				int x, y, s;
				k = k.getChild();
				if(k.getParent() == start) {
					x = getRootX();
					y = getRootY();
					s = 0;
				}
				else {
					x = k.getParent().getX();
					y = k.getParent().getY();
					s = k.getParent().getS();
				}
				if(s == 0 || s == 1 || s == 2 || s == 4 ) {
					x += 400*(1-k.getParent().getHeight()/100)/(k.getHeight()+1);
					y -= 300*(1-k.getParent().getHeight()/100)/(k.getHeight()+1);
					k.setS(1);
				}
				else {
					x -= 400*(1-k.getParent().getHeight()/100)/(k.getHeight()+1);
					y -= 300*(1-k.getParent().getHeight()/100)/(k.getHeight()+1);
					k.setS(2);
				}
				JLabel childLabel;
				childLabel = Make2Label(k);
				Color colorRGB = childLabel.getBackground();
				k.setColorR(colorRGB.getRed());
				k.setColorG(colorRGB.getGreen());
				k.setColorB(colorRGB.getBlue());
				k.setColorStrRGB();
				System.out.println("RGB test : " + k.getColorR()+"  RGB test : " + k.getColorG()+"  RGB test : " + k.getColorB());
				Panel.add(childLabel);////////////////////////////////라벨올리기
				k.setX(x);
				k.setY(y);
				k.setH(childLabel.getHeight());
				k.setW(childLabel.getWidth());
				k.setLabel(childLabel);
				childLabel.setLocation(x, y);
				
				panel.getLabels2drawing(k.getParent(),k);
				
			}
			else if(k.getSibling() != null) {
				k = showSibling(Panel, k);	
			}
			else {
				if(k == last)
					break;
				while(true) {
					k = k.getParent();
					if(k.getSibling() != null) {
						k = showSibling(Panel, k);
						if(k == last)
							return;
						break;
					}
					if(k == start) {
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
