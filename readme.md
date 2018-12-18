# Code Fellowship
An application where a community of coders can share computer programming tips, tools, projects, ideas, and collaborate with each other.

## Packages and Products
[Spring Initializr](https://start.spring.io/) with dependencies web, thymeleaf, devTools, postgreSQL, JPA, Security.

## Instructions: Running Locally
1. `git clone` this directory, `cd` into this directory.
2. Create a postgres database named `codefellowship_app`
3. In `application.properties` file, make sure you have the following lines of code:
```
   spring.datasource.platform=postgres
   spring.datasource.url=jdbc:postgresql://localhost:5432/codefellowship_app
   spring.jpa.hibernate.ddl-auto=create #comment this out after running locally for the first time
```
4. In the command line use `gradle bootrun` to start the application. Open a browser window to http://localhost:8080/ to see the application and to navigate to other pages.
5. To terminate the application type `CRTL + C `in the command line.

## The Challenge
### Day 1
1. The site should have a splash page at the root route (/) that contains basic information about the site, as well as a link to the “sign up” page.
2. An ApplicationUser should have a username, password (will be hashed using BCrypt), firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.
3. The site should allow users to create an ApplicationUser on the “sign up” page. (For now, there is no such thing as login.)
4. Your Controller should have an @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder; and use that to run bCryptPasswordEncoder.encode(password) before saving the password into the new user.
5. The site should have a page which allows viewing the data about a single ApplicationUser, at a route like /users/{id}.
6. This should include a default profile picture, which is the same for every user, and their basic information.