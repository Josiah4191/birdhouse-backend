### Shipper Controller

| Method | Endpoint       | Description            |
|--------|----------------|------------------------|
| GET    | /shippers      | Get all shippers       |
| POST   | /shippers      | Create a shipper       |
| DELETE | /shippers/{id} | Delete a shipper by id |
| GET    | /shippers/{id} | Get a shipper by id    |
| PATCH  | /shippers/{id} | Update a shipper by id |


### Inventory Part Controller

| Method | Endpoint    | Description         |
|--------|-------------|---------------------|
| GET    | /parts      | Get all parts       |
| POST   | /parts      | Create a part       |
| DELETE | /parts/{id} | Delete a part by id |
| GET    | /parts/{id} | Get a part by id    |
| PATCH  | /parts/{id} | Update a part by id |


### Shipment Controller

| Method | Endpoint                               | Description             |
|--------|----------------------------------------|-------------------------|
| GET    | /shipments                             | Get all shipments       |
| POST   | /orders/{orderId}/shippers/{shipperId} | Create a shipment       |
| DELETE | /shipments/{id}                        | Delete a shipment by id |
| GET    | /shipments/{id}                        | Get a shipment by id    |
| PATCH  | /shipments/{id}                        | Update a shipment by id |


### Employee Controller

| Method | Endpoint        | Description              |
|--------|-----------------|--------------------------|
| GET    | /employees      | Get all employees        |
| POST   | /employees      | Create a employee        |
| DELETE | /employees/{id} | Delete an employee by id |
| GET    | /employees/{id} | Get an employee by id    |
| PATCH  | /employees/{id} | Update an employee by id |


### Customer Controller

| Method | Endpoint        | Description             |
|--------|-----------------|-------------------------|
| GET    | /customers      | Get all customers       |
| POST   | /customers      | Create a customer       |
| DELETE | /customers/{id} | Delete a customer by id |
| GET    | /customers/{id} | Get a customer by id    |
| PATCH  | /customers/{id} | Update a customer by id |


### Customer Order Controller

| Method | Endpoint                      | Description                        |
|--------|-------------------------------|------------------------------------|
| GET    | /orders                       | Get all orders                     |
| POST   | /orders/customer/{customerId} | Create a customer order            |
| DELETE | /orders/{id}                  | Delete an order by id              |
| GET    | /orders/{id}                  | Get an order by id                 |
| PATCH  | /orders/{id}                  | Update an order by id              |
| GET    | /orders/customer/{id}         | Get customer orders by customer id |