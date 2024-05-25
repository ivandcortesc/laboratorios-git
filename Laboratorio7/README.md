# design patterns

### Challenge 1 Design Solution Outline:
* **Global Configuration Management:** Design a system that ensures a single, globally accessible configuration object without access conflicts
  <br>   
For resolve this problem we need implemente the  singleton pattern design, or we can also create a Enum class, but in this case we will use Singleton as a solution problem.
  <br>  
To design a system that ensures a single, globally accessible configuration object without access conflicts, you can use the Singleton design pattern. This pattern ensures that a class has only one instance and provides a global point of access to it.
  <br>
In this example, GlobalConfig is a Singleton class that holds configuration values. The getInstance method ensures that only one instance of GlobalConfig exists. The getConfigValue and setConfigValue methods allow you to get and set configuration values, respectively.
 <br>
```java
public class GlobalConfig {
  private static GlobalConfig instance;
  private Map<String, String> configValues;

  private GlobalConfig() {
    configValues = new HashMap<>();
    // Load configuration values from a file or environment variables
  }

  public static synchronized GlobalConfig getInstance() {
    if (instance == null) {
      instance = new GlobalConfig();
    }
    return instance;
  }

  public String getConfigValue(String key) {
    return configValues.get(key);
  }

  public void setConfigValue(String key, String value) {
    configValues.put(key, value);
  }
}

 // calling the GlobalConfig class 
 GlobalConfig config = GlobalConfig.getInstance();
  String value = config.getConfigValue("key");

```

* **Dynamic Object Creation Based on User Input:** Implement a system to dynamically create various types of user interface elements based on user actions.
  <br>  
To implement a system that dynamically creates various types of user interface elements based on user actions, you can use the Factory design pattern. This pattern provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.
  <br>  
In this example, UIElement is an abstract class that represents a user interface element. ButtonElement and TextFieldElement are subclasses that represent specific types of user interface elements. UIElementFactory is a factory class that creates UIElement objects based on a string input.

```java
import javax.swing.*;

public abstract class UIElement {
    public abstract JComponent create();
}

public class ButtonElement extends UIElement {
    public JButton create() {
        return new JButton("Button");
    }
}

public class TextFieldElement extends UIElement {
    public JTextField create() {
        return new JTextField("TextField");
    }
}

public class UIElementFactory {
    public UIElement createElement(String type) {
        if (type.equals("Button")) {
            return new ButtonElement();
        } else if (type.equals("TextField")) {
            return new TextFieldElement();
        }
        return null;
    }
}

// In this code, factory.createElement("Button") creates a ButtonElement object, and element.create() creates a JButton object. You can add the JButton to a JPanel or other container to display it in the user interface.  This system allows you to dynamically create various types of user interface elements based on user actions. For example, you could listen for user actions (like button clicks or menu selections) and use the factory to create and display the appropriate user interface element.

  UIElementFactory factory = new UIElementFactory();
  UIElement element = factory.createElement("Button");
  JComponent component = element.create();


```

* **State Change Notification Across System Components:** Ensure components are notified about changes in the state of other parts without creating tight coupling.
  <br>
 We will implement the Observer design pattern to ensure components are notified about changes in the state of other parts without creating tight coupling. The Observer pattern defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
  <br>
 This  pattern defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
  <br>
```java
import java.util.ArrayList;
import java.util.List;

// The Subject class
public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);       
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    } 
}

// The Observer interface
public interface Observer {
    void update();
}

// Concrete Observer classes
public class ConcreteObserverA implements Observer {
    private Subject subject;

    public ConcreteObserverA(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "ConcreteObserverA: " + subject.getState() );
    }
}

public class ConcreteObserverB implements Observer {
    private Subject subject;

    public ConcreteObserverB(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "ConcreteObserverB: " + subject.getState() );
    }
}


```
Subject is the object whose state changes. It maintains a list of observers and notifies them when its state changes. Observer is an interface that defines the update method, which is called when the Subject's state changes. ConcreteObserverA and ConcreteObserverB are concrete classes that implement the Observer interface and react to the state changes of the Subject.
```java
Subject subject = new Subject();
new ConcreteObserverA(subject);
new ConcreteObserverB(subject);

subject.setState(1);
subject.setState(2);

```
<br>
ConcreteObserverA and ConcreteObserverB are attached to the Subject. When the Subject's state changes, both observers are notified and they print the new state. This ensures that components are notified about changes in the state of other parts without creating tight coupling.

* **Efficient Management of Asynchronous Operations:** Manage multiple asynchronous operations like API calls which need to be coordinated without blocking the main application workflow.
  <br> 
To manage multiple asynchronous operations like API calls without blocking the main application workflow, you can use the concept of Futures and Promises, or the async/await syntax in languages that support it.
  <br>

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncApiCall {
    public CompletableFuture<String> asyncApiCall(String url) {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate API call with delay
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Response from " + url;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AsyncApiCall asyncApiCall = new AsyncApiCall();

        CompletableFuture<String> future1 = asyncApiCall.asyncApiCall("http://api1.com");
        CompletableFuture<String> future2 = asyncApiCall.asyncApiCall("http://api2.com");

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2);

        // Do other tasks while waiting for API calls to complete

        // Wait for all futures to complete
        combinedFuture.get();

        // Now, the results of the API calls are available
        System.out.println(future1.get());
        System.out.println(future2.get());
    }
}


```
In this example, asyncApiCall is a method that simulates an asynchronous API call. It returns a CompletableFuture that completes when the API call is finished. In the main method, two API calls are started. The CompletableFuture.allOf method is used to create a new CompletableFuture that completes when all of the given futures complete. The get method is used to wait for a future to complete and get its result. This allows you to manage multiple asynchronous operations without blocking the main application workflow.
### Challenge 2 Project Simulation Report:

We have a system that manage the execution of multiple cronjob. <br>
* In this situation we need a GolbalConfigurationManager process that handle every on of these cronjobs.
<br>
* The GlobalConfigurationManager is a Singleton class that holds configuration values for the cronjobs. The getInstance method ensures that only one instance of GlobalConfigurationManager exists. The getConfigValue and setConfigValue methods allow you to get and set configuration values for the cronjobs, respectively.
<br>
* We need implement the Observer pattern to ensure that the cronjobs are notified when the configuration values change. The Observer interface defines the update method, which is called when the configuration values change. The CronJob class is a concrete observer that reacts to the changes in the configuration values.
<br>
* Every job depending on the configuration values will be executed in a different time, so we need to manage multiple asynchronous operations like API calls without blocking the main application workflow. The AsyncJobExecutor class uses CompletableFuture to simulate asynchronous job execution. The main method starts multiple asynchronous jobs and waits for them to complete using CompletableFuture.allOf.
* For implement this solution we need to create the following classes:
* GlobalConfigurationManager: Singleton class that holds configuration values for the cronjobs.
* Observer: Interface that defines the update method for observers.
* CronJob: Concrete observer that reacts to changes in the configuration values.
* AsyncJobExecutor: Class that simulates asynchronous job execution using CompletableFuture.
* Factory: Class that creates the cronjobs based on the configuration values. 
* Main: Class that starts multiple asynchronous jobs and waits for them to complete.


    
        
      
      

  
