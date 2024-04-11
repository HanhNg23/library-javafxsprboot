package com.library.core.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import com.library.core.constant.PathConstants;
import com.library.core.model.Book;
import com.library.core.util.DateTimeUitl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {

	public Optional<List<Book>> readCSVFile() throws IOException {
		Pattern pattern = Pattern.compile(",");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			
		try (
				FileInputStream in = new FileInputStream(PathConstants.BOOK_DATA_CSV); 
				//classLoader.getResourceAsStream(PathConstants.BOOK_DATA_CSV);
				
				//Stream<String> lines = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)).lines();
				Stream<String> lines = new BufferedReader(new FileReader(PathConstants.BOOK_DATA_CSV)).lines()
				) {
			
				List<Book> books = lines.skip(1).map(line -> {
				String[] strArr = pattern.split(line);
				LocalDateTime dateTimeNow = DateTimeUitl.getLocalDateTimeNowZoneVN();
				log.debug("TIME NOW ", dateTimeNow);
				System.out.println("BOOK " + strArr[1] + " TIME NOW " + dateTimeNow);
				LocalDateTime convertedDatetimeAdded = null;
				
				if(strArr[5].equalsIgnoreCase("null")  )
					convertedDatetimeAdded = dateTimeNow;
				else
					convertedDatetimeAdded = LocalDateTime.parse(strArr[5], formatter);

				Book book = Book.builder()
						.bookId(strArr[0])
						.name(strArr[1])
						.imageSrc(strArr[2])
						.author(strArr[3])
						.rating(Double.parseDouble(strArr[4]))
						.dateadded(convertedDatetimeAdded).build();
				return book;
			}).collect(Collectors.toList());

			return Optional.of(books);
		}
		
		
	}
}
