(define (problem shakey_prob1)
  (:domain shakey)
  (:objects
    room-1 room-2 room-3
    door-1 door-2 door-3

    lswitch-1 lswitch-2 lswitch-3

    box shakey leftArm rightArm
    ball-1 ball-2 ball-3
    )

  (:init
    (connected room-1 room-2 door-1) (connected room-2 room-1 door-1)
    (connected room-2 room-3 door-2) (connected room-3 room-2 door-2)
    (connected room-2 room-3 door-3) (connected room-3 room-2 door-3)
    (in lswitch-1 room-1) (switchable lswitch-1)
    (in lswitch-2 room-2) (switchable lswitch-2)
    (in lswitch-3 room-3) (switchable lswitch-3)
    (wide door-1) (wide door-3)

    (arm leftArm) (arm rightArm) (free leftArm) (free rightArm)

    (in box room-2) (pushable box) (climbable box)
    (in ball-1 room-1) (carriable ball-1)
    (in ball-2 room-2) (carriable ball-2)
    (in ball-3 room-2) (carriable ball-3)
    (in shakey room-1) (movable shakey)
    )

  (:goal (and(in ball-1 room-3)
    (in ball-2 room-1) (in ball-3 room-3)(in shakey room-3))
  )
)
