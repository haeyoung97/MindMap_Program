import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

class updateLine{
	private int offX,offY;
	private Point pointl, points;
	private ArrayList<Point> childpointl;
	private ArrayList<Point> childpoints;
	private Data parent, child;
	private JLabel Plabel, label;
	private int s;
	private ArrayList<Data> childs;
	
	updateLine(int offX,int offY, Point pointl, Point points){
		this.offX=offX;
		this.offY=offY;
		this.pointl=pointl;
		this.points=points;
	}
	
	void separate(int s, Point pointl, Point points,Data parent) {		
		this.s=s;
		if(parent.getHeight()%2==0) {
			switch(s) {
				case 1:
					pointl.setLocation(pointl.getX(),-offY+pointl.getY());
					points.setLocation(offX*2+points.getX(),offY*2+points.getY());
					break;
				case 2:
					pointl.setLocation(offX*2+pointl.getX(),offY+pointl.getY());
					points.setLocation(-offX*2+points.getX(),-offY*2+points.getY());
					break;
				case 3:
					pointl.setLocation(offX+pointl.getX(),pointl.getY());
					points.setLocation(-offX*2+points.getX(),offY*2+points.getY());
					break;
				case 4:
					points.setLocation(offX*2+points.getX(),-offY*2+points.getY());
					pointl.setLocation(-offX+pointl.getX(),offY*2+pointl.getY());

					break;
			}
		}
		else {
			switch(s) {
				case 4:
					pointl.setLocation(pointl.getX(),offY+pointl.getY());
					points.setLocation(offX*2+points.getX(),-offY*2+points.getY());
					System.out.println("1");

					break;
				case 2:
					pointl.setLocation(offX+pointl.getX(),offY*2+pointl.getY());
					points.setLocation(-offX*2+points.getX(),-offY*2+points.getY());
					System.out.println("2");
					break;
				case 3:
					pointl.setLocation(offX*2+pointl.getX(),-offY+pointl.getY());
					points.setLocation(-offX*2+points.getX(),offY*2+points.getY());
					System.out.println("3");
					break;
				case 1:
					pointl.setLocation(-offX+pointl.getX(),pointl.getY());
					points.setLocation(offX*2+points.getX(),offY*2+points.getY());
					System.out.println("4");						
					break;
			}
		}
	}
	
	
	void modifybyP(int h,int s,Point pointl,Point points) {
		if(h%2==1) {
			
			System.out.println("ㅏㅏㅏ\nㅏㅏㅏㅏ");
			switch(s) {
				case 1:
					pointl.setLocation(offX+pointl.getX(),pointl.getY());
					points.setLocation(-offX*2+points.getX(),offY*2+points.getY());          
					break;///OKOKOKOKOKO
				case 2:
					pointl.setLocation(pointl.getX(),-offY+pointl.getY());
					points.setLocation(offX*2+points.getX(),offY*2+points.getY());
					break;
				case 3:
					pointl.setLocation(-offX+pointl.getX(),offY*2+pointl.getY());
					points.setLocation(offX*2+points.getX(),-offY*2+points.getY());
					break;
				case 4:
					pointl.setLocation(offX*2+pointl.getX(),offY+pointl.getY());
					points.setLocation(-offX*2+points.getX(),-offY*2+points.getY());
					

					break;
			}
		}
		else {
			
			switch(s) {
				case 4:
					pointl.setLocation(pointl.getX(),pointl.getY());
					points.setLocation(points.getX(),points.getY());
					System.out.println("1");
					System.out.println("ㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹ");

					break;
				case 2:
					pointl.setLocation(pointl.getX(),pointl.getY());
					points.setLocation(points.getX(),points.getY());
					System.out.println("2");
					break;
				case 3:
					pointl.setLocation(pointl.getX(),pointl.getY());
					points.setLocation(points.getX(),points.getY());
					System.out.println("3");
					break;
				case 1:
					pointl.setLocation(pointl.getX(),pointl.getY());
					points.setLocation(points.getX(),points.getY());
					System.out.println("4");						
					break;
			}
		}
		
		
		
		
		
		
		
		
	}


