kill -9 `ps -ef|grep "SMAX-restful"|grep -v "grep"|awk '{print $2} '`
sleep 2 
echo "begin to start SMAX Webhook"
nohup java -jar SMAX-restful.jar &
echo "SMAX Webhook Started"