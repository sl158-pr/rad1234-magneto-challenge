rad1234-magneto-challenge
=========================
Hello,

Instructions to run on the Tomcat 6: 
Provided the war file: rad1234.war which can be deployed through Tomcat management and also using the command: mvn clean tomcat:run

Instructions to run using jetty (Assuming maven 3 is installed) 
Please use: mvn compile jetty:run 
Application then runs on http://localhost:8080/rad1234

Bonus tasks attempted

1) Committed the webapp into github repository: https://github.com/sumanthl158/rad1234-magneto-challenge
2) Hooked up github code with Travis-CI for builds and it compiles, builds.
Travis URL: https://travis-ci.org/sumanthl158/rad1234-magneto-challenge
3) Deployed the webApp instance on the AWS free account through build scripts. 
AWS URL: http://ec2-54-187-24-41.us-west-2.compute.amazonaws.com:8080/rad1234
