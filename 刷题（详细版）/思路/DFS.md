> 参考链接：[leetcode](https://leetcode.com/problems/) [GrandYang](https://www.cnblogs.com/grandyang/p/6854825.html) [AcWing](https://www.acwing.com/about/) [剑指offer](https://www.nowcoder.com/ta/coding-interviews)

## DFS

### 题目

- 需要重置visited的（通常 **判断某路径是否存在** 这种需要重置visited）
  - lc79 单词搜索
  - lc207 课程表
- 不需要重置visited的dfs（通常 **求连成一片的子块数量/面积等性质** 这种不需要重置visited）
  - lc200 岛屿数量
  - lc695 岛屿最大面积
  - lc547 朋友圈
  - jz66 机器人的运动范围

### 算法思想

主函数：遍历每一个元素，调用dfs函数

dfs函数：

1. 判断是否越界/visited/符合条件，视情况return
2. 设置visited
3. 在各个方向递归调用dfs函数
4. 重置visited（这一步可能不需要）