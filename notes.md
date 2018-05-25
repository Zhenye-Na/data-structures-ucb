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

![dynamic-method-selection]()

##### Compile-Time Type Checking
Compiler allows method calls based on _**compile-time type**_ (or static-type) of variable.

![compile-time-type-checking]()

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
