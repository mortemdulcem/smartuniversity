#!/bin/bash
# Run script for Smart University Management System (Java CLI)
# Ankara Yildirim Beyazit Universitesi
# BBS 515 - Nesne Yonelimli Programlama

# Change to the script directory
cd "$(dirname "$0")"

# Check if compiled
if [ ! -d "out" ]; then
    echo " [HATA] Derlenmemis! Once ./build.sh calistirin."
    echo " [ERROR] Not compiled! Run ./build.sh first."
    exit 1
fi

# Run the application
java -cp out -Dfile.encoding=UTF-8 university.Main
