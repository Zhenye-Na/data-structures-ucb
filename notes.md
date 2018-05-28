# CS61B Data Structures - lecture notes

* * *

@ UCB, Spring 2018  
@ Instructor: [Josh Hug](https://www2.eecs.berkeley.edu/Faculty/Homepages/joshhug.html)  
@ Lecture: MWF 3-4 PM, Wheeler 150  
@ [Course Website](https://sp18.datastructur.es/index.html)  
@ [Java Visualizer](https://cscircles.cemc.uwaterloo.ca/java_visualize/)

* * *

<div style="display: flex; justify-content: center;">
  <img src="https://ak8.picdn.net/shutterstock/videos/25644788/thumb/12.jpg"/>
</div>

## 1.1 - Intro to Java, `Hello World`
### Running a Java Program

Given `HelloWorld.java` ->

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
```


```bash
$ javac HelloWorld.java
$ java HelloWorld
Hello World! 
```

### Variables and loops

Given `HelloNumbers.java`

```java
public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        while (x < 10) {
            System.out.print(x + " ");
            x = x + 1;
        }
    }
}
```

```bash
$ javac HelloNumbers.java
$ java HelloNumbers
$ 0 1 2 3 4 5 6 7 8 9 
```

### Defining functions in Java

```java
public class LargerDemo {
    public static int larger(int x, int y) {
        if (x > y) {
            return x;
        }
        return y;
    }

    public static void main(String[] args) {
        System.out.println(larger(8, 10));
    }
}
```


## 1.2 - Defining and Using Classes
### public static void main(String[] args)
* `public`: So far, all of our methods start with this keyword.
* `static`: It is a static method, not associated with any particular instance.
* `void`: It has no return type.
* `main`: This is the name of the method.
* `String[] args`: This is a parameter that is passed to the main method.

```java
public class ArgsDemo {
    public static void main(String[] args) {
        System.out.println(args[0]);
    }
}
```

This program prints out the 0th command line argument, e.g.

```bash
$ java ArgsDemo these are command line arguments
these
```

However, those args need to be sepaerated by `space`, or it will print all of it. In the example above, args will be an array of Strings, where the entries are `{"these", "are", "command", "line", "arguments"}`.

Convert from `String` to `int`: 

```java
String myString = "1234";
int foo = Integer.parseInt(myString);
```


## 2.1 - References, Recursion, and Lists
### The Mystery of the Walrus
Try to predict what happens when we run the code below. Does the change to b affect a? Hint: If you're coming from Python, Java has the same behavior.

```java
Walrus a = new Walrus(1000, 8.3);
Walrus b;
b = a;
b.weight = 5;
System.out.println(a);
System.out.println(b);

// weight: 5, tusk size: 8.30
// weight: 5, tusk size: 8.30
```

```java
int x = 5;
int y;
y = x;
x = 2;
System.out.println("x is: " + x);
System.out.println("y is: " + y);

// x is 2
// y is 5
```

### Bits
8 primitive types in Java: byte, short, **int**, long, float, **double**, boolean, char. **Everything else**, including arrays, is _not a primitive type_ but rather a `reference type`.

All information in your computer is stored in `memory` as a sequence of ones and zeros

* 72 is often stored as 01001000
* 205.75 is often stored as 01000011 01001101 11000000 00000000
* The letter H is often stored as 01001000 (same as 72)
* The true value is often stored as 00000001

```java
char c = 'H';
int x = c;
System.out.println(c);
System.out.println(x);

// H
// 72
```

In this case, both the x and c variables contain the same bits (well, almost...), but the Java interpreter treats them differently when printed.

### Declaring a variable
When you declare a variable of a certain type in Java:

- Your computer sets aside exactly enough bits to hold a thing of that type.
	- Example: Declaring an int sets aside a “box” of **32** bits.
	- Example: Declaring a double sets aside a box of **64** bits.

- Java creates an internal table that maps each variable name to a location.
- Java does NOT write anything into the reserved boxes.
	- For safety, Java will not let access a variable that is uninitialized.
- Instead of writing memory box contents in binary, we’ll write them in human readable symbols.

The exact memory address is below the level of abstraction accessible to us in `Java`. This is unlike languages like `C` where you can ask the language for the exact address of a piece of data.


### Reference Types
#### Object Instantiation
When we instantiate an Object using `new` (e.g. Dog, Walrus, Planet), Java first allocates a box for each instance variable of the class, and fills them with a default value. The constructor then usually (but not always) fills every box with some other value.

#### Reference Variable Declaration
When we declare a variable of any reference type (Walrus, Dog, Planet, array, etc.), `Java` allocates a box of **64 bits**, no matter what type of object.  

The 64 bit box contains **not the data** about the walrus, **but instead the address** of the Walrus in memory.

```java
public static class Walrus {
    public int weight;
    public double tuskSize;

    public Walrus(int w, double ts) {
          weight = w;
          tuskSize = ts;
    }
}


Walrus someWalrus;
someWalrus = new Walrus(1000, 8.3);
```

The first line creates a box of **64 bits**. The second line creates a new Walrus, and the address is returned by the new operator. These bits are then copied into the someWalrus box according to the GRoE.


### Parameter Passing
When you pass parameters to a function, you are also simply copying the bits. In other words, the GRoE also applies to parameter passing. Copying the bits is usually called "pass by value". In `Java`, we always pass by value.

```java
public class PassByValueFigure {
    public static void main(String[] args) {
           Walrus walrus = new Walrus(3500, 10.5);
           int x = 9;

           doStuff(walrus, x);
           System.out.println(walrus);
           System.out.println(x);
    }

    public static void doStuff(Walrus W, int x) {
           W.weight = W.weight - 100;
           x = x - 5;
    }
}
```


#### Exercise

```java
public class PassByValueFigure {
    public static void main(String[] args) {
           Walrus walrus = new Walrus(3500, 10.5);
           int x = 9;

           doStuff(walrus, x);
           System.out.println(walrus);
           System.out.println(x);
    }

    public static void doStuff(Walrus W, int x) {
           W.weight = W.weight - 100;
           x = x - 5;
    }
}
```
Does the call to `doStuff` have an effect on walrus and/or x? Hint: We only need to know the GRoE to solve this problem.

### Instantiation of Arrays

```java
int[] a = new int[]{0, 1, 2, 95, 4};
```

1. Creates a **64 bit** box for storing an **int array address**. (declaration)
1. Creates a new Object, in this case an int array. (instantiation)
1. Puts the **address** of this new `Object` into the 64 bit box named a. (assignment)

Objects can be lost if you lose the bits corresponding to the address. For example if the only copy of the address of a particular Walrus is stored in `x`, then `x = null` will cause you to **permanently** lose this Walrus. This isn't necessarily a bad thing, since you'll often decide you're done with an object, and thus it's safe to simply throw away the reference. We'll see this when we build lists later in this chapter.


### IntLists

```java
public class IntList {
    public int first;
    public IntList rest;        

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }
}

// IntList L = new IntList(5, null);
// L.rest = new IntList(10, null);
// L.rest.rest = new IntList(15, null);

IntList L = new IntList(15, null);
L = new IntList(10, L);
L = new IntList(5, L);
```

#### size and iterativeSize

```java
/** Return the size of the list using... recursion! */
public int size() {
    if (rest == null) {
        return 1;
    }
    return 1 + this.rest.size();
}

System.out.println(size())
```

The key thing to remember about recursive code is that you need a base case. In this situation, the most reasonable base case is that rest is null, which results in a size 1 list.

`iterativeSize` method is as shown below. I recommend that when you write iterative data structure code that you use the name `p` to remind yourself that the variable is holding a **pointer**. 

Pointer is needed because you cannot reassign `this` in Java. 

```java
/** Return the size of the list using no recursion! */
public int iterativeSize() {
    IntList p = this;
    int totalSize = 0;
    while (p != null) {
        totalSize += 1;
        p = p.rest;
    }
    return totalSize;
}

System.out.println(L.iterativeSize())
```

### get

```java
/** Returns the ith item of this IntList. */
public int get(int i) {
    if (i == 0) {
        return first;
    } else {
        return this.rest.get(i - 1);
    }
}
```

**Note:** the method here takes linear time! That is, if you have a list that is 1,000,000 items long, then getting the last item is going to take much longer than it would if we had a small list. We'll see an alternate way to implement a list that will avoid this problem in a future lecture.


## 2.2 - SLLists, Nested Classes, Sentinel Nodes

```java
public class IntNode {
    public int item;
    public IntNode next;

    public IntNode(int i, IntNode n) {
        item = i;
        next = n;
    }
}


public class SLList {
    public IntNode first;

    public SLList(int x) {
    	first = new IntNode(x, null);
    }
}

```

Difference between IntList and SLList (Singly-Linked List) when Instatiating:

```java
IntList L1 = new IntList(5, null);
SLList L2  = new SLList(5);
```

### addFirst and getFirst

```java
public class IntNode {
    public int item;
    public IntNode next;

    public IntNode(int i, IntNode n) {
        item = i;
        next = n;
    }
}

public class SLList {
    public IntNode first;

    public SLList(int x) {
        first = new IntNode(x, null);
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        first = new IntNode(x, first);
    }
    
    /** Retrieves the front item from the list. */
    public int getFirst() {
        return first.item;
    }
    
