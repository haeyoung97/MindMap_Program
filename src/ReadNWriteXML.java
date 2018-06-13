
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

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
	private int isSave;
	
	public WriteXMLFile(String filePath, Data start, Data last, int isSave) {
		if(start != null)
			this.Name = start.getValue();
		else
			this.Name = null;
		this.filePath = filePath+Name;
		this.start = start;
		this.last = last;
		this.isSave = isSave;
	}
	
 	public WriteXMLFile(String filePath, String Name, Data start, Data last, int isSave) {
		this.Name = Name;
		this.filePath = filePath;
		this.start = start;
		this.last = last;
		this.isSave = isSave;
	}

	void WriteFile(boolean isData) {
		
		if(start == null) {
			JOptionPane.showMessageDialog(null, "저장할 데이터가 없습니다.", "Not Saved", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try{
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// 루트 엘리먼트
			Document doc = docBuilder.newDocument();
			Element fileName = doc.createElement(Name);
			doc.appendChild(fileName);
			Data k = start;
			
			if(k != null) {
				// value 엘리먼트
				Element node = doc.createElement("node");
				fileName.appendChild(node);
				// string 엘리먼트
				Element Value = doc.createElement("Value");
				Value.appendChild(doc.createTextNode(k.getValue()));
				node.appendChild(Value);
				
				// X 엘리먼트
				Element X = doc.createElement("X");
				X.appendChild(doc.createTextNode(k.getStrX()));
				node.appendChild(X);

				// Y 엘리먼트
				Element Y = doc.createElement("Y");
				Y.appendChild(doc.createTextNode(k.getStrY()));
				node.appendChild(Y);

				// R 엘리먼트
				Element R = doc.createElement("R");
				R.appendChild(doc.createTextNode(k.getStrHeight()));
				node.appendChild(R);
				
				// H 엘리먼트
				Element H = doc.createElement("H");
				H.appendChild(doc.createTextNode(k.getStrH()));
				node.appendChild(H);
							
				// W 엘리먼트
				Element W = doc.createElement("W");
				W.appendChild(doc.createTextNode(k.getStrW()));
				node.appendChild(W);

				// ColorR 엘리먼트
				Element ColorR = doc.createElement("ColorR");
				ColorR.appendChild(doc.createTextNode(""+k.getColorR()));
				node.appendChild(ColorR);
							
				// ColorR 엘리먼트
				Element ColorG = doc.createElement("ColorG");
				ColorG.appendChild(doc.createTextNode(""+k.getColorG()));
				node.appendChild(ColorG);
				
				// ColorR 엘리먼트
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
					// value 엘리먼트
					node = doc.createElement("node");
					fileName.appendChild(node);
					
					// string 엘리먼트
					Value = doc.createElement("Value");
					Value.appendChild(doc.createTextNode(k.getValue()));
					node.appendChild(Value);
					
					// X 엘리먼트
					X = doc.createElement("X");
					X.appendChild(doc.createTextNode(k.getStrX()));
					node.appendChild(X);

					// Y 엘리먼트
					Y = doc.createElement("Y");
					Y.appendChild(doc.createTextNode(k.getStrY()));
					node.appendChild(Y);

					// R 엘리먼트
					R = doc.createElement("R");
					R.appendChild(doc.createTextNode(k.getStrHeight()));
					node.appendChild(R);
					
					// H 엘리먼트
					H = doc.createElement("H");
					H.appendChild(doc.createTextNode(k.getStrH()));
					node.appendChild(H);
								
					// W 엘리먼트
					W = doc.createElement("W");
					W.appendChild(doc.createTextNode(k.getStrW()));
					node.appendChild(W);
					
					// ColorR 엘리먼트
					ColorR = doc.createElement("ColorR");
					ColorR.appendChild(doc.createTextNode(""+k.getColorR()));
					node.appendChild(ColorR);
								
					// ColorR 엘리먼트
					ColorG = doc.createElement("ColorG");
					ColorG.appendChild(doc.createTextNode(""+k.getColorG()));
					node.appendChild(ColorG);
					
					// ColorR 엘리먼트
					ColorB = doc.createElement("ColorB");
					ColorB.appendChild(doc.createTextNode(""+k.getColorB()));
					node.appendChild(ColorB);
					
					
				}
			}
			

			// XML 파일로 쓰기
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			transformer.setOutputProperty(OutputKeys.ENCODING, "euc-KR");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");	
			DOMSource source = new DOMSource(doc);
//			StreamResult result = new StreamResult(new FileOutputStream(new File(filePath + ".xml"), false));
			StreamResult result = new StreamResult(new File(filePath + ".xml"));
			
			if(isSave == 0) {
				JOptionPane.showMessageDialog(null, start.getValue()+".xml is Saved in DeskTop", "Complete", JOptionPane.INFORMATION_MESSAGE);
				
			}
			if(isSave == 1) {
				JOptionPane.showMessageDialog(null, "File is saved", "Complete", JOptionPane.INFORMATION_MESSAGE);
				
			}
			
			transformer.transform(source, result);

			
		}
		catch (ParserConfigurationException pce)
		{
			pce.printStackTrace();
		}
		catch (TransformerException tfe)
		{
			tfe.printStackTrace();
		}
	}
	
}

