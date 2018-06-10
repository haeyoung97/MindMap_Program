import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		
		System.out.println("설마 라벨도 클릭돼? 1) "+e.getSource());
		isDragged=true;
		
	}
	public void mouseReleased(MouseEvent e){//////////////////////////////////////////////
		//마우스 버튼이 릴리즈되면 드래그 모드 종료
		isDragged = false;
		System.out.println("DOT STOP");

//		panel.getLabels2drawing(child.getParent(), child);
		
		panel.repaint();
		
		child.setW(label.getWidth());
		child.setH(label.getHeight());
		child.setX(label.getX());
		child.setY(label.getY());
		
		ul.resize(child, panel.getVlocation(), panel.getVsize());
		panel.getVlocation().remove(child.getLineNum());
		panel.getVlocation().add(child.getLineNum(),tmpl);
		panel.getVsize().remove(child.getLineNum());
		panel.getVsize().add(child.getLineNum(),tmps);
		
		
		
		
		
		}
 	public void mouseDragged(MouseEvent e){///////////////////////////////////////////////
 		if(isDragged) {
 			System.out.println("x,y == "+x+" , "+y);
 			System.out.println("EEEx,y == "+e.getX()+" , "+e.getY());
 			offX=e.getX()+dot.getX()-x;
 			offY=e.getY()+dot.getY()-y;
 			System.out.println("\n"+offX+",,,"+offY); 			
			System.out.println("라벨 사이즈 조정 > x: "+x);
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
//				
//				tmps.setLocation(tmpsX+offX,tmps.getY());
//				tmpl.setLocation(label.getX()+label.getWidth()/2-tmps.x,tmpl.y);
//				
//				
//				
//				System.out.println("<<<<<<<<<<<<<<<<<<tmpL:::"+tmpl);
//				System.out.println("<<<<<<<<<<<<<<<<<<tmpS:::"+tmps);
//				
				break;
			case 2:
				
				offY=0;
				label.setLocation(label.getX(),label.getY());
				label.setSize(offX+labelW, label.getHeight());
				dot.setLocation(dotX+offX,dot.getY());
				
//
//				tmps.setLocation(tmpsX+offX,tmps.getY());
//				tmpl.setLocation(label.getX()+label.getWidth()/2-tmps.x,tmpl.y);
//				
				
				
				break;
			case 3:
				
				offX=0;
				label.setLocation(label.getX(),label.getY());
				label.setSize(label.getWidth(),labelH+offY);
				dot.setLocation(dot.getX(),dotY+offY);
				
//				tmps.setLocation(tmps.x,tmpsY-offY*2);
//				tmpl.setLocation(tmpl.x,label.getY()+label.getHeight()-tmps.y/2);
				break;					
			}
////			System.out.println(child.getS());
////			System.out.println(panel.getVlocation().size());
////			System.out.println(child.getLineNum());
////			System.out.println(panel.getVlocation().get(child.getLineNum()));
////			System.out.println(panel.getVsize().get(child.getLineNum()));
////			System.out.println(child.getParent().getValue());
//			
//			
////			ul.setOffset(offX, offY);
////			ul.resize(child,offX,offY,panel.getVlocation(),panel.getVsize());
//			panel.getVlocation().remove(child.getLineNum());
//			panel.getVlocation().add(child.getLineNum(),tmpl);
//			panel.getVsize().remove(child.getLineNum());
//			panel.getVsize().add(child.getLineNum(),tmps);
//			panel.repaint();
			

//			label.setLocation(labelX+offX,label.getY());
			
			System.out.println("===========\"DOT MOVE\"================================");
 		}
 	}
	
	
	
}