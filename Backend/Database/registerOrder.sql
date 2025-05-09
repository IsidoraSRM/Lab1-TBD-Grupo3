CREATE OR REPLACE PROCEDURE register_order(
    IN cliente_id INT,
    IN idEmpresaAsociada INT,
    IN prioridadPedido VARCHAR(50),
    OUT p_id_pedido INT
)
    LANGUAGE plpgsql -- Lenguaje
AS $$
DECLARE
    v_repartidor_id INT;
    v_estado_pedido VARCHAR(50);
    v_fecha_entrega TIMESTAMP;
    v_tiempo_preparacion INT; -- En minutos
    v_tiempo_envio INT;      -- En minutos
BEGIN
    -- 1. Asignar repartidor disponible (el que tenga menos pedidos y este diponible)
    SELECT r.repartidor_id INTO v_repartidor_id
    FROM Repartidor r
    WHERE r.disponible = TRUE
    ORDER BY (
                 SELECT COUNT(*)
                 FROM OrderEntity o
                 WHERE o.repartidor_id = r.repartidor_id
                   AND o.estadoPedido IN ('PENDIENTE', 'EN_CAMINO')
             ) ASC
    LIMIT 1;

    -- 2. Si no hay repartidor disponible, asignar estado "PENDIENTE_ASI"
    IF v_repartidor_id IS NULL THEN
        v_estado_pedido := 'PENDIENTE_ASI';
        v_repartidor_id := NULL;
    ELSE
        v_estado_pedido := 'PREPARACION';
    END IF;

    -- 3. Calcular tiempos
    CASE prioridadPedido
        WHEN 'ALTA' THEN
            v_tiempo_preparacion := 30; -- 30 minutos
            v_tiempo_envio := 20;      -- 20 minutos
        WHEN 'MEDIA' THEN
            v_tiempo_preparacion := 45;
            v_tiempo_envio := 30;
        ELSE -- 'BAJA'
        v_tiempo_preparacion := 60;
        v_tiempo_envio := 45;
        END CASE;

    -- 4. Calcular fechaEntrega (ahora + preparación + envío)
    v_fecha_entrega := NOW() +
                       (v_tiempo_preparacion * INTERVAL '1 minute') +
                       (v_tiempo_envio * INTERVAL '1 minute');


    -- 5. Insertar el pedido
    INSERT INTO OrderEntity (
        cliente_id,
        idEmpresaAsociada,
        repartidor_id,
        fechaPedido,
        fechaEntrega,
        estadoPedido,
        prioridadPedido
    ) VALUES (
                 cliente_id,
                 idEmpresaAsociada,
                 v_repartidor_id,
                 NOW(), -- Fecha actual automática
                 v_fecha_entrega,
                 v_estado_pedido,
                 prioridadPedido
             );

    -- 4. Devolver el ID del pedido generado
    p_id_pedido := LASTVAL();
END;
$$;