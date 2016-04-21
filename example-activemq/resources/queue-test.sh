 #!/bin/bash 
 
 # check if an input dir is supplied, if not assume one
 if [ -z "$1" ]; then
    OUTPUT_DIR="d:/fuse62/jboss-fuse-pre-patched-6.2.0.redhat-143-p1/work/jms/input/"
	echo "No output dir supplied as argument supplied, using default $OUTPUT_DIR"
else 
	OUTPUT_DIR=$1
	echo "Output dir is $OUTPUT_DIR"
fi
 
 
 COUNTER=0
 while [  $COUNTER -lt 1 ]; do
	 echo The counter is $COUNTER
	 cp ./example.xml d:/fuse62/jboss-fuse-pre-patched-6.2.0.redhat-143-p1/input/"example$COUNTER".xml
	 
	 let COUNTER=COUNTER+1 
	 
 done
 