# ax-tuple-java

A simple, lightweight, and immutable Tuple implementation for Java projects.

> **ax-tuple-java** is an **Open Source Project** built by **Appxiom Team**
> Visit [https://www.appxiom.com](https://www.appxiom.com) to know more about us.
> You will love our product if you are into software engineering!

> MIT License

## Overview

`ax-tuple-java` provides two main classes to manage collections of data without the need for custom DTOs or POJOs for every small data structure:

1. **Tuple**: An ordered collection of elements.
2. **NamedTuple**: A collection of elements associated with unique string keys.

Both implementations are designed to be immutable and provide type-safe access through generic methods.

## Installation

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.appxiom.ax.tuple</groupId>
    <artifactId>ax-tuple</artifactId>
    <version>1.0.5</version>
</dependency>
```

## Usage

### Using `Tuple`

Create a tuple with `Tuple.of()`:

```java
import com.appxiom.ax.tuple.Tuple;

Tuple tuple = Tuple.of("Hello", 42, 3.14);

// Access by index
String s = tuple.get(0);               // Inferred type
Integer i = tuple.get(1, Integer.class); // Explicit type
Object o = tuple.getObject(2);

System.out.println("Size: " + tuple.size()); // Size: 3
```

### Using `NamedTuple`

Create a named tuple from a `Map`:

```java
import com.appxiom.ax.tuple.NamedTuple;
import java.util.Map;

NamedTuple user = NamedTuple.of(Map.of(
    "id", 1,
    "username", "robin",
    "active", true
));

// Access by key
String username = user.get("username");
Boolean isActive = user.get("active", Boolean.class);

System.out.println(user.toString());
```

## Using as HashMap Keys

### Using Tuple as a key

Both `Tuple` and `NamedTuple` override `equals()` and `hashCode()`, making them perfectly suitable for use as keys in a `HashMap` or as elements in a `HashSet`.

Two tuples are considered equal if they contain the same elements in the same order. Two named tuples are equal if they contain the same key-value pairs.

```java
import java.util.HashMap;
import com.appxiom.ax.tuple.Tuple;

HashMap<Tuple, String> cache = new HashMap<>();
Tuple key = Tuple.of("request", 12345);

cache.put(key, "Cached Result");

// Retrievable with a different Tuple instance containing identical data
System.out.println(cache.get(Tuple.of("request", 12345))); // Output: Cached Result
```

### Using NamedTuple as a key

```java
import java.util.HashMap;
import com.appxiom.ax.tuple.NamedTuple;
import java.util.Map;

// Using NamedTuple as a key
HashMap<NamedTuple, String> userRegistry = new HashMap<>();
NamedTuple key1 = NamedTuple.of(Map.of("id", 101, "role", "admin"));

userRegistry.put(key1, "Admin Account");

// Retrieve using a new instance with identical keys and values
NamedTuple key2 = NamedTuple.of(Map.of("id", 101, "role", "admin"));
System.out.println(userRegistry.get(key2)); // Output: Admin Account
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
