#!/bin/bash
#echo off
export MYCLASSPATH=./properties
for i in `ls ./lib/*.jar`
do 
	export MYCLASSPATH=${MYCLASSPATH}:${i}
done
echo ${MYCLASSPATH}
nohup java -classpath ${MYCLASSPATH} mqp.anji.MarioEvolver mario.properties &
