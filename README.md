# ChessWithGUI

ChessWithGUI is a JavaFX desktop chess app with local gameplay and optional TCP multiplayer.

## What it is

This project is an 8x8 chess game implemented in Java + JavaFX. It supports:

- Drag-and-drop piece movement on the board UI
- Turn-based local gameplay
- Save / import game states via a custom `.chs` format
- Host/Connect multiplayer via TCP sockets

The board is text-piece based (no artwork sprites) in this version.

## What is implemented

- Core game domain model:
  - `Board`, `Square`, `Position`
  - `Player` / `PlayerManager`
  - Piece classes in `subsystem/Pieces` (`Pawn`, `Rook`, `Knight`, `Bishop`, `Queen`, `King`)
- Main JavaFX flow:
  - [Main.java](/Users/saverionasturzio/.t3/worktrees/ChessWithGUI/t3code-dd00729d/src/application/Main.java)
  - [Controller.java](/Users/saverionasturzio/.t3/worktrees/ChessWithGUI/t3code-dd00729d/src/application/Controller.java)
  - [Main.fxml](/Users/saverionasturzio/.t3/worktrees/ChessWithGUI/t3code-dd00729d/src/application/Main.fxml)
- Save/import:
  - [File.java](/Users/saverionasturzio/.t3/worktrees/ChessWithGUI/t3code-dd00729d/src/application/subsystem/IO/File.java)
  - [FileReader.java](/Users/saverionasturzio/.t3/worktrees/ChessWithGUI/t3code-dd00729d/src/application/subsystem/IO/FileReader.java)
- Networking:
  - [Server.java](/Users/saverionasturzio/.t3/worktrees/ChessWithGUI/t3code-dd00729d/src/application/subsystem/Networking/Server.java)
  - [Client.java](/Users/saverionasturzio/.t3/worktrees/ChessWithGUI/t3code-dd00729d/src/application/subsystem/Networking/Client.java)

## What is missing

- AI/chess engine
- Full rule set (some edge cases like advanced validations/check-mate flow are incomplete)
- Graphical piece rendering and polished UI styling
- Automated tests

## Project structure

- `src/application/Main.java`
- `src/application/Controller.java`
- `src/application/Main.fxml`
- `src/application/application.css`
- `src/application/subsystem/Game/*`
- `src/application/subsystem/Pieces/*`
- `src/application/subsystem/IO/*`
- `src/application/subsystem/Networking/*`
- `src/application/subsystem/Utils/*`
- `Save.chs` (example save file)
- `pom.xml` (Maven config)
- `run.sh` (executable local launcher)
- `bin/` and `target/` are generated outputs and should be ignored from version control

## Build/run prerequisites

- Java 17+
- Apache Maven (`mvn` on PATH)
- Internet access for first-time Maven dependency download

## How to run locally

- `./run.sh`
  - Compiles project
  - Copies JavaFX/runtime dependencies
  - Launches `application.Main`

- `./run.sh build`
  - `mvn -q -DskipTests compile`

- `./run.sh package`
  - `mvn -q -DskipTests package`

- `./run.sh test`
  - `mvn -q test` (none are currently defined)

## Legacy non-Maven path

Only for JDK 8 installs that include JavaFX:

```bash
javac -d build/classes $(find src -name '*.java')
java -cp build/classes application.Main
```

## Notes

Recent launcher fix: the app loads FXML using `/application/Main.fxml` and Maven resources are packaged under `/application/...`, which avoids `Location is required` at startup.
