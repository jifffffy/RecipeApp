package org.jiffy.press.data.samples

import org.jiffy.press.domain.model.ingredient.Ingredient

val ingredient1 = Ingredient(
    id = "1",
    name = "Tomato",
    description = "A red, juicy fruit commonly used in cooking.",
    type = "Vegetable",
    measure = "1 cup"
)

val ingredient2 = Ingredient(
    id = "2",
    name = "Salt",
    description = null,
    type = null,
    measure = null
)

val ingredient3 = Ingredient(
    id = "3",
    name = "Olive Oil",
    description = "A liquid fat obtained from olives.",
    type = "Oil",
    measure = "2 tbsp"
)
val ingredient4 = Ingredient(
    id = "4",
    name = "Parmesan Cheese",
    description = "A hard, granular cheese, originally from Italy, made from cow's milk. It has a sharp, tangy flavor and is often grated over pasta dishes.",
    type = "Dairy",
    measure = "50g"
)

val ingredient5 = Ingredient(
    id = "5",
    name = "",
    description = "",
    type = "",
    measure = ""
)

val ingredient6 = Ingredient(
    id = "6",
    name = "Garlic",
    description = "A small, pungent bulb used for seasoning.",
    type = "Herb",
    measure = "3 cloves"
)

val ingredient7 = Ingredient(
    id = "7",
    name = "Cinnamon",
    description = "A spice obtained from the inner bark of several tree species from the genus Cinnamomum.",
    type = "Spice",
    measure = "1 tsp"
)

val ingredientsSampleData = listOf(
    ingredient1,
    ingredient2,
    ingredient3,
    ingredient4,
    ingredient5,
    ingredient6,
    ingredient7
)

