### jz22 获取倒数第k个节点

### 解法

```cpp
ListNode* getNthFromEnd(ListNode* head, int n) {
    if (!head) return head;
    ListNode* slow = head;
    ListNode* fast = head;
    while (n-- && fast) {
        fast = fast->next;
    }
    if (n > 0) return nullptr; // 没有n个节点
    while (fast) {
        slow = slow->next;
        fast = fast->next;
    }
    return slow;
}
```