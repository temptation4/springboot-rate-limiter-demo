# Spring Boot Rate Limiter using Resilience4j

## Overview

This project demonstrates how to implement Rate Limiting in Spring Boot using Resilience4j.

Rate Limiting controls the number of requests allowed within a specific time period.

Example:

Only 5 requests are allowed every 10 seconds.

If the request limit exceeds, fallback response is returned.

---

# Technologies Used

- Java 17
- Spring Boot 3
- Resilience4j
- Maven

---

# Maven Dependencies

```xml
<dependencies>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>io.github.resilience4j</groupId>
        <artifactId>resilience4j-spring-boot3</artifactId>
        <version>2.2.0</version>
    </dependency>

    <!-- Required for @RateLimiter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>

</dependencies>

application.yml

server:
  port: 8080

resilience4j:
  ratelimiter:
    instances:
      paymentService:
        limit-for-period: 5
        limit-refresh-period: 10s
        timeout-duration: 0


                    CLIENT REQUEST
                           │
                           ▼
                 ┌─────────────────┐
                 │   Controller    │
                 └────────┬────────┘
                          │
                          ▼
                 ┌─────────────────┐
                 │  Rate Limiter   │
                 └────────┬────────┘
                          │
             ┌────────────┴────────────┐
             │                         │
             ▼                         ▼
     Limit Available?            Limit Exceeded?
             │                         │
            YES                        YES
             │                         │
             ▼                         ▼
   Execute Service Method      Execute Fallback
             │                         │
             ▼                         ▼
   Payment Successful        Too Many Requests
