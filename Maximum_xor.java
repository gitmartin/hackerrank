// Maximum xor problem from hackerrank

class Tree {
    public Tree left = null;
    public Tree right = null;
}

public class Maximum_xor {

    public static void main(String[] args) {
        int[] arr = {0,1,2};
        int[] queries = {3,7,2};
        int[] out = maxXor(arr, queries);

        for (int a : out) System.out.println(a);
    }

    static int[] maxXor(int[] arr, int[] queries) {
        // build up arr Trie
        Tree trie = new Tree();
        Tree pointer = trie;
        for (int i = 0; i < arr.length; i++) {
            int toadd = arr[i];
            String s = getbinstring(toadd);
            pointer = trie;
            for (int j = 0; j < 32; j++) {
                char ch = s.charAt(j);
                if (ch == '0') {
                    if (pointer.left == null) {
                        pointer.left = new Tree();
                    }
                    pointer = pointer.left;
                } else {
                    if (pointer.right == null) {
                        pointer.right = new Tree();
                    }
                    pointer = pointer.right;
                }
            }

        }

        int[] out = new int[queries.length];
        for (int qu = 0; qu < queries.length; qu++) {
            int q = queries[qu];
            String qs = getbinstring(~q); // ~ inverts all the bits in the binary representation of the int

            pointer = trie;
            String st = "";
            for (int i = 0; i < 32; i++) {
                if (qs.charAt(i) == '0') {
                    if (pointer.left != null) {
                        st = st+ "0";
                        pointer = pointer.left;
                    }
                    else {
                        st = st+ "1" ;
                        pointer = pointer.right;
                    }
                } else {
                    if (pointer.right != null) {
                        st = st+ "1";
                        pointer = pointer.right;
                    }
                    else {
                        st = st+ "0" ;
                        pointer = pointer.left;
                    }
                }
            }
            int toxor = Integer.parseInt(st,2);
            out[qu] = q^toxor;
        }
        return out;
    }

    static String getbinstring(int a ) {
        String out = "";
        for (int i = 0; i < 32; i++) {
            out = ((a>>i) & 1) + out;
        }
        return out;
    }

}
