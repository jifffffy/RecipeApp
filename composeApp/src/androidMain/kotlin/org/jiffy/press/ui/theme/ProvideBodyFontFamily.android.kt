
package org.jiffy.press.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import recipeapp.composeapp.generated.resources.Res
import recipeapp.composeapp.generated.resources.pathwayextreme_italic_variablefont
import recipeapp.composeapp.generated.resources.pathwayextreme_variablefont
import org.jetbrains.compose.resources.Font

@Composable
actual fun provideBodyFontFamily(fontFamily: FontFamily): FontFamily {
    return FontFamily(
        Font(resource = Res.font.pathwayextreme_variablefont, weight = FontWeight.W100, style = FontStyle.Normal),
        Font(resource = Res.font.pathwayextreme_variablefont, weight = FontWeight.W200, style = FontStyle.Normal),
        Font(resource = Res.font.pathwayextreme_variablefont, weight = FontWeight.W300, style = FontStyle.Normal),
        Font(resource = Res.font.pathwayextreme_variablefont, weight = FontWeight.W400, style = FontStyle.Normal),
        Font(resource = Res.font.pathwayextreme_variablefont, weight = FontWeight.W500, style = FontStyle.Normal),
        Font(resource = Res.font.pathwayextreme_variablefont, weight = FontWeight.W600, style = FontStyle.Normal),
        Font(resource = Res.font.pathwayextreme_variablefont, weight = FontWeight.W700, style = FontStyle.Normal),
        Font(resource = Res.font.pathwayextreme_variablefont, weight = FontWeight.W800, style = FontStyle.Normal),
        Font(resource = Res.font.pathwayextreme_variablefont, weight = FontWeight.W900, style = FontStyle.Normal),
        Font(resource = Res.font.pathwayextreme_italic_variablefont, weight = FontWeight.W100, style = FontStyle.Italic),
        Font(resource = Res.font.pathwayextreme_italic_variablefont, weight = FontWeight.W200, style = FontStyle.Italic),
        Font(resource = Res.font.pathwayextreme_italic_variablefont, weight = FontWeight.W300, style = FontStyle.Italic),
        Font(resource = Res.font.pathwayextreme_italic_variablefont, weight = FontWeight.W400, style = FontStyle.Italic),
        Font(resource = Res.font.pathwayextreme_italic_variablefont, weight = FontWeight.W500, style = FontStyle.Italic),
        Font(resource = Res.font.pathwayextreme_italic_variablefont, weight = FontWeight.W600, style = FontStyle.Italic),
        Font(resource = Res.font.pathwayextreme_italic_variablefont, weight = FontWeight.W700, style = FontStyle.Italic),
        Font(resource = Res.font.pathwayextreme_italic_variablefont, weight = FontWeight.W800, style = FontStyle.Italic),
        Font(resource = Res.font.pathwayextreme_italic_variablefont, weight = FontWeight.W900, style = FontStyle.Italic),
    )
}
