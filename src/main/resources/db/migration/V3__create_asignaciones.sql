-- src/main/resources/db/migration/V3__create_asignaciones.sql
CREATE TABLE asignaciones (
  asignacion_id VARCHAR(50) PRIMARY KEY,
  usuario_id    VARCHAR(50) NOT NULL,
  celular_id    VARCHAR(50) NOT NULL,
  estado        VARCHAR(20) NOT NULL,
  CONSTRAINT FK_asig_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(usuario_id),
  CONSTRAINT FK_asig_celular FOREIGN KEY (celular_id) REFERENCES celulares(celular_id)
);
CREATE INDEX IX_asig_usuario_estado ON asignaciones(usuario_id, estado);
