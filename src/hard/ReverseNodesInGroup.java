package hard;

import utils.ListNode;

import java.awt.*;

public class ReverseNodesInGroup {
    public static void main(String... args) {
        //[1,2,3,4,5]
//        ListNode head =
//                new ListNode(1,
//                        new ListNode(2,
//                                new ListNode(3,
//                                        new ListNode(4,
//                                                new ListNode(5)))));

        //[1,2]
        ListNode head =
                new ListNode(1,
                        new ListNode(2));
        ListNode result = (new ReverseNodesInGroup()).reverseKGroup(head, 2);
        do {
            System.out.println(result.val);
            result = result.next;
        } while (result != null);
    }

    /**
     * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
     * <p>
     * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
     * <p>
     * You may not alter the values in the list's nodes, only nodes themselves may be changed.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: head = [1,2,3,4,5], k = 2
     * Output: [2,1,4,3,5]
     * <p>
     * Example 2:
     * <p>
     * Input: head = [1,2,3,4,5], k = 3
     * Output: [3,2,1,4,5]
     */

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        ListNode[] tempNodes = new ListNode[k];
        ListNode tempHead = head;

        while (tempHead != null) {
            int i = 0;

            //save k values
            for (; i < k; i++) {
                tempNodes[i] = tempHead;
                if (tempHead.next == null) {
                    break;
                }
                tempHead = tempHead.next;
            }

            //last small section
            if (i < k - 1) {
                break;
            }

            //swap values
            //a = a ^ b;  // a = 1111 (15)
            //b = a ^ b;  // b = 1010 (5)
            //a = a ^ b;  // a = 0101 (10)
            for (int j = 0; j < k / 2; j++) {
                tempNodes[j].val = tempNodes[k - j - 1].val ^ tempNodes[j].val;
                tempNodes[k - j - 1].val = tempNodes[k - j - 1].val ^ tempNodes[j].val;
                tempNodes[j].val = tempNodes[j].val ^ tempNodes[k - j - 1].val;
            }
        }
        return head;
    }
}
