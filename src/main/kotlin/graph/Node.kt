/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

package graph

// Understands its neighbors
class Node {
    private val links = mutableListOf<Link>()

    companion object {
        private const val UNREACHABLE = Double.POSITIVE_INFINITY
    }

    infix fun canReach(destination: Node) = hopCount(destination, noVisitedNodes) != UNREACHABLE

    infix fun hopCount(destination: Node) = hopCount(destination, noVisitedNodes).also {
        require(it != UNREACHABLE) { "Destination is unreachable" }
    }.toInt()

    internal fun hopCount(destination: Node, visitedNodes: List<Node>): Double {
        if (this == destination) return 0.0
        if (this in visitedNodes) return UNREACHABLE
        return links.minOfOrNull { neighbor -> neighbor.hopCount(destination, visitedNodes + this) } ?: UNREACHABLE
    }

    infix fun cost(destination: Node) = cost(destination, noVisitedNodes).also {
        require(it != UNREACHABLE) { "Destination is unreachable" }
    }

    internal fun cost(destination: Node, visitedNodes: List<Node>): Double {
        if (this == destination) return 0.0
        if (this in visitedNodes) return UNREACHABLE
        return links.minOfOrNull { neighbor -> neighbor.cost(destination, visitedNodes + this) } ?: UNREACHABLE
    }

    private val noVisitedNodes = emptyList<Node>()

    infix fun cost(amount: Number) = LinkBuilder(amount.toDouble(), links)

    class LinkBuilder internal constructor(private val cost: Double, private val links: MutableList<Link>) {
        infix fun to(neighbor: Node) = neighbor.also { links.add(Link(neighbor, cost)) }
    }
}