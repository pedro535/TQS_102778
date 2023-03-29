# Lab 6.3 - Custom QG

## A - IES project Quality Gate

After performing static code analysis on IES project the result obtained is the following:

![Results1](./images/logipack_analysis.png)

<br>

We can create a custom Quality Gate to check the quality of the code.


The conditions selected for the Quality Gate are:

- Duplicated lines
- Maintainability Rating
- Major Issues
- Reliability Rating
- Security Hotspots Reviewed
- Security Rating

![Results1](./images/logipack_QualityGate.png)

**Why did we choose these conditions?**

- **Duplicated lines**: We decided to use the condition "Duplicated lines" to check where we are repeating code. This analysis is crucial to develop reusable and maintainable code.

- **Maintainability Rating**: Whenever we are developing a project, it is important to write code that is easy to understand and maintain, because in a real-world scenario, the code will be read and modified by different members of the team.

- **Major Issues and Reliability Rating**: when developing software it is essential to know if it acts as expected.

- **Security Hotspots Reviewed and Security Rating**: It is important to know if the code is secure and does not have any vulnerability that could be exploited by a malicious user in the future.

<br>

## B - Break Quality Gate

In order to test the conditions previously defined, we could modify the source code by:

- Adding duplicated blocks of code
- Variables that are not used

After write some code smells, the Quality Gate did not pass the conditions.

![Results1](./images/logipack_QualityGate_fail.png)
