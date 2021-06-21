### lc2 链表大数相加

### 解法一

```cpp
ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
    int extraOne = 0;
    ListNode *dummyHead = new ListNode(), *prev = dummyHead;
    while (l1 || l2) {
        int val1 = 0;
        if (l1) {
            val1 = l1->val;
            l1 = l1->next;
        }
        int val2 = 0;
        if (l2) {
            val2 = l2->val;
            l2 = l2->next;
        }
        ListNode* curNode = new ListNode((val1 + val2 + extraOne) % 10);
        prev->next = curNode;
        prev = curNode;
        extraOne = (val1 + val2 + extraOne) / 10;
    }
    if (extraOne) { // 很关键！！！
        prev->next = new ListNode(1);
    }
    return dummyHead->next;
}
```

