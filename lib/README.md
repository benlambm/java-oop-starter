# Libraries

`.jar` files the project compiles and runs against.

Already here:

- `junit-platform-console-standalone-1.14.4.jar` — JUnit 5 (Jupiter 5.14).
  One self-contained JAR that provides both the test API you write against
  and the runner that executes tests. Eclipse, VS Code, and the terminal all
  use this same file.

To add another library:

1. Copy the `.jar` into this folder.
2. In Eclipse: **Project → Properties → Java Build Path → Libraries →
   Classpath → Add JARs…** and pick it from inside this project.
3. Commit both the JAR and the changed `.classpath`.

Always use the project-relative path (`lib/thing.jar`). An absolute path like
`C:\Users\you\Downloads\thing.jar` works only on your computer and will break
the build for everyone else.
