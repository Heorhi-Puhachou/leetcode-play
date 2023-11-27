package hard;

import utils.ListNode;

public class MergeSortedLists {
    public static void main(String... args) {
        //lists = [[1,4,5],[1,3,4],[2,6]]
        ListNode first = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode second = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode third = new ListNode(2, new ListNode(6));

        ListNode[] lists = {first, second, third};

        ListNode result = (new MergeSortedLists()).mergeKLists(lists);
        do {
            System.out.println(result.val);
            result = result.next;

        } while (result.next != null);

    }


    /**
     * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
     * <p>
     * Merge all the linked-lists into one sorted linked-list and return it.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: lists = [[1,4,5],[1,3,4],[2,6]]
     * Output: [1,1,2,3,4,4,5,6]
     * Explanation: The linked-lists are:
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * merging them into one sorted list:
     * 1->1->2->3->4->4->5->6
     * <p>
     * Example 2:
     * <p>
     * Input: lists = []
     * Output: []
     * <p>
     * Example 3:
     * <p>
     * Input: lists = [[]]
     * Output: []
     */

    public ListNode mergeKLists(ListNode[] lists) {
        boolean canContinue = true;
        ListNode result = null;
        ListNode currentResultNode = null;
        while (canContinue) {
            Integer min = null;
            canContinue = false;
            int minIndex = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    if (min == null || lists[i].val < min) {
                        min = lists[i].val;
                        minIndex = i;

                    }
                }
            }
            if (min != null) {
                canContinue = true;
                lists[minIndex] = lists[minIndex].next;
                if (result == null) {
                    result = new ListNode(min);
                    currentResultNode = result;
                } else {
                    currentResultNode.next = new ListNode(min);
                    currentResultNode = currentResultNode.next;
                }
            }

        }
        return result;
    }
}
