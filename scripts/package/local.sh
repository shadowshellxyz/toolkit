#!/usr/bin/env bash

cd ../..
mvn clean -Plocal package install -Dmaven.test.skip=true