    public static void main (String[] args) {
        SLList L = new SLList(15);
        L.addFirst(10);
        L.addFirst(5);
        int x = L.getFirst();
        System.out.println(x);
    }
}
```

**Note:** What I am confused for a while is why we can call `first.item` in `getFirst`. THe reason why is that:  
In SLList init function, `first` is a `IntNode` and `IntNode` has two 'variable', one is `int item`, another is `IntNode next`. So as what I added following, "An SLList (Singly-Linked List) is a _sequence of Nodes_."


#### Singly-Linked List
An SLList (Singly-Linked List) is a _sequence of Nodes_. Each node `u` stores a data value `u.x` and a reference `u.next` to the next node in the sequence. For the last node `w` in the sequence, `w.next = null`.

A sequence of `Stack` and `Queue` operations on an `SLList` is illustrated in this Figure.  

![](http://opendatastructures.org/ods-java/img1195.png)

An SLList can efficiently implement the Stack operations `push()` and `pop()` by adding and removing elements at the head of the sequence. The `push()` operation simply creates a new node `u` with data value `x`, sets `u.next` to the old head of the list and makes `u` the new head of the list. Finally, it increments `n` since the size of the SLList has increased by one:

```
T push(T x) {
    Node u = new Node();
    u.x = x;
    u.next = head;
    head = u;
    if (n == 0)
        tail = u;
    n++;
    return x;
}

```

The `pop()` operation, after checking that the SLList is not empty, removes the head by setting `head=head.next` and decrementing `n`. A special case occurs when the last element is being removed, in which case `tail` is set to `null`:

```
T pop() {
    if (n == 0)
        return null;
    T x = head.x;
    head = head.next;
    if (--n == 0) 
        tail = null;
    return x;
}
```



### Public vs. Private

```java
SLList L = new SLList(15);
L.addFirst(10);
L.first.next.next = L.first.next;
```

![](https://joshhug.gitbooks.io/hug61b/content/chap2/fig22/bad_SLList.png)

This results in a malformed list with an infinite loop. To deal with this problem, we can modify the SLList class so that the first variable is declared with the private keyword.

```java
public class SLList {
    private IntNode first;
...
```

Private variables and methods can only be accessed by code inside the same `.java` file, e.g. in this case `SLList.java`. That means that a class like `SLLTroubleMaker` below will fail to compile, yielding a **first has private access in SLList** error.

```java
public class SLLTroubleMaker {
    public static void main(String[] args) {
        SLList L = new SLList(15);
        L.addFirst(10);
        L.first.next.next = L.first.next;
    }
}
```

### Nested Classes
Java provides us with the ability to embed a class declaration inside of another for just this situation. The syntax is straightforward and intuitive:

```java
public class SLList {
    public class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode first; 

    public SLList(int x) {
        first = new IntNode(x, null);
    } 
...
```

- If the nested class has no need to use any of the instance methods or variables of outer class, you may declare the nested class `static`.


### addLast() and size()

Iterative `addLast` method, create a pointer variable p and have it iterate through the list to the end.

```java
/** Adds an item to the end of the list. */
public void addLast(int x) {
    IntNode p = first;

    /* Advance p to the end of the list. */
    while (p.next != null) {
        p = p.next;
    }
    /* Make p.next point to the item we wanna append. */
    p.next = new IntNode(x, null);
}
```

`size` function: create a private helper method that interacts with the underlying naked recursive data structure.

```java
/** Returns the size of the list starting at IntNode p. */
private static int size(IntNode p) {
    if (p.next == null) {
        return 1;
    }

    return 1 + size(p.next);
}

public int size() {
    return size(first);
}
```
Here, we have two methods, both named `size`. This is _allowed_ in Java, since they have _different parameters_. We say that two methods with the same name but different signatures are **overloaded**.

### Caching
`Caching`: putting aside data to speed up _retrieval_.

```java
public class SLList {
    ... /* IntNode declaration omitted. */
    private IntNode first;
    private int size;

    public SLList(int x) {
        first = new IntNode(x, null);
        size = 1;
    }

    public void addFirst(int x) {
        first = new IntNode(x, first);
        size += 1;
    }

    public int size() {
        return size;
    }
    ...
}
```

### Empty SLList

```java
public SLList() {
    first = null;
    size = 0;
}
```
Unfortunately, this causes our `addLast` method to crash if we insert into an empty list. Since `first` is `null`, the attempt to access `p.next` in `while (p.next != null)` below causes a **null pointer exception**.

```java
public void addLast(int x) {
    size += 1;
    IntNode p = first;
    while (p.next != null) {
        p = p.next;
    }

    p.next = new IntNode(x, null);
}
```

One solution is to add a special case, where `first == null` we do something special - `first = new IntNode(x, null);`.


```java
public void addLast(int x) {
    size += 1;

    if (first == null) {
    first = new IntNode(x, null);
        return;
    }

    IntNode p = first;
    while (p.next != null) {
        p = p.next;
    }

    p.next = new IntNode(x, null);
}
```

### Sentinel Nodes
Add a "starting" node which contains whatever you wanna put in. But it is just the first node in the SLList. It does not affect your SLList. Only make sure that `while(p.next != null)` will not give you error.

![](https://joshhug.gitbooks.io/hug61b/content/chap2/fig22/empty_sentinelized_SLList.png)  
![](https://joshhug.gitbooks.io/hug61b/content/chap2/fig22/three_item_sentenlized_SLList.png)

```java
public void addLast(int x) {
    size += 1;
    IntNode p = sentinel;
    while (p.next != null) {
        p = p.next;
    }

    p.next = new IntNode(x, null);
}
```

### Invariants
An invariant is a condition that is _guaranteed to be true during code execution_ (assuming there are no bugs in your code).

An `SLList` with a `sentinel` node has at least the following invariants:

* The sentinel reference always points to a sentinel node.
* The `first` node (if it exists), is always at `sentinel.next`.
* The `size` variable is always the total number of items that have been added.

Invariants make it easier to reason about code:

* Can assume they are true to simplify code (e.g. addLast doesn’t need to worry about nulls).
* Must ensure that methods preserve invariants.


## 2.3 - DLLists, Arrays

### DLLists

A `DLList` (doubly-linked list) is very similar to an `SLList` except that each node `u` in a `DLList` has references to both the node u.next that follows it and the node `u.prev` that precedes it.

When implementing an `SLList`, we saw that there were always several special cases to worry about. For example, removing the last element from an `SLList` or adding an element to an empty `SLList` requires care to ensure that `head` and `tail` are correctly updated. In a `DLList`, the number of these special cases increases considerably. Perhaps the cleanest way to take care of all these special cases in a `DLList` is to introduce a `dummy` node. This is a node that does not contain any data, but acts as a placeholder so that there are no special nodes; every node has both a `next` and a `prev`, with `dummy` acting as the node that follows the last node in the list and that precedes the first node in the list. In this way, the nodes of the list are (doubly-)linked into a cycle, as illustrated in this figure.

<div style="display: flex; justify-content: center;">
  <img src="http://opendatastructures.org/ods-java/img1251.png"/>
</div>

#### addLast

```java
public void addLast(int x) {
    size += 1;
    IntNode p = sentinel;
    while (p.next != null) {
        p = p.next;
    }

    p.next = new IntNode(x, null);
}
```

The issue with this method is that it is slow. For a long list, the addLast method has to walk through the entire list. So like `size()` function using `Caching`, we can initialize a variable named `last` which indicates the last element in the SLList.

```java
public class SLList {
    private IntNode sentinel;
    private IntNode last;
    private int size;    

    public void addLast(int x) {
        last.next = new IntNode(x, null);
        last = last.next;
        size += 1;
    }
    ...
}
```

Note: This method is good for `addLast` and `getLast`. However, for `removeLast`, once we do remove the last element. How to move to second last element?


#### Sentinel Upgrade

* Add a second sentinel node to the back of the list.

![](https://joshhug.gitbooks.io/hug61b/content/chap2/fig23/dllist_double_sentinel_size_2.png)

We have two sentinel nodes we have access to in this case. The items are inserted in between the front sentinel node and back sentinel node.

* An alternate approach is to implement the list so that it is circular, with the front and back pointers sharing the same sentinel node.

![](https://joshhug.gitbooks.io/hug61b/content/chap2/fig23/dllist_circular_sentinel_size_2.png)


#### Generic DLLists
The basic idea is that right after the name of the class in your class declaration, you use an arbitrary _*placeholder*_ inside angle brackets: `<>`. Then anywhere you want to use the arbitrary type, you use that placeholder instead.

Here is an example of the `DLList` we created before changing to Generic DLList. `<Beepblorp>` is just a _*placeholder*_ for data type when you created (`new`) a `DLList` object.

```java
public class DLList<placeholder> {
    private IntNode sentinel;
    private int size;

