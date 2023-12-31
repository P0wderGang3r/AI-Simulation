# JSON описание структуры дома

* * *
* Заголовок JSON - название файла с описанием дома 
* Название - header
* Тип - строка
* * *
* Название дома
* Название - name
* Тип - строка
* * *
* Путь до глобальной нейронной сети
* Название - path_NN
* Тип - строка
* * *
* Множество этажей
* Название - floors
* Тип - массив
* * *
* * Описание этажа - множество комнат
* * Тип - массив
* * Примечание - элемент массива, адресация по номеру
* * *
* * * Набор данных о комнате
* * * Тип - объект
* * * Примечание - элемент массива, адресация по номеру
* * *
* * * * Название комнаты
* * * * Название - name
* * * * Тип - строка
* * *
* * * * Тип комнаты
* * * * Название - room_type
* * * * Тип - строка
* * * * Принимаемые значения - "empty", "ladder", "kitchen", "hall", "bedroom"
* * *
* * * * Координаты комнаты - X
* * * * Название - coord_X
* * * * Тип - целое
* * *
* * * * Координаты комнаты - Y
* * * * Название - coord_Y
* * * * Тип - целое
* * *
* * * * Путь до нейронной сети
* * * * Название - path_NN
* * * * Тип - строка
* * *
* * * * Множество комнат, с которыми соединена рассматриваемая комната
* * * * Название - connections
* * * * Тип - массив
* * * 
* * * * * Комната, с которой соединена рассматриваемая комната
* * * * * Тип - строка
* * * * * Примечание - элемент массива, адресация по номеру
* * *
* * * * Набор устройств умного дома
* * * * Название - devices
* * * * Тип - массив
* * * 
* * * * * Устройство умного дома
* * * * * Тип - объект
* * * * * Примечание - элемент массива, адресация по номеру
* * *
* * * * * * Тип устройства
* * * * * * Название - device_type
* * * * * * Тип - строка
* * * * * * Принимаемые значения - "counter", "thermometer", "conditioner"
* * * * * * Примечание - принимаемые значения описаны в Devices.md