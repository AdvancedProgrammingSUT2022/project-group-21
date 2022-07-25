package com.example.ViewController;

import com.example.Model.Civilization;
import com.example.Model.Game;
import com.example.Model.user.User;
import com.example.View.hexagons.MapPaneMaker;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class GamePageViewController {

    @FXML
    private AnchorPane infoAnchorPane;

    @FXML
    private ScrollPane scrollPane;

	@FXML
	private void initialize(){
		scrollPane.
                setContent(MapPaneMaker.createScrollPane(Game.getInstance(), Game.getInstance().getCurrentPlayer().getCivilization()));
	
	}
    public void addInfo(User user,User currentUser)
    {
        Label Label=new Label(user.getNickname());
        if(currentUser==user)
            Label.setText(Label.getText()+" (you)");
       Label.setLayoutX(0);
        Label.setLayoutY(infoAnchorPane.getHeight()/2);
        infoAnchorPane.getChildren().add(Label);
        Label=new Label("Gold: "+Integer.toString(user.getCivilization().getGold()));
        Label.setLayoutX(infoAnchorPane.getWidth()/5);
        Label.setLayoutY(infoAnchorPane.getHeight()/2);
        infoAnchorPane.getChildren().add(Label);
        Label=new Label("Happiness: "+Integer.toString(user.getCivilization().getHappiness()));
        Label.setLayoutX(infoAnchorPane.getWidth()*2/5);
        Label.setLayoutY(infoAnchorPane.getHeight()/2);
        infoAnchorPane.getChildren().add(Label);
        Label=new Label("Score: "+Integer.toString(user.getCivilization().getScore()));
        Label.setLayoutX(infoAnchorPane.getWidth()*3/5);
        Label.setLayoutY(infoAnchorPane.getHeight()/2);
        infoAnchorPane.getChildren().add(Label);
        Label=new Label("Science: "+Integer.toString(user.getCivilization().getScience()));
        Label.setLayoutX(infoAnchorPane.getWidth()*4/5);
        Label.setLayoutY(infoAnchorPane.getHeight()/2);
        infoAnchorPane.getChildren().add(Label);
    }

}
