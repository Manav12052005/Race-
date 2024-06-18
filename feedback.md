---
mark: 18.25

section_marks:
  c1-compliance: 0.25
  c10-comments: 0.75
  c11-tests: 1
  c12-tasks5-6-7-8-9: 4
  c13-tasks10-12-14-15: 2.5
  c14-exceptional: 0
  c2-authorship: 0.5
  c3-git: 1
  c4-jar: 1
  c5-basic-playable: 4
  c6-full-playable: 0.5
  c7-strings-to-objects: 1
  c8-oop: 1
  c9-design: 0.75
---

Placing a fire tile on the right edges crashes the app with an index out of bounds error.
Placing fire tiles/pathway cards will sometimes reset some cats back to an earlier position (or vanish them).

Otherwise, well done!

Deductions:

c6-full-playable:
  * -0.25 fire tile placement rules not enforced
  * -0.25 path card placement rules not enforced
  * -0.25 cat exhaustion isn't implemented
  * -0.25 card drawing shows cards before all 6 are drawn.
  * -0.25 game cannot be lost
  * -0.25 game cannot be won (can't test as cats jump back).
  * -0.5 not all game rules enforced

c9-design:
  * there is little seperation between UI and backend classes.



