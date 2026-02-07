# ax-tuple-java

A simple, lightweight, and immutable Tuple implementation for Java projects.

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
    <version>0.1.1</version>
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

## License

MIT License

Copyright (c) 2026 Robin Panicker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
