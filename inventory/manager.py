import sqlite3
from dataclasses import dataclass
from datetime import datetime
from pathlib import Path
from typing import List

DB_PATH = Path(__file__).resolve().parent.parent / 'inventory.db'

@dataclass
class Product:
    id: int
    name: str

@dataclass
class StockItem:
    product_id: int
    quantity: int

@dataclass
class Record:
    product_id: int
    quantity: int
    date: str
    user: str
    type: str  # 'inbound' or 'outbound'

class InventoryManager:
    def __init__(self, db_path: Path = DB_PATH):
        self.db_path = db_path
        self._init_db()

    def _init_db(self):
        with sqlite3.connect(self.db_path) as conn:
            c = conn.cursor()
            c.execute(
                """
                CREATE TABLE IF NOT EXISTS products (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT UNIQUE NOT NULL
                )
                """
            )
            c.execute(
                """
                CREATE TABLE IF NOT EXISTS stock (
                    product_id INTEGER NOT NULL,
                    quantity INTEGER NOT NULL,
                    FOREIGN KEY(product_id) REFERENCES products(id)
                )
                """
            )
            c.execute(
                """
                CREATE TABLE IF NOT EXISTS records (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    product_id INTEGER NOT NULL,
                    quantity INTEGER NOT NULL,
                    date TEXT NOT NULL,
                    user TEXT NOT NULL,
                    type TEXT NOT NULL,
                    FOREIGN KEY(product_id) REFERENCES products(id)
                )
                """
            )
            conn.commit()

    def add_product(self, name: str) -> int:
        with sqlite3.connect(self.db_path) as conn:
            c = conn.cursor()
            c.execute("INSERT OR IGNORE INTO products(name) VALUES (?)", (name,))
            conn.commit()
            c.execute("SELECT id FROM products WHERE name=?", (name,))
            pid = c.fetchone()[0]
            c.execute("INSERT OR IGNORE INTO stock(product_id, quantity) VALUES (?, 0)", (pid,))
            conn.commit()
            return pid

    def record_entry(self, product_name: str, quantity: int, user: str, type: str):
        now = datetime.now().strftime('%Y-%m-%d')
        pid = self.add_product(product_name)
        with sqlite3.connect(self.db_path) as conn:
            c = conn.cursor()
            c.execute("INSERT INTO records(product_id, quantity, date, user, type) VALUES (?, ?, ?, ?, ?)", (pid, quantity, now, user, type))
            if type == 'inbound':
                c.execute("UPDATE stock SET quantity = quantity + ? WHERE product_id = ?", (quantity, pid))
            elif type == 'outbound':
                c.execute("UPDATE stock SET quantity = quantity - ? WHERE product_id = ?", (quantity, pid))
            conn.commit()

    def get_stock(self) -> List[StockItem]:
        with sqlite3.connect(self.db_path) as conn:
            c = conn.cursor()
            c.execute("SELECT product_id, quantity FROM stock")
            rows = c.fetchall()
            return [StockItem(product_id=r[0], quantity=r[1]) for r in rows]

    def get_records(self, start_date: str, end_date: str) -> List[Record]:
        with sqlite3.connect(self.db_path) as conn:
            c = conn.cursor()
            c.execute(
                "SELECT product_id, quantity, date, user, type FROM records WHERE date BETWEEN ? AND ?",
                (start_date, end_date),
            )
            rows = c.fetchall()
            return [Record(*r) for r in rows]
