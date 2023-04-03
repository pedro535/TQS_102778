package pt.ua.tqs.lab7_3;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseIT {

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres")
        .withUsername("username")
        .withPassword("password")
        .withDatabaseName("db")
        .withInitScript("db/migration/V001__INIT.sql");


    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }


    @Autowired
    private BookRepository bookRepository;


    @Test
    @Order(1)
    public void testInsertEntries() {
        Book book1 = new Book("Book 1", "Author 1", "Publisher 1");
        Book book2 = new Book("Book 2", "Author 2", "Publisher 2");
        Book book3 = new Book("Book 3", "Author 3", "Publisher 3");

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        assertThat(bookRepository.findAll(), hasSize(6));  //because we are inserting more 3 via V002__INIT.sql
    }


    @Test
    @Order(2)
    public void testReadEntries() {
        assertTrue(bookRepository.existsByTitle("Book 1"));
        assertTrue(bookRepository.existsByTitle("Book 2"));
        assertTrue(bookRepository.existsByTitle("Book 3"));
        assertTrue(bookRepository.existsByTitle("Book 4"));
        assertTrue(bookRepository.existsByTitle("Book 5"));
        assertTrue(bookRepository.existsByTitle("Book 6"));
    }

}
