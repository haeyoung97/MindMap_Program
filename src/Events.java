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
					System.out.println("�ѹ���");
					break;					//-----�̰��� 4��и�..!!!!!!!!!!!!!!
				case 2:
					pointl.setLocation(offX*2+pointl.getX(),offY+pointl.getY());
					points.setLocation(-offX*2+points.getX(),-offY*2+points.getY());
					System.out.println("�ѹ���");
					break;
				case 3:
					pointl.setLocation(offX+pointl.getX(),pointl.getY());
					points.setLocation(-offX*2+points.getX(),offY*2+points.getY());
					System.out.println("�ѹ�����");
					break;					//-----�갡 1��и��ε�?
				case 4:
					pointl.setLocation(-offX+pointl.getX(),offY*2+pointl.getY());
					points.setLocation(offX*2+points.getX(),-offY*2+points.getY());
					System.out.println("�ѹ���");					
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
		for(int i=0;i<childs.size();i++) {
			modifybyP(h,childs.get(i).getS(),childLinesLc.get(i),childLinesSz.get(i));
		}	
	}
	
	void modifybyP(int h,int s,Point pointl,Point points) {
		if(h%2==1) {
			System.out.println("aaaaaaaaaaa������");
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
			System.out.println("sdfsdfsdf����");
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

class ButtonListener implements ActionListener { //��ư �̺�Ʈ
	private Object O;
	private Mindmap mindmapSection;
	private JPanel attributeFieldPane; 
	private StringBuffer buffer; 						//text editor ��		-�� ��ư�� ����� ������ ���ο� ��ü ����� ���� ������...
	private String tmp=new String();
	private Tree tree;
	private Bar b;
	private SaveButtonListener saveListener;

	// �����ư ������
	ButtonListener(Object O, Mindmap mindmapSection, Bar b) {
		this.mindmapSection=mindmapSection;	//���� OK
		this.b = b;
		this.attributeFieldPane = (JPanel) O;
		this.tree = new Tree(mindmapSection.drawNodePanel, attributeFieldPane);
	}
	
	// �����ư ������
	ButtonListener(JPanel attributeFieldPane, Object O, Mindmap mindmapSection, Bar b, SaveButtonListener saveListener) {
		this.O=O;
		this.mindmapSection=mindmapSection;	//���� OK
		this.attributeFieldPane = attributeFieldPane;
		this.saveListener = saveListener;
		this.tree = new Tree(mindmapSection.drawNodePanel, attributeFieldPane);
		this.b = b;
	}
	
	
	Tree getTree() {
		return tree;
	}
	
	void ApplyButtonFunc() {
		int i=0; 							//while�� ī��Ʈ
		mindmapSection.drawNodePanel.removeAll();
//		mindmapSection.drawNodePanel.setBackground(arg0);
//		JDrawPanel tmpP=new JDrawPanel();
//		mindmapSection.drawNodePanel=tmpP;
		
		mindmapSection.drawNodePanel.reset();
		
		buffer=new StringBuffer(((JTextArea)O).getText());
	    tmp=buffer.toString();
		StringTokenizer st=new StringTokenizer(tmp,"\n");	//st�� ���ڿ����� ����+���ڷ� �� �ֵ� �������� �ڸ���.
		String [] member=new String[st.countTokens()];
		
		if(st.countTokens() == 0) {
			JOptionPane.showMessageDialog(null, "������ �����ϴ�.", "Null",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		while(st.hasMoreTokens()) {							//member�迭�� \n�������� �� ����. (\t��ȿ)
			member[i]=st.nextToken();
			i++;
		}
		
		mindmapSection.drawNodePanel.getArray().clear();//����. ���� �ø��� data Ŭ���� ���ֱ�!
		tree.Default();
		
		if(tree.MakeTree(member)) {
			tree.print();
			tree.AddLabel(mindmapSection.drawNodePanel);
	
//			mindmapSection.drawNodePanel.getLabels2drawing((JLabel)mindmapSection.drawNodePanel.getComponent(0), (JLabel)mindmapSection.drawNodePanel.getComponent(1));
//			mindmapSection.drawNodePanel.DrawingLine(g);
		
			///////////////////////�� �׸���.../////////////////

			mindmapSection.drawNodePanel.setVisible(false);
			mindmapSection.drawNodePanel.setVisible(true);
			// Save ��ư
			JMenuItem menuItemSave = b.getMenuItem(2);
			JButton toolBtnSave = b.getToolButton(2);
			menuItemSave.removeActionListener(saveListener); 
			toolBtnSave.removeActionListener(saveListener); 
			// SaveAs ��ư
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
		if(e.getActionCommand().equals("����") || e.getActionCommand().equals("Apply")) { 		//Text Editor
			ApplyButtonFunc();
										
		}
		
//		else if(Btn.getText().equals("����")) { //Attribute Editor 
//			JTextField[] value = new JTextField[6];
//			for(int i=1;i<=6;i++) {
//				value[i-1]=(JTextField)((JPanel)O).getComponent(i*2-1);
//				valueArray[i-1]=value[i-1].getText();
//				System.out.println(valueArray[i-1]); //0��°���� ���� �� ����. NAME�κ�.
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
					System.out.println("����� ��Ʈ�Դϱ� ? :"+child.getValue());//OKOK
					
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
//					System.out.println(parent.getValue());
					
					if(parent.toString()==((JLabel)panel.getComponent(j)).getText()){
						System.out.println("��"+((JLabel)panel.getComponent(j)).getText());
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
			//�ڽĵ� ����//
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
					System.out.println("������ī��Ʈ : "+cnt4child);
					System.out.println("���ϵ���ӽ� : "+tmpChild.getValue());
	
					cnt4child++;
				}
			}
			childLinesLc.add(vl.get(cnt4child));
			childLinesSz.add(vs.get(cnt4child));
			
			for(int i=0;i<childs.size();i++) {
				System.out.println("name + "+childs.get(i).getValue());
			}
			System.out.println(childs.size()+"ȣ�ѷ�");

		}

		
		x = e.getX();
		y = e.getY();

		isDragged = true;
		System.out.println("test name : " + label.getText() + " test X : " + x + " test Y : "+y);
		
	}
	
	public void mouseReleased(MouseEvent e){//////////////////////////////////////////////
		//���콺 ��ư�� ������Ǹ� �巡�� ��� ����
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
 			
 			
			System.out.println("�巡��...");
			offX=e.getX()-x;
			offY=e.getY()-y;
			label.setLocation(offX+label.getX(),offY+label.getY());
			//���� �� ����
			
			checkArea(child,parent, childs);
			
			
			
			
		//Ex ������
//			label.setLocation(label.getX()+60,label.getY()+60);
			
			
			
			
 			//���� ���� �����¿� ���� �� ����
			
			offX=label.getX()-tmplabelX;
			offY=label.getY()-tmplabelY;
//			
//			if(Plabel!=null) {//��Ʈ�ƴҶ�
//// 				if(points.getX()<=0) {
// 				if(Plabel.getX())
// 					
// 				}
// 				if(points.getY()<=0) {
// 					
// 				} 		
// 			}
// 			
// 			for(int i=0;i<childLinesSz.size();i++) {
// 				if(childLinesSz.get(i).getX()<=0) {
// 					//����
// 				}
// 				if(childLinesSz.get(i).getY()<=0){
// 					//����
// 				}
// 			}
// 				
			updateLine ul=new updateLine(offX,offY,pointl,points);
			
			
			
 			
 			



 			
 			if(i!=0) {
 				ul.separate(s, pointl, points,parent);
// 				 				
// 				//���� �۾�//
// 				if(points.getX()<=0) {
// 					label.setLocation(Plabel.getX()+Plabel.getWidth()/2+1,label.getY());
// 					points.setLocation(1,points.getY()); 					
// 				}
// 				if(points.getY()<=0) {
// 					label.setLocation(label.getX(),Plabel.getY()+Plabel.getHeight()/2+1);
// 					points.setLocation(points.getX(),1);
// 				}
 				
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
 		panel.repaint();
 	}
	
 	private void checkArea(Data child, Data parent, ArrayList<Data> childs) {
		// TODO Auto-generated method stub
 		int parentX=parent.getLabel().getX();
 		int parentY=parent.getLabel().getY();
 		int childX=child.getLabel().getX();
 		int childY=child.getLabel().getY();
 		int s=child.getS();
 		
	 	switch(s) {
			case 1:
				if(parentX+parent.getLabel().getWidth()>childX) {
					child.getLabel().setLocation(parentX+parent.getLabel().getWidth()+1,childY);
				}
				if(parentY<childY+child.getLabel().getHeight()) {
					child.getLabel().setLocation(child.getLabel().getX(),parentY-child.getLabel().getHeight());
				}
			break;
			
			case 2:
				if(parentX<childX+child.getLabel().getWidth()) {
					child.getLabel().setLocation(parentX-parent.getLabel().getWidth(),childY);
				}
				if(parentY<childY+child.getLabel().getHeight()) {
					child.getLabel().setLocation(child.getLabel().getX(),parentY-child.getLabel().getHeight());
				}
				
		 	break;
		 	
		 	case 3:
				if(parentX<childX+child.getLabel().getWidth()) {
					child.getLabel().setLocation(parentX-child.getLabel().getWidth(),childY);
				}
				if(parentY+parent.getLabel().getHeight()>childY) {
					child.getLabel().setLocation(child.getLabel().getX(),parentY+parent.getLabel().getHeight());
				}
		 		
		 	break;
		 	
		 	case 4:
				if(parentX+parent.getLabel().getWidth()>childX) {
					child.getLabel().setLocation(parentX+parent.getLabel().getWidth(),childY);
				}
				if(parentY+parent.getLabel().getHeight()>childY) {
					child.getLabel().setLocation(child.getLabel().getX(),parentY+parent.getLabel().getHeight());
				}
		 		
		 	break;
	 	}///////////////////////////////////�θ�󺧱���
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
 		
 		
 		
 		


 	}

//	public void mouseDragged(MouseEvent e){///////////////////////////////////////////////
//		int tmpX,tmpY;
//		if(isDragged){
//			System.out.println("�巡��...");
//			offX=e.getX()-x;
//			offY=e.getY()-y;
//			
//			
//			label.setLocation(offX+label.getX(),offY+label.getY());
//			
//			updateLine ul=new updateLine(offX,offY,pointl,points);
//
//			if(i!=0) {
//				Point tmpp=points;
//				Point tmppp=pointl;
//				
////				
////				
////				if(!ul.separate(s, pointl, points,parent)) {
////					System.out.println(pointl + "������" +points);
////					if(points.getX()<=0) {
////						label.setLocation(Plabel.getX()+Plabel.getWidth()/2+1,label.getY());
////						tmpp.setLocation(1, tmpp.getY());
////						
////					}
////					if(points.getY()<=0) {
////						label.setLocation(label.getX(),Plabel.getY()+Plabel.getHeight()/2+1);
////						tmpp.setLocation(tmpp.getX(),1);
////					}
////					}
//					
//					
//					
//					
//					
//					
//					
//					vl.removeElementAt(cnt);
//					vs.removeElementAt(cnt);
////					tmpp.setLocation(1, tmpp.getY());
//					vl.add(cnt, pointl);//
//					vs.add(cnt,points);
//					panel.repaint();
//					
//					
//					
//					mouseReleased(e);//			
//				
//			}
//				
//			if(childs.size()!=0) {
//				ul.modifychildren(childs,childLinesLc,childLinesSz);
//					
//			}
//			vl.removeElementAt(cnt);
//			vs.removeElementAt(cnt);
//				
//			vl.add(cnt, pointl);//
//			vs.add(cnt,points);
//			panel.repaint();
//			
////			label.setLocation(e.getX()+label.getX()-x,e.getY()+label.getY()-y);
//			System.out.println("MOOOVE");
//				
//			
//			else {
//				if(childs.size()!=0) {
//					ul.modifychildren(childs,childLinesLc,childLinesSz);
//					
//				}
//				panel.repaint();
//				System.out.println("�ȵŴ°ͽ�ȭ?��?��?��?");
//				
//				
//			}
//		
//	}
	
	public void mouseClicked(MouseEvent e) {
		JLabel lb = (JLabel)e.getSource();
		((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(1)).setText(lb.getText());
		((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(3)).setText(""+lb.getX());
		((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(5)).setText(""+lb.getY());
		((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(7)).setText(""+lb.getWidth());
		((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(9)).setText(""+lb.getHeight());
		((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(11)).setText("Color");
		System.out.println("�����ư ������ : __________");
		
	}
}
