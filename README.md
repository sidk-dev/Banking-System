# Smart Banking System

A console-based banking application built in Java that demonstrates core object-oriented programming principles, layered architecture (DAO pattern), flat-file persistence via CSV, input validation, and session management — all through a colorful interactive CLI.

## Features

- **User Registration** — create an account with full name, email, and a secure password
- **User Login / Logout** — authenticate with email and password; session is tracked in-memory
- **View Balance** — check your current account balance after logging in
- **Input Validation** — real-time feedback for name, email, and password rules:
  - Full name: 2–54 characters
  - Email: standard format, up to 256 characters
  - Password: 8–128 characters, must include uppercase, lowercase, digit, and special character; no whitespace
- **Colorful CLI** — ANSI color-coded prompts, success messages, and error output

## Project Structure

```
src/
├── Main.java                        # Entry point; drives the interactive menu loop
├── daos/
│   ├── UserDAO.java                 # Reads/writes user records to users.csv
│   └── AccountDAO.java              # Reads/writes account records to accounts.csv
├── models/
│   ├── User.java                    # User entity (UUID, full name, email)
│   └── Account.java                 # Account entity (balance)
├── services/
│   └── AccountService.java          # Orchestrates registration, login, logout, balance display
├── session/
│   └── Session.java                 # In-memory session; tracks the logged-in user
└── utils/
    ├── Display.java                 # ANSI-colored console output helpers
    ├── Constants.java               # Shared constants
    └── Validators/
        ├── EmailValidator.java      # Email format & length validation
        ├── FullNameValidator.java   # Full name length validation
        └── PasswordValidator.java   # Password strength & length validation
```

## Data Storage

User and account data is persisted locally as CSV files in the working directory:

| File           | Columns                       |
|----------------|-------------------------------|
| `users.csv`    | UUID, FullName, Email, Password |
| `accounts.csv` | UUID, Balance                 |

These files are created automatically on first use.

> ⚠️ **Security notice:** Passwords are currently stored in plaintext. This is intentional for simplicity in this learning project and is **not suitable for production use**. A real application should hash passwords (e.g., with BCrypt) before storing them.

## Prerequisites

- **Java 11** or newer
- **Apache Commons CSV** on the classpath (used by the DAO layer)

## Running the Application

1. Compile all sources from the `src/` directory:

   ```bash
   javac -cp /path/to/commons-csv.jar -sourcepath src src/Main.java -d out/
   ```

2. Run the compiled application:

   ```bash
   java -cp out:/path/to/commons-csv.jar Main
   ```

### Unauthenticated Menu

```
(1) Create account
(2) Login
(3) Exit
```

### Authenticated Menu

```
(1) Deposit
(2) Withdraw
(3) View balance
(4) View transaction history
(5) Logout
(6) Exit
```

## License

This project is licensed under the terms of the [LICENSE](LICENSE) file included in this repository.
