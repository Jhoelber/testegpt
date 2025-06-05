# Inventory Example

This project provides a simple inventory manager storing data in a SQLite database.

## Setup

Requires Python 3. Run:

```bash
pip install -r requirements.txt  # there are no external dependencies
```

## Usage

Register a delivery:

```bash
python -m inventory.cli inbound "Widget" 10 alice
```

Register a sale:

```bash
python -m inventory.cli outbound "Widget" 5 bob
```

Show stock levels:

```bash
python -m inventory.cli stock
```

Generate a daily or monthly report:

```bash
python -m inventory.cli report --period daily
python -m inventory.cli report --period monthly
```
