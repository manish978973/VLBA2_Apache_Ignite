import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;

//referred from ignite examples
//example of Ignite datagrid applications

public class ReadWrite {

    public static void main(String[] args) throws IgniteException {
        try (Ignite ignite = Ignition.start("examples/config/example-ignite.xml")) {
            CacheConfiguration<Integer,String> cfg = new CacheConfiguration<>("webinar");
             cfg.setBackups(1);
              IgniteCache<Integer,String> cache = ignite.getOrCreateCache(cfg);

             int cnt =10;

            // for(int i = 0;i < cnt; i++)
            //{
             //    cache.put(i,Integer.toString(i));
             //}


             for(int i = 0;i < cnt; i++)
              {
               System.out.println("Got" + i + "=" + cache.get(i));
              }
        }


    }
    }




