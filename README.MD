# SECURITY APPLICATION TASK 

## NECESSARY TOOLS 

* Java Development Kit 11+ 
* Apache Maven 3.6.0+ 
* Git 2.24+ 
* Spring 5 
* Spring Boot 2

## How to use
1. Fetch repository
2. Execute task `mvnw package`. It will generate image for spring app
3. Start `docker-compose.yml`
4. Application will create test users (`com.epam.edu.module2securityapplications.service.UserInitializer`)

## Task 
1. (1 star) CREATE SPRING BOOT MVC PROJECT. 
2. CREATE REST ENDPOINT "GET /INFO" THAT PROVIDE RANDOM STATS (EX: "MVC APPLICATION"). TEST THIS ENDPOINT.
3. ADD SPRING SECURITY MODULE TO YOUR PROJECT AND CONFIGURE IT FOR AUTHENTICATED ACCESS TO ALL RESOURCES. 
USE EMAIL/PASSWORD COMBINATION FOR IT. 
4. (2 stars) USE A NON-EMBEDDED DB TO STORE USERS. 
5. USE SALT AND HASHING TO STORE USER PASSWORDS. 
6. CREATE ADDITIONAL REST ENDPOINT "GET /ABOUT" AND CONFIGURE NON-AUTHENTICATED ACCESS TO IT. 
7. (3-4 stars) CREATE ONE MORE REST ENDPOINT "GET /ADMIN". 
8. NOW YOU NEED TO ADD AUTHORISED ACCESS TO "GET /INFO" AND"GET /ADMIN", ADD "VIEW_INFO", "VIEW_ADMIN" PERMISSIONS FOR IT. 
CREATE 3 USERS WITH DIFFERENT COMBINATION OF PERMISSIONS. 
9. CREATE NEW LOGIN/LOGOUT PAGES AND CONFIGURE SPRING SECURITY TO USE NEW LOGIN/LOGOUT. 
10. ADD BRUTE FORCE PROTECTOR. BLOCK USER EMAIL FOR 5 MINUTE ON 3 UNSUCCESSFUL LOGIN. 
11. CREATE AN ENDPOINT TO SHOW BLOCKED USERS 
12. (5 stars) IMPLEMENT A NEW SPRING BOOT MVC APPLICATION CALLED "SECRET PROVIDERS". APPLICATION SHOULD PROVIDE PAGE WITH TEXT FORM. 
AFTER SENDING A SECRET, APPLICATION MUST GENERATE UNIQ LINK ADDRESS, TO PROVIDE ONE-TIME ACCESS TO SECRET INFORMATION. 
AFTER THIS INFORMATION MUST BE REMOVED FROM APPLICATION. 
14. USER (SENDER AND RECIPIENT) MUST BE AUTHORIZED AND HAVE "STANDARD" PERMISSION. 
15. USE DOCKER CONTAINERS TO IMPLEMENT SOLUTION. Create pull requests to your mentor with implemented application (-s).
