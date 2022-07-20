package com.example.View;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.App;
import com.example.Model.Technology;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class TechnologyTree {
	private static final int buttonW=120, buttonH=40;

	private static Document document;

	private static boolean initialize(){
		if (document!=null) return true;
		try {
			URL url = App.class.getResource("assets/Technology/CIV5Technologies.xml");
			File file = new File(url.toURI());
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			document = documentBuilder.parse(file);
		} catch (Exception e) {
			System.out.println("Error: cant open TechTree xml file");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void putTechnologiesOnAnchorPane(AnchorPane anchorPane){
		if (!initialize()) return ;
		Element root = document.getDocumentElement();
		System.out.println("root: "+root);
		
		
		ArrayList<Element> techElements = new ArrayList<>();
		
		Element technologies = (Element) root.getElementsByTagName("Technologies").item(0);
		NodeList nodeList = technologies.getElementsByTagName("Row");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() != Node.ELEMENT_NODE) continue ;
			
			Element element = (Element) node;
			String techName = getValueOfElement(element, "Type").substring(5);
			try {
				Technology.valueOf(techName);
			} catch (Exception e) {
				continue ;
			}
			techElements.add(element);
		}
		
		GridPane gridPane = new GridPane();
		for (Element element : techElements) {
			int x = Integer.parseInt(getValueOfElement(element, "GridX"));
			int y = Integer.parseInt(getValueOfElement(element, "GridY"));
			gridPane.add(getTechButton(element), x, y);
		}
		gridPane.setPrefWidth(2000);
		gridPane.setPrefHeight(anchorPane.getHeight());
		gridPane.setMinHeight(anchorPane.getHeight());
		gridPane.setMaxHeight(anchorPane.getHeight());
		anchorPane.getChildren().add(gridPane);

	}

	private static String getValueOfElement(Element element, String tagName){
		return element.getElementsByTagName(tagName).item(0).getChildNodes().item(0).getNodeValue();
	}

	private static Technology getTechFromElement(Element element){
		return Technology.valueOf(getValueOfElement(element, "Type").substring(5));
	}

	private static Button getTechButton(Element element){
		Technology technology = getTechFromElement(element);
		Button button = new Button(technology.name);
		button.setPrefWidth(buttonW);
		button.setPrefHeight(buttonH);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// TODO: add button action
			}
		});
		return button;
	}

}
