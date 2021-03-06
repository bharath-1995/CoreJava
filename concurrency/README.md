# Concurrency in Java 8  
Concurrency API (```java.util.concurrent``` package) was initially introduced in Java 5 to ease threads management. Since then till Java 8, it has been enriched so much with many classes and frameworks. Below its major classes, interfaces and concepts are explained. 

## Important types

### ```Runnable``` 
```java.lang.Runnable``` is a _functional_ interface used to define a work/task that thread will execute. It has functional method ```void run()``` with no argument.

### ```Callable```
```java.util.concurrent.Callable``` is a _functional_ interface used to define a work/task that thread will execute. It has functional method ```T call()``` with no argument and returns generic type. Unlike ```Runnable```, it can throw checked exceptions. 

### ```ExecutorService```
```java.util.concurrent.ExecutorService``` is an interface with methods to take/execute tasks. It provides many useful features such as thread pooling, scheduling etc. and extends functional interface ```java.uti.concurrent.Executor``` which has functional method ```void execute(Runnable task)``` 

### ```Future``` 
An interface ```java.util.concurrent.Future``` represents a result of an asynchronous task completion and has below methods:

- ```T get()``` infinitely waits for the task to complete and returns value resulted once the task is finished
- ```T get(long, TimeUnit)``` waits for the result only for specified time period and throws ```TimeOutException``` if didn't get
- ```boolean cancel(boolean)``` attempts to cancel running task and returns boolean
- ```boolean isCancelled()``` returns true if the task is cancelled before completed
- ```boolean isDone()``` returns true if the task is completed

### ``CyclicBarrier``
A class ``java.util.concurrent.CyclicBarrier`` is used to force a set of threads to wait until they are at a certain stage of execution before continuing.

## Thread manipulation   
### Scheduling tasks
```ScheduledExecutorService``` is used to schedule a task to execute after delay or repeatedly with time interval. It extends ```ExecutorService``` interface, and its instance is taken from factory method in ```java.util.concurrent.Executors``` helper class


### Pause execution
Method ```Thread.sleep(long)``` pauses current thread to suspend its execution for certain period and throws checked exception ```InterruptedException``` if interrupt request is received while pausing

### Shutting down a thread
- It is necessary to shut down the service instance(call ```shutdown()```), otherwise application will hang.
- Once it is requested to shutdown, executor rejects new task requests and throws ```RejectedExecutionException``` and 
continues executing pending/ongoing tasks till completed. During this period, ```isShutdown()``` returns ``true`` and ```isTerminated()``` returns ``false``. 
- When ongoing tasks are completed, ```isShutdown()``` and ```isTerminated()``` methods return ``true`` and won't receive new tasks.<br>
- ```shutDownNow()``` terminates all running/pending tasks and returns ```List<Runnable>``` which are not executed tasks.<br>
- ```awaitTermination(long, TimeUnit)``` waits for specified period to complete ongoing tasks after shutdown request and returns ``true`` if executor terminated or false if timeout elapsed before termination. It doesn't do anything except for waiting.


## Threading problems
**Deadlock** - multiple processes are blocked forever, each waiting on the other.  
**Starvation** - single thread is perpetually denied access to a shared resource or lock. The thread is still active, but it is unable to complete its work as a result of other threads constantly taking the resource that they trying to access.  
**Livelock** - occurs when two or more threads are conceptually blocked forever, although they are each still active and trying to complete their task. Livelock is a special case of resource starvation in which two or more threads actively try to acquire a set of locks, are unable to do so, and restart part of the process.  
**Race condition** - occurs when several threads access one resource at the same time and mess its state.  

## Synchronization
Keyword ```synchronized``` is used for in method declaration or around code block. 
When method is declared as synchronized, lock is created on the whole object, might be less efficient.
On the other hand, synchronized block uses an object to create a lock. Static synchronized method orders thread access across all instances rather than single instance.  
> Alternative to synchronized block or method, **concurrent collections** or **atomic** primitive classes can be used

### Atomic classes
```java.util.concurrent.atomic``` package provides atomic classes which supports atomic access to single values, such as primitive value or object reference. They can be ideally used in scenarios where multiple threads read/write single counter. Increment/decrement operators ++/-- are not thread-safe meaning that while one thread is updating and taking new value, other thread can update it meantime. With atomic nature, atomic classes have methods to perform increment/decrement as a single unit of operation without interference by other threads.  
```AtomicBoolean, -- ```<br>
```AtomicInteger, AtomicIntegerArray```<br> 
```AtomicLong, AtomicLongArray```<br>
```AtomicReference, AtomicReferenceArray```<br> 

### Concurrent collections
Concurrent collections provide performance enhancements that prevent unnecessary synchronizations. Read/write accesses to collection are synchronized so that data consistency is kept during multi-thread execution outside synchronized method or block. Go to <a href="concurrentcollections">concurrent collections</a>.

 