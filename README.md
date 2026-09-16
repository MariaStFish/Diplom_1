# 🧪 Diplom_1: Юнит-тестирование класса Burger

## 📋 Описание проекта

Проект по юнит-тестированию класса `Burger` для приложения Stellar Burgers.  
Цель — покрыть класс `Burger` тестами с использованием **моков**, **стабов** и **параметризации**,  
а также обеспечить **100% покрытие кода** по отчёту **JaCoCo**.

---

## 🛠 Технологии и инструменты

| Технология | Версия | Назначение |
|------------|--------|------------|
| **Java** | 11 | Язык разработки |
| **JUnit 4** | 4.13.2 | Фреймворк для тестирования |
| **Mockito** | 4.11.0 | Моки и стабы |
| **JaCoCo** | 0.8.11 | Отчёт о покрытии кода |
| **Maven** | 3.x | Сборка проекта |

---

## 📁 Структура проекта
Diplom_1/
├── src/
│ ├── main/
│ │ └── java/
│ │ └── praktikum/
│ │ ├── Bun.java
│ │ ├── Burger.java
│ │ ├── Database.java
│ │ ├── Ingredient.java
│ │ ├── IngredientType.java
│ │ └── Praktikum.java
│ └── test/
│ └── java/
│ └── praktikum/
│ ├── BurgerAddIngredientTest.java
│ ├── BurgerGetPriceTest.java
│ ├── BurgerGetPriceParameterizedTest.java
│ ├── BurgerGetReceiptTest.java
│ ├── BurgerMoveIngredientTest.java
│ ├── BurgerRemoveIngredientTest.java
│ └── BurgerSetBunsTest.java
├── pom.xml
└── README.md

text

---

## 🧪 Что покрыто тестами

### `BurgerSetBunsTest`
- Установка булочки с корректными данными
- Перезапись предыдущей булочки
- Установка `null`

### `BurgerAddIngredientTest`
- Добавление одного ингредиента
- Добавление нескольких ингредиентов
- Сохранение порядка добавления
- Добавление ингредиента с `null` полями

### `BurgerRemoveIngredientTest`
- Удаление по индексу (первый, средний, последний)
- Ошибка при невалидном индексе (`IndexOutOfBoundsException`)

### `BurgerMoveIngredientTest`
- Перемещение ингредиента (в начало, в конец, на ту же позицию)
- Ошибка при невалидных индексах (`IndexOutOfBoundsException`)

### `BurgerGetPriceTest`
- Цена с булочкой и несколькими ингредиентами
- Цена только с булочкой
- Цена с одним ингредиентом
- Цена с пятью ингредиентами

### `BurgerGetPriceParameterizedTest`
- Параметризованная проверка разных комбинаций цен ингредиентов

### `BurgerGetReceiptTest`
- Корректный формат чека с ингредиентами
- Корректный формат чека без ингредиентов
- Корректный формат чека с несколькими ингредиентами
- Наличие цены в чеке
- Ошибка `NullPointerException` при отсутствии булочки

---

## 📊 Покрытие кода

| Класс | Покрытие |
|-------|----------|
| `Burger` | **100%** ✅ |

---

## 🚀 Как запустить

bash
mvn clean test