	void modifychildren(ArrayList<Data> childs, ArrayList<Point> childLinesLc, ArrayList<Point> childLinesSz) {
		int h=childs.get(0).getParent().getHeight();
		System.out.println("이즈엠티?"+ childs.isEmpty());
		System.out.println(childs.size());
////		
////		
		for(int i=0;i<childs.size();i++) {
//			System.out.println(h+'\n'+childs.get(i).getValue()+" "+childpointl.get(i)+" "+childpoints.get(i));
//			System.out.println(h);
//			System.out.println(childs.get(i).getValue());
//			System.out.println(childLinesLc.get(i));
//			System.out.println(childLinesSz.get(i));
			modifybyP(h,childs.get(i).getS(),childLinesLc.get(i),childLinesSz.get(i));
//			
			
			
			
		}
//		
		
		
		
		
//		}
//		if(parent.getHeight()%2==0) {
//			switch(s) {
//				case 1:
//					pointl.setLocation(pointl.getX(),-offY+pointl.getY());
//					points.setLocation(offX*2+points.getX(),offY*2+points.getY());
//					break;
//				case 2:
//					pointl.setLocation(offX*2+pointl.getX(),offY+pointl.getY());
//					points.setLocation(-offX*2+points.getX(),-offY*2+points.getY());
//					break;
//				case 3:
//					pointl.setLocation(offX+pointl.getX(),pointl.getY());
//					points.setLocation(-offX*2+points.getX(),offY*2+points.getY());
//					break;
//				case 4:
//					pointl.setLocation(-offX+pointl.getX(),offY*2+pointl.getY());
//					points.setLocation(offX*2+points.getX(),-offY*2+points.getY());
//					
//
//					break;
//			}
//		}
//		/////////////////////////////////////////////////////////////////////////////////
//		
//		else {
//			switch(s) {
//				case 4:
//					pointl.setLocation(pointl.getX(),offY+pointl.getY());
//					points.setLocation(offX*2+points.getX(),-offY*2+points.getY());
//					System.out.println("1");
//
//					break;
//				case 2:
//					pointl.setLocation(offX+pointl.getX(),offY*2+pointl.getY());
//					points.setLocation(-offX*2+points.getX(),-offY*2+points.getY());
//					System.out.println("2");
//					break;
//				case 3:
//					pointl.setLocation(offX*2+pointl.getX(),-offY+pointl.getY());
//					points.setLocation(-offX*2+points.getX(),offY*2+points.getY());
//					System.out.println("3");
//					break;
//				case 1:
//					pointl.setLocation(-offX+pointl.getX(),pointl.getY());
//					points.setLocation(offX*2+points.getX(),offY*2+points.getY());
//					System.out.println("4");						
//					break;
//			}
//		}
//		
//	}
	
	
	
	}
}



class NewButtonListener implements ActionListener{
	JPanel drawNodePanel;
	Tree t;
	JTextArea textarea;
	
	NewButtonListener(JPanel drawNodePanel,JTextArea textarea){
		this.drawNodePanel=drawNodePanel;
		this.t=t;
		this.textarea=textarea;	
	}
	
	@Override
	public void actionPerformed(ActionEvent e2) {
		drawNodePanel.removeAll();
		textarea.setText("");
		((JDrawPanel) drawNodePanel).reset();
		drawNodePanel.setVisible(false);
		drawNodePanel.setVisible(true);
		textarea.setVisible(false);
		textarea.setVisible(true);
		
		System.out.println("*******new");
	}
}

//class ListenElements{
//	Tree tree;
//}

class ButtonListener extends Elements implements ActionListener { //버튼 이벤트
	private Object O;
	private Mindmap mindmapSection;
	private StringBuffer buffer; 						//text editor 값		-이 버튼이 실행될 때마다 새로운 객체 생기는 것이 찜찜함...
	private String tmp=new String();
//	private Tree tree=new Tree(mindmapSection.drawNodePanel); //TREE추가
	private Tree tree;
	private Bar b;
	private SaveButtonListener saveListener;
//	
//	private JPanel panel4line;
	
	ButtonListener(Object O, Mindmap mindmapSection) {
		this.O=O;
		this.mindmapSection=mindmapSection;	//연결 OK
		this.tree = new Tree(mindmapSection.drawNodePanel);
//		this.saveListener = saveListener;
}
	
	ButtonListener(Object O, Mindmap mindmapSection, Bar b, SaveButtonListener saveListener) {
			this.O=O;
			this.mindmapSection=mindmapSection;	//연결 OK
			this.tree = new Tree(mindmapSection.drawNodePanel);
			this.saveListener = saveListener;
			this.b = b;
	}
	
