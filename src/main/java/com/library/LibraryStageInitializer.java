package com.library;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.library.SpringBootAppStarter.StageReadyEvent;
import com.library.core.constant.PathConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*Listening to application events*/
/*When the StageReadyEvent is called in Library.class at the time the application is started
 * --> this class will listen for the initialization of StageReadyEvent to start the onApplicationEvent methods o
 *
 * */
@Component
public class LibraryStageInitializer implements ApplicationListener<SpringBootAppStarter.StageReadyEvent> {
	private final String applicationTitle;
	private final URL libraryHomeResouceURL = LibraryStageInitializer.class.getResource(PathConstants.lIBRARY_HOME_FXML);
	public LibraryStageInitializer(@Value("${spring.application.name}") String applicationTitle) {
		this.applicationTitle = applicationTitle;
	}

	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		try {
			System.out.println("HELLO");
			Stage primaryStage = event.getStage();
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(libraryHomeResouceURL);
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root, 1315, 890);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Library Exploration");
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
}
