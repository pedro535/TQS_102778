# Lab 3.1 - Review Questions

## A) Identify a couple of examples that use AssertJ expressive methods chaining

In some test classes, there are examples of AssertJ expressive methods chaining. For example:

- ```java
    // A_EmployeeRepositoryTest
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

An example where we mock the behavior of the repository is in the class `B_EmplyeeService_UnitTest.java`, where we test service methods. Here, instead of using the real repository to access the database, we use a mock object to simulate the behavior of the repository, so that we avoid involving a database.

</br>

### C) What is the difference between standard @Mock and @MockBean?

When we are developing unit tests for a Spring Boot application, there are two java annotations for creating mock objects: `@Mock` and `@MockBean`. The `@Mock` annotation is provided by Mockito mocking framework, while `@MockBean` is provided by Spring Boot, although its implementation relies on Mockito.

When we want to mock a simple class or interface that does not belong to Spring Boot `ApplicationContext` or does not need to be controlled by Spring Boot, we can use Mockito framework. Using Mockito, tests will be much faster because mock objects are independent of Spring Boot.

However, when we want to mock classes or interfaces whose lifecycle needs to be controlled by Spring Boot (e.g. Services, Repositories, Controllers and other Beans), we need to use `@MockBean` annotation. Whenever we create a mock object with `@MockBean`, a Mockito mock is created and included in Spring `ApplicationContext`. If a bean, compatible with the declared class exists in the context, it replaces it with the mock. If it is not the case, it adds the mock in the context as a bean.

</br>

### D) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

The group of tests D and E are not Unit Tests, but Integration Tests, which means that we are not going to use mocked objects anymore but real objects instead. When using real services and repositories and an external database (not a memory database) it is necessary to configure them, and this is done in the file `application-integrationtest.properties`. It is a best practice to externalize configuration values for our applications.

### E) The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

TODO:
Test C - unit test recorrendo a um mock do service
Test D - integration test: teste num ambiente interno
Test E - integration test: teste a partir de um ambiente completamente externo

3.2: C -> B -> A -> E
    @MockBean  // NAO PODIA SER SO MOCK PQ O CICLO DE VIDA DOS SERVICOS É GERIDA PELO SPRING
