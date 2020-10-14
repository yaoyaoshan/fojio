### jz42 2sum

### 解法

使用hash表，建立HashMap<Integer, Integer>  map，键值对为<nums[i]，i>。遍历数组nums，对每个数nums[i]，查询hash表中是否有key等于target-nums[i]的记录，若有，则找到；否则，将num[i]和下标i存入hash表

