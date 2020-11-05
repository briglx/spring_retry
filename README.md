# Spring Retry with Mongo

Example code to show how to impelment spring retry with Mongo.

The code uses Spring Data with Mongo and the ShedLock library


# Dev Setup

Follow instructions setting up VSCode with docker.

Debian GNU/Linux 10

Check version of JDK and maven with ```mvn -v```

Create MongoDB on Ubuntu 18.04 VM

Allow remote connections

Connect to remote mongo "mongodb://<replace_with_server_name_or_ip>:27017" 

Create an `application.properties` file based on `applicaiton.properties.example`.
Replace the mongo host name.

# Time Out Exception

Recreate `Java.net.SocketTimeoutException: connect timed out` by setting vnet rule to deny mongo traffic

This exception is called:  `com.mongodb.MongoSocketOpenException`

# Enable Disable network port on VM

```bash

# lookup process and port
sudo netstat -lnp

sudo ufw allow 27017

sudo ufw deny 27017

```

# References
- Developing inside a container https://code.visualstudio.com/docs/remote/containers
- https://spring.io/guides/gs/accessing-data-jpa/
- Install Mongo https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/
- Spring Retry https://github.com/spring-projects/spring-retry
- Guide to Spring retry https://www.baeldung.com/spring-retry
- https://github.com/lukas-krecan/ShedLock
