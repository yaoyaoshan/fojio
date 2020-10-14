### lc1115 交替打印FooBar

### 解法

```cpp
#include <semaphore.h>
class FooBar {
private:
    int n;
    sem_t sfoo;
    sem_t sbar;

public:
    FooBar(int n) {
        this->n = n;
        sem_init(&sfoo, 0, 1); 
        sem_init(&sbar, 0, 0); 
    }

    void foo(function<void()> printFoo) {
        for (int i = 0; i < n; i++) { 
            sem_wait(&sfoo); 
            // printFoo() outputs "foo". Do not change or remove this line.
            printFoo();
            sem_post(&sbar);
        }
    }

    void bar(function<void()> printBar) { 
        for (int i = 0; i < n; i++) { 
            sem_wait(&sbar);
            // printBar() outputs "bar". Do not change or remove this line.
            printBar();
            sem_post(&sfoo);
        }
    }
};
```

