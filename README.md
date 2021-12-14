# Fitshop 


My individual project for the SoftUni Spring Advanced course.
Try it out(V2) - https://fitshop-web-app.herokuapp.com/

This is a Spring MVC application that: 

* Has these database tables:

![database](database.png)

* Has multiple layers: Database layer, Repository layer, Service Layer,
Web layer. Each layer uses directly only the layer below it.


* Uses multiple Design Patterns, such as: MVC,
  Front Controller, Repository , Singleton, Builder, etc.


* Uses the SOLID principles.


* Uses Spring Security to manage users and roles:
  * Users can log in/register, view details about products, 
  order products, view their profile, upload a profile picture, 
  view their orders in their profile and logout.
  * The Admin is permitted to all the user functionality, as well as
  adding, editing and deleting products, viewing all orders in the application and
  having access to statistics.


* Implements error handling and data validation both client and 
server-side. When validating data, the application shows appropriate messages to the user.


* Implements responsive web page design using Boostrap.


* Uses the Thymeleaf template engine to dynamically display views.
It also has one REST Controller that is called upon by the JavaScript fetch API to asynchronously
load the orders in the user's profile.


* Has 2 Interceptors used to keep track of statistics.


* Has 1 Scheduler used to evict the cache of a request every 2 hours.


* Has Integration and Unit tests.


* Uses Cloudinary to store pictures.


* Uses ModelMapper.






