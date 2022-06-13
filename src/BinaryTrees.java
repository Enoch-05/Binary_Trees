//This is a normal binary tree.

import java.util.Objects;
import java.util.Scanner;

public class BinaryTrees {

    //Global variable declaration.
    public static int NullPointer = -1;
    public static int RootPointer;
    public static int FreePointer;
    public static int index;
    public static int NewNodePointer;
    public static int ThisNodePointer;
    public static int PreviousNodePointer;
    public static boolean TurnedLeft = true;
    //public static boolean TurnedRight = true;
    public static TreeNode[] Tree = new TreeNode[7];

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        boolean Insert = false;
        String Reply;
        int item;

        //This procedure initialises the binary tree.
        //Initialise();
        System.out.println("LLLLLLLLLLLLL");
        View();


        //This is asking the user they wish to insert a node into the binary tree.
        System.out.println("Enter 'yes' to insert a node into the binary tree, or enter 'no' not to.");
        Reply = myObj.nextLine();
        System.out.println("Reply is " + Reply);
        if (Objects.equals(Reply, "yes")) {
            Insert = true;
        }
        while (Insert) {
            System.out.println("Please enter the item you wish to store in the binary tree.");
            item = myObj.nextInt();
            InsertNode(item);
            System.out.println("The root pointer is " + RootPointer);
            InOrderTraversal(RootPointer);
            System.out.println("Enter 'yes' to continue inserting items. Enter 'no' to stop.");
            Reply = myObj.next();
            System.out.println(" ");
            if (Objects.equals(Reply, "no")) {
                Insert = false;
            }
        }

        System.out.println("This is inorder.");
        InOrderTraversal(RootPointer);
        //Asking the user which traversal would tey like to use.
        System.out.println("Would you like to view the binary tree in order?");
        System.out.println("If so, enter 'yes'. If not, enter 'no'.");
        Reply = myObj.next();

        if (Objects.equals(Reply, "yes")) {
            InOrderTraversal(RootPointer);
        } else {
            System.out.println("Would you like to view the binary tree pre order?");
            System.out.println("If so, enter 'yes'. If not, enter 'no'.");
            Reply = myObj.next();
            if (Objects.equals(Reply, "yes")) {
                PreOrderTraversal(RootPointer);
            } else {
                System.out.println("Would you like to view the binary tree post order?");
                System.out.println("If so, enter 'yes'. If not, enter 'no'.");
                Reply = myObj.next();
                PostOrderTraversal(RootPointer);
            }
        }
        System.out.println("You have exited the binary tree.");
        //End of program.
    }

    //This procedure is used to initialise the tree.
    public static void Initialise() {
        //This statement is to set the start pointer, which is RootPointer.
        RootPointer = NullPointer;
        //This statement is to set the starting position of the free list.
        FreePointer = 1;
        //This for loop is from the first index position to the second last.
        //It is linking all nodes to make the free list.
        for (index = 0; index < 6; index++) {
            index = index + 1;
            Tree[index].SetLeftPtr(index);
        }
        //This is the last node of the free list, which should point to NullPointer.
        Tree[6].SetLeftPtr(NullPointer);
    }

    //This procedure is used to insert a new node into a binary tree.
    public static void InsertNode(int NewItem) {
        if (FreePointer != NullPointer) {
            //If this 'If Statement' is true, then there is space in the binary tree.
            //Take a node from the free list, store data in it and set the null pointer.
            NewNodePointer = FreePointer;
            FreePointer = Tree[FreePointer].GetLeftPtr();
            Tree[NewNodePointer].SetData(NewItem);
            Tree[NewNodePointer].SetLeftPtr(NullPointer);
            Tree[NewNodePointer].SetRightPtr(NullPointer);
            //Now you need to check if the tree is empty.
            if (RootPointer == NullPointer) {
                RootPointer = NewNodePointer;
            } else {
                //You need to find the insertion point.
                ThisNodePointer = RootPointer;
                while (ThisNodePointer != NullPointer) {
                    PreviousNodePointer = RootPointer;
                    if (Tree[ThisNodePointer].GetData() > NewItem) {
                        //Now, follow the left pointer.
                        TurnedLeft = false;
                        ThisNodePointer = Tree[ThisNodePointer].GetRightPtr();
                    }
                }
                if (TurnedLeft) {
                    Tree[PreviousNodePointer].SetLeftPtr(NewNodePointer);
                } else {
                    Tree[PreviousNodePointer].SetRightPtr(NewNodePointer);
                }
            }
        }
    }

    //This function is used to find a node in the binary tree.
    //The function will return the pointer at where the value is found, or -1 if not found.
    public static int FindNode(int SearchItem) {
        ThisNodePointer = RootPointer;
        while ((ThisNodePointer != NullPointer) && (Tree[ThisNodePointer].GetData() > SearchItem)) {
            if (Tree[ThisNodePointer].GetData() > SearchItem) {
                //Then follow the left pointer.
                ThisNodePointer = Tree[ThisNodePointer].GetLeftPtr();
            } else {
                //Then follow the right pointer.
                ThisNodePointer = Tree[ThisNodePointer].GetRightPtr();
            }
        }
        return ThisNodePointer;
    }

    //This procedure is used to view the binary tree in the form of InOrder.
    public static void InOrderTraversal(int Pointer) {
        index = 0;
        while (RootPointer != NullPointer) {
            if (Tree[index].GetLeftPtr() != NullPointer) {
                InOrderTraversal(Tree[index].GetLeftPtr());
            }
            System.out.println(Tree[index].GetData() + "   ");
            if (Tree[index].GetRightPtr() != NullPointer) {
                InOrderTraversal(Tree[index].GetRightPtr());
            }
            System.out.println(Tree[index].GetData() + "   ");
        }
        System.out.println("The binary tree is empty.");
    }

    //To see how it looks like.
    public static void View() {
        for (index = 0; index < 7; index++) {
            System.out.println(Tree[index]);
        }
    }

    //Writing a RECORD of type Tree for the binary tree.
    static class TreeNode {

        static int Data;
        int LeftPtr = 0;
        int RightPtr = 0;

        public TreeNode(int d, int lp, int rp) {
            Data = d;
            LeftPtr = lp;
            RightPtr = rp;
        }

        public static void SetData(int d) {
            Data = d;
        }

        public int GetData() {
            return Data;
        }

        public int GetLeftPtr() {
            return LeftPtr;
        }

        public int GetRightPtr() {
            return RightPtr;
        }

        public void SetLeftPtr(int lp) {
            LeftPtr = lp;
        }

        public void SetRightPtr(int rp) {
            RightPtr = rp;
        }
    }
}