    public class IntNode {
        public IntNode prev;
        public placeholder item;
        public IntNode next;
        ...
    }
    ...
}
```

To do so, we put the *desired type inside of angle brackets* during declaration, and also use *empty angle brackets* during instantiation. For example:

```java
DLList<String> d1 = new DLList<> ("hello");
DLList<String> d2 = new DLList<String> ("hello");
d2.addLast("world");
```

Since generics only work with `reference types` (recall what `reference types` are), we cannot put primitives like `int` or `double` inside of angle brackets, e.g. `<int>`. Instead, we use the reference version of the primitive type, which in the case of int case is `Integer`, e.g.

```java
DLList<Integer> d1 = new DLList<>(5);
d1.insertFront(10);
```

* In the `.java` file implementing a data structure, specify your generic type name only once at the very top of the file after the class name.
* In other `.java` files, which use your data structure, specify the specific desired type during declaration, and use the empty diamond operator during instantiation.
* If you need to instantiate a generic over a primitive type, use **reference type** $\Rightarrow$ `Integer`, `Double`, `Character`, `Boolean`, `Long`, `Short`, `Byte`, or `Float` instead of their **primitive** equivalents.


### Arrays

There are three valid notations for array creation. 

```java
x = new int[3];  // ecah container got its own default value
y = new int[]{1, 2, 3, 4, 5};
int[] z = {9, 10, 11, 12, 13};
```

`System.arraycopy` takes five parameters:

* The array to use as a source
* Where to start in the source array
* The array to use as a destination
* Where to start in the destination array
* How many items to copy

For Python veterans, `System.arraycopy(b, 0,x, 3, 2)` is the equivalent of `x[3:5] = b[0:2]` in Python.


#### 2D Arrays

```java
int[][] x = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

// share variables, so changing z will change x
int[][] z = new int[3][];
z[0] = x[0];
z[1] = x[1];
z[2] = x[2];
z[0][0] = -z[0][0];

// arraycopy does not share variables, changing z does not affect x
int[][] w = new int[3][3];
System.arraycopy(x[0], 0, w[0], 0, 3);
System.arraycopy(x[1], 0, w[1], 0, 3);
System.arraycopy(x[2], 0, w[2], 0, 3);
w[0][0] = -w[0][0];
```

#### Arrays vs. Classes

The key differences between memory boxes in arrays and classes:

* Array boxes are numbered and accessed using `[]` notation, and class boxes are named and accessed using `dot` notation.
* Array boxes must all be the same type. Class boxes can be different types.


##### Overview

###### `SLList` Drawbacks
`addLast()` is slow! We can't add to the middle of our list. In addition, if our list is really large, we have to start at the front, and loop all the way to the back of our list before adding our element.

###### A Naive Solution
Recall that we cached the size of our list as an instance variable of `SLList`. What if we cached the last element in our list as well? All of a sudden, `addLast()` is fast again; we access the last element immediately, then add our element in. But `removeLast()` is still slow. In `removeLast()`, we have to know what our second-to-last element is, so we can point our cached last variable to it. We could then cache a second-to-last variable, but now if I ever want to remove the second-to-last element, I need to know where our third-to-last element is. How to solve _this_ problem?

###### `DLList` 
The solution is to give each `IntNode` a `prev` pointer, pointing to the previous item. This creates a *doubly-linked list*, or `DLList`. With this modification, adding and removing from the front and back of our list becomes fast (*although adding/removing from the middle remains slow*).

###### Incorporating the Sentinel
Recall that we added a `sentinel node` to our `SLList`. For DLList, we can either have **two sentinels** (one for the front, and one for the back), or we can use a **circular sentinel**. A `DLList` using a circular sentinel has one sentinel. The sentinel points to the first element of the list with `next` and the last element of the list with `prev`. In addition, the last element of the list's `next` points to the `sentinel` and the first element of the list's `prev` points to the `sentinel`. _For an empty list, the sentinel points to itself in both directions._

###### Generic DLList 
How can we modify our DLList so that it can be a list of whatever objects we choose? Recall that our class definition looks like this:

```java
public class DLList { ... }
```

We will change this to

```java
public class DLList<T> { ... }
```

where `T` is a placeholder object type. Notice the angle bracket syntax. Also note that we don't have to use `T`; any variable name is fine. In our `DLList`, our item is now of type `T`, and our methods now take `T` instances as parameters. We can also rename our `IntNode` class to `TNode` for accuracy.

###### Using Generic DLList

Recall that to create a `DLList`, we typed:

```java
DLList list = new DLList(10);
```

If we now want to create a `DLList` holding `String` objects, then we must say:

```java
DLList<String> list = new DLList<>("bone");
```

On list creation, the compiler replaces all instances of `T` with `String`! We will cover generic typing in more detail in later lectures.

###### Arrays
Recall that variables are just boxes of bits. For example, `int x;` gives us a memory box of 32 bits. Arrays are a special object which consists of a numbered sequence of memory boxes! To get the ith item of array `A`, use `A[i]`. The length of an array cannot change, and *_all the elements of the array must be of the same type_* (this is different from a Python list). The boxes are zero-indexed, meaning that for a list with `N` elements, the first element is at `A[0]` and the last element is at `A[N - 1]`. **Unlike regular classes, arrays do not have methods!** Arrays do have a `length` *variable* though.

###### Instantiating Arrays
There are three valid notations for creating arrays.

* The first way specifies the size of the array, and fills the array with default values:

    ```java
    int[] y = new int[3];
    ```
    
* The second fills up the array with specific values:

    ```java
    int[] x = new int[]{1, 2, 3, 4, 5};
    ```

* The third is similar to the second way:

    ```java
    int[] w = {1, 2, 3, 4, 5};
    ```

We can set a value in an array by using array indexing. For example, we can say `A[3] = 4;`. This will access the fourth element of array A and sets the value at that box to 4.

###### Arraycopy
In order to make a copy of an array, we can use `System.arraycopy`.

###### 2D Arrays
We can declare multidimensional arrays. For 2D integer arrays, we use the syntax:

```java
int[][] array = new int[4][];
```

This creates an array that holds integer arrays. Note that we have to manually create the inner arrays like follows:

```java
array[0] = new int[]{0, 1, 2, 3};
```

Java can also create multidemensional arrays with the inner arrays created automatically. To do this, use the syntax:

```java
int[][] array = new int[4][4];
```

We can also use the notation:

```java
int[][] array = new int[][]{{1}, {1, 2}, {1, 2, 3}}
```

to get arrays with specific values.

###### Arrays vs. Classes

* Both are used to organize a bunch of memory.
* Both have a fixed number of "boxes".
* Arrays are accessed via square bracket notation. Classes are accessed via dot notation.
* Elements in the array must be all be the **same** type. Elements in a class may be of **different** types.
* Array indices are computed at runtime. We cannot compute class member variable names.



## 3.1 ALists, Resizing, vs. SLists

### Linked List Performance Puzzle Solution
It turns out that no matter how clever you are, the get method will usually be slower than getBack if we're using the doubly linked list structure described in [section 2.3](#23---dllists-arrays).

This is because, since we only have references to the first and last items of the list, we'll always need to scan the whole list from the front or back to get to the item that we're trying to retrieve.

### Naive Resizing Arrays

When the `Array` is full and the function `addLast()` can be implemented by `resizing an array`, that is

* duplicate the current array but make the size larger;
* insert to the new array

```java
int[] a = new int[size + 1];            // Initialize a new larger array
System.arraycopy(items, 0, a, 0, size); // Duplicate the array
a[size] = 11;                           // Insert into the new array
items = a;
size = size + 1;
```


### Generic ALists

There is one significant syntactical difference: `Java` **does not** allow us to create an array of generic objects due to an obscure issue with the way generics are implemented. That is, we cannot do something like:

```java
Glorp[] items = new Glorp[8];
```

Instead, we have to use the awkward syntax shown below:

```java
Glorp[] items = (Glorp []) new Object[8];
```

This will yield a compilation warning, but it's just something we'll have to live with. We'll discuss this in more details in a later chapter.

The other change we make is that we null out any items that we "delete". Whereas before, we had no reason to zero out elements that were deleted, with generic objects, we do want to null out references to the objects that we're storing. This is to avoid "loitering". Recall that `Java` only destroys objects when the last reference has been lost. If we fail to null out the reference, then Java will not garbage collect the objects that have been added to the list.

This is a subtle performance bug that you're unlikely to observe unless you're looking for it, but in certain cases could result in a significant wastage of memory.


## 3.2 Testing and Selection Sort

**Important note**:

When we test for equality of two objects, we cannot simply use the `==` operator. The `==` operator compares the literal bits in the memory boxes, e.g. `input == expected` would test **whether or not the addresses of input and expected are the same, not whether the values in the arrays are the same**. Instead, we used a `loop` in testSort, and print out the first mismatch. You could also use the built-in method `java.util.Arrays.equals` instead of a loop.

### JUnit Testing

```java
public static void testSort() {
    String[] input = {"i", "have", "an", "egg"};
    String[] expected = {"an", "egg", "have", "i"};
    Sort.sort(input);
    org.junit.Assert.assertArrayEquals(expected, input);
}

^
Exception in thread "main" arrays first differed at element [0]; expected:<[an]> but was:<[i]>
    at org.junit.internal.ComparisonCriteria.arrayEquals(ComparisonCriteria.java:55)
    at org.junit.Assert.internalArrayEquals(Assert.java:532)
    ...

```

### Selection Sort

**Selection Sort** consists of three steps:

* Find the smallest item.
* Move it to the front.
* Selection sort the remaining `N-1` items (without touching the front item).

![](http://interactivepython.org/runestone/static/pythonds/_images/selectionsortnew.png)


#### findSmallest
First, we'll create a dummy `findSmallest` method that simply returns some arbitrary value:

```java
public class Sort {
    /** Sorts strings destructively. */
    public static void sort(String[] x) { 
           // find the smallest item
           // move it to the front
           // selection sort the rest (using recursion?)
    }

    /** Returns the smallest string in x. */
    public static String findSmallest(String[] x) {
        return x[2];
    }
}
```

Obviously this is not a correct implementation, but we've chosen to defer actually thinking about how `findSmallest` works until after we've written a test. Using the `org.junit` library, adding such a test to our `TestSort` class is very easy, as shown below:

```java
public class TestSort {
    ...
    public static void testFindSmallest() {
        String[] input = {"i", "have", "an", "egg"};
        String expected = "an";

        String actual = Sort.findSmallest(input);
        org.junit.Assert.assertEquals(expected, actual);        
    }

