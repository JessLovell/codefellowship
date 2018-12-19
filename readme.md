# Code Fellowship
An application where a community of coders can share computer programming tips, tools, projects, ideas, and collaborate with each other.

## Packages, Products, & Resources
* [Spring Initializr](https://start.spring.io/) with dependencies web, thymeleaf, devTools, postgreSQL, JPA, Security.
* [Spring Auth Cheat Sheet](https://github.com/codefellows/seattle-java-401d2/blob/master/SpringAuthCheatSheet.md)

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

### Day 2
1. Allow users to log in to CodeFellowship and create posts.
    * Using the above cheat sheet, add the ability for users to log in to your app.
    * Upon logging in, users should be taken to a /myprofile route that displays their information.
    * Ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.
    * Ensure that user registration also logs users into your app automatically.
2. Add a Post entity to your app.
    * A Post has a body and a createdAt timestamp.
    * A logged-in user should be able to create a Post, and a post should belong to the user that created it. **Hint:** this is a relationship between two pieces of data
    * A user’s posts should be visible on their profile page.
3. When a user is logged in, the app should display the user’s username on every page (probably in the heading).