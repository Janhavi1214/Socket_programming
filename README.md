# 🔌 Socket Programming in Java

A collection of client-server programs built using Java's `java.net` package, demonstrating core networking concepts — TCP communication, multi-client handling, and thread-safe shared state using synchronization.

---

## 📁 Project Structure

```
src/
├── Server002.java        # Restaurant order server (single client)
├── Client002.java        # Restaurant order client
├── Server003.java        # Multithreaded bank deposit server
├── ClientHandler_003.java # Thread handler for each bank client
└── Client003.java        # Bank deposit client
```

---

## 🧩 Programs

### 1. Restaurant Order System (`Server002` + `Client002`)

A simple single-client TCP server that accepts a food order from the client and confirms it.

**Flow:**
```
Client002  ──── "Pizza" ────▶  Server002
           ◀── "Order confirmed: Pizza" ──
```

- Server listens on port `2105`
- Client sends an order string (e.g., `"Pizza"`)
- Server logs and confirms the order
- Connection closes after one exchange

---

### 2. Multithreaded Bank Deposit System (`Server003` + `Client003`)

A concurrent TCP server that handles multiple clients simultaneously. Each client deposits an amount into a shared bank account — with balance updates protected by `synchronized`.

**Flow:**
```
Client003 (Thread 1) ──── "3500" ────▶ Server003
Client003 (Thread 2) ──── "1200" ────▶ Server003
                     ◀── "Deposit successful. Updated balance: XXXX" ──
```

- Server listens on port `5000`
- Each incoming connection spawns a new `ClientHandler_003` thread
- Shared `balance` variable is updated inside a `synchronized` block to prevent race conditions
- Initial balance: `6000`

---

## ⚙️ Concepts Demonstrated

| Concept | Where Used |
|---|---|
| TCP Socket communication | All programs |
| `ServerSocket` / `Socket` | All servers/clients |
| `BufferedReader` / `PrintWriter` for I/O | All programs |
| Multi-threading (`extends Thread`) | `Server003` / `ClientHandler_003` |
| Thread synchronization (`synchronized`) | `ClientHandler_003` |
| Race condition prevention | Bank balance update |

---

## 🚀 How to Run

### Prerequisites
- Java 8+
- IntelliJ IDEA (recommended) or any Java IDE / `javac` CLI

### Restaurant Order System

**Terminal 1 — Start the server:**
```bash
javac Server002.java
java Server002
# Output: Restaurant server started...
```

**Terminal 2 — Run the client:**
```bash
javac Client002.java
java Client002
# Output: Order confirmed : Pizza
```

---

### Bank Deposit System

**Terminal 1 — Start the server:**
```bash
javac Server003.java ClientHandler_003.java
java Server003
# Output: Bank Server started.....
```

**Terminal 2 — Run a client:**
```bash
javac Client003.java
java Client003
# Output: Deposit successful. Updated balance9500
```

> Run multiple Client003 instances simultaneously to test concurrent deposit handling.

---

## 🛠️ Tech Stack

- **Language:** Java
- **Networking:** `java.net.Socket`, `java.net.ServerSocket`
- **Concurrency:** `java.lang.Thread`, `synchronized`
- **IDE:** IntelliJ IDEA

---

## 📌 Key Takeaway

> Without `synchronized`, concurrent deposits into the shared `balance` would cause a **race condition** — two threads reading the same stale value and overwriting each other's updates. The `synchronized` block on `ClientHandler_003.class` ensures only one thread modifies the balance at a time.
