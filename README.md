# Drive the design of a Recently Used List using properties!

[![Build Status](https://travis-ci.org/Jubobs/Cork-Software-Craftsmanship-workshop-on-property-based-testing.svg?branch=solutions)](https://travis-ci.org/Jubobs/Cork-Software-Craftsmanship-workshop-on-property-based-testing)

This kata is intended as a hands-on introduction to
_property-based testing_ in Java.
Your objective is to implement an abstraction known as a
_[Recently Used List][rul]_, using test-driven development.
However, your test suite will consist mostly of _properties_,
as opposed to example-based tests.
To write those properties,
you will be using [junit-quickcheck][junit-quickcheck],
which is an [JUnit][Junit] extension dedicated to property-based testing.

## Set up

First, make sure the following are installed on your machine:

* [Git][git]
* [Java 8][Java8]
* [Gradle 4.x][Gradle]

Then, run the following commands in a shell:

```shell
git clone https://github.com/Jubobs/Cork-Software-Craftsmanship-workshop-on-property-based-testing pbt-workshop
cd pbt-workshop
gradle
```

(If Gradle winces about the `jacocoTestCoverageVerification` task,
make sure you're using some version 4.x of Gradle).

## What is a Recently Used List?

A Recently Used List is an abstraction that keeps track of items
(e.g. files, webpages) that have recently been used or viewed.
It exhibits some interesting behaviours:

* it's a bit like a set, insofar as it contains no duplicate items;
* it's a bit like a list, insofar as items are ordered and you can index into it;
* it's a bit like a stack, insofar as the most recently used items
  appear at the top of the list.
  
You most likely have come across Recently Used Lists in the wild:
IntelliJ IDEA's _File > Open Recent_ menu is one,
and so is macOS Safari's _Reading List_.

As far as I know, [Kevlin Henney][Henney] coined the phrase "Recently Used List".
Several of his talks (incl. [this one][Henney-talk])
about testing involve a Recently Used List.

## Your mission

This project contains a Java interface named [`RecentlyUsedList`][rul-interface]
and a test class named [`RecentlyUsedList_spec`][rul-spec];
the latter contains property-based tests (left empty) that describe behaviours
that any implementor of [`RecentlyUsedList`][rul-interface] should exhibit.
Your goal is to write, TDD-style, an implementor of [`RecentlyUsedList`][rul-interface]
that satisfies all those properties, which you will also need to write yourself,
since I've left them empty.

To get you started,
I've drafted a class that implements [`RecentlyUsedList`][rul-interface],
named [`ListBasedRecentlyUsedList`][lbrul];
I took the liberty to add a static factory method to the class:

```java
public static ListBasedRecentlyUsedList newInstance(int capacity)
```

Don't let my design decisions limit you, though.
Feel free to:

* use any data structure you see fit as a backing collection,
* use a public constructor rather than a static factory method,
* name your implementor whatever you deem appropriate.

Ideally, however, you should proceed as follows:

1. write one of the properties listed in [`RecentlyUsedList_spec`][rul-spec];
2. implement just enough in your [`RecentlyUsedList`][rul-interface] implementor
   to satisfy that property.

Rinse & repeat until all properties are implemented and satisfied.
The order in which you write properties doesn't matter,
but be aware that some properties are harder to write (and satisfy!) than others.
If you have trouble writing a property,
you might want to write an example-based test first,
then identify which test values (if any) are incidental rather than necessary,
and only then convert the example-based test to a property.
    
Note that [`RecentlyUsedList`][rul-interface] is a generic interface:
it has a type parameter, `T`, which corresponds to the elements' type.
In order to test your [`RecentlyUsedList`][rul-interface] implementor,
you'll need to use some concrete value type in your tests,
with a sensible `equals`/`hashcode` implementation (e.g. `String` or `Integer`).

### Getting started: your first property

Let's write a test that checks that [`ListBasedRecentlyUsedList`][lbrul]
satisfies the following property:

> A new list cannot be instantiated with a nonpositive capacity.

You might express that behaviour using an example-based test:

```java
@Test
public void cannot_be_instantiated_with_a_nonpositive_capacity() {
    thrown.expect(IllegalArgumentException.class);
    newInstance(-1);
}
```

That's a good start, but it's somewhat unsatisfactory.
The main problem with such a test is that using a particular value (`-1`, here)
for the capacity leaves room for ambiguity.
Readers of this test might wonder:

> What should happen if I pass `-42` to `newInstance`?
> Who knows? The test says nothing about it...

Granted, the ambiguity is dispelled by the name of the test method,
but that name isn't much better than a comment, because it's not executable.

We can improve the situation somewhat by introducing a clarifying local variable.
However, the test still describes a particular case of a more general behaviour
that applies, not just for `-1`, but for any nonpositive integer.

Therefore, let's make our test more precise by converting it to a property:

```java
@Property
public void cannot_be_instantiated_with_a_nonpositive_capacity(
        @InRange(maxInt = 0) int capacity) {
    thrown.expect(IllegalArgumentException.class);
    newInstance(capacity);
}
```

Our test now no longer uses any particular nonpositive value.
Instead, it specifies some behaviour that the system under test should
exhibit for _any_ nonpositive integer:

> Passing any integer less than or equal to `0`
to `ListBasedRecentlyUsedList#newInstance`
should throw an `IllegalArgumentException`.

By converting the original example-based test to a property,
we're raised the level of abstraction of our burgeoning test suite!

### Keep going!

You can now go and implement just enough in [`ListBasedRecentlyUsedList`][lbrul]
to make that first property pass.
After that, write another property; make it pass;
write another property; make it pass;
and so on, until you've written all the properties
and your [`RecentlyUsedList`][rul-interface] implementor satisfies them all.

# Solutions

My solutions are available in a branch of this repo called [`solutions`][solutions].
You can follow my approach step by step by inspecting the commits on that branch
[here][solutions-commits].

# Contributing

If

* you find this README unclear, or
* if you've identified missing properties, or
* if you've found a bug in my solutions,

please submit a pull request.

[git]: https://git-scm.com/
[Gradle]: https://gradle.org/
[Henney]: http://www.two-sdg.demon.co.uk/curbralan/papers/FormFollowsFunction.pdf
[Henney-talk]: https://www.youtube.com/watch?v=ZsHMHukIlJY#t=5m15s
[Java8]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[JUnit]: http://junit.org/junit4/
[junit-quickcheck]: https://github.com/pholser/junit-quickcheck
[lbrul]: src/main/java/com/jubobs/corksoftwarecrafstmanship/propertybasedtesting/workshop/ListBasedRecentlyUsedList.java
[rul]: https://github.com/Jubobs/Cork-Software-Craftsmanship-workshop-on-property-based-testing#what-is-a-recently-used-list
[rul-interface]: src/main/java/com/jubobs/corksoftwarecrafstmanship/propertybasedtesting/workshop/RecentlyUsedList.java
[rul-spec]: src/test/java/com/jubobs/corksoftwarecrafstmanship/propertybasedtesting/workshop/RecentlyUsedList_spec.java
[solutions]: https://github.com/Jubobs/Cork-Software-Craftsmanship-workshop-on-property-based-testing/tree/solutions
[solutions-commits]: https://github.com/Jubobs/Cork-Software-Craftsmanship-workshop-on-property-based-testing/commits/solutions
