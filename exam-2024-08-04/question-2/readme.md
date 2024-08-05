## a) Analyze how inheritance and polymorphism are manifested in the following mock-up scenario. Focus on how the different catch blocks handle exceptions.

```java
abstract class AIException extends Exception {}
class DataProcessingError extends AIException {}
class ModelTrainingError extends AIException {}

class AIPipeline {
    void run() throws DataProcessingError, ModelTrainingError {
        if (new java.util.Random().nextBoolean())
            throw new DataProcessingError();
        else
            throw new ModelTrainingError();
    }
}

class AIPipeline2 {
    void run() throws AIException {
        if (new java.util.Random().nextBoolean())
            throw new DataProcessingError();
        else
            throw new ModelTrainingError();
    }
}

void main() {
    var pipeline1 = new AIPipeline();
    var pipeline2 = new AIPipeline2();

    try {
        pipeline1.run();
        pipeline2.run();
    }
    catch (DataProcessingError dp) {}
    catch (ModelTrainingError mt) {}
    catch (AIException ae) {}
}
```

### Answer:
* **Inheritance** is expressed as the hierarchy below:
```
                        ┌─────────────────────────┐                       
                        │                         │                       
                        │        Exception        │                       
                        │                         │                       
                        └────────────▲────────────┘                       
                                     │                                    
                                     │                                    
                                     │                                    
                        ┌────────────┴────────────┐                       
                        │                         │                       
                        │       AIException       │                       
                        │                         │                       
                        └────────────▲────────────┘                       
                                     │                                    
                                     │                                    
                                     │                                    
           ┌─────────────────────────┴────────────────────────┐           
           │                                                  │           
           │                                                  │           
┌──────────┴──────────────┐                    ┌──────────────┴──────────┐
│                         │                    │                         │
│   DataProcessingError   │                    │    ModelTrainingError   │
│                         │                    │                         │
└─────────────────────────┘                    └─────────────────────────┘
```
<br />

* **Polymorphism** is recognized as the code below:
```java
class AIPipeline2 {
    void run() throws AIException {
        if (new java.util.Random().nextBoolean())
            throw new DataProcessingError();
        else
            throw new ModelTrainingError();
    }
}
```
and
```java
try {
    pipeline1.run();
    pipeline2.run();
}
catch (DataProcessingError dp) {}
catch (ModelTrainingError mt) {}
catch (AIException ae) {}
```
In this code, **Polymorphism** is defined as the ability to reference an object (exception objects in this case: `DataProcessingError`, `ModelTrainingError`) through the type references of its superclass (`AIException`).

<br />

## b)  Analyze and evaluate the following code. Think it also from the perspective of the real use-case.

    Why does it work / not work?
    Discuss the advantages and disadvantages of this implementation.

```java
class CurrencyConverter {
    Object convert(double amount, String currency) {
        return switch (currency) {
            case "€" -> amount * 1.1; // USD to EUR
            case "GBP" -> amount * 0.9; // USD to GBP
            case "JPY" -> amount * 110; // USD to JPY
            default -> amount * 2;; // defaults to 2x currency
        };
    }
}

class SpecificCurrencyConverter extends CurrencyConverter {
    @Override
    Double convert(double amount, String currency) {
        return amount * 1.2;
    }
}
```

### Answer:
- It **works with Java > 5.0** because Java supports **covariant return** types when overriding methods. Before Java 5.0, when we override a method, both parameters and return type must match exactly.
    + Reference [link](https://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.4.5):
>Return types may vary among methods that override each other if the return types are reference types. The notion of return-type-substitutability supports covariant returns, that is, the specialization of the return type to a subtype.


- Advantages:
    + To limit the type of return type based on the logic. For example: limit to `Double` in this case.
    + Avoid type conversion, e.g: we can use Math operations with the return value without type casting.

- Disadvantages:
    + Potential Confusion: Covariant return types can lead to confusion and make code harder to understand, especially in complex inheritance hierarchies.
