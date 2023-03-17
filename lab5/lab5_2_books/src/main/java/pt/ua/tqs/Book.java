package pt.ua.tqs;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
	private final String title;
	private final String author;
	private final LocalDateTime published;
	private final String category;

	public Book(String title, String author, LocalDateTime published) {
		this.title = title;
		this.author = author;
		this.published = published;
		this.category = "unknown";
	}

	public Book(String title, String author, LocalDateTime published, String category) {
		this.title = title;
		this.author = author;
		this.published = published;
		this.category = category;
	}
}
