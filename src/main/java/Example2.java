import org.apache.ignite.*;
import org.apache.ignite.cluster.ClusterGroup;

import java.util.Arrays;
import java.util.Collection;
//referred from ignite examples

//this example is to count number of words..also ignite stabilizes and auto allocates node once it closes

public class Example2 {

    public static void main(String[] args) throws IgniteException {
        try (Ignite ignite = Ignition.start("examples/config/example-ignite.xml")) {
            IgniteCluster cluster = ignite.cluster();

            ClusterGroup rmts = cluster.forRemotes();

            IgniteCompute compute = ignite.compute(rmts);

            while (true) {


                Collection<Integer> res = compute.apply(
                        (String w) -> {

                            System.out.println("Counting:" + w);
                            return w.length();


                        },

                        Arrays.asList("MichaelSchumacher is legend".split(" ")));


                int sum = res.stream().mapToInt(i -> i).sum();
                System.out.println("Character count:" + sum);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("got interuppted");
                }


            }
        }
    }
}
