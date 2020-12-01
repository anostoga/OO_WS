/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

package order

interface Orderable <R> {
    fun isBetterThan(other: R): Boolean
}

fun <T: Orderable<T>> List<T>.bestOrNull(): T? {
    if (this.isEmpty()) return null
    return this.reduce { champion, challenger ->
        if(challenger.isBetterThan(champion)) challenger else champion
    }
}