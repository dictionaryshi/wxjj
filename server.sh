#!/bin/sh

log_file=~/boot/monitor.log
server_log_file=~/boot/server.log
server_name="wxjj-1.0.0-SNAPSHOT.jar"
server_test_url="http://127.0.0.1:9000/monitor/alive"
server_start="-server -Xms256m -Xmx256m -Xmn128m -Xss256k -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m \
-XX:+AlwaysPreTouch -XX:+DisableExplicitGC ${server_name}"

function monitor() {
  processId=$(getProcessId)
  echo "${server_name} 进程id=> ${processId}"
  if [[ ${processId} ]]; then
    echo "test url=> ${server_test_url}"
    http_code=$(curl -s -o /dev/null -m 5 --connect-timeout 5 ${server_test_url} -w "%{http_code}")
    if [ ${http_code} -eq 200 ]; then
      echo -e "\e[1;32m ${server_name} 服务正常 \e[0m"
    else
      echo -e "\e[1;31m ${server_name} 宕机, 开始重启... \e[0m"
    fi
  else
    echo -e "\e[1;31m ${server_name} 进程id不存在, 开始启动服务... \e[0m"
    nohup java -jar ${server_start} > ${server_log_file} 2>&1 &
    echo -e "\e[1;31m ${server_name} 启动完毕 \e[0m"
    sleep 60
  fi
}

function getProcessId() {
    processId=$(jps -l | grep ${server_name} | awk '{print $1}')
    echo ${processId}
}

while true
do
  monitor>>${log_file}
  sleep 10
done

