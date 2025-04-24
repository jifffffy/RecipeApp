package org.jiffy.press.data.repository.mapper

import org.jiffy.press.data.source.network.dto.CategoriesApiResponse
import org.jiffy.press.domain.model.category.Category

fun CategoriesApiResponse.mapToDomain(): List<Category> {
    return categories.map { categoryResponse ->
        Category(
            id = categoryResponse.idCategory,
            name = categoryResponse.strCategory,
            thumbnail = categoryResponse.strCategoryThumb,
            description = categoryResponse.strCategoryDescription,
        )
    }
}