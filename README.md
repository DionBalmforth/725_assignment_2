# 725_Assignment_2

## About Project
- This project aims to improve the implementation of a rudimentary baggage handling system (BMS). The main objective is to prevent the collision of bags using appropriate mutal exclusion algorithms. The algorithms used include central server, ring token and multicasting.

## Usage
- Unzip the folder
- Navigate into the folder labelled fbdk
- If on windows 
    - double click the editor.jar or editor.bat
- If on linux or mac
    - open terminal in the fbdk folder and then run "java -jar editor.jar"
- Accept and enter the program
- Click the folder in the top left and select the BaggageSystemCtl from the src folder
- Click the green play button
    - if any errors occur then recompile EVERYTHING, quit the program and then re-enter


## Notes
- Do not add more then ~15 bags at once
- Do not add bags to the system within 2 seconds of each other
- If BaggageSystemCtl from src does not run
    - move the BaggageSystemCTL.sys from the cs725 folder to src folder

## Known Bugs
- Sometimes the system seems to skip ticks and places bags on the conveyor when it should not
- If the system has been overloaded, conveyor 9 can somtimes stop moving bags while still running, this requires exit and entering fdbk again 
