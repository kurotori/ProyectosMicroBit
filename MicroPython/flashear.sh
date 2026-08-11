#!/usr/bin/bash

for item in /dev/ttyACM*; do
    uflash "$1" $item
done