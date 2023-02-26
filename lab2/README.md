# Lab 2

## Lab 2.2 - Questions

### Which is the SuT (subject under test)?

In this case, the Subject under Test is the AddressResolver class, more specifically the findAddressForLocation method.

### Which is the service to mock?

The service to mock is the ISimpleHttpClient interface.

---

## Mocking dependencies (for unit testing)

Mocking is primarily used in unit testing. An object under test may have dependencies on other (complex) objects. To isolate the behaviour of the object we want to test we replace the other objects by mocks that simulate the behaviour of the real objects. This is useful if the real objects are impractical to incorporate into the unit test.

In short, mocking is creating objects that simulate the behaviour of real objects.

> Definition of *"Mocking"* in the english dictionary: *"to laugh at someone, often by copying them in a funny but unkind way"*.

</br>

## Mockito basics

Mockito is a very popular mocking framework for Java. It is used in conjunction with JUnit. It allows us to create mocks and stubs in order to simulate the behaviour of classes that the Sut depends on.

### Creating a mock

```java
import static org.mockito.Mockito.*;

MyClass myMock = mock(MyClass.class);
```

### Using @Mock and @InjectMocks annotations

We can use the @Mock and @InjectMocks annotations to create mocks and inject them into the SuT (Subject under Test).

```java
@ExtendWith(MockitoExtension.class)
class MyTests {

    @Mock
    private MyClass myMock;

    @InjectMocks
    private MySut sut;

    @Test
    public void test() {
        ...
    }
}
```

### Stubbing

After creating a mock we can define the behaviour of the mock. This is called stubbing.

```java
when(myMock.get(1)).thenReturn("One");
when(myMock.get(2)).thenReturn("Two");
when(myMock.get(3)).thenReturn("Three");
```

### Verifying

After our Subject under Test has been executed we can verify that the mock has been called as expected.

```java
verify(myMock).get(1);
verify(myMock).get(2);
verify(myMock).get(3);
```

### Argument matchers

We can use argument matchers to define the behaviour of the mock for different arguments.

```java
when(myMock.get(anyInt())).thenReturn("Any int");
when(myMock.get(anyString())).thenReturn("Any string");
when(myMock.get(anyBoolean())).thenReturn("Any boolean");
```

> One pratical example of mocking is when the Subject under Test is a component that depends on a Http client. In this case we can mock the Http client and simulate the behaviour of the real client, returning the expected responses. Using mocks we have full control over the behaviour of the dependencies.

### Guideline for unit testing with mocks (from lab 2)

**1.** Prepare a mock to subsitute the remote service (or @Mock annotation)

**2.** Create an instance of the subject under test (SuT) and use the mock to set the (remote) service instance.

**3.** Load the mock with the proper expectations (when...thenReturn).

**4.** Execute the test (use the service in the SuT).

**5.** Verify the results (assert) and the use of the mock (verify).

</br>

## Using Hamcrest assertions instead of JUnit assertions

Hamcrest is commonly used with junit and other testing frameworks for making assertions. Specifically, instead of using junit‘s numerous assert methods, we only use the API's single assertThat statement with appropriate matchers.

```java
//example 1
assertThat("Hello", is("Hello"));

//example 2
String a = "foo";
String b = "FOO";
assertThat(a, equalToIgnoringCase(b));

//example 3
List<String> emptyList = new ArrayList<>();
assertThat(emptyList, empty());

//example 4
String[] hamcrestMatchers = { "collections", "beans", "text", "number" };
assertThat(hamcrestMatchers, arrayWithSize(4));

```

Some examples are from [Baeldung](https://www.baeldung.com/java-junit-hamcrest-guide) website!

## References

https://www.baeldung.com/java-junit-hamcrest-guide

https://site.mockito.org/
