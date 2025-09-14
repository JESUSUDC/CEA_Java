/domain
  /aggregate
    AsignacionDeCelular.java        # RA: invariantes + eventos
  /entity
    Usuario.java
    Celular.java
  /event
    DomainEvent.java
    CelularAsignadoAUsuario.java
    CelularDevueltoPorUsuario.java
  /exception
    DomainException.java
    ImeiDuplicadoException.java
  /factory
    CelularFactory.java
  /policy                         # Strategy / Specification
    Hechos.java
    PoliticaAsignacion.java
    PoliticaUnicidadImei.java
    PoliticaLimitePorUsuario.java
    PoliticaCapacidadesMinimas.java
  /service
    ServicioAsignacionDeCelular.java
  /valueobject
    Imei.java
    Email.java

Principios:
- Lenguaje Ubicuo del CEA (Usuario, Celular, AsignacionDeCelular, Imei).
- Dominio 100% puro: sin frameworks ni IO.
- Invariantes explícitas en la RA.
- VO inmutables con validación en su constructor/factory.
- Domain Service para reglas que cruzan agregados, usando Policies (Strategy).
- Eventos de Dominio nombrados en pasado; la publicación real ocurre fuera del dominio.
Pruebas:
- Tests de dominio sin infraestructura; mockear sólo si es estrictamente necesario (no debería).
# CEA_Java
