-- Insertar empresas asociadas
INSERT INTO EmpresaAsociada (nombreEmpresa, direccionEmpresa, tipoServicio) VALUES
('DocExpress', 'Av. Libertad 123, Santiago', 'Mensajería y documentos'),
('LegalDocs', 'Los Leones 456, Providencia', 'Documentos legales'),
('AcademyPapers', 'Av. Universidad 789, Ñuñoa', 'Documentos académicos'),
('MediDelivery', 'Av. Salud 321, Las Condes', 'Documentos médicos'),
('FirmaExpress', 'Av. Central 888, Santiago Centro', 'Gestión de firmas y autorizaciones');

-- Insertar servicios variados
INSERT INTO Servicios (idEmpresaAsociada, nombreServicio, descripcionServicio, precioServicio, categoriaServicio) VALUES
-- Servicios de DocExpress (id 1)
(1, 'Entrega Rápida', 'Entrega de documentos en menos de 2 horas dentro de la ciudad', 5000, 'Mensajería'),
(1, 'Entrega Estándar', 'Entrega de documentos en 24 horas dentro de la ciudad', 3000, 'Mensajería'),
(1, 'Envío Nacional', 'Entrega de documentos a cualquier punto del país en 48 horas', 8000, 'Mensajería'),
(1, 'Seguimiento en Línea', 'Visualiza el estado del envío en tiempo real', 1000, 'Mensajería'),

-- Servicios de LegalDocs (id 2)
(2, 'Documentos Notariales', 'Servicio de gestión y entrega de documentos notariales', 12000, 'Legal'),
(2, 'Contratos', 'Entrega de contratos con verificación de recepción', 9000, 'Legal'),
(2, 'Testamentos', 'Entrega confidencial de testamentos y documentos sucesorios', 15000, 'Legal'),
(2, 'Escrituras Legalizadas', 'Entrega de escrituras inscritas en registros públicos', 13000, 'Legal'),

-- Servicios de AcademyPapers (id 3)
(3, 'Tesis y Documentos Académicos', 'Entrega de tesis y documentos académicos con empaque protector', 7500, 'Académico'),
(3, 'Certificados Educativos', 'Entrega certificada de diplomas y certificados académicos', 6000, 'Académico'),
(3, 'Cartas de Recomendación', 'Envío seguro y privado de documentos de respaldo académico', 5000, 'Académico'),
(3, 'Transcripciones Académicas', 'Entrega de historial académico oficial', 7000, 'Académico'),

-- Servicios de MediDelivery (id 4)
(4, 'Resultados Médicos', 'Entrega privada de resultados médicos y exámenes', 8500, 'Salud'),
(4, 'Recetas Médicas', 'Entrega urgente de recetas médicas', 4500, 'Salud'),
(4, 'Traslado de Informes Clínicos', 'Entrega de informes clínicos en centros médicos', 9000, 'Salud'),
(4, 'Consentimientos Informados', 'Distribución de documentos médicos legales', 6500, 'Salud'),

-- Servicios de FirmaExpress (idEmpresaAsociada = 5)
(5, 'Firma Electrónica Simple', 'Envío de documentos para firma digital básica', 7000, 'Firmas'),
(5, 'Firma Presencial', 'Representante entrega documento para firma en persona', 10000, 'Firmas'),
(5, 'Autenticación de Documentos', 'Validación oficial de firma y contenido', 8500, 'Firmas'),
(5, 'Firma Masiva de Contratos', 'Gestión de firmas múltiples para empresas', 25000, 'Firmas');



