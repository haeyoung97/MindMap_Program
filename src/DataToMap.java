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
		System.out.println("생서왕뇰!");
	}
	
	void getLabels2drawing(JLabel P,JLabel C) {System.out.println("평화?"); this.P=P; this.C=C;	}
	
//	JDrawing(JLabel P,JLabel C) {this.P=P; this.C=C;}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("평화!");
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
	private Data child;		//자식
	private Data sibling;	//형제
	private Data parent;	//부모
	private String value;	//실제값
	private int x, y, s;	// 좌표, 차원
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
	}
	
	boolean MakeTree(String [] member) {
		int k=1;
		int h=0;
		
		for(int i=0;i<member.length;i++) {
			if(start==null && member[0].charAt(0)!='\t') { //첫 성분이 루트 (\t으로 시작 안한다.)
				start=new Data(member[0]);
				start.setHeight(0);
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
								break;
							}
						}
					}
					k++;
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
		Panel.add(rootLabel);
		
		setRootX(Panel);
		setRootY(Panel);
		k.setX(getRootX());
		k.setY(getRootY());
		k.setS(0);
		
		rootLabel.setLocation(k.getX(), k.getY());
		System.out.println("##### root의 x : " + k.getX() + " y : " + k.getY());
		System.out.println("##### test의 x : " + 600*(totalH+1)/2 + " y : " + 400*(totalH+1)/2);
		
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
		System.out.println("전달받은 값 : " + x + "__" + y + "__" + s + "____total__" + totalH);
//		if(k == last){
//			JLabel childLabel;
//			childLabel = Make2Label(last);
//			Panel.add(childLabel);/////////////////////라벨올리기
//			
//			childLabel.setLocation(10, 10);
//			return;
//		}
		if(k.getChild() != null) {  /// 자식은 1사분면에 그려진다
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
			Panel.add(childLabel);////////////////////////////////라벨올리기
			k.setX(x);
			k.setY(y);
			childLabel.setLocation(x, y);
			
//			if(rootLabel!=null || childLabel!=null) {
//				System.out.println(.getText()+" ______ "+childLabel.getText());
//				panel.getLabels2drawing(rootLabel,childLabel);}
			k.setS(1);
			ChildAddLabel(Panel, x, y, k, s);

		}
		else if(k.getSibling() != null){
			k = k.getSibling();
			if((getSiblingIndex(k) == 2 && (s == 1 )) || (getSiblingIndex(k) == 1 && s == 3)) { // 2사분면에 그리자
				x = x - 400*(1-k.getHeight()/100);
				s = 2;
			}
			else if((getSiblingIndex(k) == 3 && s == 2 ) || (getSiblingIndex(k) == 2 && (s == 3 || s == 4 || s == 2 ))) { // 3사분면에 그리자
				System.out.println("너냐!!!!");
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
			else if(getSiblingIndex(k) == 3 && (s == 1 || s == 2 || s == 3)) { // 4사분면에 그리자
				x = x + 400*(1-k.getHeight()/100);
				s = 4;
			}
			else if(getSiblingIndex(k) == 4) {
				x = x + 400*(1-k.getHeight()/100);
			}
			JLabel childLabel;
			childLabel = Make2Label(k);
			Panel.add(childLabel);
			k.setX(x);
			k.setY(y);
			childLabel.setLocation(x, y);
			k.setS(s);
			ChildAddLabel(Panel, x, y, k, s);
		}
		else{
			if(k == last) {
				System.out.println("last 끝");
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
					System.out.println("걸리나요오오오옹!!!");
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
				// k를 그리고 나서 함수 호출
				JLabel childLabel;
				childLabel = Make2Label(k);
				Panel.add(childLabel);
				k.setX(x);
				k.setY(y);
				k.setS(s);
				childLabel.setLocation(x, y);
				if(k == last) {
					 return;
				}
				ChildAddLabel(Panel, x, y, k, s);
			}
		}
	}
	

	void Default() {
		start=null;
		last=null;
		obj=null;
	}



}
