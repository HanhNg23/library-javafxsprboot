package com.library.core.model;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Book {
	
	private String bookId;
	private String name;
	private String imageSrc;
	private String author;
	private double rating;
	private LocalDateTime dateadded;
	
}
