#!/bin/bash
# This script will build the project.

echo -e 'bintrayUser ['$bintrayUser']'
if [ "$TRAVIS_PULL_REQUEST" != "false" ]; then
  echo -e "Build Pull Request #$TRAVIS_PULL_REQUEST => Branch [$TRAVIS_BRANCH]"
  ./gradlew :views:build :views:jacocoTestReport
elif [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_TAG" == "" ]; then
  echo -e 'Build Branch with Snapshot => Branch ['$TRAVIS_BRANCH']'
  ./gradlew :views:build :views:jacocoTestReport --stacktrace --info
elif [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_TAG" != "" ]; then
  echo -e 'Build Branch for Release => Branch ['$TRAVIS_BRANCH']  Tag ['$TRAVIS_TAG']'
  ./gradlew -PbintrayUser="${bintrayUser}" -PbintrayKey="${bintrayKey}" :views:build :views:jacocoTestReport :views:bintrayUpload --stacktrace --info
else
  echo -e 'WARN: Should not be here => Branch ['$TRAVIS_BRANCH']  Tag ['$TRAVIS_TAG']  Pull Request ['$TRAVIS_PULL_REQUEST']'
  ./gradlew :views:build :views:jacocoTestReport
fi

./gradlew sonarqube \
  -Dsonar.projectKey=guhungry_android-customviews \
  -Dsonar.projectName=android-customviews \
  -Dsonar.organization=guhungry-github \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.login=$sonarqubeKey