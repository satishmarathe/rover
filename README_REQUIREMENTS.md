# Problem 1 - Mars Rover

A robotic rover is going to be landed by NASA on a plateau on Mars. This plateau, which is `rectangular`, must be navigated by the rover so that its on-board cameras can get a complete
and accurate view of the surrounding terrain to send back to Earth.

A rover's position and location is represented by a combination of `x` and `y` co-ordinates and a letter representing one of the four cardinal compass points.
The plateau is divided up into a grid to simplify navigation. An example position might be `0, 0, N`, which means the rover is in the bottom left corner and facing `North`.

In order to control a rover, NASA sends a simple string of letters. The possible letters are `L`, `R` and `M`. `L` and `R` makes the rover spin 90 degrees left or right respectively,
without moving from its current spot. `M` means move forward one grid point, and maintain the same heading.

Assume that the square directly `North` from `(x, y)` is `(x, y + 1)`.

The plateau high view configuration is specified in an external file in the following format:

```
ooooooRRRR
ooRooooooo
ooooooRRoo
ooRooooooo
oooooRoooo
oooooRRRoo
```

The file will be called `plateau.txt` and can be assumed to be available in the working directory of the application.

`o` means the land is plain and the rover can navigate through it, while `R` means the high view images showed there's a rock
and the rover must go around it. Each row must have the same number of characters. The lower-left coordinates are assumed to be `0, 0`.

When the application starts it reads the plateau configuration and prints it on the screen, then it waits for user inputs:

```
Mars Rover v1.0 running, plateau configuration is:

XoooooRRRR
ooRooooooo
ooooooRRoo
ooRooooooo
oooooRoooo
oooooRRRoo

Waiting for commands.
>
```

When the user enters one of the commands (`L`, `R` or `M`) the application prints out the updated plateau using `X` to
mark the current rover position.

The initial position of the rover is considered the top-left corner with the rover facing East. For example, if we send `M`:

```
XoooooRRRR
ooRooooooo
ooooooRRoo
ooRooooooo
oooooRoooo
oooooRRRoo

Waiting for commands.
> M
```

We get:

```
oXooooRRRR
ooRooooooo
ooooooRRoo
ooRooooooo
oooooRoooo
oooooRRRoo

Waiting for commands.
> 
```

We can also send more than one command at a time, for example if we send:

```
XoooooRRRR
ooRooooooo
ooooooRRoo
ooRooooooo
oooooRoooo
oooooRRRoo

Waiting for commands.
> MMMRM
```

We get:

```
ooooooRRRR
ooRXoooooo
ooooooRRoo
ooRooooooo
oooooRoooo
oooooRRRoo

Waiting for commands.
>
```

By typing `x` or `X` the user can close the simulation.

```
ooooooRRRR
ooRXoooooo
ooooooRRoo
ooRooooooo
oooooRoooo
oooooRRRoo

Waiting for commands.
> x

Sent 5 command(s) / 0 failed.

Mars Rover v1.0 closed.
```

When the user closes the simulation the total number of commands and the number of failed commands is printed. A command
is considered `failed` if we asked the rover to move through a rock or outside the boundaries of the plateau.

Please use a build tool like Maven or Gradle and Git. Do not include any external library apart from JUnit. The application
will be judged based on the following criteria:

* Code structure and design
* Readability and maintainability
* Use of tests
* Code performs to the specifications provided