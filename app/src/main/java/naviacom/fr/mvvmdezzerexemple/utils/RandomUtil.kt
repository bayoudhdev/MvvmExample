package com.bitbucket.stephenvinouze.betclicchallenge.extensions

import java.util.*

fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) +  start