package com.library;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.library.core.constant.PathConstants;
import com.library.core.model.Book;
import com.library.core.service.BookService;
import com.library.core.util.DateTimeUitl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LibraryHomeController implements Initializable {

    @FXML
    private GridPane bookContainer;	 
	
    @FXML
	private HBox cardlayout;
    

    
	@Autowired
	private BookService bookService;
	
	private List<Book> recentlyAdded;
	
	private List<Book> booksRecommend;
	
	private final URL cardfxml = getClass().getResource(PathConstants.CARD_FXML);
	private final URL bookshowcasefxml = getClass().getResource(PathConstants.BOOK_CONTAINER_FXML);
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("LibraryHomeController initialized!");
		recentlyAdded = new ArrayList<>(recentlyAdded());
		if (recentlyAdded.size() > 0)
		{
			try {
				for (int i = 0; i < recentlyAdded.size(); i++) {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(cardfxml);
					HBox cardBox = fxmlLoader.load();
					CardController cardController = fxmlLoader.getController();
					cardController.setData(recentlyAdded.get(i));
					cardlayout.getChildren().add(cardBox);
				}
			} catch (IOException ex) {
				log.error("", ex);
				ex.printStackTrace();
			}
		}
		
		int row = 1;
		int column = 0;
		booksRecommend = new ArrayList<>(getBookRecommended());
		if (booksRecommend.size() > 0)
		{
			try {
				for (int i = 0; i < booksRecommend.size(); i++) {
					for(int x = 2; x >=1; x--) {
						FXMLLoader fxmlLoader = new FXMLLoader();
						fxmlLoader.setLocation(bookshowcasefxml);
						VBox bookBox = fxmlLoader.load();
						BookController bookController = fxmlLoader.getController();
						bookController.setData(booksRecommend.get(i));
						if(column == 6) {
							column = 0;
							++row;
						}
						bookContainer.add(bookBox, column++, row);
						GridPane.setMargin(bookBox, new Insets(10));
					}

				}
			} catch (IOException ex) {
				log.error("", ex);
				ex.printStackTrace();
			}
		}

	}

	private List<Book> recentlyAdded() {
		try {
			Optional<List<Book>> booksfrcsv = bookService.readCSVFile();
			if (!booksfrcsv.isPresent()) {
				return Collections.emptyList();
			} 
			List<Book> recentlyAdded = booksfrcsv.get().stream().filter(book -> {
				 LocalDateTime addedTime = book.getDateadded();
				 Duration duration = Duration.between(addedTime, DateTimeUitl.getLocalDateTimeNowZoneVN());
				 long minutesDifference = duration.toMillis();
				 //return minutesDifference == 100;
				 return true;
				
			}).collect(Collectors.toList());
			return recentlyAdded;	
		} catch (Exception ex) {
			log.error("", ex);
			return Collections.emptyList();
		}
	}
	
	private List<Book> getBookRecommended() {
		try {
			Optional<List<Book>> booksfrcsv = bookService.readCSVFile();
			if (!booksfrcsv.isPresent()) {
				return Collections.emptyList();
			} 
			List<Book> bookData = booksfrcsv.get().stream().filter(book -> {
				 LocalDateTime addedTime = book.getDateadded();
				 Duration duration = Duration.between(addedTime, DateTimeUitl.getLocalDateTimeNowZoneVN());
				 long minutesDifference = duration.toMillis();
				 //return minutesDifference == 100;
				 return true;
				
			}).collect(Collectors.toList());
			return bookData;	
		} catch (Exception ex) {
			log.error("", ex);
			return Collections.emptyList();
		}
		
	}
}
