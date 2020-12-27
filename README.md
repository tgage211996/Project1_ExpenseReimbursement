# Project1_ExpenseReimbursement

## Project Description
This system allows for quick and effecient handling and viewing of expense reimbursement requests. Managers are able to view any submitted requests, and can filter those requests by a single employee or by pending status. Managers can also approve or deny any pending requests. Employees are able to submit new requests, and they are able to see the status of all of their previous requests.


## Technologies Used

* Java - version 1.8
* Intellij Ultimate  - 2020.2.4
* Apache Tomcat - version 8.5.60
* Apache Maven - version 3.6.3
* AWS RDS - db.t2.micro
* DBeaver
* Servlets
* HTML/CSS/JavaScript
* Fetch API
* JUnit
* log4J
* JDBC
* Bootstrap
* SQL

## Features

Features ready
* An Employee can login
* An Employee can view the Employee Homepage
* An Employee can logout
* An Employee can submit a reimbursement request
* An Employee can view their pending reimbursement requests
* An Employee can view their resolved reimbursement requests
* An Employee can view their information
* An Employee can update their information
* A Manager can login
* A Manager can view the Manager Homepage
* A Manager can logout
* A Manager can approve/deny pending reimbursement requests
* A Manager can view all pending requests from all employees
* A Manager can view all resolved requests from all employees and see which manager resolved it
* A Manager can view all Employees
* A Manager can view reimbursement requests from a single Employee

To-do list:
* add feature where an emloyee revieves an email when their reimbursemnet is resolved
* add where a manager can register an employee, which sends the employee an email with their username and temp password 
* add feature where an employee can reset their password
* create appropriate layout for the webapp

## Getting Started

Here is the git clone command for windows and Unix that will clone the project in the specifide directory you desire
> git clone https://github.com/tgage211996/Project1_ExpenseReimbursement.git

Step 1: Download and Setup Java
- Download Java 1.8 from oracle https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
- video to help you through the setup https://www.youtube.com/watch?v=GcG2nzYn1PA&ab_channel=RomaRomanik

Step 2: Download and Setup IntelliJ
- Download IntelliJ Ultimate or Community (I will explain when project setup may differ and I will explain how to setup up for both versions) from https://www.jetbrains.com/idea/download/#section=windows

Step 3: Download and Setup Tomcat
- Download Apache Tomcat 8 embedded zip file from https://tomcat.apache.org/download-80.cgi
- Video to help install Tomcat https://www.youtube.com/watch?v=yDAXfPxUGPE&ab_channel=CodeTechClub


Step 4: Download and Setup Maven
- Download Maven from https://maven.apache.org/download.cgi
- Video to help install Maven https://www.youtube.com/watch?v=RfCWg5ay5B0&ab_channel=CodingMagic

