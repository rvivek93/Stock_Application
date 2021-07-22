# Stock_Application
This application is to serve API's for 
1. Getting all the stocks.             
2. Getting details for a single stock.
3. Updating the price of a stock.
4. Adding or Creating a stock.

# Getting Started

  1. Checkout the project from github.

  https://github.com/rvivek93/Stock_Application
  
  2. Open IDE of your choice and Import as existing maven project in your workspace
  
  - Import existing maven project
  - Run mvn clean install
  - If using STS, Run As Spring Boot App

  3. Default port is 8989
  
# Prerequisites
  
  1. Java 8
  2. Maven 3.6

# Maven Dependencies

  spring-boot-starter-web
  spring-boot-devtools
  springfox-swagger2
  springfox-swagger-ui
  spring-boot-starter-test
  junit 5
  
# Swagger
  
  REST API documentation and testing can also be done using swagger API.
  
  http://localhost:8989/api/swagger-ui.html
  
# Testing the Stock Application
  
  1. Please use the Swagger url to perform operations.

# API's Implemented

  # GET http://localhost:8989/api/stocks
    API to get all the available stocks.
  
  # GET http://localhost:8989/api/stocks/{stockId}
    API to get a single stock details.
    
  # PUT http://localhost:8989/api/stocks/{stockId}/{stockPrice}
    API to update the price of a single stock.
    
  # POST http://localhost:8989/api/stocks
    API to create or add a new stock
    
 # Implementation
   
   When the application loads the Command Line Runner will run and upload the initial stocks into the in memory data structure(Hash Map). The Keys are the Stock Id which will be used to store the data again into the hashMap data store.
   
 # Test Cases
   
   1. Covered test cases for all the API's and validated it using assert.
   2. Implemented Test cases for the Service calls and data formation.
   3. Implemented test cases for data store on application load.

# Running via Command Prompt
  
  1. Go to the folder where the application is stored, like Stock App.
  2. Then run mvn clean install.
  3. Build and tests will run.
  4. Then move to the target folder and do java - jar <jar name>.
  5. Application will be started on the default port 8989.
  
# Author
  
  Vivek
    
