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












