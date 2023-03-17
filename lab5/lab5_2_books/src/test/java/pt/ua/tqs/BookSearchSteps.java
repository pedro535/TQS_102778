package pt.ua.tqs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BookSearchSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();


	@ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
	public LocalDateTime iso8601Date(String year, String month, String day){
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0);
	}


    @Given("a/another book with the title {string}, written by {string}, published in {iso8601Date}")
	public void addNewBook(final String title, final String author, LocalDateTime published) {
		Book book = new Book(title, author, published);
		library.addBook(book);
	}


	@Given("a list of books")
	public void addBookList(final DataTable table) {
		for (Map<String, String> row : table.asMaps(String.class, String.class)) {
			Book book = new Book(row.get("title"), row.get("author"), toLocalDateTime(row.get("published")), row.get("category"));
			library.addBook(book);
		}
	}


	@When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
	public void setSearchParametersByDate(final LocalDateTime from, final LocalDateTime to) {
		result = library.findBooksByDate(from, to);
	}


	@When("the customer searches for {string} books")
	public void setSearchParametersByCategory(final String category) {
		result = library.findBooksByCategory(category);
	}


	@When("the customer searches for books written by {string}")
	public void setSearchParametersByAuthor(final String author) {
		result = library.findBooksByAuthor(author);
	}


	@Then("{int} books should have been found")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertThat(result.size(), equalTo(booksFound));
	}


	@Then("Book {int} should have the title {string}")
	public void verifyBookAtPosition(final int position, final String title) {
		assertThat(result.get(position - 1).getTitle(), equalTo(title));
	}


	public LocalDateTime toLocalDateTime(String date) {
		String[] dateFields = date.split("-");
		int year = Integer.parseInt(dateFields[0]);
		int month = Integer.parseInt(dateFields[1]);
		int day = Integer.parseInt(dateFields[2]);

		return LocalDateTime.of(year, month, day, 0, 0);
	}
}