
import paho.mqtt.client as mqtt #import the client1

import datetime
import random
now= datetime.datetime.now()


broker_address="localhost" 
#broker_address="iot.eclipse.org" #use external broker
client = mqtt.Client("P1") #create new instance
client.connect(broker_address) #connect to broker

for x in "ABCDEFG":
    now= datetime.datetime.now()
    for i in range(1,10000):
        y = random.randint(5, 500) * 0.01
        date_time = now.strftime("%m/%d/%Y %H:%M:%S")
        client.publish(x, date_time +":"+x+ "," +str( y))#publish
        now += datetime.timedelta(0,1)
