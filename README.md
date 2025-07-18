# Retail Discount Engine

A modular Java backend system to calculate and apply retail discounts based on customer type, loyalty, and purchase contents.

---

## Features

- REST API to calculate net payable amount on a bill
- Percentage-based discounts for Employees, Affiliates, and Loyal Customers
- Flat discount of $5 for every $100 spent
- Exclusion of groceries from percentage discounts
- Strategy Pattern for discount logic (easily extendable)
- In-memory H2 database with sample data
- High unit test coverage with Mockito and JUnit
- Clean architecture using Spring Boot & OOP principles

---

## Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database
- RESTful APIs
- Maven
- JUnit 5 + Mockito
- JaCoCo (test coverage)
- Checkstyle / PMD (static analysis)
- Optional: SonarQube, Jenkins, Docker

---

## Build & Run

### Clone the repository
## bash
git clone https://github.com/Princeraj3888/retail-discount-engine.git
cd retail-discount-engine

## Build the Project
##```bash
mvn clean install

## Run the Application
mvn spring-boot:run

Or package and run:

mvn package
java -jar target/retail-discount-engine.jar

## run unit tests 
mvn test

## Access API
Base URL: http://localhost:8000

## Example Endpoint:
POST method, 
http://localhost:8000/api/billing/calculate

## example curl request
curl --location 'http://localhost:8000/api/billing/calculate' \
--header 'Content-Type: application/json' \
--data '{
  "customerId": 1,
  "items": [102, 103]
}'

curl --location 'http://localhost:8000/api/billing/calculate' \
--header 'Content-Type: application/json' \
--data '{
"customerId": 2,
"items": [102, 103]
}'

curl --location 'http://localhost:8000/api/billing/calculate' \
--header 'Content-Type: application/json' \
--data '{
"customerId": 3,
"items": [102, 103]
}'

curl --location 'http://localhost:8000/api/billing/calculate' \
--header 'Content-Type: application/json' \
--data '{
"customerId": 4,
"items": [102, 103]
}'

## Key Business Rules Implemented
Employee → 30% discount
Affiliate → 10% discount
Loyal Customer (over 2 years) → 5% discount
Flat Discount: $5 off per $100 spent (applies to all customers)
Groceries Exclusion: Percentage discounts don’t apply to grocery items
Only one percentage discount applies per order (choose the highest one)

## Key Design Decisions & Assumptions
Strategy Pattern for Discounts
Each discount (Employee, Affiliate, etc.) is a DiscountStrategy implementation.
At runtime, the highest applicable strategy is selected based on priority.

Flat Discount Logic
Flat discount is applied after percentage discount.
Applies to all items, regardless of category or customer role.

Percentage Discount Scope
Applies only to non-grocery items.
Calculated first, then flat discount is computed on the reduced amount.

Extendability
New discount types (e.g., student, seasonal) can be added by implementing the DiscountStrategy interface.
No change required in controller or service logic.

Error Handling
Returns 400 Bad Request for missing or invalid customer/item IDs.
Returns 404 Not Found if the customer or any item doesn't exist.

Assumptions
Only one percentage-based discount is applied per order.
All prices are in USD.
Bill history is optional and not persisted in current version.
Blacklisted customers are ignored or excluded if used.


## H2 console
http://localhost:8000/h2-console/
jdbc url: jdbc:h2:mem:retail_discount_engine_db
username: sa

note : the initial data added using data.sql from code

## uml diagram path 
retail-discount-engine
/docs/uml.png

## git hub repository
https://github.com/Princeraj3888/retail-discount-engine.git