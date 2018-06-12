

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class DotListener extends JLabelListener {
	private JLabel dot,label;
	private Data child;
	private boolean isDragged;
	private int site,x,y,offX,offY,labelW,labelH,labelX,labelY,dotX,dotY,tmplX,tmplY,tmpsX,tmpsY;
	private updateLine ul;
	private Point tmpl,tmps;
	private int [] finalLimit;
	
	public DotListener(int site ,JLabel lbs, JDrawPanel panel, Data child,JPanel attributeFieldPane) {
		super(panel,attributeFieldPane);
		this.site=site;
		this.child=child;
		this.label=child.getLabel();
		
	}
	
	public void mousePressed(MouseEvent e) {
		dot=(JLabel)e.getSource();
		labelX=label.getX();
		labelY=label.getY();
		
		labelW=label.getWidth();
		labelH=label.getHeight();
		
		dotX=dot.getX();
		dotY=dot.getY();
		
		x = e.getX()+dot.getX();
	    y = e.getY()+dot.getY();
	    
	    if(child.getParent()!=null) {
	       tmpl=panel.getVlocation().get(child.getLineNum());
	       tmps=panel.getVsize().get(child.getLineNum());  
	       tmplX=tmpl.x;
	       tmplY=tmpl.y;
	         
	       tmpsX=tmps.x;
	       tmpsY=tmps.y;
	       
	    }
		
		
		if(child.getChild()!=null) {
			//자식들 저장//
			Data tmpChild=child.getChild();
				while(true) {
					if(tmpChild==null) {
						break;
					}
//					
					for(int k=0;k<panel.getComponentCount();k++) {									
						if(((JLabel)panel.getComponent(k)).getText()==tmpChild.toString()) {
							childs.add(tmpChild);
						}
					}
					tmpChild=tmpChild.getSibling();
			}
		}
		
		isDragged=true;
		
	}
	public void mouseReleased(MouseEvent e){//////////////////////////////////////////////
		//마우스 버튼이 릴리즈되면 드래그 모드 종료
		isDragged = false;

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
				}
				else if(obj.getSibling()!=null) {
					obj=obj.getSibling();
				}
				else {
					if(obj==last) {
						break;
					}
					while(true) {
						obj=obj.getParent();
						if(obj==last) {
							return;
						}
						if(obj.getSibling()!=null) {
							obj=obj.getSibling();
							break;
						}
					}
				}
				
				panel.getLabels2drawing(obj.getParent(), obj);
		}
		
		
		
		
		
		}
 	public void mouseDragged(MouseEvent e){///////////////////////////////////////////////
		int [] finalLimit = new int [4];
		finalLimit= checkArea(label, child, child.getParent(), childs); //여기서 라벨 위치 조정.
 		if(isDragged) {
 			offX=e.getX()+dot.getX()-x;
 			offY=e.getY()+dot.getY()-y;
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
			
			
			if(label.getX()<finalLimit[1]) {				
	 			label.setLocation(finalLimit[1],label.getY());
	 			label.setSize(labelX+labelW-finalLimit[1],label.getHeight());
	 			dot.setLocation(label.getX()-dot.getWidth(),dot.getY());
	 		}
	 		else if(label.getX()>finalLimit[0]-child.getLabel().getWidth()) {
	 			label.setLocation(label.getX(),label.getY());
	 			label.setSize(finalLimit[0]-labelX,label.getHeight());
	 			dot.setLocation(label.getX()+label.getWidth(),dot.getY());
	 			
	 		}
	 		if(label.getY()<finalLimit[3]) {
	 			label.setLocation(label.getX(),finalLimit[3]);
	 			label.setSize(label.getWidth(),labelY+labelH-finalLimit[3]);
	 			dot.setLocation(dot.getX(),label.getY()-dot.getHeight());
	 			
	 		}
	 		else if(label.getY()>finalLimit[2]-label.getHeight()) {
	 			label.setLocation(label.getX(),label.getY());
	 			label.setSize(label.getWidth(),finalLimit[2]-labelY);
	 			dot.setLocation(dot.getX(),label.getY());
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
			
 		}
 	}
	
 	public void mouseEntered(MouseEvent e) {
 		dot=(JLabel)e.getSource();
 		Cursor cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
 		dot.setCursor(cursor);
 		dot.setVisible(false);
 		dot.setVisible(true);
////
//// 		dot.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
// 		System.out.println(dot.getCursor());
// 		
//// 		dot.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
// 		System.out.println("고인사이드");
 	}
 	
 	public void mouseClicked(MouseEvent e) {
 		
 	}
	
	
}