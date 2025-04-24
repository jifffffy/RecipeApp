package org.jiffy.press.data.samples

import org.jiffy.press.domain.model.category.Category

val category1 = Category(
    id = "1",
    name = "Italian Cuisine",
    thumbnail = "https://example.com/italian-cuisine.jpg",
    description = "Italian cuisine is a Mediterranean cuisine consisting of the ingredients, recipes, and cooking techniques developed across the Italian Peninsula since antiquity and includes diverse cooking traditions of its 20 regions."
)
val category2 = Category(
    id = "2",
    name = "Asian Fusion",
    thumbnail = "https://example.com/asian-fusion.jpg",
    description = "Asian fusion cuisine combines elements from various Asian culinary traditions, often blending flavors and techniques from countries like Japan, China, Korea, and Thailand. This innovative approach creates unique and exciting dishes that appeal to a wide range of tastes."
)

val category3 = Category(
    id = "3",
    name = "",
    thumbnail = "",
    description = ""
)

val category4 = Category(
    id = "4",
    name = "Vegetarian Delights",
    thumbnail = "https://example.com/vegetarian.jpg",
    description = "Vegetarian cuisine focuses on plant-based ingredients, including vegetables, fruits, grains, legumes, nuts, and seeds. These dishes are often rich in nutrients and offer a variety of flavors and textures."
)

val category5 = Category(
    id = "5",
    name = "Mediterranean Flavors",
    thumbnail = "https://example.com/mediterranean.jpg",
    description = "Mediterranean cuisine is characterized by its use of fresh vegetables, olive oil, herbs, and seafood. Dishes from this region are known for their health benefits and vibrant flavors, often incorporating ingredients like tomatoes, olives, and citrus fruits."
)

val categoriesSampleData = listOf(category1, category2, category3, category4, category5)