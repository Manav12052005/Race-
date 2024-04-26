## Code Review

Reviewed by: Thomas Downes, u7568037

Reviewing code written by: Manav Singh, u7782612

Component: Reviewing Challenge class

### Comments 

Best Features:

- Well-encapsulated data with private members and getter methods.
- Using an enum for The ChallengeId is a good way to organise the various challenge IDs.

Documentation:

- Perhaps needs a comment at the start to explain the purpose of the Challenge class.
- Method comments could go into more depth on how the methods function.

Conventions:

- Variables and methods named appropriately.
- Proper indentation maintained.

Code improvement suggestions:
- Incorporate an error handler in the constructor to manage invalid string inputs maybe?
- Maybe create a different implementation of getDifficultyFromChallengeId, might be able to use a map?


