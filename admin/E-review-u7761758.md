## Code Review

Reviewed by: Simon Liu, u7761758

Reviewing code written by: Thomas Downes, u7568037

Component: Reviewing FireTile class

### Comments 

Best Features:

* The use of helper methods such as `TileBuilder` to break down problem into smaller parts.
* The use of 2D arrays to represent the board.
* The `rotate` method is a good way to rotate the board.

Documentation:

* The code lacks comments to explain the purpose, which makes it a bit hard to understand.

Conventions:

* The methods are named appropriately.
* The code is consistently indented.

Suggestions:

* Some arguments in the methods like `loc` and `dim` is never used, maybe consider removing them.
* The `rotate` method could be simplified, it currently checks for `direction == WEST` twice in the same if condition.