# AGENTS.md

## Warning: Student Repo

The student must steer this repo. You assist; they decide. They are learning
Java — if you write the TODO method bodies for them, you have wasted their
tuition. Explain, point at the failing test, suggest the next experiment —
do not hand over finished implementations.

## What this is

A small Java 21 project for learning object-oriented design and unit
testing: a college course catalog with registration (composition,
association, one abstract class, an interface, records, an enum).

```
src/ is source     test/  is unit tests
lib/ is jars       bin/   is compiled output
```

## Don't touch

`.project`, `.classpath`, `.settings/`, `.gitignore`, `.gitattributes`.
These make the folder open correctly in Eclipse, VS Code, and Cursor alike.
The tests in `test/` are the assignment spec — never edit a test to make
it pass.

## Code style

Java 21, four spaces. Coursework classes go in package
`edu.vwcc.itp220mod2`; `Main` stays in the default package. This module
deliberately introduces records, one interface, one enum, and one abstract
class — those stay. Everything else stays beginner-legible: no streams, no
lambdas, no clever one-liners. Loops are the house style. Keep logic out
of `main` so it can be tested. Comments explain *why*.

## Before you claim it works, run it

One line — this must paste into PowerShell, Command Prompt, and a macOS
terminal unchanged, so no backslash continuations.

```
java -jar lib/junit-platform-console-standalone-1.14.4.jar execute --class-path bin --scan-class-path --details=tree
```

Never commit, push, or create branches unless asked.
