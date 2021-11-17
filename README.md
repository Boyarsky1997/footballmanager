**~~Football manager**~~

Реалізувати систему керування футбольними командами/гравцями з використанням Java 8 (мінімум), Spring Boot, Hibernate/JDBC Template та Angular.

Система повинна підтримувати такий функціонал:

Backend:
базові CRUD операції для роботи з командами та гравцями (у відповідності до REST стилю)
операція трансферу гравця з однієї команди в іншу:
вартість трансферу = кількість місяців досвіду гравця * 100000 / вік гравця у роках
комісія зі сторони команди (від 0% до 10% від вартості трансферу) - вказується в інформації про команду
повна сума (вартість трансферу + комісія) повинна зніматись з рахунку команди, яка купує гравця, і переходити на рахунок команди, яка продає
Frontend:
виведення списку команд із основною інформацією про кожну команду (назва команди, місто, країна тощо) з можливістю додавання/видалення команд
виведення списку всіх гравців із основною інформацією про кожного гравця (ім’я, прізвище, дата початку кар’єри, команда тощо.) з можливістю додавання/видалення гравців
перехід на сторінку з детальною інформацією про команду та списком її гравців (з можливістю переходу на сторінку гравця), можливість редагування даних про команду
перехід на сторінку з детальною інформацією про гравця, можливість редагування даних про гравця, а також проведення операції трансферу, перехід на сторінку команди гравця

Реалізувати початкове наповнення бази даних гравців та команд.

Операції, що проводяться з гравцями/командами повинні валідуватись зі сторони клієнта та сервера.

Проект слід розмістити у публічному GitHub репозиторії (чи будь-якому іншому Git репозиторії на вибір). Проект повинен запускатись однією командою.

