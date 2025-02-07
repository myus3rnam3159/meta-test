# Range kata


Implement a class `Range` to present a range of elements having natural order.

To create a `Range` instance, simply give it a `lowerbound` and a `upperbound`. A `Range` can be used to check if it contains a value.

## Requirements

- Develop the `Range` class from level to level. Please note that you will only use the `Range` class to create different ranges, you could create many classes to support your implementation but you should not use them in test cases.
- You must add more tests in `RangeTest.java` as you progress through the levels. There are several failing tests written so that you can get started quickly for Level 1.
- All tests in `RangeTest` MUST pass.
- Each level MUST be a completed by a Git commit.
- Please commit **directly** to the `master` or `main` branch.
- Please avoid committing any IDE's specific files. 

## Development

- Gradle 8.7.
- Java 21.

## Class `Range` *public* API

```java
class Range {
  public static Range of(int lowerbound, int upperbound);

  public boolean contains(int value);

  public int lowerbound();
  public int upperbound();
}
```

## Level 1 -  A Range for Numbers

Class `Range` can be used with `int`.

```java
Range validAgesForWatchingPorn = Range.of(13, 100);


validAgesForWatchingPorn.contains(5); // false
validAgesForWatchingPorn.contains(13); // true
validAgesForWatchingPorn.contains(22); // true
validAgesForWatchingPorn.contains(100); // true
validAgesForWatchingPorn.contains(101); // false
```

The following constraints **MUST** be implemented:

- `Range` must be *immutable*. Once created, there is no way to change its `lowerbound` and `upperbound`.
- `Range` must provide a _static factory method_ namely `of(int, int)` to create a new instance.
- It is not allowed to create a `Range` with `lowerbound > upperbound`.
- The method `contains(x)` must returns `true` only if `lowerbound <= x <=upperbound`.

## Level 2 - More Types of `Range`

Mathmatically, a `Range` can be `open`, `closed`, `openClosed` or `closedOpen`.

```java
// open range excludes both bounds
// 4..5(..6..)7..8..9..10
//      ^^^^^
(5, 7)


// closed range includes both bounds
// 4..[5..6..7]..8..9..10
//    ^^^^^^^^^
[5, 7]

// open closed excludes lowerbound but includes upperbound
// 4..5(..6..7]..8..9..10
//     ^^^^^^^^
(5, 7]

// closed open includes lowerbound but excludes upperbound
// 4..[5..6..)7..8..9..10
//    ^^^^^^^
[5, 7)

```

- Extend `Range` such that it can support all of the above types. (This implies the method `of(int,int`) will be renamed to `closed(int, it)`)

```java
Range open = Range.open(5, 7);
open.contains(5); //false

Range closed = Range.closed(5, 7);
closed.contains(5); // true

Range openClosed = Range.openClosed(5, 7);
openClosed.contains(5); // false
openClosed.contains(7); // true

Range closedOpen = Range.closedOpen(5, 7);
closedOpen.contains(5); // true;
closedOpen.contains(7); // false;
```


## Level 3 - Make it generic with all `Comparable<T>` types

Extends the `Range` such that it can supports any types implementing `Comparable` interface.

The `Comparable` interface is implemented by several Java types (i.e `String`, )


```java
Range<String> text = Range.open("abc", "xyz");

Range<BigDecimal> decimals = Range.open(BigDecimal.valueOf("1.32432"), BigDecimal.valueOf("1.324323423423423423423"));

Range<LocalDate> dates = Range.closed(LocalDate.of(2016, Month.SEPTEMBER, 11), LocalDate.of(2017, Month.JUNE, 30)));

```

## Level 4 - Open-ended `Range`s

We want to have `Range` to support an open-ended style with `Infinity`. For example:

```java
Range<Integer> lessThanFive = Range.lessThan(5); // [Infinity, 5)
lessThanFive.contains(5); // false;
lessThanFive.contains(-9000); // true;

Range<Integer> atLeastFive = Range.atLeast(5); // [5, Infinity]
atLeastFive.contains(5); // true;
atLeastFive.contains(4); // false;

Range<Integer> atMostFive = Range.atMost(5); // [Infinity, 5]
atMostFive.contains(5); // true
atMostFive.contains(-234234); // true;
atMostFive.contains(6); // false;


Range<LocalDate> afterEpoch = Range.greaterThan(LocalDate.of(1900, Month.JANUARY, 1)); // (1900-01-01, Infinity]
afterEpoch.contains(LocalDate.of(2016, Month.JULY, 28)); // true;
afterEpoch.contains(LocalDate.of(1750, Month.JANUARY, 1)); // false;


Range<String> all = Range.all(); // [Infinity, Infinity]
all.contains("anything"); // true;
all.contains(""); // true;
all.contains(null); // true;
```

