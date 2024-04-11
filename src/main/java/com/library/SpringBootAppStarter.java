package com.library;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.ComponentScan;
import com.gluonhq.ignite.spring.SpringContext;
import com.library.core.constant.PathConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan({
	"com.library",
	"com.gluonhq.ignite.spring",
    "com.library.core.service"
})
@Slf4j
public class SpringBootAppStarter extends Application{

	public static void main(String[] args) {
		Application.launch(SpringBootAppStarter.class, args);
	}

    @Autowired
    private FXMLLoader fxmlLoader;
    
    private final SpringContext springContext = new SpringContext(this);
    private static Scene scene;
    private URL libraryHomeResouceURL = getClass().getResource(PathConstants.lIBRARY_HOME_FXML); 

    
	@Override
	public void start(Stage primaryStage) throws Exception {
		log.debug("Starting Library");
		log.debug("Initializing Spring ApplicationContext");
		//applicationContext = SpringApplication.run(Library.class);
		springContext.init(() -> SpringApplication.run(SpringBootAppStarter.class));
		//applicationContext.publishEvent(new StageReadyEvent(primaryStage));
		
		  //FXMLLoader fxmlLoader = new FXMLLoader(); 
		  fxmlLoader.setLocation(libraryHomeResouceURL);
		  Parent root = fxmlLoader.load(); 
		  scene = new Scene(root, 1700, 900);
		  primaryStage.setScene(scene); 
		  primaryStage.setTitle("Library Exploration");
		  primaryStage.show();
		log.debug("finish");

	}
	
	 public static void setRoot(Parent view) {
	        scene.setRoot(view);
	    }
		/*
		 * Make this inner class static and package visible, other classes will be
		 * listening for this event.
		 */
		static class StageReadyEvent extends ApplicationEvent {
			public StageReadyEvent(Stage primaryStage) {
				super(primaryStage);
			}
			
			public Stage getStage() {
				return (Stage) super.getSource();
			}
		}

}
