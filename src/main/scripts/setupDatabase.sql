CREATE TABLE IF NOT EXISTS client (
    id              INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY NOT NULL,
    email           VARCHAR(50),
    password        TEXT,
    firstName       VARCHAR(50),
    lastName        VARCHAR(50)
)