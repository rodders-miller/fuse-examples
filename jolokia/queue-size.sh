#!/bin/bash
 
# This bash script uses a cURL command to call the Jolokia interface of the Fuse ActiveMQ Broker to obtain the 
# depth of the queue.   The host name and http port of the Fuse container,   broker name at that container and 
# the queue name to be monitored are all take as parameters to the script 
# note that the user name and password for the Jolokia interface is assumed to be admin admin
 
# Check that the input queue name has been supplied
if [ -z "$1" ] 
  then
    echo "Insufficient Arguments Supplied - Queue Name Required"
	exit 1
fi

HOST_AND_PORT='localhost:9090'
BROKER_NAME='broker'
QUEUE_NAME=$1
echo "Reading queue depth from host and port ${HOST_AND_PORT} broker ${BROKER_NAME} and ${QUEUE_NAME}"

#Note base address for standalone containers has 'hawtio' included as Jolkoia is installed underneath 
# For Fabric containers jolokia is a separate service at the root address
# "http://localhost:8181/hawtio/jolokia/" vs "http://localhost:8181/jolokia/"
BASE_JOLIKIA_POST_ADDR="http://${HOST_AND_PORT}/jolokia/"

#  The message is a Jolokia command that instrcuts the read of the queue size.  details of the interface as described here https://confluence.ipttools.info/pages/viewpage.action?pageId=33489616
MESSAGE_TO_POST="{\"type\":\"read\",\"mbean\":\"org.apache.activemq:type=Broker,brokerName=${BROKER_NAME},destinationType=Queue,destinationName=${QUEUE_NAME}\",\"attribute\":\"QueueSize\"}"

# The queue depth is extracted from the curl command using gawk to extract the value from the returned JSON (e.g. value:=2,) 
# gawk is used to capture the regex value within the capture ( ) via the gawk match function
QUEUE_DEPTH=$(curl --silent --user admin:admin $BASE_JOLIKIA_POST_ADDR -v -XPOST -d $MESSAGE_TO_POST  | gawk 'match($0, /"value":([0-9]*/, m) { print m[1]; }')