class SaveButtonListener implements ActionListener{
	
	private JFileChooser chooser;
	private Data start, last;
	private boolean isData;
	private int isSave;
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
		
	}
	
	public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Save")) {
				filePath = System.getProperty("user.home") + "\\Desktop\\";
				
				writeXML = new WriteXMLFile(filePath, start, last, 0);
				writeXML.WriteFile(this.isData);
				
			}
			else {
				chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("XML 문서 파일", "xml");
				chooser.setFileFilter(filter);
				chooser.setDialogTitle("Save as...");
				chooser.setApproveButtonText("Save as...");
				
				int ret = chooser.showSaveDialog(null);
				if(ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "해당 데이터를 저장하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}
				filePath = chooser.getSelectedFile().getAbsolutePath(); // 파일 경로명 리턴
				fileName = chooser.getSelectedFile().getName(); // 파일의 이름.확장자
				
				writeXML = new WriteXMLFile(filePath, fileName, start, last, 1);
				writeXML.WriteFile(this.isData);
			}
			
		
	}
}


class ReadXMLFile {
	private String path;
	private JTextArea textEdit;
	private StringBuffer textAreaStr = new StringBuffer();
	private Mindmap mindmapSection;
	private Bar b;
	private Tree tree;
	private ButtonListener applyListener;
	public ReadXMLFile(JPanel attributeFieldPane, String path, JTextArea textEdit, Mindmap mindmapSection, Bar b, SaveButtonListener saveListener,ButtonListener applyListener) {
		this.path = path;
		this.textEdit = textEdit;
		this.mindmapSection = mindmapSection;
		this.b = b;
		this.applyListener=applyListener;
		ReadFile(attributeFieldPane, saveListener, applyListener);
		
	}
	
    void ReadFile(JPanel attributeFieldPane, SaveButtonListener saveListener, ButtonListener applyListener) {
  
      try {
        File fXmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
  
        NodeList nList = doc.getElementsByTagName("node");
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
    	String fixedStr = textAreaStr.toString();
    	textEdit.setText(fixedStr);
    	mindmapSection.drawNodePanel.datas.clear();
    	
    	applyListener.ApplyButtonFunc();
    	this.tree = applyListener.getTree();
    	tree.print();
    	Data k = tree.getStart();
    	Data kLast = tree.getLast();
    	mindmapSection.drawNodePanel.reset();
    	int i=0;
    	while(true) {
    		nNode = nList.item(i);
            eElement = (Element) nNode;
            k.setX(Integer.parseInt(getTagValue("X", eElement)));
    		k.setY(Integer.parseInt(getTagValue("Y", eElement)));
    		k.setH(Integer.parseInt(getTagValue("H", eElement)));
    		k.setW(Integer.parseInt(getTagValue("W", eElement)));
    		k.setColorR(Integer.parseInt(getTagValue("ColorR", eElement)));
    		k.setColorG(Integer.parseInt(getTagValue("ColorG", eElement)));
    		k.setColorB(Integer.parseInt(getTagValue("ColorB", eElement)));
    		k.setColorStrRGB();
    		k.getLabel().setBackground(new Color(k.getColorR(),k.getColorG(),k.getColorB()));
    		k.getLabel().setLocation(k.getX(),k.getY());//////////////////////////////////////////////////////////
    		k.getLabel().setSize(k.getW(), k.getH());
    		mindmapSection.drawNodePanel.getLabels2drawing(k.getParent(),k);
    		mindmapSection.drawNodePanel.datas.add(k);
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
    	}
    	
    	
    	
    	mindmapSection.drawNodePanel.setVisible(false);
    	mindmapSection.drawNodePanel.setVisible(true);
      } 
      catch (FileNotFoundException e) {
    	  JOptionPane.showMessageDialog(null, "지정된 파일을 찾을 수 없습니다.", "No Search file", JOptionPane.ERROR_MESSAGE);
			return;
      }
      catch (Exception e) {
    	  return;
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
	private ButtonListener applyListener;
	public OpenButtonListener(JPanel attributeFieldPane, JTextArea textEdit, Mindmap mindmapSection, Bar b,ButtonListener applyListener) {
		chooser = new JFileChooser();
		chooser.setDialogTitle("Open");
		chooser.setApproveButtonText("Open");
		this.textEdit = textEdit;
		this.mindmapSection = mindmapSection;
		this.attributeFieldPane = attributeFieldPane;
		this.b = b;
		
		this.applyListener=applyListener;
		
	}
	
	public void actionPerformed(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML 문서 파일", "xml");
		chooser.setFileFilter(filter);
		
		int ret = chooser.showOpenDialog(null);
		if(ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String filePath = chooser.getSelectedFile().getPath(); // 파일 경로명 리턴
		new ReadXMLFile(attributeFieldPane, filePath, textEdit, mindmapSection, b, saveListener,applyListener);
		
	}
}