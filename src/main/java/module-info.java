module com.example {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.xml;

	requires javafx.media;

	requires com.google.gson;


	opens com.example.Model.user to com.google.gson;

	opens com.example to javafx.fxml;
	opens com.example.ViewController to javafx.fxml;


	opens com.example.ViewController.popupController to javafx.fxml;

	exports com.example;
	exports com.example.View;
//	 exports com.example.ViewController;
}
