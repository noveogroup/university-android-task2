university-android-task2
========================
# Задание
Написать приложение отображающее информацию о людях.

Объект Person имеет следующие поля: name, age, gender. В проекте уже есть автоматический генератор объектов PersonProvider.

В портретной ориентации отображается список людей. Над списком необходимо расположить элементы сортировки - две RadioGroup, каждая состоящая из двух RadioButton. Первая группа указывает поле сортировки - age или gender, вторая - тип сортировки - по возрастанию или по убыванию. Кроме того, список должен быть отсортирован по алфавиту (по имени).

В лендскейпной ориентации список должен отображаться в правой части экрана, элементы сортировки - в левой.

Над списком должна быть FloatingActionButton, по нажатию на которую в список добавляется новый элемент (генерируется PersonProvider'ом).

Список должен анимированно добавлять, переставлять при добавлении и сортировке элементы, а также поддерживать удаление элемента при смахивании (swipe)