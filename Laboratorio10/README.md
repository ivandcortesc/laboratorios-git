## lab10 

###  A description of the original code problems :
* The below function updateList(items) can be optimized to reduce excessive DOM manipulation.
* The current implementation creates a new list item and appends it to the list for each item in the items array. 
* This results in multiple reflows and repaints of the page, which can be costly in terms of performance.  

Issues identified : 

Inefficient Loops : Excessive DOM Manipulation
      



## JavaScript Snippet:
```javascript

// Inefficient loop handling and excessive DOM manipulation
function updateList(items) {
  let list = document.getElementById("itemList");
  list.innerHTML = "";
  for (let i = 0; i < items.length; i++) {
    let listItem = document.createElement("li");
    listItem.innerHTML = items[i];
    list.appendChild(listItem);
  }
}
```
* In this version, the forEach method is used to iterate over the items, which is more idiomatic in modern JavaScript. The list.innerHTML = ""; line is moved after the loop to clear the list just before the new items are appended. This prevents the list from being cleared unnecessarily if the items array is empty.
#### Detailed explanations of the changes made.
* A more efficient approach would be to create a document fragment, append all the list items to this fragment, and then append the fragment to the list. 
* This way, the DOM is updated only once, regardless of the number of items.
```javascript

function updateList(items) {
  let list = document.getElementById("itemList");
  let fragment = document.createDocumentFragment();

  items.forEach(item => {
    let listItem = document.createElement("li");
    listItem.innerHTML = item;
    fragment.appendChild(listItem);
  });

  list.innerHTML = "";
  list.appendChild(fragment);
}


```
###  Discussion on the expected or observed outcomes of these changes in terms of performance improvement.
* The function updateList(items) can be further optimized by reducing the number of times document.createElement("li") is called. Instead of creating a new list item for each item in the array, we can create a single string of HTML and then set the innerHTML of the list to this string. This reduces the number of times the browser has to create new elements, which can be a costly operation.

```javascript
function updateList(items) {
    let list = document.getElementById("itemList");
    let html = items.map(item => `<li>${item}</li>`).join("");
    list.innerHTML = html;
}
```
* In this version, the map method is used to create an array of list item strings for each item in the items array. The join method is then used to combine these strings into a single string of HTML. This string is then set as the innerHTML of the list, effectively replacing all the list items in one operation.


###  A description of the original code problems :
* The provided Java code can be optimized by reducing the number of database queries. Currently, the `loadProducts` method is making a separate database query for each product ID, which can be inefficient if there are a large number of products.
* If your database supports it, a more efficient approach would be to make a single query that retrieves all products at once. This would reduce the number of round trips between your application and the database, which can significantly improve performance.

#### Issues identified
 **Redundant database queries**
 **Inefficient Loops** : Excessive Database Queries
## Java  Snippet:

* The issue here is that we are making a separate database query for each product ID in the loop. This can be inefficient if there are a large number of products, as it results in multiple round trips between the application and the database. A more efficient approach would be to make a single query that retrieves all products at once.
```java 

// Redundant database queries
public class ProductLoader {
    public List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        for (int id = 1; id <= 100; id++) {
            products.add(database.getProductById(id));
        }
        return products;
    }
}
```



#### Detailed explanations of the changes made.

* In this version, the `loadProducts` method calls  `getAllProducts` method on the `database` object, which is assumed to retrieve all products in a single query. 
* Also, We can implement some caching mechanism to avoid multiple database queries for the same product ID.
```java
public class ProductLoader {
    public List<Product> loadProducts() {
         Database database = new Database();
        return database.getAllProducts( 1, 100, Product.class);
    }
    
    private  List<T> getAllProducts( int initialElement, int lastElement, Class<T> Product) {

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE product_id BETWEEN ? AND ?");
        statement.executeQuery();
        ResultSet resultSet = statement.getResultSet();
        // method to convert resultSet to List of Products
        List<Product> products = convertResultSetToList(resultSet);    
        return products; 
    }
    
}




```

###  Discussion on the expected or observed outcomes of these changes in terms of performance improvement.
#### Issues identified
**Redundant database queries**
**Inefficient Loops** : Excessive Database Queries


## C#  Snippet:
```C#

// Unnecessary computations in data processing
public List<int> ProcessData(List<int> data) {
    List<int> result = new List<int>();
    foreach (var d in data) {
        if (d % 2 == 0) {
            result.Add(d * 2);
        } else {
            result.Add(d * 3);
        }
    }
    return result;
}

```

#### Detailed explanations of the changes made.

*  The Select method is used to transform each element in the data list. 
* The result is a new list where each element is either twice or three times the corresponding element in the input list, depending on whether the element is even or odd. 
* The ToList method is then used to convert the resulting enumerable back into a list.


```C#
public List<int> ProcessData(List<int> data) {
    return data.Select(d => d % 2 == 0 ? d * 2 : d * 3).ToList();
}


```

###  Discussion on the expected or observed outcomes of these changes in terms of performance improvement.

