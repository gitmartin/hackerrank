// Poisonous Plants problem from hackerrank.


import java.util.*;

public class Poisonous_Plants {

    public static void main(String[] args) {

        int[] p = {6, 5, 8, 4, 7, 10, 9};
        int out = poisonousPlants(p);
        System.out.println("ANSWER: " + out);

    }

    static int poisonousPlants(int[] p) {
        // list of lili
        ArrayList<LinkedList<Integer>> ra = new ArrayList<>();
        LinkedList<Integer> lili = new LinkedList<>();
        for (int i = 0; i < p.length; i++) {
            if (i > 0 && p[i] > p[i-1]) {
                ra.add(lili);
                lili = new LinkedList<>();
            }
            lili.add(p[i]);
        }
        ra.add(lili);

        for (LinkedList<Integer> li : ra)
            System.out.println(li);

        int days = 0;
        while (ra.size() > 1) {
            // remove leading int
            for (int i = 1; i < ra.size(); i++) {
                LinkedList<Integer> temp = ra.get(i);
                temp.removeFirst();
            }

            for (int i =ra.size()-1 ; i >=0 ; i--)
                if (ra.get(i).size() == 0) ra.remove(i);

            int i = 0;
            outerloop:
            while (true) {
                int j = i+1;
                if (j >= ra.size()) break;
                while (ra.get(i).getLast() >= ra.get(j).getFirst()) {
                    ra.get(i).addAll(ra.get(j));
                    ra.remove(j);
                    if (j >= ra.size()) break outerloop;
                }
                i++;
            }
            days++;
        }
        return days;
    }
}