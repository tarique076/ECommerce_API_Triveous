# ECommerce_API_Triveous
Assignment for Triveous hiring process.

### Tech Stacks

- Spring Boot 
- Spring Framework
- Spring Data JPA 
- MySQL 
- Hibernate
- Java
- Layered Architechture
- Lombok
- Postman
- Spring Security

### Modules
- User Module
-	Category Module
-	Products Module
-	Cart Module
-	Orders Module


### Installation & Run
- Clone the Repository in your Local system.
- Before running the API server, you have to update the database configuration inside the application.properties file
- Update the port number, username and password as per your local database configuration.
- Make sure to create a database with name "ecommerce_triveousDB" in MySQL.
````
    server.port=8089

    spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_triveousDB
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root
    
````

### Functionalities
- User Module:-
    * Registration to the system.
    * API Endpoints
       POST ( http://localhost:8089/user/register ) :- Register a user.
RequestBody:- 
````
{
    "userName" : "anwartarique408@gmail.com",
    "password" : "tarique088",
    "authorities":[
        {
            "name" : "ROLE_ADMIN"
        },
        {
            "name": "ROLE_USER"
        }
    ]
}
````

  ## Login
  * API Endpoints
  * GET ( http://localhost:8089/logIn ) :- Login to the system.
Make sure you select Basic auth in Authorization and pass the username and password that you used while registering. You will receive a JWT token in the Headers section with Key as "Authorization".
Please keep this JWT token handy as it will be used in further requests.
    
## Category Module:-
   * Add a new category to the system. Only available to ADMIN when logged in.
      * API Endpoints
      * POST ( http://localhost:8089/category/add ) :- Add a new Category. Pass the JWT token in Authorization section after selecting the Auth type as Bearer Token.
RequestBody
````
{
    "categoryName" : "T-Shirts"
}
````

  * View categories. Accessible by both ADMIN and USER. You don't need to log in.
      * API Endpoints
      * GET ( http://localhost:8089/category/getAll ) :- Get all categories. No need to pass the JWT Token here. It is accessible without Authentication.

## Products Module:-
  * Add a product. Category must be chosen while adding a product. Available only to ADMIN when logged in.
    * API Endpoints
    * POST ( http://localhost:8089/product/add ) :- Add a new product. Pass the JWT token in Authorization section after selecting the Auth type as Bearer Token.
RequestBody
````
{
    "title" : "USPA T-shirt",
    "description" : "Blue color plain USPA t-shirt",
    "availability": 10,
    "price" : 2099,
    "category": {
        "categoryId" : 1,
        "categoryName" : "T-shirts"
    }
}
````

      
  * Find product by categoryId. Available to all users. Login is not required.
      * API Endpoints
      * GET ( http://localhost:8089/product/getByCategory?categoryId=1 ) :- Get products in a particular category. You can change the categoryId in the uri according to your requirement. No need to pass the JWT Token here. It is accessible without Authentication.

      
  * Find product by productId. Available to all users. Login is not required.
      * API Endpoints
      * GET ( http://localhost:8089/product/getByProductId?productId=2 ) :- Get a product through productId. You can change the productId in the uri according to your requirements. No need to pass the JWT Token here. It is accessible without Authentication.
      
       
## Cart Module:-
  * Add product to Cart. Available to both ADMIN and USER only if logged in.
    * API Endpoints
    * POST ( http://localhost:8089/cart/addProduct?productId=1&quantity=2 ) :- Add product to cart. Specify the productId & quantity in the request param in uri according to your requirement. Pass the JWT token in Authorization section after selecting the Auth type as Bearer Token.

      
  * Get Cart details. Available to both ADMIN and USER only if logged in.
    * API Endpoints
    * GET ( http://localhost:8089/cart/get ) :- Get your cart details. Pass the JWT token in Authorization section after selecting the Auth type as Bearer Token.
    
  
  * Update quantity of a product in the cart. Available to both ADMIN and USER only if logged in.
    * API Endpoints
    * PUT ( http://localhost:8089/cart/updateQuantity?productId=1&quantity=3 ) :- Update the quantity of a product in the Cart. Pass the productId of the product you want to change the quantity of and the new quantity in the request param in the uri. Pass the JWT token in Authorization section after selecting the Auth type as Bearer Token.
  
  * Delete a product from the cart. Available to both ADMIN and USER only if logged in.
    * API Endpoints
    * DELETE ( http://localhost:8089/cart/deleteProduct?productId=1 ) :- Remove a product from the cart. pass the productId of the product that you want to remove from the cart int the uri in request param. Pass the JWT token in Authorization section after selecting the Auth type as Bearer Token.

      
## Orderd Module:-
  * Place an order. The products in the cart will be ordered and the cart will be emptied. Available to both ADMIN and USER only if logged in.
      * API Endpoints
      * POST ( http://localhost:8089/orders/place ) :- Place order. Pass the JWT token in Authorization section after selecting the Auth type as Bearer Token.
      
  * Find order by orderId. Available to both ADMIN and USER only if logged in.
    * API Endpoints
    * GET ( http://localhost:8089/orders/findById?orderId=1 ) :- Get order by orderId. Pass the JWT token in Authorization section after selecting the Auth type as Bearer Token.

  
  * Get Order History. Available to both ADMIN and USER only if logged in.
     * API Endpoints
     * GET ( http://localhost:8089/orders/history ) :- Get complete order history. Pass the JWT token in Authorization section after selecting the Auth type as Bearer Token.



##   ER_Diagram                                            


