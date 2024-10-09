# Custom OIDC Provider

<pre>
Assumptions
 A) You have Maven 3.6.3 installed      (the frontend-maven-plugin requires Maven 3.6.0 or greater)
    [see learnMaven / howToInstallMaven_3.6.3.OnCentOS.txt]

 B) You have Java 17 JDK installed
    [see learnJava / howToInstallJava_OpenJdk_OnCentos8.txt]


Procedures
 1. Startup your keycloak
    a. Create a realm
    b. Setup a custom OIDC provider

 2. Configure the application.yaml in this project so it has keycloak's oidc provider ID and client-secret

 3. Compile the Custom OIDC Provider
    terminal> mvn clean package

 4. Run the Custom OIDC Provider so it listens on https://localhost:9444/
    terminal> java  -Djavax.net.ssl.trustStorePassword=changeit  -Djavax.net.ssl.trustStore=backend/src/main/dev-resources/cacerts  -jar ./target/custom-oidc-provider-1.0-SNAPSHOT-exec.jar 

</pre>
