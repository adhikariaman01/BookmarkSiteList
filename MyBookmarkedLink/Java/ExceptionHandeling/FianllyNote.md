* **Does `Finally` always gets called ?**
 ```javascript
 Yes, finally will be called.
 The only times finally wont be called are:
 -- If you invoke System.exit();
-- If the JVM crashes first;
-- If there is an infinite loop (or some other non-interruptable, non-terminating statement) in the try block;
-- If the OS forcibly terminates the JVM process; e.g. "kill -9 " on UNIX.
-- If the host system dies; e.g. power failure, hardware error, OS panic, etc
```
```Java
public static void main(String[] args) {
    System.out.println(Test.test());
}

public static int test() {
    try {
        return 0;
    }
    finally {
        System.out.println("finally trumps return.");
    }
}
```
> Output :
```java
finally trumps return. 
 0
```
