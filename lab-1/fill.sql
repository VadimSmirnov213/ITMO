
INSERT INTO GeneticMaterial (name, type, size)
VALUES ('эмбрион', 'генетический материал', 'большой');


INSERT INTO Money (type, date, amount)
VALUES 
('наличные', '2025-02-01 12:00:00', 500),
('наличные', '2025-03-01 14:00:00', 500);  


INSERT INTO People (name, surname, GeneticMaterialID, role, MoneyID)
VALUES 
('Доджсон', 'фамилия1', 1, 'главный', 2),
('ИнДжин', 'фамилия2', 1, 'сотрудник', 1);


INSERT INTO Location (name, cool, address)
VALUES 
('Бар', 'крутой', 'дом.36, к.2'),
('Остров', 'крутой', 'Россия');


INSERT INTO LocationOwner (name, surname, profession, LocationID)
VALUES 
('имя1', 'фамилия1', 'консультант', 1),
('Карлос', 'фамилия1', 'консультант', 2),
('Чарли', 'фамилия2', 'консультант', 2),
('имя2', 'фамилия2', 'подрядчик', 1);


INSERT INTO Place (LocationID, name, shortname)
VALUES 
(1, 'Силиконовая долина', 'Долина'),
(2, 'Океан', 'Ocean');


INSERT INTO Visit (PeopleID, LocationID, time)
VALUES 
(1, 1, '2025-02-01 19:00:00'),
(1, 1, '2025-03-01 19:00:00');
