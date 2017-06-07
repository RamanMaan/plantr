# Activity Log

### Raman - 5/17/2017
- Initialized repo, added other group members & bristow
- Started up a messy gitignore, will clean up later on
- Austin still needs to be added to repo
- project structure should be figured out
---
### Raman - 5/22/2017
- Set up project structure
  - Added temp .gitkeep files to commit folder structure
- Set up temporary log as this md log, we should look at better solutions
- Cleaned up ReadMe
- Set up Issue Tracker
  - Setup Milestone 1
    - Added an example dev task and defect to propose an organization method
- Setup Plantr project
    - Added some columns to illustrate dev task movement and defect movement
- To-Do:
  - add dev tasks to issue tracker
  - possibly add Kotlin (must discuss with Bristow)
---
### Keaton - 5/26/2017
- Researched possible database implementations.
- Found out that SQLite is integrated into Android Studio.
- Wrote code to create a "PLANT" table with: ID, NAME, DESCRIPTION columns as a sample table.
- Pushed changes so others can review and discuss if this is a possible database implementation we want to pursue for development.
- Will implement generic CRUD operations for the sample table if this is the implementation we choose.
---
### Raman - 5/28/2017
- Added dev tasks to issue tracker
- Assigned myself [DT3-I1](raman-maan/plantr/issues/3)
- Added project consistent coding standards to git for easier control
- Got a basic CLI working using the sample project as a guide
- Created a Plant object holding the information required for [DT3-I1](raman-maan/plantr/issues/3) and [DT4-I1]((raman-maan/plantr/issues/4))
- Added a temperature object - need to discuss where we'll keep "helper" classes
- Created a Database interface and an empty database stub for this iteration
---
### Group Discussion - 5/30/2017
- Discussed architecture of project.
- Heavy DB work for next iteration (lots of functionality depends on the DB).
- Three-point scale for plant difficulty based on How hardy it is, how long it can go without water, if it needs any other care, etc.
- GUI design discussed.
- Iteration focus discussed.
- Everyone claimed an issue.
- We identified our workflow:
  - iteration will be the branch with all the information of our current iteration. 
  - We do feature branches off Iteration and do dev work in those, then merge them back into Iteration. 
  - Once the iteration is complete we merge it into master. Bug fixes go into Iteration and are merged into master.
---
### Keaton - 5/31/2017
- Added a stub database class to perform basic creation of plants as JSONObjects to be used as test data.
- Added a stub database interface to allow abstraction from implementation details.
- Created a stub database test class that tests the current methods in the stub database.
- Added testCompile 'org.json:json:20140107' to build.gradle file to allow JSON objects within Unit Tests.
---
### Keaton - 6/01/2017
- Refactored stub database functions with more accurate sample test data so they can be used more robustly throughout development
- Refactored stub database tests to model these changes
- Created a class and interface for the "PlantLookup" page of the application
- Implemented a function to lookup a plant by name from the stub database
- Created a PlantLookupTest class to test current and future functions in the PlantLookup class
- Created a "Constants" class under application for constants that will be used across the entire applcation
---
### Group Discussion - 6/04/2017
- Discussed project state
- Stub database overhaul
- Discussed requirements + implementation of GUI for plant view
- Distributed remaining high priority tasks
---
### Raman - 6/04/2017
- Completed [DT3-I1](raman-maan/plantr/issues/3)
  - Waiting on Kevin to complete GUI portion
- Added an AllTests.java file to organize tests
- Code reviewed #16
---
### Keaton - 6/01/2017
- Reviewed current Iteration branch and removed code that would be replaced by the newly added changes for the StubDatabase
- Reworked current StubDatabase functions to use ArrayLists to store records
- Reworked tests for the StubDatabase functions to use ArrayLists
- Implemented a Singleton pattern for the database
---
### Keaton - 6/06/2017
- Revised all PlantLookup Class / Interface functions to use different code structure (ArrayLists)
- Refactored build.gradle file since we will not be using JSONObjects
- Removed unnecessary code from main
---
### Michael - 06/06/2017
- Created a Difficulty class that represents how difficult it is to take care of a Plant
- Finished [DT5-I1](https://github.com/Raman-Maan/plantr/issues/5)
---
