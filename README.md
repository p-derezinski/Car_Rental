# Car_Rental

1. Description

1.1. Name of the Application

Car_Rental

1.2. Purpose of Creating the Application

The purpose of creating this application is to test the skills learned during the software development course.

1.3. Purpose of the Application

The purpose of this application is running the car rental business.  



2. Overview

The application is created using the following technologies:
- Spring framework and Java
- Thymeleaf
- MySQL
- Maven
- Git
- HTML, CSS and JS  



3. Data design

- Cars and user roles are created and stored in the database.
- Users are created using the registration form and added to the database.
- The roles of each user are stored in the separate table in the database.
- Reservations are created using the reservation process and stored in the database. They contain the references to the user and to the car.  



4. Functionality

4.1. Restriction of access

Every user can view the home page, statistics page, contact page, registration page and login page. Other sections and functionalities are accessible only for logged users.

4.2. Home page

Home page presents the list of cars with information if a particular car is available or not. There are also options to filter cars or order cars based on a chosen criterium.

4.3. Branches

Choosing one of the branches from the list allows to display only cars from that branch.

4.4. Registration

User can create his account by providing several information in the registration form, including email and password. Validation of the input is handled in the frontend. Input data is sent to the controller and new record (client account) is added to the database with the password hashed.

4.5. Login / Logout

User can log in (using the login form) and out. These functionalities are handled by WebSecurityConfig class.

4.6. Car reservation

User can choose one of the available cars and confirm the reservation. In addition to creating a new record in the database (new reservation), the car status changes from available to booked.

4.7. Car return

Only an employee can return a booked car. Its status changes and the car is available again.

4.8. Client's section

This section presents selected personal data of the looged user and lists his previous reservations.

4.9. Statistics

This section presents some basic statistics regarding the car rental company, including the list of users who have done at least one reservation. It is also possible to go to the advanced statistics page with the form, which allows to choose the brand and years and display the number of cars based on the selected criteria.

4.10. Contact page

This section presents the contact data of the car rental company.