    public static void main(String[] args) {
        testFindSmallest(); // note: we changed this from testSort!
    }
}
```

**Notice:** Java does not allow comparisons between `Strings` using the `<` operator.

`str1.compareTo(str2)` method will return a *negative number* if `str1 < str2`, `0` if they are `equal`, and a *positive number* if `str1 > str2`.

```java
/** Returns the smallest string in x. 
  * @source Got help with string compares from https://goo.gl/a7yBU5. */
public static String findSmallest(String[] x) {
    String smallest = x[0];
    for (int i = 0; i < x.length; i += 1) {
        int cmp = x[i].compareTo(smallest);
        if (cmp < 0) {
            smallest = x[i];
        }
    }
    return smallest;
}
```


#### Swap

```java
public static void swap(String[] x, int a, int b) {
    String temp = x[a];
    x[a] = x[b];
    x[b] = temp;
}
```

```java
public class TestSort {
    ...    

    /** Test the Sort.swap method. */
    public static void testSwap() {
        String[] input = {"i", "have", "an", "egg"};
        int a = 0;
        int b = 2;
        String[] expected = {"an", "have", "i", "egg"};

        Sort.swap(input, a, b);
        org.junit.Assert.assertArrayEquals(expected, input);
    }

    public static void main(String[] args) {
        testSwap();
    }
}
```

#### Recursive Helper Methods

To begin this section, consider how you might make the recursive call needed to complete sort:

```java
/** Sorts strings destructively. */
public static void sort(String[] x) { 
   int smallestIndex = findSmallest(x);
   swap(x, 0, smallestIndex);
   // recursive call??
}
```

For those of you who are used to a language like `Python`, it might be tempting to try and use something like slice notation, e.g.

```java
/** Sorts strings destructively. */
public static void sort(String[] x) { 
   int smallestIndex = findSmallest(x);
   swap(x, 0, smallestIndex);
   sort(x[1:])
}
```

However, there is no such thing in `Java` as a reference to a sub-array, i.e. we can't just pass the address of the next item in the array.

This problem of needing to consider only a subset of a larger array is very common. A typical solution is to create a private helper method that has an additional parameter (or parameters) that delineate which part of the array to consider. For example, we might write a private helper method also called sort that consider only the items starting with item start.

```java
/** Sorts strings destructively starting from item start. */
private static void sort(String[] x, int start) { 
    // TODO
}
```

Unlike our `public sort` method, it's relatively straightforward to use recursion now that we have the additional parameter start, as shown below. We'll test this method in the next section.

```java
/** Sorts strings destructively starting from item start. */
private static void sort(String[] x, int start) { 
   int smallestIndex = findSmallest(x);
   swap(x, start, smallestIndex);
   sort(x, start + 1);
}
```

Now that we have a helper method, we need to set up the correct original call. If we set the start to 0, we effectively sort the entire array.

```java
/** Sorts strings destructively. */
public static void sort(String[] x) { 
   sort(x, 0);
}
```

This approach is quite common when trying to use recursion on a data structure that is not inherently recursive, e.g. arrays.


### JUnit Reflection

First, let's reflect on the new syntax we've seen today, namely `org.junit.Assert.assertEquals(expected, actual)`. This method (with a very long name) tests that expected and actual are equal, and if they are not, terminates the program with a verbose error message.

The first enhancement is to use what is known as a "test annotation". To do this, we:

* Precede each method with `@org.junit.Test` (no semi-colon).
* Change each test method to be **non-static**.
* Remove our `main` method from the `TestSort class`.

The second enhancement will let us use shorter names for some of the very lengthy method names, as well as the annotation name. Specifically, we'll use what is known as an `"import statement"`.
We first add the import statement `import org.junit.Test`; to the top of our file. After doing this, we can replace all instances of `@org.junit.Test` with simply `@Test`.

We then add our second import statement `import static org.junit.Assert.*`. After doing this, anywhere we can omit anywhere we had `org.junit.Assert..` For example, we can replace `org.junit.Assert.assertEquals(expected2, actual2);` with simply `assertEquals(expected2, actual2);`


## 3.3 Inheritance, Implements

### Hypernyms, Hyponyms, and Interface Inheritance

In Java, in order to express this hierarchy, we need to do two things:

- Step 1: Define a type for the general list hypernym -- we will choose the name `List61B`.
- Step 2: Specify that `SLList` and `AList` are hyponyms of that type.

The new List61B is what Java calls an **interface**.

```java
public interface List61B<Item> {
    public void addFirst(Item x);
    public void add Last(Item y);
    public Item getFirst();
    public Item getLast();
    public Item removeLast();
    public Item get(int i);
    public void insert(Item x, int position);
    public int size();
}
```

*`interface` is what you should do, not how you do it.* So how we do it? we need `implements`.

We will add to

```java
public class AList<Item> {...}
```

a relationship-defining word: `implements`.

```java
public class AList<Item> implements List61B<Item>{...}
```

If you are still confused about `interface`, check out this wonderful video explanation of interface in Java by [Navin Reddy](https://www.youtube.com/watch?v=Yaa3QroWe7Q).

### Overriding

When implementing the required functions in the subclass, it's useful (and actually required in 61B) to include the `@Override` tag right on top of the method signature. Here, we have done that for just one method.

```java
@Override
public void addFirst(Item x) {
    insert(x, 0);
}
```

- Even if you don’t write `@Override`, subclass still overrides the method.
- `@Override` is just an **optional** reminder that you’re overriding.




### Implementation Inheritance

If we define this method in `List61B`, make sure you include the **`default`** keyword in the method signature.

```java
default public void print() {
    for (int i = 0; i < size(); i += 1) {
        System.out.print(get(i) + " ");
    }
    System.out.println();
}
```

Java is able to do this due to something called "dynamic method selection".

We know that variables in java have a type.

```java
List61B<String> lst = new SLList<String>();
```

In the above declaration and instantiation, lst is of type "List61B". This is called the `"static type"`.

However, the objects themselves have types as well. the object that lst points to is of type `SLList`. Although this object is intrinsically an `SLList` (since it was declared as such), it is also a `List61B`, because of the "is-a" relationship we explored earlier. But, because the object itself was instantiated using the SLList constructor, We call this its `"dynamic type"`.

Aside: the name "dynamic type" is actually quite semantic in its origin! Should lst be reassigned to point to an object of another type, say a `AList` object, lst’s dynamic type would now be `AList` and not `SLList`! It’s dynamic because it changes based on the type of the object it's currently referring to.

When Java runs a method that is overriden, it searches for the appropriate method signature in it's dynamic type and runs it.


Say there are two methods in the same class

```java
public static void peek(List61B<String> list) {
    System.out.println(list.getLast());
}
public static void peek(SLList<String> list) {
    System.out.println(list.getFirst());
}
```

and you run this code

```java
SLList<String> SP = new SLList<String>();
List61B<String> LP = SP;
SP.addLast("elk");
SP.addLast("are");
SP.addLast("cool");
peek(SP);
peek(LP);
```

The first call to `peek()` will use the *second peek* method that takes in an `SLList`. The second call to `peek()` will use the **first peek method** which takes in a `List61B`.

This is because the only distinction between two **overloaded methods** is the types of the parameters. When Java checks to see which method to call, it checks the `static type` and calls the method with the parameter of the same type.


### Interface Inheritance vs Implementation Inheritance

How do we differentiate between "interface inheritance" and "implementation inheritance"? Well, you can use this simple distinction:

- Interface inheritance (what): Simply **tells** what the subclasses should be able to do.
    - EX) all lists should be able to print themselves, how they do it is up to them.
- Implementation inheritance (how): Tells the subclasses **how** they should behave.
    - EX) Lists should print themselves exactly this way: by getting each element in order and then printing them.

> When you are creating these hierarchies, remember that the relationship between a subclass and a superclass should be an "is-a" relationship. AKA Cat should only implement Animal Cat is an Animal. You should not be defining them using a "has-a" relationship. Cat has-a Claw, but Cat definitely should not be implementing Claw.


## 4.1 Extends, Casting, Higher Order Functions

### Extends

Remember that inheritance allows subclasses to reuse code from an already defined class. So let's define our `RotatingSLList` class to inherit from `SLList`.

We can set up this inheritance relationship in the class header, using the extends keyword like so:

```java
public class RotatingSLList<Item> extends SLList<Item>
```

In the same way that AList shares an "is-a" relationship with List61B, RotatingSLList shares an "is-a" relationship SLList. The extends keyword lets us keep the original functionality of SLList, while enabling us to make modifications and add additional functionality.

![](https://joshhug.gitbooks.io/hug61b/content/assets/list_subclasses.png)

Here's what we came up with.

```java
public void rotateRight() {
    Item x = removeLast();
    addFirst(x);
}
```

By using the extends keyword, subclasses inherit all members of the parent class. "Members" includes:

- All instance and static variables
- All methods
- All nested classes

**Note** that constructors are not inherited, and private members cannot be directly accessed by subclasses.

### VengefulSLList

We create a new class, `VengefulSLList`, that remembers all items that have been banished by removeLast.

Like before, we specify in VengefulSLList's class header that it should inherit from SLList.

```java
public class VengefulSLList<Item> extends SLList<Item>
```

Now, let's give `VengefulSLList` a method to print out all of the items that have been removed by a call to the removeLast method, `printLostItems()`. We can do this by adding an instance variable that can keep track of all the deleted items. If we use an `SLList` to keep track of our items, then we can simply make a call to the print() method to print out all the items.
So far this is what we have:

```java
public class VengefulSLList<Item> extends SLList<Item> {
    SLList<Item> deletedItems;

