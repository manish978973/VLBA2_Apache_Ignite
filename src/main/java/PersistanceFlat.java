
import org.apache.ignite.*;
//referred from Ignite examples

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheWriteSynchronizationMode;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;

import java.io.*;
import java.util.List;

public class PersistanceFlat {

    private static final String ORG_CACHE = "FLAT_FILE_VLBA2_CHECK";
    private static final boolean UPDATE = true;

    public static void main(String[] args) throws Exception {
        Ignition.setClientMode(true);
        int i = 0;
        String line;
        BufferedReader br =null;

        try (Ignite ignite = Ignition.start("examples/config/persistentstore/example-persistent-store.xml")) {
            // Activate the cluster. Required to do if the persistent store is enabled because you might need
            // to wait while all the nodes, that store a subset of data on disk, join the cluster.
            ignite.active(true);

            CacheConfiguration<Integer, String> cacheCfg = new CacheConfiguration<>(ORG_CACHE);

            cacheCfg.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
            cacheCfg.setBackups(1);
            cacheCfg.setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_SYNC);
            cacheCfg.setIndexedTypes(Integer.class, String.class);

            IgniteCache<Integer, String> cache = ignite.getOrCreateCache(cacheCfg);

            if(UPDATE) {


                System.out.println("Populating the cache...");

                try (IgniteDataStreamer<Integer, String> streamer = ignite.dataStreamer(ORG_CACHE)) {
                    streamer.allowOverwrite(true);


                    br = new BufferedReader(new FileReader("/home/manish/Downloads/apache-ignite-fabric-2.3.0-bin/helloworld/src/main/java/Access.log"));
                    while ((line = br.readLine()) != null) {

                        streamer.addData(++i, line);
                        System.out.println("Processing " + i + "th Line");

                    }


                }

            }

            QueryCursor<List<?>> cur = cache.query(
                    new SqlFieldsQuery("select count(_val) from String where _val like '%88.240.129.183%'"));


            System.out.println("SQL Count Result : " + cur.getAll());




        }
    }
}
