import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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

class ButtonListener implements ActionListener{ //버튼 이벤트
	private Object O;
	private Mindmap mindmapSection;
	private StringBuffer buffer; 						//text editor 값		-이 버튼이 실행될 때마다 새로운 객체 생기는 것이 찜찜함...
	private String tmp=new String();
//	private Tree tree=new Tree(mindmapSection.drawNodePanel); //TREE추가
	private Tree tree;
	
//	private JPanel panel4line;
	
	
	
	ButtonListener(Object O,Mindmap mindmapSection) {
			this.O=O;
			this.mindmapSection=mindmapSection;	//연결 OK
			this.tree=new Tree(mindmapSection.drawNodePanel);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("적용") || e.getActionCommand().equals("Apply")) { 		//Text Editor
			int i=0; 							//while문 카운트
			mindmapSection.drawNodePanel.removeAll();
//			mindmapSection.drawNodePanel.setBackground(arg0);
//			JDrawPanel tmpP=new JDrawPanel();
//			mindmapSection.drawNodePanel=tmpP;
			
			mindmapSection.drawNodePanel.reset();
			
			buffer=new StringBuffer(((JTextArea)O).getText());
		    tmp=buffer.toString();
			StringTokenizer st=new StringTokenizer(tmp,"\n");	//st에 문자열에서 개행+문자로 된 애들 기준으로 자르기.
			String [] member=new String[st.countTokens()];
			
			
			while(st.hasMoreTokens()) {							//member배열에 \n기준으로 값 저장. (\t유효)
				member[i]=st.nextToken();
				i++;
			}
			
			if(tree.MakeTree(member)) {
				tree.print();
				tree.AddLabel(mindmapSection.drawNodePanel);
				
				
//				mindmapSection.drawNodePanel.getLabels2drawing((JLabel)mindmapSection.drawNodePanel.getComponent(0), (JLabel)mindmapSection.drawNodePanel.getComponent(1));
//				mindmapSection.drawNodePanel.DrawingLine(g);
				
				
				///////////////////////선 그리기.../////////////////
				
				
				
				
				mindmapSection.drawNodePanel.setVisible(false);
				mindmapSection.drawNodePanel.setVisible(true);
				tree.Default();
			}						
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
	private int cnt,i,s;
	private ArrayList<Data> datas;
	private int offX,offY;
	
	public JLabelListener(JDrawPanel panel) {
		this.panel=panel;
//		System.out.println(panel.getName());
	}

	public void mousePressed(MouseEvent e) {
		datas=panel.getArray();
		
		label = (JLabel)e.getSource();
		child=null;
		parent=null;
		i=0;
		s=0;
		
		
//		panel.getArray().get(i).getParent().getValue()
		
		while(true) {
			if(label.getText()==((JLabel) panel.getComponent(i)).getText()) {
				if(i==0) {
					return;
				}
				cnt=i;
				child=datas.get(i);
				parent=child.getParent();
				for(int j=0;j<panel.getComponentCount();j++) {
					System.out.println(parent.getValue());
					if(parent.toString()==((JLabel)panel.getComponent(j)).getText()){
						System.out.println("ㅌ"+((JLabel)panel.getComponent(j)).getText());
						Plabel=(JLabel)(panel.getComponent(j));
						break;
					}
				}
				
				if(label.getX()<Plabel.getX()) {
					if(label.getY()<Plabel.getY()) {
						s=2;
//						System.out.println("그림2 "+P.getValue()+"의 자식: "+C.getValue());
					}
					else {
						s=3;
//						System.out.println("그림3 "+P.getValue()+"의 자식: "+C.getValue());
					}
					
				}
				else {
					if(label.getY()<Plabel.getY()) {
						s=4;
//						System.out.println("그림4 "+P.getValue()+"의 자식: "+C.getValue());
					}
					else {
						s=1;
//						System.out.println("그림1 "+P.getValue()+"의 자식: "+C.getValue());
					}
		
				}
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
			/////////////////////////////////////////////////////////////////////////////////
			
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
			

			

				vl.removeElementAt(cnt);
				vs.removeElementAt(cnt);
				
				vl.add(cnt, pointl);//
				vs.add(cnt,points);
				panel.repaint();
				
				
			
			
			
			label.setLocation(e.getX()+label.getX()-x,e.getY()+label.getY()-y);
			
			
			System.out.println("MOOOVE");
		}
	}
	
}


class SaveButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}



class Mouser extends MouseAdapter{

	boolean isDragged;
	int offX=0, offY=0;
	JPanel panel;
	Component []labels;
	Component label;
	
	Mouser(JPanel drawNodePanel){
		this.panel=drawNodePanel;
		labels=panel.getComponents();
	}
	
	public void mousePressed(MouseEvent me){/////////////////////////////////////////////////

		System.out.println(panel.getComponentCount());
		
		for(int i=0;i<panel.getComponentCount();i++) {
			if((me.getX()>panel.getComponent(i).getX() &&me.getX()<(panel.getComponent(i).getWidth()+panel.getComponent(i).getX()))&&(me.getY()>panel.getComponent(i).getY()&&me.getY()<(panel.getComponent(i).getHeight()+panel.getComponent(i).getY()))){

				System.out.println("포함이오~~");
				offX = me.getX() - panel.getComponent(i).getLocation().x;
				offY = me.getY() - panel.getComponent(i).getLocation().y;
				this.label=panel.getComponent(i);
				isDragged = true;
			}
		System.out.println(me.getX()+" "+me.getY());
		}
	}

	public void mouseReleased(MouseEvent me){//////////////////////////////////////////////
		//마우스 버튼이 릴리즈되면 드래그 모드 종료
		isDragged = false;
		System.out.println("FALSE");
	}
	public void mouseDragged(MouseEvent me){///////////////////////////////////////////////
		
		if(isDragged){

			label.setLocation(me.getX()-offX,me.getY()-offY);
			panel.setVisible(false);
			panel.setVisible(true);

			System.out.println("MOOOVE");
		}
	}
}