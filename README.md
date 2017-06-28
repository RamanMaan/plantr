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