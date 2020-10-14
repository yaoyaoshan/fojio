### lc92 反转链表的第m-n个

### 解法

```cpp
ListNode* reverseBetween(ListNode* head, int m, int n) {
    if (head == nullptr) return head;
    ListNode* dummyHead = new ListNode(-1);
    dummyHead->next = head;
    ListNode* cur = head;
    ListNode* prev = dummyHead;
    int i = 1;
    while (i < m) {
        prev = prev->next;
        cur = cur->next;
        ++i;
    }
    ListNode* last = cur;
    cur = cur->next;
    while (i < n) {
        ListNode* next = cur->next;
        cur->next = prev->next;
        prev->next = cur;
        cur = next;
        ++i;
    }
    last->next = cur; // 注意这里！需要把这组和原链表连起来！
    return dummyHead->next;
}
```
