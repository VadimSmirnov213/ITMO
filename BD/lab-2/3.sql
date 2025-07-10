SELECT 
    CASE 
        WHEN EXISTS (
            SELECT 1
            FROM Н_ЛЮДИ
            JOIN Н_ОБУЧЕНИЯ ON Н_ЛЮДИ.ИД = Н_ОБУЧЕНИЯ.ЧЛВК_ИД
            JOIN Н_УЧЕНИКИ ON Н_ОБУЧЕНИЯ.ИД = Н_УЧЕНИКИ.ОБУЧЕНИЕ_ИД
            WHERE Н_УЧЕНИКИ.ГРУППА = '3102'
            AND (EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM Н_ЛЮДИ.ДАТА_РОЖДЕНИЯ)) > 25
        ) THEN 'Да, есть студенты старше 25 лет в группе 3102'
        ELSE 'Нет студентов старше 25 лет в группе 3102'
    END AS Результат;