    public void printLostItems() {
        deletedItems.print();
    }
}
```

VengefulSLList's `removeLast` should do exactly the same thing that SLList's does, except with one additional operation - adding the removed item to the deletedItems list. 

In an effort to reuse code, we can **override** the removeLast method to modify it to fit our needs, and call the removeLast method defined in the parent class, SLList, using the `super` keyword.

> We cannot direcctly copy the `removeLast` function to our VengefulSLList because variable are declared as `private` in `SLList` class. Even though sub-class has no access to those varaibles. So we need the `super` keyword to use the removeLast method in the super-class `SLList`.


```java
public class VengefulSLList<Item> extends SLList<Item> {
    SLList<Item> deletedItems;

    public VengefulSLList() {
        deletedItems = new SLList<Item>();
    }

    @Override
    public Item removeLast() {
        Item x = super.removeLast();
        deletedItems.addLast(x);
        return x;
    }

    /** Prints deleted items. */
    public void printLostItems() {
        deletedItems.print();
    }
}
```

### Constructors Are Not Inherited

Subclasses inherit all members of the parent class, which includes instance and static variables, methods, and nested classes, but does not include constructors.

To gain some intuition on why that it is, recall that the extends keywords defines an "is-a" relationship between a subclass and a parent class. If a VengefulSLList "is-an" SLList, then it follows that every VengefulSLList must be set up like an SLList.

Here's a more in-depth explanation. Let's say we have two classes:

```java
public class Human {...}
```
```java
public class TA extends Human {...}
```

It is logical for TA to extend Human, **because all TA's are Human**. Thus, we want TA's to inherit the attributes and behaviors of Humans.

We can either explicitly make a call to the superclass's constructor, using the `super` keyword:

```java
public VengefulSLList() {
    super();
    deletedItems = new SLList<Item>();
}
```

Or, if we choose not to, Java will automatically make a call to the superclass's no-argument constructor for us.

In this case, adding `super()` has no difference from the constructor we wrote before. It just makes explicit what was done implicitly by Java before. However, if we were to *define another constructor in VengefulSLList, Java's implicit call may not be what we intend to call*.

Suppose we had a one-argument constructor that took in an item. If we had relied on an implicit call to the superclass's no-argument constructor, `super()`, the item passed in as an argument wouldn't be placed anywhere!

So, we must _*make an explicit call*_ to the correct constructor by passing in the item as a parameter to super.

```java
public VengefulSLList(Item x) {
    super(x);
    deletedItems = new SLList<Item>();
}
```

> If we do not make an explicit call of `super(x)`, Java will automatically call the default constructor (the one without arguments)


### The Object Class

Every class in Java is a descendant of the `Object` class, or `extends` the Object class. Even classes that do not have an explicit `extends` in their class still implicitly extend the `Object` class.

For example,

- VengefulSLList extends SLList explicitly in its class declaration
- SLList extends Object implicitly

This means that since SLList inherits all members of Object, VengefulSLList inherits all members of SLList and Object, transitively.


### Is-a vs. Has-a

Important Note: The extends keyword defines "is-a", or hypernymic relationships. A common mistake is to instead use it for "has-a", or meronymic relationships.

**When extending a class, a wise thing to do would be to ask yourself if the "is-a" relationship makes sense.**


### Encapsulation

Encapsulation is one of the fundamental principles of object oriented programming, and is one of the approaches that we take as programmers to resist our biggest enemy: complexity. Managing complexity is one of the major challenges we must face when writing large programs.

Some of the tools we can use to fight complexity include hierarchical abstraction (abstraction barriers!) and a concept known as "Design for change". This revolves around the idea that *programs should be built into modular, interchangeable pieces* that can be swapped around without breaking the system. Additionally, hiding information that others don't need is another fundamental approach when managing a large system.

Take the `ArrayDeque` class, for example. The outside world is able to utilize and interact with an `ArrayDeque` through its defined methods, like `addLast` and `removeLast`. However, they need not understand the complex details of how the data structure was implemented in order to be able to use it effectively.

### Abstraction Barriers

Ideally, a user should not be able to observe the internal workings of, say, a data structure they are using. Fortunately, Java makes it easy to enforce *abstraction barriers*. Using the `private` keyword in Java, it becomes virtually impossible to look inside an object - ensuring that the underlying complexity isn't exposed to the outside world.

### How Inheritance Breaks Encapsulation

Suppose we had the following two methods in a `Dog` class. We could have implemented `bark` and `barkMany` like so:

```java
public void bark() {
    System.out.println("bark");
}

public void barkMany(int N) {
    for (int i = 0; i < N; i += 1) {
        bark();
    }
}
```

Or, alternatively, we could have implemented it like so:

```java
public void bark() {
    barkMany(1);
}

public void barkMany(int N) {
    for (int i = 0; i < N; i += 1) {
        System.out.println("bark");
    }
}
```

From a user's perspective, the functionality of either of these implementations is exactly the same. However, observe the effect if we were to define a a subclass of `Dog` called `VerboseDog`, and override its `barkMany` method as such:

```java
@Override
public void barkMany(int N) {
    System.out.println("As a dog, I say: ");
    for (int i = 0; i < N; i += 1) {
        bark();
    }
}
```

**Quiz:** Given a `VerboseDog vd`, what would `vd.barkMany(3)` output, given the first implementation above? The second implementation?

- a: As a dog, I say: bark bark bark
- b: bark bark bark
- c: Something else


### Type Checking and Casting

![](https://joshhug.gitbooks.io/hug61b/content/assets/dynamic_selection.png)

```java
sl.addLast(50);
sl.removeLast();
```

These lines above also compile. The call to addLast is unambiguous, as VengefulSLList did not override or implement it, so the method executed is in SLList. _*The removeLast method is overridden by VengefulSLList*_, however, so we take a look at the dynamic type of sl. Its *_dynamic type_* is VengefulSLList, and so *_dynamic method selection_* chooses the *_overridden method_* in the VengefulSLList class.

##### Dynamic Method Selection
If overridden, decide which method to call based on _**run-time type**_ (or dynamic-type) of variable.

![dynamic-method-selection](https://github.com/Zhenye-Na/cs61b-ucb/blob/master/fig/dynamic-method-selection.jpg?raw=true)

##### Compile-Time Type Checking
Compiler allows method calls based on _**compile-time type**_ (or static-type) of variable.

![compile-time-type-checking](https://github.com/Zhenye-Na/cs61b-ucb/blob/master/fig/dynamic-method-selection.jpg?raw=true)

Compiler also allows assignments based on compile-time types.

- Even though sl’s runtime-type is VengefulSLList, cannot assign to vsl2.
- Compiler plays it as safe as possible with type checking.


#### Expressions

Like variables as seen above, expressions using the `new` keyword also have *_compile-time types_*.

```java
SLList<Integer> sl = new VengefulSLList<Integer>();
```

Above, the *_compile-time type_* of the right-hand side of the expression is `VengefulSLList`. The compiler checks to make sure that _VengefulSLList "is-a" SLList_, and allows this assignment,

```java
VengefulSLList<Integer> vsl = new SLList<Integer>();
```

Above, the _*compile-time type*_ of the right-hand side of the expression is `SLList`. The compiler checks if **SLList "is-a" VengefulSLList**, which it is **not** in all cases, and thus a compilation error results.

Further, method calls have compile-time types equal to their declared type. Suppose we have this method:

```java
public static Dog maxDog(Dog d1, Dog d2) { ... }
```

Since the _return type_ of maxDog is _Dog_, any call to maxDog will have _compile-time type Dog_.

```java
Poodle frank = new Poodle("Frank", 5);
Poodle frankJr = new Poodle("Frank Jr.", 15);

Dog largerDog = maxDog(frank, frankJr);
Poodle largerPoodle = maxDog(frank, frankJr); //does not compile! RHS has compile-time type Dog, LHS is Poodle.
```

Assigning a Dog object to a Poodle variable, like in the SLList case, results in a compilation error. A Poodle "is-a" Dog, but a more general Dog object may not always be a Poodle, even if it clearly is to you and me (we know that frank and frankJr are both Poodles!).

#### Casting

Java has a special syntax where you can tell the compiler that a specific expression has a specific compile-time type. This is called "casting". With casting, we can tell the compiler to view an expression as a different compile-time type.

```java
Poodle largerPoodle = (Poodle) maxDog(frank, frankJr);
// compiles! Right hand side has compile-time type Poodle after casting
```

### Higher Order Functions

Taking a little bit of a detour, we are going to introduce higher order functions. *_A higher order function is a function that treats other functions as data_*. For example, take this Python program `do_twice` that takes in another function as input, and applies it to the input x twice.

```python
def tenX(x):
    return 10*x

def do_twice(f, x):
    return f(f(x))
```

A call to `print(do_twice(tenX, 2))` would apply `tenX` to `2`, and apply `tenX` again to its result, `20`, resulting in `200`. How would we do something like this in Java?

In old school Java (Java 7 and earlier), memory boxes (variables) could not contain pointers to functions. What that means is that we could not write a function that has a "Function" type, as there was simply no type for functions.

To get around this we can take advantage of _*interface inheritance*_. Let's write an interface that defines any function that _takes in an integer and returns an integer - an IntUnaryFunction_.

```java
public interface IntUnaryFunction {
    int apply(int x);
}
```

Now we can write a class which implements `IntUnaryFunction` to represent a concrete function. Let's make a function that takes in an integer and returns 10 times that integer.

```java
public class TenX implements IntUnaryFunction {
    /* Returns ten times the argument. */
    public int apply(int x) {
        return 10 * x;
    }
}
```

At this point, we've written in Java the Python equivalent of the tenX function. Let's write `do_twice` now.

```java
public static int do_twice(IntUnaryFunction f, int x) {
    return f.apply(f.apply(x));
}
```

A call to `print(do_twice(tenX, 2))` in Java would look like this:
`System.out.println(do_twice(new TenX(), 2));`

* * *

**Notes:**
> **Interaces are types**. We can *_use the names of interfaces to declare variables_*: local and parameter variables in methods, and instance variables in classes. This simple statement leads to some extremely interesting and deep ideas in object-oriented programming. What can we do with a variable whose type is declared by the name of an interface?
> 
- We can store into it a reference to an object constructed from any class that says that it implements the interface.
- We can use it to call any of the methods specified in the interface. The actual method called is the one defined in the object that such a variable refers to.
>
So, both of the following declaratins, and their pictures (Image source: Interfaces-Advanced Programming/Practicum 15-200), make sense.

![](https://www.cs.cmu.edu/~pattis/15-1XX/15-200/lectures/interfaces/images/decisionint.gif)

more details please refer this [tutorial](https://www.cs.cmu.edu/~pattis/15-1XX/15-200/lectures/interfaces/lecture.html).

Check out this wonderful video explanation of interface in Java by [Navin Reddy](https://www.youtube.com/watch?v=Yaa3QroWe7Q) at 6:40.

* * *

### Inheritance Cheatsheet

1. VengefulSLList extends SLList means VengefulSLList *_"is-an"_* SLList, and inherits all of SLList's members:

    - Variables, methods nested classes
    - **Not** constructors Subclass constructors must invoke superclass constructor first. The `super` keyword can be used to *_invoke overridden superclass methods_* and constructors.

2. Invocation of overridden methods follows two simple rules:

    - Compiler plays it safe and only allows us to do things according to the `static type`.
    - For *_overridden_* methods (not _*overloaded*_ methods), the actual method invoked is based on the `dynamic type` of the invoking expression
    - Can use casting to overrule compiler type checking.


## 4.2 Subtype Polymorphism vs. HoFs

### Subtype Polymorphism

We've seen how inheritance lets us reuse existing code in a superclass while implementing small modifications by *_overriding_* a superclass's methods or writing brand new methods in the subclass. _Inheritance also makes it possible to design general data structures and methods using polymorphism._

`Polymorphism`, at its core, means _'many forms'_. In Java, polymorphism refers to how objects can have many forms or types. In object-oriented programming, polymorphism relates to how an object can be regarded as an instance of its own class, an instance of its superclass, an instance of its superclass's superclass, and so on.

Consider a variable `deque` of static type `Deque`. A call to `deque.addFirst()` will be determined at the time of execution, depending on the _run-time type_, or _dynamic type_, of deque when `addFirst` is called. As we saw in the last chapter, Java picks which method to call using `dynamic method selection`.

Suppose we want to write a python program that prints a string representation of the larger of two objects. There are two approaches to this.

1. Explicit HoF Approach

    ```python
    def print_larger(x, y, compare, stringify):
        if compare(x, y):
            return stringify(x)
        return stringify(y)
    ```

2. Subtype Polymorphism Approach

    ```python
    def print_larger(x, y):
        if x.largerThan(y):
            return x.str()
        return y.str()
    ```






### Interfaces Quiz

```java
public class DogLauncher {
    public static void main(String[] args) {
        ...
        Dog[] dogs = new Dog[]{d1, d2, d3};
        System.out.println(Maximizer.max(dogs));
    }
}

public class Dog implements OurComparable {
    ...
    public int compareTo(Object o) {
        Dog uddaDog = (Dog) o;
        if (this.size < uddaDog.size) {
            return -1;
        } else if (this.size == uddaDog.size) {
            return 0;
        }
        return 1;
    }
    ...
}

public class Maximizer {
    public static OurComparable max(OurComparable[] items) {
        ...
        int cmp = items[i].compareTo(items[maxDex]);
        ...
    }
}
```



![](https://github.com/Zhenye-Na/cs61b-ucb/blob/master/fig/interfaces-quiz1.jpg?raw=true)

In this case, the **Dog class fails to compile**. By declaring that it `implements OurComparable`, the Dog class _*makes a claim that it "is-an" OurComparable*_. As a result, the compiler checks that this claim is actually true, but sees that *_Dog doesn't implement compareTo_*.

![](https://github.com/Zhenye-Na/cs61b-ucb/blob/master/fig/interfaces-quiz2.jpg?raw=true)

What if we were to omit implements OurComparable from the Dog class header? This would cause a compile error in **DogLauncher** due to this line:

```java
System.out.println(Maximizer.max(dogs));
```

If Dog does not implement the OurComparable interface, then trying to pass in an array of Dogs to Maximizer's max function wouldn't be approved by the compiler. max only accepts an array of _*OurComparable objects*_.


> Problem 1: **Dog will fail** to compile because it does not implement all abstract methods required by OurComparable interface. (And I suppose *DogLauncher will fail as well since Dog.class doesn’t exist*)

> Problem 2: **DogLauncher will fail**, because it tries to pass things that are not OurComparables, and Maximizer expects OurComparables.


### Comparables

We'll take advantage of an interface that already exists called `Comparable`. Comparable is already defined by Java and is used by countless libraries.

![](https://joshhug.gitbooks.io/hug61b/content/assets/comparable_interface.png)

Notice that `Comparable<T>` means that it takes a _*generic type*_. This will help us avoid having to cast an object to a specific type!


### Comparator

We've just learned about the comparable interface, which imbeds into each Dog the ability to compare itself to another Dog. Now, we will introduce a new interface that looks very similar called `Comparator`.
Let's start off by defining some terminology.

> **Natural order** - used to refer to the ordering implied in the compareTo method of a particular class.

What if we'd like to sort Dogs in a different way than their natural ordering, such as by alphabetical order of their name? Java's way of doing this is by using _Comparator's_. Since a `comparator` is an object, the way we'll use Comparator is by _writing a nested class inside Dog that implements the Comparator interface._

```java
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```

This shows that the Comparator interface requires that any implementing class implements the compare method. The rule for compare is just like `compareTo`:

- Return negative number if o1 < o2.
- Return 0 if o1 equals o2.
- Return positive number if o1 > o2.

Let's give Dog a `NameComparator`. To do this, we can simply defer to `String`'s already defined `compareTo` method.

```java
import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    ...
    public int compareTo(Dog uddaDog) {
        return this.size - uddaDog.size;
    }

