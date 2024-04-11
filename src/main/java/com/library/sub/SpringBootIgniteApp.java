// SpringBootIgniteApp.java - Main application
package com.library.sub;
import com.gluonhq.ignite.spring.SpringContext;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
    "com.gluonhq.ignite.spring",
    "com.library.core.service"
})
public class SpringBootIgniteApp extends Application 
        implements ExampleApp {
    
	
	  public static void main(String[] args) {
	  Application.launch(SpringBootIgniteApp.class, args); }
	 

    @Autowired
    private FXMLLoader loader;
    
    private final SpringContext context = new SpringContext(this);
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    	System.out.println("HELLO");
        context.init(() -> SpringApplication.run(SpringBootIgniteApp.class));
        loader.setLocation(getViewLocation());
        Parent primaryView = loader.load();
        stage.setTitle("Spring Boot Ignite Example");
        stage.setScene(scene = new Scene(primaryView));
        stage.show();
    }

    public static void setRoot(Parent view) {
        scene.setRoot(view);
    }
}
