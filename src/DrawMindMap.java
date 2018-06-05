
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

		splitPaneOne.setLeftComponent(textSection.textEditorPanel); //���� ������Ʈ ����
		splitPaneOne.setRightComponent(splitPaneTwo); //���� ������Ʈ ����
		splitPaneOne.setDividerLocation(250); //����̴�(�и���) ��ġ ���� 
		splitPaneOne.setDividerSize(10); //����̴�(�и���) ���� ����
			
		splitPaneTwo.setLeftComponent(mindmapSection.mindMapPanel); //���� ������Ʈ ����
		splitPaneTwo.setRightComponent(attributeSection.attributePanel); //���� ������Ʈ ����
		splitPaneTwo.setDividerLocation(650); //����̴�(�и���) ��ġ ����     
		splitPaneTwo.setDividerSize(10); //����̴�(�и���) ���� ����
		
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