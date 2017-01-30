The welcome project
===================

The objective of this mini-project is to create an application with JEE in only 4 days. This project requires backend skills (Java) & frontend skills (JSP, HTML, JavaScript).
You should work together to be able to finish on time.

# Pre-requisites
You will develop your project as a team, with Agile management. At the end we want to have only one project on a github.
We will organize a daily retrospective to explain the problems you encountered.
Do not hesitate to work in pair, to think together how you will develop the project.

   * Daily scrum
   * 1 project on github
   * Clean & commented code
   * Don't use Spring/SpringBoot

# The Project    
The project a web app in JEE. Your objective is to create an administration panel to manage code reviews.
To do that, we must be able to have some features :
    
  * Create a new member with these attributes (name, birthdate, promotion)
  * Create a new promotion (name, members)
  * Create a code review (name, datetime, promotion)

A member can be editable, he belongs to a promotion and have a name and a birthdate.
A promotion is a group of members. It needs a name and is able to retrieve its list of members.    
A code review is a meeting for members of a promotion. It must have a date & hour, a name and be linked to a promotion.

You don't need to implement security like login or user account at the moment.
We provide you the front pages but you'll need to edit it.

# Day 1    
## Setup environment

### MySQL Database
First, install a local **MySQL** server and eventually a PhpMyAdmin to manage your database in a web app.

### Your IDE
You are free to use the IDE you want to develop the project (Eclipse or IntelliJ). 
Eclipse should be a better idea ;)
Install a **Tomcat 8.0** server. Search & read the doc to know how to do :)

### GitHub repository
Create a repository on github and name it **ebiz-welcome**
Everyone must be able to push code on the repository.
The important fact here is to **create your workflow as a team**.


# Day 2/3

## Project Architecture
Take your time and brainstorm in group to create a clean architecture for the project.
We advise to use EclipseLink JPA for data persistence in your project, but you can use JPA API if you want.
Don't use Hibernate!
Create the model of the web app with the differents layers.

# Day 4

## Intégration
Download the provided front architecture in the folder **resources/**
Integrate it in your project.
Transform it in JSP and link them to your backend.
No Java code in JSP pages.
Avoid duplicate code in views (title bar & footer).
Use a calendar widget for code reviews form.

### Provided content
We provided you some content to help you in your work.
  - HTML architecture
  - Bootstrap 3
  - Jquery 3
  - Font Awesome 4
 
Pages:
  - add_event.html
  - add_member.html
  - add_promotion.html
  - index.html

# To go further

  * Implement front validation with jQuery
  * Implement backend tests


