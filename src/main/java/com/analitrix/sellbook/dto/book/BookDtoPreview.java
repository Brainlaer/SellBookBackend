package com.analitrix.sellbook.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDtoPreview{

	private Long isxn;
	private String title;
	private String author;
	private Long cost;
	private String image;
}
