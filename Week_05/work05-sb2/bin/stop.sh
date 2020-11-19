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

JAVA_HOME=${JAVA_HOME:=/usr}
LOG_DIR=/Users/huangyan110110114/loclog/work05sb2
LOG_STD_OUT=${LOG_DIR}/stdout.log
LOG_STD_ERR=${LOG_DIR}/stderr.log

mkdir -p ${LOG_DIR}

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