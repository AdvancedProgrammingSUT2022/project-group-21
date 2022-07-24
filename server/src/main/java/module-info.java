module com.example {
	requires com.google.gson;

	opens com.example.Model.user to com.google.gson;

	exports com.example;
}
