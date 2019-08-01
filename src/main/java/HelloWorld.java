
import org.apache.ignite.*;
import org.apache.ignite.cluster.ClusterGroup;
import org.apache.ignite.configuration.CacheConfiguration;

public class HelloWorld {

    public static void main(String[] args) throws IgniteException {
        try (Ignite ignite = Ignition.start("examples/config/example-ignite.xml")) {
            IgniteCompute compute = ignite.compute();

            compute.broadcast(() -> System.out.println("Hello node"));
        }
    }




}