    /** private nested class that implements Comparator<Dog>. */
    private static class NameComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.name.compareTo(b.name);
        }
    }

    public static Comparator<Dog> getNameComparator() {
        return new NameComparator();
    }
}
```

Note that we've declared `NameComparator` to be a `static` class. A minor difference, but we do so because _**we do not need to instantiate a Dog to get a NameComparator**_. Let's see how this Comparator works in action.

To summarize, interfaces in Java provide us with the ability to make **callbacks**. Sometimes, a function needs the help of another function that might not have been written yet (e.g. `max` needs `compareTo`). _*A callback function is the helping function*_ (in the scenario, compareTo). In some languages, this is accomplished using explicit function passing; in Java, we wrap the needed function in an interface.


## 4.3 Libraries, Abstract Classes, Packages

### Abstract Data Types (ADTS)

We have this interface deque that both `ArrayDeque` and `LinkedListDeque` implement. What is the relationship between Deque and its implementing classes? Well, deque simply provides a list of methods (behaviors):

```java
public void addFirst(T item);
public void addLast(T item);
public boolean isEmpty();
public int size();
public void printDeque();
public T removeFirst();
public T removeLast();
public T get(int index);
```

These methods are actually implemented by ArrayDeque and LinkedListDeque.

In Java, _Deque is called an interface_. Conceptually, we call deque an `Abstract data type`. Deque only comes with behaviors, not any concrete ways to exhibit those behaviors. In this way, it is abstract.


> We can characterize the situation in the abstract by describing sets of operations that are supported by different data structures—that is by describing possible abstract data types. From the point of view of a program that needs to represent some kind of collection of data, this set of operations is all that one needs to know.


### Java Libraries

Java has certain built-in Abstract data types that you can use. These are packaged in Java Libraries.
The three most important ADTs come in the java.util library:

- [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html): an ordered collection of items
    - A popular implementation is the [`ArrayList`](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
- [Set](https://docs.oracle.com/javase/7/docs/api/java/util/Set.html): an unordered collection of strictly unique items (no repeats)
    - A popular implementation is the [`HashSet`](https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html)
- [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html): a collection of key/value pairs. You access the value via the key.
    - A popular implementation is the [`HashMap`](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)

Finish the exercises below by using the above three ADT's. Reading the documentations linked above will help immensely.

```java
public static List<String> getWords(String inputFileName) {
    List<String> lst = new ArrayList<String>();
    In in = new In();
    while (!in.isEmpty()) {
        lst.add(in.readString()); //optionally, define a cleanString() method that cleans the string first.       
    }
    return lst;
}

