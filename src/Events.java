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
			
			System.out.println("たたた\nたたたた");
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
//		
		
		
		
		
		
		
	}


	void modifychildren(ArrayList<Data> childs, ArrayList<Point> childLinesLc, ArrayList<Point> childLinesSz) {
		int h=childs.get(0).getParent().getHeight();
		System.out.println("戚綜織銅?"+ childs.isEmpty());
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

class ButtonListener implements ActionListener{ //獄動 戚坤闘
	private Object O;
	private Mindmap mindmapSection;
	private StringBuffer buffer; 						//text editor 葵		-戚 獄動戚 叔楳吃 凶原陥 歯稽錘 梓端 持奄澗 依戚 択択敗...
	private String tmp=new String();
//	private Tree tree=new Tree(mindmapSection.drawNodePanel); //TREE蓄亜
	private Tree tree;
	
//	private JPanel panel4line;
	
	
	
	ButtonListener(Object O,Mindmap mindmapSection) {
			this.O=O;
			this.mindmapSection=mindmapSection;	//尻衣 OK
			this.tree=new Tree(mindmapSection.drawNodePanel);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("旋遂") || e.getActionCommand().equals("Apply")) { 		//Text Editor
			int i=0; 							//while庚 朝錘闘
			mindmapSection.drawNodePanel.removeAll();
//			mindmapSection.drawNodePanel.setBackground(arg0);
//			JDrawPanel tmpP=new JDrawPanel();
//			mindmapSection.drawNodePanel=tmpP;
			
			mindmapSection.drawNodePanel.reset();
			
			buffer=new StringBuffer(((JTextArea)O).getText());
		    tmp=buffer.toString();
			StringTokenizer st=new StringTokenizer(tmp,"\n");	//st拭 庚切伸拭辞 鯵楳+庚切稽 吉 蕉級 奄層生稽 切牽奄.
			String [] member=new String[st.countTokens()];
			
			
			while(st.hasMoreTokens()) {							//member壕伸拭 \n奄層生稽 葵 煽舌. (\t政反)
				member[i]=st.nextToken();
				i++;
			}
			
			if(tree.MakeTree(member)) {
				tree.print();
				tree.AddLabel(mindmapSection.drawNodePanel);
				
				
//				mindmapSection.drawNodePanel.getLabels2drawing((JLabel)mindmapSection.drawNodePanel.getComponent(0), (JLabel)mindmapSection.drawNodePanel.getComponent(1));
//				mindmapSection.drawNodePanel.DrawingLine(g);
				
				
				///////////////////////識 益軒奄.../////////////////
				
				
				
				
				mindmapSection.drawNodePanel.setVisible(false);
				mindmapSection.drawNodePanel.setVisible(true);
				tree.Default();
			}						
		}
		
//		else if(Btn.getText().equals("痕井")) { //Attribute Editor 
//			JTextField[] value = new JTextField[6];
//			for(int i=1;i<=6;i++) {
//				value[i-1]=(JTextField)((JPanel)O).getComponent(i*2-1);
//				valueArray[i-1]=value[i-1].getText();
//				System.out.println(valueArray[i-1]); //0腰属拭澗 薄仙 朔 因娃. NAME採歳.
//			}
//			
//			
//		}
		
	}
}


class JLabelListener extends MouseAdapter {
//	private JTextField[] valueArray = new JTextField[6];			//attribute editor 葵	-戚 獄動戚 叔楳吃 凶原陥 歯稽錘 梓端 持奄澗 依戚 択択敗...
//	private JTextField[] fields;			//attribute editor 葵	-戚 獄動戚 叔楳吃 凶原陥 歯稽錘 梓端 持奄澗 依戚 択択敗...
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
						System.out.println("ぜ"+((JLabel)panel.getComponent(j)).getText());
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
		
		System.out.println("葵精 郊稽郊稽 "+cnt);
		vl=panel.getVlocation();
		vs=panel.getVsize();
		if(cnt>=0) {
			points=vs.get(cnt);
			pointl=vl.get(cnt);
//			System.out.println(v.get(cnt));
		}
		
		if(child.getChild()!=null) {
			//切縦級 煽舌//
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
					System.out.println("託析綜朝錘闘 : "+cnt4child);
					System.out.println("託析球革績什 : "+tmpChild.getValue());
	
					cnt4child++;
				}
			}
			childLinesLc.add(vl.get(cnt4child));
			childLinesSz.add(vs.get(cnt4child));
			
			for(int i=0;i<childs.size();i++) {
				System.out.println("name + "+childs.get(i).getValue());
			}
			System.out.println(childs.size()+"硲継継");

		}

		
		x = e.getX();
		y = e.getY();
//		int lbX = x+label.getWidth()/2; 	// width
//		int lbY = y+label.getHeight()/2; // height
//		
//		System.out.println("label税 是帖 "+lb.getLocation().x+" "+lb.getLocation().y); //虞婚税 図楕雌舘乞辞軒 葵!!!!
//		System.out.println("原酔什 匂昔斗税 是帖 " + e.getX()+ " "+ e.getY()); //虞婚 照拭辞 原酔什 匂昔斗税 是帖
//		System.out.println("鳶確 是 原酔什 匂昔斗 是帖 " + (lb.getLocation().x+e.getX())+ " "+(lb.getLocation().y+e.getY()));
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
		//原酔什 獄動戚 険軒綜鞠檎 球掘益 乞球 曽戟
		isDragged = false;
		cnt=0;
		System.out.println("FALSE");
		
	}
	
	public void mouseDragged(MouseEvent e){///////////////////////////////////////////////
		System.out.println("球掘益...");
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

				System.out.println("匂敗戚神~~");
				offX = me.getX() - panel.getComponent(i).getLocation().x;
				offY = me.getY() - panel.getComponent(i).getLocation().y;
				this.label=panel.getComponent(i);
				isDragged = true;
			}
		System.out.println(me.getX()+" "+me.getY());
		}
	}

	public void mouseReleased(MouseEvent me){//////////////////////////////////////////////
		//原酔什 獄動戚 険軒綜鞠檎 球掘益 乞球 曽戟
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