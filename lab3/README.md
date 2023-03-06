# Lab 3

## Lab 3.1 - Review questions

### A) Identify a couple of examples that use AssertJ expressive methods chaining

Test A
```java 
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
```
TODO - explain what is does

Test B
```java
assertThat(found).extracting(Employee::getName).containsOnly("bob");
```
TODO - explain what is does

Test E
```java
assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");
```
TODO - explain what is does

### B) Identify an example in which you mock the behavior of the repository (and avoid involving a database)

An example where we mock the behavior of the repository is in the class test `B_EmplyeeService_UnitTest.java`. Here, instead of using the real repository to access the database, we use a mock object to simulate the behavior of the repository.

### C) What is the difference between standard @Mock and @MockBean?

Using SpringBoot framework, when we need to mock a class, for example, a service class, as its lifecycle is entirely managed by Spring, we need to use the annotation `@MockBean` instead of `@Mock`. The difference is that `@MockBean` is used to mock a bean that is managed by Spring, while `@Mock` is used to mock a bean that is not managed by Spring.

>**Note**: We can use the @MockBean to add mock objects to the Spring application context. The mock will replace any existing bean of the same type in the application context. If no bean of the same type is defined, a new one will be added. This annotation is useful in integration tests where a particular bean, like an external service, needs to be mocked.

### D) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

The group of tests D and E are not Unit Tests, but Integration Tests, which means that we are not going to use mocked objects anymore but the real objects instead. When using real services and repositories and an external database (not a memory database) it is necessary to configure it, and this is done in the file `application-integrationtest.properties`.

TODO: review and complete

### E) The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

TODO:
Test C - unit test recorrendo a um mock do service
Test D - integration test: teste num ambiente interno
Test E - integration test: teste a partir de um ambiente completamente externo

3.2: C -> B -> A -> E
    @MockBean  // NAO PODIA SER SO MOCK PQ O CICLO DE VIDA DOS SERVICOS É GERIDA PELO SPRING 

## Notes

### Run individual tests

```bash
mvn test -Dtest=EmployeeService*
```

## References

<https://www.baeldung.com/java-spring-mockito-mock-mockbean>