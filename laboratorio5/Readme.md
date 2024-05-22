# Labortaorio 5

## 1. SQL Query Optimization
####  1.1- Orders Query: Retrieve orders with many items and calculate the total price.

DE primera mano tenemos que validar que ambas tablas tengan sus respectivos indices.
En la clusula Where OrderDetails podria tener miles/millones de registros por lo que

```
SELECT Orders.OrderID, SUM(OrderDetails.Quantity * OrderDetails.UnitPrice) AS TotalPrice
FROM Orders
JOIN OrderDetails ON Orders.OrderID = OrderDetails.OrderID
WHERE OrderDetails.Quantity > 10
GROUP BY Orders.OrderID;

```
Para solucionar el tema, primero tendriamos que verificar si las tablas  Orders y OrderDetails tiene un indice, si no los creamos con la siguinete instruccion:
```
CREATE INDEX idx_orders_orderid ON Orders(OrderID);
CREATE INDEX idx_orderdetails_orderid ON OrderDetails(OrderID);
```

Este seria el query optimizado
```
SELECT Orders.OrderID, SUM(OrderDetails.Quantity * OrderDetails.UnitPrice) AS TotalPrice
FROM Orders
JOIN (SELECT Quantity, UnitPrice FROM OrderDetails WHERE Quantity > 10) AS FilteredOrderDetails
ON Orders.OrderID = FilteredOrderDetails.OrderID
GROUP BY Orders.OrderID;
```

Otra solucion es solo utilizar el query
```
SELECT OrderID , Quantity, UnitPrice 
FROM OrderDetails 
WHERE Quantity > 10
```
A menos que obtengamos otro dato que no sea el OrderID hace sentido que hagamos el join.

#### 1.2 Customer Query: Find all customers from London and sort by CustomerName.
Este Query esta casi optimizado, solo faltaria crear los indices para poder consultar por ciudad
```
SELECT CustomerName FROM Customers WHERE City = 'London' ORDER BY CustomerName;

```
CReamos el indice para mejorar la busqueda por ciudad y por el nombre : 
```
CREATE INDEX index_city ON Customers(City);
CREATE INDEX index_customername ON Customers(CustomerName);
```

## 2. NoSQL Query Implementation
### 2.1 User Posts Query: Retrieve the most popular active posts and display their title and like count.

Query original : 
```
db.posts
.find({ status: "active" }, { title: 1, likes: 1 })
.sort({ likes: -1 });

```
Para optimizar el query se debe crear un indice para la columna status y likes
```
db.posts.createIndex({ status: 1 });
db.posts.createIndex({ likes: -1 });
```


### 2.2 User Data Aggregation: Summarize the number of active users by location.


Query original :

```
db.users.aggregate([
  { $match: { status: "active" } },
  { $group: { _id: "$location", totalUsers: { $sum: 1 } } },
]);
```
Para optimizar el QUery se debe crear un indice para la columan 1 que es status.
```
db.users.createIndex({ status: 1 });
```



