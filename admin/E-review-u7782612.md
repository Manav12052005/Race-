## Code Review

Reviewed by: Manav Singh, u7782612

Reviewing code written by: Simon Liu u7761758

Component: Reviewing Square Class

### Comments 

Best Features:
- The use of enum makes for a clear way to represent various types of squares.
- The class encompasses/encapsulates both the data (position, type, image) and behavior (methods: getters for values) related to a square.

Documentation:

- The code should have comments for methods to explain the function of the code snippet which it is lacking.

Conventions:
- The code is properly structured with appropriate indentations.
- Variables and methods are aptly named to indicate their use.

Code improvement suggestions:
- Using constants for the image file path, to make the code more readable, a constructor file that uses constants instead of hardcoding the file path url may be more appropriate here.
- Handling errors when loading image files can be useful in displaying error images and pinpointing the position of the error




