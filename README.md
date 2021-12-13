# Advent of Code Day 6

## Tech

Since I last used Clojure the version number has gone from 1.6 to 1.10.3, it has transducers (which I need to learn more about), Lein can be replaced with Clojure CLI) and I'm using the new version of NREPL that supports unix file sockets. The future is exciting.

## Solution

https://adventofcode.com/2021/day/6

Thought I'd mix it up today and use Clojure.

So this is a straightforward application of a function over a list of things which grows over time. Part one runs in a second or two, whilst part two takes a lot longer*, welcome to exponential growth!

An obversation here is that we don't really need to concern ourselves with the individual matings of fish, we can instead work on them in groups, i.e., as a map of fish aged a certain day and the counts. Here's what that looks like...

Slow way meaning we process our list of fish one by one, from one generation to the next.

```
113488888
0023777778
```

Note that there is nothing important about the order of the fish hence we can group them in a map as follows.

```
fish age, count
1,2
3,1
4,1
8,5
```

We can now age our 2 fish with 1 day left by removing the 2 from fish 1 and adding it to fish age 0

```
0,2
1,0
3,1
4,1
8,5
```

Do the same for each fish age. We can take fish age 0 and put them in age 6, and in doing so add newborns to age 8.

In this manner we can much rapidly live the fish life for 256 days.

* A lot longer meaning pretty much forever if you have enough memory.
