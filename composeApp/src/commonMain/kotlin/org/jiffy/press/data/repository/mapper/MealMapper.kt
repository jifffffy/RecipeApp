package org.jiffy.press.data.repository.mapper

import org.jiffy.press.data.source.network.dto.FilterMealsApiResponse
import org.jiffy.press.data.source.network.dto.MealsApiResponse
import org.jiffy.press.domain.model.ingredient.Ingredient
import org.jiffy.press.domain.model.meal.Meal

// Mapper function to convert MealsApiResponse to a list of Meal objects
fun MealsApiResponse.mapToDomain(): List<Meal> {
    return meals.map { mealResponse ->
        val ingredients = listOfNotNull(
            mealResponse.strIngredient1 to mealResponse.strMeasure1,
            mealResponse.strIngredient2 to mealResponse.strMeasure2,
            mealResponse.strIngredient3 to mealResponse.strMeasure3,
            mealResponse.strIngredient4 to mealResponse.strMeasure4,
            mealResponse.strIngredient5 to mealResponse.strMeasure5,
            mealResponse.strIngredient6 to mealResponse.strMeasure6,
            mealResponse.strIngredient7 to mealResponse.strMeasure7,
            mealResponse.strIngredient8 to mealResponse.strMeasure8,
            mealResponse.strIngredient9 to mealResponse.strMeasure9,
            mealResponse.strIngredient10 to mealResponse.strMeasure10,
            mealResponse.strIngredient11 to mealResponse.strMeasure11,
            mealResponse.strIngredient12 to mealResponse.strMeasure12,
            mealResponse.strIngredient13 to mealResponse.strMeasure13,
            mealResponse.strIngredient14 to mealResponse.strMeasure14,
            mealResponse.strIngredient15 to mealResponse.strMeasure15,
            mealResponse.strIngredient16 to mealResponse.strMeasure16,
            mealResponse.strIngredient17 to mealResponse.strMeasure17,
            mealResponse.strIngredient18 to mealResponse.strMeasure18,
            mealResponse.strIngredient19 to mealResponse.strMeasure19,
            mealResponse.strIngredient20 to mealResponse.strMeasure20
        ).filter { (ingredient, measure) -> ingredient?.isNotEmpty() == true && measure?.isNotEmpty() == true }
            .map { (ingredient, measure) -> Ingredient(ingredient!!, measure!!) }

        val tags = mealResponse.strTags?.split(",")?.filter { it.isNotEmpty() }

        Meal(
            id = mealResponse.idMeal,
            name = mealResponse.strMeal,
            alternateName = mealResponse.strMealAlternate.orEmpty(),
            category = mealResponse.strCategory,
            area = mealResponse.strArea,
            instructions = mealResponse.strInstructions,
            thumbnailUrl = mealResponse.strMealThumb,
            tags = tags,
            youtubeUrl = mealResponse.strYoutube,
            ingredients = ingredients,
            source = mealResponse.strSource.orEmpty(),
            imageSource = mealResponse.strImageSource.orEmpty(),
            creativeCommonsConfirmed = mealResponse.strCreativeCommonsConfirmed?.toBooleanStrictOrNull(),
            dateModified = mealResponse.dateModified.orEmpty()
        )
    }
}

fun FilterMealsApiResponse.mapToDomain(): List<Meal> {
    return meals.map {
        Meal(
            id = it.idMeal,
            name = it.strMeal,
            thumbnailUrl = it.strMealThumb
        )
    }
}