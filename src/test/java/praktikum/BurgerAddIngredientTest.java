package praktikum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import org.assertj.core.api.SoftAssertions;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerAddIngredientTest {

    private Burger burger;
    @Mock
    private Ingredient mockIngredientFirst;

    @Mock
    private Ingredient mockIngredientSecond;

    @Mock
    private Ingredient mockIngredientThird;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void addIngredientShouldIncreaseListSize() {

        int initialSize = burger.ingredients.size();
        burger.addIngredient(mockIngredientFirst);
        assertEquals("Размер списка должен увеличиться на 1", initialSize + 1, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldContainAddedIngredient() {
        burger.addIngredient(mockIngredientFirst);
        assertTrue("Список должен содержать добавленный ингредиент", burger.ingredients.contains(mockIngredientFirst));
    }


    @Test
    public void addIngredientShouldAddMultipleIngredients() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        assertEquals("Размер списка должен быть 2", 2, burger.ingredients.size());
    }



    @Test
    public void addIngredientShouldAddIngredientsInCorrectOrder() {
        // Проверяем порядок, а не поведение, поэтому, на мой взгляд, моки не нужны, но я не уверена, хотя это зависимость, оставлю так пока
        Ingredient firstIngredient = new Ingredient(IngredientType.SAUCE, "sauce1", 100f);
        Ingredient secondIngredient = new Ingredient(IngredientType.FILLING, "filling1", 150f);
        Ingredient thirdIngredient = new Ingredient(IngredientType.SAUCE, "sauce2", 120f);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.get(0))
                .as("Первый элемент должен быть sauce1")
                .isEqualTo(firstIngredient);
        softly.assertThat(burger.ingredients.get(1))
                .as("Второй элемент должен быть filling1")
                .isEqualTo(secondIngredient);
        softly.assertThat(burger.ingredients.get(2))
                .as("Третий элемент должен быть sauce2")
                .isEqualTo(thirdIngredient);
        softly.assertAll();
    }

    @Test
    public void addIngredientShouldAddIngredientWithNullFields() {
        Ingredient nullIngredient = new Ingredient(null, null, 0f);
        burger.addIngredient(nullIngredient);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.size()).as("Размер списка должен быть 1").isEqualTo(1);
        softly.assertThat(burger.ingredients.get(0).getType()).as("Тип ингредиента должен быть null").isNull();
        softly.assertThat(burger.ingredients.get(0).getName()).as("Имя ингредиента должно быть null").isNull();
        softly.assertThat(burger.ingredients.get(0).getPrice()).as("Цена ингредиента должна быть 0").isEqualTo(0f);
        softly.assertAll();
    }
}

