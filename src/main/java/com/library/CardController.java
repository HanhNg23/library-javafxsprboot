package com.library;

import org.springframework.stereotype.Component;

import com.library.core.model.Book;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import lombok.RequiredArgsConstructor;

@Component
public class CardController {
	
	@FXML
	private HBox box;

	@FXML
	private Label bookId;
	
	@FXML
	private ImageView bookImage;
	
	@FXML
	private Label bookName;
	
	@FXML
	private Label authorName;
	
	@FXML
	private Label rating;
	
	private String[] colors = {"B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};
	
	public void setData(Book book) {
		bookId.setText(book.getBookId());
		Image image = new Image(getClass().getResourceAsStream(book.getImageSrc()));
		bookImage.setImage(image);
		bookName.setText(book.getName());
		authorName.setText(book.getAuthor());
		rating.setText(Double.toString(book.getRating()));
		box.setStyle(
					"-fx-background-color:" + colors[(int)(Math.random()*colors.length)] + ";" 
					//+ "-fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0), 10, 0, 0, 10);"
					+ "-fx-background-radius: 15px;"
		);
	}
}
