insert into inventory_part
(part_number, description, stock_price, weight, stock_level, reorder_level, stock_on_order)
values
    ('P-1001', 'Steel Bolt 3/4" x 2"',          0.45,  0.02,  500, 100, 200),
    ('P-1002', 'Hex Nut 3/4"',                  0.25,  0.01,  650, 150, 150),
    ('P-1003', 'Flat Washer 3/4"',              0.10,  0.01, 1000, 200, 500),
    ('P-2001', 'Aluminum Sheet 24x36"',        45.00,  4.50,   40,  10,   20),
    ('P-2002', 'Copper Wire Roll 100ft',       29.99,  1.20,   60,  20,   10),
    ('P-3001', 'Plastic Gear (Medium)',         3.75,  0.15,  120,  30,   25),
    ('P-3002', 'Plastic Gear (Small)',          2.25,  0.08,  300,  50,   40),
    ('P-4001', 'Hydraulic Hose 6ft',           18.50,  1.80,   25,  10,    5),
    ('P-4002', 'Hydraulic Coupling',           12.75,  0.50,   80,  20,   10),
    ('P-5001', 'Bearing Assembly (Type A)',    22.40,  0.75,   45,  15,   10),
    ('P-5002', 'Bearing Assembly (Type B)',    24.10,  0.80,   50,  15,   10),
    ('P-6001', 'Rubber Gasket 4"',              1.50,  0.05,  500, 100, 200),
    ('P-7001', 'Circuit Board – Control Module',120.00,0.35,   12,   5,    8),
    ('P-7002', 'Sensor Unit – Temperature',     38.00, 0.20,   30,  10,    5),
    ('P-8001', 'Packaging Box – Large',         0.95,  0.10,  400, 100, 150);