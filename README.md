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

  # GET /api/stocks
    API to get all the available stocks.
    
    Request Params : No Params
    Response Code  : 200
    Response       : [
                       {
                          "id": 1,
                          "name": "Nasdaq",
                          "currentPrice": 977.65,
                          "lastUpdate": "2021-07-22T18:32:37.569+00:00"
                      },
                      {
                          "id": 2,
                          "name": "Tesla",
                          "currentPrice": 300.45,
                          "lastUpdate": "2021-07-22T18:32:37.570+00:00"
                      }
                    ]
  
  # GET /api/stocks/{stockId}
    API to get a single stock details.
    
    Request Param : Id : 1
    Response Code : 200
    Response      : {
                      "id": 1,
                      "name": "Nasdaq",
                      "currentPrice": 977.65,
                      "lastUpdate": "2021-07-22T18:32:37.569+00:00"
                   }
    
  # PUT /api/stocks/{stockId}/{stockPrice}
    API to update the price of a single stock.
    
    Request Param : Id : 1 , Price : 200.00
    Response Code : 200
    Response      : {
                        "id": 1,
                        "name": "Nasdaq",
                        "currentPrice": 200.00,
                        "lastUpdate": "2021-07-22T18:44:35.621+00:00"
                    }
    
  # POST /api/stocks
    API to create or add a new stock
    
      Request Body : {
                        "id": 2,
                        "name": "Dettol",
                        "currentPrice": 292.67,
                        "lastUpdate": "2021-07-19T16:47:13.780+00:00"
                    }
    Response Code : 201
    Response      : {
                        "id": 2,
                        "name": "Dettol",
                        "currentPrice": 292.67,
                        "lastUpdate": "2021-07-19T16:47:13.780+00:00"
                    }
    
    
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
    
