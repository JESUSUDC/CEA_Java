-- src/main/resources/db/migration/V2__create_celulares.sql
CREATE TABLE celulares (
  celular_id       VARCHAR(50) PRIMARY KEY,
  marca            VARCHAR(100) NOT NULL,
  imei             VARCHAR(20)  NOT NULL,
  nfc              BIT NOT NULL,
  huella           BIT NOT NULL,
  operador         VARCHAR(60)  NOT NULL,
  tecnologia_banda VARCHAR(60)  NOT NULL,
  cantidad_sim     INT NOT NULL
);
CREATE INDEX IX_celulares_imei ON celulares(imei);
