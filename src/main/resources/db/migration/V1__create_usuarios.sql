-- src/main/resources/db/migration/V1__create_usuarios.sql
CREATE TABLE usuarios (
  usuario_id VARCHAR(50) PRIMARY KEY,
  nombre     VARCHAR(120) NOT NULL,
  email      VARCHAR(160) NOT NULL,
  password_hash VARCHAR(100) NOT NULL  -- Nuevo campo obligatorio
);
