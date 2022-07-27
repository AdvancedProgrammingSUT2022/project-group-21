module com.example {
	requires com.google.gson;

	opens com.example.Model.user to com.google.gson;
	opens com.example.Network to com.google.gson;

	opens com.example.Model.UserAction to com.google.gson;
	opens com.example.Model.resource to com.google.gson;
	opens com.example.Model.tile to com.google.gson;
	opens com.example.Model.unit to com.google.gson;
	opens com.example.Model.city to com.google.gson;
	opens com.example.Model to com.google.gson;
	
	opens com.example;

	exports com.example;
}
