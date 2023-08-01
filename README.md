# vts
Vessel Traffic Service

-Ship Management Service (only backend)
-Voyage Management Service (only backend)
-Docker compose file to deploy Kafka client, kafka zookeeper, and a Mongodb database

To Run:

- Run composed containers in compose.yml through an ide or by $ docker compose up -d command to run in detached mode.
- Run spring boot appliacations sms and vms together.

Ports:
- localhost:8080 SMS port
- localhost:8081 VMS port
- localhost:27017 MongoDB database port
- localhost:29092 Kafka Client
