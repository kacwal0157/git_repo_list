# GitHub Repository Lister API

## The project written in Java 17 using Spring Boot 3 showing a list of repo for given user.

This project was created as a recruitment task. It contains all good programing standards like DRY 
(don't repeat yourself) or class single responsibility. The API is responsible for:

* Check if given user exists on GitHub, it lists its name, owner and all branches (each with its last commit SHA)
* If given user does not exist it will throw an 404 error with proper message
* If given header is application/xml not application/json it will throw 406 error with proper message

## How to use this API

The easiest way to see how it works is to use software like for example Postman or do following steps 
in your browser. In steps below i will refer to Postman, in your software, names may be different.

1. Run the project in your IDE (for example IntelliJ)
2. Enter 'localhost:8080/api/{name}' into your browser or GET request in Postman ({name} is github username of user that you want to check)
3. Check if you have proper header properties (Headers -> Accept -> application/json) - this step is for external software only
4. Click 'Enter' (in browser) or 'Send' (in Postman)

## Find a bug?

If you found an issue or would like to submit an improvement to this project, 
please submit an issue using the issue tab above.

## Known issues

*What if I get an error that says: "{Your browser} can't connect to the server"?*
Probably you did not run the program.

*What if I get an error that says: `"Internal error 500"`?*
Check if you correctly entered the path to the API. Maybe you missed something.

## Share your feedback

Tell me what you think about this project or maybe you want to know my other projects? 
Text me on [LinkedIn](https://www.linkedin.com/in/kacper-walat-6a8166262/)!
