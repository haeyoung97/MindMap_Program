import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class DotListener extends MouseAdapter {
	private JDrawPanel panel;
	private JPanel attributeFieldPane;
	private JLabel dot,label;
	private Data child;
	private boolean isDragged;
	private int site,x,y,offX,offY,labelW,labelH,labelX,labelY,dotX,dotY,tmplX,tmplY,tmpsX,tmpsY;
	private updateLine ul;
	private Point tmpl,tmps;
	
	public DotListener(int site ,JLabel lbs, JDrawPanel panel, Data child,JPanel attributeFieldPane) {
		this.panel=panel;
		this.site=site;
		this.attributeFieldPane=attributeFieldPane;
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
		((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(3)).setText(""+child.getX());
	    ((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(5)).setText(""+child.getY());
	    ((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(7)).setText(child.getStrW());
	    ((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(9)).setText(child.getStrH());
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
			System.out.println("라벨 사이즈 조정 > x: "+x);
			switch(site) {
			case 0:
				offX=0;
				label.setLocation(label.getX(),labelY+offY);
				label.setSize(label.getWidth(),labelH-offY);
				dot.setLocation(dot.getX(),dotY+offY);
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
			
			child.setX(label.getX());
			child.setY(label.getY());
			child.setW(label.getWidth());
			child.setH(label.getHeight());
			
			((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(1)).setText(child.getValue());
			((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(3)).setText(""+child.getX());
			((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(5)).setText(""+child.getY());
			((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(7)).setText(child.getStrW());
			((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(9)).setText(child.getStrH());
			((JTextField)((JPanel)attributeFieldPane.getComponent(1)).getComponent(11)).setText("0x"+child.getColorStrRGB());
			
			
			
			
			
			System.out.println("===========\"DOT MOVE\"================================");
 		}
 	}
	
	
	
}