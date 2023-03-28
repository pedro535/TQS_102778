# Lab 6.3 - Custom QG

## A - IES project Quality Gate

After performing static code analysis on IES project the result obtained is the following:

![Results1](./images/logipack_analysis.png)

<br>

We can create a custom Quality Gate to check the quality of the code.

![Results1](./images/logipack_QualityGate.png)

The conditions selected for the Quality Gate are:

- Duplicated lines
- Maintainability Rating
- Major Issues
- Reliability Rating
- Security Hotspots Reviewed
- Security Rating


## B - Break Quality Gate

In order to test the conditions previously defined, we could modify the source code by:

- Adding duplicated blocks of code
- Variables that are not used

After write some code smells, the Quality Gate did not pass the conditions.

![Results1](./images/logipack_QualityGate_fail.png)