	void ApplyButtonFunc() {
		int i=0; 							//while문 카운트
		mindmapSection.drawNodePanel.removeAll();
//		mindmapSection.drawNodePanel.setBackground(arg0);
//		JDrawPanel tmpP=new JDrawPanel();
//		mindmapSection.drawNodePanel=tmpP;
		
		mindmapSection.drawNodePanel.reset();
		
		buffer=new StringBuffer(((JTextArea)O).getText());
	    tmp=buffer.toString();
		StringTokenizer st=new StringTokenizer(tmp,"\n");	//st에 문자열에서 개행+문자로 된 애들 기준으로 자르기.
		String [] member=new String[st.countTokens()];
		
		if(st.countTokens() == 0) {
			JOptionPane.showMessageDialog(null, "내용이 없습니다.", "Null", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		while(st.hasMoreTokens()) {							//member배열에 \n기준으로 값 저장. (\t유효)
			member[i]=st.nextToken();
			i++;
		}
		
		if(tree.MakeTree(member)) {
			tree.print();
			tree.AddLabel(mindmapSection.drawNodePanel);
	
//			mindmapSection.drawNodePanel.getLabels2drawing((JLabel)mindmapSection.drawNodePanel.getComponent(0), (JLabel)mindmapSection.drawNodePanel.getComponent(1));
//			mindmapSection.drawNodePanel.DrawingLine(g);
		
			///////////////////////선 그리기.../////////////////

			mindmapSection.drawNodePanel.setVisible(false);
			mindmapSection.drawNodePanel.setVisible(true);
			// Save 버튼
			JMenuItem menuItemSave = b.getMenuItem(2);
			JButton toolBtnSave = b.getToolButton(2);
			menuItemSave.removeActionListener(saveListener); 
			toolBtnSave.removeActionListener(saveListener); 
			// SaveAs 버튼
			JMenuItem menuItemSaveAs = b.getMenuItem(3);
			JButton toolBtnSaveAs = b.getToolButton(3);
			menuItemSaveAs.removeActionListener(saveListener); 
			toolBtnSaveAs.removeActionListener(saveListener); 
			System.out.println("testtesttest : " + tree.getStart().toString());
			saveListener = new SaveButtonListener(tree.getStart(), tree.getLast());
			menuItemSave.addActionListener(saveListener); 
			toolBtnSave.addActionListener(saveListener); 
			menuItemSaveAs.addActionListener(saveListener); 
			toolBtnSaveAs.addActionListener(saveListener); 
			tree.Default();
		}
			
	}
		
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("적용") || e.getActionCommand().equals("Apply")) { 		//Text Editor
			ApplyButtonFunc();
										
		}
		
//		else if(Btn.getText().equals("변경")) { //Attribute Editor 
//			JTextField[] value = new JTextField[6];
//			for(int i=1;i<=6;i++) {
//				value[i-1]=(JTextField)((JPanel)O).getComponent(i*2-1);
//				valueArray[i-1]=value[i-1].getText();
//				System.out.println(valueArray[i-1]); //0번째에는 현재 빈 공간. NAME부분.
//			}
//			
//			
//		}
		
	}
}


class JLabelListener extends MouseAdapter {
//	private JTextField[] valueArray = new JTextField[6];			//attribute editor 값	-이 버튼이 실행될 때마다 새로운 객체 생기는 것이 찜찜함...
//	private JTextField[] fields;			//attribute editor 값	-이 버튼이 실행될 때마다 새로운 객체 생기는 것이 찜찜함...
	private JLabel label,Plabel;
	private Data parent,child;
	private boolean isDragged;
	private JDrawPanel panel;
	private int x,y;
	private Point pointl,points;
	private Vector<Point> vl,vs;
	private int cnt,i,s,cnt4child;
	private ArrayList<Data> datas,childs;
	private ArrayList<Point> childLinesLc,childLinesSz;
	private int offX,offY;
	
	private JTextField[] fields;
	
	public JLabelListener(JDrawPanel panel) {
		this.panel=panel; 
		childs=new ArrayList<Data>();
		childLinesLc=new ArrayList<Point>();
		childLinesSz=new ArrayList<Point>();
		
//		System.out.println(panel.getName());
	}

