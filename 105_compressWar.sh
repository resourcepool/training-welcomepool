#!/bin/sh
mkdir -p out/war/WEB-INF
cp -R out/classes out/war/WEB-INF
cp -R lib/ out/war/WEB-INF/lib
cp -R src/main/webapp/* out/war/
jar -cvf out/war/Pool.war  -C  out/war/ .