Step 5: Setup an Acount with AWS and create a RDS instance
- Go to AWS https://aws.amazon.com/console/
- click "sign in to consol" in the top right of the browser
- click "create a new AWS account" and go through the process to create the process"
- once you have create an account and you are at the home page, type "RDS" in the search bar at the top and click on "RDS: Managed Relational Database Services"
- click the "create database" button that is orange and will be on the page you just arrived to
- once you have options available, change the database "Engine Options" to PostgreSQL
- Scroll down to the next section, "Template options", and select Free tier.
- Scroll down to the next section, "Settings", and setup your master username and password (make sure to wright these down, for you will need them later).
- Now click "create database" at the botton of the page to start the creation of your database.
- Once your database has been created, go to the database dashboard and then click on the name of the database (The name of the databased should be under the database identifier   column
- Underneath the first tab "Connectivity and Security" you should see your Database's endpoint that you need to writedown for later when we setup DBeaver in the next step.

Step 6: Download and Setup DBeaver
- Download DBeaver from https://dbeaver.io/download/
- Once you have downloaded and opened the dbeaver program, find the symbol that looks like an electrical plug with a green plus sign and click it
- Select PostgreSQL as you type of database instance and click next
- Put your database endpoint in the "Database field" and the database's username and password in the appropriate fields and click finish.
- The needed scripts for the project are given below

  Table creation scripts:
  
  >create table if not exists MANAGER(
  >manage_id serial primary key,
  >first_name varchar(30) not null,
  >last_name varchar(30) not null,
  >email     varchar(30) unique not null,
  >password  varchar(30) not null,
  >address   varchar(30)
  >);

  >create table if not exists DEPARTMENT(
  >depart_id serial primary key,
  >name      varchar(30) not null,
  >manager_id int8 references MANAGER(manage_id)
  >);

  >create table if not exists EMPLOYEE(
  >emp_id serial primary key,
  >first_name varchar(30) not null,
  >last_name varchar(30) not null,
  >email     varchar(30) unique not null,
  >password  varchar(30) not null,
  >address   varchar(30),
  >department_id int8 references DEPARTMENT(depart_id),
  >supervisor_id int8 references MANAGER(manage_id)
  >);

  >create table if not exists REIMBURSEMENT(
  >reimburse_id serial primary key,
  >amount		 numeric(12,2) not null,
  >status       varchar(30) not null,
  >created_by   varchar(30) references EMPLOYEE(email),
  >date_sub     varchar(30) not null,
  >reviewed_by  int8 references MANAGER(manage_id)
  >);
  
  Scripts to run to create starting data for the projcet:
  
 > insert into manager(first_name, last_name, email, password, address) values('Leo', 'Purell', 'leopurell@gmail.com', '123456', 'not available');
 > insert into manager(first_name, last_name, email, password, address) values('Mary', 'Purell', 'marypurelln@gmail.com', '123456', 'not available');
 > insert into manager(first_name, last_name, email, password, address) values('Max', 'Purell', 'maxpurell@gmail.com', '123456', 'not available');
 > insert into manager(first_name, last_name, email, password, address) values('Sarah', 'Purell', 'sarahpurell@gmail.com', '123456', 'not available');
 > insert into manager(first_name, last_name, email, password, address) values('Sara', 'Bell', 'sarabell@gmail.com', '1Ab23456', 'not available');

 > insert into department(name, manager_id) values('accounting', 1);
 > insert into department(name, manager_id) values('human_resources', 2);
 > insert into department(name, manager_id) values('production', 3);
 > insert into department(name, manager_id) values('purchasing', 4);

 > insert into employee(first_name, last_name, email, password, address, department_id, supervisor_id) values('Sam', 'Pengear', 'sampengear@gmail.com','456789', '656 North >Blvd',   1, 1);
 
 > insert into employee(first_name, last_name, email, password, address, department_id, supervisor_id) values('Bill', 'Pen', 'billpen@gmail.com','456789', '656 North Blvd', 1,    >1);
 
 > insert into employee(first_name, last_name, email, password, address, department_id, supervisor_id) values('Max', 'Fish', 'maxfish@gmail.com','456789', '656 North Blvd', 2,    >2);
 
 > insert into employee(first_name, last_name, email, password, address, department_id, supervisor_id) values('Will', 'Bear', 'willbear@gmail.com','456789', '656 North Blvd', 2, >2);
 
 > insert into employee(first_name, last_name, email, password, address, department_id, supervisor_id) values('Brett', 'Cattlefly', 'brettcattlefly@gmail.com','456789', '656 >North Blvd', 3, 3);
 
 > insert into employee(first_name, last_name, email, password, address, department_id, supervisor_id) values('Meg', 'TheEgg', 'megtheegg@gmail.com','456789', '656 North Blvd',   >3, 3);
 
  >insert into employee(first_name, last_name, email, password, address, department_id, supervisor_id) values('Thomas', 'Train', 'thomastrain@gmail.com','456789', '656 North  >Blvd', 4, 4);
  
 > insert into employee(first_name, last_name, email, password, address, department_id, supervisor_id) values('Frodo', 'Baggins', 'frodobaggins@gmail.com','456789', '656 North  >Blvd', 4, 4);


Step 7: Open the cloned project in IntelliJ from where you downloaded the project

Step 8: Click the import Maven Templat popup that will appear in the bottom right corner of the window or add the maven framework by right clicking on the project name in the file directory and selecting add frameworks support and scrolling through your given options and till you see maven. Click the Maven option and then click the "Okay" button

Step 9.a: Setup Tomcat in Intellij Ultimate
 - https://www.youtube.com/watch?v=_Uq2mn56SDs&ab_channel=HamzaMalik
 
Step 9.b: Setup Tomcat in Intellij community
 - https://www.youtube.com/watch?v=Sr9DqV3hZhA&ab_channel=LearningFromExperience

Step 10: got to the resources folder underneath src/main/resources in the file called db.properties and provide the following
>db.url=jdbc:postgresql://databasendpoint/
>db.username=databaseusername
>db.password=databasepassword

## Usage

- To start the program follow this tutorial to add Tomcat Run configuration https://mkyong.com/intellij/intellij-idea-run-debug-web-application-on-tomcat/
- Once this is done you should be able to start the Tomcat Run configuration and the program will start in you default browser!

## Licence

This project uses the following license: [MIT].









