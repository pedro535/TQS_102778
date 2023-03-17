package pt.ua.tqs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
 
public class Library {
	private final List<Book> store = new ArrayList<>();
 
	public void addBook(final Book book) {
		store.add(book);
	}
 
	public List<Book> findBooksByDate(final LocalDateTime from, final LocalDateTime to) {
		return store.stream().filter(book -> {
			return from.isBefore(book.getPublished()) && to.isAfter(book.getPublished());
		}).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
	}


	public List<Book> findBooksByCategory(final String category) {
		return store.stream().filter(book -> {
			return book.getCategory().equals(category);
		}).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
	}
	

	public List<Book> findBooksByAuthor(String author) {
		return store.stream().filter(book -> {
			return book.getAuthor().startsWith(author);
		}).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
	}
}