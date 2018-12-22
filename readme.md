# Code Fellowship
An application where a community of coders can share computer programming tips, tools, projects, ideas, and collaborate with each other.

## Packages, Products, & Resources
* [Spring Initializr](https://start.spring.io/) with dependencies web, thymeleaf, devTools, postgreSQL, JPA, Security.
* [Spring Auth Cheat Sheet](https://github.com/codefellows/seattle-java-401d2/blob/master/SpringAuthCheatSheet.md)

## Instructions: Deployed Site
Visit [this link](https://afternoon-brook-28815.herokuapp.com/) to play around with. 

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

### Testing Locally
To test the routes on `/login` and `/signup`:
1. Go to `ApplicationController`
2. For routes `/login (method=GET)` and `/signup (method=GET)`, uncomment `@RequestBody` above the method declaration.
3. Run the test in IntelliJ on the file `CodefllowshipApplictionTests`. All should go smoothly. 

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

### Day 3
1. Ensure that users can’t perform SQL injection or HTML injection with their posts.
2. Allow users to follow other users. Following a user means that their posts show up in the logged-in user’s feed, where they can see what all of their followed users have posted recently.
3. Ensure there is some way (like a users index page) that a user can discover other users on the service.
4. On a user profile page that does NOT belong to the currently logged-in user, display a “Follow” button. When a user clicks that follow button, the logged-in user is now following the viewed-profile-page user.
    * note: this will require a self-join on ApplicationUsers.
5. A user can visit a url (like /feed) to view all of the posts from the users that they follow.
6. Each post should have a link to the user profile of the user who wrote the post.
7. **Stretch Goals:** When users create posts, allow them to specify whether or not a post is “public”. Public posts show up in everyone’s feeds. Add the ability for users to comment on posts.
