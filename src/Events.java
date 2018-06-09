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
	private boolean inSquare;
	updateLine(int offX,int offY, Point pointl, Point points){
		this.offX=offX;
		this.offY=offY;
		this.pointl=pointl;
		this.points=points;
	}
	
	void separate(int s, Point pointl, Point points,Data parent){		
		this.s=s;
		if(parent.getHeight()%2==0) {
			switch(s) {
				case 1:
					pointl.setLocation(pointl.getX(),-offY+pointl.getY());
					points.setLocation(offX*2+points.getX(),offY*2+points.getY());
					System.out.println("넘버포");
					break;					//-----이것이 4사분면..!!!!!!!!!!!!!!
				case 2:
					pointl.setLocation(offX*2+pointl.getX(),offY+pointl.getY());
					points.setLocation(-offX*2+points.getX(),-offY*2+points.getY());
					System.out.println("넘버투");
					break;
				case 3:
					pointl.setLocation(offX+pointl.getX(),pointl.getY());
					points.setLocation(-offX*2+points.getX(),offY*2+points.getY());
					System.out.println("넘버쓰리");
					break;					//-----얘가 1사분면인듯?
				case 4:
					pointl.setLocation(-offX+pointl.getX(),offY*2+pointl.getY());
					points.setLocation(offX*2+points.getX(),-offY*2+points.getY());
					System.out.println("넘버원");					
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
	
	void modifychildren(ArrayList<Data> childs, ArrayList<Point> childLinesLc, ArrayList<Point> childLinesSz) {
		int h=childs.get(0).getParent().getHeight();
		System.out.println("미쳤니........>>"+h+ " "+childs.size()+" "+childLinesLc.size()+" "+childLinesSz.size());
		for(int i=0;i<childs.size();i++) {
			modifybyP(h,childs.get(i).getS(),childLinesLc.get(i),childLinesSz.get(i));
		}	
	}
	
	void modifybyP(int h,int s,Point pointl,Point points) {
		if(h%2==1) {
			System.out.println("aaaaaaaaaaaㄹㄹㄹ");
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
			System.out.println("sdfsdfsdfㄹㄹ");
			switch(s) {
				case 1:
					pointl.setLocation(offX*2+pointl.getX(),-offY+pointl.getY());
					points.setLocation(-offX*2+points.getX(),offY*2+points.getY());
					System.out.println("----------------1");
					

					break;
				case 2:
					pointl.setLocation(-offX+pointl.getX(),pointl.getY());
					points.setLocation(offX*2+points.getX(),offY*2+points.getY());
					System.out.println("----------------2");
					break;
				case 3:
					pointl.setLocation(pointl.getX(),offY+pointl.getY());
					points.setLocation(offX*2+points.getX(),-offY*2+points.getY());
					System.out.println("----------------3");
					break;
				case 4:
					pointl.setLocation(offX+pointl.getX(),offY*2+pointl.getY());
					points.setLocation(-offX*2+points.getX(),-offY*2+points.getY());
					System.out.println("-----------------4");						
					break;
			}
		}		
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

class ButtonListener implements ActionListener { //버튼 이벤트
	private Object O;
	private Mindmap mindmapSection;
	private JPanel attributeFieldPane; 
	private StringBuffer buffer; 						//text editor 값		-이 버튼이 실행될 때마다 새로운 객체 생기는 것이 찜찜함...
	private String tmp=new String();
	private Tree tree;
	private Bar b;
	private SaveButtonListener saveListener;

	// 변경버튼 생성자
	ButtonListener(Object O, Mindmap mindmapSection, Bar b) {
		this.mindmapSection=mindmapSection;	//연결 OK
		this.b = b;
		this.attributeFieldPane = (JPanel) O;
		this.tree = new Tree(mindmapSection.drawNodePanel, attributeFieldPane);
	}
	
	// 적용버튼 생성자
	ButtonListener(JPanel attributeFieldPane, Object O, Mindmap mindmapSection, Bar b, SaveButtonListener saveListener) {
		this.O=O;
		this.mindmapSection=mindmapSection;	//연결 OK
		this.attributeFieldPane = attributeFieldPane;
		this.saveListener = saveListener;
		this.tree = new Tree(mindmapSection.drawNodePanel, attributeFieldPane);
		this.b = b;
	}
	
	
	Tree getTree() {
		return tree;
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
			JOptionPane.showMessageDialog(null, "내용이 없습니다.", "Null",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		while(st.hasMoreTokens()) {							//member배열에 \n기준으로 값 저장. (\t유효)
			member[i]=st.nextToken();
			i++;
		}
		
		mindmapSection.drawNodePanel.getArray().clear();//수정. 적용 시마다 data 클리어 해주기!
		tree.Default();
		
		if(tree.MakeTree(member)) {
			tree.print();
			tree.AddLabel(mindmapSection.drawNodePanel);
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
	private JLabel label,Plabel;
	private Data parent,child;
	private boolean isDragged;
	private JDrawPanel panel;
	private JPanel attributeFieldPane;
	private int x,y;
	private Point pointl,points;
	private Vector<Point> vl,vs;
	private int cnt,i,s,cnt4child;
	private ArrayList<Data> datas,childs;
	private ArrayList<Point> childLinesLc,childLinesSz;
	private int offX,offY;
	
	public JLabelListener(JDrawPanel panel, JPanel attributeFieldPane) {
		this.panel=panel; 
		this.attributeFieldPane = attributeFieldPane;
		childs=new ArrayList<Data>();
		childLinesLc=new ArrayList<Point>();
		childLinesSz=new ArrayList<Point>();
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
		x = e.getX();
		y = e.getY();
		vl=panel.getVlocation();
		vs=panel.getVsize();

		while(true) {
			if(label.getText()==((JLabel) panel.getComponent(i)).getText()) {
				if(i==0) {
					isDragged=true;
					child=datas.get(0);
					System.out.println("당신은 루트입니까 ? :"+child.getValue());//OKOK
					
					cnt4child=0;
					
					
					if(vl.size()!=0) {
						tmpChild=child.getChild();
						childs.add(tmpChild);
						childLinesLc.add(vl.get(cnt4child));
						childLinesSz.add(vs.get(cnt4child));
						while(tmpChild.getSibling()!=null) {
							tmpChild=tmpChild.getSibling();
								for(int k=0;k<panel.getComponentCount();k++) {									
									if(((JLabel)panel.getComponent(k)).getText()==tmpChild.toString()) {
										cnt4child=k-1;
										childs.add(tmpChild);
										childLinesLc.add(vl.get(cnt4child));
										childLinesSz.add(vs.get(cnt4child));
								}
							}
						}
						
					}

						
					return;/////////////
				}

				cnt=i;
				child=datas.get(cnt);
				parent=child.getParent();
				
				cnt4child=cnt;
				System.out.println("Parent ::::::::: "+parent.getValue());
				System.out.println("selected ::::::::: "+child.getValue());
				
				for(int j=0;j<panel.getComponentCount();j++) {
					
					if(parent.toString()==((JLabel)panel.getComponent(j)).getText()){
						System.out.println("ㅌ"+((JLabel)panel.getComponent(j)).getText());
						Plabel=(JLabel)(panel.getComponent(j));
						break; //for for
					}				
				}
				
				s=panel.checkS(label.getX(),label.getY(),Plabel.getX(),Plabel.getY());
				break;
			}
			i++;
		}
		cnt--;
		if(cnt>=0) {
			points=vs.get(cnt);
			pointl=vl.get(cnt);
		}
		
		if(child.getChild()!=null) {
			//자식들 저장//
			tmpChild=child.getChild();
				while(true) {
					if(tmpChild==null) {
						break;
					}
//					
					for(int k=0;k<panel.getComponentCount();k++) {									
						if(((JLabel)panel.getComponent(k)).getText()==tmpChild.toString()) {
							cnt4child=k-1;
							childs.add(tmpChild);
							childLinesLc.add(vl.get(cnt4child));
							childLinesSz.add(vs.get(cnt4child));
						}
					}
					tmpChild=tmpChild.getSibling();
						
			}
		}
		x = e.getX();
		y = e.getY();
		
		isDragged = true;
		System.out.println("test name : " + label.getText() + " test X : " + x + " test Y : "+y);
		
	}
	
	public void mouseReleased(MouseEvent e){//////////////////////////////////////////////
		//마우스 버튼이 릴리즈되면 드래그 모드 종료
		isDragged = false;
		cnt=0;
		child.setX(label.getX());
		child.setY(label.getY());
		System.out.println("FALSE");
		
	}
	
	

 	
 	public void mouseDragged(MouseEvent e){///////////////////////////////////////////////
 		
 		if(isDragged){
 			
 			int tmplabelX=label.getX();
 			int tmplabelY=label.getY();
 			
 			
			System.out.println("드래그...");
			offX=e.getX()-x;
			offY=e.getY()-y;
//			
			
			
			
			
			//***********크기조절은 이런 느낌?**************//
//			if(x<label.getWidth()/15 || label.getWidth()-label.getWidth()/15<x) {
//				System.out.println("크기조절?");
//				System.out.println(x);
//				System.out.println(label.getX());
//				label.setSize(-offX+label.getWidth(), label.getHeight());
//				label.setLocation(label.getX()-Math.abs(offX),label.getY());
//			}
			//***********크기조절은 이런 느낌?**************//
			
			
			
			
			
			label.setLocation(offX+label.getX(),offY+label.getY());
			
			checkArea(label, child, parent, childs); //여기서 라벨 위치 조정.
			
 			//다음 라벨의 오프셋에 따른 선 조정
			
			offX=label.getX()-tmplabelX;
			offY=label.getY()-tmplabelY;
			
			updateLine ul=new updateLine(offX,offY,pointl,points);
 			if(i!=0) {
 				ul.separate(s, pointl, points,parent);
 				
 				vl.removeElementAt(cnt);
 				vs.removeElementAt(cnt);
 				vl.add(cnt,pointl);//
 				vs.add(cnt,points);
 				
 				System.out.println("MOOOVE");
 			} 	
 			
 			if(childs.size()!=0) {
 				ul.modifychildren(childs,childLinesLc,childLinesSz);
 			}
 		}
 		System.out.println("어딨니 !!!!!!!!!    "+label.getLocation());
 		
 		panel.repaint();
 	}
	
 	private void checkArea(JLabel label, Data child, Data parent, ArrayList<Data> childs) {
		// TODO Auto-generated method stub

 		int childX=child.getLabel().getX();
 		int childY=child.getLabel().getY();
 		int s=child.getS();
 		Point tmpPoint;
 		
 		Point [] limit = new Point [4];
 		int [] finalLimit = new int [4];
// 		
 		
 		if(child.getParent()!=null) {

 	 		int parentX=parent.getLabel().getX();
 	 		int parentY=parent.getLabel().getY();
 	 		switch(s) {
			case 1:		//노드기준 3사분면에 위치
				limit[2]=new Point(parentX+parent.getLabel().getWidth(),parentY);
				System.out.println("3사분면??????????????????");
				
				
			break;
			
			case 2:		//노드기준 4사분면에 위치
				limit[3]=new Point(parentX,parentY);
				System.out.println("4사분면?????????????????");
								
		 	break;
		 	
		 	case 3:		//노드기준 1사분면에 위치
				limit[0]=new Point(parentX,parentY+parent.getLabel().getHeight());
				System.out.println("1사분면??????????????/");
				
		 	break;
		 	
		 	case 4:		//노드기준 2사분면에 위치
				limit[1]=new Point(parentX+parent.getLabel().getWidth(),parentY+parent.getLabel().getHeight());
				System.out.println("2사분면????????????");
				
		 	break;
 		}
 			
 		}
 		
 		/////부모기준/////
 		
 		
 		for(int i=0;i<childs.size();i++) {
 			JLabel tmpLabel=childs.get(i).getLabel();
 			tmpPoint=childs.get(i).getLabel().getLocation();
 			limit[childs.get(i).getS()-1]=childs.get(i).getLabel().getLocation();
 			if(childs.get(i).getS()-1==0) {
 				limit[childs.get(i).getS()-1].setLocation(tmpPoint.x,tmpPoint.y+tmpLabel.getHeight());
 			}
 			if(childs.get(i).getS()-1==1) {
 				limit[childs.get(i).getS()-1].setLocation(tmpPoint.x+tmpLabel.getWidth(),tmpPoint.y+childs.get(i).getLabel().getHeight());
 			}
 			if(childs.get(i).getS()-1==2) {
 				System.out.println("여긴가??????????????/");
 				limit[childs.get(i).getS()-1].setLocation(tmpPoint.x+tmpLabel.getWidth(),tmpPoint.y);
 				System.out.println(tmpPoint.x);
 				System.out.println(limit[childs.get(i).getS()-1].x);	
 			}
 		}
 		
 		if(limit[0]==null) {
 			limit[0]=new Point(panel.getWidth(),0);
// 			System.out.println(panel.getName());
 		}
 		if(limit[1]==null) { 
 			limit[1]=new Point(0,0);
 		}
 		if(limit[2]==null) {
 			limit[2]=new Point(0,panel.getHeight());
 		}
 		if(limit[3]==null) {
 			limit[3]=new Point(panel.getWidth(),panel.getHeight());
 		}
 		
 		finalLimit[0]=Math.min(limit[0].x, limit[3].x);
 		finalLimit[1]=Math.max(limit[1].x, limit[2].x);
 		System.out.println("너희 이상해 : "+limit[1].x+"__"+limit[2].x);
 		finalLimit[2]=Math.min(limit[2].y, limit[3].y);
 		finalLimit[3]=Math.max(limit[0].y, limit[1].y);
	 	
	 	
	 	///////////////////////////////////////////////제한조건생성///////////////////////////////////////////////
	 	
	 	//1.
 		if(childX<finalLimit[1]) {
 			label.setLocation(finalLimit[1],label.getY());
 		}
 		else if(childX>finalLimit[0]-child.getLabel().getWidth()) {
 			label.setLocation(finalLimit[0]-label.getWidth(),label.getY());
 			
 		}
 		if(childY<finalLimit[3]) {
 			label.setLocation(label.getX(),finalLimit[3]);
 		}
 		else if(childY>finalLimit[2]-label.getHeight()) {
 			label.setLocation(label.getX(),finalLimit[2]-label.getHeight());
 		}

 	}
	
	public void mouseClicked(MouseEvent e) {
		JLabel lb = (JLabel)e.getSource();
		((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(1)).setText(lb.getText());
		((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(3)).setText(""+lb.getX());
		((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(5)).setText(""+lb.getY());
		((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(7)).setText(""+lb.getWidth());
		((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(9)).setText(""+lb.getHeight());
		((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(11)).setText("Color");
		System.out.println("적용버튼 생성자 : __________");
		
	}
}
