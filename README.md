# BDD in Action: Advanced BDD Test Automation workshop

In this workshop we will be writing executable specifications using Cucumber, WebDriver and Serenity BDD. You wi

## Prerequisite overview

For this workshop, you will need the tools mentioned below, as well as a reasonable understanding of Java programming. 
No knowledge of Cucumber is required.

* [JDK 1.8](https://www.oracle.com/technetwork/java/javase/downloads) or higher
* [Maven](http://maven.apache.org) 3.3 or higher
* [Git](https://git-scm.com) and a [Github](https://github.com) account
* [IntelliJ Community Edition](https://www.jetbrains.com/idea/) or [Eclipse](https://www.eclipse.org)
* [Google Chrome](https://www.google.com/chrome/)
* [NodeJS](http://nodejs.org) (if you want to run the application locally)

## Prerequisite details

### Maven
The sample project will use Maven 3.6.0 (https://maven.apache.org) (any version higher than 3.3 should work.

### Gradle
The project will also run with Gradle. You can use the Gradle Wrapper to make sure you are using the right version of Gradle (see below).

### JDK
You will need a version of the Java JDK (8 or higher). The demo will be done using OpenJDK 12.

### IntelliJ or Eclipse

You will also need a Java IDE. You can use IntelliJ or Eclipse. We recommend IntelliJ - you can download the Community edition from here: https://www.jetbrains.com/idea/download. 

For IntelliJ, make sure the [Cucumber for Java](https://plugins.jetbrains.com/plugin/7212-cucumber-for-java) plugin is installed and enabled. For Eclipse, you will need the [Cucumber for Eclipse Plugin](https://cucumber.github.io/cucumber-eclipse/)

### Chrome
We will be running tests in Chrome, so make sure you have the latest version of Chrome installed (Version 74.0.3729.169 or higher)

## Getting started
Clone or download the starter project from https://github.com/serenity-bdd/serenity-cucumber4-starter

```
git clone https://github.com/serenity-dojo/bdd-test-automation-workshop
```

Run the initial tests to make sure everything works:
```
mvn clean verify
```
Or if you prefer to use Gradle:
```
./gradlew clean test
```


Now load the project into your IDE.

## Running the TodoMVC application locally

We will be writing tests for the [TodoMVC](http://todomvc.com) application. You can run your tests either against the publicly available site, or by running the application locally. If you want to run the application locally, install [NodeJS](http://nodejs.org) on your machine and then install the [http-server](https://www.npmjs.com/package/http-server) package, e.g.
```
$ npm install http-server -g
```
Then, to run the demo application, run the following from the project root directory:
```
$ http-server todomvc
```

## Want to learn more?
For more information about Serenity BDD, you can read the [**Serenity BDD Book**](https://serenity-bdd.github.io/theserenitybook/latest/index.html), the official online Serenity documentation source. Other sources include:
* **[Byte-sized Serenity BDD](https://www.youtube.com/channel/UCav6-dPEUiLbnu-rgpy7_bw/featured)** - tips and tricks about Serenity BDD
* [**Serenity BDD Blog**](https://johnfergusonsmart.com/category/serenity-bdd/) - regular articles about Serenity BDD
* [**The Serenity BDD Dojo**](https://serenitydojo.teachable.com) - Online training on Serenity BDD and on test automation and BDD in general. 