	public void mousePressed(MouseEvent e) {
		datas=panel.getArray();
		Data tmpChild;
		label = (JLabel)e.getSource();
		child=null;
		parent=null;
		childLinesLc.clear();
		childLinesSz.clear();
		
		i=0;
		s=0;
		childs.clear();
		
//		panel.getArray().get(i).getParent().getValue()
		
		while(true) {
			if(label.getText()==((JLabel) panel.getComponent(i)).getText()) {
				if(i==0) {
					return;
				}
				cnt=i;
				child=datas.get(cnt);
				parent=child.getParent();
				
				cnt4child=cnt;
				
				for(int j=0;j<panel.getComponentCount();j++) {
//					System.out.println(parent.getValue());
					
					if(parent.toString()==((JLabel)panel.getComponent(j)).getText()){
						System.out.println("ㅌ"+((JLabel)panel.getComponent(j)).getText());
						Plabel=(JLabel)(panel.getComponent(j));
						break; //for for
					}				
				}
				
				s=panel.checkS(label.getX(),label.getY(),Plabel.getX(),Plabel.getY());
//				
//				if(label.getX()<Plabel.getX()) {
//					if(label.getY()<Plabel.getY()) {s=2;}
//					else {s=3;}
//				}
//				else {
//					if(label.getY()<Plabel.getY()) {s=4;}
//					else {s=1;}		
//				}
				break;
			}
			i++;
		}
		cnt--;
		
		System.out.println("값은 바로바로 "+cnt);
		vl=panel.getVlocation();
		vs=panel.getVsize();
		if(cnt>=0) {
			points=vs.get(cnt);
			pointl=vl.get(cnt);
//			System.out.println(v.get(cnt));
		}
		
		if(child.getChild()!=null) {
			//자식들 저장//
			tmpChild=child.getChild();
			childs.add(tmpChild);
			if(tmpChild!=null) {	
				while(true) {
	
					if(tmpChild.getSibling()==null) {
						break;
					}
					tmpChild=tmpChild.getSibling();
					
					childs.add(tmpChild);
					childLinesLc.add(vl.get(cnt4child));
					childLinesSz.add(vs.get(cnt4child));
					System.out.println("차일즈카운트 : "+cnt4child);
					System.out.println("차일드네임스 : "+tmpChild.getValue());
	
					cnt4child++;
				}
			}
			childLinesLc.add(vl.get(cnt4child));
			childLinesSz.add(vs.get(cnt4child));
			
			for(int i=0;i<childs.size();i++) {
				System.out.println("name + "+childs.get(i).getValue());
			}
			System.out.println(childs.size()+"호롤롤");

		}

		
		x = e.getX();
		y = e.getY();
//		int lbX = x+label.getWidth()/2; 	// width
//		int lbY = y+label.getHeight()/2; // height
//		
//		System.out.println("label의 위치 "+lb.getLocation().x+" "+lb.getLocation().y); //라벨의 왼쪽상단모서리 값!!!!
//		System.out.println("마우스 포인터의 위치 " + e.getX()+ " "+ e.getY()); //라벨 안에서 마우스 포인터의 위치
//		System.out.println("패널 위 마우스 포인터 위치 " + (lb.getLocation().x+e.getX())+ " "+(lb.getLocation().y+e.getY()));
//		
		
//		fields[0] = new JTextField(name);
//		fields[1] = new JTextField(x);
//		fields[2] = new JTextField(y);
//		fields[3] = new JTextField(lbX);
//		fields[4] = new JTextField(lbY);
//		fields[5] = new JTextField();
		
		
		isDragged = true;
		System.out.println("test name : " + label.getText() + " test X : " + x + " test Y : "+y);
//		System.out.println("label's loction of x "+lb.getLocation().x);
		
	}
	
	public void mouseReleased(MouseEvent e){//////////////////////////////////////////////
		//마우스 버튼이 릴리즈되면 드래그 모드 종료
		isDragged = false;
		cnt=0;
		System.out.println("FALSE");
		
	}
	
