#!/bin/bash
# Build script for Smart University Management System (Java CLI)
# Ankara Yildirim Beyazit Universitesi
# BBS 515 - Nesne Yonelimli Programlama

echo "================================================"
echo "  Smart University System - Java CLI Builder"
echo "================================================"
echo ""

# Change to the script directory
cd "$(dirname "$0")"

# Create output directory
mkdir -p out

# Compile all Java files
echo " Derleniyor / Compiling..."
javac -encoding UTF-8 -d out src/university/model/*.java src/university/util/*.java src/university/service/*.java src/university/Main.java

if [ $? -eq 0 ]; then
    echo " [OK] Derleme basarili / Compilation successful!"
    echo ""
    echo " Calistirmak icin / To run:"
    echo "   ./run.sh"
    echo ""
else
    echo " [HATA] Derleme hatasi / Compilation failed!"
    exit 1
fi
