
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;



class BasicFramework extends JFrame {
	
	private JSplitPane splitPaneOne, splitPaneTwo;	
	private Mindmap mindmapSection;
	private Attribute attributeSection;
	private Text textSection;
	
	public BasicFramework() {
		
		setTitle("DrawMindMap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		mindmapSection = new Mindmap();
		attributeSection = new Attribute(mindmapSection);
		Bar bar = new Bar(c, mindmapSection);
		textSection = new Text(mindmapSection, bar);
		
		
		splitPaneOne = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPaneTwo = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		splitPaneOne.setLeftComponent(textSection.textEditorPanel); //좌측 컴포넌트 장착
		splitPaneOne.setRightComponent(splitPaneTwo); //우측 컴포넌트 장착
		splitPaneOne.setDividerLocation(250); //디바이더(분리대) 위치 설정 
		splitPaneOne.setDividerSize(10); //디바이더(분리대) 굵기 설정
			
		splitPaneTwo.setLeftComponent(mindmapSection.mindMapPanel); //좌측 컴포넌트 장착
		splitPaneTwo.setRightComponent(attributeSection.attributePanel); //우측 컴포넌트 장착
		splitPaneTwo.setDividerLocation(650); //디바이더(분리대) 위치 설정     
		splitPaneTwo.setDividerSize(10); //디바이더(분리대) 굵기 설정
		
		c.add(splitPaneOne, BorderLayout.CENTER);
		
		setSize(1200, 700);
		setResizable(false);
		setVisible(true);
	}
}




public class DrawMindMap {

	public static void main(String[] args) {

		new BasicFramework();
		
	}

}