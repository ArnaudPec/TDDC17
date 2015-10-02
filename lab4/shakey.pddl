;; Shakey's World lab3

(define (domain shakey)
  (:requirements :strips :typing)
  (:predicates

  (:types )

  (:action move
    :parameters (?disc ?from ?to)
    :precondition (and (smaller ?to ?disc) (on ?disc ?from)
		       (clear ?disc) (clear ?to))
    :effect  (and (clear ?from) (on ?disc ?to) (not (on ?disc ?from))
		  (not (clear ?to))))
  )

  (:action push

  )

  (:action climb

  )

  (:action turnon

  )
