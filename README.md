# Rubiks Solver

A Java project to help a user solve a Rubik's cube. The program assumes that you are familiar with Rubik's Cube Notation (https://ruwix.com/the-rubiks-cube/notation/).

**Please note: This project is still in development, and will be improved over time.**

## Usage
Compile with:
```
javac -d bin src/*.java
```

Run with:
```
java -cp bin Solver
```

You will be prompted to enter the colours of the cells on the Rubik's Cube you want to solve. Each colour is represented by a single character, as such:
- Red = 'R'
- Blue = 'B'
- Green = 'G'
- Orange = 'O'
- Yellow = 'Y'
- White = 'W'

First, enter the cell colours of any face of the Rubik's Cube one by one, going from left-to-right then top-to-bottom.
You will then repeat this process for the other sides of the cube by rotating it. It is important to rotate the cube in a particular way, in order to ensure the orientation is accurate. The order of the sides are as follows:
- Front
- Left (y' rotation from Front)
- Right (y rotation from Front)
- Back (y2 rotation from Front)
- Top (x' rotation from Front)
- Bottom (x rotation from Front)

After inputting all of the cell colours, the program will then attempt to solve the Rubik's Cube by applying the beginner's algorithm (https://ruwix.com/the-rubiks-cube/how-to-solve-the-rubiks-cube-beginners-method/).
It should be noted that the program only works if the cube is in a valid state at the start.
