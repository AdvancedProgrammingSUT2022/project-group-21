package com.example.View;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javafx.scene.Cursor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.App;
import com.example.Contoller.GameController;
import com.example.Model.Civilization;
import com.example.Model.Game;
import com.example.Model.Technology;
import com.example.Model.UserAction.CivilizationUserAction;
import com.example.Model.UserAction.UserActionQuery;
import com.example.Model.user.User;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class TechnologyTree {
	private static final int buttonW=120, buttonH=40;
	private static final int gapW=60, gapH=25;

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
		
		HashMap<Technology, Button> buttons = new HashMap<>();
		
		Element technologies = (Element) root.getElementsByTagName("Technologies").item(0);
		NodeList nodeList = technologies.getElementsByTagName("Row");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() != Node.ELEMENT_NODE) continue ;
			
			Element element = (Element) node;
			String techName = getValueOfElement(element, "Type").substring(5);
			Technology tech;
			try {
				tech=Technology.valueOf(techName);
			} catch (Exception e) {
				continue ;
			}
			buttons.put(tech, getTechButton(element));
		}

		for (Technology tech1 : Technology.values()) {
			for (Technology tech2 : tech1.prequisiteTechs) {
				anchorPane.getChildren().add(createLineBetweenTechButtons(buttons.get(tech2), buttons.get(tech1)));
			}
		}
		for (Button button : buttons.values()) {
			button.setCursor(Cursor.HAND);
			anchorPane.getChildren().add(button);
		}
	}

	private static String getValueOfElement(Element element, String tagName){
		return element.getElementsByTagName(tagName).item(0).getChildNodes().item(0).getNodeValue();
	}

	private static Technology getTechFromElement(Element element){
		return Technology.valueOf(getValueOfElement(element, "Type").substring(5));
	}

	private static Button getTechButton(Element element){
		Technology technology = getTechFromElement(element);
		int x = Integer.parseInt(getValueOfElement(element, "GridX"));
		int y = Integer.parseInt(getValueOfElement(element, "GridY"));
		
		Button button = new Button(technology.name);
		button.setPrefWidth(buttonW);
		button.setPrefHeight(buttonH);
		
		button.setLayoutX((buttonW+gapW)*x + 20);
		button.setLayoutY((buttonH+gapH)*y);

		
		User user = Game.getInstance().getCurrentPlayer();
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				UserActionQuery query = CivilizationUserAction.setResearch(user.getUsername(), technology);
				GameController.getInstance().handleQueryFromView(query);
            }
		});
		applyCivilizationToButton(button, technology, user.getCivilization());

		return button;
	}

	private static Line createLineBetweenTechButtons(Button button1, Button button2){
		Line line = new Line();
		line.setStartX(button1.getLayoutX()+buttonW);
		line.setStartY(button1.getLayoutY()+buttonH/2);
		line.setEndX(button2.getLayoutX());
		line.setEndY(button2.getLayoutY()+buttonH/2);
		line.setStrokeWidth(2);
		return line;
	}

	private static void applyCivilizationToButton(Button button, Technology technology, Civilization civilization){		
		if (technology.canBeResearchedBy(civilization)) button.setDisable(false);
		else button.setDisable(true);
		if (civilization.hasTechnology(technology)) button.setStyle("-fx-background-color: #12ed0b;");
		else if (technology.canBeResearchedBy(civilization)) button.setStyle("-fx-background-color: #0be2ed;");
		else button.setStyle("-fx-background-color: #c16573;");
	}

}
