package reverselist;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 */

public class ReverseList {

    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private static ListNode initList(int... numbers) {
        ListNode node = new ListNode(numbers[0]);
        ListNode first = node;
        for (int i = 1; i < numbers.length; i++) {
            node.next = new ListNode(numbers[i]);
            node = node.next;
        }
        return first;
    }

    /**
     * Reverses passed list using recursion
     *
     * @param head head of the list
     * @return - new head after reversing
     */
    public static ListNode reverseList(ListNode head) {
        if (head.next == null) return head;
        ListNode node = reverseList(head.next);
        head.next.next=head;
        head.next=null;
        return node;
    }

    /**
     * Reverses given part of passed list using recursion
     *
     * @param head - list head
     * @param left - position of element to start reverse from
     * @param right - position of element to end reverse on
     * @return - reversed list head
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode nodeLeft = new ListNode(-1, head);
        ListNode nodeRight = head;
        for (int i = 0; i < right; i++) {
            if(i + 1 == left - 1) nodeLeft = nodeRight;
            nodeRight = nodeRight.next;
        }

        class Reverser {
            static ListNode reverseListBetween(ListNode left, ListNode right) {
                if (left.next == right) return left;
                ListNode node = reverseListBetween(left.next, right);
                left.next.next = left;
                left.next = right;
                return node;
            }
        }

        nodeLeft.next = Reverser.reverseListBetween(nodeLeft.next, nodeRight);
        return (left != 1) ? head : nodeLeft.next;
    }

    private static void printList(ListNode head) {
        if (head == null) return;
        System.out.print(head.val + "->");
        printList(head.next);
    }

    public static void main(String[] args) {
        ListNode head = initList( 1, 2, 3, 4, 5);
        printList(head);

        System.out.println();
        printList(reverseBetween(head,1, 3));
    }
}
