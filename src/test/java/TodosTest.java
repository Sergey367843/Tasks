import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTasks() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить Хлеб");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Обсуждение проекта",
                "Проект Хлебозавод",
                "Завтра в 10:00"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        // Поиск по слову "Хлеб" - должны найти все три задачи
        Task[] result = todos.search("Хлеб");
        Task[] expected = {simpleTask, epic, meeting};
        Assertions.assertArrayEquals(expected, result);

        // Поиск по слову "Молоко" - должен найти только Epic
        Task[] result2 = todos.search("Молоко");
        Task[] expected2 = {epic};
        Assertions.assertArrayEquals(expected2, result2);

        // Поиск по слову "Проект" - должен найти только Meeting
        Task[] result3 = todos.search("Проект");
        Task[] expected3 = {meeting};
        Assertions.assertArrayEquals(expected3, result3);

        // Поиск по несуществующему слову
        Task[] result4 = todos.search("Несуществующее");
        Task[] expected4 = {};
        Assertions.assertArrayEquals(expected4, result4);
    }

    @Test
    public void shouldSearchWhenNoTasks() {
        Todos todos = new Todos();

        Task[] result = todos.search("что-то");
        Task[] expected = {};

        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void shouldNotFindTasksWithEmptyQuery() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);

        Task[] result = todos.search("");
        Task[] expected = {};

        Assertions.assertArrayEquals(expected, result);
    }
}