module com.example {
	requires javafx.controls;
	requires javafx.fxml;

	requires com.google.gson;

	opens com.example.Model.user to com.google.gson;

	opens com.example to javafx.fxml;
	exports com.example;
}
