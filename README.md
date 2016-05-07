# restService

To run from command line:
- Fork and clone repo
- run ./gradlew build 
- run java -jar ./build/libs/rest-service-0.1.0.jar

To run through eclipse:
- Fork and clone repo
- Import>Existing Maven Project
- Right click on project
- Run as>Maven Build> clean install
- Right click on project
- Run as>Java Application

The project will run on localhost:8080
set header as Content-Type:application/json in a RESTClient

JSON Structure:
{
  "id" : int,
  "name" : string,
  "description" : string
}


REST calls:
- POST: url = /v1/products with JSON (id will be automatically generated) 
request body:
{
  "name" : "productName",
  "description" : "productDesc"
}
- GET: url = /v1/products?id=1
- PUT: url = /v1/products with JSON
request body:
{
  "id": 1,
  "name" : "productName",
  "description" : "productDesc"
}
- DELETE: url = /v1/products?id=1

