module com.example {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.xml;

	requires javafx.media;

	requires com.google.gson;


	opens com.example.Model.user to com.google.gson;
	opens com.example.Model.UserAction to com.google.gson;
	opens com.example.Model.resource to com.google.gson;
	opens com.example.Model.tile to com.google.gson;
	opens com.example.Model.unit to com.google.gson;
	opens com.example.Model.city to com.google.gson;
	opens com.example.Model to com.google.gson;

	opens com.example;
	opens com.example.ViewController to javafx.fxml;


	opens com.example.ViewController.popupController to javafx.fxml;

	exports com.example;
	exports com.example.View;
//	 exports com.example.ViewController;
}
