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
	
	boolean separate(int s, Point pointl, Point points,Data parent){		
		this.s=s;
		Point tmppointl=pointl;
		Point tmppoints=points;
		
//		Point parentlocation=new Point(parent.getLabel().getX()+parent.getLabel().getWidth()/2,parent.getLabel().getY()+parent.getLabel().getHeight()/2);
		
		
		if(parent.getHeight()%2==0) {
			switch(s) {
				case 1:
					tmppointl.setLocation(pointl.getX(),-offY+pointl.getY());
					tmppoints.setLocation(offX*2+points.getX(),offY*2+points.getY());
					if(tmppoints.getX()<0) {
						System.out.println("므에에ㅔㄱ!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//						pointl.setLocation(tmppointl);
//						points.setLocation(points);
						
						return false;
					}
//					
					break;					//-----이것이 4사분면..!!!!!!!!!!!!!!
				case 2:
					tmppointl.setLocation(offX*2+pointl.getX(),offY+pointl.getY());
					tmppoints.setLocation(-offX*2+points.getX(),-offY*2+points.getY());
					break;
				case 3:
					tmppointl.setLocation(offX+pointl.getX(),pointl.getY());
					tmppoints.setLocation(-offX*2+points.getX(),offY*2+points.getY());
					break;					//-----얘가 1사분면인듯?
				case 4:
					tmppoints.setLocation(offX*2+points.getX(),-offY*2+points.getY());
					tmppointl.setLocation(-offX+pointl.getX(),offY*2+pointl.getY());
					break;
			}
		}
		else {
			switch(s) {
				case 4:
					tmppointl.setLocation(pointl.getX(),offY+pointl.getY());
					tmppoints.setLocation(offX*2+points.getX(),-offY*2+points.getY());
					System.out.println("1");
					break;
				case 2:
					tmppointl.setLocation(offX+pointl.getX(),offY*2+pointl.getY());
					tmppoints.setLocation(-offX*2+points.getX(),-offY*2+points.getY());
					System.out.println("2");
					break;
				case 3:
					tmppointl.setLocation(offX*2+pointl.getX(),-offY+pointl.getY());
					tmppoints.setLocation(-offX*2+points.getX(),offY*2+points.getY());
					System.out.println("3");
					break;
				case 1:
					tmppointl.setLocation(-offX+pointl.getX(),pointl.getY());
					tmppoints.setLocation(offX*2+points.getX(),offY*2+points.getY());
					System.out.println("4");						
					break;
			}
		}
		
		
//		//***
//		if(tmppointl.x<0 || tmppoints.y<0) {
//			pointl.setLocation(parentlocation.x-1,parentlocation.y-1);
//			System.out.println("넘어가져용..");
//		}
		///***
		
		
		points.setLocation(tmppoints);
		return true;
	}
	
	
	void modifybyP(int h,int s,Point pointl,Point points) {
		if(h%2==1) {
			System.out.println("aaaaaaaaaaaㄹㄹㄹ");

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


	void modifychildren(ArrayList<Data> childs, ArrayList<Point> childLinesLc, ArrayList<Point> childLinesSz) {
		int h=childs.get(0).getParent().getHeight();
		System.out.println("이즈엠티?"+ childs.isEmpty());
		System.out.println(childs.size());
		
		for(int i=0;i<childs.size();i++) {
			modifybyP(h,childs.get(i).getS(),childLinesLc.get(i),childLinesSz.get(i));
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
					
					tmpChild=child.getChild();
					childs.add(tmpChild);
					childLinesLc.add(vl.get(cnt4child));
					childLinesSz.add(vs.get(cnt4child));
					while(tmpChild.getSibling()!=null) {
						tmpChild=tmpChild.getSibling();
						for(int k=0;k<panel.getComponentCount();k++) {
								System.out.println("이름을 대시오 ~\n 제 이름은 ... "+tmpChild.getValue());
								System.out.println(((JLabel)panel.getComponent(k)).getText()+" 왈 : 그 자식은 제 동생입니다.");
										
								if(((JLabel)panel.getComponent(k)).getText()==tmpChild.toString()) {
									System.out.println(((JLabel)panel.getComponent(k)).getText()+" 왈 : 그 자식은 제 동생입니다.");
									cnt4child=k-1;
									childs.add(tmpChild);
									childLinesLc.add(vl.get(cnt4child));
									childLinesSz.add(vs.get(cnt4child));
									System.out.println("차일즈카운트 : "+cnt4child);
									System.out.println("차일드네임스 : "+tmpChild.getValue());
								}
							}
					}
						
						
						
						
						for(int i=0;i<childs.size();i++) {
							System.out.println("name + "+childs.get(i).getValue());
						}
					
					return;
				}

				cnt=i;
				child=datas.get(cnt);
				parent=child.getParent();
				
				cnt4child=cnt;
				System.out.println("Parent ::::::::: "+parent.getValue());
				System.out.println("selected ::::::::: "+child.getValue());
				
				for(int j=0;j<panel.getComponentCount();j++) {
//					System.out.println(parent.getValue());
					
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
			System.out.println("드래그...");
			offX=e.getX()-x;
			offY=e.getY()-y;
			updateLine ul=new updateLine(offX,offY,pointl,points);

			if(i!=0) {
				Point tmpp=points;
				Point tmppp=pointl;
				if(!ul.separate(s, pointl, points,parent)) {
					label.setLocation(label.getX(),label.getY());
					vl.removeElementAt(cnt);
					vs.removeElementAt(cnt);
						
					vl.add(cnt, pointl);//
					vs.add(cnt,points);
					panel.repaint();
//					isDragged=false;
//					mouseReleased(e);//
					return;					
				
			}
				
				if(childs.size()!=0) {
					ul.modifychildren(childs,childLinesLc,childLinesSz);
					
				}
				vl.removeElementAt(cnt);
				vs.removeElementAt(cnt);
					
				vl.add(cnt, pointl);//
				vs.add(cnt,points);
				panel.repaint();
				
				label.setLocation(e.getX()+label.getX()-x,e.getY()+label.getY()-y);
				System.out.println("MOOOVE");
				
			}
			else {
				if(childs.size()!=0) {
					ul.modifychildren(childs,childLinesLc,childLinesSz);
					
				}
				panel.repaint();
				System.out.println("안돼는것실화?ㄹ?ㄹ?ㄹ?");
				label.setLocation(e.getX()-x+label.getX(),e.getY()-y+label.getY());
				
			}
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
