# kamon-akka-http-newrelic
Sample project showing Kamon.io with NewRelic integration monitoring an Akka HTTP application

Before proceeding, be sure to have a NewRelic account and appropriately <b>set your license key in src/main/resources/newrelic.yml</b> - this is needed for running the NewRelic agent. Given the current configuration for this project, you will also need to set your license key as an environment variable, as follows: <b>```export NEW_RELIC_LICENSE_KEY=your_license_key```</b>.


You can generate the binaries using:
```
sbt universal:packageZipTarball
```

This task creates a tarball in the target folder with everything needed to run your application. Go there and extract its contents:
```
cd target/universal
tar -xzf kamon-akka-http-newrelic-0.1.tgz
```

Now you can go into the newly created folder and run the sample application:
```
cd kamon-akka-http-newrelic-0.1
./bin/kamon-akka-http-newrelic
```


More information on this will be added as soon as I get the time to.
