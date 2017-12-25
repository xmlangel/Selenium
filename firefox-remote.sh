#!/bin/bash
REMOTE_IP=$1
REMOTE_PORT=$2

run(){
 	mvn clean install -Dremote=true -Dbrowser=firefox -DgridURL=http://${REMOTE_IP}:${REMOTE_PORT}/wd/hub
}

usage(){
        echo "-------------------------------------------------------------------------"
    echo "[ Usage ] $0 [REMOTE_IP] [REMOTE_PORT]"

    echo "-------------------------------------------------------------------------"
}

if [ $# -eq 2 ]
        then
                run
        else
                usage
                exit
        fi
