FROM maven:3.6.2-jdk-8
WORKDIR /atm
RUN wget https://github.com/ThiagoMiranda27/ibm-atm-challenge/archive/master.zip 
RUN unzip master.zip
COPY . .
RUN mvn clean
RUN mvn compile
CMD ["java", "-cp", "target/ibm-atm-challenge-1.0.jar", "com.ibm.challenge.Application"]