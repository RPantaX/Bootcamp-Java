-- Insertar 10 registros aleatorios en la tabla libros
INSERT INTO libros (ISBN, titulo)
VALUES
('9780439554930', 'Harry Potter and the Philosopher'),
('9780061120084', 'To Kill a Mockingbird'),
('9780140449334', 'Pride and Prejudice'),
('9780739330944', 'The Great Gatsby'),
('9780553212471', 'Moby-Dick'),
('9780141182703', '1984'),
('9780553382167', 'Brave New World'),
('9780060935467', 'The Catcher in the Rye'),
('9780385472579', 'One Hundred Years of Solitude'),
('9780062315007', 'The Hunger Games');

-- Insertar 10 registros aleatorios en la tabla Autores
INSERT INTO Autores (autor_id, nombre)
VALUES
(1, 'J.K. Rowling'),
(2, 'Harper Lee'),
(3, 'Jane Austen'),
(4, 'F. Scott Fitzgerald'),
(5, 'Herman Melville'),
(6, 'George Orwell'),
(7, 'Aldous Huxley'),
(8, 'J.D. Salinger'),
(9, 'Gabriel Garcia Marquez'),
(10, 'Suzanne Collins');
-- Insertar 10 registros aleatorios en la tabla autores_libros
INSERT INTO autores_libros (ISBN, autor_id)
VALUES
('9780439554930', 1),
('9780061120084', 2),
('9780140449334', 3),
('9780739330944', 4),
('9780553212471', 5),
('9780141182703', 6),
('9780553382167', 7),
('9780060935467', 8),
('9780385472579', 9),
('9780062315007', 10);

-- Insertar 10 registros aleatorios en la tabla Categorias
INSERT INTO Categorias (categoria_id, nombre)
VALUES
(1, 'Fiction'),
(2, 'Classic'),
(3, 'Science Fiction'),
(4, 'Mystery'),
(5, 'Romance'),
(6, 'Thriller'),
(7, 'Fantasy'),
(8, 'Dystopian'),
(9, 'Historical Fiction'),
(10, 'Adventure');

-- Insertar 10 registros aleatorios en la tabla Libros_categorias
INSERT INTO Libros_categorias (ISBN, categoria_id)
VALUES
('9780439554930', 1),
('9780061120084', 2),
('9780140449334', 3),
('9780739330944', 4),
('9780553212471', 5),
('9780141182703', 6),
('9780553382167', 7),
('9780060935467', 8),
('9780385472579', 9),
('9780062315007', 10);

-- Insertar 10 registros aleatorios en la tabla lectores
INSERT INTO lectores (lector_id, nombre)
VALUES
(1, 'Alice Johnson'),
(2, 'Bob Smith'),
(3, 'Charlie Brown'),
(4, 'Diana Martinez'),
(5, 'Ethan Wilson'),
(6, 'Fiona Taylor'),
(7, 'George Clark'),
(8, 'Haley White'),
(9, 'Ian Turner'),
(10, 'Jessica Adams');

-- Insertar 10 registros aleatorios en la tabla Prestamo
INSERT INTO Prestamo (prestamo_id, fecha_prestamo, Fecha_Devolucion_Esperada, ISBN, lector_ID)
VALUES
(1, '2024-01-01', '2024-01-15', '9780439554930', 1),
(2, '2024-02-01', '2024-02-15', '9780061120084', 2),
(3, '2024-03-01', '2024-03-15', '9780140449334', 3),
(4, '2024-04-01', '2024-04-15', '9780739330944', 4),
(5, '2024-05-01', '2024-05-15', '9780553212471', 5),
(6, '2024-06-01', '2024-06-15', '9780141182703', 6),
(7, '2024-07-01', '2024-07-15', '9780553382167', 7),
(8, '2024-08-01', '2024-08-15', '9780060935467', 8),
(9, '2024-09-01', '2024-09-15', '9780385472579', 9),
(10, '2024-10-01', '2024-10-15', '9780062315007', 10);