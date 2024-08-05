Next, let's consider which class constructions are suitable for describing the presented situations. Reflect on Java class constructions in the task and select, with justification, which one is appropriate for the task. Also, briefly explain why you would exclude other options.
## a) We want to query the customer database for clients registered in 2024. The database query returns a list of entries, where each entry consists of five attributes (Name, Address, Email Address, Purchased Product, Purchase Date).

### Answer:
- As it a database query result, so it should be immutable and `Record` is suitable in this case.


**Why not other options:**
- There is no relationship with other objects -> `Static Inner Class`, `Nested Class`, `Anonymous Class`, `Function literals and interfaces`, `Sealed class` are not suitable.
- There are unknown records of clients -> `Enum` is not suitable.
- We can also implement it with a final basic class with final members and hashCode & equals that compare members in pairs.

**Example implementation:**
```java
record Entry(String name, String address, String email, String purchasedProduct, LocalDate purchaseDate) {
}
```

<br />

## b) We want to operate on the list from the previously mentioned customer database (type List\<Entry>) and filter out all clients whose purchase date is before January 2023. The remaining list contains those who are eligible for a loyalty reward. Instead of writing the filter again as a for-loop each time, it should be reusable. The functional part of the filter's signature could be a boolean filter(Entry entry).

### Answer:
- We choose the `Basic class` (for the filter interface) and `Record` (for the implementation) in this case for reusable as we don't want to repeat the filter, and there is no property in this filter.
- We can use `Basic class` instead of `Record` also.

**Why not other options:**
- There is no relation with other objects in this case => `Static Inner Class`, `Nested Class` or `Sealed class` is not suitable.
- `Enum` is not suitable in this case as there is no predefined values (objects).
- `Anonymous class`, `Function literals and interfaces` is not suitable as we have to repeat the logic each time we want to filter.

**Example implementation:**
```java
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Entry> entries = new ArrayList<>();

        entries.add(new Entry("Name 1", "Address 1", "email1@email.com", "Product 1", LocalDate.of(2023, 1, 2)));
        entries.add(new Entry("Name 2", "Address 2", "email2@email.com", "Product 2", LocalDate.of(2023, 1, 1)));
        entries.add(new Entry("Name 3", "Address 3", "email3@email.com", "Product 3", LocalDate.of(2022, 12, 30)));

        LoyaltyCustomerSearchResultFilter filter = new LoyaltyCustomerSearchResultFilter();
        List<Entry> loyaltyCustomers = entries.stream().filter(filter::filter).toList();

        loyaltyCustomers.forEach(System.out::println);
    }
}

record Entry(String name, String address, String email, String purchasedProduct, LocalDate purchaseDate) {
}

interface CustomerSearchResultFilter {
    boolean filter(Entry entry);
}

record LoyaltyCustomerSearchResultFilter() implements CustomerSearchResultFilter {
    @Override
    public boolean filter(Entry entry) {
        return entry.purchaseDate().isBefore(LocalDate.of(2023, 1, 1));
    }
}
```

<br />

## c) We want to model financial transactions. A transaction has value semantics and behaves differently than a double, but it also includes information that it is a transaction. Different types of transactions (Deposit, Withdrawal, Transfer) should be supported. Additionally, the transaction should be displayed according to its type, e.g., "Deposit of $100", or "Withdrawal of €50". It should be easy to perform operations with different transaction types.

### Answer:
- We choose the `Record` for the **transaction** in this case as it has value semantics, and the values are not fixed.
- For the **type of transaction**, we choose `Enum` as there are limited types (Deposit, Withdrawal, Transfer). And it's also a `Static Inner Class` of `Record` transaction. 
- When perform operations with different transaction type, we return a new instance of this `Record`.

**Why not other options:**
- There are unlimited transactions can be created => we can not use `Enum` for transaction (although it is used for transaction type).
- There is no relation (no parent, no child) in this case => `Static Inner Class`, `Nested Class`, `Anonymous class` or `Sealed class` is not suitable.
- There are multiple operations => `Function literals and interfaces` is also not suitable.
- We can also implement it with a final basic class with final members and hashCode & equals that compare members in pairs.

**Example implementation:**
```java
public record Transaction(double value, Type type) {
    enum Type {
        DEPOSIT("Deposit"), WITHDRAWAL("Withdrawal"), TRANSFER("Transfer");

        final String name;

        Type(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public Transaction {
        Objects.requireNonNull(type, "Type should not be null");
    }

    @Override
    public String toString() {
        return type + " of $" + value;
    }

    public Transaction add(Transaction transaction) {
        if (transaction.type() == type) {
            return new Transaction(value + transaction.value(), type);
        }
        
        throw new RuntimeException("Transaction type is not accepted");
    }
}
```