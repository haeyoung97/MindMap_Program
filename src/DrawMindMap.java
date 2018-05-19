import java.lang.Class;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JMenu;

import java.awt.event.ActionEvent;

class BasicFramework extends JFrame {
	
	private JSplitPane splitPaneOne, splitPaneTwo;	
	private Bar menubar,toolbar;
	private Mindmap mindmapSection;
	private Attribute attributeSection;
	private Text textSection;
	
	public BasicFramework() {
		
		
		setTitle("DrawMindMap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		new Bar(c);		
		mindmapSection=new Mindmap();
		attributeSection=new Attribute(mindmapSection);
		textSection=new Text(mindmapSection);
		
		
		splitPaneOne = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPaneTwo = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		splitPaneOne.setContinuousLayout(true); //연속적인 레이아웃 기능 활성화
		splitPaneOne.setLeftComponent(textSection.textEditorPanel); //좌측 컴포넌트 장착
		splitPaneOne.setRightComponent(splitPaneTwo); //우측 컴포넌트 장착
//		splitPaneOne.setDividerLocation((int)c.getSize().getWidth()/2); //디바이더(분리대) 위치 설정     
		splitPaneOne.setDividerSize(15); //디바이더(분리대) 굵기 설정
			
		splitPaneTwo.setContinuousLayout(true); //연속적인 레이아웃 기능 활성화
		splitPaneTwo.setLeftComponent(mindmapSection.mindMapPanel); //좌측 컴포넌트 장착
		splitPaneTwo.setRightComponent(attributeSection.attributePanel); //우측 컴포넌트 장착
//		splitPaneTwo.setDividerLocation((int)c.getSize().getWidth()/2); //디바이더(분리대) 위치 설정     
		splitPaneOne.setDividerSize(15); //디바이더(분리대) 굵기 설정
		
		c.add(splitPaneOne, BorderLayout.CENTER);
		
		
		setSize(1000, 500);
		setVisible(true);
	}
}


public class DrawMindMap {

	public static void main(String[] args) {
		new BasicFramework();
	}

}