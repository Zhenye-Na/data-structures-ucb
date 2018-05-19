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
An SLList (Singly-Linked List) is a _sequence of Nodes_. Each node $\mathtt{u` stores a data value $\mathtt{u.x` and a reference $\mathtt{u.next` to the next node in the sequence. For the last node $\mathtt{w` in the sequence, $\mathtt{w.next} = \mathtt{null`.

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

### Naive Array Based List









































{style="text-align:center"}