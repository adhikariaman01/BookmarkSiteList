## Array - Deep Copy / Shallow Copy
- [ ] `System.arraycopy()` : perform deep copy
- [ ] `Arrays.copyOf()` : performs shallow copy
- [ ] `Object.clone()` : perform shallow copy
- [ ] `Arrays.copyOfRange()` :  similar to `copyOf()` until specified range
- [ ] using `Stream API (Java 8)` : 
    ```java 
    String[] strArray = {"orange", "red", "green'"};
    String[] copiedArray = Arrays.stream(strArray).toArray(String[]::new);

    ```

- [ ] Serialization using external Libraries like GSON, Commons 3 : (Deep Copy)


- [ ] Deep Copy : 
    - deep copy creates a new value for referenced object instead of copying a reference.
- [ ] Shallow Copy :
    - Shallow copy simply copies the reference of a referenced object 
        
- [ ] If there is only primitive type field or Immutable object then Deep Copy and Shallow Copy are same.

- [ ] Cloning :
    - creates exact copy of an existing object in memory. Object implementing `Cloneable Interface` are only eligible for cloning process

- [x] Shallow vs Deep Copy in Java - by [Marcus Biel](https://dzone.com/articles/java-copy-shallow-vs-deep-in-which-you-will-swim)
