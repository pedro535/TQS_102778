# Lab 3

## Multi-layer application testing (with Spring Boot)

Most Spring Boot applications are multi-layered applications, so it is important to know how to test each layer properly.
Normally, we have the following three layers:

- Controller
- Service
- Repository

The basic logic to test each layer is:

- **Controller**: test the controller mocking the service layer
- **Service**: test the service mocking the repository layer
- **Repository**: test the repository using objects that allow direct access to the database (with no caches involved)

</br>

### Limit application context

In order to speed up the tests, we can limit the application context to the layers we want to test.

For example, if we want to develop unit tests for the controller layer, we can just load a simplified and light environment, simulating the behavior of an application server, by using `@WebMvcTest` mode.

If we want to test the repository layer, we can use `@DataJpaTest` mode.

However, every time we want to develop tests that make use of the entire application context (e.g. integration tests), we can use `@SpringBootTest` mode.

</br>

### Useful classes for testing

- `TestEntityManager` (from Spring Data JPA): allows to perform operations directly on the database, without using the repository layer.

- `MockMvc`: allows us to access the server context through a special testing servlet and interact with the API.

- `TestRestTemplate`: allows us to interact with the API using an HTTP client. We can simulate external requests to the API.

</br>

### Run individual tests from the command line

```bash
mvn test -Dtest=EmployeeService*
```
