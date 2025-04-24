package org.jiffy.press.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import recipeapp.composeapp.generated.resources.Res
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_black
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_blackitalic
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_bold
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_bolditalic
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_extrabold
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_extrabolditalic
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_extralight
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_extralightitalic
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_italic
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_light
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_lightitalic
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_medium
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_mediumitalic
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_regular
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_semibold
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_semibolditalic
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_thin
import recipeapp.composeapp.generated.resources.pathwayextreme_14pt_thinitalic

@Composable
expect fun provideBodyFontFamily(
    fontFamily: FontFamily = FontFamily(
        Font(
            resource = Res.font.pathwayextreme_14pt_black,
            weight = FontWeight.Black,
            style = FontStyle.Normal
        ),
        Font(
            resource = Res.font.pathwayextreme_14pt_bold,
            weight = FontWeight.Bold,
            style = FontStyle.Normal
        ),
        Font(
            resource = Res.font.pathwayextreme_14pt_extrabold,
            weight = FontWeight.ExtraBold,
            style = FontStyle.Normal
        ),
        Font(
            resource = Res.font.pathwayextreme_14pt_extralight,
            weight = FontWeight.ExtraLight,
            style = FontStyle.Normal
        ),
        Font(
            resource = Res.font.pathwayextreme_14pt_light,
            weight = FontWeight.Light,
            style = FontStyle.Normal
        ),
        Font(
            resource = Res.font.pathwayextreme_14pt_medium,
            weight = FontWeight.Medium,
            style = FontStyle.Normal
        ),
        Font(
            resource = Res.font.pathwayextreme_14pt_regular,
            weight = FontWeight.Normal,
            style = FontStyle.Normal
        ),
        Font(
            resource = Res.font.pathwayextreme_14pt_semibold,
            weight = FontWeight.SemiBold,
            style = FontStyle.Normal
        ),
        Font(
            resource = Res.font.pathwayextreme_14pt_thin,
            weight = FontWeight.Thin,
            style = FontStyle.Normal
        ),

        Font(
            resource = Res.font.pathwayextreme_14pt_blackitalic,
            weight = FontWeight.Black,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.pathwayextreme_14pt_bolditalic,
            weight = FontWeight.Bold,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.pathwayextreme_14pt_extrabolditalic,
            weight = FontWeight.ExtraBold,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.pathwayextreme_14pt_extralightitalic,
            weight = FontWeight.ExtraLight,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.pathwayextreme_14pt_lightitalic,
            weight = FontWeight.Light,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.pathwayextreme_14pt_mediumitalic,
            weight = FontWeight.Medium,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.pathwayextreme_14pt_italic,
            weight = FontWeight.Normal,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.pathwayextreme_14pt_semibolditalic,
            weight = FontWeight.SemiBold,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.pathwayextreme_14pt_thinitalic,
            weight = FontWeight.Thin,
            style = FontStyle.Italic
        ),
    ),
): FontFamily
