#!/bin/bash

# usage: ./sample.sh <graph-name> <number-of-edges> <SF>

cat $1-full/$1-edges.csv | head -n $2 > $1-$3-edges.csv
cat $1-$3-edges.csv | cut -f 1 > source-nodes.csv
cat $1-$3-edges.csv | cut -f 3 > target-nodes.csv
cat source-nodes.csv target-nodes.csv | sort -u > $1-$3-nodes.csv
rm source-nodes.csv target-nodes.csv

wc -l $1-$3-nodes.csv
wc -l $1-$3-edges.csv
