java -jar /home/elf/Downloads/payara-micro-5.2020.7.jar --postbootcommandfile src/config/post-boot.conf --deploy target/persistent-service-1.0.0-SNAPSHOT.war:ps --port 8080 --sslPort 8181 --addlibs src/config/postgresql-42.2.18.jar --outputUberJar target/ps.jar