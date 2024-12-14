CREATE TABLE cars (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    make VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    year INT NOT NULL,
    price NUMERIC NOT NULL,
    vin VARCHAR(17) NOT NULL UNIQUE
);

-- нужно для gen_random_uuid()
CREATE EXTENSION IF NOT EXISTS pgcrypto;