public static int countUniqueWords(List<String> words) {
    Set<String> ss = new HashSet<>();
    for (String s : words) { // equivalent to python syntax: for s in words
           ss.add(s);        
    }
    return ss.size();
}
```

> Write a method collectWordCount that takes in a `List<String> targets` and a `List<String> words` and finds the _number of times_ each target word appears in the word list.

```java
public static Map<String, Integer> collectWordCount(List<String> words), List<String> targets {
    Map<String, Integer> counts = new HashMap<String, Integer>();
    for (String t: target) {
        counts.put(s, 0);
    }
    for (String s: words) {
        if (counts.containsKey(s)) {
            counts.put(word, counts.get(s)+1);
        }
    }
    return counts;
}
```

The interface body can contain `abstract methods`, `default methods`, and `static methods`. 

- `Abstract method` within an interface is followed by a semicolon, but no braces (an abstract method does not contain an implementation).
- `Default methods` are defined with the default modifier.
- `Static methods` with the static keyword. 

All abstract, default, and static methods in an interface are _implicitly public_, so _you can omit the public modifier_.

In addition, an interface can contain constant declarations. All constant values defined in an interface are _implicitly public, static, and final (cannot be changed) _. Once again, you can omit these modifiers.

#### Notes:

**Interfaces:**

- All methods must be `public`.
- All variables must be `public static final`.
- Cannot be instantiated
- All methods are by default `abstract` unless specified to be `default`
- Can implement more than one interface per class

**Abstract Classes:**

- Methods can be `public` or `private`
- Can have any types of variables
- Cannot be instantiated
- Methods are _by default concrete_ unless specified to be `abstract`
- _Can only implement one per class_

**_Basically, abstract classes can do everything interfaces can do and more._**


## 5.2 Generics, Autoboxing

### Autoboxing and Unboxing
As we saw in the previous chapter, we can define classes which have generic type variables using the `<>` syntax, e.g. `LinkedListDeque<Item>` and `ArrayDeque<Item>`. When we want to instantiate an object whose class uses _generics_, we have to substitute the generic with a concrete class, i.e. specify what type of items are going to go into that class.

Recall that Java has 8 primitive types -- all other types are reference types. One particular feature of Java is that we cannot provide a primitive type as an actual type argument for generics, e.g. `ArrayDeque<int>` is a syntax error. Instead, we use `ArrayDeque<Integer>`. _For each primitive type, we use the corresponding reference type as shown in the table below. These reference types are called "wrapper classes"._

![](https://joshhug.gitbooks.io/hug61b/content/assets/wrapper_classes.png)

There are a few things to keep in mind when it comes to autoboxing and unboxing:

- **Arrays are never autoboxes or auto-unboxed**, e.g. if you have an array of integers `int[] x`, and try to put its address into a variable of type `Integer[]`, the compiler will **not** allow your program to compile.
- Autoboxing and unboxing also has a measurable performance impact. That is, **code that relies on autoboxing and unboxing will be slower than code that eschews such automatic conversions**.
- Additionally, **wrapper types use much more memory than primitive types**. On most modern comptuers, not only must your code _hold a 64 bit reference to the object_, but _every object also requires 64 bits of overhead_ used to store things like the dynamic type of the object.


### Widening

Similar to the autoboxing/unboxing process, Java will also automatically widen a primitive if needed. Specifically, if a program expects a primitive of type T2 and is given a variable of type T1, and type T2 can take on a wider range of values than T1, the the variable will be implicitly cast to type T2.
For example, doubles in Java are wider than ints. If we have the function shown below:

```java
public static void blahDouble(double x) {
    System.out.println("double: " + x);
}
```

We can call it with an `int` argument:

```java
int x = 20;
blahDouble(x);
```

If you want to go from a wider type to a narrower type, you must manually cast. For example, if you have the method below:

```java
public static void blahInt(int x) {
    System.out.println("int: " + x);
}
```

Then we'd need to use a cast if we want to call this method using a double value, e.g.

```java
double x = 20;
blahInt((int) x);
```

For more details on widening, including a full description of what types are wider than others, see the official Java documentation.


### Immutability

The final keyword is a keyword for variables that prevents the variable from being changed after its first assignment. For example, consider the Date class below:

```java
public class Date {
    public final int month;
    public final int day;
    public final int year;
    private boolean contrived = true;
    public Date(int m, int d, int y) {
        month = m; day = d; year = y;
    }
}
```


### Creating Another Generic Class

#### ArrayMap

```
put(key, value): Associate key with value.
containsKey(key): Checks if map contains the key.
get(key): Returns value, assuming key exists.
keys(): Returns a list of all keys.
size(): Returns number of keys.
```

Feel free to try building an ArrayMap on your own, but for reference, the full implementation is below.

```java
package Map61B;

import java.util.List;
import java.util.ArrayList;

/**
 * An array-based implementation of Map61B.
 */
public class ArrayMap<K, V> implements Map61B<K, V> {

    private K[] keys;
    private V[] values;
    int size;

    public ArrayMap() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
        size = 0;
    }

    /**
    * Returns the index of the key, if it exists. Otherwise returns -1.
    **/
    private int keyIndex(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
            return i;
        }
        return -1;
    }

    public boolean containsKey(K key) {
        int index = keyIndex(key);
        return index > -1;
    }

    public void put(K key, V value) {
        int index = keyIndex(key);
        if (index == -1) {
            keys[size] = key;
            values[size] = value;
            size += 1;
        } else {
            values[index] = value;
        }
    }

    public V get(K key) {
        int index = keyIndex(key);
        return values[index];
    }

    public int size() {
        return size;
    }

    public List<K> keys() {
        List<K> keyList = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            keyList.add(keys[i]);
        }
        return keyList;
    }
}
```

### ArrayMap and Autoboxing Puzzle

If we write a test as shown below:

```java
@Test
public void test() { 
    ArrayMap<Integer, Integer> am = new ArrayMap<Integer, Integer>();
    am.put(2, 5);
    int expected = 5;
    assertEquals(expected, am.get(2));
}
```

You will find that we get a compile-time error!

```java
$ javac ArrayMapTest.java
ArrayMapTest.java:11: error: reference to assertEquals is ambiguous
    assertEquals(expected, am.get(2));
    ^
    both method assertEquals(long, long) in Assert and method assertEquals(Object, Object) in Assert match
```

We get this error because JUnit's `assertEquals` method is overloaded, eg. `assertEquals(int expected, int actual)`, `assertEquals(Object expected, Object actual)`, etc. Thus, Java is unsure which method to call for `assertEquals(expected, am.get(2))`, which requires one argument to be _autoboxed/unboxed_.

#### Quiz

What would we need to do in order to call `assertEquals(long, long)`?  
A.) Widen expected to a `long`  
B.) Autobox expected to a `Long`  
C.) Unbox `am.get(2)`  
D.) Widen the unboxed `am.get(2)` to long

_*Answer A, C, and D all work.*_

How would we make it work with `assertEquals(Object, Object)`?

*_Answer Autobox expected to an Integer because `Integers` are `Objects`._*

### Generic Methods

`get` is a static method that takes in a `Map61B` instance and a key and returns the value that corresponds to the key if it exists, otherwise returns `null`.

![](https://github.com/Zhenye-Na/cs61b-ucb/blob/master/fig/generic-methods.jpg?raw=true)


Compared to Generic Class `public class CS61B<Item>`, we add the "reference type" in front of return type.


The `>` operator can't be used to compare `K` objects. This only works on primitives and map may not hold primitives

```java
public static <K extends Comparable<K>, V> K maxKey(Map61B<K, V> map) {
    List<K> keylist = map.keys();
    K largest = map.get(0);
    for (K k: keylist) {
        if (k.compareTo(largest)) {
            largest = k;
        }
    }
    return largest;
}
```


```java
public static <K extends Comparable<K>, V> K maxKey(Map61B<K, V> map) {...}
```

The `K extends Comparable<K>` means keys must implement the comparable interface and can be compared to other `K`'s. We need to include the `<K>` after Comparable because _Comparable itself is a generic interface_! Therefore, we must specify what kind of comparable we want. In this case, we want to compare `K`'s with `K`'s.


### Type upper bounds

You might be wondering, why does it "extend" comparable and not "implement"? Comparable is an interface after all. Well, it turns out, "extends" in this context has a different meaning than in the polymorphism context.

When we say that the `Dog` class extends the `Animal` class, we are saying that Dogs can do anything that animals can do and more! We are giving `Dog` the abilities of an animal. 

When we say that `K extends Comparable`, we are simply stating a fact. We aren't giving K the abilities of a Comparable, _we are just saying that K must be Comparable_. This different use of extends is called **type upper bounding**. 

In the context of generics, `extends` simply states a fact: **_You must be a subclass of whatever you're extending_**. When used with generics (like in generic method headers), `extends` imposes a constraint rather than grants new abilities.



## 5.3 Exceptions, Iterators, Iterables

### Throwing Exceptions

When something goes really wrong in a program, we want to break the normal flow of control. It may not make sense to continue on, or it may not be possible at all. In these cases, the program throws an exception.
Let's look at what might be a familiar case: an `IndexOutOfBounds` exception. The code below inserts the value 5 into an ArrayMap under the key "hello", then tries to print out the value when getting "yolp".

```java
public static void main (String[] args) {
    ArrayMap<String, Integer> am = new ArrayMap<String, Integer>();
    am.put("hello", 5);
    System.out.println(am.get("yolp"));
}
```

The program attempts to access a key which doesn't exist, and crashes! This results in the following error message:

```java
$ java ExceptionDemo
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: -1
at ArrayMap.get(ArrayMap.java:38)
at ExceptionDemo.main(ExceptionDemo.java:6)
```

We can throw our own exceptions, using the `throw` keyword. This lets us provide our own error messages which may be more informative to the user. We can also provide information to error-handling code within our program. This is an explicit exception because we purposefully threw it as the programmer.

In the case above, we might implement get with a check for a missing key, that throws a more informative exception:

```java
public V get(K key) {
    intlocation = findKey(key);
if(location < 0) {
    throw newIllegalArgumentException("Key " + key + " does not exist in map."\); 
}
    return values[findKey(key)];
}
```

Now, instead of `java.lang.ArrayIndexOutOfBoundsException: -1`, we see:

```java
$java ExceptionDemo
Exception in thread "main" java.lang.IllegalArgumentException: Key yolp does not exist in map.
at ArrayMap.get(ArrayMap.java:40)
at ExceptionDemo.main(ExceptionDemo.java:6)
```


### Catching Exceptions

```java
public static void main(String[] args) {
    System.out.println("ayyy lmao");
    throw new RuntimeException("For no reason.");
}
```

which produces the error:

```java
$ java Alien
ayyy lmao
Exception in thread "main" java.lang.RuntimeException: For no reason.
at Alien.main(Alien.java:4)
```

In this example, note a familiar construction: `new RuntimeException("For no reason.")`. This looks a lot like instantiating a class -- because that's exactly what it is. **_A RuntimeException is just a Java Object, like any other._**

![](https://joshhug.gitbooks.io/hug61b/content/assets/exceptions.png)


```java
Dog d = new Dog("Lucy", "Retriever", 80);
d.becomeAngry();

