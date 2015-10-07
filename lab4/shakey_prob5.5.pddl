(define (problem shakey_prob5.5)
  (:domain shakey)
  (:objects

    room-1 room-2 room-3
    room-4 room-5 room-6 room-7 room-8
    room-9 room-10 room-11

    door-1 door-2 door-3
    door-4 door-5 door-6 door-7 door-8
    door-9 door-10 door-11

    lswitch-1 lswitch-2 lswitch-3
    lswitch-4 lswitch-5 lswitch-6
    lswitch-7 lswitch-8 lswitch-9

    box shakey leftArm rightArm
    ball-1 ball-2 ball-3
    ball-4 ball-5 ball-6
    )

  (:init
    (connected room-1 room-2 door-1) (connected room-2 room-1 door-1)
    (connected room-2 room-3 door-2) (connected room-3 room-2 door-2)
    (connected room-2 room-3 door-3) (connected room-3 room-2 door-3)
    (connected room-1 room-4 door-4) (connected room-4 room-1 door-4)
    (connected room-3 room-6 door-5) (connected room-4 room-1 door-6)
    (connected room-4 room-5 door-6) (connected room-5 room-4 door-6)
    (connected room-5 room-6 door-7) (connected room-6 room-5 door-7)
    (connected room-5 room-8 door-8) (connected room-8 room-5 door-8)
    (connected room-6 room-9 door-9) (connected room-9 room-6 door-9)
    (connected room-7 room-8 door-10) (connected room-8 room-7 door-10)
    (connected room-8 room-9 door-11) (connected room-9 room-8 door-11)


    (in lswitch-1 room-1) (switchable lswitch-1)
    (in lswitch-2 room-2) (switchable lswitch-2)
    (in lswitch-3 room-3) (switchable lswitch-3)
    (in lswitch-4 room-4) (switchable lswitch-4)
    (in lswitch-5 room-5) (switchable lswitch-5)
    (in lswitch-6 room-6) (switchable lswitch-6)
    (in lswitch-7 room-7) (switchable lswitch-7)
    (in lswitch-8 room-8) (switchable lswitch-8)
    (in lswitch-9 room-9) (switchable lswitch-9)

    (wide door-1) (wide door-3) (wide door-4)
    (wide door-6) (wide door-7) (wide door-8)
    (wide door-10) (wide door-11)

    (arm leftArm) (arm rightArm) (free leftArm) (free rightArm)

    (in box room-1) (pushable box) (climbable box)
    (in ball-1 room-3) (carriable ball-1)
    (in ball-2 room-6) (carriable ball-2)
    (in ball-3 room-9) (carriable ball-3)
    (in ball-4 room-3) (carriable ball-4)
    (in ball-5 room-3) (carriable ball-5)
    (in ball-6 room-9) (carriable ball-6)

    (in shakey room-2) (movable shakey)
    )

  (:goal (and(in ball-1 room-9)
    (in ball-3 room-1)
    (in ball-5 room-7)
    (in ball-6 room-2)))
)
