-- Tabla Cliente
CREATE TABLE Cliente (
    id_cliente SERIAL PRIMARY KEY,
    nombre_cliente VARCHAR(100) NOT NULL,
    correo_cliente VARCHAR(100) NOT NULL,
    telefono_cliente VARCHAR(15),
    direccion_cliente TEXT
);

-- Tabla Repartidor
CREATE TABLE Repartidor (
    id_repartidor SERIAL PRIMARY KEY,
    nombre_repartidor VARCHAR(100) NOT NULL
);

-- Tabla Medios de Pago
CREATE TABLE Medios_pago (
    id_detalle SERIAL PRIMARY KEY,
    nombre_metodo VARCHAR(50) NOT NULL
);

-- Tabla Empresas Asociadas
CREATE TABLE Empresas_asociadas (
    id_empresa SERIAL PRIMARY KEY,
    nombre_empresa VARCHAR(100) NOT NULL
);

-- Tabla Productos/Servicios
CREATE TABLE Productos_servicios (
    id_producto SERIAL PRIMARY KEY,
    nombre_empresa VARCHAR(100), 
    categoria_producto VARCHAR(50),
    id_empresa INTEGER REFERENCES Empresas_asociadas(id_empresa) ON DELETE CASCADE
);

-- Tabla Pedidos
CREATE TABLE Pedidos (
    id_pedido SERIAL PRIMARY KEY,
    cliente_id INTEGER NOT NULL REFERENCES Cliente(id_cliente) ON DELETE CASCADE,
    repartidor_id INTEGER REFERENCES Repartidor(id_repartidor) ON DELETE SET NULL,
    fecha_pedido DATE NOT NULL,
    fecha_entrega DATE,
    estado_pedido VARCHAR(50),
    prioridad_pedido VARCHAR(20),
    destino_pedido TEXT,
    medio_pago_id INTEGER REFERENCES Medios_pago(id_detalle) ON DELETE SET NULL
);

-- Tabla Detalle_pedido
CREATE TABLE Detalle_pedido (
    id_detalle SERIAL PRIMARY KEY,
    pedido_id INTEGER NOT NULL REFERENCES Pedidos(id_pedido) ON DELETE CASCADE,
    producto_id INTEGER NOT NULL REFERENCES Productos_servicios(id_producto) ON DELETE CASCADE,
    cantidad INTEGER NOT NULL CHECK (cantidad > 0)
);

-- Tabla Calificaciones
CREATE TABLE Calificaciones (
    id_calificacion SERIAL PRIMARY KEY,
    pedido_id INTEGER NOT NULL REFERENCES Pedidos(id_pedido) ON DELETE CASCADE,
    puntuacion INTEGER CHECK (puntuacion BETWEEN 1 AND 5),
    fecha_calificacion DATE NOT NULL
);