try {
    d.receivePat();
} catch (Exception e) {
    System.out.println("Tried to pat: " + e);
}
System.out.println(d);
```

It seems silimar to someone who is familiar with `python` syntax.

```python
try:
    blablabla
    blablabla
except:
    blablabla
    blablabla

```

### Uncaught Exceptions

![](https://joshhug.gitbooks.io/hug61b/content/assets/callstack.png)

If the `peek()` method does not explicitly catch the exception, the exception will propagate to the calling function, `sample()`. We can think of this as popping the current method off the stack, and moving to the next method below it. If `sample()` also fails to catch the exception, it moves to `main()`.

If the exception reaches the bottom of the stack without being caught, the program crashes and Java provides a message for the user, printing out the stack trace. Ideally the user is a programmer with the power to do something about it.

```java
java.lang.RuntimeException in thread "main": 
at ArrayRingBuffer.peek:63 
at GuitarString.sample:48 
at GuitarHeroLite.java:110
```

### Checked vs Unchecked Exceptions

The basic idea is that some exceptions are considered so disgusting by the compiler that you **MUST** handle them somehow. We call these **"checked"** exceptions. (You might think of that as shorthand for **"must be checked"** exceptions.)


Let's consider this example:

```java
public static void main(String[] args) {
    Eagle.gulgate();
}
```

It looks reasonable enough. But when we attempt to compile, we receive this error:

```java
$ javac What.java
What.java:2: error: unreported exception IOException; must be caught or declared to be thrown
Eagle.gulgate();
^
```

We can't compile, because of an _"unreported IOException."_ Let's look a little deeper into the Eagle class:

```java
public class Eagle {
    public static void gulgate() {
        if (today == “Thursday”) { 
            throw new IOException("hi"); }
        }
    }
}
```

On Thursdays, the `gulgate()` method is programmed to throw an `IOException`. If we try and compile `Eagle.java`, we receive a similar error to the one we saw when compiling the calling class above:

```java
$ javac Eagle
Eagle.java:4: error: unreported exception IOException; must be caught or declared to be thrown
throw new IOException("hi"); }
^
```

It's clear that Java isn't happy about this `IOException`. This is because `IOExceptions` are "checked' exceptions and must be handled accordingly. 

```java
public class UncheckedExceptionDemo {
    public static void main(String[] args) {
        if (today == “Thursday”) { 
            throw new RuntimeException("as a joke"); 
        }    
    }
}
```

`RuntimeExceptions` are considered **"unchecked"** exceptions, and do not have the same requirements as the checked exceptions. The code above will compile just fine -- though it will crash at runtime on Thursdays:

```
$ javac UncheckedExceptionDemo.java
$ java UncheckedExceptionDemo
Exception in thread "main" java.lang.RuntimeException: as a joke.
at UncheckedExceptionDemo.main(UncheckedExceptionDemo.java:3)
```

![](https://joshhug.gitbooks.io/hug61b/content/assets/checked_exceptions.png)

1. Catch

    ```java
    public static void main(String[] args) {
        try {
            gulgate();
        } catch(IOException e) {
            System.out.println("Averted!");
        }
    }
    ```

2. Specify

    ```java
    public static void main(String[] args) throws IOException {
        gulgate();
    }
    ```

- **Catch** the error when you can handle the problem there.
- **Specify** the error when someone else should handle the error.


## Iteration

`Iterator` is an `Object`.

in `List.java` we might define an `iterator()` method that returns an `iterator` object.

```java
public Iterator<E> iterator();
```

then:

```java
List<Integer> friends = new ArrayList<Integer>();
...
Iterator<Integer> seer = friends.iterator();

while (seer.hasNext()) {
    System.out.println(seer.next());
}
```

> It advances the iterator by one item. In this way, _the iterator will only inspect each item once_.


### Implementing Iterators

When instatiating a nested-class, which is not static, we should add the instance name before the `new` keyword, like:

```java
blablah.nestedblabla nbl= bl.new nestedblabla();
```
`bl` is the instance name

```java
List<Integer> friends = new ArrayList<Integer>();
Iterator<Integer> seer = friends.iterator();

while(seer.hasNext()) {
    System.out.println(seer.next());
}
```

- Does the List interface have an iterator() method?
- Does the Iterator interface have next/hasNext() methods?


The `List` interface extends the `Iterable` interface, inheriting the abstract `iterator()` method. (Actually, `List` extends `Collection` which extends `Iterable`, but it's easier to codethink of this way to start.)

```java
public interface Iterable<T> {
    Iterator<T> iterator();
}
```
```java
public interface List<T> extends Iterable<T>{
...
}
```

Next, the compiler checks that Iterators have `hasNext()` and `next()`. The Iterator interface specifies these abstract methods explicitly:

```java
package java.util;
public interface Iterator<T> {
    boolean hasNext();
    T next();
}
```


Here we've seen **`Iterable`, the interface that makes a class able to be iterated on**, and requires the method `iterator()`, which returns an **Iterator object**. And we've seen **`Iterator`, the interface that defines the object with methods to actually do that iteration**. You can think of an Iterator as a machine that you put onto an iterable that facilitates the iteration. Any iterable is the object on which the iterator is performing.



## 6.1 Packages, Access Control, Objects

### Packages and JAR files

Any Java class without an explicit package name at the top of the file is automatically considered to be part of the **_"default"_** package. Therefore, your Java files should generally start with an explicit package declaration.


### Access Control

**Private** Only code from the _given class_ can access private members. It is truly private from everything else, as subclasses, packages, and other external classes cannot access private members. TL;DR: only the class needs this piece of code.

**Package Private** This is the default access given to Java members if there is no explicit modifier written. Package private entails that classes that belong _in the same package can access_, but _not subclasses_! Why is this useful? Usually, packages are handled and modified by the same (group of) people. It is also common for people to extend classes that they didn't initially write. The original owners of the class that's being extended may not want certain features or members to be tampered with, if people choose to extend it — hence, package-private allows those who are familiar with the inner workings of the program to access and modify certain members, whereas it blocks those who are subclassing from doing the same. 
> *_TL;DR: only classes that live in the same package can access_*.

**Protected** Protected members are protected from the "outside" world, so _classes within the same package and subclasses can access_ these members, but the rest of the world (e.g. classes external to the package or non-subclasses) cannot! 
> *_TL;DR: subtypes might need it, but subtype clients will not_*.

**Public** This keyword opens up the access to everyone! This is generally what clients of the package can rely on to use, and once deployed, the public members' signatures should not change. It's like a promise and contract to people using this public code that it will always be accessible to them. Usually if developers want to "get rid of" something that's public, rather than removing it, they would call it "deprecated" instead.
> _*TL;DR: open and promised to the world*_

![](https://joshhug.gitbooks.io/hug61b/content/assets/access_modifiers.png)


## 6.2 Encapsulation, Lists, Delegation vs. Extension

### Encapsulation

We will first define a few terms:

- **Module**: A set of methods that work together as a whole to perform some task or set of related tasks.
- **Encapsulated**: A module is said to be encapsulated if its implementation is completely hidden, and it can be accessed only through a documented interface.

### API's

An API(*_Application Programming Interface_*) of an `ADT` is the list of constructors and methods and a short description of each.

_API consists of syntactic and semantic specification._

- Compiler verifies that syntax is met.
    - AKA, everything specified in the API is present.
- Tests help verify that semantics are correct.
    - AKA everything actually works the way it should.
    - Semantic specification usually written out in English (possibly including usage examples). Mathematically precise formal specifications are somewhat possible but not widespread.

### ADT's

ADT's (Abstract Data Structures) are high-level types that are defined by their behaviors, not their implementations.

> `Deque` in _Proj1_ was an `ADT` that had certain behaviors (`addFirst`, `addLast`, etc.). But, the data structures we actually used to implement it was `ArrayDeque` and `LinkedListDeque`

Some `ADT`'s are actually special cases of other ADT's. For example, `Stacks` and `Queues` are just _lists_ that have _even more specific behavior_.


## 7.1 Asymptotics I

Execution Cost (everything in the course from this point on)

- **Time complexity**: How much time does it take for your program to execute?
- **Space complexity**: How much memory does your program require?



## 7.2 Asymptotics II

```java
public static void printParty(int N) {
   for (int i = 1; i <= N; i = i * 2) {
      for (int j = 0; j < i; j += 1) {
         System.out.println("hello");   
         int ZUG = 1 + 1;
      }
   }
}
```

$$C(N) \in \Theta(N) $$










