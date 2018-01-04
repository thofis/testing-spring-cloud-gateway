Testing Spring Cloud Gateway
============================

This repository contains a simple example project for testing Spring
Cloud Gateway.

I chose a simple live stock-ticker with random values as the „business"
domain for the project.

It consists of four separate Micro-Services implemented with "Spring Boot 2"
Each service is contained within each own directory

-   frontend\
    Contains static HTML and JS representing the GUI of the
    project.\
    The Spring-Boot app is used purely as a web server.
-   gateway\
    contains the gate-way component based on Spring Cloud Gateway
-   stock-names\
    contains a rest endpoint for retrieving company names
-   stock-values\
    contains a websocket-endpoint for generating random stock values.
    
Running the example application
-------------------------------
- Build the project by calling ```mvn clean package``` from base dir
- Run the four microservices, e.g. by calling ```mvn spring-boot:run``` from within each dir
- the frontend should be accessible under ```http://localhost:8080```

Issues
------
- SockJS uses http based fallback at the moment




