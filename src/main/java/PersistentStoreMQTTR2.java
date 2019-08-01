//referred from ignite examples
import com.google.common.base.Splitter;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheWriteSynchronizationMode;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.internal.util.lang.GridMapEntry;
import org.apache.ignite.stream.StreamSingleTupleExtractor;
import org.apache.ignite.stream.mqtt.MqttStreamer;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class PersistentStoreMQTTR2 {
    /** Organizations cache name. */
    private static final String ORG_CACHE = "PIR_MOTION_NEW_FILE";

    /** */


    /**
     * @param args Program arguments, ignored.
     * @throws Exception If failed.
     */
    public static void main(String[] args) throws Exception {
        Ignition.setClientMode(true);

        Ignite ignite = Ignition.start("examples/config/persistentstore/example-persistent-store.xml");
      //  Ignite ignite = Ignition.start("examples/config/example-ignite.xml");
        // Activate the cluster. Required to do if the persistent store is enabled because you might need
        // to wait while all the nodes, that store a subset of data on disk, join the cluster.
        ignite.active(true);


        //IgniteCache<String, String> cache = ignite.getOrCreateCache(cacheCfg);

        CacheConfiguration<String, Double> cacheCf = new CacheConfiguration<>(ORG_CACHE);
        cacheCf.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);  //only for key value
        cacheCf.setBackups(1);
        cacheCf.setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_SYNC); //ignite will wait for reply from all nodes
        cacheCf.setIndexedTypes(String.class, Double.class);
        //cacheCfg.setIndexedTypes(String.class, Double.class);
        IgniteCache<String, Double> cache = ignite.getOrCreateCache(cacheCf);
        //System.out.println("Populating the cache...");
         //cache mode can be additonally set

        IgniteDataStreamer<String, Double> streamerCache = ignite.dataStreamer(cache.getName());
        streamerCache.allowOverwrite(true);


        MqttStreamer<String, Double> streamer = new MqttStreamer<>();
        streamer.setIgnite(ignite);
        streamer.setStreamer(streamerCache);
        streamer.setBrokerUrl("tcp://localhost:1883");
        streamer.setBlockUntilConnected(true);

        streamer.setSingleTupleExtractor(new StreamSingleTupleExtractor<MqttMessage, String, Double>() {
            @Override public Map.Entry<String, Double> extract(MqttMessage msg) {
                List<String> s = Splitter.on(",").splitToList(new String(msg.getPayload()));
                //System.out.println(s);
                cache.put(s.get(0), Double.parseDouble(s.get(1)));


                return new GridMapEntry<>(s.get(0), Double.parseDouble(s.get(1)));
            }
        });



        streamer.setTopics(Arrays.asList("A", "B","C", "D", "E", "F", "G"));

        streamer.start();


       QueryCursor<List<?>> cur = cache.query(
               new SqlFieldsQuery("select count(_val) from Double where _key like '%16:48:50%'"));
//

       System.out.println("SQL Count Result : " + cur.getAll());









    }
}