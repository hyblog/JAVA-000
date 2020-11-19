#! /bin/bash
### BEGIN INIT INFO
# Provides:
# Required-Start:
# Required-Stop:
# Default-Start:
# Default-Stop:
# Short-Description:
### END INIT INFO

FWDIR="$(cd "`dirname "$0"`"/..; pwd)"
PIDDIR="$(cd "`dirname "$0"`"/../..; pwd)"
#echo "base dir is $FWDIR"
LIB_DIR=${FWDIR}/libs
CONF_DIR=${FWDIR}/conf
APP_MAIN=com.ipman.work05sb2.Work05Sb2Application
APP_PARAMS=""
PID_FILE=${PIDDIR}/work05sb2.pid

JAVA_OPTS='-server'
# JAVA_OPTS=$JAVA_OPTS' ${java.opts}'
JAVA_OPTS=$JAVA_OPTS' -Dfile.encoding=UTF8 -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=3302'
JAVA_OPTS=$JAVA_OPTS' -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath='$FWDIR
#JAVA_OPTS=$JAVA_OPTS' -XX:SurvivorRatio=4 -XX:PermSize=56m -XX:MaxPermSize=128m -XX:+DisableExplicitGC -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:../logs/gc.log -XX:ErrorFile=../logs/hs_err_pid%p.log'
JAVA_OPTS=$JAVA_OPTS' -Xms2g -Xmx2g -Djava.net.preferIPv4Stack=true -XX:NewSize=1024m -XX:MaxNewSize=2048m -XX:+UseG1GC -XX:MaxGCPauseMillis=90 -XX:InitiatingHeapOccupancyPercent=70 -XX:ParallelGCThreads=8 -XX:ConcGCThreads=6 -XX:+PrintGCDetails -XX:+PrintReferenceGC -XX:+PrintGCDateStamps -XX:+PrintTenuringDistribution -XX:+PrintHeapAtGC -Xloggc:../logs/gc.log -XX:+HeapDumpOnOutOfMemoryError  -XX:ErrorFile=../logs/hs_err_pid%p.log'

CLASSPATH=""pid
JAVA_HOME=${JAVA_HOME:=/usr}
LOG_DIR=/Users/huangyan110110114/loclog/work05sb2
LOG_STD_OUT=${LOG_DIR}/stdout.log
LOG_STD_ERR=${LOG_DIR}/stderr.log

mkdir -p ${LOG_DIR}

do_start() {
	if [ -f ${PID_FILE} ];then
		pid=`cat ${PID_FILE}`
		if [ ! -z "$pid" -a -x /proc/$pid ];then
			echo "service is already runing with pid ${pid}"
			exit 0
		fi
	fi

	CLASSPATH=${CLASSPATH}:${CONF_DIR}
	for f in "${LIB_DIR}"/*.jar; do
		if [[ ! -e "$f" ]]; then
			echo "Failed to find jar in ${LIB_DIR}" 1>&2
			exit 1
		fi
		CLASSPATH=${CLASSPATH}:"$f"
	done
#	export CLASSPATH

#	JAVA_OPTS="-Duser.timezone=GMT+8 -server -Xms2048m -Xmx2048m -Xloggc:../logs/gc.log -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=19990 "
	echo "Starting service..."
	${JAVA_HOME}/bin/java -classpath $CLASSPATH ${JAVA_OPTS} ${APP_MAIN} ${APP_PARAMS} >> ${LOG_STD_OUT} 2>>${LOG_STD_ERR} &
	pid=$!
	sleep 5
	if [ -x /proc/$pid ];then
		echo $pid > ${PID_FILE}
		echo "Success start service with pid $pid"
		exit 0
	else
		echo "Failed start service log ${LOG_STD_ERR}"
		exit 1
	fi
}

do_stop() {
	if [ ! -f ${PID_FILE} ];then
		echo "Pid file ${PID_FILE} does not exist,service is not running"
	else
		pid=$(cat ${PID_FILE})
		if [ ! -z "$pid" -a -x /proc/${pid} ];then
			echo "Stopping service...,service pid is ${pid}"
			kill ${pid}
			sleep 3
			while [ -x /proc/$pid ];do
				echo "Waiting for service to shutdown..."
				sleep 1
			done
			echo ''>${PID_FILE}
			echo "Success stop service with pid ${pid}"
		else
			echo "Service is not running"
			echo ''>${PID_FILE}
		fi
	fi
}

case "$1" in
    start)
	    do_start
        ;;
    restart)
        echo "restart"
		do_stop
		do_start
        ;;
    stop)
		do_stop
        ;;
    *)
        echo "Usage: $0 start|restart|stop"
        exit 3
        ;;
esac