	public void mouseDragged(MouseEvent e){///////////////////////////////////////////////
		System.out.println("드래그...");
		if(isDragged && i!=0){
			offX=e.getX()-x;
			offY=e.getY()-y;

			updateLine ul=new updateLine(offX,offY,pointl,points);
			
			ul.separate(s, pointl, points,parent);
			if(childs.size()!=0) {
				ul.modifychildren(childs,childLinesLc,childLinesSz);
				
			}
		
//			if(parent.getHeight()%2==0) {
//				switch(s) {
//					case 1:
//						pointl.setLocation(pointl.getX(),-offY+pointl.getY());
//						points.setLocation(offX*2+points.getX(),offY*2+points.getY());
//						break;
//					case 2:
//						pointl.setLocation(offX*2+pointl.getX(),offY+pointl.getY());
//						points.setLocation(-offX*2+points.getX(),-offY*2+points.getY());
//						break;
//					case 3:
//						pointl.setLocation(offX+pointl.getX(),pointl.getY());
//						points.setLocation(-offX*2+points.getX(),offY*2+points.getY());
//						break;
//					case 4:
//						points.setLocation(offX*2+points.getX(),-offY*2+points.getY());
//						pointl.setLocation(-offX+pointl.getX(),offY*2+pointl.getY());
//	
//						break;
//				}
//			}
//			/////////////////////////////////////////////////////////////////////////////////
//			
//			else {
//				switch(s) {
//					case 4:
//						pointl.setLocation(pointl.getX(),offY+pointl.getY());
//						points.setLocation(offX*2+points.getX(),-offY*2+points.getY());
//						System.out.println("1");
//
//						break;
//					case 2:
//						pointl.setLocation(offX+pointl.getX(),offY*2+pointl.getY());
//						points.setLocation(-offX*2+points.getX(),-offY*2+points.getY());
//						System.out.println("2");
//						break;
//					case 3:
//						pointl.setLocation(offX*2+pointl.getX(),-offY+pointl.getY());
//						points.setLocation(-offX*2+points.getX(),offY*2+points.getY());
//						System.out.println("3");
//						break;
//					case 1:
//						pointl.setLocation(-offX+pointl.getX(),pointl.getY());
//						points.setLocation(offX*2+points.getX(),offY*2+points.getY());
//						System.out.println("4");						
//						break;
//				}
//			}
			

			

				vl.removeElementAt(cnt);
				vs.removeElementAt(cnt);
				
				vl.add(cnt, pointl);//
				vs.add(cnt,points);
				panel.repaint();
				
				
			
			
			
			label.setLocation(e.getX()+label.getX()-x,e.getY()+label.getY()-y);
			
			
			System.out.println("MOOOVE");
		}
	}
	
	public void mouseClicked(MouseEvent e) {
//		JLabel lb = (JLabel) e.getSource();
//		fields[0].setText(lb.toString());
//		fields[1].setText(""+lb.getX());
//		fields[2].setText(""+lb.getY());
//		fields[3].setText(""+lb.getWidth());
//		fields[4].setText(""+lb.getHeight());
//		fields[5].setText(lb.toString());
	}
}

//class Mouser extends MouseAdapter{
//
//	boolean isDragged;
//	int offX=0, offY=0;
//	JPanel panel;
//	Component []labels;
//	Component label;
//	
//	Mouser(JPanel drawNodePanel){
//		this.panel=drawNodePanel;
//		labels=panel.getComponents();
//	}
//	
//	public void mousePressed(MouseEvent me){/////////////////////////////////////////////////
//
//		System.out.println(panel.getComponentCount());
//		
//		for(int i=0;i<panel.getComponentCount();i++) {
//			if((me.getX()>panel.getComponent(i).getX() &&me.getX()<(panel.getComponent(i).getWidth()+panel.getComponent(i).getX()))&&(me.getY()>panel.getComponent(i).getY()&&me.getY()<(panel.getComponent(i).getHeight()+panel.getComponent(i).getY()))){
//
//				System.out.println("포함이오~~");
//				offX = me.getX() - panel.getComponent(i).getLocation().x;
//				offY = me.getY() - panel.getComponent(i).getLocation().y;
//				this.label=panel.getComponent(i);
//				isDragged = true;
//			}
//		System.out.println(me.getX()+" "+me.getY());
//		}
//	}
//
//	public void mouseReleased(MouseEvent me){//////////////////////////////////////////////
//		//마우스 버튼이 릴리즈되면 드래그 모드 종료
//		isDragged = false;
//		System.out.println("FALSE");
//	}
//	public void mouseDragged(MouseEvent me){///////////////////////////////////////////////
//		
//		if(isDragged){
//
//			label.setLocation(me.getX()-offX,me.getY()-offY);
//			panel.setVisible(false);
//			panel.setVisible(true);
//
//			System.out.println("MOOOVE");
//		}
//	}
//}