#!/bin/sh
sbt clean update universal:packageZipTarball
docker build -t ruimo/jobbrokersmokeworker .
