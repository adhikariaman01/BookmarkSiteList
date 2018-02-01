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
