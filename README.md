# Basic-RMIPOC
Proof of Concept of RMI architecture

This code was extracted from Head First Java book - Kathy Sierra & Bert Bates - O'Reilly - Alta Books - 2ª edition.

#Instructions

->> Running the server application in a different machine

1) Go to your classes directory and start the rmiregistry in the server machine:

RMIProj/classes$ rmiregistry

2) In a different terminal run the server application:

RMIProj/classes$ java -Djava.rmi.server.hostname=10.146.93.108 ServiceServerImpl
