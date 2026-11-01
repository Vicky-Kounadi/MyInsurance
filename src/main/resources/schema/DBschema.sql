CREATE TABLE client(
    client_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15) NOT NULL /*international extensions*/
);

CREATE TABLE vehicle(
    vehicle_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    registration_number VARCHAR(20) UNIQUE NOT NULL,
    vin VARCHAR(17) UNIQUE NOT NULL, /*tops out at 17*/
    manufacturer VARCHAR(50)  NOT NULL,
    model VARCHAR(50) NOT NULL,
    /*first  car invented in 1885*/
    manufacturing_year INT CHECK(manufacturing_year>=1885),
    client_id BIGINT NOT NULL,

    FOREIGN KEY (client_id) REFERENCES client(client_id)
);

CREATE TABLE product(
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_code VARCHAR(10) UNIQUE NOT NULL,
    product_name VARCHAR(20) NOT NULL,
    description VARCHAR(100),
    base_premium DECIMAL(10,2) NOT NULL CHECK(base_premium>0),
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE insurancePolicy(
    policy_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    policy_number VARCHAR(20) UNIQUE NOT NULL,
    description VARCHAR(100),
    status VARCHAR(10) NOT NULL CHECK (status IN ('PENDING', 'ACTIVE', 'CANCELLED')),
    monthly_premium DECIMAL(10,2) NOT NULL CHECK(premium>0),
    start_date DATE NOT NULL, 
    end_date DATE NOT NULL CHECK(end_date > start_date), 
    client_id BIGINT NOT NULL,
    vehicle_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,

    FOREIGN KEY (client_id) REFERENCES client(client_id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);