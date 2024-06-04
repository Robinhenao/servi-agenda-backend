Feature: Servicio de Gestión de Servicios

  Scenario: Registrar un nuevo servicio
    Given que deseo registrar con descripcion "Servicio de Plomería", precio 70.0, empleado 1 y fecha de creacion "2024-06-03"
    When se registra el servicio
    Then el sistema muestra el servicio registrado con éxito

  Scenario: Obtener un servicio existente por ID
    Given que deseo obtener el servicio con ID 1
    When se busca el servicio por ID
    Then el sistema muestra la información del servicio con ID 1

  Scenario: Actualizar un servicio existente
    Given que deseo actualizar el servicio con ID 2 con nueva descripción "Servicio de Podas"
    When se actualiza el servicio
    Then el sistema muestra el servicio actualizado correctamente

  Scenario: Eliminar un servicio existente
    Given que deseo eliminar el servicio con ID 1
    When se elimina el servicio
    Then el servicio con ID 1 es marcado como eliminado