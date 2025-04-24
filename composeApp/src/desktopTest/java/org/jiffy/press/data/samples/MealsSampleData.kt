package org.jiffy.press.data.samples

import org.jiffy.press.domain.model.ingredient.Ingredient
import org.jiffy.press.domain.model.meal.Meal

val meal1 = Meal(
    id = "1",
    name = "Spaghetti Carbonara",
    alternateName = "Spaghetti alla Carbonara",
    category = "Pasta",
    area = "Italy",
    instructions = "1. Boil spaghetti in salted water.\n2. Fry pancetta in a pan.\n3. Mix eggs and Parmesan cheese.\n4. Combine pasta with pancetta and egg mixture.",
    thumbnailUrl = "https://example.com/spaghetti-carbonara.jpg",
    tags = listOf("pasta", "italian", "dinner"),
    youtubeUrl = "https://www.youtube.com/watch?v=example",
    ingredients = listOf(
        Ingredient("Spaghetti", "200g"),
        Ingredient("Pancetta", "100g"),
        Ingredient("Eggs", "2"),
        Ingredient("Parmesan Cheese", "50g")
    ),
    source = "https://example.com/recipe",
    imageSource = "https://example.com/image",
    creativeCommonsConfirmed = true,
    dateModified = "2025-04-20"
)

val meal2 = Meal(
    id = "2",
    name = "Tomato Soup",
    alternateName = null,
    category = "Soup",
    area = "",
    instructions = "1. Chop tomatoes and onions.\n2. Fry onions in oil.\n3. Add tomatoes and simmer.\n4. Blend until smooth.",
    thumbnailUrl = null,
    tags = null,
    youtubeUrl = "",
    ingredients = listOf(
        Ingredient("Tomatoes", "3"),
        Ingredient("Onions", "1"),
        Ingredient("Olive Oil", "2 tbsp")
    ),
    source = null,
    imageSource = null,
    creativeCommonsConfirmed = false,
    dateModified = null
)

val meal3 = Meal(
    id = "3",
    name = "Grilled Chicken",
    alternateName = "",
    category = "",
    area = "",
    instructions = "",
    thumbnailUrl = "",
    tags = listOf(),
    youtubeUrl = "",
    ingredients = listOf(),
    source = "",
    imageSource = "",
    creativeCommonsConfirmed = false,
    dateModified = ""
)

val meal4 = Meal(
    id = "4",
    name = "Vegetable Stir Fry",
    alternateName = "Mixed Vegetables",
    category = "Vegetarian",
    area = "Asia",
    instructions = "1. Stir fry vegetables in a wok.\n2. Add soy sauce and cook until tender.",
    thumbnailUrl = "https://example.com/vegetable-stir-fry.jpg",
    tags = listOf("vegetarian", "stir-fry", "quick"),
    youtubeUrl = "https://www.youtube.com/watch?v=example",
    ingredients = listOf(
        Ingredient("Broccoli", "1 head"),
        Ingredient("Carrots", "2"),
        Ingredient("Bell Peppers", "1"),
        Ingredient("Soy Sauce", "2 tbsp")
    ),
    source = "https://example.com/recipe",
    imageSource = "https://example.com/image",
    creativeCommonsConfirmed = true,
    dateModified = "2025-04-21"
)

val mealSampleData = listOf(meal1, meal2, meal3, meal4)


val meal5 = Meal(
    id = "5",
    name = "Chocolate Cake",
    alternateName = "Dark Chocolate Cake",
    category = "Dessert",
    area = "Europe",
    instructions = "1. Preheat oven to 180°C.\n2. Mix flour, sugar, cocoa powder, baking powder, and salt.\n3. Add eggs, milk, and oil. Stir until smooth.\n4. Pour batter into a greased pan.\n5. Bake for 30 minutes.",
    thumbnailUrl = "https://example.com/chocolate-cake.jpg",
    tags = listOf("dessert", "chocolate", "baking"),
    youtubeUrl = "https://www.youtube.com/watch?v=example",
    ingredients = listOf(
        Ingredient("Flour", "200g"),
        Ingredient("Sugar", "150g"),
        Ingredient("Cocoa Powder", "50g"),
        Ingredient("Baking Powder", "1 tsp"),
        Ingredient("Salt", "1/2 tsp"),
        Ingredient("Eggs", "2"),
        Ingredient("Milk", "100ml"),
        Ingredient("Vegetable Oil", "50ml")
    ),
    source = "https://example.com/recipe",
    imageSource = "https://example.com/image",
    creativeCommonsConfirmed = true,
    dateModified = "2025-04-22"
)
