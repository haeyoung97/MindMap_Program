import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
//import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
//import javax.swing.border.LineBorder;

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
	
	String getValue() { return value.trim(); }
}

class MakeToLabel extends Elements {
	private JPanel panel;
	private Color[] labelColor = {Color.MAGENTA, Color.ORANGE, Color.GREEN, Color.CYAN, Color.RED, Color.BLUE,  Color.PINK};
	Color getColor(int h) {return labelColor[h];}
//	private JLabelListener labelListen = new JLabelListener(panel);
	private JLabelListener labelListen;
	
	MakeToLabel(JPanel panel){
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
	    lb.setOpaque(true);
	    lb.addMouseListener(labelListen);
		lb.addMouseMotionListener(labelListen);
		return lb;
	}
}

class Tree extends MakeToLabel{
	Tree(JPanel panel) {
		super(panel);
		// TODO Auto-generated constructor stub
	}

	Data start=null, last=null, obj=null;
	private int rootX, rootY;
	int i=0;
	JPanel panel;
	


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
						JOptionPane.showMessageDialog(null, "obj.toString().charAt(0)!='\\t'", "���� �޽��� ����", JOptionPane.WARNING_MESSAGE);
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
		System.out.println(k.getHeight()+" "+start.toString());
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

	void setRootX(JPanel Panel){
		this.rootX = Panel.getSize().width/2-Panel.getComponent(0).getWidth()/2;
	}

	void setRootY(JPanel Panel){
		this.rootY = Panel.getSize().height/2-Panel.getComponent(0).getHeight()/2;
	}
	
	int getRootX(){
		return this.rootX;
	}

	int getRootY(){
		return this.rootY;
	}
	
	void AddLabel(JPanel Panel) {

		Data k=start;
		
		JLabel rootLabel;
		rootLabel = Make2Label(k);
		Panel.add(rootLabel);

		rootLabel.setSize(k.toString().length()*20, 30);
		
		setRootX(Panel);
		setRootY(Panel);
		int x = getRootX();
		int y = getRootY();

		rootLabel.setLocation(x, y);
		System.out.println("181@@@@x : " + x + " y : " + y);
		ChildAddLabel(Panel, x, y, k, 0);

	}
	
	int getSiblingIndex(Data nowK) {
		Data k = start;
		int cnt = 0;
		int height = nowK.getHeight();
		
		while(k.getHeight() != height) {
			k = k.getChild();
		}
		
		while(k.getValue() != nowK.getValue()) {
			cnt++;
			if(k.getSibling() != null) {
				k = k.getSibling();				
			}
			else
				break;
		}
		return cnt;
	}

	void ChildAddLabel(JPanel Panel, int x, int y, Data k, int s){
		if(k == last){
			JLabel childLabel;
			childLabel = Make2Label(last);
			Panel.add(childLabel);
			childLabel.setSize(k.toString().length()*20, 30);

			childLabel.setLocation(10, 10);
			return;
		}
		else if(k.getChild() != null) {  /// �ڽ��� 1��и鿡 �׷�����
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
			Panel.add(childLabel);
			childLabel.setSize(k.toString().length()*20, 30);

			childLabel.setLocation(x, y);
			
			System.out.println("242 #######@@@@x : " + x + " y : " + y);
			ChildAddLabel(Panel, x, y, k, 1);
		}
		else if(k.getSibling() != null){
			k = k.getSibling();
			if(getSiblingIndex(k) != 0) {
				if((getSiblingIndex(k) == 2 && (s == 1 || s == 2 )) || (getSiblingIndex(k) == 1 && s == 3)) { // 2��и鿡 �׸���
					x = x - Math.abs((x-getRootX()))*2/3;
//					y = y - Math.abs((getRootY(Panel)-y))*2/3;
					s = 2;
				}
				else if((getSiblingIndex(k) == 3 && s == 2 ) || (getSiblingIndex(k) == 2 && (s == 3 || s == 4 ))) { // 3��и鿡 �׸���
//					x = x - Math.abs((x-getRootX(Panel)))/2;
					y = y + Math.abs((y-getRootY()))*2/3;
					s = 3;
				}
				else if(getSiblingIndex(k) == 3 && (s == 1 || s == 2 || s == 3)) { // 4��и鿡 �׸���
					x = x + Math.abs((x-getRootX()))*2/3;
//					y = y + Math.abs((y-getRootY(Panel)))/2;
					s = 4;
				}
				
				JLabel childLabel;
				childLabel = Make2Label(k);
				Panel.add(childLabel);
				childLabel.setSize(k.toString().length()*20, 30);
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
					childLabel.setSize(k.toString().length()*20, 30);
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
				childLabel.setSize(k.toString().length()*20, 30);
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
