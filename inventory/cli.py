import argparse
from datetime import datetime, timedelta

from .manager import InventoryManager

def cmd_inbound(args):
    mgr = InventoryManager()
    mgr.record_entry(args.product, args.quantity, args.user, 'inbound')
    print(f"Inbound recorded: {args.quantity} x {args.product}")

def cmd_outbound(args):
    mgr = InventoryManager()
    mgr.record_entry(args.product, args.quantity, args.user, 'outbound')
    print(f"Outbound recorded: {args.quantity} x {args.product}")

def cmd_stock(args):
    mgr = InventoryManager()
    stock = mgr.get_stock()
    for item in stock:
        print(f"Product {item.product_id}: {item.quantity}")

def cmd_report(args):
    mgr = InventoryManager()
    if args.period == 'daily':
        start = datetime.now().strftime('%Y-%m-%d')
    else:
        start = (datetime.now() - timedelta(days=30)).strftime('%Y-%m-%d')
    end = datetime.now().strftime('%Y-%m-%d')
    records = mgr.get_records(start, end)
    for r in records:
        print(f"{r.date} - {r.type} - pid:{r.product_id} qty:{r.quantity} user:{r.user}")

def build_parser():
    p = argparse.ArgumentParser(description='Inventory management CLI')
    sub = p.add_subparsers(dest='command')

    inbound = sub.add_parser('inbound')
    inbound.add_argument('product')
    inbound.add_argument('quantity', type=int)
    inbound.add_argument('user')
    inbound.set_defaults(func=cmd_inbound)

    outbound = sub.add_parser('outbound')
    outbound.add_argument('product')
    outbound.add_argument('quantity', type=int)
    outbound.add_argument('user')
    outbound.set_defaults(func=cmd_outbound)

    stock = sub.add_parser('stock')
    stock.set_defaults(func=cmd_stock)

    report = sub.add_parser('report')
    report.add_argument('--period', choices=['daily', 'monthly'], default='daily')
    report.set_defaults(func=cmd_report)

    return p

if __name__ == '__main__':
    parser = build_parser()
    args = parser.parse_args()
    if hasattr(args, 'func'):
        args.func(args)
    else:
        parser.print_help()
