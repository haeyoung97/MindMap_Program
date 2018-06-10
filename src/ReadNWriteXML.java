
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class WriteXMLFile {
	private String Name, filePath;
	private Data start, last;
	private boolean isSave;
	
	public WriteXMLFile(String filePath, Data start, Data last, boolean isSave) {
		if(start != null)
			this.Name = start.getValue();
		else
			this.Name = null;
		this.filePath = filePath+Name;
		this.start = start;
		this.last = last;
		this.isSave = isSave;
	}
	
 	public WriteXMLFile(String filePath, String Name, Data start, Data last, boolean isSave) {
		this.Name = Name;
		this.filePath = filePath;
		this.start = start;
		this.last = last;
		this.isSave = isSave;
	}

	void WriteFile(boolean isData) {
		System.out.println("����ȴ�!");
		System.out.println(filePath);
		System.out.println(Name);
//		System.out.println(start.toString());
//		if(start == null) {
//			JOptionPane.showMessageDialog(null, "������ �����Ͱ� �����ϴ�.", "Not Saved", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
		if(!isData) {
			JOptionPane.showMessageDialog(null, "�����ư�� Ŭ�� �� �� ������ �� �ֽ��ϴ�.", "Not Saved", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(start == null) {
			JOptionPane.showMessageDialog(null, "������ �����Ͱ� �����ϴ�.", "Not Saved", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try{
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// ��Ʈ ������Ʈ
			Document doc = docBuilder.newDocument();
			Element fileName = doc.createElement(Name);
			doc.appendChild(fileName);
			Data k = start;
			
			if(k != null) {
				// value ������Ʈ
				Element node = doc.createElement("node");
				fileName.appendChild(node);
				System.out.println("node : "+k.getValue() + ", x : "+k.getStrX()+", y : "+k.getStrY()+", R : "+k.getStrS()+", H : "+k.getStrH()+", W : "+k.getStrW());
				// string ������Ʈ
				Element Value = doc.createElement("Value");
				Value.appendChild(doc.createTextNode(k.getValue()));
				node.appendChild(Value);
				
				// X ������Ʈ
				Element X = doc.createElement("X");
				X.appendChild(doc.createTextNode(k.getStrX()));
				node.appendChild(X);

				// Y ������Ʈ
				Element Y = doc.createElement("Y");
				Y.appendChild(doc.createTextNode(k.getStrY()));
				node.appendChild(Y);

				// R ������Ʈ
				Element R = doc.createElement("R");
				R.appendChild(doc.createTextNode(k.getStrHeight()));
				node.appendChild(R);
				
				// H ������Ʈ
				Element H = doc.createElement("H");
				H.appendChild(doc.createTextNode(k.getStrH()));
				node.appendChild(H);
							
				// W ������Ʈ
				Element W = doc.createElement("W");
				W.appendChild(doc.createTextNode(k.getStrW()));
				node.appendChild(W);

				// ColorR ������Ʈ
				Element ColorR = doc.createElement("ColorR");
				ColorR.appendChild(doc.createTextNode(""+k.getColorR()));
				node.appendChild(ColorR);
							
				// ColorR ������Ʈ
				Element ColorG = doc.createElement("ColorG");
				ColorG.appendChild(doc.createTextNode(""+k.getColorG()));
				node.appendChild(ColorG);
				
				// ColorR ������Ʈ
				Element ColorB = doc.createElement("ColorB");
				ColorB.appendChild(doc.createTextNode(""+k.getColorB()));
				node.appendChild(ColorB);
				
				
				while(true) {
					if(k.getChild() != null) {
						k = k.getChild();
					}
					else if(k.getSibling() != null) {
						k = k.getSibling();
					}
					else {
						if(k == last) {
							break;
						}
						while(true) {
							k=k.getParent();
							if(k.getSibling()!=null) {
								k=k.getSibling();
								break;
							}
						}
					}
					// value ������Ʈ
					node = doc.createElement("node");
					fileName.appendChild(node);
					
					// string ������Ʈ
					Value = doc.createElement("Value");
					Value.appendChild(doc.createTextNode(k.getValue()));
					node.appendChild(Value);
					
					// X ������Ʈ
					X = doc.createElement("X");
					X.appendChild(doc.createTextNode(k.getStrX()));
					node.appendChild(X);

					// Y ������Ʈ
					Y = doc.createElement("Y");
					Y.appendChild(doc.createTextNode(k.getStrY()));
					node.appendChild(Y);

					// R ������Ʈ
					R = doc.createElement("R");
					R.appendChild(doc.createTextNode(k.getStrHeight()));
					node.appendChild(R);
					
					// H ������Ʈ
					H = doc.createElement("H");
					H.appendChild(doc.createTextNode(k.getStrH()));
					node.appendChild(H);
								
					// W ������Ʈ
					W = doc.createElement("W");
					W.appendChild(doc.createTextNode(k.getStrW()));
					node.appendChild(W);
					
					// ColorR ������Ʈ
					ColorR = doc.createElement("ColorR");
					ColorR.appendChild(doc.createTextNode(""+k.getColorR()));
					node.appendChild(ColorR);
								
					// ColorR ������Ʈ
					ColorG = doc.createElement("ColorG");
					ColorG.appendChild(doc.createTextNode(""+k.getColorG()));
					node.appendChild(ColorG);
					
					// ColorR ������Ʈ
					ColorB = doc.createElement("ColorB");
					ColorB.appendChild(doc.createTextNode(""+k.getColorB()));
					node.appendChild(ColorB);
					
					
				}
			}
			

			// XML ���Ϸ� ����
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			transformer.setOutputProperty(OutputKeys.ENCODING, "euc-KR");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");	
			DOMSource source = new DOMSource(doc);
//			StreamResult result = new StreamResult(new FileOutputStream(new File(filePath + ".xml"), false));
			StreamResult result = new StreamResult(new File(filePath + ".xml"));
			
			if(isSave) {
				JOptionPane.showMessageDialog(null, start.getValue()+".xml is Saved in DeskTop", "Complete", JOptionPane.OK_OPTION);
				
			}
			
			
			// ���Ϸ� ���� �ʰ� �ֿܼ� ���� ���� ��� ������ ��� (������)
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");
			
		}
		catch (ParserConfigurationException pce)
		{
			pce.printStackTrace();
//			return false;
		}
		catch (TransformerException tfe)
		{
			tfe.printStackTrace();
//			return false;
		}
//		catch (FileNotFoundException fnfe) {
//			fnfe.printStackbTrace();
////			return false;
//		}
//		return true;
	}
	
}

class SaveButtonListener implements ActionListener{
	
	private JFileChooser chooser;
	private Data start, last;
	private boolean isData;
	private boolean isSave = false;
	private WriteXMLFile writeXML;
	private String filePath, fileName;
	
	public SaveButtonListener() {
		chooser = new JFileChooser();
		isData = false;
	}
	
	public SaveButtonListener(Data start, Data last) {
		this.start = start;
		this.last = last;
		isData = true;
		System.out.println("start : " + start);
	}
	
	public void actionPerformed(ActionEvent e) {
//		if(!isSave) {
			chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("XML ���� ����", "xml");
			chooser.setFileFilter(filter);
			if(e.getActionCommand().equals("Save")) {
				chooser.setDialogTitle("Save");
				chooser.setApproveButtonText("Save");
				filePath = "C:\\Users\\���ؿ�\\Desktop\\"; // ���� ��θ� ����
				
				writeXML = new WriteXMLFile(filePath, start, last, true);
				writeXML.WriteFile(this.isData);
				
			}
			else {
				chooser.setDialogTitle("Save as...");
				chooser.setApproveButtonText("Save as...");
				
				int ret = chooser.showSaveDialog(null);
				if(ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "�ش� �����͸� �������� �ʾҽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
					return;
				}
				filePath = chooser.getSelectedFile().getAbsolutePath(); // ���� ��θ� ����
				fileName = chooser.getSelectedFile().getName(); // ������ �̸�.Ȯ����
				
				writeXML = new WriteXMLFile(filePath, fileName, start, last, false);
				writeXML.WriteFile(this.isData);
			}
			
//			filePath = chooser.getSelectedFile().getAbsolutePath(); // ���� ��θ� ����
//			fileName = chooser.getSelectedFile().getName(); // ������ �̸�.Ȯ����
//			writeXML = new WriteXMLFile(filePath, fileName, start, last);
//			writeXML.WriteFile(this.isData);
//		}
//		else
//			isSave = writeXML.WriteFile(this.isData);
////		
		
	}
}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class ReadXMLFile {
	private String path;
	private JTextArea textEdit;
	private StringBuffer textAreaStr = new StringBuffer();
	private Mindmap mindmapSection;
	private Bar b;
	private Tree tree;
	public ReadXMLFile(JPanel attributeFieldPane, String path, JTextArea textEdit, Mindmap mindmapSection, Bar b, SaveButtonListener saveListener) {
		this.path = path;
		this.textEdit = textEdit;
		this.mindmapSection = mindmapSection;
		this.b = b;
		ReadFile(attributeFieldPane, saveListener);
	}
	
    void ReadFile(JPanel attributeFieldPane, SaveButtonListener saveListener) {
  
      try {
        File fXmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
  
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("node");
        System.out.println("-----------------------");
        Node nNode = nList.item(0);
        Element eElement = (Element) nNode;
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	nNode = nList.item(temp);
        	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        		eElement = (Element) nNode;
        		int rank = Integer.parseInt(getTagValue("R", eElement));
        		while(true) {
        			if(rank == 0) {
        				break;
        			}
        			rank--;
        			textAreaStr = textAreaStr.append("\t");		
        		}
            	textAreaStr = textAreaStr.append(getTagValue("Value", eElement)+"\n");

           }
        }
    	System.out.println(textAreaStr);
    	String fixedStr = textAreaStr.toString();
    	textEdit.setText(fixedStr);
    	ButtonListener treeButton = new ButtonListener(attributeFieldPane, textEdit, mindmapSection, b, saveListener);
    	treeButton.ApplyButtonFunc();
    	this.tree = treeButton.getTree();
    	System.out.println("�׽�Ʈ ����@@@@@@@@@@@@@@@@@@@@@@@@@@");
    	tree.print();
    	Data k = tree.getStart();
    	Data kLast = tree.getLast();
    	mindmapSection.drawNodePanel.reset();
    	int i=0;
    	while(true) {
    		nNode = nList.item(i);
            eElement = (Element) nNode;
            System.out.println("-----------------------");
            System.out.println("Value : " + getTagValue("Value", eElement));
        	System.out.println("X : " + getTagValue("X", eElement));
        	System.out.println("Y : " + getTagValue("Y", eElement));
//        	System.out.println("R : " + getTagValue("R", eElement));
        	System.out.println("H : " + getTagValue("H", eElement));
        	System.out.println("W : " + getTagValue("W", eElement));
//        	System.out.println("Color : " + getTagValue("Color", eElement));
    		k.setX(Integer.parseInt(getTagValue("X", eElement)));
    		k.setY(Integer.parseInt(getTagValue("Y", eElement)));
    		k.setH(Integer.parseInt(getTagValue("H", eElement)));
    		k.setW(Integer.parseInt(getTagValue("W", eElement)));
    		k.setColorR(Integer.parseInt(getTagValue("ColorR", eElement)));
    		k.setColorG(Integer.parseInt(getTagValue("ColorG", eElement)));
    		k.setColorB(Integer.parseInt(getTagValue("ColorB", eElement)));
    		k.getLabel().setBackground(new Color(k.getColorR(),k.getColorG(),k.getColorB()));
    		k.getLabel().setLocation(k.getX(),k.getY());//////////////////////////////////////////////////////////
    		mindmapSection.drawNodePanel.getLabels2drawing(k.getParent(),k);
    		if(k.getChild() != null) {
    			k = k.getChild();
    		}
    		else if(k.getSibling() != null) {
    			k = k.getSibling();
    		}
    		else {
				if(k == kLast) {
					break;
				}
				while(true) {
					k=k.getParent();
					if(k.getSibling()!=null) {
						k=k.getSibling();
						break;
					}
				}
			}
    		if(i == nList.getLength())
    			break;
    		i++;
//    		k.setW(Integer.parseInt(getTagValue("Color", eElement)));
    	}
    	mindmapSection.drawNodePanel.setVisible(false);
    	mindmapSection.drawNodePanel.setVisible(true);
//    	System.out.println("�׽�Ʈ �غ��� : " + k.getValue());
//        	while(true) {
//        		
//        	}
//        	System.out.println("tree.start : " + start);
//        }
      } catch (Exception e) {
        e.printStackTrace();
      }
  }
  
    private static String getTagValue(String sTag, Element eElement) {
    	NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();
    }
}

class OpenButtonListener implements ActionListener{
	
	private JFileChooser chooser;
	private JTextArea textEdit;
	private Mindmap mindmapSection;
	private JPanel attributeFieldPane;
	private SaveButtonListener saveListener;
	private Bar b;
	
	public OpenButtonListener(JPanel attributeFieldPane, JTextArea textEdit, Mindmap mindmapSection, Bar b, SaveButtonListener saveListener) {
		chooser = new JFileChooser();
		chooser.setDialogTitle("Open");
		chooser.setApproveButtonText("Open");
		this.textEdit = textEdit;
		this.mindmapSection = mindmapSection;
		this.attributeFieldPane = attributeFieldPane;
		this.saveListener = saveListener;
		this.b = b;
	}
	
	public void actionPerformed(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML ���� ����", "xml");
		chooser.setFileFilter(filter);
		
		int ret = chooser.showOpenDialog(null);
		if(ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String filePath = chooser.getSelectedFile().getPath(); // ���� ��θ� ����
		new ReadXMLFile(attributeFieldPane, filePath, textEdit, mindmapSection, b, saveListener);
//		String fileName = chooser.getSelectedFile().getName(); // ������ �̸�.Ȯ����
		
	}
}