package org.wigs;

//https://www.programiz.com/java-programming/online-compiler/

/*
Given two sorted arrays Num1  and nums2 of size m and n respectively, return the median of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).

Example 1:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Example 2:
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 */


class MergeAndFindMedian {
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5};
        int[] nums2 = {2, 4, 6};

        int[] mergedArray = mergeArrays(nums1, nums2);

        float median = 0;
        if(mergedArray.length % 2 == 0){
            float sum = mergedArray[(mergedArray.length / 2) -1] + mergedArray[(mergedArray.length / 2)];
            System.out.println("sum is : "+sum);
            median = sum / 2;
        }else{
            median = mergedArray[(mergedArray.length / 2) -1];
        }

        System.out.println("median is : "+median);
    }

    private static int[] mergeArrays(int[] nums1, int[] nums2){

        int m = nums1.length;
        int n = nums2.length;

        int[] mergedArray = new int[m + n];

        int nums1Index = 0;
        int nums2Index = 0;
        for(int i =0; i < mergedArray.length; i++){

            if(nums1Index < m && nums2Index < n){
                if(nums1[nums1Index] <= nums2[nums2Index]){
                    mergedArray[i] = nums1[nums1Index];
                    nums1Index++;
                }else{
                    mergedArray[i] = nums2[nums2Index];
                    nums2Index++;
                }

            } else if(nums1Index < m){
                mergedArray[i] = nums1[nums1Index];
                nums1Index++;
            }else if(nums2Index < n){
                mergedArray[i] = nums2[nums2Index];
                nums2Index++;
            }
        }

        for(int i = 0; i < mergedArray.length; i++){

            System.out.println("merged array : "+mergedArray[i]);
        }

        return mergedArray;
    }
}
