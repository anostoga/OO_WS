package graph

import graph.Link.Companion.cost

class Path internal constructor() {
    private val links = mutableListOf<Link>()
    internal infix fun prepend(link: Link) = links.add(0, link)
    fun cost() = links.cost()
    fun hopCount() = links.size
}

internal typealias PathStrategy = (Path) -> Number
