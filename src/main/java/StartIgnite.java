import org.apache.ignite.*;
import org.apache.ignite.cluster.ClusterGroup;

//Referred from Ignite examples

public class StartIgnite {

    public static void main(String[] args) throws Exception {

        Ignite ignite = Ignition.start("examples/config/example-ignite.xml");

       // Ignite ignite = Ignition.start("examples/config/persistentstore/example-persistent-store.xml");
    }




}
