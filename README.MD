# LintCode每日一练

# 问题：限制条件子集
---

给一个数组，给定一个target，求满足以下条件的子集个数：
条件：子集中的最小值+最大值小于给定target。

问题链接：[http://www.lintcode.com/zh-cn/problem/subset-with-target/](http://www.lintcode.com/zh-cn/problem/subset-with-target/)

# 样例
---

给出 array = [1,5,2,4,3], target = 4, 返回 2。

解释：只有子集[1],[1,2]满足条件。所以答案是2

# 解法一
---

最容易想到的方案是：以[1,5,2,4,3]为例，先对数组进行排序，
变成[1,2,3,4,5]，然后从小到大枚举其可能的区间，[1,2]，[1,2,3]，[1,2,3,4]，[1,2,3,4,5]，[2,3]，[2,3,4]，[2,3,4,5]等等...，一旦发现区间的首尾和小于target，则子集总数加上2的（区间长度 - 2）次幂，然后再遍历每个单个元素，每次有元素的两倍小于target，则子集总数再加上1。代码如下：

```
    public long power(int exp){
        long result = 1;
        for ( int i = 0; i < exp; i++){
            result *= 2;
        }
        return result;
    }

    /**
     * @param nums: the array
     * @param target: the target
     * @return: the number of subsets which meet the following conditions
     */
    public long subsetWithTarget(int[] nums, int target) {
        // Write you code here
        Arrays.sort(nums);
        long sum = 0;
        for ( int i = 0; i < nums.length; i++ ){
            if ( 2 * nums[i] < target  ){
                sum++;
            }
            for ( int j = i + 1; j < nums.length; j++ ){
                if ( nums[i] + nums[j] < target ){
                    sum += power(j-i-1);
                }
            }
        }
        return sum;
    }
```
这里也可以不用写那个power函数，而是直接用左移运算("<<")替代。

提交，排名如下：
![解法一排名](http://upload-images.jianshu.io/upload_images/10192684-50f2ba9794b384a6..png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 改进解法一
---

解法一其实做了一些多余的运算，比如当我发现[1,2,3,4]满足target时，那么[1,2]，[1,2,3]也是肯定满足的，而且他们的和可以用等比数列求和公式直接算出，所以这个时候不需要一个个判定，直接找到从某个数开始的最长区间，然后计算一下等比数列求和公式就可以了，代码如下：

```
    /**
     * @param nums: the array
     * @param target: the target
     * @return: the number of subsets which meet the following conditions
     */
    public long subsetWithTarget(int[] nums, int target) {
        Arrays.sort(nums);

        long sum = 0;
        for ( int i = 0; i < nums.length; i++ ){
            if ( 2 * nums[i] < target ){
                sum++;
            }
            int rest = target - nums[i];
            for ( int j = nums.length - 1; j > i; j-- ){
                if ( nums[j] < rest ){
                    sum += (1 - (1L << (j - i))) / (1 - 2);
                    break;
                }
            }
        }
        return sum;
    }
```

提交，排名上升到第六名：

![解法二排名](http://upload-images.jianshu.io/upload_images/10192684-3ce1c249c4a1614a..png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



