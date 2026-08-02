# Java OOP Starter

Module 2 of the same series as
[java-git-starter](https://github.com/benlambm/java-git-starter). Same
tooling, same workflow — but where module 1 was six unrelated methods,
this is **one connected object model**: a small college course catalog
with registration, built from nine classes that have to cooperate.

The same clone opens as a real Java project in **Eclipse**, **VS Code**, or
**Cursor**, on **Windows** or **macOS**, with no conversion step.

## What you need

- **Git**
- **JDK 21 or newer** (Temurin / Adoptium is the usual free build)
- One of:
  - Eclipse IDE for Java Developers (2023-12 or newer)
  - VS Code or Cursor, with the recommended **Extension Pack for Java**

Setup is identical to module 1 — if that project opens on your machine,
this one will too. Clone URL:

```text
https://github.com/benlambm/java-oop-starter.git
```

Clone it from inside your IDE exactly the way you did for module 1
(VS Code / Cursor: **Clone Repository** on the Welcome screen. Eclipse:
**File → New → Project… → Git → Projects from Git**, then **Clone URI**).

## The model

You are building the object model behind a college registration system —
a system you are personally a user of.

| Class | What it is | The idea it teaches |
| --- | --- | --- |
| `Course` | Catalog entry: "ITP 220, 4 credits" | **Composition** — Courses create and own Sections |
| `Section` | One offering: CRN, seats, times | Derived state, encapsulated collections |
| `MeetingTime` | "MWF 10:00–10:50" | **Record** (value object) |
| `Modality` | How a section meets | **Interface** + polymorphism |
| `InPerson` / `VirtualSync` / `OnlineAsync` | The three ways | Records implementing an interface |
| `Person` | Shared part of people | **Abstract class**, inheritance |
| `Student` / `Instructor` | The people | `extends`, `super`, overriding |
| `Enrollment` | One student ↔ one section link | **Association class** |
| `EnrollmentStatus` | ENROLLED / WAITLISTED / DROPPED | **Enum** |

Two vocabulary words this module leans on:

- **Composition** (strong has-a): the part cannot exist without the whole.
  A Section without a Course is meaningless, so only `Course.addSection`
  can create one — the Section constructor is deliberately not public.
- **Association** (plain has-a): both objects live independently and point
  at each other. An association has **two ends**, and both must be kept in
  sync — half the bugs this module can teach you live right there.

## New Java in this module

Four things you have not seen in class before each get a plain-English
introduction at the top of one file. Read these four comments first:

1. **enum** → `EnrollmentStatus.java`
2. **interface** → `Modality.java`
3. **record** → `MeetingTime.java`
4. **abstract class** → `Person.java`

## Run the tests

The `test/` folder holds 29 tests across six files. On a fresh clone
**all 29 fail**. That is on purpose — each failing test is one to-do item,
and the tests are the precise version of the instructions.

- **Eclipse:** right-click the `test` folder, or a single test file →
  **Run As → JUnit Test**
- **VS Code / Cursor:** click the beaker icon in the left sidebar, or the
  arrow in the gutter next to a test method
- **Any terminal:**

```text
java -jar lib/junit-platform-console-standalone-1.14.4.jar execute --class-path bin --scan-class-path --details=tree
```

## The exercises

24 methods across the model carry a `// TODO: your code goes here`
placeholder. Every one sits under an `Instructions:` comment that says
exactly what it must do. Suggested route, easiest to hardest:

| Step | Where | What you implement |
| --- | --- | --- |
| 1 | `InPerson`, `VirtualSync`, `OnlineAsync` | `describe()` and `isOnline()` — six tiny wins |
| 2 | `Student`, `Instructor` | `role()`, `teachingLoad()` |
| 3 | `Person` | `contactLine()` — your first polymorphism payoff |
| 4 | `Enrollment` | `isActive()` |
| 5 | `MeetingTime` | `overlaps()` — think before you type |
| 6 | `Course` | `addSection()`, `sections()`, `findSection()` |
| 7 | `Section` | the big one: seats, enroll, waitlist, drop, conflicts |

Order matters more than it did in module 1: `Section.enroll` uses
`isOpen`, which uses `seatsRemaining`, and the drop test exercises
`isActive`. The suggested route keeps you building on finished ground.

### How to work through them

1. **Run the tests first.** All 29 fail on a fresh clone. That is the
   correct starting point, not something you broke.
2. **Pick the next class on the route.** Read its `Instructions:`
   comments, then open its test file and read the exact values expected.
3. **Write the method body.**
4. **Re-run just that one test class** until it is green.
5. **Commit.** One green test class is a perfect-sized commit with an
   obvious message.

One reading tip specific to this module: if a test dies with a
`NullPointerException` instead of a tidy `expected: <x> but was: <y>`,
some method it depends on is still returning its placeholder — usually
`enroll()` returning `null`. Finish the route in order and those clear up
on their own.

## The victory lap

`src/Main.java` is a **demo, not an exercise** — it has no tests and
nothing for you to implement. On a fresh clone it prints one line telling
you to go do the work. Once all 29 tests are green, run it and read the
output top to bottom: a catalog prints itself, a full section waitlists a
third student, a drop promotes him into the seat, a time conflict is
caught, and one loop prints a contact sheet for students and instructors
alike without knowing which is which.

That output is the whole module in twenty lines. If you can explain every
line of it, you own this material.
