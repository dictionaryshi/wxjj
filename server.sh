#!/bin/sh

log_file=~/boot/monitor.log
server_log_file=~/boot/server.log
server_name="wxjj-1.0.0-SNAPSHOT.jar"
server_test_url="http://127.0.0.1:9000/monitor/alive"
server_start="-server -Xms256m -Xmx256m -Xmn128m -Xss256k -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m \
-XX:+AlwaysPreTouch -XX:+DisableExplicitGC ${server_name}"

function monitor() {
}

while true
do
  monitor>>${log_file}
  sleep 2
done