## Level 5 - Introduce useful `toString()`

Implement `toString()` method for class `Range`. The `toString()` should represent the type and the bounds of the current `Range` instance.

```java
Range<Integer> lessThen100 = Range.lessThen(100);
assert lessThen100.toString() == "[Infinity, 100)"

Range<LocalDate> within2020 = Range.closed(
  LocalDate.of(1, JANUARY, 2020),
  LocalDate.of(31, DECEMBER, 2020)
)

assert within2020 == "[2020-01-01, 2020-12-31]"
```

## Level 6 - Parse a `Range` notation.

It is possible to create a new `Range` using the result from `Range#toString()`.

This level tests your API design skills so we won't provide much information here. Please decide the signature of `parse` yourself.

```java
String rangeString = Range.lessThan(100).toString();
Range<Integer> lessThan100 = Range.parse(rangeString, ...);
assert lessThan100.toString() == "[Infinity, 100)"
assert lessThan100.contains(99);
```

## Level 7 - `Range` as an HTTP API

It should be possible to use `Range`'s most important feature, `contains`, via an HTTP API at `/api/range`.

This level tests your experience in working with a web application. The requirements are quite simple:

- The HTTP API is accessible at `/api/range/*`. Additional sub paths and/or path parameters are free to use.
- The HTTP API should receive: (1) a String representation of a `Range` for a supported type and (2) a value of that type.
- The HTTP API should be able to re-construct a `Range` instance and checks if it (the range) contains the specified value. The response's body should contain such rersult.

Your are free to:

- Use any of Java-based libraries or frameworks which you are familiar with.
- Decide the details of the HTTP API (e.g HTTP method, sub paths, type of request, type of response etc). It is not required at all make it RESTful.

Bonus Points:

- There is a accompanying Integration Test for this feature.
- There is an OpenAPI Specification (v3) for the HTTP API.

## Level 8 - Further Discussions

This level does not need implementation. The questions defined in this level are reserved for the upcoming Technical Interview. You can take time to think of the answers and we will go through them in then interview.

a. In Level 5, `"Infinity"` is chosen to denote that there is no bound. In Level 6, the literal `"Infinity"` is also used to parse open-ended ranges. The question is: if we would like to have a `Range<String>.of("Infinity", "Yes")` in which `"Infinity"` is the actual value of the bound, what do you do?

b. Similar to the above question, how do you deal with bounds of type `String` containing comma(s)? Say `Range.parse("[[,,]]")` in which lower bound is `"["` and upperbound is `",]"`. What would you do if you are free to define the syntax of `Range#toString()`?

c. Method `Range#parse` uses the result of `Range#toString()` to re-create an instance of `Range`. However, in Level 5, `Range#toString()` relies on `toString()` of the type. What would you do if the type of the range does not implement `toString()`?



d. We would like to introduce a new feature: not only a `Range` can tell whether a value belongs in it, but also capable of emitting values between two bounds. The method `Range#iterate()` is added such that the class `Range` can be used like below:

```java
var oneMonth = Range.closed(LocalDate.now(), LocalDate.now().plusMonths(1));
for (LocalDate date : oneMonth.iterate()) {
  System.out.println(date);
}
```

How would you implement this feature? What changes you make to the class `Range`?

## Meta

v2024.07.15

- Add "Level 8" which is a set of questions we would like to discuss in Technical Interview round.

v2024.06.13

- Add "Level 7" to expose `Range` as an HTTP API.

v2024.05.15

- Update requirements to avoid confusions: many candidates misunderstood that they can only write code in `Range` class, we actually allow creating many classes to support the implementation.

v2021.12.02

- Update Requirements to avoid confusions (some candidates ended up create six versions of `Range.java`).
- Refactor levels' names.
- Add an assertion for Level 6 to ensure that the `Range#parse` works correctly with the original type.

v2020.11.20

- Add level 6.
- Rename levels to reflect its true intent.
- Added _visualization_ of types of Ranges.

v2020.06.01
- Initial

