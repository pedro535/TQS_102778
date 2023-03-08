# Lab 3.1 - Review Questions

## A) Identify a couple of examples that use AssertJ expressive methods chaining

In some test classes, there are examples of AssertJ expressive methods chaining. For example:

- ```java
    // A_EmployeeRepositoryTest.java
    assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
    ```

    In this case, we are testing if the value returned by the method `findAll()` from `EmployeeRepository` is a list of 3 employees, and if the names of the employees are `alex`, `ron` and `bob`.

- ```java
    // D_EmployeeRestControllerIT.java and E_EmployeeRestControllerTemplateIT.java
    assertThat(found).extracting(Employee::getName).containsOnly("bob");
    ```

    In this case, after doing a POST request to the endpoint `/api/employees` passing an Employee object, we are testing if it persists in the database.

- ```java
    // E_EmployeeRestControllerTemplateIT.java
    assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");
    ```

    In this case, after doing a GET request to the endpoint `/api/employees`, we are testing if the response has exactly two elements and if the names of the employees are `bob` and `alex`.

</br>

### B) Identify an example in which you mock the behavior of the repository (and avoid involving a database)

An example where we mock the behavior of the repository is in the class `B_EmployeeService_UnitTest.java`, where we test service methods. Here, instead of using the real repository to access the database, we use a mock object to simulate the behavior of the repository, so that we avoid involving a database and the tests are more independent.

</br>

### C) What is the difference between standard @Mock and @MockBean?

When we are developing unit tests for a Spring Boot application, there are two java annotations for creating mock objects: `@Mock` and `@MockBean`. The `@Mock` annotation is provided by Mockito mocking framework, while `@MockBean` is provided by Spring Boot, although its implementation relies on Mockito.

When we want to mock a simple class or interface that does not belong to Spring Boot `ApplicationContext` or does not need to be controlled by Spring Boot, we can use Mockito framework. Using Mockito, tests will be much faster because mock objects are independent of Spring Boot.

However, when we want to mock classes or interfaces whose lifecycle needs to be controlled by Spring Boot (e.g. Services, Repositories, Controllers and other Beans), we need to use `@MockBean` annotation. Whenever we create a mock object with `@MockBean`, a Mockito mock is created and included in Spring `ApplicationContext`. If a bean, compatible with the declared class exists in the context, it replaces it with the mock. If it is not the case, it adds the mock in the context as a bean.

</br>

### D) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

The group of tests D and E are not Unit Tests, but Integration Tests, which means that we are not going to use mocked objects anymore but real objects instead. When using real services and repositories and an external database (not an in-memory database) it is necessary to configure them, and this is done in the file `application-integrationtest.properties`. It is a best practice to externalize configuration values for our applications.

In this case, the file `application-integrationtest.properties` contains essential data that allow us to connect to a MySQL database (database user, password, etc.) to perform the Integration Tests.

</br>

### E) The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

In this exercise, we are testing the EmployeeController using three different strategies, namely:

- **Strategy C**:
    In this strategy, we are testing the EmployeeController using **Unit Tests**. In order to make unit tests as independent as possible, we are limiting the Spring Boot application context by using the `@WebMvcTest` annotation and mocking the behavior of the EmployeeService. In this strategy, the interaction with the endpoints is done using a `MockMvc` object that accesses the server context through a special testing servlet.

- **Strategy D**:
    In this strategy, we are testing the EmployeeController using **Integration Tests**. As it is an integration test, we need to load the whole Spring Boot application context, so we use the `@SpringBootTest` annotation. Similarly to the previous strategy, we interact with the API using a `MockMvc` object.

- **Strategy E**:
    This strategy is very similar to the previous one, however instead of interacting with the API using an internal entry point (`MockMvc` object), we are interacting using an HTTP client (in this case, the `RestTemplate` object). This strategy is useful because we are accessing the API from the outside, as it should be done in a real scenario.

In summary, the main differences between these strategies are:

|   |Type of test | Application context  | Mock objects | Interaction with the API|
|---|---|---|---|---|
| Strategy C | Unit test  |Limited. Uses `@WebMvcTest` annotation  | Mocks the `EmployeeService` class  | Internally, using a `MockMvc` object  |
| Strategy D  | Integration test  |Entire application context. Uses `@SpringBootTest` annotation  | There are no mocked objects  | Internally, using a `MockMvc` object  |
| Strategy E  | Integration test  |Entire application context. Uses `@SpringBootTest` annotation  | There are no mocked objects  | Externally, using a `RestTemplate` object |
 
