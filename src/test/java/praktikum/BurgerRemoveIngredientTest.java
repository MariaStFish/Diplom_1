package praktikum;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerRemoveIngredientTest {

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
    public void removeIngredientShouldRemoveIngredientAtIndex() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);

        int initialSize = burger.ingredients.size();
        burger.removeIngredient(1);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.size())
                .as("Размер списка должен уменьшиться на 1")
                .isEqualTo(initialSize - 1);
        softly.assertThat(burger.ingredients.contains(mockIngredientSecond))
                .as("Список не должен содержать удалённый ингредиент")
                .isFalse();
        softly.assertThat(burger.ingredients.contains(mockIngredientFirst))
                .as("Первый ингредиент должен остаться")
                .isTrue();
        softly.assertThat(burger.ingredients.contains(mockIngredientThird))
                .as("Третий ингредиент должен остаться")
                .isTrue();
        softly.assertAll();
    }


    @Test
    public void removeIngredientShouldRemoveFirstIngredient() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);

        burger.removeIngredient(0);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.size())
                .as("Размер списка должен быть 1")
                .isEqualTo(1);
        softly.assertThat(burger.ingredients.contains(mockIngredientFirst))
                .as("Первый ингредиент должен быть удалён")
                .isFalse();
        softly.assertThat(burger.ingredients.contains(mockIngredientSecond))
                .as("Второй ингредиент должен остаться")
                .isTrue();
        softly.assertAll();
    }

    @Test
    public void removeIngredientShouldRemoveLastIngredient() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);

        burger.removeIngredient(1);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.size())
                .as("Размер списка должен быть 1")
                .isEqualTo(1);
        softly.assertThat(burger.ingredients.contains(mockIngredientFirst))
                .as("Первый ингредиент должен остаться")
                .isTrue();
        softly.assertThat(burger.ingredients.contains(mockIngredientSecond))
                .as("Второй ингредиент должен быть удалён")
                .isFalse();
        softly.assertAll();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientShouldThrowExceptionWhenIndexOutOfBounds() {
        burger.removeIngredient(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientShouldThrowExceptionWhenIndexIsNegative() {
        burger.removeIngredient(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientShouldThrowExceptionWhenIndexIsTooLarge() {
        burger.addIngredient(mockIngredientFirst);
        burger.removeIngredient(5);
    }
}
