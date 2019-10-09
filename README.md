### MAP REDUCE AND IoT DATABASES/IN_MEMORY COMPUTING POWERED BY APACHE IGNITE

Apache Ignite is a memory centric distributed database, caching, and processing platform that is durable strongly consistent and highly available with powerful SQL, key-value and processing APIs.

<Image src="Images/ApacheIntro.png" class="center" style="width:50%">
  
  
 ## SETTING UP THE JAVA and LINUX ENVIRONMENT 

This project was developed using IntelliJ IDEA integrated environment and in Ubuntu.Follow the [link ](https://www.jetbrains.com/help/idea/installation-guide.html) to set up the Java IDE.


## IGNITE-GETTING STARTED AND INSTALLING

* Download the Ignite Binary distribution as a ZIP archive from [link ](https://ignite.apache.org/) 
* Unzip the ZIP archive into the installation folder in your system.
* Set IGNITE_HOME environment variable/PATH to point to the installation folder and make sure there is no trailing in the path.
* To start a Ignite cluster node with default configuration, open the command shell and assuming you are in IGNITE_HOME, just type

`$ bin/ignite.sh`

* By default ignite.sh starts a node with the default configuration which is config/default-config.xml

* To pass a custom configuration file, pass the file as a parameter to **ignite.sh/bat** as follows :

`$ bin/ignite.sh customconfig.xml`

## HOOKING UP TO MAVEN

* Ignite requires only one **ignite-core** mandatory dependency.
* Usually, you will also need to add **ignite-spring** for spring-based XML configuration and **ignite-indexing** for SQL querying.
* Replace ${ignite-version} with the actual Ignite version.


### MEMORY CENTRIC STORAGE IN IGNITE

* In-memory processing coupled together with the disk durability and strong consistency
* Functioning as a memory centric system with native persistence switched on.
* By default persistence is turned off and Ignite functions as a In-Memory database
* Ignite provides strong ACID durability guarantee to the data.

<Image src="Images/ignitemem1.png" class="center" style="width:50%"> <Image src="Images/Ignitemem2.png" class="center" style="width:50%">

#### FEATURES OF IGNITE MEMORY CENTRIC STORAGE

##### <ins>In Memory Mode</ins>

* In-Memory - The whole dataset is stored in memory and processed. Backup copies are taken care to handle node failure.RAM is always treated as the first memory tier.
* Multiple API support as a part of Data processing, streaming and Tool integration.

##### <ins>Persistance Mode</ins>

* Data Resiliency – survive node crash as it sores data on disk
* Instantenous cluster restarts
* Superset of data stored in persistent disk and retrieved to main memory during processing hence increasing the performance.


#### INTEGRATION OF IGNITE WITH 3rd PARTY DATABASES AND VISUALIZATION TOOLS

* Apache Ignite can be used to accelerate existing applications running with legacy database management systems.
* Ignite can be employed as a In-memory caching layer over the existing persistent database like RDBMS ,Apache Cassandra and Mongo DB.
* Ignite provides APIs like CacheLoader and CacheWriter to connect to underlying SQL and NOSQL databases.

<Image src="Images/hbase.png" class="center" style="width:50%"><Image src="Images/cassandra.png" class="center" style="width:50%"><Image src="Images/sqlser.jpg" class="center" style="width:50%"><Image src="Images/zep.jpg" class="center" style="width:50%">
  
  

### DISTRIBUTED SQL IN IGNITE

* SQL  ACID Transactions
* Automatic Deadlock management when multiple transactions run.
* SQL and In-Memory Mode -- Apache Ignite can function in a pure in-memory mode in which all data and indexes are stored entirely in RAM. 
* SQL and Native Persistence -- In this mode, Ignite  serves as the primary storage that can survive cluster failures and restarts. On cluster restart, Ignite reads data and indexes from disk eliminating the need for memory warm-up. 
* Apache Ignite JDBC and ODBC drivers is used to connect to the cluster and process data stored there from a SQL tool like Dbeaver.
* The data in Ignite Cluster can be connected to 3rd party Visualization tools like **Zeppelin** and **Tableau** for further data analysis.


### DISTRIBUTED KEY_VALUE IN IGNITE

* The in-memory data grid component in Ignite is a fully transactional distributed key-value store that can scale upto 100s of servers in the cluster.
* Ignite portrays as a partitioned hash map with every cluster node owning a portion of the overall data set.
* Ignite key-value APIs support.
* In-Memory Key Value Store.
* Basic Cache operations.
* Pluggable Persistance (Native Disk or 3rd party database)


### COLLOCATED PROCESSING IN IGNITE

* Collocated processing is an add on feature over conventional Client-Server approach.
* Computations are carried over the server side rather than client side bringing down the latency and processing time.
* Advanced SQL queries and JOINS have been successful with this approach.

<Image src="Images/col.png" class="center" style="width:50%">
  
 ## FUNCTIONALITIES AND VIVID SERVICES WITH APACHE IGNITE
 
 <Image src="Images/igfea.png" class="center" style="width:50%">
  
  
  ### CLUSTERING IN IGNITE
  
* Ignite is an customizable horizontally scalable distributed system that endorses adding and removing cluster nodes on demand.
* Ignite cluster can be started with different configuration files . It is based on the configuration file that persistence is enabled or disabled.
* Each ignite node = Each JAVA Virtual Machine
* Customising Ignite nodes - Ignite has an optional notion of client and server nodes. Server nodes participate in caching, compute execution, stream processing, etc., while the native client nodes provide the ability to connect to the servers remotely. By default the ignite node starts as server unless specified.
* While creating a cache Ignite make sure that it deploys a distributed cache on all server nodes.
* Ignite nodes can automatically discover each other.(TCP/IP and Zookeeper Discovery) This helps to scale the cluster when needed, without having to restart the whole cluster.
* Ignite provides various Cluster APIs :

     `IgniteCluster which is an API Interface that’s used to start and stop Cluster.`
     
* Cluster groups can also  be customised to make it Remote, Cache , Client/Server.



 ### DATAGRID IN IGNITE

* Ignite data grid is an in-memory distributed key-value store with every cluster node owning a portion of the processing data.
* A plugging hashing algorithm is employed which makes a client to discover the appropriate node with the suitable key unlike the mapping server tagging.
* As long as cluster is alive Ignite will guarantee that the data between different cluster nodes will always remain consistent regardless 
* Ignite APIs for Data grid :
   `IgniteCache  -- which is employed to initialize a cache in newly deployed node.`
   `DynamicCache – which detects newly joined nodes and binds them.`

  
  









