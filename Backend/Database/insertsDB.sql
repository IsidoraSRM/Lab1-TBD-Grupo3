-- Insertar empresas asociadas
INSERT INTO EmpresaAsociada (nombreEmpresa, direccionEmpresa, tipoServicio) VALUES
('DocExpress', 'Av. Libertad 123, Santiago', 'Mensajería y documentos'),
('LegalDocs', 'Los Leones 456, Providencia', 'Documentos legales'),
('AcademyPapers', 'Av. Universidad 789, Ñuñoa', 'Documentos académicos'),
('MediDelivery', 'Av. Salud 321, Las Condes', 'Documentos médicos');

-- Insertar servicios variados
INSERT INTO Servicios (idEmpresaAsociada, nombreServicio, descripcionServicio, precioServicio, categoriaServicio) VALUES
-- Servicios de DocExpress (id 1)
(1, 'Entrega Rápida', 'Entrega de documentos en menos de 2 horas dentro de la ciudad', 5000, 'Mensajería'),
(1, 'Entrega Estándar', 'Entrega de documentos en 24 horas dentro de la ciudad', 3000, 'Mensajería'),
(1, 'Envío Nacional', 'Entrega de documentos a cualquier punto del país en 48 horas', 8000, 'Mensajería'),

-- Servicios de LegalDocs (id 2)
(2, 'Documentos Notariales', 'Servicio de gestión y entrega de documentos notariales', 12000, 'Legal'),
(2, 'Contratos', 'Entrega de contratos con verificación de recepción', 9000, 'Legal'),
(2, 'Testamentos', 'Entrega confidencial de testamentos y documentos sucesorios', 15000, 'Legal'),

-- Servicios de AcademyPapers (id 3)
(3, 'Tesis y Documentos Académicos', 'Entrega de tesis y documentos académicos con empaque protector', 7500, 'Académico'),
(3, 'Certificados Educativos', 'Entrega certificada de diplomas y certificados académicos', 6000, 'Académico'),

-- Servicios de MediDelivery (id 4)
(4, 'Resultados Médicos', 'Entrega privada de resultados médicos y exámenes', 8500, 'Salud'),
(4, 'Recetas Médicas', 'Entrega urgente de recetas médicas', 4500, 'Salud');