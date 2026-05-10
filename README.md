# Smart Banking System

A console-based banking app written in Java. It uses a layered DAO/service structure, in-memory session tracking, CSV persistence, and ANSI-colored console output to simulate a small banking workflow from the terminal.

## What You Can Do

- Create an account with full name, email, and password validation.
- Log in and log out using your saved credentials.
- Deposit money into the current account.
- Withdraw money with balance checks.
- View the current balance after login.
- View the transaction history for the logged-in user.

## How It Works

- User records are stored in `users.csv`.
- Account balances are stored in `accounts.csv`.
- Transaction history is stored in `transactions.csv`.
- The active login is kept in memory through the session helper.
- CSV files are created automatically the first time they are needed.

## Validation Rules

- Full name: 2 to 54 characters, cannot be empty.
- Email: valid email format, up to 256 characters.
- Password: 8 to 128 characters, must contain at least one uppercase letter, one lowercase letter, one digit, and one special character, and cannot contain whitespace.

## Project Structure

```
src/
├── Main.java
├── daos/
│   ├── UserDAO.java
│   ├── AccountDAO.java
│   └── TransactionDAO.java
├── models/
│   ├── User.java
│   ├── Account.java
│   └── Transaction.java
├── services/
│   └── AccountService.java
├── session/
│   └── Session.java
└── utils/
      ├── Display.java
      ├── Constants.java
      └── Validators/
            ├── EmailValidator.java
            ├── FullNameValidator.java
            └── PasswordValidator.java
```

## Requirements

- Java 11 or newer
- Apache Commons CSV on the classpath

## Run Locally

This project does not use Maven or Gradle, so you need to compile and run it with `javac` and `java` directly.

### Windows

```bash
javac -cp .;path\to\commons-csv.jar -sourcepath src -d out src\Main.java
java -cp out;path\to\commons-csv.jar Main
```

### macOS / Linux

```bash
javac -cp .:/path/to/commons-csv.jar -sourcepath src -d out src/Main.java
java -cp out:/path/to/commons-csv.jar Main
```

## Menu Flow

### When Logged Out

```
(1) Create account
(2) Login
(3) Exit
```

### When Logged In

```
(1) Deposit
(2) Withdraw
(3) View balance
(4) View transaction history
(5) Logout
(6) Exit
```

## Data Files

| File               | Purpose                                                     |
| ------------------ | ----------------------------------------------------------- |
| `users.csv`        | Stores user UUID, full name, email, and password            |
| `accounts.csv`     | Stores account UUID and balance                             |
| `transactions.csv` | Stores transaction ID, user ID, amount, type, and timestamp |

## Security Note

Passwords are stored in plaintext in this learning project. That is not suitable for production use. If you plan to evolve the app, password hashing should be the first security upgrade.

## License

This project is licensed under the terms of the [LICENSE](LICENSE) file.
