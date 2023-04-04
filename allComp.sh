#!/bin/sh
rm /home/takima/Documents/wildfly-26.1.3.Final/standalone/deployments/Pool.war
sh 100_compile.sh
sh 105_compressWar.sh
sh 120_deploy.sh
sh 200_start.sh
