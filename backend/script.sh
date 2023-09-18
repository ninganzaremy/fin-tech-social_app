#!/bin/bash

# Run Maven commands
mvn clean
mvn install
mvn dependency:copy-dependencies

echo "Maven installation complete."

# Check if lib directory exists, if so, delete existing JARs
if [ -d "lib" ]; then
  echo "Deleting existing JARs."
  rm lib/*.jar
else
  # If lib directory doesn't exist, create it
  mkdir lib
fi

# Copy JAR files from target/dependency to lib
echo "Copying dependencies to lib."
cp target/dependency/*.jar lib/

echo "Begin cleanup."
# Remove the target directory
rm -rf target
echo "Cleanup complete."