module Hospital2 {
	requires javafx.controls;
	
	opens application to javafx.graphics, javafx.fxml;
	
	
    requires javafx.fxml;
    opens controller;
    exports controller;
    
    
    requires java.desktop;
    requires java.sql;
}