-- insertar clientes
INSERT INTO cliente (nombre, direccion, email, telefono) VALUES 
('Sofía Martínez', 'Av. Documentos 123', 'sofia@docuexpress.cl', '912345678'),
('Matías Rojas', 'Calle Legal 456', 'matias@docuexpress.cl', '987654321'),
('Daniela Torres', 'Pasaje Carta 789', 'daniela@docuexpress.cl', '911223344'),
('Cristóbal Díaz', 'Av. Central 321', 'cristobal@docuexpress.cl', '922334455'),
('Valentina Castro', 'Calle Notaría 100', 'valentina@docuexpress.cl', '933445566'),
('Sebastián Pino', 'Camino Firmas 10', 'sebastian@docuexpress.cl', '944556677'),
('Isidora Reyes', 'Villa Trámite 45', 'isidora@docuexpress.cl', '955667788'),
('Joaquín Vidal', 'Condominio Oficios 234', 'joaquin@docuexpress.cl', '966778899'),
('Camila Herrera', 'Torre Certificados 123', 'camila@docuexpress.cl', '977889900'),
('Benjamín Morales', 'Sector Cartas 90', 'benjamin@docuexpress.cl', '988990011');


-- insertar repartidores
INSERT INTO repartidor (nombre, telefono) VALUES 
('Ignacio Valenzuela', '911111111'),
('Paula Ramírez', '922222222'),
('Andrés Fuentes', '933333333'),
('Catalina Bravo', '944444444'),
('Tomás León', '955555555');


-- insertar pedidos historicos
INSERT INTO OrderEntity (cliente_id, idEmpresaAsociada, repartidor_id, fechaPedido, fechaEntrega, estadoPedido, prioridadPedido) VALUES
(1, 1, 1, '2024-05-01 09:00', '2024-05-01 10:30', 'ENTREGADO', 'ALTA'),
(2, 2, 2, '2024-05-02 10:00', '2024-05-02 12:00', 'ENTREGADO', 'MEDIA'),
(3, 3, 3, '2024-05-03 11:00', '2024-05-03 13:00', 'ENTREGADO', 'BAJA'),
(4, 4, 4, '2024-05-04 08:30', '2024-05-04 10:00', 'FALLIDO', 'ALTA'),
(5, 5, 5, '2024-05-05 14:00', '2024-05-05 16:00', 'ENTREGADO', 'MEDIA'),
(6, 1, 1, '2024-05-06 09:30', '2024-05-06 11:30', 'ENTREGADO', 'MEDIA'),
(7, 2, 2, '2024-05-07 10:30', '2024-05-07 12:00', 'CANCELADO', 'BAJA'),
(8, 3, 3, '2024-05-08 13:00', '2024-05-08 14:30', 'ENTREGADO', 'ALTA'),
(9, 4, 4, '2024-05-09 12:00', '2024-05-09 13:45', 'ENTREGADO', 'BAJA'),
(10, 5, 5, '2024-05-10 15:00', '2024-05-10 16:30', 'ENTREGADO', 'MEDIA'),
(1, 2, 1, '2024-05-11 11:00', '2024-05-11 12:30', 'ENTREGADO', 'MEDIA'),
(2, 3, 2, '2024-05-12 09:00', '2024-05-12 10:45', 'ENTREGADO', 'BAJA'),
(3, 4, 3, '2024-05-13 10:00', '2024-05-13 11:30', 'ENTREGADO', 'ALTA'),
(4, 5, 4, '2024-05-14 13:00', '2024-05-14 14:15', 'FALLIDO', 'ALTA'),
(5, 1, 5, '2024-05-15 08:30', '2024-05-15 10:00', 'ENTREGADO', 'MEDIA'),
(6, 2, 1, '2024-05-16 14:00', '2024-05-16 15:45', 'ENTREGADO', 'BAJA'),
(7, 3, 2, '2024-05-17 09:45', '2024-05-17 11:15', 'ENTREGADO', 'MEDIA'),
(8, 4, 3, '2024-05-18 12:00', '2024-05-18 13:30', 'ENTREGADO', 'ALTA'),
(9, 5, 4, '2024-05-19 11:00', '2024-05-19 12:30', 'ENTREGADO', 'BAJA'),
(10, 1, 5, '2024-05-20 10:30', '2024-05-20 12:00', 'ENTREGADO', 'ALTA');

