package naviacom.fr.mvvmdezzerexemple.utils

import java.util.*

fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) +  start