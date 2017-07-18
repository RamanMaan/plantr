# plantr 
:seedling:*An app to smooth the transition into the plant lyfe* :seedling:

### COMP3350 Group 7
+ Austin Cooke
+ Kevin Dam
+ Raman Maan
+ Keaton Macleod
+ Michael Ramsay

The [project log](group_log.md) is located in the root directory

## Iteration 1

### Organization
+ Our source code is located in the src/main/comp3350/plantr directory. This contains our packages: 
    + application - contains application-wide code
    + business - contains our business logic
    + objects - contains objects used throughout application
    + persistence - contains our database related files
    + presentation - our presentation/GUI layer
+ Our test code is located under src/test/comp3350/plantr
    + each class is contained in the same package its class is located in

### Overview of Implemented Features
+ We completed Big-US : "View information on plants"
    + We can see all the relevant details we would need to see on a plant
    + This can be observed by clicking on any plant in our list of plants
+ We can list all the plants in the database
    + These plants can then be clicked to bring up the plant detailed view
    
## Iteration 2

### Organization
+ Our source code is located in the src/main/comp3350/plantr directory. This contains our packages:     
    + business - contains our business logic
    + model - contains objects used throughout application
    + persistence - contains our database related files
    + presentation - our presentation/GUI layer   
    
+ Our test code is located under src/test/comp3350/plantr
    + each class is contained in the same package its class is located in

### Overview of Implemented Features
+ We completed Detailed User Story : "Personal Plant View"
    + We can see all the relevant details we would need to see on a PersonalPlant
    + This can be observed by clicking on any plant in our Garden (list of PersonalPlants)   
    + The details shown are: Personal Plant name, last time watered, when to next water the plant
    + Also have buttons for removing from Garden*, and watering plant*
+ We completed Detailed User Story : "Modify Users Garden Plants"
    + Personal Plants can be added and removed from the Garden*   

+ Difficulty is now calculated within a Plant, versus as its own Difficulty class

Items marked with * are not effective due to the Non-Implemented features below
    
### Overview of Non-Implemented Features
+ We could not get the HSQLDB working

+ Note to markers: in DatabaseAccess.Java, the open() method can be filled with: 

    return _db == null ? new StubDatabase() : _db;

    + Replaces the HSQLDB with the stub database, so it doesn't crash immediately. 
    + Unfortunately many of the features in the iteration will not work with the stub. 
    + However, running AllTests passes when done like so

## Iteration 3

### Organization
+ Our source code is located in the src/main/comp3350/plantr directory. This contains our packages:     
    + business - contains our business logic
    + model - contains objects used throughout application
    + persistence - contains our database related files
    + presentation - our presentation/GUI layer   
    
+ Our unit test code is located under src/androidTest/comp3350/plantr
    + each class is contained in the same package its class is located in
+ Our acceptance test code is located under src/test/comp3350/plantr
    + each class corresponds with a big user story

### Overview of Implemented Features
+ We completed acceptance tests of 3 big user stories
    + NOTE: Depending on the machine, up to 3 tests will fail for unknown reasons. On windows machines there seems to be no failed tests
+ We completed integration tests
    + NOTE: integration tests have database issues and so won't actually test anything
+ Fixed the broken HSQLDB  
+ Implemented users
    + With user email, password, and their own garden
+ Personal Plant watering now tracked in DB
