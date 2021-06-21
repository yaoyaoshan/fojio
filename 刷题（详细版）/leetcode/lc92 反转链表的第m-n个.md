### lc92 反转链表的第m-n个

### 解法

```cpp
// 写法一
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
// 写法二
ListNode* reverseBetween(ListNode* head, int left, int right) {
    if (left == right) return head;
    ListNode* dummyHead = new ListNode();
    dummyHead->next = head;
    ListNode* cur = dummyHead;
    // find the node at index left
    for (int i = 1; i < left; ++i) cur = cur->next;
    ListNode *prev = cur, *start = cur->next;
    cur = start;
    int count = right - left;
    while (count >= 0) {
        ListNode* nextNode = cur->next;
        cur->next = prev->next;
        prev->next = cur;
        cur = nextNode;
        --count;
    }
    start->next = cur;
    return dummyHead->next;
}
```