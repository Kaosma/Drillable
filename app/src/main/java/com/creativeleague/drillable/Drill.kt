package com.creativeleague.drillable

import java.io.*

class Drill(val name: String = "", val length: Int = 0, val content: String = "", val rating: MutableMap<String, Int>? = null, var id: String = "") : Serializable