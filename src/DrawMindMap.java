import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
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
		Bar bar = new Bar(c, mindmapSection);
		attributeSection = new Attribute(mindmapSection, bar);
		textSection = new Text(attributeSection, mindmapSection, bar);
		
		splitPaneOne = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPaneTwo = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		splitPaneOne.setLeftComponent(textSection.textEditorPanel); //���� ������Ʈ ����
		splitPaneOne.setRightComponent(splitPaneTwo); //���� ������Ʈ ����
		splitPaneOne.setDividerLocation(250); //����̴�(�и���) ��ġ ���� 
		splitPaneOne.setDividerSize(10); //����̴�(�и���) ���� ����
		splitPaneOne.setEnabled(false);
			
		splitPaneTwo.setLeftComponent(mindmapSection.mindMapPanel); //���� ������Ʈ ����
		splitPaneTwo.setRightComponent(attributeSection.attributePanel); //���� ������Ʈ ����
		splitPaneTwo.setDividerLocation(900); //����̴�(�и���) ��ġ ����     
		splitPaneTwo.setDividerSize(10); //����̴�(�и���) ���� ����
		splitPaneTwo.setEnabled(false);
		c.add(splitPaneOne, BorderLayout.CENTER);
		
		setSize(1500, 900);
		setResizable(false);
		setVisible(true);
	}
}




public class DrawMindMap {

	public static void main(String[] args) {

		new BasicFramework();
		
	}
}