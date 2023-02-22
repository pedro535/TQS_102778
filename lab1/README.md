# TQS - Lab 1

## Lab 1.2: Euromillions

After generating the code coverage report using the Jacoco plugin, we can conclude that the coverage for the `BoundedSetOfNaturals` class is only 54%, which is not ideal.

![Code coverage](./images/code_coverage_before_1.png)

It was also possible to verify that the code coverage was reduced due to the fact that the Unit Tests developed did not test certain methods of the class, namely the `fromArray`, `intersects`, `size` and `hashCode` methods, as can be seen in the following image.

![Code coverage details](./images/code_coverage_before_2.png)

</br>

## "What kind of unit test are worth writing for proper validation of BoundedSetOfNaturals?"

To test the `BoundedSetOfNaturals` class in order to ensure that its behavior is as expected, we should perform the following checks:

1. The size is 0 after its construction, in the case where the `fromArray` method is not used;

2. When the `fromArray` method is used, the set size and maxSize should be equal to the number of elements in the array passed as argument;

3. When an invalid array is passed as argument to the `fromArray` method, an `IllegalArgumentException` should be thrown;

4. After inserting a valid element, the size should be incremented by 1 and the element should be present in the set;

5. After inserting an invalid element, an `IllegalArgumentException` should be thrown;

6. After inserting an element that is already present in the set, an `IllegalArgumentException` should be thrown;

7. After inserting an element when the size is already equal to the limit, an `IllegalArgumentException` should be thrown;

8. The hashcode of two sets with exactly the same elements must be equal;

9. The hashcode of two sets with at least one distinct element must be different;

10. The intersection is true when it is made with a subset of the `BoundedSetOfNaturals` to be tested;

11. The intersection is false when it is made with a set that is not a subset of the `BoundedSetOfNaturals` to be tested;

> Note: In order to test if the maxSize attribute is being correctly set, when creating a `BoundedSetOfNaturals` object using the fromArray method, a maxSize getter was added to the class.

</br>

## Code Coverage after adding new tests

After adding the tests described above, the code coverage increased to 96%. No more tests were developed in order to reach the 100% mark, since not all code needs to be tested, in this case the `equals` method.

![Code coverage](./images/code_coverage_after_1.png)

![Code coverage details](./images/code_coverage_after_2.png)

In real situations, there is no interest in having a code coverage of 100%, as this would mean that code that should not be tested is being tested. Examples of this type of code are the *getters* and *setters*, *framework code*, etc.

</br>

---

## Unit Testing *(notes)*

### What is Unit Testing?

Unit Testing is a type of software testing where individual units or components of a software are tested. The purpose is to validate that each unit of the software code performs as expected. Unit Testing is done during the development (coding phase) of an application by the developers. Unit Tests isolate a section of code and verify its correctness. A unit may be an individual function, method, procedure, module, or object.

### Why perform Unit Testing?

There are several advantages of performing unit testing, namely:

- Unit tests help to fix bugs early in the development cycle and save costs.

- It helps the developers to understand the testing code base and enables them to make changes quickly.

- Good unit tests serve as project documentation.

- Increase confidence in the code.

### Unit testing best practices

- Unit Test cases should be independent. In case of any enhancements or change in requirements, unit test cases should not be affected.

- Test only one code at a time.

- Follow clear and consistent naming conventions for your unit tests.

- Bugs identified during unit testing must be fixed before proceeding to the next phase in SDLC.

### What is code coverage?

Code coverage is a measure which describes the degree of which the source code of the program has been tested. It is one form of white box testing which finds the areas of the program not exercised by a set of test cases.

In most cases, code coverage system (e.g Jacoco) gathers information about the running program. It also combines that with source code information to generate a report about the test suite’s code coverage.

Some reasons why code coverage is important:

- It helps you to measure the efficiency of test implementation.

- It offers a quantitative measurement.

- It defines the degree to which the source code has been tested.

</br>

## References

<https://www.guru99.com/unit-testing-guide.html>

<https://www.guru99.com/code-coverage.html>
