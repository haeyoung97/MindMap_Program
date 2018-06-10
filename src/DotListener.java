import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JLabel;



public class DotListener extends MouseAdapter {
	private JDrawPanel panel;
	private JLabel dot,label;
	private Data child;
	private boolean isDragged;
	private int site,x,y,offX,offY,labelW,labelH,labelX,labelY,dotX,dotY,tmplX,tmplY,tmpsX,tmpsY;
	private updateLine ul;
	private Point tmpl,tmps;
	
	public DotListener(int site ,JLabel lbs, JDrawPanel panel, Data child) {
		this.panel=panel;
		this.site=site;

		this.child=child;
		this.label=child.getLabel();
		ul=child.getul();
		
	}
	
	public void mousePressed(MouseEvent e) {
		dot=(JLabel)e.getSource();
		labelX=label.getX();
		labelY=label.getY();
		
		labelW=label.getWidth();
		labelH=label.getHeight();
		
		dotX=dot.getX();
		dotY=dot.getY();
		
		tmpl=panel.getVlocation().get(child.getLineNum());
		tmps=panel.getVsize().get(child.getLineNum());
		
		System.out.println(site +"  DOT PRESSED");
		
		x = e.getX()+dot.getX();
		y = e.getY()+dot.getY();		
		System.out.println("x,y == "+x+" , "+y);
		tmplX=tmpl.x;
		tmplY=tmpl.y;
		
		tmpsX=tmps.x;
		tmpsY=tmps.y;
		
		System.out.println("���� �󺧵� Ŭ����? 1) "+e.getSource());
		isDragged=true;
		
	}
	public void mouseReleased(MouseEvent e){//////////////////////////////////////////////
		//���콺 ��ư�� ������Ǹ� �巡�� ��� ����
		isDragged = false;
		System.out.println("DOT STOP");

//		panel.getLabels2drawing(child.getParent(), child);
		
		panel.repaint();
		
		child.setW(label.getWidth());
		child.setH(label.getHeight());
		child.setX(label.getX());
		child.setY(label.getY());
		
		JLabel [] dots= child.getDots();
		dots[0].setLocation(label.getX()+label.getWidth()/2-dots[0].getWidth()/2,label.getY()-dots[0].getHeight());
		dots[1].setLocation(label.getX()-dots[1].getHeight(),label.getY()+label.getHeight()/2-dots[1].getHeight()/2);
		dots[2].setLocation(label.getX()+label.getWidth(),label.getY()+label.getHeight()/2-dots[1].getHeight()/2);
		dots[3].setLocation(label.getX()+label.getWidth()/2-dots[0].getWidth()/2,label.getY()+label.getHeight());

		panel.getVlocation().clear();
		panel.getVsize().clear();
		
		
		Data start=null, obj=null, last=null;
		start = panel.getArray().get(0);
		obj=start;
		while(true) {
				if(obj.getChild()!=null) {
					obj=obj.getChild();
					panel.getLabels2drawing(obj.getParent(), obj);
				}
				else if(obj.getSibling()!=null) {
					obj=obj.getSibling();
					panel.getLabels2drawing(obj.getParent(), obj);
				}
				else {
					if(obj==last) {
						break;
					}
					while(true) {
						obj=obj.getParent();
						if(obj==null) {
							return;
						}
						if(obj.getSibling()!=null) {
							obj=obj.getSibling();
							break;
						}
					}
				}
				
				
		}
		
		
		
		
		
		}
 	public void mouseDragged(MouseEvent e){///////////////////////////////////////////////
 		if(isDragged) {
 			System.out.println("x,y == "+x+" , "+y);
 			System.out.println("EEEx,y == "+e.getX()+" , "+e.getY());
 			offX=e.getX()+dot.getX()-x;
 			offY=e.getY()+dot.getY()-y;
 			System.out.println("\n"+offX+",,,"+offY); 			
			System.out.println("�� ������ ���� > x: "+x);
//			switch(child.getS()) {
//			case 0:
//				break;
//			case 1:
//				break;
//			case 2:
//				break;
//			case 3:
//				break;
//			
//			
//			
//			}
			switch(site) {
			case 0:
				offX=0;
				label.setLocation(label.getX(),labelY+offY);
				label.setSize(label.getWidth(),labelH-offY);
				dot.setLocation(dot.getX(),dotY+offY);
//				
////
////				tmps.setLocation(tmps.x,tmpsY+offY);
////				tmpl.setLocation(tmpl.x,label.getY()-tmps.y*2);
//				
				break;
			case 1:
				
				offY=0;
				label.setLocation(labelX+offX,label.getY());
				label.setSize(-offX+labelW, label.getHeight());
				dot.setLocation(dotX+offX,dot.getY());
				break;
			case 2:
				
				offY=0;
				label.setLocation(label.getX(),label.getY());
				label.setSize(offX+labelW, label.getHeight());
				dot.setLocation(dotX+offX,dot.getY());
				
				break;
			case 3:
				
				offX=0;
				label.setLocation(label.getX(),label.getY());
				label.setSize(label.getWidth(),labelH+offY);
				dot.setLocation(dot.getX(),dotY+offY);
				break;					
			}
			
			System.out.println("===========\"DOT MOVE\"================================");
 		}
 	}
	
	
	
}