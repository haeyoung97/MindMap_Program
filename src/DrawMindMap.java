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

		splitPaneOne.setContinuousLayout(true); //�������� ���̾ƿ� ��� Ȱ��ȭ
		splitPaneOne.setLeftComponent(textSection.textEditorPanel); //���� ������Ʈ ����
		splitPaneOne.setRightComponent(splitPaneTwo); //���� ������Ʈ ����
//		splitPaneOne.setDividerLocation((int)c.getSize().getWidth()/2); //����̴�(�и���) ��ġ ����     
		splitPaneOne.setDividerSize(15); //����̴�(�и���) ���� ����
			
		splitPaneTwo.setContinuousLayout(true); //�������� ���̾ƿ� ��� Ȱ��ȭ
		splitPaneTwo.setLeftComponent(mindmapSection.mindMapPanel); //���� ������Ʈ ����
		splitPaneTwo.setRightComponent(attributeSection.attributePanel); //���� ������Ʈ ����
//		splitPaneTwo.setDividerLocation((int)c.getSize().getWidth()/2); //����̴�(�и���) ��ġ ����     
		splitPaneOne.setDividerSize(15); //����̴�(�и���) ���� ����
		
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