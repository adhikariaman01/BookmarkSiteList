# Pointers
```c
int x = 42;

printf("Value of x is : %d \n",x);

int* pointer_to_x = &x; // pointer_to_x has value 5

printf("Pointer to x is : %d \n",pointer_to_x);

int y = *pointer_to_x; // y is assigned the value found at address "pointer_to_x"
                       // which is the address of x. x has value 42, so y will be 42.

printf(" Value at that pointer is %d \n",y);

 ```


## When to use ampersend (&) OR Asterik (*) ?
```c
You have pointers and values:

int* p; // variable p is pointer to integer type
int i; // integer value
You turn a pointer into a value with *:

int i2 = *p; // integer i2 is assigned with integer value that pointer p is pointing to
You turn a value into a pointer with &:

int* p2 = &i; // pointer p2 will point to the address of integer i
Edit: In the case of arrays, they are treated very much like pointers. If you think of them as pointers, you'll be using * to get at the values inside of them as explained above, but there is also another, more common way using the [] operator:

int a[2];  // array of integers
int i = *a; // the value of the first element of a
int i2 = a[0]; // another way to get the first element
To get the second element:

int a[2]; // array
int i = *(a + 1); // the value of the second element
int i2 = a[1]; // the value of the second element
So the [] indexing operator is a special form of the * operator, and it works like this:

a[i] == *(a + i);  // these two statements are the same thing
 ```
