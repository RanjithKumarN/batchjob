#!/bin/bash

# ----------------------------
# Compile and Run Java Program
# ----------------------------

# Set Java file names
MAIN_CLASS="InsertTransactionApp"
DEPENDENCIES="ojdbc11.jar"
SRC_FILES="InsertTransactionApp.java TransactionDO.java"

# Step 1: Compile Java files
echo "Compiling..."
javac -cp $DEPENDENCIES $SRC_FILES
if [ $? -ne 0 ]; then
  echo "Compilation failed"
  exit 1
fi

# Step 2: Run the app
echo "Running the program..."
java -cp ".:$DEPENDENCIES" $MAIN_CLASS
