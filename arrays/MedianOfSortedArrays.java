/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * 
 * For an odd-length array, median's value is  array[array.length/2]
 * For an even-length array, median's value is  (array[array.length/2]+array[array.length/2+1])/2, i.e. it is an average of two adjacent elements in the middle of the array.
 *
 * Essentially, we merge the 2 sorted arrays updating the median value to its next element till we reach the median position. Then if the size of the merged araay
 * is even we need to calculate the average of the sum of the median and the next element of the merged array.
 *
 * Time complexity: O(m+n), i.e. proportional to the sum of the lengths of 2 arrays
 * Space complexity: O(1)
 */
class MedianOfSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length==0 && nums2.length==0) {
            return 0d;
        }
        
        int totalLength = nums1.length+nums2.length;
        boolean isEven = totalLength%2==0;
        int medianIndex = totalLength/2;
        
        if (isEven) {
            medianIndex--;
        }
        
        double median = Integer.MIN_VALUE;        
        
        int i = 0, j = 0;
        
        for (int count = 0; count <= medianIndex; count++) {
            if (i >= nums1.length || (j < nums2.length && nums2[j]<nums1[i])) {                
                median = nums2[j];
                j++;
            } else if (j >= nums2.length || (i < nums1.length && nums1[i]<=nums2[j])) {
                median = nums1[i];
                i++;
            }
        }
        
        if (isEven) {
            if (i >= nums1.length || (j < nums2.length && nums2[j]<nums1[i])) {                
                median += nums2[j];
            } else if (j >= nums2.length || (i < nums1.length && nums1[i]<=nums2[j])) {
                median += nums1[i];
            }
            median /= 2;                                        
        }                                            
        
        return median;
    }
}
