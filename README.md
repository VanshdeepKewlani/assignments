# assignments
Assignments worked on for Wallethub

WalletHub Assignments


The WalletHub Assignments are split into two parts:

Assignment1 – In this user signs-in Facebook and posts a feed. Once this is done, we need to verify the feed.

Assignment2 – In this user signs-in to Wallethub site and provides star rating and writes review text. Once this is posted, user has to verify whether the Reviews are correct or not.


Project Structure

src/main/java – This contains the main code to be used
     com.wallethub.assignments – This is the parent package of both assignments
       assignment1 – This package contains the code specific to Facebook assignment
         assignment2 – This package contains the code specific to Wallethub assignment
          utilities – This package contains the Common Library file having the common functions shared between both assignments     

src/test/java – This contains the Test Script prepared using TestNG
     assignments1_test – This package contains the test for assignment1 i.e. of Facebook
     assignments2_test - This package contains the test for assignment2 i.e. of Wallethub

src/test/resources – This contains the properties file used by both assignments respectively 

lib  - This contains the chromedriver.exe to launch Chrome Browser

screenshots – The screenshots are saved in this folder after execution of test

doc – doc folder contains the Javadoc generated for code in order to get idea about the functions, fields and classes in the code



Common Logic between both Assignment1 and Assignment2

context – This package contains the code used for sharing across all files
     ScenarioContext – This is used to store and retrieve the values in Context enum which can be shared across throughout code as a global set of values
     TestContext – This acts as a main class to access the objects of core classes in the Framework

enums – This package contains the enum Context which acts as a global storage repository inside the package

managers – This package contains the code for managing the Driver related code and handling of pages
      DriverManager – This class contains all the logic related to handling of WebDriver e.g. launching and closing browser, navigating to URL etc. It also contains code of reading the properties files present in /src/test/resources
     PageObjectManager – This class contains the logic for managing the object creation of Pages thus ensuring we do not have to worry about issues related to pages like NullPointerException in case page object creation code is missing

pageObjects – This package contains the code for handling pages using Page Object Model
     

