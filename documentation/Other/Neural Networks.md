# Описание нейронных сетей

## Основная нейронная сеть
* Перцептрон

### Входные данные НС
* Получает информацию о последних двух неделях [4]
* Получает информацию о текущем времени (24 часа разбиением по 15 минут (96 разбиений)) [7]
* Получает информацию о текущих состояниях [ Sum(Resolution_n) ] <- НС комнат
* * Количество людей в комнате
* * Состояние устройств

### Выходные данные НС
* Выдаёт информацию о требуемых действиях [ Sum(Request_n) ] -> НС комнат

### Замечания
* n - количество оперируемых комнат


## Вариант комнаты №1

### Соответствующая НС
* NN_first.json

### Устройства ввода
* Датчик присутствия со счётчиком
* Термометр

### Устройства вывода
* Кондиционер

### Входные данные
* Количество людей в комнате
* Температура в комнате
* Управляющее действие от глобальной НС
* Управляющее действие от пользователя

### Выходные данные
* Количество людей в комнате -> глобальная НС
* Температура в комнате -> глобальная НС
* Принятое решение -> глобальная НС, устройство вывода

### Входные данные НС
* Количество людей в комнате [ 4 x [0; 1] ] <- Датчик присутствия со счётчиком
* * программирование количества людей в побитовом формате
* Температурные показатели в комнате [ 4 x [0; 1] ] <- Термометр
* * программирование температуры от 14-ти до 30 градусов в побитовом формате
* Требуемое действие [ 2 x [0; 1] ] <- Глобальная НС
* * требуемое действие в побитовом формате
* * 00 -> выключить устройство
* * 01 -> включить устройство
* * 10 -> понизить температуру
* * 11 -> повысить температуру
* Запрошенное действие [ 2 x [0; 1] ] <- Управляющее действие человека
* * Запрошенное действие в побитовом формате
* * 00 -> выключить устройство
* * 01 -> включить устройство
* * 10 -> понизить температуру
* * 11 -> повысить температуру

### Выходные данные НС
* Принятое решение [ 2 x [0; 1] ] -> Глобальная НС, устройство вывода


## Вариант комнаты №2

### Соответствующая НС
* NN_second.json

### Устройства ввода
* Термометр

### Устройства вывода
* Кондиционер

### Входные данные
* Температура в комнате
* Управляющее действие от глобальной НС
* Управляющее действие от пользователя

### Выходные данные
* Температура в комнате -> глобальная НС
* Принятое решение -> глобальная НС, устройство вывода

### Входные данные НС
* Температурные показатели в комнате [ 4 x [0; 1] ] <- Термометр
* * программирование температуры от 14-ти до 30 градусов в побитовом формате
* Требуемое действие [ 2 x [0; 1] ] <- Глобальная НС
* * требуемое действие в побитовом формате
* * 00 -> выключить устройство
* * 01 -> включить устройство
* * 10 -> понизить температуру
* * 11 -> повысить температуру
* Запрошенное действие [ 2 x [0; 1] ] <- Управляющее действие человека
* * Запрошенное действие в побитовом формате
* * 00 -> выключить устройство
* * 01 -> включить устройство
* * 10 -> понизить температуру
* * 11 -> повысить температуру

### Выходные данные НС
* Принятое решение [ 2 x [0; 1] ] -> Глобальная НС, устройство вывода


## Вариант комнаты №3

### Соответствующая НС
* NN_third.json

### Устройства ввода
* Отсутствуют

### Устройства вывода
* Кондиционер

### Входные данные
* Управляющее действие от глобальной НС
* Управляющее действие от пользователя

### Выходные данные
* Температура в комнате -> глобальная НС
* Принятое решение -> глобальная НС, устройство вывода

### Входные данные НС
* Требуемое действие [ 2 x [0; 1] ] <- Глобальная НС
* * требуемое действие в побитовом формате
* * 00 -> выключить устройство
* * 01 -> включить устройство
* * 10 -> понизить температуру
* * 11 -> повысить температуру
* Запрошенное действие [ 2 x [0; 1] ] <- Управляющее действие человека
* * Запрошенное действие в побитовом формате
* * 00 -> выключить устройство
* * 01 -> включить устройство
* * 10 -> понизить температуру
* * 11 -> повысить температуру

### Выходные данные НС
* Принятое решение [ 2 x [0; 1] ] -> Глобальная НС, устройство вывода
