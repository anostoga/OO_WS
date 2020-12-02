/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

package graph

internal class Link(private val target: Node, private val cost: Double) {
    companion object {
        internal fun List<Link>.cost() = this.sumByDouble { it.cost }
    }

    internal fun paths(destination: Node, visitedNodes: List<Node>) =
        target.paths(destination, visitedNodes).onEach { it.prepend(this) }
}
